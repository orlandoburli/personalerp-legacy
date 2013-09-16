package br.com.orlandoburli.personalerp;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class Versao {
	
	private static Versao versao;
	
	String date = "07/08/2013";
	String numeroVersao = "2.0a";
	
	private Versao() {
		
	}
		
	public static Versao getInstance() {
		if (versao == null) {
			versao = new Versao();
		}
		
		return versao;
	}
	
	public String getNumeroVersao() {
		return numeroVersao;
	}

	public Date getDataVersao() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return new Date(sdf.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
