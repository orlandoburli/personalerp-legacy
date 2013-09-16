package br.com.orlandoburli.core.utils.etiqueta.impressoras.zebra;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.utils.etiqueta.ItemImpressaoVO;
import br.com.orlandoburli.core.utils.etiqueta.impressoras.IModelImpressoraEtiqueta;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloEtiquetaVO;

public class ZebraTLP2844Impressora implements IModelImpressoraEtiqueta {

	@Override
	public void imprimir(List<ItemImpressaoVO> itens, ModeloEtiquetaVO modelo) {
		//
		List<String> listComandosEtiqueta = new ArrayList<String>();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			byte[] dados = Utils.decode64Bytes(modelo.getArquivoModeloEtiqueta());
			baos.write(dados);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String comandoEtiqueta = new String(baos.toString());

		for (ItemImpressaoVO item : itens) {

			for (int i = 0; i < item.getQuantidadeCopias(); i++) {
				if (comandoEtiqueta == null) {
					comandoEtiqueta = new String(baos.toString());
				}

				comandoEtiqueta = replaceStrWithVO(item.getProduto(), comandoEtiqueta);
				if (comandoEtiqueta.indexOf("[") < 0) { // Se isso acontecer,
														// acabou os espacos na
														// etiqueta
					listComandosEtiqueta.add(comandoEtiqueta);
					comandoEtiqueta = null;
				}
			}
		}

		// Adiciona o ultimo comando, se tiver adicionado alguma coisa.
		if (comandoEtiqueta != null) {
			// Remove espacos remanescentes
			while (comandoEtiqueta.indexOf("[") >= 0) {
				comandoEtiqueta = replaceStrWithVO(new ProdutoVO(), comandoEtiqueta);
			}
			listComandosEtiqueta.add(comandoEtiqueta);
		}
		
		sendPrintCommand(Constants.PORTA_LPT1, listComandosEtiqueta.toArray(new String[listComandosEtiqueta.size()]));

	}
	private String replaceStrWithVO(ProdutoVO produto, String comandoEtiqueta) {
		List<String> listComandos = new ArrayList<String>();

		int pInicio = 0;
		int pFinal = 0;

		while (true) {
			if (comandoEtiqueta.indexOf("[", pInicio) >= 0) {

				int pInicioCampo = comandoEtiqueta.indexOf("[", pInicio) + 1;

				pFinal = comandoEtiqueta.indexOf("]", pInicio);

				String campo = comandoEtiqueta.substring(pInicioCampo, pFinal);

				// Se estiver repetindo o comando, sai
				if (listComandos.indexOf(campo) >= 0) {
					break;
				}

				comandoEtiqueta = comandoEtiqueta.replaceFirst("\\[" + campo + "\\]", getStringValue(produto, campo));

				listComandos.add(campo);

			} else {
				break;
			}
		}

		return comandoEtiqueta;
	}

	private String getStringValue(Object object, String texto) {
		int posicaoInicial = -1;
		int posicaoFinal = -1;

		String[] dados = texto.split(",");

		if (dados.length >= 2) {
			try {
				posicaoInicial = Integer.parseInt(dados[1]);
			} catch (NumberFormatException e) {
				posicaoInicial = 0;
			}
		} else {
			posicaoInicial = 0;
		}

		if (dados.length >= 3) {
			try {
				posicaoFinal = Integer.parseInt(dados[2]);
			} catch (NumberFormatException e) {
				posicaoInicial = dados[0].length();
			}
		}

		String nomeCampo = dados[0];
		String retorno = "";

		try {
			Field field = object.getClass().getDeclaredField(nomeCampo);
			field.setAccessible(true);

			Object value = field.get(object);

			if (value != null) {
				if (value instanceof BigDecimal) {
					DecimalFormat df = null;
					Precision precision = field.getAnnotation(Precision.class);
					String mask = "#0.";
					if (precision != null) {
						mask += Utils.fillString(mask, "0", precision.decimals(), 2);
					} else {
						mask += "00";
					}
					df = new DecimalFormat(mask);

					retorno = df.format(value);
				} else {
					retorno = value.toString();

					if (posicaoInicial == -1) {
						posicaoInicial = 0;
					}
					if (posicaoFinal == -1 || posicaoFinal >= retorno.length()) {
						posicaoFinal = retorno.length();
					}

					try {
						retorno = retorno.substring(posicaoInicial, posicaoFinal);
					} catch (IndexOutOfBoundsException e) {
						retorno  = "";
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private void sendPrintCommand(String port, String[] commands) {
		// net use lpt1 \\pc-gamer\ZDesigner TLP 2844 /YES
		System.out.println("Iniciando Impressao");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(port);
			PrintStream ps = new PrintStream(os);

			for (String command : commands) {
				ps.print(command);
			}
			ps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finalizando Impressao");
	}
}