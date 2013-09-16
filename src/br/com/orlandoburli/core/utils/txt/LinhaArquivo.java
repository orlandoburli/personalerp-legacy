package br.com.orlandoburli.core.utils.txt;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LinhaArquivo {

	private String linha;

	public LinhaArquivo(String linha) {
		this.linha = linha;
	}

	protected String getLinha() {
		return linha;
	}

	protected String getCampo(CampoArquivoTexto campo) {

		String dado = null;

		try {
			dado = linha.substring(campo.getPosicaoInicial(), campo.getPosicaoFinal()).trim();
		} catch (IndexOutOfBoundsException e) {
		}
		return dado;
	}

	protected Integer getCampoInt(CampoArquivoTexto campo) {
		String dado = getCampo(campo);

		try {
			return Integer.parseInt(dado);
		} catch (NumberFormatException e) {} catch (NullPointerException e) {}
		return null;
	}

	protected BigDecimal getCampoDec(CampoArquivoTexto campo) {
		Integer dado = getCampoInt(campo);

		if (dado != null) {
			BigDecimal decimal = new BigDecimal(dado);

			if (campo.getPrecisao() != null && campo.getPrecisao() > 0) {
				BigDecimal divisor = BigDecimal.TEN.pow(campo.getPrecisao());
				decimal = decimal.divide(divisor, BigDecimal.ROUND_CEILING);
			}
			
			return decimal;
		}

		return null;
	}

	protected Date getCampoDate(CampoArquivoTexto campo, String Formato) {
		String dado = getCampo(campo);

		if (dado != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(Formato);
			try {
				return new Date(sdf.parse(dado).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}