package br.com.orlandoburli.core.extras.nfe.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.nfe.utils.NfeSigner;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.extras.nfe.utils.NfeSigner.TagSign;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class NfeCancelamentoService extends GeneralSoapService {

	public boolean execute(String xmlCancelamento, Ambiente ambiente, LojaVO loja) {
//		try {
//			NfeSigner.sign(xml, "", "", NfeSigner.TagSign.infCanc);
//			//new Assinador().assinar(xml, Statics.getKeystoreFile(), Statics.getKeystorePassword(), "nfecancelamento-assinado.xml", "2");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String xmlCanc = NfeUtils.getXmlContent("nfecancelamento-assinado.xml");
//		xmlCanc = xmlCanc.substring(xmlCanc.indexOf("?>") + 2);
		
//		String soapXml = "<?xml version=\"1.0\"?>" +
//				"<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">" +
//				"	<S:Header>" +
//				"		<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2\">" +
//				"			<cUF>51</cUF>" +
//				"			<versaoDados>2.00</versaoDados>" +
//				"		</nfeCabecMsg>" +
//				"	</S:Header>" +
//				"	<S:Body>" +
//				"		<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeCancelamento2\">" +
//				xmlCancelamento +
//				"		</nfeDadosMsg>" +
//				"	</S:Body>" +
//				"</S:Envelope>";
		
		
		String soapXml = "<?xml version=\"1.0\"?>" +
				"<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"	<S:Header>" +
				"		<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">" +
				"			<cUF>51</cUF>" +
				"			<versaoDados>1.00</versaoDados>" +
				"		</nfeCabecMsg>" +
				"	</S:Header>" +
				"	<S:Body>" +
				"		<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento\">" +
				xmlCancelamento +
				"		</nfeDadosMsg>" +
				"	</S:Body>" +
				"</S:Envelope>";
		
		return super.execute(soapXml, ambiente, "http://www.portalfiscal.inf.br/nfe/wsdl/RecepcaoEvento/nfeRecepcaoEvento", loja);
	}
	
//	public String buildXmlCancelamentoOld(String ChaveNfe, String protocolo, String Motivo, Ambiente ambiente, LojaVO loja) throws SQLException {
//		StringBuilder sb = new  StringBuilder();
//		sb.append("<cancNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\">");
//		sb.append("<infCanc Id=\"ID" + ChaveNfe.replace("NFe", "") + "\">");
//		sb.append("<tpAmb>" + getTpAmb(ambiente) +"</tpAmb>");
//		sb.append("<xServ>CANCELAR</xServ>");
//		sb.append("<chNFe>" + ChaveNfe.replace("NFe", "") + "</chNFe>");
//		sb.append("<nProt>" + protocolo + "</nProt>");
//		sb.append("<xJust>" + Motivo + "</xJust>");
//		sb.append("</infCanc></cancNFe>");
//		
//		ParametroLojaDAO parmLojaDao = new ParametroLojaDAO();
//		
//		String keystoreFile = parmLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja());
//		String keystorePassword = parmLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja());
//		
//		try {
//			String xmlCanc = NfeSigner.sign(sb.toString(), keystoreFile, keystorePassword, TagSign.infCanc);
//			xmlCanc = NfeUtils.removeXmlTag(xmlCanc);
//			return xmlCanc;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public String buildXmlCancelamento(String ChaveNfe, String protocolo, String Motivo, Ambiente ambiente, LojaVO loja) throws SQLException {
		StringBuilder sb = new  StringBuilder();
//		sb.append("<cancNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\">");
//		sb.append("<infCanc Id=\"ID" + ChaveNfe.replace("NFe", "") + "\">");
//		sb.append("<tpAmb>" + getTpAmb(ambiente) +"</tpAmb>");
//		sb.append("<xServ>CANCELAR</xServ>");
//		sb.append("<chNFe>" + ChaveNfe.replace("NFe", "") + "</chNFe>");
//		sb.append("<nProt>" + protocolo + "</nProt>");
//		sb.append("<xJust>" + Motivo + "</xJust>");
//		sb.append("</infCanc></cancNFe>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		String dataHora = sdf.format(Utils.getNow()) + "-04:00";
		
//		sb.append("<envEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\"> versao=\"1.00\"");
//		sb.append("<idLote>000000000000001</idLote>");
//		
		sb.append("<evento versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
		sb.append("<infEvento Id=\"ID110111" + ChaveNfe.replace("NFe", "") + "01\">");
		sb.append("<cOrgao>51</cOrgao>");
		sb.append("<tpAmb>" + getTpAmb(ambiente) +"</tpAmb>");
		sb.append("<CNPJ>" + loja.getCNPJLoja() +"</CNPJ>");
		sb.append("<chNFe>" + ChaveNfe.replace("NFe", "") + "</chNFe>");
		sb.append("<dhEvento>" + dataHora + "</dhEvento>");
		sb.append("<tpEvento>110111</tpEvento>");
		sb.append("<nSeqEvento>1</nSeqEvento>");
		sb.append("<verEvento>1.00</verEvento>");
		sb.append("<detEvento versao=\"1.00\">");
		sb.append("<descEvento>Cancelamento</descEvento>");
		sb.append("<nProt>" + protocolo + "</nProt>");
		sb.append("<xJust>" + Motivo + "</xJust>");
		sb.append("</detEvento>");
		sb.append("</infEvento>");
		sb.append("</evento>");
		
//		sb.append("</envEvento>");
		
		ParametroLojaDAO parmLojaDao = new ParametroLojaDAO();
		
		String keystoreFile = parmLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja());
		String keystorePassword = parmLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja());
		
		try {
			NfeSigner.showCertificateProperties(loja);
			String xmlCanc = NfeSigner.sign(sb.toString(), keystoreFile, keystorePassword, TagSign.infEvento);
			xmlCanc = NfeUtils.removeXmlTag(xmlCanc);
			
			xmlCanc = "<envEvento xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"1.00\"><idLote>000000000000001</idLote>" + xmlCanc + "</envEvento>";
			return xmlCanc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getServiceAddress(Ambiente ambiente) {
		if (ambiente.equals(Ambiente.Producao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Producao.NFECANCELAMENTO);
		} else if (ambiente.equals(Ambiente.Homologacao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Homologacao.NFECANCELAMENTO);
		}
		return null;
	}
}