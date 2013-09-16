package br.com.orlandoburli.core.utils.cripto;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.utils.cripto.exceptions.ChaveInvalidaException;

public class AlgoritmoCriptografia {

	private static final int CONSTANTE = 13;
	private static final int CONSTANTE_CHARS = 70;
	private static final int TAMANHO_MAXIMO_CHAVE = 8;
	private String chave;
	
	public static void main (String[] args) {
		try {
			System.out.println(Utils.getNow());
			
			AlgoritmoCriptografia algoritmo = new AlgoritmoCriptografia("carol140");
			
			String dados = "Orlando Burli Júnior";
			
			String novoDado = algoritmo.criptografar(dados);
			System.out.println(novoDado);
			
			String dadoAntigo = algoritmo.descriptografar(novoDado);
			System.out.println(dadoAntigo);
			
			System.out.println(Utils.getNow());
		
		} catch (ChaveInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public AlgoritmoCriptografia(String chave) throws ChaveInvalidaException {
		setChave(chave);
	}
	
	public String criptografar(String dados) {
		
		if (dados == null) {
			throw new NullPointerException("Dados nulos!");
		}
		
		StringBuilder sb = new StringBuilder();
		
		int posicaoChave = -1;
		for (int i = 0; i < dados.length(); i++) {
			
			// Para cada caracter, indicar uma letra que indicara o tamanho da cadeia de caracteres.
			posicaoChave++;
			if (posicaoChave >= chave.length()) {
				posicaoChave = 0;
			}
			int chaveCharacter = chave.charAt(posicaoChave);
			
			// Busca o caracter da posicao nos dados
			int charDados = dados.charAt(i);
			
			// Calcula o novo dado
			int novoDado = chaveCharacter * charDados * CONSTANTE;
			
			// Transforma o novo dado em string
			String strNovoDado = Integer.toString(novoDado);
			
			// A primeira informacao da cadeia e o numero de caracteres
			int tamanhoCadeia = strNovoDado.length();
			
			// Adiciona na string final
			sb.append(Character.toChars(tamanhoCadeia + CONSTANTE_CHARS));
			
			// Cada numero do novo dado, sera convertido em uma String
			for (int j = 0; j < strNovoDado.length(); j++) {
				int caracterNovoDado = Integer.parseInt(strNovoDado.substring(j, j+1));
				sb.append(Character.toChars(caracterNovoDado + CONSTANTE_CHARS));
			}
		}
		
		return sb.toString();
	}
	
	public String descriptografar(String dados) {
		
		if (dados == null) {
			throw new NullPointerException("Dados nulos!");
		}
		
		StringBuilder buffer = new StringBuilder(dados);
		StringBuilder sb  = new StringBuilder();
		
		int posicaoChave = -1;
		
		while (buffer.length() > 0) {
			posicaoChave++;
			if (posicaoChave >= chave.length()) {
				posicaoChave = 0;
			}
			int chaveCharacter = chave.charAt(posicaoChave);
			
			int charTamanhoCadeia = buffer.charAt(0);
			
			// Busca tamanho do dado
			int tamanhoCadeia = charTamanhoCadeia - CONSTANTE_CHARS;

			// Le dado1 do buffer
			String dado1 = buffer.substring(1, tamanhoCadeia + 1);
			
			String dadoNumerico1 = "";
			// Descriptografa dado1
			for (int i = 0; i < dado1.length(); i++) {
				int numero = dado1.charAt(i) -  CONSTANTE_CHARS;
				dadoNumerico1 += numero;
			}
			
			int dadoFinal = Integer.parseInt(dadoNumerico1) / chaveCharacter / CONSTANTE;
			
			sb.append((char)dadoFinal);
			
			// Remove o dado do buffer
			buffer.delete(0, tamanhoCadeia + 1);
			
		}
		
		return sb.toString();
	}
	
	@SuppressWarnings("unused")
	private String fillString(Object ovalue, String fillWith, int size, int direction) {
		String value = ovalue.toString();
		// Checa se Linha a preencher é nula ou branco
		if (value == null || value.trim() == "") {
			value = "";
		}
		
		// Enquanto Linha a preencher possuir 2 espaços em branco seguidos,
		// substitui por 1 espaço apenas
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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) throws ChaveInvalidaException {
		if (chave == null || chave.length() != TAMANHO_MAXIMO_CHAVE) {
			throw new ChaveInvalidaException("Chave deve ter " + TAMANHO_MAXIMO_CHAVE + " bits!");
		}
		this.chave = chave;
	}

}
