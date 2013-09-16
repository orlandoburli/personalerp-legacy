package br.com.orlandoburli.core.extras.nfe.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;

import br.com.orlandoburli.core.extras.nfe.interfaces.IServico;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public abstract class GeneralSoapService implements IServico {

	private String[] lastMessages;
	private String soapXmlSend;
	private String soapXmlReceived;
	
	{
		//NfeUtils.InitializeSSL(false);
	}
	
	public boolean execute(String soapXml, Ambiente ambiente, String soapAction, LojaVO loja) {
		try {
			
			NfeUtils.InitializeSSL(false, loja);
			
			MessageFactory factory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);  
			SOAPMessage message;  
			
			MimeHeaders header = new MimeHeaders();
			
			if (soapAction != null) {
				header.addHeader("SOAPAction", soapAction);
			}
			
			header.addHeader("Content-Type", "application/soap+xml");
			
			this.soapXmlSend = soapXml;
			
			message = factory.createMessage(header, new ByteArrayInputStream(soapXml.getBytes()));  
            SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();  
            
			URL url = new URL(getServiceAddress(ambiente));  
			SOAPMessage res = con.call(message, url);  
   
			ByteArrayOutputStream in = new ByteArrayOutputStream();  
			message.writeTo(in);  
   
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			res.writeTo(out);
			
			try {
				lastMessages = new String[]{out.toString()};
				this.soapXmlReceived = out.toString();
			} catch (Exception e) {
				lastMessages = new String[] {NfeUtils.FillInXml(e.getMessage(), "erro")};
				e.printStackTrace();
				return false;
			}
		} catch (ConnectException e) {
			lastMessages = new String[] {NfeUtils.FillInXml(e.getMessage(), "erro")};
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			lastMessages = new String[] {NfeUtils.FillInXml(e.getMessage(), "erro")};
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getTpAmb(Ambiente ambiente) {
		return (ambiente ==Ambiente.Producao?"1":"2");
	}
	
	protected void writeFile(String content, String fileName) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(content.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getLastMessages() {
		return lastMessages;
	}

	public String getSoapXmlSend() {
		return soapXmlSend;
	}

	public String getSoapXmlReceived() {
		return soapXmlReceived;
	}
}