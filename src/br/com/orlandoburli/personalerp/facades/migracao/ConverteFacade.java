package br.com.orlandoburli.personalerp.facades.migracao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseDAO;
import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.base.EstadoDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.ReferenciaComercialDAO;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.FabricanteDAO;
import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.SubGrupoDAO;
import br.com.orlandoburli.core.dao.estoque.UnidadeDAO;
import br.com.orlandoburli.core.dao.fiscal.CfopDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.NFEntradaDAO;
import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.base.EstadoVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.ReferenciaComercialVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.FabricanteVO;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubGrupoVO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.vo.fiscal.CfopVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

@IgnoreFacadeAuthentication
public class ConverteFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private static final int CODIGO_EMPRESA_PADRAO = 1 ;
	private static final int CODIGO_LOJA_PADRAO = 1;
	private static String FOLDER = "C:\\temp\\xxx\\";

	@IgnoreMethodAuthentication
	public void execute() {
		try {
//			importaEstados();
			importaCidades2();
			importaNada();
//			importaCidades();
//			converteClientes();
//			importaReferenciaComercial();
//			importaFabricantes();
//			importaGrupos();
//			importaSubGrupos();
//			importaUnidades();
//			importaProdutos();
//			importaTributacoes();
//			importaCfop();
//			importaNFEntrada();
//			importaVendedor();
//			importaEstoque();
			write("\nFim da importacao.");
		} catch (IOException e) {
			write(e.getMessage());
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}
	
	public void apagaDados(String table) {
		UtilDao dao = new UtilDao();
		try {
			dao.deletetable(table);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void importaNada() throws IOException, SQLException {
		
	}
	
	public void importaEstoque() throws IOException, SQLException {
		apagaDados("movimentacaoestoque");
		apagaDados("estoque");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "ESTC");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		EstoqueDAO dao = new EstoqueDAO();
		while ((line = reader.readLine()) != null) {
			EstoqueVO vo = new EstoqueVO();
			vo.setNewRecord(true);
			
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoEmpresaEstoque(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLojaEstoque(CODIGO_LOJA_PADRAO);
			vo.setCodigoProduto(Integer.parseInt(line.substring(2, 11).trim()));
			vo.setEstoqueFisico(new BigDecimal(Double.parseDouble(line.substring(11, 22).trim()) / 1000));
			vo.setEstoqueFiscal(new BigDecimal(Double.parseDouble(line.substring(11, 22).trim()) / 1000));
			vo.setPrecoMedioEstoque(new BigDecimal(Double.parseDouble(line.substring(22, 35).trim()) / 1000));
			vo.setDataPrecoMedioEstoque(getDate(line.substring(35, 43).trim()));
			vo.setDataUltimaEntradaEstoque(getDate(line.substring(43, 51).trim()));
			vo.setLocacaoEstoque(line.substring(51, 61).trim());
			vo.setDemandaEstoque(new BigDecimal(Double.parseDouble(line.substring(61, 67).trim())));
			vo.setFrequenciaEstoque(new BigDecimal(Double.parseDouble(line.substring(67, 73).trim())));
			dao.persist(vo);
			linhas++;
		}
		write("Estoque importado! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaVendedor() throws IOException, SQLException {
		apagaDados("vendedor");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "VEND");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		VendedorDAO dao = new VendedorDAO();
		while ((line = reader.readLine()) != null) {
			VendedorVO vo = new VendedorVO();
			vo.setNewRecord(true);
			
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoVendedor(Integer.parseInt(line.substring(2, 5).trim()));
			vo.setNomeVendedor(line.substring(5, 35).trim());
			vo.setTipoVendedor(line.substring(35, 36).trim());
			vo.setPercComissaoClienteVendedor(Double.parseDouble(line.substring(44, 48).trim()) / 100);
			vo.setPercComissaoClienteEmpresa(Double.parseDouble(line.substring(48, 52).trim()) / 100);
			vo.setCategoriaVendedor(line.substring(64, 65).trim());
			vo.setSituacaoVendedor(line.substring(68, 69).trim());
			vo.setEmailVendedor(line.substring(69, 104).trim());
			
			dao.persist(vo);
			linhas++;
		}
		write("Vendedores importados! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaNFEntrada() throws IOException, SQLException {
		apagaDados("nfentrada");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "NFEN");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		NFEntradaDAO dao = new NFEntradaDAO();
		while ((line = reader.readLine()) != null) {
			NFEntradaVO vo = new NFEntradaVO();
			vo.setNewRecord(true);
			
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoEmpresaEmitenteNFEntrada(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLojaEmitenteNFEntrada(CODIGO_LOJA_PADRAO);
			vo.setCodigoPessoaEmitenteNFEntrada(Integer.parseInt(line.substring(0, 6).trim()));
			vo.setCodigoNFEntrada(Integer.parseInt(line.substring(6, 14).trim()));
			
			dao.persist(vo);
			linhas++;
		}
		write("Notas de entrada importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaCfop() throws IOException, SQLException {
		apagaDados("cfop");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "CFOP");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		CfopDAO dao = new CfopDAO();
		while ((line = reader.readLine()) != null) {
			CfopVO vo = new CfopVO();
			vo.setNewRecord(true);
			
			vo.setCodigoCfop(Integer.parseInt(line.substring(0, 4).trim()));
			vo.setComplementoCfop(line.substring(4, 14).trim());
			vo.setDescricaoCfop(line.substring(14, 74).trim());
			vo.setTipoCfop(line.substring(74, 75).trim());
			
			try {
				vo.setClassificacaoCfop(Integer.parseInt(line.substring(76, 77).trim()));
			} catch(Exception ex) {
				
			}
			
			vo.setObservacoesCfop(line.substring(86, 486).trim());
			
			dao.persist(vo);
			linhas++;
		}
		write("Tributacoes importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaProdutos() throws IOException, SQLException {
		apagaDados("produto");
		int linhas = 0;
		// Usa o arquivo de estoque para gerar as unidades
		FileInputStream in = new FileInputStream(FOLDER + "ESTO");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		ProdutoDAO dao = new ProdutoDAO();
		while ((line = reader.readLine()) != null) {
			
			ProdutoVO vo = new ProdutoVO();
			
			vo.setNewRecord(true);
			
			vo.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			vo.setCodigoLoja(CODIGO_LOJA_PADRAO);
			vo.setCodigoProduto(Integer.parseInt(line.substring(0, 9).trim()));
			vo.setDescricaoProduto(line.substring(69, 119).trim());
			vo.setCodigoReferenciaProduto(line.substring(9, 29).trim());
			vo.setTipoEstoqueProduto(line.substring(449, 450).trim());
			vo.setCodigoGrupo(getCodigoGrupo(line.substring(122, 125).trim()));
			vo.setCodigoSubGrupo(getCodigoSubGrupo(line.substring(125, 128).trim()));
			
			if (line.substring(119, 122).trim() != null && !line.substring(119, 122).trim().equals("") && Integer.parseInt(line.substring(119, 122).trim()) > 0) {
				vo.setCodigoFabricante(Integer.parseInt(line.substring(119, 122).trim()));
			}
			
//			vo.setCodigoClasseTributaria(Integer.parseInt(line.substring(133, 135).trim()));
			
			if (line.substring(139, 141).trim() != null && !line.substring(139, 141).trim().equals("")) {
				vo.setSiglaUnidade(line.substring(139, 141).trim());
			}
			
			if (line.substring(459, 470).trim() != null && !line.substring(459, 470).trim().equals("")) {
				vo.setComprimentoProduto(new BigDecimal(Double.parseDouble(line.substring(459, 470).trim()) / 1000));
			}
			if (line.substring(470, 481).trim() != null && !line.substring(470, 481).trim().equals("")) {
				vo.setLarguraProduto(new BigDecimal(Double.parseDouble(line.substring(470, 481).trim()) / 1000));
			}
			vo.setPermiteVendaFracionadaProduto(line.substring(437, 438).trim());
			try {
				vo.setMargemLucroVarejoProduto(new BigDecimal(Double.parseDouble(line.substring(147, 154).trim()) / 10000));
				vo.setMargemLucroAtacadoProduto(new BigDecimal(Double.parseDouble(line.substring(154, 161).trim()) / 10000));
			} catch (Exception ex) {}
			
			if (line.substring(141, 147).trim() != null && !line.substring(141, 147).trim().equals("") && Integer.parseInt(line.substring(141, 147).trim()) > 0) {
				vo.setCodigoEmpresaFornecedor(CODIGO_EMPRESA_PADRAO);
				vo.setCodigoLojaFornecedor(CODIGO_LOJA_PADRAO);
				vo.setCodigoPessoaFornecedor(Integer.parseInt(line.substring(141, 147).trim()));	
			}
			
			vo.setEstoqueMinimoProduto(new BigDecimal(Double.parseDouble(line.substring(438, 449).trim()) / 1000));
			vo.setSituacaoProduto(line.substring(435, 436).trim());
			vo.setObservacoesProduto(line.substring(191, 431).trim());
			
			dao.persist(vo);
			
			linhas++;
//			if (linhas >= 50) break;
		}
		write("Unidades importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaUnidades() throws IOException, SQLException {
		apagaDados("unidade");
		int linhas = 0;
		// Usa o arquivo de estoque para gerar as unidades
		FileInputStream in = new FileInputStream(FOLDER + "ESTO");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		UnidadeDAO dao = new UnidadeDAO();
		while ((line = reader.readLine()) != null) {
			// Verifica se já existe a unidade
			UnidadeVO filter = new UnidadeVO();
			String sigla = line.substring(139, 141).trim();
			if (sigla.trim().equals("")) { 
				continue; // Pula pro próximo - vazio é nulo
			}
			filter.setSiglaUnidade(sigla);
			if (dao.getList(filter).size() <= 0) {
				UnidadeVO vo = new UnidadeVO();
				vo.setNewRecord(true);
				vo.setSiglaUnidade(sigla);
				dao.persist(vo);
				linhas++;
			}
		}
		write("Unidades importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaSubGrupos() throws IOException, SQLException {
		apagaDados("subgrupo");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "SGRU");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		SubGrupoDAO dao = new SubGrupoDAO();
		while ((line = reader.readLine()) != null) {
			SubGrupoVO vo = new SubGrupoVO();
			vo.setNewRecord(true);
			
			vo.setCodigoSubGrupo(Integer.parseInt(line.substring(0, 3).trim()));
			vo.setNomeSubGrupo(line.substring(3, 43).trim());
			
			dao.persist(vo);
			linhas++;
		}
		write("Sub Grupos importados! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaGrupos() throws IOException, SQLException {
		apagaDados("grupo");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "GRUP");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		GrupoDAO dao = new GrupoDAO();
		while ((line = reader.readLine()) != null) {
			GrupoVO vo = new GrupoVO();
			vo.setNewRecord(true);
			
			vo.setCodigoGrupo(Integer.parseInt(line.substring(0, 3).trim()));
			vo.setNomeGrupo(line.substring(3, 43).trim());
			//line.substring(43, 44).trim(); // não sei o que é...
			vo.setTipoGrupo(line.substring(44, 45).trim());
			vo.setQuantidadeMinimaUnitariaGrupo(Integer.parseInt(line.substring(45, 56).trim()) / 1000);
			vo.setQuantidadeMinimaMetroGrupo(Double.parseDouble(line.substring(56, 67).trim()) / 1000);
			vo.setPercentualComissaoGrupo(Double.parseDouble(line.substring(67, 72).trim()) / 100);
			
			dao.persist(vo);
			linhas++;
		}
		write("Grupos importados! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaFabricantes() throws IOException, SQLException {
		apagaDados("fabricante");
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "FABR");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		FabricanteDAO dao = new FabricanteDAO();
		while ((line = reader.readLine()) != null) {
			FabricanteVO vo = new FabricanteVO();
			vo.setNewRecord(true);
			vo.setCodigoFabricante(Integer.parseInt(line.substring(0, 3).trim()));
			vo.setNomeFabricante(line.substring(3, 43).trim());
			dao.persist(vo);
			linhas++;
		}
		write("Fabricantes importados! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaCidades () throws IOException, SQLException {
		apagaDados("cidade");
		int linhas = 0;
		
		// Importacao de cidades
		FileInputStream in = new FileInputStream(FOLDER + "cidades.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		CidadeDAO dao = new CidadeDAO();
		while ((line = reader.readLine()) != null) {
			CidadeVO cidade = new CidadeVO();
			cidade.setNewRecord(true);
			cidade.setCodigoCidade(Integer.parseInt(line.substring(0, 11).trim()));
			cidade.setNomeCidade(Utils.RemoverAcentos.remover(line.substring(11, 70).trim()).toUpperCase());
			cidade.setCodigoIbgeCidade(null);
			cidade.setCodigoEstado(Integer.parseInt(line.substring(70, 81).trim()));
			dao.persist(cidade);
			linhas++;
		}
		write("Cidades importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaCidades2 () throws IOException, SQLException {
		int linhas = 0;
		
		// Importacao de cidades
		FileInputStream in = new FileInputStream(FOLDER + "MUNI");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		CidadeDAO dao = new CidadeDAO();
		while ((line = reader.readLine()) != null) {
			
			CidadeVO cidade = new CidadeVO();
			cidade.setNomeCidade(Utils.RemoverAcentos.remover(line.substring(0, 30).trim()).toUpperCase());
			cidade.setNomeCidade(cidade.getNomeCidade().replace("'", "''"));
			cidade.setSiglaUFCidade(line.substring(30, 32));
			
			List<CidadeVO> list = dao.getList(cidade);
			
			if (list.size() > 0) {
				// Se ja existe, atualiza somente o codigo IBGE
				cidade = list.get(0);
				cidade.setCodigoIbgeCidade(line.substring(32, 37));
				dao.persist(cidade);
			} else {
				// Senao, cria
				cidade = new CidadeVO();
				cidade.setNewRecord(true);
				write("Cidade nao existente - " + line + "\n");
			}
			
			linhas++;
		}
		write("Cidades importadas! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void importaEstados () throws IOException, SQLException {
		int linhas = 0;
		
		// Importacao de cidades
		FileInputStream in = new FileInputStream(FOLDER + "UFED");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		EstadoDAO dao = new EstadoDAO();
		while ((line = reader.readLine()) != null) {
			
			EstadoVO estado = new EstadoVO();
			estado.setSiglaEstado(line.substring(0, 2));
			
			List<EstadoVO> list = dao.getList(estado);
			
			if (list.size() > 0) {
				// Se ja existe, atualiza somente o codigo IBGE
				estado = list.get(0);
				estado.setCodigoIbgeEstado(line.substring(44, 46));
				dao.persist(estado);
			} else {
				// Senao, cria
				write("Estado nao existente - " + line + "\n");
			}
			
			linhas++;
		}
		write("Estados importados! Total " + linhas+ " registros.\n");
		reader.close();
	}
	
	public void converteClientes() throws IOException, SQLException {
		// Apaga dados
		apagaDados("pessoafisica");
		apagaDados("enderecopessoa");
		apagaDados("pessoa");
		
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "OPER");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		PessoaDAO pesDao = new PessoaDAO(); 
		EnderecoPessoaDAO endDao = new EnderecoPessoaDAO();
		
		while ((line = reader.readLine()) != null) {
			// Pessoa
			PessoaVO pessoa = new PessoaVO();
			pessoa.setNewRecord(true);
			pessoa.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			pessoa.setCodigoLoja(CODIGO_LOJA_PADRAO);
			pessoa.setCodigoPessoa(Integer.parseInt(line.substring(0, 6)));
			pessoa.setNaturezaPessoa(line.substring(26, 27));
			pessoa.setNomeRazaoSocialPessoa(line.substring(29, 79).trim());
			pessoa.setEmailPessoa(line.substring(835, 875).trim());
			pessoa.setObservacoesPessoa(line.substring(659, 819).trim());
			
			// Endereço Cobrança
			EnderecoPessoaVO endCobPes = new EnderecoPessoaVO();
			endCobPes.setNewRecord(true);
			endCobPes.setCodigoEmpresa(pessoa.getCodigoEmpresa());
			endCobPes.setCodigoLoja(pessoa.getCodigoLoja());
			endCobPes.setCodigoPessoa(pessoa.getCodigoPessoa());
			endCobPes.setLogradouroEnderecoPessoa(line.substring(169, 219).trim());
			endCobPes.setBairroEnderecoPessoa(line.substring(239, 259).trim());
			endCobPes.setFone1EnderecoPessoa(line.substring(279, 309).trim());
			endCobPes.setCepEnderecoPessoa(line.substring(354, 362).trim());
			endCobPes.setTipoEnderecoPessoa("1"); // Tipo Cobranca
			// Busca o código da cidade
			String cidadeNom = line.substring(309, 344).trim();
			String ufSigla = line.substring(352, 354).trim();
			endCobPes.setCodigoCidadeEnderecoPessoa(getCodigoCidade(cidadeNom, ufSigla));
			
			// Endereço Entrega
			EnderecoPessoaVO endEntPes = new EnderecoPessoaVO();
			endEntPes.setNewRecord(true);
			endEntPes.setCodigoEmpresa(pessoa.getCodigoEmpresa());
			endEntPes.setCodigoLoja(pessoa.getCodigoLoja());
			endEntPes.setCodigoPessoa(pessoa.getCodigoPessoa());
			endEntPes.setLogradouroEnderecoPessoa(line.substring(362, 412).trim());
			endEntPes.setBairroEnderecoPessoa(line.substring(432, 452).trim());
			endEntPes.setFone1EnderecoPessoa(line.substring(472, 502).trim());
			endEntPes.setCepEnderecoPessoa(line.substring(547, 555).trim());
			endEntPes.setTipoEnderecoPessoa("2"); // Tipo Entrega
			// Busca o código da cidade
			cidadeNom = line.substring(502, 537).trim();
			ufSigla = line.substring(545, 547).trim();
			endEntPes.setCodigoCidadeEnderecoPessoa(getCodigoCidade(cidadeNom, ufSigla));
			
			// Persistência
			pesDao.persist(pessoa);
			endDao.persist(endCobPes);
			endDao.persist(endEntPes);
			
			linhas ++;
//			if (linhas > 19) break;
		}
		reader.close();
		write("Clientes / Fornecedores importados! Total " + linhas+ " registros.\n");		
	}
	
	private Integer getCodigoCidade(String nomeCidade, String siglaUf) throws SQLException {
		EstadoVO estado = new EstadoVO();
		estado.setSiglaEstado(siglaUf);
		
		CidadeVO filter = new CidadeVO();
		filter.setNomeCidade(nomeCidade);
		
		List<EstadoVO> listUf= new EstadoDAO().getList(estado);
		if (listUf.size() > 0) {
			filter.setCodigoEstado(listUf.get(0).getCodigoEstado());
		} else {
			return null;
		}
		
		List<CidadeVO> listCid = new CidadeDAO().getList(filter);
		if (listCid.size() > 0) {
			return listCid.get(0).getCodigoCidade();
		}
		
		return null;
	}
	
	public void importaReferenciaComercial() throws IOException, SQLException {
		apagaDados("referenciacomercial");
		
		int linhas = 0;
		FileInputStream in = new FileInputStream(FOLDER + "OPE2");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		
		ReferenciaComercialDAO dao = new ReferenciaComercialDAO();
		
		while ((line = reader.readLine()) != null) {
			ReferenciaComercialVO refcom = new ReferenciaComercialVO();
			
			refcom.setNewRecord(true);
			refcom.setCodigoEmpresa(CODIGO_EMPRESA_PADRAO);
			refcom.setCodigoLoja(CODIGO_LOJA_PADRAO);
			refcom.setCodigoPessoa(Integer.parseInt(line.substring(0, 6).trim()));
			refcom.setCodigoReferenciaComercial(Integer.parseInt(line.substring(6, 9).trim()));
			refcom.setLocalReferenciaComercial(line.substring(9, 39).trim());
			refcom.setFoneReferenciaComercial(line.substring(39, 54).trim());
			refcom.setContatoReferenciaComercial(line.substring(54, 94).trim());
			refcom.setDataReferenciaComercial(getDate(line.substring(94, 102).trim()));
			refcom.setFormaPagamentoReferenciaComercial(line.substring(108, 109).trim());
			refcom.setMediaMensalReferenciaComercial(Double.parseDouble(line.substring(109, 119).trim()) / 100);
			refcom.setMediaDiasAtrasoReferenciaComercial(Integer.parseInt(line.substring(119, 122).trim()));
			refcom.setMaiorCompraReferenciaComercial(Double.parseDouble(line.substring(122, 132).trim()) / 100);
			refcom.setDataMaiorCompraReferenciaComercial(getDate(line.substring(132, 140).trim()));
			refcom.setUltimaCompraReferenciaComercial(Double.parseDouble(line.substring(140, 150).trim()) / 100);
			refcom.setDataUltimaCompraReferenciaComercial(getDate(line.substring(150, 158).trim()));
			refcom.setConceitoReferenciaComercial(Integer.parseInt(line.substring(158, 159).trim()));
			refcom.setDataCadastroReferenciaComercial(getDate(line.substring(102, 108).trim() + "01"));
			refcom.setObservacoesReferenciaComercial(line.substring(159, 239).trim());
			
			dao.persist(refcom);
			linhas++;
		}
		write("Referencias comerciais importadas! Total " + linhas + " linhas.");
		reader.close();
	}
	
	@SuppressWarnings("deprecation")
	protected Date getDate(String valor) {
		if (Integer.parseInt(valor) <= 0) {
			return null;
		}
		String sData = valor;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		java.util.Date dateAux = null;
		try {
			dateAux = format.parse(sData);
		} catch (ParseException e) {
		}
		if (dateAux != null) {
			return new Date(dateAux.getYear(), dateAux.getMonth(), dateAux.getDate());
		}
		return null;
	}
	
	protected String limpa(String valor, String permitidos) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < valor.length(); i ++) {
			if ( permitidos.indexOf(valor.substring(i, i + 1)) >= 0 ) {
				sb.append(valor.substring(i, i + 1));
			}
		}
		return sb.toString();
	}
	
	public class UtilDao extends BaseDAO {
		public void deletetable(String tablename) throws IllegalArgumentException, ClassNotFoundException, SQLException, IllegalAccessException{
			this.executeDelete("delete from " + tablename, null);
		}
	}
	
	private Integer getCodigoGrupo(String codigoGrupo) {
		try {
			Integer codGrupo = Integer.parseInt(codigoGrupo);
			if (codGrupo <= 0) {
				return null;
			}
			GrupoVO filter = new GrupoVO();
			filter.setCodigoGrupo(codGrupo);
			GrupoDAO dao = new GrupoDAO();
			if (dao.getList(filter).size() > 0) {
				return codGrupo;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}
	
	private Integer getCodigoSubGrupo(String codigoSubGrupo) {
		try {
			Integer codSubGrupo = Integer.parseInt(codigoSubGrupo);
			if (codSubGrupo == 0) {
				return null;
			}
			SubGrupoVO filter = new SubGrupoVO();
			filter.setCodigoSubGrupo(codSubGrupo);
			SubGrupoDAO dao = new SubGrupoDAO();
			if (dao.getList(filter).size() > 0) {
				return codSubGrupo;
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}
}