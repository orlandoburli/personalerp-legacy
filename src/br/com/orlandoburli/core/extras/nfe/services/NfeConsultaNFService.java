package br.com.orlandoburli.core.extras.nfe.services;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class NfeConsultaNFService extends GeneralSoapService {
	
	public boolean execute(String chave, Ambiente ambiente, LojaVO loja) {
		
		String soapXml = "<?xml version=\"1.0\"?>" +
				"<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">" +
				"<S:Header>" +
				"<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">" +
				"<cUF>51</cUF>" +
				"<versaoDados>2.01</versaoDados>" +
				"</nfeCabecMsg>" +
				"</S:Header>" +
				"<S:Body>" +
				"<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2\">" +
				"<consSitNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.01\">" +
				"<tpAmb>" + getTpAmb(ambiente) + "</tpAmb>" +
				"<xServ>CONSULTAR</xServ>" +
				"<chNFe>" + chave.replace("NFe", "") + "</chNFe>" +
				"</consSitNFe>" +
				"</nfeDadosMsg>" +
				"</S:Body>" +
				"</S:Envelope>";
		
		System.out.println(soapXml);
		
		return super.execute(soapXml, ambiente, "http://www.portalfiscal.inf.br/nfe/wsdl/NfeConsulta2/nfeConsultaNF2", loja);
	}

	public String getServiceAddress(Ambiente ambiente) {
		if (ambiente.equals(Ambiente.Producao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Producao.NFECONSULTANF);
		} else if (ambiente.equals(Ambiente.Homologacao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Homologacao.NFECONSULTANF);
		}
		return null;
	}
}