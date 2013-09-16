package br.com.orlandoburli.core.extras.nfe.utils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.nfsaida.ItemNFSaidaDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.extras.nfe.interfaces.IServico.Ambiente;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.extras.nfe.model.details.Det;
import br.com.orlandoburli.core.extras.nfe.services.NfeCancelamentoService;
import br.com.orlandoburli.core.extras.nfe.services.NfeConsultaNFService;
import br.com.orlandoburli.core.extras.nfe.services.NfeRecepcaoService;
import br.com.orlandoburli.core.extras.nfe.services.NfeRetRecepcaoService;
import br.com.orlandoburli.core.extras.nfe.services.NfeStatusServicoService;
import br.com.orlandoburli.core.extras.nfe.utils.NfeSigner.TagSign;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.utils.Utils.RemoverAcentos;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.base.EstadoVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.TributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.LogNFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.TipoRegistroNFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class NFSaidaToNfe {

	public static void execute(NFSaidaVO nfsaida) throws SQLException {
		GenericDAO _dao = new GenericDAO();
		_dao.setAutoCommit(false);

		try {
			LojaVO loja = new LojaVO();
			loja.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			loja.setCodigoLoja(nfsaida.getCodigoLoja());
			loja = (LojaVO) _dao.get(loja);

			// String amb = SystemManager.getProperty("nfe.ambiente");
			ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();
			String amb = paramLojaDao.getStringParametro(Constants.Parameters.NFe.AMBIENTE_NFE, loja.getCodigoEmpresa(), loja.getCodigoLoja());

			Ambiente ambiente = amb.equals("producao") ? Ambiente.Producao : Ambiente.Homologacao;

			nfsaida = (NFSaidaVO) _dao.get(nfsaida);

			if (nfsaida.getStatusNFSaida().equals(NFEletronicaVO.STATUS_AUTORIZADA) || nfsaida.getStatusNFSaida().equals(NFEletronicaVO.STATUS_CANCELADA)) {
				return;
			}

			// Verifica se já existe a NF-e gerada
			NFEletronicaVO nfeVo = new NFEletronicaVO();
			nfeVo.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			nfeVo.setCodigoLoja(nfsaida.getCodigoLoja());
			nfeVo.setCodigoNFe(nfsaida.getCodigoNFSaida());

			nfeVo = (NFEletronicaVO) _dao.get(nfeVo);

			if (nfeVo != null) {
				nfsaida.setCodigoNfe(null);
				_dao.persist(nfsaida);

				// Limpar todos os registros existentes...
				LogNFEletronicaVO _filter = new LogNFEletronicaVO();
				_filter.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
				_filter.setCodigoLoja(nfeVo.getCodigoLoja());
				_filter.setCodigoNFe(nfeVo.getCodigoNFe());

				List<IValueObject> listLog = _dao.getList(_filter);

				for (IValueObject item : listLog) {
					_dao.remove(item);
				}

				_dao.remove(nfeVo);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			// Dados do Destinatário
			PessoaVO destinatario = new PessoaVO();
			destinatario.setCodigoEmpresa(nfsaida.getCodigoEmpresaDestinatarioNFSaida());
			destinatario.setCodigoLoja(nfsaida.getCodigoLojaDestinatarioNFSaida());
			destinatario.setCodigoPessoa(nfsaida.getCodigoPessoaDestinatarioNFSaida());

			destinatario = (PessoaVO) _dao.get(destinatario);

			EnderecoPessoaVO enderecoDestinatario = new EnderecoPessoaVO();
			enderecoDestinatario.setCodigoEmpresa(nfsaida.getCodigoEmpresaDestinatarioNFSaida());
			enderecoDestinatario.setCodigoLoja(nfsaida.getCodigoLojaDestinatarioNFSaida());
			enderecoDestinatario.setCodigoPessoa(nfsaida.getCodigoPessoaDestinatarioNFSaida());
			enderecoDestinatario.setCodigoEnderecoPessoa(nfsaida.getCodigoEnderecoPessoaDestinatarioNFSaida());

			enderecoDestinatario = (EnderecoPessoaVO) _dao.get(enderecoDestinatario);

			CidadeVO cidadeDestinatario = new CidadeVO();
			cidadeDestinatario.setCodigoCidade(enderecoDestinatario.getCodigoCidadeEnderecoPessoa());
			cidadeDestinatario = (CidadeVO) _dao.get(cidadeDestinatario);

			EstadoVO ufDestinatario = new EstadoVO();
			ufDestinatario.setCodigoEstado(cidadeDestinatario.getCodigoEstado());
			ufDestinatario = (EstadoVO) _dao.get(ufDestinatario);

			// Dados do Emitente
			EmpresaVO empresa = new EmpresaVO();
			empresa.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			empresa = (EmpresaVO) _dao.get(empresa);

			CidadeVO cidadeEmitente = new CidadeVO();
			cidadeEmitente.setCodigoCidade(loja.getCodigoCidadeLoja());
			cidadeEmitente = (CidadeVO) _dao.get(cidadeEmitente);

			EstadoVO ufEmitente = new EstadoVO();
			ufEmitente.setCodigoEstado(cidadeEmitente.getCodigoEstado());
			ufEmitente = (EstadoVO) _dao.get(ufEmitente);

			// Dados da Transportadora
			PessoaVO transportadora = new PessoaVO();
			transportadora.setCodigoEmpresa(nfsaida.getCodigoEmpresaTransportadoraNFSaida());
			transportadora.setCodigoLoja(nfsaida.getCodigoLojaTransportadoraNFSaida());
			transportadora.setCodigoPessoa(nfsaida.getCodigoPessoaTransportadoraNFSaida());

			transportadora = (PessoaVO) _dao.get(transportadora);

			EnderecoPessoaVO enderecoTransportadora = new EnderecoPessoaVO();
			enderecoTransportadora.setCodigoEmpresa(nfsaida.getCodigoEmpresaTransportadoraNFSaida());
			enderecoTransportadora.setCodigoLoja(nfsaida.getCodigoLojaTransportadoraNFSaida());
			enderecoTransportadora.setCodigoPessoa(nfsaida.getCodigoPessoaTransportadoraNFSaida());
			enderecoTransportadora.setCodigoEnderecoPessoa(nfsaida.getCodigoEnderecoPessoaTransportadoraNFSaida());

			enderecoTransportadora = (EnderecoPessoaVO) _dao.get(enderecoTransportadora);

			CidadeVO cidadeTransportadora = new CidadeVO();

			if (enderecoTransportadora != null) {
				cidadeTransportadora.setCodigoCidade(enderecoTransportadora.getCodigoCidadeEnderecoPessoa());
				cidadeTransportadora = (CidadeVO) _dao.get(cidadeTransportadora);
			}

			EstadoVO ufTransportadora = new EstadoVO();

			if (enderecoTransportadora != null) {
				ufTransportadora.setCodigoEstado(cidadeTransportadora.getCodigoEstado());
				ufTransportadora = (EstadoVO) _dao.get(ufTransportadora);
			}

			ItemNFSaidaVO filter = new ItemNFSaidaVO();
			filter.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			filter.setCodigoLoja(nfsaida.getCodigoLoja());
			filter.setCodigoNFSaida(nfsaida.getCodigoNFSaida());
			filter.setSerieNFSaida(nfsaida.getSerieNFSaida());

			ItemNFSaidaDAO itemDao = new ItemNFSaidaDAO();

			List<ItemNFSaidaVO> list = itemDao.getList(filter, "SequencialItemNFSaida");

			// Cria a NF-e
			NFe nfe = NFe.getInstance();

			// Identificação da Nota Fiscal
			nfe.getInfNfe().getIde().setcMunFG(ufEmitente.getCodigoIbgeEstado() + cidadeEmitente.getCodigoIbgeCidade());
			nfe.getInfNfe().getIde().setcNF("093749528");
			nfe.getInfNfe().getIde().setnNF(nfsaida.getCodigoNFSaida().toString());
			nfe.getInfNfe().getIde().setcUF(ufEmitente.getCodigoIbgeEstado());
			nfe.getInfNfe().getIde().setdEmi(sdf.format(nfsaida.getDataEmissaoNFSaida()));
			nfe.getInfNfe().getIde().setdSaiEnt(sdf.format(nfsaida.getDataEmissaoNFSaida()));
			nfe.getInfNfe().getIde().setFinNFe("1");
			nfe.getInfNfe().getIde().setIndPag(nfsaida.getFormaPagamentoNFSaida().toString());
			nfe.getInfNfe().getIde().setMod(nfsaida.getModeloNFSaida());
			nfe.getInfNfe().getIde().setNatOp("VENDA DE MERCADORIAS");
			nfe.getInfNfe().getIde().setProcEmi("0");
			nfe.getInfNfe().getIde().setSerie(nfsaida.getSerieNFSaida());
			nfe.getInfNfe().getIde().setTpAmb(ambiente.equals(Ambiente.Producao) ? "1" : "2");
			nfe.getInfNfe().getIde().setTpEmis("1");
			nfe.getInfNfe().getIde().setTpNF("1");
			nfe.getInfNfe().getIde().setTpImp("1");
			nfe.getInfNfe().getIde().setVerProc("PersonalERP 1.0.2.0");

			// Dados do Emitente
			nfe.getInfNfe().getEmit().setCNPJ(loja.getCNPJLoja());
			nfe.getInfNfe().getEmit().setxNome(RemoverAcentos.remover(empresa.getRazaoSocialEmpresa()));
			nfe.getInfNfe().getEmit().setIE(loja.getInscricaoEstadualLoja().replace(".", "").replace("-", ""));
			nfe.getInfNfe().getEmit().setCRT("1"); // TODO - Parametrizar a Loja

			// Endereco do Emitente
			nfe.getInfNfe().getEmit().getEnderEmit().setxLgr(RemoverAcentos.remover(loja.getEnderecoLoja()));
			nfe.getInfNfe().getEmit().getEnderEmit().setNro("0");
			nfe.getInfNfe().getEmit().getEnderEmit().setxBairro(RemoverAcentos.remover(loja.getBairroLoja()));
			nfe.getInfNfe().getEmit().getEnderEmit().setcMun(ufEmitente.getCodigoIbgeEstado() + cidadeEmitente.getCodigoIbgeCidade());
			nfe.getInfNfe().getEmit().getEnderEmit().setxMun(RemoverAcentos.remover(cidadeEmitente.getNomeCidade()));
			nfe.getInfNfe().getEmit().getEnderEmit().setUF(ufEmitente.getSiglaEstado());
			nfe.getInfNfe().getEmit().getEnderEmit().setCEP(loja.getCepLoja());

			String fone = loja.getFoneLoja();

			if (fone != null) {
				fone = fone.replace("(", "");
				fone = fone.replace(")", "");
				fone = fone.replace("-", "");
				fone = fone.replace(" ", "");
			}

			nfe.getInfNfe().getEmit().getEnderEmit().setFone(fone);

			// Dados do destinatário
			if (ambiente == Ambiente.Producao) {
				nfe.getInfNfe().getDest().setCNPJ(enderecoDestinatario.getCpfCnpjEnderecoPessoa());
				nfe.getInfNfe().getDest().setxNome(RemoverAcentos.remover(destinatario.getNomeRazaoSocialPessoa()));
			} else {
				nfe.getInfNfe().getDest().setCNPJ("99999999000191");
				nfe.getInfNfe().getDest().setxNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			}

			// nfe.getInfNfe().getDest().setIE(RemoverAcentos.remover(enderecoDestinatario.getInscricaoEstadualEnderecoPessoa()));
			// nfe.getInfNfe().getDest().setIM(RemoverAcentos.remover(enderecoDestinatario.getInscricaoMunicipalEnderecoPessoa()));
			// nfe.getInfNfe().getDest().set

			// Endereco do destinatário
			nfe.getInfNfe().getDest().getEnderDest().setxLgr(RemoverAcentos.remover(enderecoDestinatario.getLogradouroEnderecoPessoa()));
			nfe.getInfNfe().getDest().getEnderDest().setNro("0");
			nfe.getInfNfe().getDest().getEnderDest().setxBairro(RemoverAcentos.remover(enderecoDestinatario.getBairroEnderecoPessoa()));
			nfe.getInfNfe().getDest().getEnderDest().setcMun(ufDestinatario.getCodigoIbgeEstado() + cidadeDestinatario.getCodigoIbgeCidade());
			nfe.getInfNfe().getDest().getEnderDest().setxMun(RemoverAcentos.remover(cidadeDestinatario.getNomeCidade()));
			nfe.getInfNfe().getDest().getEnderDest().setUF(ufDestinatario.getSiglaEstado());
			nfe.getInfNfe().getDest().getEnderDest().setCEP(enderecoDestinatario.getCepEnderecoPessoa());
			nfe.getInfNfe().getDest().getEnderDest().setFone(enderecoDestinatario.getFone1EnderecoPessoa());
			nfe.getInfNfe().getDest().setIE(RemoverAcentos.remover(enderecoDestinatario.getInscricaoMunicipalEnderecoPessoa()));

			// Endereco de entrega
			if (ambiente == Ambiente.Producao) {
				nfe.getInfNfe().getEntrega().setCNPJ(enderecoDestinatario.getCpfCnpjEnderecoPessoa());
			} else {
				nfe.getInfNfe().getEntrega().setCNPJ("99999999000191");
			}

			nfe.getInfNfe().getEntrega().setxLgr(RemoverAcentos.remover(enderecoDestinatario.getLogradouroEnderecoPessoa()));
			nfe.getInfNfe().getEntrega().setNro("0");
			nfe.getInfNfe().getEntrega().setxBairro(RemoverAcentos.remover(enderecoDestinatario.getBairroEnderecoPessoa()));
			nfe.getInfNfe().getEntrega().setcMun(ufDestinatario.getCodigoIbgeEstado() + cidadeDestinatario.getCodigoIbgeCidade());
			nfe.getInfNfe().getEntrega().setxMun(RemoverAcentos.remover(cidadeDestinatario.getNomeCidade()));
			nfe.getInfNfe().getEntrega().setUF(ufDestinatario.getSiglaEstado());

			// Itens da Nota
			for (ItemNFSaidaVO item : list) {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());

				produto = (ProdutoVO) _dao.get(produto);

				TributacaoVO tributacao = new TributacaoVO();
				tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
				tributacao.setUFOrigemTributacao(loja.getUFLoja());
				tributacao.setUFDestinoTributacao(enderecoDestinatario.getSiglaUFEnderecoPessoa());
				tributacao.setOperacaoTributacao("SV"); // Saída por vendas

				tributacao = (TributacaoVO) _dao.get(tributacao);

				// Se nao achou especifico para este estado, tenta para todos os
				// estados
				if (tributacao == null) {
					tributacao = new TributacaoVO();
					tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
					tributacao.setUFOrigemTributacao(loja.getUFLoja());
					tributacao.setUFDestinoTributacao("XX");
					tributacao.setOperacaoTributacao("SV"); // Saída por vendas

					tributacao = (TributacaoVO) _dao.get(tributacao);
				}

				Det det = Det.getInstance(nfe);

				det.getProd().setcProd(item.getCodigoProduto().toString());
				det.getProd().setxProd(RemoverAcentos.remover(item.getDescricaoItemNFSaida().trim()));
				det.getProd().setcEAN("");
				det.getProd().setNCM(item.getNCMItemNFSaida());
				det.getProd().setCFOP(item.getCfopItemNFSaida());
				det.getProd().setuCom(item.getUnidadeItemNFSaida());
				det.getProd().setqCom(item.getQuantidadeItemNFSaida());
				det.getProd().setuTrib(item.getUnidadeItemNFSaida());
				det.getProd().setvUnCom(item.getValorUnitarioItemNFSaida());
				det.getProd().setvProd(item.getValorTotalBrutoItemNFSaida());
				det.getProd().setqTrib(item.getQuantidadeItemNFSaida());

				if (item.getValorDescontoItemNFSaida() != null && item.getValorDescontoItemNFSaida().compareTo(BigDecimal.ZERO) > 0) {
					det.getProd().setvDesc(item.getValorDescontoItemNFSaida());
				}

				det.getProd().setcEANTrib("");
				det.getProd().setvUnTrib(item.getValorUnitarioItemNFSaida());
				det.getProd().setIndTot("1");
				det.getProd().setxPed(null);
				det.getProd().setnItemPed(null);

				// ICMS
				det.getImposto().getICMS().setCSTICMS(item.getCstIcmsItemNFSaida());

				if (det.getImposto().getICMS().getICMSSN102() != null) {
					det.getImposto().getICMS().getICMSSN102().setOrig("0");
					det.getImposto().getICMS().getICMSSN102().setCSOSN(item.getCstIcmsItemNFSaida());
				} else if (det.getImposto().getICMS().getICMS00() != null) {
					det.getImposto().getICMS().getICMS00().setModBC("3"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS00().setOrig("0"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS00().setpICMS(item.getAliquotaIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS00().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS00().setvICMS(item.getValorIcmsItemNFSaida());
				} else if (det.getImposto().getICMS().getICMS10() != null) {
					// TODO ICMS Substituicao Tributaria
					det.getImposto().getICMS().getICMS10().setModBC("3"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS10().setOrig("0"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS10().setpICMS(item.getAliquotaIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS10().setpICMSST(item.getAliquotaIcmsSTItemNFSaida());
					det.getImposto().getICMS().getICMS10().setModBCST("4"); // TODO
																			// Parametrizar
																			// ??

					det.getImposto().getICMS().getICMS10().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS10().setvBCST(item.getBaseIcmsStItemNFSaida());
					det.getImposto().getICMS().getICMS10().setvICMS(item.getValorIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS10().setvICMSST(item.getValorIcmsSTItemNFSaida());
					det.getImposto().getICMS().getICMS10().setpMVAST(tributacao.getMargemValorAgregadoTributacao());

					if (tributacao.getReducaoBaseCalculoIcmsTributacao() != null && tributacao.getReducaoBaseCalculoIcmsTributacao().compareTo(BigDecimal.ZERO) > 0) {
						det.getImposto().getICMS().getICMS10().setpRedBCST(tributacao.getReducaoBaseCalculoIcmsTributacao());
					}

				} else if (det.getImposto().getICMS().getICMS20() != null) {
					det.getImposto().getICMS().getICMS20().setModBC("3");
					det.getImposto().getICMS().getICMS20().setOrig("0");
					det.getImposto().getICMS().getICMS20().setpICMS(item.getAliquotaIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS20().setpRedBC(tributacao.getReducaoBaseCalculoIcmsTributacao());
					det.getImposto().getICMS().getICMS20().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getICMS().getICMS20().setvICMS(item.getValorIcmsItemNFSaida());
				} else if (det.getImposto().getICMS().getICMS30() != null) {
					// TODO Substituicao Tributaria
					// det.getImposto().getICMS().getICMS30().setModBC("4"); //
					// TODO Parametrizar ??
					// det.getImposto().getICMS().getICMS30().setOrig("0"); //
					// TODO Parametrizar ??
					// det.getImposto().getICMS().getICMS30().set
				} else if (det.getImposto().getICMS().getICMS40() != null) {
					det.getImposto().getICMS().getICMS40().setMotDesICMS("9"); // TODO
																				// PARAMETRIZAR
																				// MOTIVO
																				// DA
																				// DESONERACAO
																				// DE
																				// ICMS
					det.getImposto().getICMS().getICMS40().setOrig("0"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS40().setvICMS(BigDecimal.ZERO);
				} else if (det.getImposto().getICMS().getICMS41() != null) {
					det.getImposto().getICMS().getICMS41().setMotDesICMS("9"); // TODO
																				// PARAMETRIZAR
																				// MOTIVO
																				// DA
																				// DESONERACAO
																				// DE
																				// ICMS
					det.getImposto().getICMS().getICMS41().setOrig("0"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS41().setvICMS(BigDecimal.ZERO);
				} else if (det.getImposto().getICMS().getICMS50() != null) {
					det.getImposto().getICMS().getICMS50().setMotDesICMS("9"); // TODO
																				// PARAMETRIZAR
																				// MOTIVO
																				// DA
																				// DESONERACAO
																				// DE
																				// ICMS
					det.getImposto().getICMS().getICMS50().setOrig("0"); // TODO
																			// Parametrizar
																			// ??
					det.getImposto().getICMS().getICMS50().setvICMS(BigDecimal.ZERO);
				}

				// PIS
				det.getImposto().getPIS().setCSTPIS(item.getCstPisItemNFSaida());

				if (det.getImposto().getPIS().getPISNT() != null) {
					det.getImposto().getPIS().getPISNT().setCST(item.getCstPisItemNFSaida());
				} else if (det.getImposto().getPIS().getPISAliq() != null) {
					det.getImposto().getPIS().getPISAliq().setCST(item.getCstPisItemNFSaida());
					det.getImposto().getPIS().getPISAliq().setpPIS(item.getAliquotaPisItemNFSaida());
					det.getImposto().getPIS().getPISAliq().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getPIS().getPISAliq().setvPIS(item.getValorPisItemNFSaida());
				} else if (det.getImposto().getPIS().getPISQtde() != null) {
					det.getImposto().getPIS().getPISQtde().setqBCProd(item.getQuantidadeItemNFSaida());
					det.getImposto().getPIS().getPISQtde().setvAliqProd(item.getAliquotaPisItemNFSaida());
					det.getImposto().getPIS().getPISQtde().setvPIS(item.getValorPisItemNFSaida());
				} else if (det.getImposto().getPIS().getPISOutr() != null) {
					det.getImposto().getPIS().getPISOutr().setpPIS(item.getAliquotaPisItemNFSaida());
					// det.getImposto().getPIS().getPISOutr().setqBCProd(item.getQuantidadeItemNFSaida());
					// det.getImposto().getPIS().getPISOutr().setvAliqProd(item.getValorPisItemNFSaida());
					det.getImposto().getPIS().getPISOutr().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getPIS().getPISOutr().setvPIS(item.getValorPisItemNFSaida());
				}

				// COFINS
				det.getImposto().getCOFINS().setCSTCOFINS(item.getCstCofinsItemNFSaida());

				if (det.getImposto().getCOFINS().getCOFINSNT() != null) {
					det.getImposto().getCOFINS().getCOFINSNT().setCST(item.getCstCofinsItemNFSaida());
				} else if (det.getImposto().getCOFINS().getCOFINSAliq() != null) {
					det.getImposto().getCOFINS().getCOFINSAliq().setCST(item.getCstCofinsItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSAliq().setpCOFINS(item.getAliquotaCofinsItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSAliq().setvBC(item.getBaseCalculoIcmsItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSAliq().setvCOFINS(item.getValorCofinsItemNFSaida());
				} else if (det.getImposto().getCOFINS().getCOFINSQtde() != null) {
					det.getImposto().getCOFINS().getCOFINSQtde().setqBCProd(item.getQuantidadeItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSQtde().setvAliqProd(item.getAliquotaCofinsItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSQtde().setvCOFINS(item.getValorCofinsItemNFSaida());
				} else if (det.getImposto().getCOFINS().getCOFINSOutr() != null) {
					det.getImposto().getCOFINS().getCOFINSOutr().setvBC(item.getValorTotalLiquidoItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSOutr().setpCOFINS(item.getAliquotaCofinsItemNFSaida());
					// det.getImposto().getCOFINS().getCOFINSOutr().setqBCProd(item.getQuantidadeItemNFSaida());
					// det.getImposto().getCOFINS().getCOFINSOutr().setvAliqProd(item.getAliquotaCofinsItemNFSaida());
					det.getImposto().getCOFINS().getCOFINSOutr().setvCOFINS(item.getValorCofinsItemNFSaida());
				}
			}

			// Totais da nota
			nfe.getInfNfe().getTotal().getICMSTot().setvBC(nfsaida.getValorBaseCalculoIcmsNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvBCST(nfsaida.getValorBaseCalculoIcmsSTNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvCOFINS(nfsaida.getValorTotalCofinsNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvDesc(nfsaida.getValorTotalDescontoNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvFrete(nfsaida.getValorFreteNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvICMS(nfsaida.getValorIcmsNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvII(new BigDecimal(0.00));
			nfe.getInfNfe().getTotal().getICMSTot().setvIPI(nfsaida.getValorTotalIpiNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvNF(nfsaida.getValorTotalNFSaida().add(nfsaida.getValorSeguroNFSaida()));
			nfe.getInfNfe().getTotal().getICMSTot().setvOutro(nfsaida.getValorOutrasDespesasNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvPIS(nfsaida.getValorTotalPisNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvProd(nfsaida.getValorTotalProdutosNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvSeg(nfsaida.getValorSeguroNFSaida());
			nfe.getInfNfe().getTotal().getICMSTot().setvST(nfsaida.getValorIcmsSTNFSaida());

			// Frete
			nfe.getInfNfe().getTransp().setModFrete(nfsaida.getModalidadeFreteNFSaida());

			if (enderecoTransportadora != null) {
				if (transportadora.getNaturezaPessoa().equals("F")) {
					nfe.getInfNfe().getTransp().getTransporta().setCPF(enderecoTransportadora.getCpfCnpjEnderecoPessoa());
				} else {
					nfe.getInfNfe().getTransp().getTransporta().setCNPJ(enderecoTransportadora.getCpfCnpjEnderecoPessoa());
				}

				nfe.getInfNfe().getTransp().getTransporta().setIE(enderecoTransportadora.getInscricaoEstadualEnderecoPessoa());
				nfe.getInfNfe().getTransp().getTransporta().setUF(ufTransportadora.getSiglaEstado());
				nfe.getInfNfe().getTransp().getTransporta().setxEnder(enderecoTransportadora.getLogradouroEnderecoPessoa());
				nfe.getInfNfe().getTransp().getTransporta().setxMun(cidadeTransportadora.getNomeCidade());
				nfe.getInfNfe().getTransp().getTransporta().setxNome(RemoverAcentos.remover(transportadora.getNomeRazaoSocialPessoa()));
			}

			// Por ultimo seta o Id... depois de calculado
			nfe.getInfNfe().setId("NFe" + NfeUtils.buildNfeKey(nfe));

			// FIM DA GERACAO DA NF-E

			// Salva a geracao da nota eletronica
			nfeVo = new NFEletronicaVO();

			nfeVo.setNewRecord(true);
			nfeVo.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			nfeVo.setCodigoLoja(nfsaida.getCodigoLoja());
			nfeVo.setCodigoNFe(Integer.valueOf(nfe.getInfNfe().getIde().getnNF()));
			nfeVo.setStatusNFe(NFEletronicaVO.STATUS_VALIDADA);
			nfeVo.setChaveNFe(nfe.getInfNfe().getId());

			_dao.persist(nfeVo);

			// Log de geracao da nf-e (Objeto)
			XStream stream = new XStream();

			LogNFEletronicaVO log1 = new LogNFEletronicaVO();
			log1.setNewRecord(true);
			log1.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log1.setCodigoLoja(nfeVo.getCodigoLoja());
			log1.setCodigoNFe(nfeVo.getCodigoNFe());
			log1.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_OBJETO);
			log1.setDataHoraLogNFe(Utils.getNow());
			log1.setDadosLogNFe(stream.toXML(nfe));

			_dao.persist(log1);

			// Log de geracao da nf-e (Xml NF-e)
			String xmlNfe = NfeUtils.NfeToXml(nfe);

			LogNFEletronicaVO log2 = new LogNFEletronicaVO();
			log2.setNewRecord(true);
			log2.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log2.setCodigoLoja(nfeVo.getCodigoLoja());
			log2.setCodigoNFe(nfeVo.getCodigoNFe());
			log2.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_GERADO);
			log2.setDataHoraLogNFe(Utils.getNow());
			log2.setDadosLogNFe(xmlNfe);

			_dao.persist(log2);

			// Log de geracao da nf-e (Xml NF-e Assinado)
			String keystoreFile = paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja());
			String keystorePass = paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja());

			String xmlAssinado = NfeSigner.sign(xmlNfe, keystoreFile, keystorePass, TagSign.infNFe);
			
			NfeUtils.salvaArquivo("c:\\temp\\xml1.xml", xmlAssinado);
			
			xmlAssinado = NfeUtils.removeXmlTag(xmlAssinado);

			NfeUtils.salvaArquivo("c:\\temp\\xml2.xml", xmlAssinado);
			
			LogNFEletronicaVO log3 = new LogNFEletronicaVO();
			log3.setNewRecord(true);
			log3.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log3.setCodigoLoja(nfeVo.getCodigoLoja());
			log3.setCodigoNFe(nfeVo.getCodigoNFe());
			log3.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_ASSINADO);
			log3.setDataHoraLogNFe(Utils.getNow());
			log3.setDadosLogNFe(xmlAssinado);

			_dao.persist(log3);

			// Vincula a Nota Fiscal à NF-e
			nfsaida.setCodigoNfe(nfeVo.getCodigoNFe());
			nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_ASSINADA);
			_dao.persist(nfsaida);

			nfeVo.setStatusNFe(NFEletronicaVO.STATUS_ASSINADA);
			_dao.persist(nfeVo);
			_dao.commit();
		} catch (SQLException e) {
			_dao.rollback();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			_dao.rollback();
		}
	}

	public static void transmitir(NFSaidaVO nfsaida) throws SQLException, NfeException {
		GenericDAO _dao = new GenericDAO();
		nfsaida = (NFSaidaVO) _dao.get(nfsaida);
		XStream stream = new XStream();

		LojaVO loja = new LojaVO();
		loja.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		loja.setCodigoLoja(nfsaida.getCodigoLoja());
		loja = (LojaVO) _dao.get(loja);

		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();
		String amb = paramLojaDao.getStringParametro(Constants.Parameters.NFe.AMBIENTE_NFE, loja.getCodigoEmpresa(), loja.getCodigoLoja());

		Ambiente ambiente = amb.equals("producao") ? Ambiente.Producao : Ambiente.Homologacao;

		// Verifica se já existe a NF-e gerada
		NFEletronicaVO nfeVo = new NFEletronicaVO();
		nfeVo.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		nfeVo.setCodigoLoja(nfsaida.getCodigoLoja());
		nfeVo.setCodigoNFe(nfsaida.getCodigoNFSaida());

		nfeVo = (NFEletronicaVO) _dao.get(nfeVo);

		String xmlNfe = null;
		NFe nfe = null;

		LogNFEletronicaVO logNfeObjeto = null;

		if (nfeVo != null) {

			// Limpar todos os registros existentes...
			LogNFEletronicaVO _filter = new LogNFEletronicaVO();
			_filter.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			_filter.setCodigoLoja(nfeVo.getCodigoLoja());
			_filter.setCodigoNFe(nfeVo.getCodigoNFe());

			List<IValueObject> listLog = _dao.getList(_filter, "SequencialLogNFe DESC");

			for (IValueObject item : listLog) {
				LogNFEletronicaVO log = (LogNFEletronicaVO) item;

				if (log.getCodigoTipoRegistroNFe().equals(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_ASSINADO)) {
					xmlNfe = new String(log.getDadosLogNFe());
				} else if (log.getCodigoTipoRegistroNFe().equals(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_OBJETO)) {
					nfe = (NFe) stream.fromXML(new String(log.getDadosLogNFe()));
					logNfeObjeto = log;
				}
			}
		} else {
			throw new NfeException("NF-e ainda não foi gerada, verifique!");
		}

		if (xmlNfe == null || nfe == null) {
			throw new NfeException("NF-e não foi localizada, verifique!");
		}

		NfeRecepcaoService recepcaoService = new NfeRecepcaoService();

		// Transmite
		if (recepcaoService.execute(xmlNfe, ambiente, loja)) {

			// Grava o que foi enviado...
			LogNFEletronicaVO log4 = new LogNFEletronicaVO();
			log4.setNewRecord(true);
			log4.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log4.setCodigoLoja(nfeVo.getCodigoLoja());
			log4.setCodigoNFe(nfeVo.getCodigoNFe());
			log4.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_ENVIO_RECEPCAO);
			log4.setDataHoraLogNFe(Utils.getNow());
			log4.setDadosLogNFe(recepcaoService.getSoapXmlSend());

			_dao.persist(log4);

			// Grava o que foi retornado
			LogNFEletronicaVO log5 = new LogNFEletronicaVO();
			log5.setNewRecord(true);
			log5.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log5.setCodigoLoja(nfeVo.getCodigoLoja());
			log5.setCodigoNFe(nfeVo.getCodigoNFe());
			log5.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_RETORNO_RECEPCAO);
			log5.setDataHoraLogNFe(Utils.getNow());
			log5.setDadosLogNFe(recepcaoService.getSoapXmlReceived());

			_dao.persist(log5);

			// Ler retorno
			String codigoRetornoEnvio = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "cStat");

			// Se o lote foi recebido com sucesso...
			if (codigoRetornoEnvio.equals(Constants.NfeParameters.Retornos.LOTE_RECEBIDO_SUCESSO)) {

				String numeroRecibo = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "nRec");
				// String dataHoraRecibo =
				// NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0],
				// "dhRecbto");

				// Enquanto retornar o codigo 105 (em processamento), fica
				// buscando...
				String codigoStatusProcessamento = Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO;

				while (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)) {

					NfeRetRecepcaoService retornoService = new NfeRetRecepcaoService();

					if (retornoService.execute(numeroRecibo, ambiente, loja)) {

						codigoStatusProcessamento = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "cStat");

						if (!codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)) {

							// Grava o que foi enviado...
							LogNFEletronicaVO log6 = new LogNFEletronicaVO();
							log6.setNewRecord(true);
							log6.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
							log6.setCodigoLoja(nfeVo.getCodigoLoja());
							log6.setCodigoNFe(nfeVo.getCodigoNFe());
							log6.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_ENVIO_RETRECEPCAO);
							log6.setDataHoraLogNFe(Utils.getNow());
							log6.setDadosLogNFe(retornoService.getSoapXmlSend());

							_dao.persist(log6);

							// Grava o que foi retornado
							LogNFEletronicaVO log7 = new LogNFEletronicaVO();
							log7.setNewRecord(true);
							log7.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
							log7.setCodigoLoja(nfeVo.getCodigoLoja());
							log7.setCodigoNFe(nfeVo.getCodigoNFe());
							log7.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_RETORNO_RETRECEPCAO);
							log7.setDataHoraLogNFe(Utils.getNow());
							log7.setDadosLogNFe(retornoService.getSoapXmlReceived());

							_dao.persist(log7);

							if (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_PROCESSADO)) {

								String codigoRetornoNfe = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt", "cStat");

								if (codigoRetornoNfe.equals(Constants.NfeParameters.Retornos.NFE_AUTORIZADA)) {
									String numeroProtocoloNfe = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt", "nProt");
									nfe.setNumeroProtocoloAutorizacao(numeroProtocoloNfe);
									logNfeObjeto.setDadosLogNFe(stream.toXML(nfe));
									_dao.persist(logNfeObjeto);

									nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_AUTORIZADA); // NF-e
																								// Autorizada
									_dao.persist(nfsaida);

									nfeVo.setStatusNFe(NFEletronicaVO.STATUS_AUTORIZADA);
									nfeVo.setProtocoloNFe(numeroProtocoloNfe);
									_dao.persist(nfeVo);

									// Finalizado
									return;
								} else {
									throw new NfeException(NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt", "xMotivo"));
								}
							}
						}
					} else {
						throw new NfeException(NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "xMotivo"));
					}

					// Pausa por 5 segundos...
					if (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						throw new NfeException(NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "xMotivo"));
					}
				}
			} else {
				throw new NfeException(NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "xMotivo"));
				// throw new NfeException("Erro na transmissão! " +
				// recepcaoService.getLastMessages()[0]);
			}
		} else {
			throw new NfeException(NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "xMotivo"));
		}
	}

	public static String consulta(NFSaidaVO nfsaida) throws NfeException, SQLException {
		GenericDAO _dao = new GenericDAO();
		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();
		XStream stream = new XStream();

		LojaVO loja = new LojaVO();
		loja.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		loja.setCodigoLoja(nfsaida.getCodigoLoja());

		loja = (LojaVO) _dao.get(loja);

		String amb = paramLojaDao.getStringParametro(Constants.Parameters.NFe.AMBIENTE_NFE, loja.getCodigoEmpresa(), loja.getCodigoLoja()); // SystemManager.getProperty("nfe.ambiente");
		Ambiente ambiente = amb.equals("producao") ? Ambiente.Producao : Ambiente.Homologacao;

		nfsaida = (NFSaidaVO) _dao.get(nfsaida);

		NFEletronicaVO nfEletronica = new NFEletronicaVO();
		nfEletronica.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		nfEletronica.setCodigoLoja(nfsaida.getCodigoLoja());
		nfEletronica.setCodigoNFe(nfsaida.getCodigoNFSaida());

		nfEletronica = (NFEletronicaVO) _dao.get(nfEletronica);

		if (nfEletronica == null) {
			throw new NfeException("NF-e ainda não foi transmitida!");
		}

		NfeConsultaNFService nfeConsulta = new NfeConsultaNFService();
		nfeConsulta.execute(nfEletronica.getChaveNFe(), ambiente, loja);

		NFEletronicaVO nfeVo = new NFEletronicaVO();
		nfeVo.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		nfeVo.setCodigoLoja(nfsaida.getCodigoLoja());
		nfeVo.setCodigoNFe(nfsaida.getCodigoNFSaida());

		nfeVo = (NFEletronicaVO) _dao.get(nfeVo);

		NFe nfe = null;

		LogNFEletronicaVO logNfeObjeto = null;

		if (nfeVo != null) {

			// Limpar todos os registros existentes...
			LogNFEletronicaVO _filter = new LogNFEletronicaVO();
			_filter.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			_filter.setCodigoLoja(nfeVo.getCodigoLoja());
			_filter.setCodigoNFe(nfeVo.getCodigoNFe());

			List<IValueObject> listLog = _dao.getList(_filter, "SequencialLogNFe DESC");

			for (IValueObject item : listLog) {
				LogNFEletronicaVO log = (LogNFEletronicaVO) item;

				if (log.getCodigoTipoRegistroNFe().equals(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_OBJETO)) {
					nfe = (NFe) stream.fromXML(new String(log.getDadosLogNFe()));
					logNfeObjeto = log;
				}
			}
		} else {
			throw new NfeException("NF-e ainda não foi gerada, verifique!");
		}

		String codigoRetornoNfe = NfeUtils.readXmlTag(nfeConsulta.getLastMessages()[0], "cStat");

		if (codigoRetornoNfe.equals(Constants.NfeParameters.Retornos.NFE_AUTORIZADA)) {

			String numeroProtocoloNfe = NfeUtils.readXmlTag(nfeConsulta.getLastMessages()[0], "infProt", "nProt");
			nfe.setNumeroProtocoloAutorizacao(numeroProtocoloNfe);
			logNfeObjeto.setDadosLogNFe(stream.toXML(nfe));
			_dao.persist(logNfeObjeto);

			nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_AUTORIZADA); // NF-e
																		// Autorizada
			_dao.persist(nfsaida);

			nfeVo.setStatusNFe(NFEletronicaVO.STATUS_AUTORIZADA);
			nfeVo.setProtocoloNFe(numeroProtocoloNfe);
			_dao.persist(nfeVo);

			// Finalizado
			return NfeUtils.readXmlTag(nfeConsulta.getLastMessages()[0], "xMotivo");
		} else if (codigoRetornoNfe.equals(Constants.NfeParameters.Retornos.NFE_CANCELADA)) {
			nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_CANCELADA);
			_dao.persist(nfsaida);
			return NfeUtils.readXmlTag(nfeConsulta.getLastMessages()[0], "xMotivo");
		} else {
			throw new NfeException(NfeUtils.readXmlTag(nfeConsulta.getLastMessages()[0], "xMotivo"));
		}
	}

	public static void status(LojaVO loja) throws SQLException {
		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();
		String amb = paramLojaDao.getStringParametro(Constants.Parameters.NFe.AMBIENTE_NFE, loja.getCodigoEmpresa(), loja.getCodigoLoja()); // SystemManager.getProperty("nfe.ambiente");
		Ambiente ambiente = amb.equals("producao") ? Ambiente.Producao : Ambiente.Homologacao;

		NfeStatusServicoService nfeStatus = new NfeStatusServicoService();
		nfeStatus.execute(ambiente, loja);

		System.out.println(nfeStatus.getLastMessages()[0]);
	}

	public static void cancelar(NFSaidaVO nfsaida, String motivo) throws SQLException, NfeException {

		if (motivo == null || motivo.length() < 15) {
			throw new NfeException("O motivo do cancelamento deve ter pelo menos 15 dígitos!");
		}

		GenericDAO _dao = new GenericDAO();
		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();

		LojaVO loja = new LojaVO();
		loja.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		loja.setCodigoLoja(nfsaida.getCodigoLoja());

		loja = (LojaVO) _dao.get(loja);

		String amb = paramLojaDao.getStringParametro(Constants.Parameters.NFe.AMBIENTE_NFE, loja.getCodigoEmpresa(), loja.getCodigoLoja()); // SystemManager.getProperty("nfe.ambiente");
		Ambiente ambiente = amb.equals("producao") ? Ambiente.Producao : Ambiente.Homologacao;

		nfsaida = (NFSaidaVO) _dao.get(nfsaida);

		NFEletronicaVO nfEletronica = new NFEletronicaVO();
		nfEletronica.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
		nfEletronica.setCodigoLoja(nfsaida.getCodigoLoja());
		nfEletronica.setCodigoNFe(nfsaida.getCodigoNFSaida());

		nfEletronica = (NFEletronicaVO) _dao.get(nfEletronica);

		NfeCancelamentoService cancelamento = new NfeCancelamentoService();
		String xmlCancelamento = cancelamento.buildXmlCancelamento(nfEletronica.getChaveNFe(), nfEletronica.getProtocoloNFe(), motivo, ambiente, loja);

		LogNFEletronicaVO log = new LogNFEletronicaVO();
		log.setNewRecord(true);
		log.setCodigoEmpresa(nfEletronica.getCodigoEmpresa());
		log.setCodigoLoja(nfEletronica.getCodigoLoja());
		log.setCodigoNFe(nfEletronica.getCodigoNFe());
		log.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_ENVIO_CANCELAMENTO);
		log.setDataHoraLogNFe(Utils.getNow());
		log.setDadosLogNFe(xmlCancelamento);

		_dao.persist(log);

		if (cancelamento.execute(xmlCancelamento, ambiente, loja)) {

			String codigoRetornoEvento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "cStat");
			String mensagemRetornoEvento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "xMotivo");

			if (codigoRetornoEvento.equals(Constants.NfeParameters.Retornos.LOTE_EVENTO_PROCESSADO)) {
				// Se foi processado, verificar o retorno do mesmo.

				String codigoRetornoCancelamento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "infEvento", "cStat");
				String mensagemRetornoCancelamento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "infEvento", "xMotivo");

				if (codigoRetornoCancelamento.equals(Constants.NfeParameters.Retornos.EVENTO_REGISTRADO)) {
					nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_CANCELADA);
					_dao.persist(nfsaida);
				} else {
					log = new LogNFEletronicaVO();
					log.setNewRecord(true);
					log.setCodigoEmpresa(nfEletronica.getCodigoEmpresa());
					log.setCodigoLoja(nfEletronica.getCodigoLoja());
					log.setCodigoNFe(nfEletronica.getCodigoNFe());
					log.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_RETORNO_CANCELAMENTO);
					log.setDataHoraLogNFe(Utils.getNow());
					log.setDadosLogNFe(cancelamento.getLastMessages()[0]);

					_dao.persist(log);

					throw new NfeException(mensagemRetornoCancelamento);
				}
			}

			log = new LogNFEletronicaVO();
			log.setNewRecord(true);
			log.setCodigoEmpresa(nfEletronica.getCodigoEmpresa());
			log.setCodigoLoja(nfEletronica.getCodigoLoja());
			log.setCodigoNFe(nfEletronica.getCodigoNFe());
			log.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_RETORNO_CANCELAMENTO);
			log.setDataHoraLogNFe(Utils.getNow());
			log.setDadosLogNFe(cancelamento.getLastMessages()[0]);

			_dao.persist(log);

			throw new NfeException(mensagemRetornoEvento);
		} else {
			String codigoRetornoCancelamento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "infCanc", "cStat");
			String mensagemRetornoCancelamento = NfeUtils.readXmlTag(cancelamento.getLastMessages()[0], "infCanc", "xMotivo");
			throw new NfeException(codigoRetornoCancelamento + " - " + mensagemRetornoCancelamento);
		}
	}
}