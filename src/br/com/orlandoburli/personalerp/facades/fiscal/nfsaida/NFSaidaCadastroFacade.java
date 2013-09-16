package br.com.orlandoburli.personalerp.facades.fiscal.nfsaida;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.nfsaida.ItemNFSaidaDAO;
import br.com.orlandoburli.core.dao.fiscal.nfsaida.NFSaidaDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.extras.nfe.utils.NFSaidaToNfe;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.TributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.sistema.ParametroLojaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class NFSaidaCadastroFacade extends BaseCadastroFlexFacade<NFSaidaVO, NFSaidaDAO>{

	private static final long serialVersionUID = 1L;
	
	private String DataSaidaAuxiliar;
	private String HoraSaidaAuxiliar;
	
	private String MotivoCancelamento;

	public NFSaidaCadastroFacade() {
		super();
		this.setWriteVoOnInsert(true);
		this.setWriteVoOnUpdate(true);
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoNFSaida(getNextNFSaida(getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()));
		this.getVo().setSerieNFSaida("1");
		this.getVo().setModeloNFSaida("55");
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		try {
			// Seta o campo datahorasaida
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			try {
				getVo().setDataHoraSaidaNFSaida(new Timestamp(sdf.parse(getDataSaidaAuxiliar() + " " + getHoraSaidaAuxiliar()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (!validaAlteraNF(getVo(), this.messages)) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getVo().setStatusNFSaida(NFEletronicaVO.STATUS_DIGITACAO);
		return super.doBeforeSave();
	}
	
	@Override
	public void doAfterCommit(String operacao) throws SQLException {
		try {
			validarNF(getGenericDao(), getVo(), getLojasessao());
		} catch (NfeException e) {
			e.printStackTrace();
		}
		super.doAfterCommit(operacao);
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		try {
			if (!validaAlteraNF(getVo(), this.messages)) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}
	
	public static boolean validaAlteraNF(NFSaidaVO nfsaida, List<MessageVO> messages) throws SQLException {
		NFSaidaDAO dao = new NFSaidaDAO();
		NFSaidaVO nfsaidaClone = dao.get(nfsaida);
		if (nfsaidaClone != null && nfsaidaClone.getStatusNFSaida() != null) {
			String mensagem = null;
			if (nfsaidaClone.getStatusNFSaida().equals(NFEletronicaVO.STATUS_AUTORIZADA)) {
				mensagem = "NF-e já foi autorizada!";
			} else if (nfsaidaClone.getStatusNFSaida().equals(NFEletronicaVO.STATUS_CANCELADA)) {
				mensagem = "NF-e já foi cancelada!";
			} else {
				nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_DIGITACAO); // Volta pro status de digitação
			}
			
			if (mensagem != null) {
				messages.add(new MessageVO(mensagem));
				return false;
			}
		}
		return true;
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoas() {
		try {
			EnderecoPessoaVO _filter = new EnderecoPessoaVO();
			write(Utils.voToXml(getGenericDao().getList(_filter), getGenericDao().getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void operacao() {
		try {
			OperacaoTributacaoVO _filter = new OperacaoTributacaoVO();
			write(Utils.voToXml(getGenericDao().getList(_filter), getGenericDao().getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void validar() {
		try {
			validarNF(getGenericDao(), getVo(), getLojasessao());
			write("ok");
		} catch (SQLException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (NfeException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void consultar() {
		try {
			writeErrorMessage(NFSaidaToNfe.consulta(getVo()));
		} catch (SQLException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (NfeException e) {
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void cancelar() {
		try {
			NFSaidaToNfe.cancelar(getVo(), MotivoCancelamento);
			write("ok");
		} catch (SQLException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (NfeException e) {
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}

	public static void validarNF(GenericDAO _dao, NFSaidaVO vo, LojaVO loja) throws SQLException, NfeException {
		try {
			_dao.setAutoCommit(false);
			
			vo = (NFSaidaVO) _dao.get(vo);
			
			if (vo.getStatusNFSaida().equals(NFEletronicaVO.STATUS_AUTORIZADA) || vo.getStatusNFSaida().equals(NFEletronicaVO.STATUS_CANCELADA)) {
				return;
			}
			
			BigDecimal cem = new BigDecimal(100);
			
			EnderecoPessoaVO destinatario = new EnderecoPessoaVO();
			destinatario.setCodigoEmpresa(vo.getCodigoEmpresaDestinatarioNFSaida());
			destinatario.setCodigoLoja(vo.getCodigoLojaDestinatarioNFSaida());
			destinatario.setCodigoPessoa(vo.getCodigoPessoaDestinatarioNFSaida());
			destinatario.setCodigoEnderecoPessoa(vo.getCodigoEnderecoPessoaDestinatarioNFSaida());
			
			destinatario = (EnderecoPessoaVO) _dao.get(destinatario);
			
			ItemNFSaidaVO filter = new ItemNFSaidaVO();
			filter.setCodigoEmpresa(vo.getCodigoEmpresa());
			filter.setCodigoLoja(vo.getCodigoLoja());
			filter.setCodigoNFSaida(vo.getCodigoNFSaida());
			filter.setSerieNFSaida(vo.getSerieNFSaida());
			
			ItemNFSaidaDAO itemDao = new ItemNFSaidaDAO();
			
			List<ItemNFSaidaVO> list = itemDao.getList(filter);
			
			// Zera os totais
			
			vo.setValorBaseCalculoIcmsNFSaida(BigDecimal.ZERO);
			vo.setValorIcmsNFSaida(BigDecimal.ZERO);
			vo.setValorBaseCalculoIcmsSTNFSaida(BigDecimal.ZERO);
			vo.setValorIcmsSTNFSaida(BigDecimal.ZERO);
			vo.setValorFreteNFSaida(BigDecimal.ZERO);
			
			vo.setValorTotalProdutosNFSaida(BigDecimal.ZERO);
			vo.setValorTotalDescontoNFSaida(BigDecimal.ZERO);
			vo.setValorTotalIpiNFSaida(BigDecimal.ZERO);
			vo.setValorTotalPisNFSaida(BigDecimal.ZERO);
			vo.setValorTotalCofinsNFSaida(BigDecimal.ZERO);
			vo.setValorOutrasDespesasNFSaida(BigDecimal.ZERO);
			vo.setValorTotalNFSaida(BigDecimal.ZERO);
			
			OperacaoTributacaoVO operacao = new OperacaoTributacaoVO();
			operacao.setOperacaoTributacao(vo.getOperacaoTributacao());
			operacao = (OperacaoTributacaoVO) _dao.get(operacao);
			
			for (ItemNFSaidaVO item : list) {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				
				produto = (ProdutoVO) _dao.get(produto);
				
				if (produto.getCodigoTipoTributacao() == null) {
					throw new NfeException("O produto \n[" + produto.getDescricaoProduto() + "] \n código " + produto.getCodigoProduto() + " não está configurado quanto ao tipo de tributação!");
				}
				
				TributacaoVO tributacao = new TributacaoVO();
				tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
				tributacao.setUFOrigemTributacao(loja.getUFLoja());
				tributacao.setUFDestinoTributacao(destinatario.getSiglaUFEnderecoPessoa());
				tributacao.setOperacaoTributacao(vo.getOperacaoTributacao());
				
				tributacao = (TributacaoVO) _dao.get(tributacao);
				
				// Se nao achou especifico para este estado, tenta para todos os estados
				if (tributacao == null) {
					tributacao = new TributacaoVO();
					tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
					tributacao.setUFOrigemTributacao(loja.getUFLoja());
					tributacao.setUFDestinoTributacao("XX");
					tributacao.setOperacaoTributacao(vo.getOperacaoTributacao());
					
					tributacao = (TributacaoVO) _dao.get(tributacao);
				}
				
				if (tributacao == null) {
					_dao.rollback();
					throw new NfeException("Configuração de tributação não encontrada\npara a operação [" + operacao.getDescricaoOperacaoTributacao() + "]\npara emitente " + loja.getUFLoja() + "\ne destinatário " + destinatario.getSiglaUFEnderecoPessoa() + "!");
				}
				
				item.setCfopItemNFSaida(tributacao.getCfopTributacao());
				
				item.setCstIcmsItemNFSaida(tributacao.getClassificacaoTributariaIcmsTributacao());
				item.setCstIpiItemNFSaida(tributacao.getCstIpiTributacao());
				item.setCstCofinsItemNFSaida(tributacao.getCstCofinsTributacao());
				item.setCstPisItemNFSaida(tributacao.getCstPisTributacao());
				
				item.setAliquotaIcmsItemNFSaida(tributacao.getAliquotaIcmsTributacao());
				item.setAliquotaPisItemNFSaida(tributacao.getAliquotaPisTributacao());
				item.setAliquotaCofinsItemNFSaida(tributacao.getAliquotaCofinsTributacao());
				item.setAliquotaIpiItemNFSaida(tributacao.getAliquotaIpiTributacao());
				
				// Calcula Bruto
				item.setValorTotalBrutoItemNFSaida(item.getValorUnitarioItemNFSaida()
						.multiply(item.getQuantidadeItemNFSaida()));
				
				if (item.getValorTotalBrutoItemNFSaida() == null) {
					item.setValorTotalBrutoItemNFSaida(BigDecimal.ZERO);
				}
				if (item.getValorFreteItemNFSaida() == null) {
					item.setValorFreteItemNFSaida(BigDecimal.ZERO);
				}
				if (item.getValorOutrasDespesasItemNFSaida() == null) {
					item.setValorOutrasDespesasItemNFSaida(BigDecimal.ZERO);
				}
				if (item.getValorDescontoItemNFSaida() == null) {
					item.setValorDescontoItemNFSaida(BigDecimal.ZERO);
				}
				
				// Calcula Liquido
				item.setValorTotalLiquidoItemNFSaida(
						item.getValorTotalBrutoItemNFSaida()
						.add(item.getValorFreteItemNFSaida())
						.add(item.getValorOutrasDespesasItemNFSaida())
						.subtract(item.getValorDescontoItemNFSaida()));
				
				
				// COFINS
				item.setValorCofinsItemNFSaida(item.getValorTotalLiquidoItemNFSaida()
						.multiply(item.getAliquotaCofinsItemNFSaida())
						.divide(cem, BigDecimal.ROUND_HALF_EVEN));
				
				// PIS
				item.setValorPisItemNFSaida(item.getValorTotalLiquidoItemNFSaida()
						.multiply(item.getAliquotaPisItemNFSaida())
						.divide(cem, BigDecimal.ROUND_HALF_EVEN));
				
				// IPI
				item.setValorIpiItemNFSaida(item.getValorTotalLiquidoItemNFSaida()
						.multiply(item.getAliquotaIpiItemNFSaida())
						.divide(cem, BigDecimal.ROUND_HALF_EVEN));
				
				// ICMS
				if (tributacao.getFormaCalculoIcmsTributacao().equals("NT")) {
					// Não tributado
					item.setBaseCalculoIcmsItemNFSaida(BigDecimal.ZERO);
					item.setBaseIcmsStItemNFSaida(BigDecimal.ZERO);
					item.setValorIcmsItemNFSaida(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFSaida(BigDecimal.ZERO);
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("ST")) {
					
					// Substituicao Tributaria
					item.setBaseCalculoIcmsItemNFSaida(item.getValorTotalLiquidoItemNFSaida());
					item.setBaseIcmsStItemNFSaida(item.getValorTotalLiquidoItemNFSaida());
					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("A")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
						item.setBaseIcmsStItemNFSaida(item.getBaseIcmsStItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
					}
					
					// Verifica se Outras Despesas fazem parte da base de cálculo (antes da reducao)...
					if (tributacao.getOutrasDespesasBaseIcmsTributacao().equals("A")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
						item.setBaseIcmsStItemNFSaida(item.getBaseIcmsStItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
					}
					
					// Aliquotas de ICMS
					item.setAliquotaIcmsItemNFSaida(tributacao.getAliquotaIcmsTributacao());
					item.setAliquotaIcmsSTItemNFSaida(tributacao.getAliquotaIcmsStTributacao());
					
					if (tributacao.getReducaoBaseCalculoIcmsTributacao() != null && tributacao.getReducaoBaseCalculoIcmsTributacao().compareTo(BigDecimal.ZERO) > 0) {
						item.setBaseCalculoIcmsItemNFSaida(
								item.getBaseCalculoIcmsItemNFSaida()
								.subtract(item.getBaseCalculoIcmsItemNFSaida()
								.multiply(tributacao.getReducaoBaseCalculoIcmsTributacao())
								.divide(cem)));
					}
					
					// Verifica se o IPI faz parte da base de cálculo (depois da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("D")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
						item.setBaseIcmsStItemNFSaida(item.getBaseIcmsStItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
					}
					
					// Verifica se Outras Despesas fazem parte da base de cálculo (depois da reducao)...
					if (tributacao.getOutrasDespesasBaseIcmsTributacao().equals("D")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
						item.setBaseIcmsStItemNFSaida(item.getBaseIcmsStItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
					}
					
					
					// Valor do ICMS
					item.setValorIcmsItemNFSaida(item.getAliquotaIcmsItemNFSaida()
							.multiply(item.getBaseCalculoIcmsItemNFSaida())
							.divide(cem));
					
					// Valor do ICMS ST
					// A base de calculo é aumentada pelo MVA (Margem do Valor Agregado)
					item.setBaseIcmsStItemNFSaida(item.getBaseIcmsStItemNFSaida()
							.add(item.getBaseIcmsStItemNFSaida()
									.multiply(tributacao.getMargemValorAgregadoTributacao()
											.divide(cem))));
					
					BigDecimal icmsSt = item.getBaseIcmsStItemNFSaida().multiply(item.getAliquotaIcmsSTItemNFSaida().divide(cem));
					icmsSt = icmsSt.subtract(item.getValorIcmsItemNFSaida());
					item.setValorIcmsSTItemNFSaida(icmsSt);
					
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("GN")) {
					// TODO Garantido Normal 
//					item.setBaseCalculoIcmsItemNFSaida(item.getValorTotalLiquidoItemNFSaida());
//					item.setValorIcmsItemNFSaida(item.getAliquotaIcmsItemNFSaida().multiply(item.getBaseCalculoIcmsItemNFSaida()));
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("GI")) {
					// TODO Garantido Integral
//					item.setBaseCalculoIcmsItemNFSaida(item.getValorTotalLiquidoItemNFSaida());
//					item.setValorIcmsItemNFSaida(item.getAliquotaIcmsItemNFSaida().multiply(item.getBaseCalculoIcmsItemNFSaida()));
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("TI")) {
					// Tributacao Integral
					item.setBaseIcmsStItemNFSaida(BigDecimal.ZERO);
					item.setValorIcmsSTItemNFSaida(BigDecimal.ZERO);
					
					item.setBaseCalculoIcmsItemNFSaida(item.getValorTotalBrutoItemNFSaida());
					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("A")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
					}
					
					// Verifica se Outras Despesas fazem parte da base de cálculo (antes da reducao)...
					if (tributacao.getOutrasDespesasBaseIcmsTributacao().equals("A")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
						
					}
					
					// Verifica se tem redução na base de calculo
					if (tributacao.getReducaoBaseCalculoIcmsTributacao() != null && 
						tributacao.getReducaoBaseCalculoIcmsTributacao().compareTo(BigDecimal.ZERO) > 0) {
						item.setBaseCalculoIcmsItemNFSaida(
								item.getBaseCalculoIcmsItemNFSaida()
								.subtract(item.getBaseCalculoIcmsItemNFSaida()
								.multiply(tributacao.getReducaoBaseCalculoIcmsTributacao())
								.divide(cem)));
					}
					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("D")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorIpiItemNFSaida()));
					}
					
					// Verifica se Outras Despesas fazem parte da base de cálculo (antes da reducao)...
					if (tributacao.getOutrasDespesasBaseIcmsTributacao().equals("D")) {
						item.setBaseCalculoIcmsItemNFSaida(item.getBaseCalculoIcmsItemNFSaida()
								.add(item.getValorOutrasDespesasItemNFSaida()));
						
					}
					
					item.setValorIcmsItemNFSaida(item.getAliquotaIcmsItemNFSaida()
							.multiply(item.getBaseCalculoIcmsItemNFSaida())
							.divide(cem));
				}
				
				if (item.getBaseCalculoIcmsItemNFSaida() != null) {
					vo.setValorBaseCalculoIcmsNFSaida(vo.getValorBaseCalculoIcmsNFSaida().add(item.getBaseCalculoIcmsItemNFSaida()));
				}
				
				if (item.getValorIcmsItemNFSaida() != null) {
					vo.setValorIcmsNFSaida(vo.getValorIcmsNFSaida().add(item.getValorIcmsItemNFSaida()));
				}
				
				if (item.getBaseIcmsStItemNFSaida() != null) {
					vo.setValorBaseCalculoIcmsSTNFSaida(vo.getValorBaseCalculoIcmsSTNFSaida().add(item.getBaseIcmsStItemNFSaida()));
				}
				
				if (item.getValorIcmsSTItemNFSaida() != null) {
					vo.setValorIcmsSTNFSaida(vo.getValorIcmsSTNFSaida().add(item.getValorIcmsSTItemNFSaida()));
				}
				
				if (item.getValorTotalBrutoItemNFSaida() != null) {
					vo.setValorTotalProdutosNFSaida(vo.getValorTotalProdutosNFSaida().add(item.getValorTotalBrutoItemNFSaida()));
				}
				
				if (item.getValorDescontoItemNFSaida() != null) {
					vo.setValorTotalDescontoNFSaida(vo.getValorTotalDescontoNFSaida().add(item.getValorDescontoItemNFSaida()));
				}
				
				if (item.getValorIpiItemNFSaida() != null) {
					vo.setValorTotalIpiNFSaida(vo.getValorTotalIpiNFSaida().add(item.getValorIpiItemNFSaida()));
				}
				
				if (item.getValorPisItemNFSaida() != null) {
					vo.setValorTotalPisNFSaida(vo.getValorTotalPisNFSaida().add(item.getValorPisItemNFSaida()));
				}
				
				if (item.getValorCofinsItemNFSaida() != null) {
					vo.setValorTotalCofinsNFSaida(vo.getValorTotalCofinsNFSaida().add(item.getValorCofinsItemNFSaida()));
				}
				
				if (item.getValorOutrasDespesasItemNFSaida() != null) {
					vo.setValorOutrasDespesasNFSaida(vo.getValorOutrasDespesasNFSaida().add(item.getValorOutrasDespesasItemNFSaida()));
				}
				
				if (item.getValorTotalLiquidoItemNFSaida() != null) {
					vo.setValorTotalNFSaida(vo.getValorTotalNFSaida().add(item.getValorTotalLiquidoItemNFSaida()));
				}
				
				if (item.getValorFreteItemNFSaida() != null) {
					vo.setValorFreteNFSaida(vo.getValorFreteNFSaida().add(item.getValorFreteItemNFSaida()));
				}
				
				_dao.persist(item);
			}
			
			vo.setStatusNFSaida(NFEletronicaVO.STATUS_VALIDADA);
			
			_dao.persist(vo);
			
			_dao.commit();
			
		} catch (SQLException e) {
			_dao.rollback();
			throw e;
		}
	}

	public void gerarnfe() {
		try {
			validarNF(getGenericDao(), getVo(), getLojasessao());
			NFSaidaToNfe.execute(getVo());
			write("nfegerada");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch(NfeException e) {
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@IgnoreMethodAuthentication
	public void transmitir() {
		try {
			System.out.println("Transmitindo nf-e...");
			NFSaidaToNfe.transmitir(getVo());
			write("ok");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (NfeException e) {
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public static Integer getNextNFSaida(int CodigoEmpresa, int CodigoLoja) {
		ParametroLojaDAO paramDao = new ParametroLojaDAO();
		try {
			ParametroLojaVO param = new ParametroLojaVO();
			param.setChaveParametro(Constants.Parameters.NFSaida.PROXIMA_NF_SAIDA);
			param.setCodigoEmpresa(CodigoEmpresa);
			param.setCodigoLoja(CodigoLoja);
			param = paramDao.get(param);
			
			if (param == null) {
				param = new ParametroLojaVO();
				param.setNewRecord(true);
				param.setChaveParametro(Constants.Parameters.NFSaida.PROXIMA_NF_SAIDA);
				param.setCodigoEmpresa(CodigoEmpresa);
				param.setCodigoLoja(CodigoLoja);
				param.setValorParametro("0");
			}
			
			Integer CodigoNFSaida = null;
			try {
				CodigoNFSaida = Integer.parseInt(param.getValorParametro());
			} catch (NumberFormatException e) {
				CodigoNFSaida = 0;
			}
			
			if (CodigoNFSaida == null) {
				CodigoNFSaida = 0;
			}
			CodigoNFSaida += 1;
			
			param.setValorParametro(CodigoNFSaida.toString());
			paramDao.persist(param);
			
			return CodigoNFSaida;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setDataSaidaAuxiliar(String dataSaidaAuxiliar) {
		DataSaidaAuxiliar = dataSaidaAuxiliar;
	}

	public String getDataSaidaAuxiliar() {
		return DataSaidaAuxiliar;
	}

	public void setHoraSaidaAuxiliar(String horaSaidaAuxiliar) {
		HoraSaidaAuxiliar = horaSaidaAuxiliar;
	}

	public String getHoraSaidaAuxiliar() {
		return HoraSaidaAuxiliar;
	}

	public String getMotivoCancelamento() {
		return MotivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		MotivoCancelamento = motivoCancelamento;
	}
}