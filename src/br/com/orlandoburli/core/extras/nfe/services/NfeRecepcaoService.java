package br.com.orlandoburli.core.extras.nfe.services;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class NfeRecepcaoService extends GeneralSoapService {
	
	public boolean execute(String xml, Ambiente ambiente, LojaVO loja) {
		
		String loteNfe = NfeUtils.geraLote(NfeUtils.removeXmlTag(xml), 1); //.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>", "");
		
		String soapXml = "<?xml version=\"1.0\"?>" +
		"<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\">" +
		"<S:Header>" +
		"<nfeCabecMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2\">" +
		"<cUF>51</cUF>" +
		"<versaoDados>2.00</versaoDados>" +
		"</nfeCabecMsg>" +
		"</S:Header>" +
		"<S:Body>" +
		"<nfeDadosMsg xmlns=\"http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2\">" +
		
		NfeUtils.removeXmlTag(loteNfe) +
		
		"</nfeDadosMsg>" +
		"</S:Body>" +
		"</S:Envelope>";
		
		return super.execute(soapXml, ambiente, "http://www.portalfiscal.inf.br/nfe/wsdl/NfeRecepcao2/nfeRecepcaoLote2", loja);
	}

	public String getServiceAddress(Ambiente ambiente) {
		if (ambiente.equals(Ambiente.Producao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Producao.NFERECEPCAO);
		} else if (ambiente.equals(Ambiente.Homologacao)) {
			return SystemManager.getProperty(Constants.NfeParameters.Homologacao.NFERECEPCAO);
		}
		return null;
	}
}