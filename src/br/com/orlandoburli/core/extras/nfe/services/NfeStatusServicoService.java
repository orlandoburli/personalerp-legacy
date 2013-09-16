package br.com.orlandoburli.core.extras.nfe.services;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.sistema.LojaVO;


public class NfeStatusServicoService extends GeneralSoapService {

	public boolean execute(String xml, Ambiente ambiente, LojaVO loja) {
		
		String soapXml = "<?xml version=\"1.0\"?>" +
		"<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">" +
		"<S:Header>" +
		"<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">" +
		"<cUF>51</cUF>" +
		"<versaoDados>2.00</versaoDados>" +
		"</nfeCabecMsg>" +
		"</S:Header>" +
		"<S:Body>" +
		"<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2\">" +
		"<consStatServ xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\">" +
		"<tpAmb>" + getTpAmb(ambiente) + "</tpAmb>" +
		"<cUF>51</cUF>" +
		"<xServ>STATUS</xServ>" +
		"</consStatServ>" +
		"</nfeDadosMsg>" +
		"</S:Body>" +
		"</S:Envelope>";
		
		return super.execute(soapXml, ambiente, "http://www.portalfiscal.inf.br/nfe/wsdl/NfeStatusServico2/nfeStatusServicoNF2", loja);
	}
	
	public boolean execute(Ambiente ambiente, LojaVO loja) {
		return execute(null, ambiente, loja);
	}
	
	public String getServiceAddress(Ambiente ambiente) {
		if (ambiente.equals(Ambiente.Producao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Producao.NFESTATUSSERVICO);
		} else if (ambiente.equals(Ambiente.Homologacao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Homologacao.NFESTATUSSERVICO);
		}
		return null;
	}
}