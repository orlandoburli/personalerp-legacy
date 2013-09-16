package br.com.orlandoburli.core.extras.nfe.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.security.Security;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.INumberFormat;
import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlIgnore;
import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlNamespace;
import br.com.orlandoburli.core.extras.nfe.interfaces.XmlAtributte;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.extras.nfe.model.NfeProc;
import br.com.orlandoburli.core.extras.nfe.model.cobranca.Dup;
import br.com.orlandoburli.core.extras.nfe.model.details.Det;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi.Ipi;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public final class NfeUtils {

	public static String NfeToXml(Object nfe) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>");
		sb.append("<" + nfe.getClass().getSimpleName() + " xmlns=\"http://www.portalfiscal.inf.br/nfe\">");

		// Loop dos campos...
		Field[] fields = nfe.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);

			if (!Modifier.isStatic(field.getModifiers()) && field.getAnnotation(IXmlIgnore.class) == null) {

				Object retorno = field.get(nfe);

				if (retorno != null) {
					String attributeName = field.getName();

					XmlAtributte xmlAtt = field.getAnnotation(XmlAtributte.class);
					if (xmlAtt != null) {
						attributeName = xmlAtt.value();
					}

					sb.append("<" + attributeName);

					if (retorno instanceof IXmlNamespace) {
						sb.append(((IXmlNamespace) retorno).getNamespaceDeclaration());
					}

					sb.append(">");

					if (retorno.getClass().equals(String.class)) {
						sb.append(retorno.toString());
					} else if (retorno instanceof List<?>) {

					} else {
						sb.append(objectToXml(retorno));
					}

					sb.append("</" + attributeName + ">");
				}
			}
		}
		sb.append("</" + nfe.getClass().getSimpleName() + ">");
		return sb.toString();
	}

	public static String objectToXml(Object xObject) throws Exception {
		StringBuilder sb = new StringBuilder();

		// Loop dos campos...
		Field[] fields = xObject.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if (!Modifier.isStatic(field.getModifiers()) && field.getAnnotation(IXmlIgnore.class) == null) {

				Object retorno = field.get(xObject);

				if (retorno != null) {

					String attributeName = field.getName();

					XmlAtributte xmlAtt = field.getAnnotation(XmlAtributte.class);
					if (xmlAtt != null) {
						attributeName = xmlAtt.value();
					}

					if (retorno.getClass().equals(String.class)) {
						sb.append("<" + attributeName);

						if (retorno instanceof IXmlNamespace) {
							sb.append(((IXmlNamespace) retorno).getNamespaceDeclaration());
						}

						sb.append(">");

						sb.append(retorno.toString());

						sb.append("</" + attributeName + ">");

					} else if (retorno instanceof BigDecimal) {
						sb.append("<" + attributeName);
						sb.append(">");

						BigDecimal valor = (BigDecimal) retorno;

						// Verifica se tem a anotacao de formatacao de numeros
						INumberFormat number = field.getAnnotation(INumberFormat.class);

						if (number != null) {
							DecimalFormat f = new DecimalFormat(number.maskNumber());

							DecimalFormatSymbols symbols = new DecimalFormatSymbols();
							symbols.setGroupingSeparator(',');
							symbols.setDecimalSeparator('.');

							f.setDecimalFormatSymbols(symbols);
							sb.append(f.format(valor));
						} else {
							sb.append(valor.toString());
						}

						// sb.append(valor.toString());

						sb.append("</" + attributeName + ">");

					} else if (retorno instanceof List<?>) {

						// Tratamento para List

						List<?> list = (List<?>) retorno;
						for (Object item : list) {
							if (item != null) {
								String strItem = objectToXml(item);
								if (strItem != null && !strItem.trim().equals("")) {
									sb.append("<" + attributeName);
									if (item instanceof IXmlNamespace) {
										sb.append(((IXmlNamespace) item).getNamespaceDeclaration());
									}

									sb.append(">");

									sb.append(strItem);

									sb.append("</" + attributeName + ">");
								}
							}
						}
					} else {
						sb.append("<" + attributeName);

						if (retorno instanceof IXmlNamespace) {
							sb.append(((IXmlNamespace) retorno).getNamespaceDeclaration());
						}

						sb.append(">");

						sb.append(objectToXml(retorno));
						sb.append("</" + attributeName + ">");
					}

				}
			}
		}

		return sb.toString();
	}

	/**
	 * Valida o xml da nf-e 2.0 passado o caminho dos schemas
	 * 
	 * @param xml
	 * @param xsdPath
	 * @return
	 */
	public static boolean validateXmlNfe(String xml, String xsdPath) throws SAXException, ParserConfigurationException, IOException {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		final String nfe200 = xsdPath + "\\nfe_v2.00.xsd";
		final String leiauteNfe200 = xsdPath + "\\leiauteNFe_v2.00.xsd";
		final String tiposBasicos103 = xsdPath + "\\tiposBasico_v1.03.xsd";

		StreamSource s1 = new StreamSource(nfe200);
		StreamSource s2 = new StreamSource(leiauteNfe200);
		StreamSource s3 = new StreamSource(tiposBasicos103);

		javax.xml.validation.Schema schema = factory.newSchema(new Source[] { s3, s2, s1 });
		javax.xml.validation.Validator validator = schema.newValidator();

		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes());

		Document document = parser.parse(input);
		DOMResult result = new DOMResult();
		validator.validate(new DOMSource(document), result);

		return true;
	}

	/**
	 * Compoe a chave da NF-e
	 * 
	 * @param nfe
	 *            Nota fiscal a ser calculada
	 * @return Atributo Id da NF-e
	 */
	public static String buildNfeKey(NFe nfe) {
		StringBuilder sb = new StringBuilder();
		sb.append(nfe.getInfNfe().getIde().getcUF()); // Codigo da UF

		Date date = Date.valueOf(nfe.getInfNfe().getIde().getdEmi());

		sb.append(new SimpleDateFormat("yyMM").format(date)); // AA e MM da
																// emissao
		sb.append(nfe.getInfNfe().getEmit().getCNPJ()); // CNPJ do emitente
		sb.append(nfe.getInfNfe().getIde().getMod()); // Modelo da Nf-e

		sb.append(preencheCom(nfe.getInfNfe().getIde().getSerie().trim(), "0", 3, 1)); // Série
																						// da
																						// Nf-e
		sb.append(NfeUtils.preencheCom(nfe.getInfNfe().getIde().getnNF().trim(), "0", 9, 1)); // Numero
																								// da
																								// Nf-e
		sb.append(nfe.getInfNfe().getIde().getTpEmis()); // Forma de emissao da
															// Nf-e
		sb.append(NfeUtils.preencheCom(nfe.getInfNfe().getIde().getcNF().trim(), "0", 8, 1)); // Codigo
																								// numerico
																								// (aleatorio)
																								// da
																								// Nf-e
		sb.append(modulo11(sb.toString())); // Digito verificador

		return sb.toString();
	}

	public static int modulo11(String chave) {
		int[] pesos = { 4, 3, 2, 9, 8, 7, 6, 5 };
		int posicaoPeso = 0;
		int somaPonderada = 0;
		for (int i = 0; i < chave.length(); i++) {
			somaPonderada += pesos[posicaoPeso] * (Integer.parseInt(chave.substring(i, i + 1)));
			posicaoPeso++;
			if (posicaoPeso >= pesos.length) {
				posicaoPeso = 0;
			}
		}
		int resto = somaPonderada % 11;
		int digito = -1;
		if (resto == 0 || resto == 1) {
			digito = 0;
		} else {
			digito = 11 - resto;
		}

		return digito;
	}

	public static String FillInXml(String mensagem, String tag) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<" + tag + ">");
		sb.append("<mensagem>");
		sb.append(mensagem);
		sb.append("</mensagem>");
		sb.append("</" + tag + ">");
		return sb.toString();
	}

	public static String getXmlContent(String xmlFile) {
		String retorno = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(xmlFile));
			String aux = null;
			while ((aux = reader.readLine()) != null) {
				retorno += aux;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static String geraLote(String NFe, int Lote) {
		try {
			// DocumentBuilderFactory factory = DocumentBuilderFactory
			// .newInstance();
			// factory.setNamespaceAware(false);
			// // Document doc = factory.newDocumentBuilder().newDocument();
			// Document docnfe = factory.newDocumentBuilder().parse(
			// new ByteArrayInputStream(NFe.getBytes()));
			//
			// ByteArrayOutputStream os = new ByteArrayOutputStream();
			// TransformerFactory tf = TransformerFactory.newInstance();
			// Transformer trans = tf.newTransformer();
			// trans.transform(new DOMSource(docnfe), new StreamResult(os));

			//String newNfe = NFe.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
			//newNfe = newNfe.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			//<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>
			String cabecalho = "<enviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"2.00\"><idLote>" + preencheCom(Integer.toString(Lote), "0", 10, 1) + "</idLote>";
			String rodape = "</enviNFe>";
			return cabecalho + NFe + rodape;
		} catch (Exception ex) {
			return null;
		}
	}

	public static String preencheCom(String linha, String character, int tamanho, int direcao) {
		// Checa se Linha a preencher é nula ou branco
		if (linha == null || linha.trim() == "") {
			linha = "";
		}
		// Enquanto Linha a preencher possuir 2 espaços em branco seguidos,
		// substitui por 1 espaço apenas
		while (linha.contains("  ")) {
			linha = linha.replaceAll("  ", " ").trim();
		}
		// Retira caracteres estranhos
		linha = linha.replaceAll("[./-]", "");
		StringBuffer sb = new StringBuffer(linha);
		if (direcao == 1) { // a Esquerda
			for (int i = sb.length(); i < tamanho; i++) {
				sb.insert(0, character);
			}
		} else if (direcao == 2) {// a Direita
			for (int i = sb.length(); i < tamanho; i++) {
				sb.append(character);
			}
		}
		return sb.toString();
	}

	public static void InitializeSSL(boolean debug, LojaVO loja) throws SQLException {

		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		ParametroLojaDAO paramLojaDao = new ParametroLojaDAO();

		System.setProperty("javax.net.ssl.keyStore", paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja())); // SystemManager.getProperty(Constants.NfeParameters.Ssl.KEYSTORE_FILE));
		System.setProperty("javax.net.ssl.keyStorePassword", paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja()));// SystemManager.getProperty(Constants.NfeParameters.Ssl.KEYSTORE_PASSWORD));
		System.setProperty("javax.net.ssl.keyStoreType", paramLojaDao.getStringParametro(Constants.Parameters.NFe.KEYSTORE_TYPE, loja.getCodigoEmpresa(), loja.getCodigoLoja()));// SystemManager.getProperty(Constants.NfeParameters.Ssl.KEYSTORE_TYPE));

		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", paramLojaDao.getStringParametro(Constants.Parameters.NFe.TRUSTSTORE_FILE, loja.getCodigoEmpresa(), loja.getCodigoLoja()));// SystemManager.getProperty(Constants.NfeParameters.Ssl.TRUSTSTORE_FILE));
		System.setProperty("javax.net.ssl.trustStorePassword", paramLojaDao.getStringParametro(Constants.Parameters.NFe.TRUSTSTORE_PASSWORD, loja.getCodigoEmpresa(), loja.getCodigoLoja()));// SystemManager.getProperty(Constants.NfeParameters.Ssl.TRUSTSTORE_PASSWORD));
		System.setProperty("javax.net.ssl.trustStoreProvider", "SUN");

		if (debug) {
			System.setProperty("javax.net.debug", "all");
		}
	}

	public static String readXmlTag(String xml, String tag) {
		return xml.substring(xml.indexOf("<" + tag + ">") + tag.length() + 2, xml.indexOf("</" + tag + ">"));
	}

	/**
	 * Remove a tag <?xml do xml.
	 * 
	 * @param xml
	 *            Xml para remoção da tag
	 * @return Xml sem a tag
	 */
	public static String removeXmlTag(String xml) {
		if (xml.indexOf("?>") >= 0) {
			return xml.substring(xml.indexOf("?>") + 2);
		} else {
			return xml;
		}
	}

	public static String readXmlTag(String xml, String tagPai, String tag) {
		String xmlPai = xml.substring(xml.indexOf("<" + tagPai + ">") + tagPai.length() + 2, xml.indexOf("</" + tagPai + ">"));
		return xmlPai.substring(xmlPai.indexOf("<" + tag + ">") + tag.length() + 2, xmlPai.indexOf("</" + tag + ">"));
	}

	public static void salvaArquivo(String path, String xmlAssinado) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			fos.write(xmlAssinado.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static NfeProc XmlToNfe(String xmlData) {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xmlData.getBytes());
			Document doc = builder.parse(is);

			// Elementos
			Element nfeProcElement = (Element) doc.getElementsByTagName("nfeProc").item(0);
			Element nfeElement = (Element) nfeProcElement.getElementsByTagName("NFe").item(0);
			Element infNfeElement = (Element) nfeElement.getElementsByTagName("infNFe").item(0);

			Element ideElement = (Element) infNfeElement.getElementsByTagName("ide").item(0);

			Element emitElement = (Element) infNfeElement.getElementsByTagName("emit").item(0);
			Element enderEmitElement = (Element) emitElement.getElementsByTagName("enderEmit").item(0);

			Element destElement = (Element) infNfeElement.getElementsByTagName("dest").item(0);
			Element enderDestElement = (Element) destElement.getElementsByTagName("enderDest").item(0);

			Element entregaElement = (Element) infNfeElement.getElementsByTagName("entrega").item(0);

			Element totalElement = (Element) infNfeElement.getElementsByTagName("total").item(0);
			Element icmsTotalElement = (Element) totalElement.getElementsByTagName("ICMSTot").item(0);

			Element transpElement = (Element) infNfeElement.getElementsByTagName("transp").item(0);
			Element transportaElement = null;
			if (transpElement != null) {
				transportaElement = (Element) transpElement.getElementsByTagName("transporta").item(0);
			}

			Element cobrElement = (Element) infNfeElement.getElementsByTagName("cobr").item(0);
			NodeList nodeListDup = null;
			if (cobrElement != null) {
				nodeListDup = cobrElement.getElementsByTagName("dup");
			}

			NfeProc nfeProc = new NfeProc();
			nfeProc.setVersao(nfeProcElement.getAttribute("versao"));

			nfeProc.getNFe().getInfNfe().setId(infNfeElement.getAttribute("Id"));

			// Dados da tag ide (identificacao)
			nfeProc.getNFe().getInfNfe().getIde().setcUF(ideElement.getElementsByTagName("cUF").item(0).getTextContent());
			nfeProc.getNFe().getInfNfe().getIde().setcNF(ideElement.getElementsByTagName("cNF").item(0).getTextContent());
			nodeListToObject(ideElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getIde());

			// Dados da tag emit (Emitente)
			nodeListToObject(emitElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getEmit());
			// Dados da tag enderEmit (Endereco do emitente)
			nodeListToObject(enderEmitElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getEmit().getEnderEmit());
			// Dados da tag dest (Destinatario)
			if (destElement != null) {
				nodeListToObject(destElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getDest());
			}
			// Dados da tag enderDest (Endereco do Destinatario)
			nodeListToObject(enderDestElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getDest().getEnderDest());
			// Dados da tag entrega (Endereco de entrega)
			if (entregaElement != null) {
				nodeListToObject(entregaElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getEntrega());
			}

			// Dados da tag total
			nodeListToObject(icmsTotalElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getTotal().getICMSTot());

			// Dados da tag transportadora
			if (transpElement != null) {
				nodeListToObject(transpElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getTransp());
				if (transportaElement != null) {
					nodeListToObject(transportaElement.getChildNodes(), nfeProc.getNFe().getInfNfe().getTransp().getTransporta());
				}
			}

			// Dados da tag cob (Cobranca)
			if (nodeListDup != null) {
				for (int i = 0; i < nodeListDup.getLength(); i++) {
					Element dupElement = (Element) nodeListDup.item(i);
					Dup dup = new Dup();
					nodeListToObject(dupElement.getChildNodes(), dup);
					nfeProc.getNFe().getInfNfe().getCobr().getDup().add(dup);
				}
			}

			// Itens
			NodeList nodeListDet = infNfeElement.getElementsByTagName("det");
			for (int i = 0; i < nodeListDet.getLength(); i++) {
				Det det = Det.getInstance(nfeProc.getNFe());

				Element detElement = (Element) nodeListDet.item(i);
				Element prodElement = (Element) detElement.getElementsByTagName("prod").item(0);
				Element impostoElement = (Element) detElement.getElementsByTagName("imposto").item(0);

				// ICMS
				Element icmsElement = (Element) impostoElement.getElementsByTagName("ICMS").item(0);
				Element icms00Element = (Element) icmsElement.getElementsByTagName("ICMS00").item(0);
				Element icms10Element = (Element) icmsElement.getElementsByTagName("ICMS10").item(0);
				Element icms20Element = (Element) icmsElement.getElementsByTagName("ICMS20").item(0);
				Element icms30Element = (Element) icmsElement.getElementsByTagName("ICMS30").item(0);
				Element icms40Element = (Element) icmsElement.getElementsByTagName("ICMS40").item(0);
				Element icms41Element = (Element) icmsElement.getElementsByTagName("ICMS41").item(0);
				Element icms50Element = (Element) icmsElement.getElementsByTagName("ICMS50").item(0);

				// IPI
				Element ipiElement = (Element) impostoElement.getElementsByTagName("IPI").item(0);
				Element ipiTribElement = null;
				Element ipiNtElement = null;
				if (ipiElement != null) {
					ipiTribElement = (Element) ipiElement.getElementsByTagName("IPITrib").item(0);
					ipiNtElement = (Element) ipiElement.getElementsByTagName("IPINT").item(0);
				}

				// PIS
				Element pisElement = (Element) impostoElement.getElementsByTagName("PIS").item(0);
				Element pisAliqElement = null;
				Element pisNTElement = null;
				Element pisOutrElement = null;
				Element pisQtdeElement = null;

				if (pisElement != null) {
					pisAliqElement = (Element) pisElement.getElementsByTagName("PISAliq").item(0);
					pisQtdeElement = (Element) pisElement.getElementsByTagName("PISQtde").item(0);
					pisNTElement = (Element) pisElement.getElementsByTagName("PISNT").item(0);
					pisOutrElement = (Element) pisElement.getElementsByTagName("PISOutr").item(0);
				}

				// COFINS
				// TODO
				Element cofinsElement = (Element) impostoElement.getElementsByTagName("COFINS").item(0);
				Element cofinsAliqElement = null;
				Element cofinsNTElement = null;
				Element cofinsQtdeElement = null;
				Element cofinsOutrElement = null;

				if (cofinsElement != null) {
					cofinsAliqElement = (Element) cofinsElement.getElementsByTagName("COFINSAliq").item(0);
					cofinsNTElement = (Element) cofinsElement.getElementsByTagName("COFINSNT").item(0);
					cofinsQtdeElement = (Element) cofinsElement.getElementsByTagName("COFINSQtde").item(0);
					cofinsOutrElement = (Element) cofinsElement.getElementsByTagName("COFINSOutr").item(0);
				}

				// Dados da tag prod
				nodeListToObject(prodElement.getChildNodes(), det.getProd());

				// Dados da tag Icms
				if (icms00Element != null) {
					det.getImposto().getICMS().setCSTICMS("00");
					nodeListToObject(icms00Element.getChildNodes(), det.getImposto().getICMS().getICMS00());
				} else if (icms10Element != null) {
					det.getImposto().getICMS().setCSTICMS("10");
					nodeListToObject(icms10Element.getChildNodes(), det.getImposto().getICMS().getICMS10());
				} else if (icms20Element != null) {
					det.getImposto().getICMS().setCSTICMS("20");
					nodeListToObject(icms20Element.getChildNodes(), det.getImposto().getICMS().getICMS20());
				} else if (icms30Element != null) {
					det.getImposto().getICMS().setCSTICMS("30");
					nodeListToObject(icms30Element.getChildNodes(), det.getImposto().getICMS().getICMS30());
				} else if (icms40Element != null) {
					det.getImposto().getICMS().setCSTICMS("40");
					nodeListToObject(icms40Element.getChildNodes(), det.getImposto().getICMS().getICMS40());
				} else if (icms41Element != null) {
					det.getImposto().getICMS().setCSTICMS("41");
					nodeListToObject(icms41Element.getChildNodes(), det.getImposto().getICMS().getICMS41());
				} else if (icms50Element != null) {
					det.getImposto().getICMS().setCSTICMS("50");
					nodeListToObject(icms50Element.getChildNodes(), det.getImposto().getICMS().getICMS50());
				}

				// Dados da tag IPI
				if (ipiElement != null) {
					if (ipiTribElement != null) {
						det.getImposto().setIPI(Ipi.getIPITribInstance());
						nodeListToObject(ipiElement.getChildNodes(), det.getImposto().getIPI());
						nodeListToObject(ipiTribElement.getChildNodes(), det.getImposto().getIPI().getIPITrib());
					} else if (ipiNtElement != null) {
						det.getImposto().setIPI(Ipi.getIPINTInstance());
						nodeListToObject(ipiElement.getChildNodes(), det.getImposto().getIPI());
						nodeListToObject(ipiNtElement.getChildNodes(), det.getImposto().getIPI().getIPINT());
					}
				}

				// Dados da tag PIS
				if (pisElement != null) {
					nodeListToObject(pisElement.getChildNodes(), det.getImposto().getPIS());

					if (pisAliqElement != null) {
						Element cstElement = (Element) pisAliqElement.getElementsByTagName("CST").item(0);
						det.getImposto().getPIS().setCSTPIS(cstElement.getTextContent());

						nodeListToObject(pisAliqElement.getChildNodes(), det.getImposto().getPIS().getPISAliq());
					} else if (pisNTElement != null) {
						Element cstElement = (Element) pisNTElement.getElementsByTagName("CST").item(0);
						det.getImposto().getPIS().setCSTPIS(cstElement.getTextContent());

						nodeListToObject(pisNTElement.getChildNodes(), det.getImposto().getPIS().getPISNT());
					} else if (pisQtdeElement != null) {
						Element cstElement = (Element) pisQtdeElement.getElementsByTagName("CST").item(0);
						det.getImposto().getPIS().setCSTPIS(cstElement.getTextContent());

						nodeListToObject(pisQtdeElement.getChildNodes(), det.getImposto().getPIS().getPISQtde());
					} else if (pisOutrElement != null) {
						Element cstElement = (Element) pisOutrElement.getElementsByTagName("CST").item(0);
						det.getImposto().getPIS().setCSTPIS(cstElement.getTextContent());

						nodeListToObject(pisOutrElement.getChildNodes(), det.getImposto().getPIS().getPISOutr());
					}
				}

				// Dados da tag COFINS
				if (cofinsElement != null) {
					nodeListToObject(cofinsElement.getChildNodes(), det.getImposto().getCOFINS());
					if (cofinsAliqElement != null) {
						Element cstElement = (Element) cofinsAliqElement.getElementsByTagName("CST").item(0);
						det.getImposto().getCOFINS().setCSTCOFINS(cstElement.getTextContent());

						nodeListToObject(cofinsAliqElement.getChildNodes(), det.getImposto().getCOFINS().getCOFINSAliq());
					} else if (cofinsNTElement != null) {
						Element cstElement = (Element) cofinsNTElement.getElementsByTagName("CST").item(0);
						det.getImposto().getCOFINS().setCSTCOFINS(cstElement.getTextContent());

						nodeListToObject(cofinsNTElement.getChildNodes(), det.getImposto().getCOFINS().getCOFINSNT());
					} else if (cofinsQtdeElement != null) {
						Element cstElement = (Element) cofinsQtdeElement.getElementsByTagName("CST").item(0);
						det.getImposto().getCOFINS().setCSTCOFINS(cstElement.getTextContent());

						nodeListToObject(cofinsQtdeElement.getChildNodes(), det.getImposto().getCOFINS().getCOFINSQtde());
					} else if (cofinsOutrElement != null) {
						Element cstElement = (Element) cofinsOutrElement.getElementsByTagName("CST").item(0);
						det.getImposto().getCOFINS().setCSTCOFINS(cstElement.getTextContent());

						nodeListToObject(cofinsOutrElement.getChildNodes(), det.getImposto().getCOFINS().getCOFINSOutr());
					}
				}
			}

			return nfeProc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void nodeListToObject(NodeList nList, Object object) {

		for (int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);

			if (node.getTextContent() != null) {
				try {
					node.getNodeName();

					Field f = object.getClass().getDeclaredField(node.getNodeName());
					f.setAccessible(true);

					if (f.getType().equals(String.class)) {
						f.set(object, node.getTextContent());
					} else if (f.getType().equals(Integer.class)) {
						f.set(object, Integer.parseInt(node.getTextContent()));
					} else if (f.getType().equals(BigDecimal.class)) {
						f.set(object, new BigDecimal(node.getTextContent()));
					}

				} catch (NumberFormatException e) {
					// e.printStackTrace();
				} catch (SecurityException e) {
					// e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// e.printStackTrace();
				} catch (DOMException e) {
					// e.printStackTrace();
				} catch (IllegalAccessException e) {
					// e.printStackTrace();
				}
			}
		}
	}
}