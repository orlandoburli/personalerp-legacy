package br.com.orlandoburli.personalerp.facades.fiscal.nfereader;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class NFeReaderFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private String htmlNfe;
	
	public void execute() {
		try {
			
			if (getHtmlNfe() == null) {
				write("Nenhum valor recebido!");
				return;
			}
			
			salvarArquivo(getHtmlNfe().getBytes(), "c:\\temp\\completo.html");
			
			String xml1 = getXmlFromHtmlNfe(getHtmlNfe(), 0);
			salvarArquivo(xml1.getBytes(), "c:\\temp\\xml1.xml");
			
			int posicaoStart2 = getHtmlNfe().indexOf(xml1) + xml1.length();
			String xml2 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart2);
			salvarArquivo(xml2.getBytes(), "c:\\temp\\xml2.xml");
			
			int posicaoStart3 = getHtmlNfe().indexOf(xml2) + xml2.length();
			String xml3 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart3);
			salvarArquivo(xml3.getBytes(), "c:\\temp\\xml3.xml");
			
			int posicaoStart4 = getHtmlNfe().indexOf(xml3) + xml3.length();
			String xml4 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart4);
			salvarArquivo(xml4.getBytes(), "c:\\temp\\xml4.xml");
			
			int posicaoStart5 = getHtmlNfe().indexOf(xml4) + xml4.length();
			String xml5 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart5);
			salvarArquivo(xml5.getBytes(), "c:\\temp\\xml5.xml");
			
			int posicaoStart6 = getHtmlNfe().indexOf(xml5) + xml5.length();
			String xml6 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart6);
			salvarArquivo(xml6.getBytes(), "c:\\temp\\xml6.xml");
			
			int posicaoStart7 = getHtmlNfe().indexOf(xml6) + xml6.length();
			String xml7 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart7);
			salvarArquivo(xml7.getBytes(), "c:\\temp\\xml7.xml");
			
			int posicaoStart8 = getHtmlNfe().indexOf(xml7) + xml7.length();
			String xml8 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart8);
			salvarArquivo(xml8.getBytes(), "c:\\temp\\xml8.xml");
			
			int posicaoStart9 = getHtmlNfe().indexOf(xml8) + xml8.length();
			String xml9 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart9);
			salvarArquivo(xml9.getBytes(), "c:\\temp\\xml9.xml");
			
			int posicaoStart10 = getHtmlNfe().indexOf(xml9) + xml9.length();
			String xml10 = getXmlFromHtmlNfe(getHtmlNfe(), posicaoStart10);
			salvarArquivo(xml10.getBytes(), "c:\\temp\\xml10.xml");
			
			// INICIO DO PROCESSAMENTO
			
			// Dao generica
			GenericDAO dao = new GenericDAO();
			dao.setAutoCommit(false);
			
			// Dados da nota de entrada
			NFEntradaVO nfentrada = new NFEntradaVO();
			nfentrada.setNewRecord(true);
			
			PessoaVO pessoa = new PessoaVO();
			pessoa.setNewRecord(true);
			
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();
			endereco.setNewRecord(true);
			
			LojaVO loja = new LojaVO();
			
			// Xml 1 - Cabecalho da nota
			processaXmlDadosNota(xml1, dao, nfentrada, pessoa, endereco, loja);
			
			// Xml 2 - Dados do emitente
			processaXmlDadosEmitente(xml2, dao, nfentrada, pessoa, endereco, loja);
			
			salvarArquivo(Utils.voToXml(nfentrada).getBytes(), "c:\\temp\\nfentrada.xml");
			write("ok");
		} catch (Exception e) {
			write("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void processaXmlDadosNota(String xml, GenericDAO dao, NFEntradaVO nfentrada, PessoaVO pessoa, EnderecoPessoaVO endereco, LojaVO loja) throws ParserConfigurationException, SAXException, IOException, ParseException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		Element element = doc.getDocumentElement();
		NodeList list = element.getElementsByTagName("table");
		
		// tagDadosNFe - table > tr
		Element tagDadosNFe = (Element) list.item(1).getChildNodes().item(0);
		
		// Hierarquia: td > span
		
		// td 1 - Codigo / Numero da Nota
		nfentrada.setCodigoNFEntrada(Integer.parseInt(tagDadosNFe.getChildNodes().item(0).getChildNodes().item(1).getTextContent()));
		
		// td 2 - Serie da Nota
		nfentrada.setSerieNFEntrada(tagDadosNFe.getChildNodes().item(1).getChildNodes().item(1).getTextContent());
		
		// td 3 - Data de emissao
		nfentrada.setDataEmissaoNFEntrada(parseDate(tagDadosNFe.getChildNodes().item(2).getChildNodes().item(1).getTextContent()));
		
		// td 4 - Valor Total da Nota Fiscal
		nfentrada.setValorTotalNFEntrada(parseNumber(tagDadosNFe.getChildNodes().item(3).getChildNodes().item(1).getTextContent()));
		
		// Tag de dados do Emitente
		// Hierarquia: td > span
		Element tagDadosEmitente = (Element) list.item(3).getChildNodes().item(0);
		
		// td 1 - CNPJ do Emitente
		endereco.setCpfCnpjEnderecoPessoa(tagDadosEmitente.getChildNodes().item(0).getChildNodes().item(1).getTextContent().replace(".", "").replace("/", ""));
		
		// td 2 - Razao Social
		pessoa.setNomeRazaoSocialPessoa(tagDadosEmitente.getChildNodes().item(0).getChildNodes().item(1).getTextContent());
		
		// Tag de dados do Destinatario - table > tr
		// Hierarquia: td > span
		Element tagDadosDestinatario = (Element) list.item(5).getChildNodes().item(0);
		
		// td1 - CNPJ do Destinatario
		loja.setCNPJLoja(tagDadosDestinatario.getChildNodes().item(0).getChildNodes().item(1).getTextContent().replace(".", "").replace("/", ""));
		
		// Tag de dados de informacoes da emissao - table > tr
		// Hierarquia: td > span
		// Obs. esse tem 2 tr´s, somente o segundo interessa.
		Element tagDadosEmissao = (Element) list.item(7).getChildNodes().item(1);
		
		String naturezaOperacao = tagDadosEmissao.getChildNodes().item(0).getChildNodes().item(1).getTextContent().trim();
		String tipoOperacao = tagDadosEmissao.getChildNodes().item(1).getChildNodes().item(1).getTextContent().trim();
		String formaPagamento = tagDadosEmissao.getChildNodes().item(2).getChildNodes().item(1).getTextContent().trim();
		
		System.out.println(naturezaOperacao);
		System.out.println(tipoOperacao);
		System.out.println(formaPagamento);
	}
	
	private void processaXmlDadosEmitente(String xml, GenericDAO dao, NFEntradaVO nfentrada, PessoaVO pessoa, EnderecoPessoaVO endereco, LojaVO loja) throws ParserConfigurationException, SAXException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		Element element = doc.getDocumentElement();
		@SuppressWarnings("unused")
		NodeList list = element.getElementsByTagName("table");
	}
	
	private String getXmlFromHtmlNfe(String htmlNfe, int start) {
		int posicaoInicial = htmlNfe.indexOf("<?xml ", start);
		
		// Busca tag root do xml
		int posicao1 = htmlNfe.indexOf("<", posicaoInicial + 1);
		int posicao2 = htmlNfe.indexOf(">", posicao1 + 1);
		String tagRoot = htmlNfe.substring(posicao1, posicao2 + 1);
		int posicaoEspacoRoot = tagRoot.indexOf(" ");
		String tagRootFim = "</" + tagRoot.substring(1, posicaoEspacoRoot) + ">";
		
		int posicaoFinal = htmlNfe.indexOf(tagRootFim, posicaoInicial) + tagRootFim.length();
		
		return htmlNfe.substring(posicaoInicial, posicaoFinal);
	}

	protected void salvarArquivo(byte[] data, String fileName) throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
		out.write(data);
		out.close();
	}

	private BigDecimal parseNumber(String value) throws ParseException {
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		
		DecimalFormat decFormat = new DecimalFormat("", symbols);
		String newValue = decFormat.parse(value).toString();
		return new BigDecimal(newValue);
	}
	
	private Date parseDate(String value) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return new Date(sdf.parse(value).getTime());
	}
	
	public void setHtmlNfe(String htmlNfe) {
		this.htmlNfe = htmlNfe;
	}

	public String getHtmlNfe() {
		return htmlNfe;
	}
}
