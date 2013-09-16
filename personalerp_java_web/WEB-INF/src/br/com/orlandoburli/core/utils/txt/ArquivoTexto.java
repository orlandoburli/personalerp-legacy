package br.com.orlandoburli.core.utils.txt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.utils.txt.exceptions.CampoNaoEncontradoException;

public class ArquivoTexto {

	private List<LinhaArquivo> linhas;

	private List<CampoArquivoTexto> campos;

	private Integer Posicao;

	public ArquivoTexto() {
		this.linhas = new ArrayList<LinhaArquivo>();
		this.campos = new ArrayList<CampoArquivoTexto>();
		this.Posicao = 0;
	}

	public void load(String arquivo) throws IOException {
		FileInputStream in = new FileInputStream(arquivo);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String linha = "";
		while ((linha = reader.readLine()) != null) {
			this.linhas.add(new LinhaArquivo(linha));
		}

		in.close();
		Posicao = -1;
	}

	public void addCampo(String nomeCampo, Integer posicaoInicial, Integer posicaoFinal) {
		this.campos.add(new CampoArquivoTexto(nomeCampo, posicaoInicial, posicaoFinal));
	}

	public void addCampo(String nomeCampo, Integer posicaoInicial, Integer posicaoFinal, Integer precisao) {
		this.campos.add(new CampoArquivoTexto(nomeCampo, posicaoInicial, posicaoFinal, precisao));
	}

	public void first() {
		this.Posicao = 0;
	}

	public boolean next() {
		Posicao++;
		if (Posicao >= linhas.size()) {
			Posicao--;
			return false;
		}
		return true;
	}

	public boolean previous() {
		if (Posicao <= 0) {
			return false;
		}
		Posicao--;
		return true;
	}

	public void last() {
		Posicao = linhas.size() - 1;
	}

	protected CampoArquivoTexto getCampo(String nomeCampo) throws CampoNaoEncontradoException {
		for (CampoArquivoTexto campo : campos) {
			if (campo.getNomeCampo().equalsIgnoreCase(nomeCampo)) {
				return campo;
			}
		}
		throw new CampoNaoEncontradoException("");
	}

	public String getStringField(String nomeCampo) throws CampoNaoEncontradoException {
		CampoArquivoTexto campo = getCampo(nomeCampo);

		return linhas.get(Posicao).getCampo(campo);
	}

	public Integer getIntegerField(String nomeCampo) throws CampoNaoEncontradoException {
		CampoArquivoTexto campo = getCampo(nomeCampo);

		return linhas.get(Posicao).getCampoInt(campo);
	}

	public BigDecimal getDecimalField(String nomeCampo) throws CampoNaoEncontradoException {
		CampoArquivoTexto campo = getCampo(nomeCampo);

		return linhas.get(Posicao).getCampoDec(campo);
	}

	public BigDecimal getDecimalField(CampoArquivoTexto campo) {
		return linhas.get(Posicao).getCampoDec(campo);
	}

	public String getStringField(CampoArquivoTexto campo) {
		return linhas.get(Posicao).getCampo(campo);
	}

	public Integer getIntegerField(CampoArquivoTexto campo) {
		return linhas.get(Posicao).getCampoInt(campo);
	}
	
	public Date getDateField(CampoArquivoTexto campo, String Formato) {
		return linhas.get(Posicao).getCampoDate(campo, Formato);
	}
}
