package br.com.orlandoburli.core.extras.nfe.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

//import com.developermaster.nfe.utils.X509KeySelector;

public class NfeSigner {

	public enum TagSign {
		infNFe,
		infCanc,
		infInut,
		infEvento
	}
	
	private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
	
	/**
	 * Assinatura do xml da Nf-e
	 * @param xmlData Xml em String
	 * @param pfxPath Caminho do certificado
	 * @param password Senha do certificado
	 * @param tag Tag a ser assinada
	 * @return Xml Assinado
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String sign(String xmlData, String pfxPath, String password, TagSign tag) throws Exception 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xmlData.getBytes());
		Document docs = builder.parse(is);

		// Obtem elemento do documento a ser assinado, será criado uma
		// REFERENCE para o mesmo
		NodeList elements = docs.getElementsByTagName(tag.toString());
		Element el = (Element) elements.item(0);
		String id = el.getAttribute("Id");
		
		// Create a DOM XMLSignatureFactory that will be used to
		// generate the enveloped signature.
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());
		
		// Create a Reference to the enveloped document (in this case,
		// you are signing the whole document, so a URI of "" signifies
		// that, and also specify the SHA1 digest algorithm and
		// the ENVELOPED Transform.
		ArrayList<Transform> transformList = new ArrayList<Transform>();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);
		
		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);
		// Create the SignedInfo.
		SignedInfo si = fac
				.newSignedInfo(fac.newCanonicalizationMethod(
						CanonicalizationMethod.INCLUSIVE,
						(C14NMethodParameterSpec) null), fac
						.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
						Collections.singletonList(ref));
		
		// Load the KeyStore and get the signing key and certificate.
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(pfxPath), password.toCharArray());
		Enumeration aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				break;
			}
		}
		
		// Original
		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(password.toCharArray()));
	    
		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List<Object> x509Content = new ArrayList<Object>();
		
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
		
		// Instantiate the document to be signed.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xmlData.getBytes()));

		// Create a DOMSignContext and specify the RSA PrivateKey and
		// location of the resulting XMLSignature's parent element.
		DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getDocumentElement());

		// Create the XMLSignature, but don't sign it yet.
		XMLSignature signature = fac.newXMLSignature(si, ki);

		// Marshal, generate, and sign the enveloped signature.
		signature.sign(dsc);

		// Output the resulting document.
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		StreamResult result = new StreamResult(os);
		DOMSource domSource = new DOMSource(doc);
		trans.transform(domSource, result);

		// Find Signature element.
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		// Create a DOMValidateContext and specify a KeySelector and document
		// context.
		DOMValidateContext valContext = new DOMValidateContext( new X509KeySelector(ks), nl.item(0));
		// Unmarshal the XMLSignature.
		XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
		// Validate the XMLSignature.
		boolean coreValidity = signatures.validate(valContext);
		// Check core validation status.
		
		if (coreValidity == false) {
			System.err.println("Falha na Assinatura!");
		} else {
			//System.out.println("Assinatura Correta!");
		}
		
		return os.toString();
	}

	public static void showCertificateProperties(LojaVO loja) throws Exception {
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new org.jcp.xml.dsig.internal.dom.XMLDSigRI());
		
		KeyStore ks = KeyStore.getInstance("PKCS12");
		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();
		
		String keystoreFile = paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja());
		String keystorePassword = paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja());
		
		ks.load(new FileInputStream(keystoreFile), keystorePassword.toCharArray());
		Enumeration<?> aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				break;
			}
		}
		
		// OriginalSystemManager.getProperty(Constants.NfeParameters.Ssl.KEYSTORE_PASSWORD)
		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(keystorePassword.toCharArray()));
	    
		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
		// Create the KeyInfo containing the X509Data.
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List<Object> x509Content = new ArrayList<Object>();
		
		cert.checkValidity();
		
		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));
		System.out.println(ki.toString());
	}
}
