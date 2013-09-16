package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlIgnore;
import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlNamespace;
import br.com.orlandoburli.core.extras.nfe.model.cobranca.Cobr;
import br.com.orlandoburli.core.extras.nfe.model.details.Det;
import br.com.orlandoburli.core.extras.nfe.model.totais.Total;
import br.com.orlandoburli.core.extras.nfe.model.transporte.Transporte;

public class InfNFe implements Serializable, IXmlNamespace {

	private static final long serialVersionUID = 1L;
	
	/**
	 * identificação da NF-e
	 */
	private Ide ide;
	/**
	 * Identificação do emitente
	 */
	private Emit emit;
	/**
	 * Identificação do destinatário
	 */
	private Dest dest;
	/**
	 * Identificacao do endereco de entrega
	 */
	private Entrega entrega;
	/**
	 * Lista de Itens da Nota
	 */
	private ArrayList<Det> det;
	
	/**
	 * Grupo de Valores Totais da NF-e
	 */
	private Total total;
	
	/**
	 * Informações do Transporte da NF-e
	 */
	private Transporte transp;
	
	/**
	 * Informacoes de cobranca
	 */
	private Cobr cobr;
	
	/**
	 * Id (Chave) da Nota Fiscal Eletronica.
	 */
	@IXmlIgnore
	private String Id;

	public InfNFe() {
		ide = new Ide();
		emit = new Emit();
		dest = new Dest();
		det = new ArrayList<Det>();
		total = new Total();
		transp = new Transporte();
		entrega = new Entrega();
		setCobr(new Cobr());
	}
	
	/**
	 * Retorna a identificação da NF-e
	 */
	public Ide getIde() {
		return ide;
	}
	/**
	 * Retorna Identificação do Emitente
	 * @return Dados do emitente
	 */
	public Emit getEmit() {
		return emit;
	}
	/**
	 * Retorna Identificação do Destinatário
	 * @return Dados do destinatário
	 */
	public Dest getDest() {
		return dest;
	}
	/**
	 * Seta o Id (Chave) da Nota Fiscal Eletronica.
	 */
	public void setId(String id) {
		Id = id;
		if (this.Id != null) {
			this.getIde().setcDV(this.getId().substring(this.getId().length() - 1, this.getId().length()));
		}
	}
	public String getId() {
		return Id;
	}
	@Override
	public String getNamespaceDeclaration() {
		return " Id=\"" + getId() + "\" versao=\"2.00\"";
	}
	/**
	 * Lista de Itens da Nota
	 */
	public List<Det> getDet() {
		return det;
	}
	/**
	 * Grupo de Valores Totais da NF-e
	 */
	public Total getTotal() {
		return total;
	}
	
	/**
	 * Informações do Transporte da NF-e
	 */
	public Transporte getTransp() {
		return transp;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Cobr getCobr() {
		return cobr;
	}

	public void setCobr(Cobr cobr) {
		this.cobr = cobr;
	}

}