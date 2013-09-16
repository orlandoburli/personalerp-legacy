package br.com.orlandoburli.core.utils.txt;

public class CampoArquivoTexto {

	private String nomeCampo;
	private Integer posicaoInicial;
	private Integer posicaoFinal;
	private Integer precisao;
	
	public CampoArquivoTexto(String NomeCampo, Integer PosicaoInicial, Integer PosicaoFinal) {
		setNomeCampo(NomeCampo);
		setPosicaoInicial(PosicaoInicial);
		setPosicaoFinal(PosicaoFinal);
		setPrecisao(null);
	}
	
	public CampoArquivoTexto(String NomeCampo, Integer PosicaoInicial, Integer PosicaoFinal, Integer Precisao) {
		setNomeCampo(NomeCampo);
		setPosicaoInicial(PosicaoInicial);
		setPosicaoFinal(PosicaoFinal);
		setPrecisao(Precisao);
	}
	
	public String getNomeCampo() {
		return nomeCampo;
	}
	private void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public Integer getPosicaoInicial() {
		return posicaoInicial;
	}
	private void setPosicaoInicial(Integer posicaoInicial) {
		this.posicaoInicial = posicaoInicial;
	}
	public Integer getPosicaoFinal() {
		return posicaoFinal;
	}
	private void setPosicaoFinal(Integer posicaoFinal) {
		this.posicaoFinal = posicaoFinal;
	}
	public Integer getPrecisao() {
		return precisao;
	}
	public void setPrecisao(Integer precisao) {
		this.precisao = precisao;
	}
}
