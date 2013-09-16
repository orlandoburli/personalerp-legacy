package br.com.orlandoburli.core.vo.replicacao.pdv;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TabelaReplicacaoPdvVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;

	@Key
	private String NomeTabela;
	private String DescricaoTabela;
	private Integer OrdemTabela;
	
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

	public String getNomeTabela() {
		return NomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		NomeTabela = nomeTabela;
	}

	public String getDescricaoTabela() {
		return DescricaoTabela;
	}

	public void setDescricaoTabela(String descricaoTabela) {
		DescricaoTabela = descricaoTabela;
	}

	public Integer getOrdemTabela() {
		return OrdemTabela;
	}

	public void setOrdemTabela(Integer ordemTabela) {
		OrdemTabela = ordemTabela;
	}
}
