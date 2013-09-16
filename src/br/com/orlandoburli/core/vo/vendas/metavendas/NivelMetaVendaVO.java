package br.com.orlandoburli.core.vo.vendas.metavendas;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class NivelMetaVendaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoNivelMetaVenda;
	private String NomeNivelMetaVenda;
	private Integer PesoNivelMetaVenda;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoNivelMetaVenda(Integer codigoNivelMetaVenda) {
		CodigoNivelMetaVenda = codigoNivelMetaVenda;
	}

	public Integer getCodigoNivelMetaVenda() {
		return CodigoNivelMetaVenda;
	}

	public void setNomeNivelMetaVenda(String nomeNivelMetaVenda) {
		NomeNivelMetaVenda = nomeNivelMetaVenda;
	}

	public String getNomeNivelMetaVenda() {
		return NomeNivelMetaVenda;
	}

	public void setPesoNivelMetaVenda(Integer pesoNivelMetaVenda) {
		PesoNivelMetaVenda = pesoNivelMetaVenda;
	}

	public Integer getPesoNivelMetaVenda() {
		return PesoNivelMetaVenda;
	}
}