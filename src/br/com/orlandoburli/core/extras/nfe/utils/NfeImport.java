package br.com.orlandoburli.core.extras.nfe.utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.extras.nfe.model.Emit;
import br.com.orlandoburli.core.extras.nfe.model.Ide;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.extras.nfe.model.NfeProc;
import br.com.orlandoburli.core.extras.nfe.model.cobranca.Dup;
import br.com.orlandoburli.core.extras.nfe.model.details.Det;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins.Cofins;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms.Icms;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi.Ipi;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis.Pis;
import br.com.orlandoburli.core.extras.nfe.model.totais.IcmsTot;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.EntradaEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.ItemEntradaEstoqueVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.contapagar.NFEntradaContaPagarVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.estoque.NFEntradaEntradaEstoqueVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.personalerp.facades.estoque.entrada.entradaestoque.EntradaEstoqueCadastroFacade;

public class NfeImport {

	public static void importNfe(String xmlData, UsuarioVO usuario, boolean importarContas, boolean importarEstoque, HttpServletRequest request, HttpServletResponse response, ServletContext context) throws NfeException {
		
		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);
		
		try {
			// 1 - Parse do xml para objeto Nfe
			NfeProc nfeProc = NfeUtils.XmlToNfe(xmlData);
			
			// Busca loja pelo CNPJ
			LojaVO loja = new LojaVO();
			loja.setCNPJLoja(nfeProc.getNFe().getInfNfe().getDest().getCNPJ());
			List<IValueObject> listLoj = dao.getList(loja);
			
			if (listLoj.size() > 0) {
				loja = (LojaVO) listLoj.get(0);
			} else {
				throw new NfeException("Loja com o CNPJ " + nfeProc.getNFe().getInfNfe().getDest().getCNPJ() + " não cadastrada!");
			}
			
			EmpresaVO empresa = new EmpresaVO();
			empresa.setCodigoEmpresa(loja.getCodigoEmpresa());
			empresa = (EmpresaVO) dao.get(empresa);
			
			// 2 - Verifica se a nota ja esta cadastrada
			// 2.1 - Verifica se o emissor ja esta cadastrado
			EnderecoPessoaVO endPes = new EnderecoPessoaVO();
			
			Emit emit = nfeProc.getNFe().getInfNfe().getEmit();
			
			if (emit.getCNPJ() != null && !emit.getCNPJ().trim().equals("")) {
				endPes.setCpfCnpjEnderecoPessoa(emit.getCNPJ());
			} else if (emit.getCPF() != null && !emit.getCPF().trim().equals("")) {
				endPes.setCpfCnpjEnderecoPessoa(emit.getCPF());
			}
			
			List<IValueObject> listEndPes = dao.getList(endPes);
			
			if (listEndPes.size() > 0) {
				// Se encontrou, pega o 1 da lista
				endPes = (EnderecoPessoaVO) listEndPes.get(0);
			} else {
				// Se nao, cadastrar
				// Dados da pessoa
				PessoaVO pes = new PessoaVO();
				pes.setNewRecord(true);
				
				endPes = new EnderecoPessoaVO();
				endPes.setNewRecord(true);
				
				pes.setCodigoEmpresa(empresa.getCodigoEmpresa());
				pes.setCodigoLoja(loja.getCodigoLoja());
				
				pes.setNomeRazaoSocialPessoa(emit.getxNome());
				
				if (emit.getCNPJ() != null && !emit.getCNPJ().trim().equals("")) {
					pes.setNaturezaPessoa("J");
					endPes.setCpfCnpjEnderecoPessoa(emit.getCNPJ());
				} else if (emit.getCPF() != null && !emit.getCPF().trim().equals("")) {
					pes.setNaturezaPessoa("F");
					endPes.setCpfCnpjEnderecoPessoa(emit.getCPF());
				}
				
				pes.setObservacoesPessoa("IMPORTADO POR XML");
				
				pes.setCodigoEmpresaUsuarioLog(usuario.getCodigoEmpresa());
				pes.setCodigoLojaUsuarioLog(usuario.getCodigoLoja());
				pes.setCodigoUsuarioLog(usuario.getCodigoUsuario());
				
				dao.persist(pes);
				
				// Chave do endereco da pessoa
				endPes.setCodigoEmpresa(pes.getCodigoEmpresa());
				endPes.setCodigoLoja(pes.getCodigoLoja());
				endPes.setCodigoPessoa(pes.getCodigoPessoa());
				
				// Dados do endereco da pessoa
				endPes.setNomeEnderecoPessoa(emit.getxNome());
				endPes.setFantasiaApelidoEnderecoPessoa(emit.getxFant());
				
				if (endPes.getFantasiaApelidoEnderecoPessoa() == null || endPes.getFantasiaApelidoEnderecoPessoa().trim().equals("")) {
					endPes.setFantasiaApelidoEnderecoPessoa(pes.getNomeRazaoSocialPessoa());
				}
				
				endPes.setLogradouroEnderecoPessoa(emit.getEnderEmit().getxLgr() + ", " + emit.getEnderEmit().getNro());
				endPes.setLogradouroEnderecoPessoa(endPes.getLogradouroEnderecoPessoa() + " " + emit.getEnderEmit().getNro());
				endPes.setBairroEnderecoPessoa(emit.getEnderEmit().getxBairro());
				endPes.setCepEnderecoPessoa(emit.getEnderEmit().getCEP());
				endPes.setFone1EnderecoPessoa(emit.getEnderEmit().getFone());
				endPes.setTipoEnderecoPessoa("3");
				endPes.setInscricaoEstadualEnderecoPessoa(emit.getIE());
				endPes.setInscricaoMunicipalEnderecoPessoa(emit.getIM());
				
				// Busca a cidade
				CidadeVO cidade = new CidadeVO();
				cidade.setCodigoIbgeCidade(emit.getEnderEmit().getcMun().substring(2));
				
				List<IValueObject> listCidades = dao.getList(cidade);
				
				if (listCidades.size() > 0) {
					endPes.setCodigoCidadeEnderecoPessoa(((CidadeVO)listCidades.get(0)).getCodigoCidade());
				}
				
				endPes.setCodigoEmpresaUsuarioLog(usuario.getCodigoEmpresa());
				endPes.setCodigoLojaUsuarioLog(usuario.getCodigoLoja());
				endPes.setCodigoUsuarioLog(usuario.getCodigoUsuario());
				
				dao.persist(endPes);
			}
			
			NFEntradaVO nfEntrada = new NFEntradaVO();
			nfEntrada.setChaveNFeEntrada(nfeProc.getNFe().getInfNfe().getId());
			
			List<IValueObject> listNfEntrada = dao.getList(nfEntrada);
			
			if (listNfEntrada.size() > 0) {
				throw new NfeException("NF-e já importada!");
			}
			
			// Se nao existe, cadastra
			Ide ide = nfeProc.getNFe().getInfNfe().getIde();
			
			nfEntrada.setNewRecord(true);
			nfEntrada.setCodigoEmpresa(empresa.getCodigoEmpresa());
			nfEntrada.setCodigoLoja(loja.getCodigoLoja());
			
			nfEntrada.setCodigoNFEntrada(Integer.parseInt(ide.getnNF()));
			nfEntrada.setSerieNFEntrada(ide.getSerie());

			nfEntrada.setCodigoEmpresaEmitenteNFEntrada(endPes.getCodigoEmpresa());
			nfEntrada.setCodigoLojaEmitenteNFEntrada(endPes.getCodigoLoja());
			nfEntrada.setCodigoPessoaEmitenteNFEntrada(endPes.getCodigoPessoa());
			nfEntrada.setCodigoEnderecoPessoaEmitenteNFEntrada(endPes.getCodigoEnderecoPessoa());
			
			nfEntrada.setFormaPagamentoNFEntrada(Integer.parseInt(ide.getIndPag()));
			// TODO - Operacao da Tributacao
			//nfEntrada.setOperacaoTributacao();
			
			nfEntrada.setModeloNFEntrada(ide.getMod());
			
			try {
				nfEntrada.setDataHoraEntradaNFEntrada(Timestamp.valueOf(ide.getdSaiEnt() + " " + ide.gethSaiEnt()));
			} catch (Exception e ) {}
			
			nfEntrada.setDataEmissaoNFEntrada(Date.valueOf(ide.getdEmi()));
			
			// TODO - Cadastro da transportadora
			// TODO - Dados do transporte - volume
			// nfEntrada.setQuantidadeVolumesTransporteNFEntrada(nfeProc.getNFe().getInfNfe().getTransp())
			nfEntrada.setModalidadeFreteNFEntrada(nfeProc.getNFe().getInfNfe().getTransp().getModFrete());
			nfEntrada.setInscricaoEstadualSTNFEntrada(emit.getIEST());
			
			IcmsTot icmsTot = nfeProc.getNFe().getInfNfe().getTotal().getICMSTot();
			
			nfEntrada.setValorBaseCalculoIcmsNFEntrada(icmsTot.getvBC());
			nfEntrada.setValorBaseCalculoIcmsSTNFEntrada(icmsTot.getvBCST());
			nfEntrada.setValorIcmsNFEntrada(icmsTot.getvICMS());
			nfEntrada.setValorIcmsSTNFEntrada(icmsTot.getvST());
			
			nfEntrada.setValorTotalProdutosNFEntrada(icmsTot.getvProd());
			nfEntrada.setValorFreteNFEntrada(icmsTot.getvFrete());
			nfEntrada.setValorSeguroNFEntrada(icmsTot.getvSeg());
			nfEntrada.setValorTotalDescontoNFEntrada(icmsTot.getvDesc());
			nfEntrada.setValorTotalIpiNFEntrada(icmsTot.getvIPI());
			nfEntrada.setValorTotalPisNFEntrada(icmsTot.getvPIS());
			nfEntrada.setValorTotalCofinsNFEntrada(icmsTot.getvCOFINS());
			nfEntrada.setValorOutrasDespesasNFEntrada(icmsTot.getvOutro());
			nfEntrada.setValorTotalNFEntrada(icmsTot.getvNF());
			nfEntrada.setStatusNFEntrada(NFEletronicaVO.STATUS_IMPORTADA);
			nfEntrada.setObservacaoNFEntrada("NOTA IMPORTADA POR XML");
			
			nfEntrada.setCodigoEmpresaUsuarioLog(usuario.getCodigoEmpresa());
			nfEntrada.setCodigoLojaUsuarioLog(usuario.getCodigoLoja());
			nfEntrada.setCodigoUsuarioLog(usuario.getCodigoUsuario());
			
			dao.persist(nfEntrada);
			
			// Itens
			for (Det det : nfeProc.getNFe().getInfNfe().getDet()) {
				ItemNFEntradaVO item = new ItemNFEntradaVO();
				item.setNewRecord(true);
				
				item.setCodigoEmpresa(nfEntrada.getCodigoEmpresa());
				item.setCodigoLoja(nfEntrada.getCodigoLoja());
				item.setCodigoNFEntrada(nfEntrada.getCodigoNFEntrada());
				item.setSerieNFEntrada(nfEntrada.getSerieNFEntrada());
				
				item.setCodigoEmpresaEmitenteNFEntrada(nfEntrada.getCodigoEmpresaEmitenteNFEntrada());
				item.setCodigoLojaEmitenteNFEntrada(nfEntrada.getCodigoLojaEmitenteNFEntrada());
				item.setCodigoPessoaEmitenteNFEntrada(nfEntrada.getCodigoPessoaEmitenteNFEntrada());
				item.setCodigoEnderecoPessoaEmitenteNFEntrada(nfEntrada.getCodigoEnderecoPessoaEmitenteNFEntrada());
				
				item.setSequencialItemNFEntrada(Integer.parseInt(det.getnItem()));
				
				// Verifica se o produto existe
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoReferenciaProduto(det.getProd().getcProd());
				
				List<IValueObject> listProdutos = dao.getList(produto);
				
				if (listProdutos.size() > 0) {
					produto = (ProdutoVO) listProdutos.get(0);
				} else {
					// Verifica se a unidade existe
					UnidadeVO unidade = new UnidadeVO();
					unidade.setSiglaUnidade(det.getProd().getuCom());
					unidade = (UnidadeVO) dao.get(unidade);
					if (unidade == null) {
						unidade = new UnidadeVO();
						unidade.setNewRecord(true);
						unidade.setSiglaUnidade(det.getProd().getuCom());
						unidade.setNomeUnidadeSingular(det.getProd().getuCom());
						unidade.setNomeUnidadePlural(det.getProd().getuCom());
						unidade.setCodigoEmpresaUsuarioLog(usuario.getCodigoEmpresa());
						unidade.setCodigoLojaUsuarioLog(usuario.getCodigoLoja());
						unidade.setCodigoUsuarioLog(usuario.getCodigoUsuario());
						dao.persist(unidade);
					}
					
					produto.setNewRecord(true);
					produto.setCodigoEmpresa(empresa.getCodigoEmpresa());
					produto.setCodigoLoja(loja.getCodigoLoja());
					produto.setDescricaoProduto(det.getProd().getxProd());
					produto.setTipoEstoqueProduto("V"); // Venda
					produto.setSituacaoProduto("N"); // Normal
					produto.setCodigoNCMProduto(det.getProd().getNCM());
					produto.setSiglaUnidade(det.getProd().getuCom());
					produto.setPermiteVendaFracionadaProduto("N");
					
					dao.persist(produto);
				}
				
				item.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
				item.setCodigoLojaProduto(produto.getCodigoLoja());
				item.setCodigoProduto(produto.getCodigoProduto());
				
				item.setCfopItemNFEntrada(det.getProd().getCFOP());
				item.setDescricaoItemNFEntrada(det.getProd().getxProd());
				item.setNCMItemNFEntrada(det.getProd().getNCM());
				item.setUnidadeItemNFEntrada(det.getProd().getuCom());
				item.setQuantidadeItemNFEntrada(det.getProd().getqCom());
				item.setValorUnitarioItemNFEntrada(det.getProd().getvUnCom());
				item.setValorTotalBrutoItemNFEntrada(det.getProd().getvProd());
				
				item.setValorTotalLiquidoItemNFEntrada(det.getProd().getvProd()
														.add(det.getProd().getvOutro() == null ? BigDecimal.ZERO : det.getProd().getvOutro())
														.add(det.getProd().getvFrete() == null ? BigDecimal.ZERO : det.getProd().getvFrete())
														.add(det.getProd().getvSeg()   == null ? BigDecimal.ZERO : det.getProd().getvSeg())
														.subtract(det.getProd().getvDesc() == null? BigDecimal.ZERO : det.getProd().getvDesc()));
				
				Icms icms = det.getImposto().getICMS();
				
				if (icms.getICMS00() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS00().getCST());
					item.setAliquotaIcmsItemNFEntrada(icms.getICMS00().getpICMS());
					item.setBaseCalculoIcmsItemNFEntrada(icms.getICMS00().getvBC());
					item.setValorIcmsItemNFEntrada(icms.getICMS00().getvICMS());
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
				} else if (icms.getICMS10() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS10().getCST());
					item.setAliquotaIcmsItemNFEntrada(icms.getICMS10().getpICMS());
					item.setBaseCalculoIcmsItemNFEntrada(icms.getICMS10().getvBC());
					item.setValorIcmsItemNFEntrada(icms.getICMS10().getvICMS());
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(icms.getICMS10().getpICMSST());
					item.setValorIcmsSTItemNFEntrada(icms.getICMS10().getvICMSST());
					item.setBaseCalculoIcmsItemNFEntrada(icms.getICMS10().getvBCST());
				} else if (icms.getICMS20() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS20().getCST());
					item.setAliquotaIcmsItemNFEntrada(icms.getICMS20().getpICMS());
					item.setBaseCalculoIcmsItemNFEntrada(icms.getICMS20().getvBC());
					item.setValorIcmsItemNFEntrada(icms.getICMS20().getvICMS());
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
				} else if (icms.getICMS30() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS30().getCST());
					item.setAliquotaIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsItemNFEntrada(BigDecimal.ZERO);
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(icms.getICMS30().getpICMSST());
					item.setValorIcmsSTItemNFEntrada(icms.getICMS30().getvICMSST());
					item.setBaseCalculoIcmsItemNFEntrada(icms.getICMS30().getvBCST());
				} else if (icms.getICMS40() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS40().getCST());
					item.setAliquotaIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsItemNFEntrada(BigDecimal.ZERO);
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
				} else if (icms.getICMS41() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS41().getCST());
					item.setAliquotaIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsItemNFEntrada(BigDecimal.ZERO);
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
				} else if (icms.getICMS50() != null) {
					// ICMS
					item.setCstIcmsItemNFEntrada(icms.getICMS50().getCST());
					item.setAliquotaIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsItemNFEntrada(BigDecimal.ZERO);
					// ICMS ST
					item.setAliquotaIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFEntrada(BigDecimal.ZERO);
					item.setBaseCalculoIcmsItemNFEntrada(BigDecimal.ZERO);
				}
				
				Pis pis = det.getImposto().getPIS();
				
				if (pis != null) {
					if (pis.getPISAliq() != null) {
						item.setCstPisItemNFEntrada(pis.getPISAliq().getCST());
						item.setAliquotaPisItemNFEntrada(pis.getPISAliq().getpPIS());
						item.setValorPisItemNFEntrada(pis.getPISAliq().getvPIS());
					} else if (pis.getPISNT() != null) {
						item.setCstPisItemNFEntrada(pis.getPISNT().getCST());
						item.setAliquotaPisItemNFEntrada(BigDecimal.ZERO);
						item.setValorPisItemNFEntrada(BigDecimal.ZERO);
					} else if (pis.getPISOutr() != null) {
						item.setCstPisItemNFEntrada(pis.getPISOutr().getCST());
						item.setAliquotaPisItemNFEntrada(pis.getPISOutr().getpPIS());
						item.setValorPisItemNFEntrada(pis.getPISOutr().getvPIS());
					} else if (pis.getPISQtde() != null) {
						item.setCstPisItemNFEntrada(pis.getPISQtde().getCST());
						item.setAliquotaPisItemNFEntrada(pis.getPISQtde().getvAliqProd());
						item.setValorPisItemNFEntrada(pis.getPISQtde().getvPIS());
					}
				}
				
				Cofins cofins = det.getImposto().getCOFINS();
				
				if (cofins != null) {
					if (cofins.getCOFINSAliq() != null) {
						item.setCstCofinsItemNFEntrada(cofins.getCOFINSAliq().getCST());
						item.setAliquotaCofinsItemNFEntrada(cofins.getCOFINSAliq().getpCOFINS());
						item.setValorCofinsItemNFEntrada(cofins.getCOFINSAliq().getvCOFINS());
					} else if (cofins.getCOFINSNT() != null) {
						item.setCstCofinsItemNFEntrada(cofins.getCOFINSNT().getCST());
						item.setAliquotaCofinsItemNFEntrada(BigDecimal.ZERO);
						item.setValorCofinsItemNFEntrada(BigDecimal.ZERO);
					} else if (cofins.getCOFINSOutr() != null) {
						item.setCstCofinsItemNFEntrada(cofins.getCOFINSOutr().getCST());
						item.setAliquotaCofinsItemNFEntrada(cofins.getCOFINSOutr().getpCOFINS());
						item.setValorCofinsItemNFEntrada(cofins.getCOFINSOutr().getvCOFINS());
					} else if (cofins.getCOFINSQtde() != null) {
						item.setCstCofinsItemNFEntrada(cofins.getCOFINSQtde().getCST());
						item.setAliquotaCofinsItemNFEntrada(cofins.getCOFINSQtde().getvAliqProd());
						item.setValorCofinsItemNFEntrada(cofins.getCOFINSQtde().getvCOFINS());
					}
				}
				
				Ipi ipi = det.getImposto().getIPI();
				
				if (ipi != null) {
					if (ipi.getIPITrib() != null) {
						item.setCstIpiItemNFEntrada(ipi.getIPITrib().getCST());
						item.setAliquotaIpiItemNFEntrada(ipi.getIPITrib().getpIPI());
						item.setValorIpiItemNFEntrada(ipi.getIPITrib().getvIPI());
					} else if (ipi.getIPINT() != null) {
						item.setCstIpiItemNFEntrada(ipi.getIPINT().getCST());
						item.setAliquotaIpiItemNFEntrada(BigDecimal.ZERO);
						item.setValorIpiItemNFEntrada(BigDecimal.ZERO);
					}
				}
				dao.persist(item);
				
				nfEntrada.getItensNFEntrada().add(item);
			}
			
			// Importa contas a pagar
			if (importarContas) {
				importNfeToContasPagar(nfeProc.getNFe(), nfEntrada, empresa, loja, usuario, dao);
			}
			
			// Importa entrada
			if (importarEstoque) {
				importNfeToEntrada(nfeProc.getNFe(), nfEntrada, empresa, loja, usuario, dao, request, response, context);
			}
			
			dao.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			dao.rollback();
			throw new NfeException(ex.getMessage());
		}
	}
	
	private static void importNfeToEntrada(NFe nfe, NFEntradaVO nfEntrada, EmpresaVO empresa, LojaVO loja, UsuarioVO usuario, GenericDAO dao, HttpServletRequest request, HttpServletResponse response, ServletContext context) throws SQLException {
		EntradaEstoqueVO entrada = new EntradaEstoqueVO();
		
		entrada.setNewRecord(true);
		entrada.setCodigoEmpresa(nfEntrada.getCodigoEmpresa());
		entrada.setCodigoLoja(nfEntrada.getCodigoLoja());
		entrada.setDataHoraEntradaEstoque(Utils.getNow());
		entrada.setStatusEntradaEstoque("A"); // Aberto`
		entrada.setNumeroDocumentoEntradaEstoque(nfEntrada.getCodigoNFEntrada().toString());
		
		entrada.setCodigoEmpresaPessoa(nfEntrada.getCodigoEmpresaEmitenteNFEntrada());
		entrada.setCodigoLojaPessoa(nfEntrada.getCodigoLojaEmitenteNFEntrada());
		entrada.setCodigoPessoa(nfEntrada.getCodigoPessoaEmitenteNFEntrada());
		entrada.setCodigoEnderecoPessoa(nfEntrada.getCodigoEnderecoPessoaEmitenteNFEntrada());
		entrada.setObservacoesEntradaEstoque(nfEntrada.getObservacaoNFEntrada());
		
		dao.persist(entrada);
		
		// Itens
		for (ItemNFEntradaVO itemNF : nfEntrada.getItensNFEntrada()) {
			ItemEntradaEstoqueVO itemEntrada = new ItemEntradaEstoqueVO();
			itemEntrada.setNewRecord(true);
			
			itemEntrada.setCodigoEmpresa(entrada.getCodigoEmpresa());
			itemEntrada.setCodigoLoja(entrada.getCodigoLoja());
			itemEntrada.setCodigoEntradaEstoque(entrada.getCodigoEntradaEstoque());
			
			itemEntrada.setCodigoEmpresaProduto(itemNF.getCodigoEmpresaProduto());
			itemEntrada.setCodigoLojaProduto(itemNF.getCodigoLojaProduto());
			itemEntrada.setCodigoProduto(itemNF.getCodigoProduto());
			
			itemEntrada.setQuantidadeItemEntradaEstoque(itemNF.getQuantidadeItemNFEntrada());
			
			dao.persist(itemEntrada);
		}
		
		// Tabela de ligacao
		NFEntradaEntradaEstoqueVO ligacao = new NFEntradaEntradaEstoqueVO();
		ligacao.setNewRecord(true);
		
		ligacao.setCodigoEmpresa(nfEntrada.getCodigoEmpresa());
		ligacao.setCodigoLoja(nfEntrada.getCodigoLoja());
		ligacao.setCodigoNFEntrada(nfEntrada.getCodigoNFEntrada());
		ligacao.setSerieNFEntrada(nfEntrada.getSerieNFEntrada());
		ligacao.setCodigoEmpresaEmitenteNFEntrada(nfEntrada.getCodigoEmpresaEmitenteNFEntrada());
		ligacao.setCodigoLojaEmitenteNFEntrada(nfEntrada.getCodigoLojaEmitenteNFEntrada());
		ligacao.setCodigoPessoaEmitenteNFEntrada(nfEntrada.getCodigoPessoaEmitenteNFEntrada());
		ligacao.setCodigoEnderecoPessoaEmitenteNFEntrada(nfEntrada.getCodigoEnderecoPessoaEmitenteNFEntrada());
		
		ligacao.setCodigoEmpresaEntradaEstoque(entrada.getCodigoEmpresa());
		ligacao.setCodigoLojaEntradaEstoque(entrada.getCodigoLoja());
		ligacao.setCodigoEntradaEstoque(entrada.getCodigoEntradaEstoque());
		
		dao.persist(ligacao);
		
		// Processamento - execucao da facade
		EntradaEstoqueCadastroFacade entradaFacade = new EntradaEstoqueCadastroFacade();
		entradaFacade.setRequest(request);
		entradaFacade.setResponse(response);
		entradaFacade.setContext(context);
		entradaFacade.setVo(entrada);
		
		entradaFacade.setMethodName("processar");
		
		entradaFacade.setGenericDao(dao);
		
		entradaFacade.setUsuariosessao(usuario);
		entradaFacade.setEmpresasessao(empresa);
		entradaFacade.setLojasessao(loja);
		
		entradaFacade.processar();
		
	}
	
	private static void importNfeToContasPagar(NFe nfe, NFEntradaVO nfEntrada, EmpresaVO empresa, LojaVO loja, UsuarioVO usuario, GenericDAO dao) throws SQLException, NfeException {
		// Checa os parametros de geracao da conta a pagar
		ParametroLojaDAO paramDao = new ParametroLojaDAO();
		
		Integer codigoCentroCusto = paramDao.getIntegerParametro(Constants.Parameters.ImportacaoNFeXML.IMPORTACAO_XML_NFE_CENTRO_CUSTO, empresa.getCodigoEmpresa(), loja.getCodigoLoja());
		Integer codigoPortador = paramDao.getIntegerParametro(Constants.Parameters.ImportacaoNFeXML.IMPORTACAO_XML_NFE_PORTADOR, empresa.getCodigoEmpresa(), loja.getCodigoLoja());
		Integer codigoPlanoConta = paramDao.getIntegerParametro(Constants.Parameters.ImportacaoNFeXML.IMPORTACAO_XML_NFE_PLANO_CONTA, empresa.getCodigoEmpresa(), loja.getCodigoLoja());
		Integer codigoTipoDocumento = paramDao.getIntegerParametro(Constants.Parameters.ImportacaoNFeXML.IMPORTACAO_XML_NFE_TIPO_DOCUMENTO, empresa.getCodigoEmpresa(), loja.getCodigoLoja());
		
		
		if (codigoCentroCusto == null) {
			throw new NfeException("Centro de custo não configurado nos parâmetros da loja!");
		}
		if (codigoPortador == null) {
			throw new NfeException("Código do portador não configurado nos parâmetros da loja!");
		}
		if (codigoPlanoConta == null) {
			throw new NfeException("Código do Plano de Contas não configurado nos parâmetros da loja!");
		}
		if (codigoTipoDocumento == null) {
			throw new NfeException("Código do tipo de documento não configurado nos parâmetros da loja!");
		}
		
		int parcela = 0;
		for (Dup duplicata : nfe.getInfNfe().getCobr().getDup()) {
			parcela++;
			// Gera contas a pagar
			ContaPagarVO conta = new ContaPagarVO();
			conta.setNewRecord(true);
			conta.setCodigoEmpresa(empresa.getCodigoEmpresa());
			conta.setCodigoLoja(loja.getCodigoLoja());
			conta.setDataLancamentoContaPagar(Utils.getToday());
			conta.setCodigoPlanoConta(codigoPlanoConta);
			conta.setCodigoCentroCusto(codigoCentroCusto);
			
			conta.setCodigoEmpresaPessoaContaPagar(nfEntrada.getCodigoEmpresaEmitenteNFEntrada());
			conta.setCodigoLojaPessoaContaPagar(nfEntrada.getCodigoLojaEmitenteNFEntrada());
			conta.setCodigoPessoaContaPagar(nfEntrada.getCodigoPessoaEmitenteNFEntrada());

			conta.setNumeroTituloContaPagar(duplicata.getnDup());
			conta.setDescricaoContaPagar(nfEntrada.getCodigoNFEntrada().toString() + "/" + duplicata.getnDup());
			
			conta.setNumeroParcelasContaPagar(nfe.getInfNfe().getCobr().getDup().size());
			conta.setParcelaContaPagar(parcela);
			conta.setCodigoTipoDocumento(codigoTipoDocumento); 
			conta.setCodigoPortador(codigoPortador);
			conta.setSituacaoContaPagar(ContaPagarVO.SITUACAO_ABERTO);
			conta.setDataEmissaoContaPagar(nfEntrada.getDataEmissaoNFEntrada());
			conta.setDataVencimentoContaPagar(Date.valueOf(duplicata.getdVenc()));
			
			conta.setValorMultaContaPagar(BigDecimal.ZERO);
			conta.setPercentualMultaContaPagar(BigDecimal.ZERO);
	
			conta.setValorJurosDiarioContaPagar(BigDecimal.ZERO);
			conta.setPercentualJurosDiarioContaPagar(BigDecimal.ZERO);
	
			conta.setValorContaPagar(duplicata.getvDup());
			conta.setDescontoAteContaPagar(null);
			
			conta.setObservacaoContaPagar("IMPORTADO DO XML NF-e" + nfe.getInfNfe().getId());
			conta.setFlagDREContaPagar("S");
			conta.setFlagResumoContaPagar("S");
			
			dao.persist(conta);
			
			// Tabela de ligacao
			
			NFEntradaContaPagarVO ligacao = new NFEntradaContaPagarVO();
			ligacao.setNewRecord(true);
			
			ligacao.setCodigoEmpresa(nfEntrada.getCodigoEmpresa());
			ligacao.setCodigoLoja(nfEntrada.getCodigoLoja());
			ligacao.setCodigoNFEntrada(nfEntrada.getCodigoNFEntrada());
			ligacao.setSerieNFEntrada(nfEntrada.getSerieNFEntrada());
			ligacao.setCodigoEmpresaEmitenteNFEntrada(nfEntrada.getCodigoEmpresaEmitenteNFEntrada());
			ligacao.setCodigoLojaEmitenteNFEntrada(nfEntrada.getCodigoLojaEmitenteNFEntrada());
			ligacao.setCodigoPessoaEmitenteNFEntrada(nfEntrada.getCodigoPessoaEmitenteNFEntrada());
			ligacao.setCodigoEnderecoPessoaEmitenteNFEntrada(nfEntrada.getCodigoEnderecoPessoaEmitenteNFEntrada());
			
			ligacao.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
			ligacao.setCodigoLojaContaPagar(conta.getCodigoLoja());
			ligacao.setCodigoContaPagar(conta.getCodigoContaPagar());
			
			dao.persist(ligacao);
		}
	}
}