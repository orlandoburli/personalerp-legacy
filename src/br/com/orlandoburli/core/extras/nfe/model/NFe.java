package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlIgnore;

/**
 * Classe Utilitária NFe.
 * Esta classe não é persistida (pelo menos não ainda),
 * ela apenas representa o XML que será gerado posteriormente.
 * @author Orlando Burli
 */
public class NFe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private InfNFe infNFe;
	@IXmlIgnore
	private String numeroProtocoloAutorizacao;
	@IXmlIgnore
	private String DataHoraProtocoloAutorizacao;
	
	/**
	 * Retorna uma nova nf-e para trabalhar, com os valores
	 * padrão setados e dependencias instanciadas.
	 * @return
	 */
	public static NFe getInstance() {
		NFe nfe = new NFe();
		return nfe;
	}
	
	private NFe() {
		super();
		this.infNFe = new InfNFe();
	}

	public InfNFe getInfNfe() {
		return infNFe;
	}

	public void setDataHoraProtocoloAutorizacao(
			String dataHoraProtocoloAutorizacao) {
		DataHoraProtocoloAutorizacao = dataHoraProtocoloAutorizacao;
	}

	public String getDataHoraProtocoloAutorizacao() {
		return DataHoraProtocoloAutorizacao;
	}

	public void setNumeroProtocoloAutorizacao(String numeroProtocoloAutorizacao) {
		this.numeroProtocoloAutorizacao = numeroProtocoloAutorizacao;
	}

	public String getNumeroProtocoloAutorizacao() {
		return numeroProtocoloAutorizacao;
	}
}