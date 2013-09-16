package br.com.orlandoburli.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;

import com.lowagie.text.ElementTags;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Utils {

	public static final String WEBINF_CLASSES_DIRECTORY = "/WEB-INF/classes/";
	public static final String DOT_CLASS = ".class";
	static final int TAMANHO_BUFFER = 2048; // 2kb

	public static String parseClassName(String directory, String name) {
		name = name.substring(directory.length(), name.length() - DOT_CLASS.length());
		name = name.replaceAll("/|\\\\", ".");
		name = name.replaceAll("\\.\\.", ".");
		return name;
	}

	public static String getFacadeName(String directory, String facadeName, ServletContext context) {
		String webinfdir = SystemManager.INITIAL_APP_DIRECTORY + "WEB-INF" + File.separator + "classes" + File.separator;
		List<File> files = findFiles(new File(webinfdir), facadeName);

		if (files.size() > 0) {
			return parseClassName(webinfdir, files.get(0).getAbsolutePath());
		}
		return "";
	}

	public static List<File> findFiles(File startingDirectory,
			final String pattern) {
		List<File> files = new ArrayList<File>();
		if (startingDirectory.isDirectory()) {
			File[] sub = startingDirectory.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String finalString = pathname.getName().substring(
							pathname.getName().lastIndexOf("/") + 1);
					return pathname.isDirectory()
							|| finalString.equalsIgnoreCase(pattern);
				}
			});
			for (File fileDir : sub) {
				if (fileDir.isDirectory()) {
					files.addAll(findFiles(fileDir, pattern));
				} else {
					files.add(fileDir);
				}
			}
		}
		return files;
	}

	public static String voToXml(Object vo) {
		return voToXml(vo, true);
	}

	public static String voToXml(List<?> list, int count) {
		String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
		for (Object _vo : list) {
			xmlList += Utils.voToXml(_vo, false);
		}
		xmlList += "<count>" + count + "</count>";
		xmlList += "</list>";
		return xmlList;
	}

	public static String voContentToXml(Object vo) {
		if (vo == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();

		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(vo);
				sb.append("<" + field.getName() + "><![CDATA["
						+ (value == null ? "" : value.toString()) + "]]></"
						+ field.getName() + ">");
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}

		return sb.toString();
	}

	public static String voToXml(Object vo, boolean appendXmlHeader) {
		if (vo == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if (appendXmlHeader) {
			sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		}
		sb.append("<" + vo.getClass().getSimpleName().toLowerCase() + "> ");
		
		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(vo);

				if (value instanceof IValueObject) {
					sb.append("<" + field.getName() + ">"
							+ voToXml(value, false) + "</" + field.getName()
							+ ">");
					// } else if ((value instanceof List) && (value != null)) {
					// sb.append("<" + field.getName() + ">");
					// for(Object object : (List) value) {
					// if (object instanceof IValueObject) {
					// sb.append(voToXml(object, false));
					// }
					// }
					// sb.append("</"+ field.getName() + ">");
				} else {
					sb.append("<" + field.getName() + "><![CDATA["
							+ (value == null ? "" : value.toString()) + "]]></"
							+ field.getName() + ">");
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		sb.append("</" + vo.getClass().getSimpleName().toLowerCase() + ">");

		return sb.toString();
	}

	public static String voToXml(List<?> list) {
		return voToXml(list, true);
	}

	public static String voToXml(List<?> list, boolean headerXml) {
		StringBuilder sb = new StringBuilder();
		if (headerXml) {
			sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>");
		}
		for (Object vo : list) {
			sb.append(voToXml(vo, false));
		}
		if (headerXml) {
			sb.append("</list>");
		}
		return sb.toString();
	}

	/**
	 * Monta XML so dos itens
	 * 
	 * @param vo
	 * @return
	 */
	public static String fieldsToXml(IValueObject vo) {
		StringBuilder sb = new StringBuilder();
		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(vo);

				if (value instanceof IValueObject) {
					sb.append("<" + field.getName() + ">"
							+ voToXml(value, false) + "</" + field.getName()
							+ ">");
				} else {
					sb.append("<" + field.getName() + "><![CDATA["
							+ (value == null ? "" : value.toString()) + "]]></"
							+ field.getName() + ">");
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return sb.toString();
	}

	public static String decode64(String valor) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(valor));
		} catch (IOException e) {
			return valor;
		}
	}
	
	public static byte[] decode64Bytes(String valor) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(valor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encode64(byte[] valor) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(valor);
	}

	public static String toUtf8(String valor) {
		Charset charset = Charset.forName("UTF-8");
		ByteBuffer bb = charset.encode(valor);
		return bb.toString();
	}

	public static class RemoverAcentos {
		static String acentuado = "Á«·ÈÌÛ˙˝¡…Õ”⁄›‡ËÏÚ˘¿»Ã“Ÿ„ıÒ‰ÎÔˆ¸ˇƒÀœ÷‹√’—‚ÍÓÙ˚¬ Œ‘€";
		static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
		static char[] tabela;
		static {
			tabela = new char[256];
			for (int i = 0; i < tabela.length; ++i) {
				tabela[i] = (char) i;
			}
			for (int i = 0; i < acentuado.length(); ++i) {
				tabela[acentuado.charAt(i)] = semAcento.charAt(i);
			}
		}

		public static String remover(final String s) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); ++i) {
				char ch = s.charAt(i);
				if (ch < 256) {
					sb.append(tabela[ch]);
				} else {
					sb.append(ch);
				}
			}
			return sb.toString();
		}
	}

	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		Date date = Date.valueOf(cal.get(Calendar.YEAR) + "-"
				+ fillString(cal.get(Calendar.MONTH) + 1, "0", 2, 1) + "-"
				+ fillString(cal.get(Calendar.DAY_OF_MONTH), "0", 2, 1));
		return date;
	}

	public static Timestamp getNow() {
		Calendar cal = Calendar.getInstance();
		Timestamp time = new Timestamp(cal.getTimeInMillis());
		return time;
	}

	public static Double getDiffDays(Date data1, Date data2) {
		Double milis1 = new Double(data1.getTime());
		Double milis2 = new Double(data2.getTime());
		Double dias = (milis2 - milis1) / 86400000;
		return dias;
	}

	public static Object getproperty(Object objeto, String property) {
		if (objeto == null) {
			return null;
		}
		String fieldname = property;
		Object objetoaux = objeto;
		int index = property.indexOf('.');
		if (index >= 0) {
			fieldname = property.substring(0, index);
		}
		Object retorno = null;
		try {
			Field[] fields = objetoaux.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldname)) {
					field.setAccessible(true);
					retorno = field.get(objetoaux);

					if (index >= 0) {
						retorno = getproperty(retorno, property
								.substring(index + 1));
					}
					return retorno;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static String mapValues(Object object, String property) {
		String _retorno = property;
		String fieldname_old = "";
		if (object == null) {
			// return property;
		}
		if (_retorno != null) {
			while (_retorno.indexOf('{') >= 0) {
				int inicio = _retorno.indexOf('{') + 1;
				int fim = _retorno.indexOf('}');
				String fieldname = _retorno.substring(inicio, fim);
				if (fieldname.equals(fieldname_old)) {
					_retorno = _retorno.replace("{" + fieldname + "}", "");
				} else {
					Object retorno = Utils.getproperty(object, fieldname);
					if (retorno != null) {
						String newstring = retorno.toString();
						_retorno = _retorno.replace("{" + fieldname + "}",
								newstring);
					} else {
						_retorno = _retorno.replace("{" + fieldname + "}", "");
					}
				}
				fieldname_old = fieldname;
			}
		}
		return _retorno;
	}

	public static double round(double value, int decimals) {
		double aux = value * Math.pow(10, decimals);
		aux = Math.round(aux);
		return aux / Math.pow(10, decimals);
	}

	public static String getDoubleToString(Double valor, int decimais) {
		BigDecimal valorCerto = new BigDecimal(valor.toString());
		int multiplicador = Double.valueOf((Math.pow(10, decimais))).intValue();
		valorCerto = valorCerto.multiply(new BigDecimal(multiplicador));
		return fillString(Integer.toString(valorCerto.intValue()), "0",
				decimais, 1);
	}

	/**
	 * 
	 * @param value
	 * @param fillWith
	 * @param size
	 * @param direction
	 *            1=Esquerda 2=Direita
	 * @return
	 */
	public static String fillString(Object ovalue, String fillWith, int size,
			int direction) {
		String value = ovalue.toString();
		// Checa se Linha a preencher È nula ou branco
		if (value == null || value.trim() == "") {
			value = "";
		}
		// if (value.length() > size) {
		// value = value.substring(0, size);
		// return value;
		// }
		// Enquanto Linha a preencher possuir 2 espaÁos em branco seguidos,
		// substitui por 1 espaÁo apenas
		while (value.contains("  ")) {
			value = value.replaceAll("  ", " ").trim();
		}
		// Retira caracteres estranhos
		value = value.replaceAll("[./-]", "");
		StringBuffer sb = new StringBuffer(value);
		if (direction == 1) { // a Esquerda
			for (int i = sb.length(); i < size; i++) {
				sb.insert(0, fillWith);
			}
		} else if (direction == 2) {// a Direita
			for (int i = sb.length(); i < size; i++) {
				sb.append(fillWith);
			}
		}
		return sb.toString();
	}

	public static void setNullAsEmpty(IValueObject vo) {
		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.getType().equals(String.class)) {
					if (field.get(vo) == null) {
						field.set(vo, "");
					}
				} else if (field.getType().equals(Integer.class)) {
					// if (field.get(vo) == null) {
					// field.set(vo, 0);
					// }
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}

	public static void setNullAsZero(IValueObject vo) {
		Field[] fields = vo.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.getType().equals(Integer.class)) {
					if (field.get(vo) == null) {
						field.set(vo, 0);
					}
				} else if (field.getType().equals(Double.class)) {
					if (field.get(vo) == null) {
						field.set(vo, 0.0);
					}
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}

	public static void compactar(String arqSaida, List<String> arquivos) {
		int i, cont;
		byte[] dados = new byte[TAMANHO_BUFFER];
		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		FileOutputStream destino = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;
		try {
			destino = new FileOutputStream(arqSaida);
			saida = new ZipOutputStream(new BufferedOutputStream(destino));
			for (i = 0; i < arquivos.size(); i++) {
				File arquivo = new File(arquivos.get(i));
				if (arquivo.isFile() && !(arquivo.getName()).equals(arqSaida)) {
					streamDeEntrada = new FileInputStream(arquivo);
					origem = new BufferedInputStream(streamDeEntrada,
							TAMANHO_BUFFER);
					entry = new ZipEntry(arquivos.get(i).substring(
							arquivos.get(i).lastIndexOf(File.separator) + 1));
					saida.putNextEntry(entry);

					while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
						saida.write(dados, 0, cont);
					}
					origem.close();
				}
			}
			saida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// fim compactar()

	public static void deletarArquivos(List<String> arquivos) {
		for (String arq : arquivos) {
			File f = new File(arq);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static Calendar dateToCalendar(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date calendarToDate(Calendar calendar) {
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}
	
	public static void setUsuarioLog(IValueObject vo, UsuarioVO usuarioSessao) {
		if (vo == null || usuarioSessao == null) {
			return;
		}
		
		vo.setCodigoEmpresaUsuarioLog(usuarioSessao.getCodigoEmpresa());
		vo.setCodigoLojaUsuarioLog(usuarioSessao.getCodigoLoja());
		vo.setCodigoUsuarioLog(usuarioSessao.getCodigoUsuario());
	}
	
	public static String corrigeHtml(String texto) {
		String texto2 = texto;
		
		texto2 = texto2.replace("<strong>", "<b>");
		texto2 = texto2.replace("</strong>", "</b>");
		
		texto2 = texto2.replace("<em>", "<i>");
		texto2 = texto2.replace("</em>", "</i>");
		
		return texto2;
	}

	public static String makeHtml(String texto) {
		
		String texto2 = texto;
		texto2 = texto2.replace("<b>", "[b]");
		texto2 = texto2.replace("</b>", "[/b]");
		texto2 = texto2.replace("<B>", "[b]");
		texto2 = texto2.replace("</B>", "[/b]");
		
		texto2 = texto2.replace("<i>", "[i]");
		texto2 = texto2.replace("</i>", "[/i]");
		texto2 = texto2.replace("<I>", "[i]");
		texto2 = texto2.replace("</I>", "[/i]");
		
		texto2 = texto2.replace("<FONT", "<font");
		texto2 = texto2.replace("</FONT>", "</font>");
		texto2 = texto2.replace("<P", "<p");
		texto2 = texto2.replace("</P>", "</p>");
		
		texto2 = texto2.replace("SIZE=\"", "size=\"");
		texto2 = texto2.replace("COLOR=\"", "color=\"");
		texto2 = texto2.replace("FACE=\"", "face=\"");
		texto2 = texto2.replace("LETTERSPACING=\"", "letterspacing=\"");
		
		texto2 = texto2.replace("ALIGN=\"", "align=\"");
		texto2 = texto2.replace("LETTERSPACING=\"", "letterspacing=\"");
		
		texto2 = texto2.replace("<TEXTFORMAT LEADING=\"2\">", "");
		texto2 = texto2.replace("</TEXTFORMAT>", "");
		
		String styleFont = ""; //"font-family: Arial; font-size: 8pt;";
		
		// O strong e mais complicado...
		texto2 = replaceStrong(texto2);
		
		List<Paragrafo> paragrafos = breakLines(texto2);
		// Remove todas as tags html restantes
		for (int i = 0; i < paragrafos.size(); i++) {
			paragrafos.get(i).setTexto(paragrafos.get(i).getTexto().replaceAll("<[^>]*>", ""));
			paragrafos.get(i).setTexto(paragrafos.get(i).getTexto().replace("[b]", "<b>"));
			paragrafos.get(i).setTexto(paragrafos.get(i).getTexto().replace("[/b]", "</b>"));
			paragrafos.get(i).setTexto(paragrafos.get(i).getTexto().replace("[i]", "<i>"));
			paragrafos.get(i).setTexto(paragrafos.get(i).getTexto().replace("[/i]", "</i>"));
		}
		
		String textoFinal = "";
		
		boolean branco = false;
		
		for (Paragrafo paragrafo : paragrafos) {
			String aux =  paragrafo.getTexto().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", " ");
			if (aux.length() > 0) {
				textoFinal += "\n<p style=\"text-align:" + paragrafo.getAlinhamento() + "; " + styleFont + " \">" + paragrafo.getTexto() + "</p>";
				branco = false;
			} else {
				if (!branco) {
					branco = true;
					textoFinal += "\n<br/>";
				}
			}
		}
		
		return textoFinal;
	}

	public static String replaceStrong(String source) {
		String copyofsource = source.toString();

		while (copyofsource.indexOf("<strong") >= 0) {
			int begin = copyofsource.indexOf("<strong");
			int end = copyofsource.indexOf(">", begin);
			copyofsource = copyofsource.substring(0, begin) + "[b]"
					+ copyofsource.substring(end + 1);
		}
		copyofsource = copyofsource.replace("</strong>", "[/b]");
		return copyofsource;
	}

	public static List<Paragrafo> breakLines(String source) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Paragrafo> listPar = new ArrayList<Paragrafo>();

		String copyofsource = source.toString();

		// Se o <p> nao for o 1 caracter...
		if (copyofsource.indexOf("<p") > 0) {
			int begin = copyofsource.indexOf("<p");

			list.add(copyofsource.substring(0, begin));
			listPar.add(new Paragrafo(copyofsource.substring(0, begin), ""));

			copyofsource = copyofsource.substring(begin);
		}

		String alinhamento = "";

		while (copyofsource.indexOf("<p") >= 0) {
			int begin = copyofsource.indexOf("<p");
			int end = copyofsource.indexOf(">", begin);

			alinhamento = "";

			// Busca alinhamento
			String aux = copyofsource.substring(begin, end);
			if (aux.indexOf("justify") > 0 || aux.indexOf("JUSTIFY") > 0) {
				alinhamento = ElementTags.ALIGN_JUSTIFIED.toLowerCase();
			} else if (aux.indexOf("center") > 0 || aux.indexOf("CENTER") > 0) {
				alinhamento = ElementTags.ALIGN_CENTER.toLowerCase();
			} else if (aux.indexOf("left") > 0 || aux.indexOf("LEFT") > 0) {
				alinhamento = ElementTags.ALIGN_LEFT.toLowerCase();
			} else if (aux.indexOf("right") > 0 || aux.indexOf("RIGHT") > 0) {
				alinhamento = ElementTags.ALIGN_RIGHT.toLowerCase();
			} else {
				alinhamento = ElementTags.ALIGN_JUSTIFIED.toLowerCase();
			}

			int endOfTag = copyofsource.indexOf("</p>", end + 1);

			list.add(copyofsource.substring(end + 1, endOfTag));
			listPar.add(new Paragrafo(
					copyofsource.substring(end + 1, endOfTag), alinhamento));

			copyofsource = copyofsource.substring(endOfTag + 4);
		}
		// Ultima parte
		if (copyofsource.trim().length() > 0) {
			list.add(copyofsource.trim());
			listPar.add(new Paragrafo(copyofsource.trim(), ""));
		}
		String[] out = new String[list.size()];
		int i = 0;
		for (String s : list) {
			out[i] = s;
			i++;
		}
		return listPar;
	}

	public static String filtro(String valor, String valoresPermitidos) {
		if (valor == null || valoresPermitidos == null) {
			return null;
		}
		String novoValor = "";
		for (int i = 0; i < valor.length(); i++) {
			if (valoresPermitidos.indexOf(valor.substring(i, i + 1)) >= 0) {
				novoValor += valor.substring(i, i + 1);
			}
		}
		return novoValor;
	}
}

class Paragrafo {
	private String texto;
	private String alinhamento;

	public Paragrafo(String texto, String alinhamento) {
		setTexto(texto);
		setAlinhamento(alinhamento);
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setAlinhamento(String alinhamento) {
		this.alinhamento = alinhamento;
	}

	public String getAlinhamento() {
		return alinhamento;
	}
}