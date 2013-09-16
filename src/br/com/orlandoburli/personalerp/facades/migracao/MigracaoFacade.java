package br.com.orlandoburli.personalerp.facades.migracao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.RomaneioToraDAO;
import br.com.orlandoburli.core.dao.financeiro.BancoDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.RecebimentoDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.ItemNFEntradaDAO;
import br.com.orlandoburli.core.dao.rh.CargoDAO;
import br.com.orlandoburli.core.dao.rh.CboDAO;
import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.FuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.VerbaContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasDiaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.view.vendas.VendasDiaView;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.bi.vendas.VendaDiariaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.RomaneioToraVO;
import br.com.orlandoburli.core.vo.financeiro.BancoVO;
import br.com.orlandoburli.core.vo.financeiro.FormaPagamentoVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.RecebimentoVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.vo.rh.CargoVO;
import br.com.orlandoburli.core.vo.rh.CboVO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

@IgnoreFacadeAuthentication
public class MigracaoFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	public static final int CODIGO_EMPRESA_PADRAO = 1;
	public static final int CODIGO_LOJA_PADRAO = 1;
	private static String FOLDER = "C:\\temp\\";

	@IgnoreMethodAuthentication
	public void execute() {
		try {
			// CHILI BEANS
			// Contas a pagar
			// geraLoja();
			// importaFornecChili();
			// importaContaPagarChili();
			// importaBaixaContaPagarChili();
			// baixaContasAntigasChili();

			// Contas a receber
			// geraContasReceber();
			// importaPlanoContasChili();
			// importaContaPagarXPlanoContaChili();
			// BigDecimal.ROUND_CEILING
			// importaVendasChili();

			// Tabela de precos
			importaTabelaPrecos();

		} catch (Exception e) {
			e.printStackTrace();
			write(e.getMessage());
		}
	}

	@IgnoreMethodAuthentication
	public void importarcontas() {
		try {
			FileInputStream in = new FileInputStream("c:\\temp\\contas_luz.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String linha = "";
			int linhas = 0;
			GenericDAO dao = new GenericDAO();

			dao.setAutoCommit(false);

			Map<String, Integer> map = null;

			while ((linha = reader.readLine()) != null) {
				linhas++;

				if (linhas == 1) {
					map = buscaCampos(linha);
					continue;
				}

				String[] campos = linha.split(";");

				ContaPagarVO conta = new ContaPagarVO();

				conta.setNewRecord(true);
				conta.setSituacaoContaPagar("A");
				conta.setFlagResumoContaPagar("S");
				conta.setFlagDREContaPagar("S");
				
				conta.setCodigoEmpresa(Integer.parseInt(campos[map.get("CodigoEmpresa")]));
				conta.setCodigoLoja(Integer.parseInt(campos[map.get("CodigoLoja")]));
				conta.setDataEmissaoContaPagar(getDate(campos[map.get("emissao")]));
				conta.setDataVencimentoContaPagar(getDate(campos[map.get("vencimento")]));
				conta.setNumeroParcelasContaPagar(1);
				conta.setParcelaContaPagar(1);
				conta.setNumeroTituloContaPagar(campos[map.get("documento")]);
				conta.setDataLancamentoContaPagar(Utils.getToday());
				conta.setValorContaPagar(getNumber(campos[map.get("valor")], 2));

				conta.setCodigoPlanoConta(Integer.parseInt(campos[map.get("CodigoPlanoConta")]));
				conta.setCodigoCentroCusto(Integer.parseInt(campos[map.get("CodigoCentroCusto")]));

				conta.setCodigoEmpresaPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoEmpresaPessoaContaPagar")]));
				conta.setCodigoLojaPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoLojaPessoaContaPagar")]));
				conta.setCodigoPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoPessoaContaPagar")]));
				conta.setCodigoTipoDocumento(Integer.parseInt(campos[map.get("CodigoTipoDocumento")]));
				conta.setCodigoPortador(Integer.parseInt(campos[map.get("CodigoPortador")]));
				
				conta.setObservacaoContaPagar("CONTA IMPORTADA POR PLANILHA EXCEL");
				
				dao.persist(conta);

			}

			write("Linhas importadas: " + linhas + "\n");
			dao.commit();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void importarcontas2() {
		try {
			FileInputStream in = new FileInputStream("c:\\temp\\contas_fortuna.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String linha = "";
			int linhas = 0;
			GenericDAO dao = new GenericDAO();

			dao.setAutoCommit(false);

			Map<String, Integer> map = null;

			while ((linha = reader.readLine()) != null) {
				linhas++;

				if (linhas == 1) {
					map = buscaCampos(linha);
					continue;
				}

				String[] campos = linha.split(";");

				ContaPagarVO conta = new ContaPagarVO();

				conta.setNewRecord(true);
				conta.setSituacaoContaPagar("A");
				conta.setFlagResumoContaPagar("S");
				conta.setFlagDREContaPagar("S");
				
				conta.setCodigoEmpresa(Integer.parseInt(campos[map.get("CodigoEmpresa")]));
				conta.setCodigoLoja(Integer.parseInt(campos[map.get("CodigoLoja")]));
				conta.setDataEmissaoContaPagar(getDate(campos[map.get("emissao")]));
				conta.setDataVencimentoContaPagar(getDate(campos[map.get("vencimento")]));
				conta.setNumeroParcelasContaPagar(1);
				conta.setParcelaContaPagar(1);
				conta.setNumeroTituloContaPagar(campos[map.get("documento")]);
				conta.setDataLancamentoContaPagar(Utils.getToday());
				conta.setValorContaPagar(getNumber(campos[map.get("valor")], 2));

				conta.setCodigoPlanoConta(Integer.parseInt(campos[map.get("CodigoPlanoConta")]));
				conta.setCodigoCentroCusto(Integer.parseInt(campos[map.get("CodigoCentroCusto")]));

				conta.setCodigoEmpresaPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoEmpresaPessoaContaPagar")]));
				conta.setCodigoLojaPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoLojaPessoaContaPagar")]));
				conta.setCodigoPessoaContaPagar(Integer.parseInt(campos[map.get("CodigoPessoaContaPagar")]));
				conta.setCodigoTipoDocumento(Integer.parseInt(campos[map.get("CodigoTipoDocumento")]));
				conta.setCodigoPortador(Integer.parseInt(campos[map.get("CodigoPortador")]));
				
				conta.setObservacaoContaPagar("CONTA IMPORTADA POR PLANILHA EXCEL");
				
				dao.persist(conta);

			}

			write("Linhas importadas: " + linhas + "\n");
			dao.commit();
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}

	@IgnoreMethodAuthentication
	public void precoestoque() {
		GenericDAO dao = new GenericDAO();
		try {
			EstoqueDAO estoqueDao = new EstoqueDAO();
			ItemNFEntradaDAO itemNfEntradaDao = new ItemNFEntradaDAO();

			dao.setAutoCommit(false);
			dao.getConnection();

			dao.mergeDAO(estoqueDao);
			dao.mergeDAO(itemNfEntradaDao);

			for (EstoqueVO estoque : estoqueDao.getList()) {
				// Busca ultima compra do item

				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(estoque.getCodigoEmpresa());
				produto.setCodigoLoja(estoque.getCodigoLoja());
				produto.setCodigoProduto(estoque.getCodigoProduto());

				produto = (ProdutoVO) dao.get(produto);

				ItemNFEntradaVO itemFilter = new ItemNFEntradaVO();
				itemFilter.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
				itemFilter.setCodigoLojaProduto(produto.getCodigoLoja());
				itemFilter.setCodigoProduto(produto.getCodigoProduto());

				List<ItemNFEntradaVO> listItemNfEntrada = itemNfEntradaDao.getList(itemFilter, "DataEmissaoNFEntrada DESC");

				if (listItemNfEntrada.size() > 0) {
					ItemNFEntradaVO item = listItemNfEntrada.get(0);
					estoque.setPrecoMedioEstoque(item.getValorUnitarioItemNFEntrada());
					dao.persist(estoque);
				}
			}

			dao.commit();

		} catch (SQLException e) {
			dao.rollback();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			dao.rollback();
			e.printStackTrace();
		}
	}

	public static void geraContasReceber() throws SQLException, ClassNotFoundException {
		// Gerar contas a receber a partir das vendas
		List<LojaVO> lojas = new LojaDAO().getList();

		VendasDiaDAO vendasDao = new VendasDiaDAO();
		vendasDao.setAutoCommit(false);

		vendasDao.getConnection();

		ContaReceberDAO contaDao = new ContaReceberDAO();
		contaDao.mergeDAO(vendasDao);

		RecebimentoDAO recebimentoDao = new RecebimentoDAO();
		recebimentoDao.mergeDAO(vendasDao);

		BaixaContaReceberDAO baixaDao = new BaixaContaReceberDAO();
		baixaDao.mergeDAO(vendasDao);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat extenso = new SimpleDateFormat("yyyyMMdd");

		for (LojaVO loja : lojas) {
			VendasDiaView filter = new VendasDiaView();
			filter.setCodigoEmpresa(loja.getCodigoEmpresa());
			filter.setCodigoLoja(loja.getCodigoLoja());

			// Busca as vendas para esta loja
			List<VendasDiaView> vendas = vendasDao.getList(filter, "Data");

			for (VendasDiaView venda : vendas) {

				ContaReceberVO conta = new ContaReceberVO();
				conta.setNewRecord(true);

				conta.setCodigoEmpresa(venda.getCodigoEmpresa());
				conta.setCodigoLoja(venda.getCodigoLoja());
				conta.setParcelaContaReceber(1);
				conta.setNumeroParcelasContaReceber(1);
				conta.setDescricaoContaReceber("CONTA GERADA PELAS VENDAS DO DIA " + sdf.format(venda.getData()));

				conta.setDataLancamentoContaReceber(new Timestamp(venda.getData().getTime()));
				conta.setDataEmissaoContaReceber(venda.getData());
				conta.setDataVencimentoContaReceber(venda.getData());
				conta.setNumeroTituloContaReceber(extenso.format(venda.getData()));
				conta.setSituacaoContaReceber("Q"); // Quitado
				conta.setValorContaReceber(venda.getValorTotal());

				conta.setCodigoPlanoConta(1); // TODO
				conta.setCodigoCentroCusto(1); // TODO
				conta.setCodigoTipoDocumento(1); // TODO
				conta.setCodigoPortador(1); // TODO

				contaDao.persist(conta);

				// Gerar baixas
				RecebimentoVO recebimento = new RecebimentoVO();
				recebimento.setNewRecord(true);
				recebimento.setCodigoEmpresa(conta.getCodigoEmpresa());
				recebimento.setCodigoLoja(conta.getCodigoLoja());
				recebimento.setCodigoFormaRecebimento(1); // Dinheiro
				recebimento.setDataHoraRecebimento(conta.getDataLancamentoContaReceber());
				recebimento.setValorRecebimento(conta.getValorContaReceber());

				recebimentoDao.persist(recebimento);

				BaixaContaReceberVO baixa = new BaixaContaReceberVO();
				baixa.setNewRecord(true);

				baixa.setCodigoEmpresaContaReceber(conta.getCodigoEmpresa());
				baixa.setCodigoLojaContaReceber(conta.getCodigoLoja());
				baixa.setCodigoContaReceber(conta.getCodigoContaReceber());

				baixa.setCodigoEmpresa(recebimento.getCodigoEmpresa());
				baixa.setCodigoLoja(recebimento.getCodigoLoja());
				baixa.setCodigoRecebimento(recebimento.getCodigoRecebimento());

				baixa.setValorRecebimento(recebimento.getValorRecebimento());

				baixaDao.persist(baixa);
			}
		}

		vendasDao.commit();
	}

	public static Map<String, Integer> buscaCampos(String linha) {
		String[] campos = linha.split(";");
		Map<String, Integer> map = new HashMap<String, Integer>(campos.length);

		for (int i = 0; i < campos.length; i++) {
			map.put(campos[i].replace("\"", ""), i);
		}
		return map;
	}

	public static String getCampo(Map<String, Integer> map, String[] campos, String nomeCampo, String linha) {
		String valor = campos[map.get(nomeCampo)];
		if (valor != null) {
			valor = valor.substring(1, valor.length() - 1);
		}
		return valor;
	}

	public static CidadeVO getCidade(String nomeCidade, String uf, GenericDAO dao) throws SQLException {
		CidadeVO cidade = new CidadeVO();
		cidade.setNomeCidade(nomeCidade);
		cidade.setSiglaUFCidade(uf);

		List<IValueObject> list = dao.getList(cidade);
		if (list.size() > 0) {
			return (CidadeVO) list.get(0);
		}

		return null;
	}

	public LojaVO getLoja(int codigoVelho, GenericDAO dao) throws SQLException {
		LojaVO loja = new LojaVO();
		if (codigoVelho == 1) {
			// GOIABEIRAS
			loja.setCodigoEmpresa(1);
			loja.setCodigoLoja(1);
		} else if (codigoVelho == 2) {
			// PANTANAL LOJA
			loja.setCodigoEmpresa(1);
			loja.setCodigoLoja(2);
		} else if (codigoVelho == 3) {
			// 3A - PRA�A
			loja.setCodigoEmpresa(1);
			loja.setCodigoLoja(3);
		} else if (codigoVelho == 4) {
			// 3A - LJ - 0002 RAFAELI
			loja.setCodigoEmpresa(2);
			loja.setCodigoLoja(2);
		} else if (codigoVelho == 5) {
			// PANTANAL QSQ - 0001 RAFAELI
			loja.setCodigoEmpresa(2);
			loja.setCodigoLoja(1);
		} else if (codigoVelho == 6) {
			// S I N O P
			loja.setCodigoEmpresa(2);
			loja.setCodigoLoja(3);
		} else if (codigoVelho == 7) {
			// RONDON PLAZA
			loja.setCodigoEmpresa(1);
			loja.setCodigoLoja(4);
		} else if (codigoVelho == 200 || codigoVelho == 0) {
			// ADM / FINANCEIRO ??? Ainda nao sei...
			loja.setCodigoEmpresa(99);
			loja.setCodigoLoja(1);
		}

		loja = (LojaVO) dao.get(loja);

		return loja;
	}

	public FormaPagamentoVO getForma(int codigoFormaPagamento, GenericDAO dao) throws SQLException {
		FormaPagamentoVO forma = new FormaPagamentoVO();

		if (Arrays.binarySearch(new int[] { 1, 2 }, codigoFormaPagamento) >= 0) {
			// Dinheiro
			forma.setCodigoFormaPagamento(1);
		} else if (Arrays.binarySearch(new int[] { 9 }, codigoFormaPagamento) >= 0) {
			// Cheque
			forma.setCodigoFormaPagamento(2);
		} else if (Arrays.binarySearch(new int[] { 3, 4, 5, 6, 7, 8, 10, 11, 12, 13 }, codigoFormaPagamento) >= 0) {
			// Debito em conta
			forma.setCodigoFormaPagamento(3);
		} else {
			// Foda-se, vai ser dinheiro mesmo.
			forma.setCodigoFormaPagamento(1);
		}
		return (FormaPagamentoVO) dao.get(forma);
	}

	public PessoaVO getPessoa(int codigoPessoa, GenericDAO dao) throws SQLException {
		PessoaVO pessoa = new PessoaVO();
		pessoa.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
		pessoa.setCodigoLoja(CODIGO_LOJA_PADRAO);
		pessoa.setCodigoPessoa(codigoPessoa);

		pessoa = (PessoaVO) dao.get(pessoa);

		return pessoa;
	}

	public ContaPagarVO getConta(int codigoConta, GenericDAO dao) throws SQLException {
		ContaPagarVO conta = new ContaPagarVO();
		conta.setCodigoContaPagar(codigoConta);

		conta = (ContaPagarVO) dao.get(conta);

		return conta;
	}

	public Date getDate(String dataString) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");

		try {
			return new Date(sdf.parse(dataString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BigDecimal getNumber(String numberString, int precisao) {
		String mascara = "#,";
		if (precisao > 0) {
			for (int i = 0; i < precisao; i++) {
				mascara += "0";
			}
		} else {
			mascara = "#";
		}
		DecimalFormat formater = new DecimalFormat(mascara);

		try {
			return new BigDecimal(formater.parse(numberString).doubleValue()).setScale(precisao, BigDecimal.ROUND_CEILING);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public BigDecimal getNumber2(String numberString, int precisao) {
		String mascara = "#,";
		if (precisao > 0) {
			for (int i = 0; i < precisao; i++) {
				mascara += "0";
			}
		} else {
			mascara = "#";
		}

		DecimalFormat formater = new DecimalFormat(mascara);
		formater.setCurrency(Currency.getInstance(new Locale("pt", "BR")));
		formater.setParseBigDecimal(true);
		BigDecimal valor = null;
		try {
			valor = (BigDecimal) formater.parse(numberString.replace("R$", "").trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return valor;
	}

	public void importaTabelaPrecos() throws IOException, SQLException {

		FileInputStream in = new FileInputStream(FOLDER + "precos.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;

		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			try {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoReferenciaProduto(campos[map.get("codigo")]);
				List<IValueObject> listProd = dao.getList(produto);

				if (listProd.size() > 0) {
					produto = (ProdutoVO) listProd.get(0);
				} else {
					produto = null;
				}

				if (produto != null) {

					TabelaPrecoProdutoVO tabProd = new TabelaPrecoProdutoVO();
					tabProd.setNewRecord(false);
					tabProd.setCodigoEmpresa(3);
					tabProd.setCodigoLoja(2);
					tabProd.setCodigoTabelaPreco(1);
					tabProd.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
					tabProd.setCodigoLojaProduto(produto.getCodigoLoja());
					tabProd.setCodigoProduto(produto.getCodigoProduto());
					tabProd = (TabelaPrecoProdutoVO) dao.get(tabProd);

					if (tabProd != null) {
						tabProd.setPrecoVenda(getNumber(campos[map.get("preco")], 4));
						dao.persist(tabProd);
					} else {
						tabProd = new TabelaPrecoProdutoVO();
						tabProd.setNewRecord(true);
						tabProd.setCodigoEmpresa(3);
						tabProd.setCodigoLoja(2);
						tabProd.setCodigoTabelaPreco(1);
						tabProd.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
						tabProd.setCodigoLojaProduto(produto.getCodigoLoja());
						tabProd.setCodigoProduto(produto.getCodigoProduto());
						tabProd.setPrecoVenda(getNumber(campos[map.get("preco")], 4));
						dao.persist(tabProd);
					}

					tabProd = new TabelaPrecoProdutoVO();
					tabProd.setNewRecord(false);
					tabProd.setCodigoEmpresa(3);
					tabProd.setCodigoLoja(1);
					tabProd.setCodigoTabelaPreco(1);
					tabProd.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
					tabProd.setCodigoLojaProduto(produto.getCodigoLoja());
					tabProd.setCodigoProduto(produto.getCodigoProduto());
					tabProd = (TabelaPrecoProdutoVO) dao.get(tabProd);

					if (tabProd != null) {
						tabProd.setPrecoVenda(getNumber(campos[map.get("preco")], 4));
						dao.persist(tabProd);
					} else {
						tabProd = new TabelaPrecoProdutoVO();
						tabProd.setNewRecord(true);
						tabProd.setCodigoEmpresa(3);
						tabProd.setCodigoLoja(1);
						tabProd.setCodigoTabelaPreco(1);
						tabProd.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
						tabProd.setCodigoLojaProduto(produto.getCodigoLoja());
						tabProd.setCodigoProduto(produto.getCodigoProduto());
						tabProd.setPrecoVenda(getNumber(campos[map.get("preco")], 4));
						dao.persist(tabProd);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// continue;
				break;
			}
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void geraLoja() throws SQLException {
		EmpresaVO empresa99 = new EmpresaVO();
		empresa99.setNewRecord(true);
		empresa99.setCodigoEmpresa(99);
		empresa99.setRazaoSocialEmpresa("ADMINISTRATIVO");
		empresa99.setFantasiaEmpresa("ADMINISTRATIVO");

		LojaVO loja9901 = new LojaVO();
		loja9901.setNewRecord(true);
		loja9901.setCodigoEmpresa(99);
		loja9901.setCodigoLoja(1);
		loja9901.setCNPJLoja("99999999999999");
		loja9901.setCodigoCidadeLoja(4347);
		loja9901.setFlagMatrizLoja(false);
		loja9901.setNomeLoja("ADMINISTRATIVO");
		loja9901.setInscricaoEstadualLoja(".");
		loja9901.setEnderecoLoja(".");
		loja9901.setBairroLoja(".");
		loja9901.setCepLoja("7800000");
		loja9901.setIssLoja(new BigDecimal(5));

		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);
		dao.persist(empresa99);
		dao.persist(loja9901);
		dao.commit();
	}

	public PlanoContaVO getPlanoConta(String numeroPlano, GenericDAO dao) throws SQLException {
		PlanoContaVO plano = new PlanoContaVO();
		plano.setNumeroPlanoConta(numeroPlano);
		List<IValueObject> list = dao.getList(plano);

		if (list.size() > 0) {
			return (PlanoContaVO) list.get(0);
		}

		return null;
	}

	public void importaVendasChili() throws IOException, SQLException {

		FileInputStream in = new FileInputStream(FOLDER + "vendas_chili.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			try {
				VendaDiariaVO venda = new VendaDiariaVO();

				venda.setCodigoEmpresa(Integer.parseInt(campos[map.get("Empresa")]));
				venda.setCodigoLoja(Integer.parseInt(campos[map.get("Loja")]));
				venda.setDataVenda(getDate(campos[map.get("Data")]));

				venda = (VendaDiariaVO) dao.get(venda);

				if (venda == null) {
					venda = new VendaDiariaVO();
					venda.setNewRecord(true);

					venda.setCodigoEmpresa(Integer.parseInt(campos[map.get("Empresa")]));
					venda.setCodigoLoja(Integer.parseInt(campos[map.get("Loja")]));
					venda.setDataVenda(getDate(campos[map.get("Data")]));
				}
				venda.setValorVenda(getNumber2(campos[map.get("Valor")], 2));

				dao.persist(venda);

			} catch (SQLException e) {
				e.printStackTrace();
				writeErrorMessage(e.getMessage());
			}
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void importaFornecChili() throws IOException, SQLException {
		FileInputStream in = new FileInputStream(FOLDER + "fornecedor.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			PessoaVO pessoa = new PessoaVO();
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();

			pessoa.setNewRecord(true);
			endereco.setNewRecord(true);

			String cpfCnpj = campos[map.get("CGC_CPF")];
			cpfCnpj = cpfCnpj == null ? "" : Utils.filtro(cpfCnpj, "1234567890");

			pessoa.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			pessoa.setCodigoLoja(CODIGO_LOJA_PADRAO);
			pessoa.setCodigoPessoa(1000 + Integer.parseInt(getCampo(map, campos, "CODIGO", linha)));
			pessoa.setNomeRazaoSocialPessoa(getCampo(map, campos, "NOME", linha));
			pessoa.setNaturezaPessoa(cpfCnpj.length() == 11 ? "F" : "J");
			pessoa.setEmailPessoa(getCampo(map, campos, "E_MAIL", linha));
			pessoa.setObservacoesPessoa(getCampo(map, campos, "HOME_PAGE", linha));

			dao.persist(pessoa);

			endereco.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			endereco.setCodigoLoja(CODIGO_LOJA_PADRAO);
			endereco.setCodigoPessoa(pessoa.getCodigoPessoa());
			endereco.setCodigoEnderecoPessoa(pessoa.getCodigoPessoa());

			endereco.setEmailEnderecoPessoa(getCampo(map, campos, "E_MAIL", linha));
			endereco.setLogradouroEnderecoPessoa(getCampo(map, campos, "ENDERECO", linha));
			endereco.setBairroEnderecoPessoa(getCampo(map, campos, "BAIRRO", linha));
			endereco.setCepEnderecoPessoa(Utils.filtro(getCampo(map, campos, "CEP", linha), "1234567890"));
			endereco.setFone1EnderecoPessoa(Utils.filtro(getCampo(map, campos, "FONE", linha), "1234567890"));
			endereco.setTipoEnderecoPessoa("3"); // Ambos (Cobranca e Entrega)
			endereco.setCpfCnpjEnderecoPessoa(cpfCnpj);
			endereco.setFantasiaApelidoEnderecoPessoa(pessoa.getNomeRazaoSocialPessoa());
			endereco.setInscricaoEstadualEnderecoPessoa(getCampo(map, campos, "IE_RG", linha));

			// CIDADE
			String nomeCidade = Utils.RemoverAcentos.remover(getCampo(map, campos, "CIDADE", linha));
			String uf = getCampo(map, campos, "UF", linha);
			CidadeVO cidade = getCidade(nomeCidade, uf, dao);

			if (cidade != null) {
				endereco.setCodigoCidadeEnderecoPessoa(cidade.getCodigoCidade());
			} else {
				// Tenta buscar de novo, somente pelo nome da cidade
				cidade = getCidade(nomeCidade, null, dao);
				if (cidade != null) {
					endereco.setCodigoCidadeEnderecoPessoa(cidade.getCodigoCidade());
				}
			}

			dao.persist(endereco);
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void importaPlanoContasChili() throws IOException, SQLException {

		FileInputStream in = new FileInputStream(FOLDER + "contas.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");
			String contaNova;
			String contaVelha;
			String nomeConta;
			Integer codigoPlanoConta;
			try {
				contaNova = campos[map.get("NovaConta")];
				contaVelha = campos[map.get("CODIGO")];
				nomeConta = campos[map.get("NOME")];
				codigoPlanoConta = Integer.parseInt(contaVelha);
			} catch (Exception e) {
				continue;
			}

			PlanoContaVO plano = getPlanoConta(contaNova, dao);
			if (plano == null) {
				plano = new PlanoContaVO();
				plano.setNewRecord(true);

				plano.setCodigoPlanoConta(codigoPlanoConta);
				plano.setNumeroPlanoConta(contaNova);
				plano.setNomePlanoConta(nomeConta);
				plano.setCodigoTipoPlanoConta(1);

				dao.persist(plano);
			}
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public static Map<String, Integer> loadItens() throws IOException {
		FileInputStream in = new FileInputStream(FOLDER + "entrada_itens.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;

		Map<String, Integer> map = null;
		Map<String, Integer> itens = new HashMap<String, Integer>();

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			itens.put(getCampo(map, campos, "ENT_COD", linha), Integer.parseInt(getCampo(map, campos, "CONTA", linha)));

		}
		
		reader.close();
		return itens;
	}

	public static Map<String, String> loadPlanoContas() throws IOException {
		FileInputStream in = new FileInputStream(FOLDER + "contas.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;

		Map<String, Integer> map = null;
		Map<String, String> contas = new HashMap<String, String>();

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			try {
				contas.put(campos[map.get("CODIGO")], campos[map.get("NovaConta")]);
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}

		}
		
		reader.close();
		return contas;
	}

	public void importaContaPagarXPlanoContaChili() throws IOException, SQLException {
		// Carrega itens num vetor
		Map<String, Integer> itens = loadItens();
		Map<String, String> contas = loadPlanoContas();

		FileInputStream in = new FileInputStream(FOLDER + "entrada.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			Integer codigoContaPagar = null;
			codigoContaPagar = Integer.parseInt(getCampo(map, campos, "REC1", linha));

			ContaPagarVO conta = getConta(codigoContaPagar, dao);

			if (conta != null) {
				Integer codigoPlanoConta = itens.get(getCampo(map, campos, "CODIGO", linha));

				PlanoContaVO plano = getPlanoConta(contas.get(codigoPlanoConta.toString()), dao);

				if (plano != null) {
					conta.setCodigoPlanoConta(plano.getCodigoPlanoConta());
					dao.persist(conta);
				} else {
					write("Plano " + codigoPlanoConta + " não encontrado!\n");
				}
			}
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void importaBaixaContaPagarChili() throws IOException, SQLException {
		FileInputStream in = new FileInputStream(FOLDER + "baixapagar.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			// Busca conta a pagar
			ContaPagarVO conta = getConta(Integer.parseInt(getCampo(map, campos, "PAGAR", linha)), dao);
			FormaPagamentoVO forma = getForma(Integer.parseInt(getCampo(map, campos, "AGE_CODIGO", linha)), dao);
			PagamentoVO pagamento = new PagamentoVO();
			pagamento.setNewRecord(true);
			pagamento.setCodigoFormaPagamento(forma.getCodigoFormaPagamento());

			pagamento.setCodigoEmpresa(conta.getCodigoEmpresa());
			pagamento.setCodigoLoja(conta.getCodigoLoja());
			pagamento.setDataHoraBaixaPagamento(new Timestamp(getDate(getCampo(map, campos, "DATA", linha)).getTime()));
			pagamento.setValorPagamento(getNumber(getCampo(map, campos, "VALOR", linha), 2));
			pagamento.setObservacaoPagamento("IMPORTADO DO SISTEMA PROTHEUS");

			dao.persist(pagamento);

			BaixaContaPagarVO baixa = new BaixaContaPagarVO();
			baixa.setNewRecord(true);

			baixa.setCodigoEmpresa(pagamento.getCodigoEmpresa());
			baixa.setCodigoLoja(pagamento.getCodigoLoja());
			baixa.setCodigoPagamento(pagamento.getCodigoPagamento());

			baixa.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
			baixa.setCodigoLojaContaPagar(conta.getCodigoLoja());
			baixa.setCodigoContaPagar(conta.getCodigoContaPagar());

			baixa.setValorBaixaContaPagar(pagamento.getValorPagamento());

			dao.persist(baixa);
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void baixaContasAntigasChili() throws SQLException {
		ContaPagarDAO contaDao = new ContaPagarDAO();

		String sb = "";
		sb += " DataVencimentoContaPagar <= '2012-05-01' ";
		sb += " AND SituacaoContaPagar IN ('A')";

		contaDao.setSpecialCondition(sb);

		List<ContaPagarVO> contas = contaDao.getList(null, " DataVencimentoContaPagar ");

		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);

		for (ContaPagarVO conta : contas) {
			PagamentoVO pagamento = new PagamentoVO();
			pagamento.setNewRecord(true);
			pagamento.setCodigoEmpresa(conta.getCodigoEmpresa());
			pagamento.setCodigoLoja(conta.getCodigoEmpresa());
			pagamento.setDataHoraBaixaPagamento(Utils.getNow());
			pagamento.setValorPagamento(conta.getValorContaPagar());
			pagamento.setCodigoFormaPagamento(1);

			dao.persist(pagamento);

			BaixaContaPagarVO baixa = new BaixaContaPagarVO();
			baixa.setNewRecord(true);

			baixa.setCodigoEmpresa(pagamento.getCodigoEmpresa());
			baixa.setCodigoLoja(pagamento.getCodigoLoja());
			baixa.setCodigoPagamento(pagamento.getCodigoPagamento());

			baixa.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
			baixa.setCodigoLojaContaPagar(conta.getCodigoLoja());
			baixa.setCodigoContaPagar(conta.getCodigoContaPagar());

			baixa.setValorBaixaContaPagar(conta.getValorContaPagar());

			dao.persist(baixa);

			conta.setSituacaoContaPagar("Q");

			dao.persist(conta);
		}
		dao.commit();
		
	}

	public void importaContaPagarChili() throws IOException, SQLException {

		FileInputStream in = new FileInputStream(FOLDER + "pagar.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;
			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			ContaPagarVO conta = new ContaPagarVO();
			conta.setNewRecord(true);

			LojaVO loja = getLoja(Integer.parseInt(getCampo(map, campos, "EMP_COD", linha)), dao);
			PessoaVO pessoa = getPessoa(1000 + Integer.parseInt(getCampo(map, campos, "FOR_COD", linha)), dao);

			conta.setCodigoEmpresa(loja.getCodigoEmpresa());
			conta.setCodigoLoja(loja.getCodigoLoja());
			conta.setCodigoContaPagar(Integer.parseInt(getCampo(map, campos, "CODIGO", linha)));
			conta.setDescricaoContaPagar(getCampo(map, campos, "DOCTO1", linha));

			conta.setCodigoEmpresaPessoaContaPagar(pessoa.getCodigoEmpresa());
			conta.setCodigoLojaPessoaContaPagar(pessoa.getCodigoLoja());
			conta.setCodigoPessoaContaPagar(pessoa.getCodigoPessoa());

			// AINDA VEREMOS COMO PARAMETRIZAR...
			conta.setCodigoTipoDocumento(3); // TODO Todo mundo e duplicata
			conta.setCodigoCentroCusto(1); // TODO Centro de custo 1 para todos
			conta.setCodigoPlanoConta(1); // TODO Falta plano de contas
			conta.setCodigoPortador(1); // CARTEIRA

			conta.setNumeroTituloContaPagar(getCampo(map, campos, "DOCTO2", linha));
			conta.setNumeroParcelasContaPagar(getNumber(getCampo(map, campos, "PARCELATOTAL", linha), 0).intValue());
			conta.setParcelaContaPagar(getNumber(getCampo(map, campos, "PARCELAATUAL", linha), 0).intValue());

			conta.setDataEmissaoContaPagar(getDate(getCampo(map, campos, "DTEMISSAO", linha)));
			conta.setDataVencimentoContaPagar(getDate(getCampo(map, campos, "DTVECTO", linha)));
			conta.setDataLancamentoContaPagar(getDate(getCampo(map, campos, "DTLACTO", linha)));

			conta.setValorContaPagar(getNumber(getCampo(map, campos, "VALOR", linha), 2)); // campos[map.get("VALOR")],
																							// 2));

			conta.setObservacaoContaPagar(getCampo(map, campos, "OBS", linha));
			conta.setFlagDREContaPagar("S");

			// Pagamentos
			BigDecimal valorPago = getNumber(getCampo(map, campos, "VALORPAGO", linha), 2);
			if (conta.getValorContaPagar().compareTo(valorPago) <= 0) {
				conta.setSituacaoContaPagar("Q"); // Quitado
			} else {
				conta.setSituacaoContaPagar("A"); // Aberto
			}

			dao.persist(conta);
		}

		write("Linhas importadas: " + linhas + "\n");
		dao.commit();
		
		reader.close();
	}

	public void importaClienteChili() throws IOException, SQLException {
		FileInputStream in = new FileInputStream(FOLDER + "cliente.csv");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		int linhas = 0;
		GenericDAO dao = new GenericDAO();

		dao.setAutoCommit(false);

		Map<String, Integer> map = null;

		while ((linha = reader.readLine()) != null) {
			linhas++;
			System.out.println(linha);

			if (linhas == 1) {
				map = buscaCampos(linha);
				continue;
			}

			String[] campos = linha.split(";");

			PessoaVO pessoa = new PessoaVO();
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();

			pessoa.setNewRecord(true);
			endereco.setNewRecord(true);

			String cpfCnpj = campos[map.get("CGC_CPF")];
			cpfCnpj = cpfCnpj == null ? "" : Utils.filtro(cpfCnpj, "1234567890");

			pessoa.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			pessoa.setCodigoLoja(CODIGO_LOJA_PADRAO);
			pessoa.setCodigoPessoa(1000 + Integer.parseInt(getCampo(map, campos, "CODIGO", linha)));
			pessoa.setNomeRazaoSocialPessoa(getCampo(map, campos, "NOME", linha));
			pessoa.setNaturezaPessoa(cpfCnpj.length() == 11 ? "F" : "J");
			pessoa.setEmailPessoa(getCampo(map, campos, "E_MAIL", linha));
			pessoa.setObservacoesPessoa(getCampo(map, campos, "HOME_PAGE", linha));

			dao.persist(pessoa);

			endereco.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			endereco.setCodigoLoja(CODIGO_LOJA_PADRAO);
			endereco.setCodigoPessoa(pessoa.getCodigoPessoa());
			endereco.setCodigoEnderecoPessoa(pessoa.getCodigoPessoa());

			endereco.setEmailEnderecoPessoa(getCampo(map, campos, "E_MAIL", linha));
			endereco.setLogradouroEnderecoPessoa(getCampo(map, campos, "ENDERECO", linha));
			endereco.setBairroEnderecoPessoa(getCampo(map, campos, "BAIRRO", linha));
			endereco.setCepEnderecoPessoa(Utils.filtro(getCampo(map, campos, "CEP", linha), "1234567890"));
			endereco.setFone1EnderecoPessoa(Utils.filtro(getCampo(map, campos, "FONE", linha), "1234567890"));
			endereco.setTipoEnderecoPessoa("3"); // Ambos (Cobranca e Entrega)
			endereco.setCpfCnpjEnderecoPessoa(cpfCnpj);
			endereco.setFantasiaApelidoEnderecoPessoa(pessoa.getNomeRazaoSocialPessoa());
			endereco.setInscricaoEstadualEnderecoPessoa(getCampo(map, campos, "IE_RG", linha));

			// CIDADE
			String nomeCidade = Utils.RemoverAcentos.remover(getCampo(map, campos, "CIDADE", linha));
			String uf = getCampo(map, campos, "UF", linha);
			CidadeVO cidade = getCidade(nomeCidade, uf, dao);

			if (cidade != null) {
				endereco.setCodigoCidadeEnderecoPessoa(cidade.getCodigoCidade());
			} else {
				// Tenta buscar de novo, somente pelo nome da cidade
				cidade = getCidade(nomeCidade, null, dao);
				if (cidade != null) {
					endereco.setCodigoCidadeEnderecoPessoa(cidade.getCodigoCidade());
				}
			}

			dao.persist(endereco);
		}

		write("Linhas importadas: " + linhas);
		dao.commit();
		
		reader.close();
	}

	public void importaBancos() throws IOException, SQLException {
		FileInputStream in = new FileInputStream("c:\\temp\\bancos.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		int linhas = 0;
		BancoDAO dao = new BancoDAO();
		while ((line = reader.readLine()) != null) {
			BancoVO vo = new BancoVO();
			vo.setNewRecord(true);
			vo.setCodigoBanco(line.substring(0, 3).trim());
			vo.setNomeBanco(line.substring(4).trim());

			dao.persist(vo);
			linhas++;
		}

		write("Linhas importadas: " + linhas);
		reader.close();
	}

	public void importaFuncionarios() throws SQLException, ClassNotFoundException {
		RowSet row = new MySqlDAO("madeireirasaojose").getRowSet("SELECT * FROM func WHERE Situacao = 'Ativo' ");

		CargoDAO cargoDao = new CargoDAO();

		while (row.next()) {
			// CARGOS

			// Verifica se o cargo j� existe
			CargoVO cargoFilter = new CargoVO();
			cargoFilter.setNomeCargo(row.getString("Cargo").toUpperCase());
			List<CargoVO> listCargo = cargoDao.getList(cargoFilter);
			CargoVO cargo = null;

			if (listCargo.size() == 0) {
				// Se nao existe, insere
				cargo = new CargoVO();
				cargo.setNewRecord(true);
				cargo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
				cargo.setCodigoLoja(CODIGO_LOJA_PADRAO);
				cargo.setNomeCargo(row.getString("Cargo").toUpperCase());
				cargo.setStatusCargo("A");
				cargoDao.persist(cargo);
			} else {
				cargo = listCargo.get(0);
			}
			// FIM CARGOS

			// FUNCIONARIOS
			FuncionarioDAO funcDao = new FuncionarioDAO();
			FuncionarioVO funcionarioFilter = new FuncionarioVO();
			funcionarioFilter.setNomeFuncionario(row.getString("Nome"));

			FuncionarioVO funcionario = null;

			List<FuncionarioVO> listFunc = funcDao.getList(funcionarioFilter);

			if (listFunc.size() > 0) {
				funcionario = listFunc.get(0);
			}

			if (funcionario == null && row.getString("Nome").indexOf("ATIVO") < 0) {
				funcionario = new FuncionarioVO();
				funcionario.setNewRecord(true);
				funcionario.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
				funcionario.setCodigoLoja(CODIGO_LOJA_PADRAO);
				funcionario.setCodigoFuncionario(row.getInt("CodFunc"));

				funcionario.setNomeFuncionario(row.getString("Nome"));
				funcionario.setSexoFuncionario(row.getString("Sexo").substring(0, 1));
				funcionario.setEnderecoFuncionario(row.getString("Endereco"));
				funcionario.setFone1Funcionario(somenteNumeros(row.getString("Fone")));
				funcionario.setFone2Funcionario(somenteNumeros(row.getString("Celular")));
				funcionario.setBairroFuncionario(row.getString("Bairro"));
				try {
					funcionario.setCepFuncionario(somenteNumeros(row.getString("Cep").substring(0, 6)));
				} catch (Exception ex) {

				}
				funcionario.setCpfFuncionario(somenteNumeros(row.getString("Cpf")));
				funcionario.setRgFuncionario(row.getString("Ident"));
				funcionario.setDataEmissaoRgFuncionario(row.getDate("DataEmisIdent"));
				String orgaoExp = row.getString("OrgaoExped");

				if (orgaoExp != null) {
					try {
						funcionario.setOrgaoEmissaoRgFuncionario(orgaoExp.split("/")[0]);
						funcionario.setUFEmissaoRgFuncionario(orgaoExp.split("/")[1].substring(0, 2));
					} catch (Exception ex) {

					}
				}

				funcionario.setCtpsFuncionario(row.getString("Ctps"));
				funcionario.setPisFuncionario(row.getString("Pis"));
				funcionario.setTituloEleitorFuncionario(row.getString("Titulo"));
				// funcionario.setSecaoTituloEleitorFuncionario(row.getString("Serie"));
				funcionario.setZonaTituloEleitorFuncionario(row.getString("Zona"));

				// Busca cidade natural
				String nomeCidade = row.getString("Naturalid");
				if (nomeCidade != null) {
					String nomeUf = nomeCidade.substring(nomeCidade.length() - 2);
					nomeCidade = nomeCidade.substring(0, nomeCidade.length() - 3);

					CidadeVO cidadeFilter = new CidadeVO();
					CidadeDAO cidadeDao = new CidadeDAO();
					CidadeVO cidade = null;
					cidadeFilter.setNomeCidade(nomeCidade);
					cidadeFilter.setSiglaUFCidade(nomeUf);

					List<CidadeVO> listCidades = cidadeDao.getList(cidadeFilter);

					if (listCidades.size() > 0) {
						cidade = listCidades.get(0);
					}

					if (cidade != null) {
						funcionario.setNaturalidadeFuncionario(cidade.getCodigoCidade());
					}
				}
				funcionario.setDataNascimentoFuncionario(row.getDate("Nasc"));

				String estadoCivil = row.getString("EstCiv");
				int codEstadoCivil = 1;
				if (estadoCivil != null) {
					if (estadoCivil.equalsIgnoreCase("solteiro")) {
						codEstadoCivil = 1;
					} else if (estadoCivil.equalsIgnoreCase("casado") || estadoCivil.equalsIgnoreCase("casada")) {
						codEstadoCivil = 2;
					} else if (estadoCivil.equalsIgnoreCase("separado")) {
						codEstadoCivil = 3;
					} else {
						codEstadoCivil = 99;
					}
				}
				funcionario.setEstadoCivilFuncionario(codEstadoCivil);
				funcionario.setNomePaiFuncionario(row.getString("Pai"));
				funcionario.setNomeMaeFuncionario(row.getString("Mae"));
				funcionario.setNomeConjugueFuncionario(row.getString("Conjuge"));
				funcionario.setObservacoesFuncionario(row.getString("Obs"));

				funcDao.persist(funcionario);

				// FIM FUNCIONARIOS

				// CONTRATOS
				RowSet rowCont = new MySqlDAO("madeireirasaojose").getRowSet("SELECT * FROM contratostrab WHERE CodFunc = " + row.getInt("CodFunc") + " AND DataRescisao IS NULL");

				if (rowCont.next()) {
					ContratoTrabalhoDAO contratoDao = new ContratoTrabalhoDAO();
					// Faz o codigo do contrato como o mesmo codigo do
					// funcionario
					ContratoTrabalhoVO contrato = contratoDao.get(new Object[] { CODIGO_EMPRESA_PADRAO, CODIGO_LOJA_PADRAO, row.getInt("CodFunc") });

					if (contrato == null) {
						contrato = new ContratoTrabalhoVO();
						contrato.setNewRecord(true);
						contrato.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
						contrato.setCodigoLoja(CODIGO_LOJA_PADRAO);
						contrato.setCodigoContratoTrabalho(row.getInt("CodFunc"));

						contrato.setDataInicioContratoTrabalho(rowCont.getDate("DataAdmissao"));
						contrato.setDataFimContratoTrabalho(rowCont.getDate("DataRescisao"));
						contrato.setDiasExperiencia1ContratoTrabalho(rowCont.getInt("DiasExper"));
						contrato.setDiasExperiencia2ContratoTrabalho(rowCont.getInt("ProrrogExp"));

						contrato.setCodigoEmpresaCargo(cargo.getCodigoEmpresa());
						contrato.setCodigoLojaCargo(cargo.getCodigoLoja());
						contrato.setCodigoCargo(cargo.getCodigoCargo());

						contrato.setCodigoEmpresaFuncionario(funcionario.getCodigoEmpresa());
						contrato.setCodigoLojaFuncionario(funcionario.getCodigoLoja());
						contrato.setCodigoFuncionario(funcionario.getCodigoFuncionario());

						contrato.setCodigoHorarioTrabalho(1);
						contrato.setCodigoLojaHorarioTrabalho(CODIGO_LOJA_PADRAO);
						contrato.setCodigoEmpresaHorarioTrabalho(CODIGO_EMPRESA_PADRAO);

						contrato.setStatusContratoTrabalho(ContratoTrabalhoVO.STATUS_ATIVO);

						contratoDao.persist(contrato);

						// FIM CONTRATO

						// VERBA (SALARIO BASE)

						VerbaContratoTrabalhoVO verbaContrato = new VerbaContratoTrabalhoVO();
						VerbaContratoTrabalhoDAO verbaContDao = new VerbaContratoTrabalhoDAO();
						VerbaVO verbaSalbase = getVerbaSalarioBase();

						verbaContrato.setNewRecord(true);
						verbaContrato.setCodigoEmpresa(contrato.getCodigoEmpresa());
						verbaContrato.setCodigoLoja(contrato.getCodigoLoja());
						verbaContrato.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());

						verbaContrato.setCodigoEmpresaVerba(verbaSalbase.getCodigoEmpresa());
						verbaContrato.setCodigoLojaVerba(verbaSalbase.getCodigoLoja());
						verbaContrato.setCodigoVerba(verbaSalbase.getCodigoVerba());

						verbaContrato.setValorVerba(row.getBigDecimal("Salario"));

						verbaContDao.persist(verbaContrato);
						// FIM VERBA (SALARIO BASE)
					}
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public void cbo() throws SQLException, ClassNotFoundException, IOException {
		FileInputStream in = new FileInputStream("c:\\cbo.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";

		int linhas = 0;

		CboDAO dao = new CboDAO();

		while ((line = reader.readLine()) != null) {
			CboVO vo = new CboVO();
			vo.setNewRecord(true);
			vo.setCodigoCbo(line.substring(0, 6).trim());
			vo.setDescricaoCbo(line.substring(7).trim());
			dao.persist(vo);
			linhas++;
		}
		
		reader.close();
	}

	@SuppressWarnings("unused")
	public void importaToras(String arquivo) throws SQLException, ClassNotFoundException, IOException {
		FileInputStream in = new FileInputStream(FOLDER + arquivo);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		int linhas = 0;

		RomaneioToraVO romaneio = new RomaneioToraVO();
		romaneio.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
		romaneio.setCodigoLoja(CODIGO_LOJA_PADRAO);
		romaneio.setDataHoraLancamentoRomaneio(Utils.getNow());
		romaneio.setNumeroRomaneio(9999);
		romaneio.setObservacaoRomaneio("ROMANEIO DE IMPORTACAO DO SISTEMA ANTIGO");
		romaneio.setNewRecord(true);
		romaneio.setCodigoPessoaFornecedor(97);
		romaneio.setCodigoEmpresaFornecedor(1);
		romaneio.setCodigoLojaFornecedor(1);

		RomaneioToraDAO dao = new RomaneioToraDAO();
		dao.persist(romaneio);

		ItemRomaneioToraDAO itemdao = new ItemRomaneioToraDAO();
		EssenciaDAO essenciaDao = new EssenciaDAO();

		while ((line = reader.readLine()) != null) {
			int plaqueta = 0;
			try {
				plaqueta = Integer.parseInt(line.substring(0, 8).trim());
			} catch (Exception e) {
				plaqueta = 0;
			}

			if (plaqueta > 0) {

				// Busca essencia
				EssenciaVO essencia = new EssenciaVO();
				essencia.setNomeEssencia(line.substring(11, 27).trim());
				if (essencia.getNomeEssencia().equalsIgnoreCase("ANGELIM")) {
					essencia.setNomeEssencia("ANGELIM PEDRA");
				} else {
					essencia.setNomeEssencia(essencia.getNomeEssencia() + "%");
				}

				List<EssenciaVO> list = essenciaDao.getList(essencia);
				essencia = null;
				for (EssenciaVO item : list) {
					essencia = item;
				}

				if (essencia == null) {
					System.out.println("Essencia [" + line.substring(11, 27) + "] nao cadastrada!");
				} else {
					ItemRomaneioToraVO vo = new ItemRomaneioToraVO();
					vo.setNewRecord(true);

					vo.setCodigoLoja(romaneio.getCodigoLoja());
					vo.setCodigoEmpresa(romaneio.getCodigoEmpresa());
					vo.setCodigoRomaneio(romaneio.getCodigoRomaneio());
					vo.setCodigoEssencia(essencia.getCodigoEssencia());
					vo.setNumeroPlaquetaRomaneio(plaqueta);
					vo.setComprimentoTora(new BigDecimal(Double.parseDouble(line.substring(41, 49).trim().replace(",", "."))));
					vo.setDiametroTora(new BigDecimal(Double.parseDouble(line.substring(50, 60).trim().replace(",", "."))));
					vo.setFlagBaixaTora("N");

					BigDecimal volume = vo.getDiametroTora().multiply(vo.getDiametroTora()).multiply(vo.getComprimentoTora()).multiply(ItemRomaneioToraVO.CONSTANTE_VOLUME_TORA).setScale(3, BigDecimal.ROUND_HALF_DOWN);
					vo.setVolumeTora(volume);

					itemdao.persist(vo);
				}
			}
			linhas++;
		}
		
		reader.close();
	}

	public void importaFornecedor() throws SQLException, ClassNotFoundException {
		RowSet row = new MySqlDAO("madeireirasaojoseconsulta").getRowSet("SELECT * FROM fornec");
		PessoaDAO dao = new PessoaDAO();
		EnderecoPessoaDAO endDao = new EnderecoPessoaDAO();
		CidadeDAO cidDao = new CidadeDAO();
		row.beforeFirst();
		while (row.next()) {
			// Pessoa
			PessoaVO vo = new PessoaVO();
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoPessoa(row.getInt("CodFornec"));
			if (dao.get(vo) == null) {
				vo.setNomeRazaoSocialPessoa(row.getString("Razao"));
				vo.setNaturezaPessoa(row.getString("Tipo").substring(0, 1));
				dao.persist(vo);
			}
			// Endereco
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();
			endereco.setNewRecord(true);
			endereco.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			endereco.setCodigoLoja(CODIGO_LOJA_PADRAO);
			endereco.setCodigoPessoa(row.getInt("CodFornec"));
			endereco.setBairroEnderecoPessoa(row.getString("Bairro"));
			if (endereco.getBairroEnderecoPessoa() == null) {
				endereco.setBairroEnderecoPessoa("<NAO CADASTRADO>");
			}
			endereco.setLogradouroEnderecoPessoa(row.getString("Endereco"));
			if (endereco.getLogradouroEnderecoPessoa() == null) {
				endereco.setLogradouroEnderecoPessoa("<NAO CADASTRADO>");
			}
			endereco.setFantasiaApelidoEnderecoPessoa(row.getString("Fantasia"));
			CidadeVO cidade = cidDao.getByNomeAndUF(row.getString("Cidade"), row.getString("Estado"));
			if (cidade != null) {
				endereco.setCodigoCidadeEnderecoPessoa(cidade.getCodigoCidade());
			} else {
				// Padrao: JUARA
				endereco.setCodigoCidadeEnderecoPessoa(4383);
			}
			endereco.setFone1EnderecoPessoa(row.getString("Fone"));
			endereco.setFone2EnderecoPessoa(row.getString("Fax"));
			endereco.setCepEnderecoPessoa(row.getString("Cep").replace("-", "").substring(0, 6));
			endereco.setTipoEnderecoPessoa("3");
			if (endereco.getCepEnderecoPessoa() == null) {
				endereco.setCepEnderecoPessoa("0000000");
			}
			if (row.getString("Docum1") != null) {
				endereco.setCpfCnpjEnderecoPessoa(row.getString("Docum1").replace("/", "").replace(".", "").replace("-", ""));
			} else {
				endereco.setCpfCnpjEnderecoPessoa("0");
			}
			endDao.persist(endereco);
		}
	}

	public void importaEssencia() throws SQLException, ClassNotFoundException {
		RowSet row = new MySqlDAO().getRowSet("SELECT * FROM essencias");
		row.beforeFirst();
		EssenciaDAO dao = new EssenciaDAO();

		while (row.next()) {
			EssenciaVO vo = new EssenciaVO();
			vo.setNewRecord(true);
			vo.setCodigoEssencia(row.getInt("codigo"));
			vo.setNomeEssencia(row.getString("descricao"));

			dao.persist(vo);
		}

		row.close();
	}

	public void importaMadeiraSerrada() throws SQLException, ClassNotFoundException {
		RowSet row = new MySqlDAO("madeiraconsulta").getRowSet("SELECT * FROM serrada");
		row.beforeFirst();
		ProdutoDAO dao = new ProdutoDAO();
		EssenciaDAO edao = new EssenciaDAO();

		while (row.next()) {
			ProdutoVO vo = new ProdutoVO();
			vo.setNewRecord(true);
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoProduto(row.getInt("codigo"));

			vo.setDescricaoProduto(row.getString("desc2"));

			// Busca a essencia
			EssenciaVO evo = new EssenciaVO();
			evo.setNomeEssencia(row.getString("essencia"));
			List<EssenciaVO> list = edao.getList(evo);
			if (list.size() > 0) {
				vo.setCodigoEssencia(list.get(0).getCodigoEssencia());
			} else {
				System.out.println("Essencia nao encontrada!");
			}

			vo.setComprimentoProduto(new BigDecimal(row.getObject("comp").toString().replace(",", ".")));
			vo.setLarguraProduto(new BigDecimal(row.getObject("largura").toString().replace(",", ".")));
			vo.setAlturaProduto(new BigDecimal(row.getObject("espessura").toString().replace(",", ".")));

			vo.setTipoEstoqueProduto("S"); // Tipo Madeira Serrada
			vo.setPermiteVendaFracionadaProduto("N"); // N�o permite venda
														// fracionada

			dao.persist(vo);
		}

		row.close();
	}

	private String somenteNumeros(String source) {
		if (source == null) {
			return "";
		}
		String numeros = "1234567890";
		String copia = "";
		for (int i = 0; i < source.length(); i++) {
			if (numeros.indexOf(source.charAt(i)) >= 0) {
				copia += source.charAt(i);
			}
		}
		return copia;
	}

	private VerbaVO getVerbaSalarioBase() {
		try {
			VerbaVO filter = new VerbaVO();
			filter.setTipoCalculoVerba("V"); // Valor Fixo
			filter.setFlagSalarioBaseVerba("S"); // Salario base (SIM)
			VerbaDAO verbadao = new VerbaDAO();
			List<VerbaVO> list = verbadao.getList(filter);
			for (VerbaVO verba : list) {
				return verba;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public class MySqlDAO {

		private String dbname = "madeira";

		public MySqlDAO(String dbname) {
			this.dbname = dbname;
		}

		public MySqlDAO() {

		}

		private Connection getConnection() {
			Connection conn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname, "root", "carol1408");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}

		public ResultSet getResult(String statement, Connection conn) throws ClassNotFoundException, SQLException {
			ResultSet result = null;
			PreparedStatement prepared = conn.prepareStatement(statement);
			result = prepared.executeQuery();
			return result;
		}

		public CachedRowSet getRowSet(String statement) throws SQLException, ClassNotFoundException {
			CachedRowSet rowset = null;
			rowset = new CachedRowSetImpl();
			Connection conn = getConnection();
			ResultSet result = getResult(statement, conn);
			rowset.populate(result);
			result.close();
			conn.close();
			return rowset;
		}
	}
}