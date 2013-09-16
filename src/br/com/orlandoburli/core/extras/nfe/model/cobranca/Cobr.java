package br.com.orlandoburli.core.extras.nfe.model.cobranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cobr implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Dup> dup;

	public Cobr() {
		this.dup = new ArrayList<Dup>();
	}
	
	public List<Dup> getDup() {
		return dup;
	}

	public void setDup(List<Dup> dup) {
		this.dup = dup;
	}
}
