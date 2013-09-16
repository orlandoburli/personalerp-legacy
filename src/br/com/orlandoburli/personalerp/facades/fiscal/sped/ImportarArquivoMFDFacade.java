package br.com.orlandoburli.personalerp.facades.fiscal.sped;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.fiscal.cupomfiscal.CupomFiscalDAO;
import br.com.orlandoburli.core.dao.fiscal.cupomfiscal.ItemCupomFiscalDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.cupom.exceptions.CupomException;
import br.com.orlandoburli.core.utils.txt.ArquivoTexto;
import br.com.orlandoburli.core.utils.txt.CampoArquivoTexto;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.vo.fiscal.cupomfiscal.CupomFiscalVO;
import br.com.orlandoburli.core.vo.fiscal.cupomfiscal.ItemCupomFiscalVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.sistema.ParametroLojaVO;
import br.com.orlandoburli.core.vo.vendas.pedido.EcfVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.personalerp.facades.fiscal.cupomfiscal.CupomFiscalCadastroFacade;

@IgnoreFacadeAuthentication
public class ImportarArquivoMFDFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private static String IDENTIFICADOR_CONTRIBUINTE = "E02";
	private static String IDENTIFICADOR_CUPOM_FISCAL = "E14";
	private static String IDENTIFICADOR_ITEM_CUPOM_FISCAL = "E15";
//	private static String IDENTIFICADOR_REDUCAOZ_TOTALIZADORES_PARCIAIS = "E13";

	private static String FORMATO_DATE = "yyyyMMdd";

	public void execute() {
		importarMFD("c:\\temp\\chillibeans\\MFD226189_20130704_103133.txt");
		importarMFD("c:\\temp\\chillibeans\\MFD226189_20130704_105319.txt");
		importarMFD("c:\\temp\\chillibeans\\MFD226189_20130704_105816.txt");
	}

	public void importarMFD(String nomeArquivoMFD) {
		// Declaracao dos campos
		BigDecimal cem = new BigDecimal(100);

		CampoArquivoTexto campoIdentificadorRegistro = new CampoArquivoTexto("CAMPO_IDENTIFICADOR", 0, 3);

		ArquivoTexto arquivo = new ArquivoTexto();

		List<CupomFiscalVO> listCupons = new ArrayList<CupomFiscalVO>();
		List<ItemCupomFiscalVO> listItensCupons = new ArrayList<ItemCupomFiscalVO>();

		LojaVO loja = null;

		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);

		String seriePadraoCupomFiscal = "1";

		try {
			dao.getConnection();

			ParametroLojaDAO paramDao = new ParametroLojaDAO();
			paramDao.mergeDAO(dao);

			CupomFiscalDAO cupomDao = new CupomFiscalDAO();
			cupomDao.mergeDAO(dao);

			ItemCupomFiscalDAO itemDao = new ItemCupomFiscalDAO();
			itemDao.mergeDAO(dao);

			ProdutoDAO prodDao = new ProdutoDAO();
			prodDao.mergeDAO(dao);

			
			arquivo.load(nomeArquivoMFD);

			// Declaracao dos campos para registro E14 - Cupom Fiscal
			CampoArquivoTexto campoCupomFiscal = new CampoArquivoTexto("CUPOM_FISCAL", 52, 58);
			CampoArquivoTexto campoCupomDataCupomFiscal = new CampoArquivoTexto("CUPOM_DATA", 58, 66);
			CampoArquivoTexto campoCupomNumeroEcf = new CampoArquivoTexto("CUPOM_NUMERO_ECF", 3, 23);
			CampoArquivoTexto campoCupomModeloEcf = new CampoArquivoTexto("CUPOM_MODELO_ECF", 24, 44);
			CampoArquivoTexto campoCupomSubTotal = new CampoArquivoTexto("CUPOM_SUBTOTAL", 66, 80, 2);
			CampoArquivoTexto campoCupomDesconto = new CampoArquivoTexto("CUPOM_DESCONTO", 80, 93, 2);
			CampoArquivoTexto campoCupomTipoDesconto = new CampoArquivoTexto("CUPOM_TIPO_DESCONTO", 93, 94);
			CampoArquivoTexto campoCupomAcrescimoSubTotal = new CampoArquivoTexto("CUPOM_ACRESCIMO", 94, 107, 2);
			CampoArquivoTexto campoCupomTipoAcrescimo = new CampoArquivoTexto("CUPOM_TIPO_ACRESCIMO", 107, 108);
			CampoArquivoTexto campoCupomValorTotal = new CampoArquivoTexto("CUPOM_VALOR_TOTAL", 108, 122, 2);
			CampoArquivoTexto campoCupomCancelamento = new CampoArquivoTexto("CUPOM_CANCELAMENTO", 122, 123);

			CampoArquivoTexto campoCupomNomeCliente = new CampoArquivoTexto("CUPOM_NOME_CLIENTE", 137, 177);
			CampoArquivoTexto campoCupomCpfCnpjCliente = new CampoArquivoTexto("CUPOM_CPF_CNPJ_CLIENTE", 177, 191);

			// Declaracao dos campos para registro E15 - Itens do Cupom Fiscal
			CampoArquivoTexto campoNumeroItem = new CampoArquivoTexto("", 58, 61);
			CampoArquivoTexto campoCodigoProduto = new CampoArquivoTexto("", 61, 75);
			CampoArquivoTexto campoDescricaoProduto = new CampoArquivoTexto("", 75, 175);
			CampoArquivoTexto campoQuantidadeItem = new CampoArquivoTexto("", 175, 182);
			CampoArquivoTexto campoUnidade = new CampoArquivoTexto("", 182, 185);
			CampoArquivoTexto campoValorUnitario = new CampoArquivoTexto("", 185, 193);
			CampoArquivoTexto campoDescontoItem = new CampoArquivoTexto("", 193, 201, 2);
			CampoArquivoTexto campoAcrescimoItem = new CampoArquivoTexto("", 201, 209, 2);
			CampoArquivoTexto campoValorTotalItemLiquido = new CampoArquivoTexto("", 209, 223, 2);

			// CampoArquivoTexto campoItemCancelado = new CampoArquivoTexto("",
			// 230, 231);
			// CampoArquivoTexto campoQuantidadeCancelada = new
			// CampoArquivoTexto("", 231, 238);
			// CampoArquivoTexto campoValorCancelado = new CampoArquivoTexto("",
			// 238, 251, 2);
			// CampoArquivoTexto campoCancelamentoAcrescimoItem = new
			// CampoArquivoTexto("", 251, 264);
			// CampoArquivoTexto campoIndicadorArredondamento = new
			// CampoArquivoTexto("", 264, 265);

			CampoArquivoTexto campoDecimaisQuantidade = new CampoArquivoTexto("", 265, 266);
			CampoArquivoTexto campoDecimaisValorUnitario = new CampoArquivoTexto("", 266, 267);

			while (arquivo.next()) {

				String identificador = arquivo.getStringField(campoIdentificadorRegistro);

				if (identificador.equals(IDENTIFICADOR_CONTRIBUINTE)) {
					CampoArquivoTexto campoCnpj = new CampoArquivoTexto("CNPJ", 44, 58);

					String cnpj = arquivo.getStringField(campoCnpj);
					loja = new LojaVO();
					loja.setCNPJLoja(cnpj);

					List<LojaVO> listLojas = new LojaDAO().getList(loja);
					if (listLojas.size() > 0) {
						loja = listLojas.get(0);
					}

				} else if (identificador.equals(IDENTIFICADOR_CUPOM_FISCAL)) {

					String cupomFiscal = arquivo.getStringField(campoCupomFiscal);
					Date dataCupomFiscal = arquivo.getDateField(campoCupomDataCupomFiscal, FORMATO_DATE);

					// Verifica se a ECF está cadastrada
					EcfVO ecf = new EcfVO();
					ecf.setNumeroSerieEcf(arquivo.getStringField(campoCupomNumeroEcf));
					ecf = (EcfVO) dao.get(ecf);

					if (ecf == null) {
						ecf = new EcfVO();
						ecf.setNewRecord(true);
						ecf.setNumeroSerieEcf(arquivo.getStringField(campoCupomNumeroEcf));
						ecf.setModeloEcf(arquivo.getStringField(campoCupomModeloEcf));
						ecf.setCodigoEmpresa(loja.getCodigoEmpresa());
						ecf.setCodigoLoja(loja.getCodigoLoja());
						ecf.setCodigoPdv(1); // Caixa padrao.
						dao.persist(ecf);
					}

					// Alimenta CupomFiscalVO
					CupomFiscalVO cupom = new CupomFiscalVO();
					cupom.setNewRecord(true);

					// Verifica se o cupom já existe
					cupom.setCodigoEmpresa(loja.getCodigoEmpresa());
					cupom.setCodigoLoja(loja.getCodigoLoja());
					cupom.setSerieCupomFiscal(seriePadraoCupomFiscal);
					cupom.setNumeroCupomFiscal(cupomFiscal);
					cupom.setNumeroSerieEcf(arquivo.getStringField(campoCupomNumeroEcf));
					cupom.setCodigoPdv(1);

					List<CupomFiscalVO> listFindCupom = cupomDao.getList(cupom);

					if (listFindCupom.size() > 0) {
						cupom = listFindCupom.get(0);
					} else {
						Integer codigoCupomFiscal = 0;

						ParametroLojaVO parametro = new ParametroLojaVO();
						parametro.setCodigoEmpresa(loja.getCodigoEmpresa());
						parametro.setCodigoLoja(loja.getCodigoLoja());
						parametro.setChaveParametro(Constants.Parameters.Geral.PROXIMO_CUPOM_FISCAL);

						parametro = paramDao.get(parametro);

						if (parametro == null) {
							parametro = new ParametroLojaVO();
							parametro.setCodigoEmpresa(loja.getCodigoEmpresa());
							parametro.setCodigoLoja(loja.getCodigoLoja());
							parametro.setChaveParametro(Constants.Parameters.Geral.PROXIMO_CUPOM_FISCAL);
							parametro.setNewRecord(true);
						} else {
							codigoCupomFiscal = Integer.parseInt(parametro.getValorParametro());
						}

						codigoCupomFiscal++;
						parametro.setValorParametro(codigoCupomFiscal.toString());
						paramDao.persist(parametro);

						cupom.setCodigoCupomFiscal(codigoCupomFiscal);
					}

					cupom.setDataHoraCupomFiscal(new Timestamp(dataCupomFiscal.getTime()));
					cupom.setStatusCupomFiscal(arquivo.getStringField(campoCupomCancelamento).equals("N") ? "P" : "C");

					if (cupom.getStatusCupomFiscal().equals("C")) {
						cupom.setDataHoraCancelamentoCupomFiscal(cupom.getDataHoraCupomFiscal());
					}
					cupom.setValorTotalItensCupomFiscal(arquivo.getDecimalField(campoCupomSubTotal));

					// Desconto
					if (arquivo.getStringField(campoCupomTipoDesconto).equals("V")) {
						cupom.setValorDescontoCupomFiscal(arquivo.getDecimalField(campoCupomDesconto));
					} else if (arquivo.getStringField(campoCupomTipoDesconto).equals("P")) {
						cupom.setValorDescontoCupomFiscal(cupom.getValorTotalItensCupomFiscal().multiply(arquivo.getDecimalField(campoCupomDesconto)).divide(cem, BigDecimal.ROUND_CEILING));
					}

					// Acrescimo
					if (arquivo.getStringField(campoCupomTipoAcrescimo).equals("V")) {
						cupom.setValorAcrescimoCupomFiscal(arquivo.getDecimalField(campoCupomAcrescimoSubTotal));
					} else if (arquivo.getStringField(campoCupomTipoAcrescimo).equals("P")) {
						cupom.setValorAcrescimoCupomFiscal(cupom.getValorTotalItensCupomFiscal().multiply(arquivo.getDecimalField(campoCupomAcrescimoSubTotal)).divide(cem, BigDecimal.ROUND_CEILING));
					}

					// Dados do cliente
					cupom.setNomeClienteCupomFiscal(arquivo.getStringField(campoCupomNomeCliente));
					if (arquivo.getIntegerField(campoCupomCpfCnpjCliente) != 0) {
						cupom.setCpfCnpjClienteCupomFiscal(arquivo.getStringField(campoCupomCpfCnpjCliente));
					} else {
						cupom.setCpfCnpjClienteCupomFiscal("");
					}

					cupom.setValorTotalCupomFiscal(arquivo.getDecimalField(campoCupomValorTotal));

					dao.persist(cupom);

					listCupons.add(cupom);

				} else if (identificador.equals(IDENTIFICADOR_ITEM_CUPOM_FISCAL)) {

					CampoArquivoTexto campoCupomFiscalItem = new CampoArquivoTexto("CUPOM_FISCAL_ITEM", 46, 52);

					String cupomFiscal = arquivo.getStringField(campoCupomFiscalItem);

					ItemCupomFiscalVO item = new ItemCupomFiscalVO();

					// Busca cupom fiscal
					CupomFiscalVO cupom = null;
					for (CupomFiscalVO c : listCupons) {
						if (cupomFiscal.equals(c.getNumeroCupomFiscal())) {
							cupom = c;
						}
					}

					if (cupom == null) {
						throw new CupomException("Cupom " + cupomFiscal + " não encontrado no arquivo!\n");
					} else {

						ProdutoVO produto = new ProdutoVO();
						produto.setCodigoReferenciaProduto(arquivo.getStringField(campoCodigoProduto).trim());

						List<ProdutoVO> listProdutos = prodDao.getList(produto);
						if (listProdutos.size() > 0) {
							produto = listProdutos.get(0);
						} else {
							// Verifica se existe a unidade
							UnidadeVO unidade = new UnidadeVO();
							unidade.setSiglaUnidade(arquivo.getStringField(campoUnidade));
							unidade = (UnidadeVO) dao.get(unidade);

							if (unidade == null) {
								unidade = new UnidadeVO();
								unidade.setNewRecord(true);
								unidade.setSiglaUnidade(arquivo.getStringField(campoUnidade).toUpperCase());
								dao.persist(unidade);
							}

							// Produto nao existe, cadastra
							produto = new ProdutoVO();
							produto.setCodigoEmpresa(loja.getCodigoEmpresa());
							produto.setCodigoLoja(loja.getCodigoLoja());
							produto.setCodigoReferenciaProduto(arquivo.getStringField(campoCodigoProduto));
							produto.setDescricaoProduto(arquivo.getStringField(campoDescricaoProduto));
							produto.setSiglaUnidade(unidade.getSiglaUnidade());
							produto.setPermiteVendaFracionadaProduto("N");
							produto.setCodigoTipoTributacao(1); // TODO
																// Parametrizar
																// o codigo da
																// tributacao
							produto.setNewRecord(true);
							dao.persist(produto);
						}

						item.setCodigoEmpresa(cupom.getCodigoEmpresa());
						item.setCodigoLoja(cupom.getCodigoLoja());
						item.setCodigoCupomFiscal(cupom.getCodigoCupomFiscal());
						item.setSerieCupomFiscal(cupom.getSerieCupomFiscal());
						item.setCodigoPdv(cupom.getCodigoPdv());
						item.setSequencialItemCupomFiscal(arquivo.getIntegerField(campoNumeroItem));

						item = itemDao.get(item);

						if (item == null) {
							item = new ItemCupomFiscalVO();
							item.setNewRecord(true);

							item.setCodigoEmpresa(cupom.getCodigoEmpresa());
							item.setCodigoLoja(cupom.getCodigoLoja());
							item.setCodigoCupomFiscal(cupom.getCodigoCupomFiscal());
							item.setSerieCupomFiscal(cupom.getSerieCupomFiscal());
							item.setCodigoPdv(cupom.getCodigoPdv());
							item.setSequencialItemCupomFiscal(arquivo.getIntegerField(campoNumeroItem));
						}

						item.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
						item.setCodigoLojaProduto(produto.getCodigoLoja());
						item.setCodigoProduto(produto.getCodigoProduto());

						// Determina a precisao dos campos valor unitario e
						// quantidade
						campoValorUnitario.setPrecisao(arquivo.getIntegerField(campoDecimaisValorUnitario));
						campoQuantidadeItem.setPrecisao(arquivo.getIntegerField(campoDecimaisQuantidade));

						item.setQuantidadeItemCupomFiscal(arquivo.getDecimalField(campoQuantidadeItem));
						item.setValorUnitarioItemCupomFiscal(arquivo.getDecimalField(campoValorUnitario));
						item.setValorDescontoItemCupomFiscal(arquivo.getDecimalField(campoDescontoItem));
						item.setValorAcrescimoItemCupomFiscal(arquivo.getDecimalField(campoAcrescimoItem));
						item.setValorLiquidoItemCupomFiscal(arquivo.getDecimalField(campoValorTotalItemLiquido));

						// Valor Bruto - Calculado
						item.setValorTotalItemCupomFiscal(item.getQuantidadeItemCupomFiscal().multiply(item.getValorUnitarioItemCupomFiscal()));

						dao.persist(item);

						listItensCupons.add(item);
					}
				}
			}

			dao.commit();

			// Processa os cupons
			for (CupomFiscalVO cupom : listCupons) {
				CupomFiscalCadastroFacade.validarCupomFiscal(dao, cupom, loja);
			}
			
			write("ok");
			
		} catch (IOException e) {
			dao.rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (SQLException e) {
			dao.rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (CupomException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}