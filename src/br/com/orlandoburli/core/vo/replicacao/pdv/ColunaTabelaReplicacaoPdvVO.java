package br.com.orlandoburli.core.vo.replicacao.pdv;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ColunaTabelaReplicacaoPdvVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private String NomeTabela;
	@Key
	private String NomeColuna;
	
	private Integer ChaveColuna;
	private String FormulaColuna;
	
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

	public String getNomeColuna() {
		return NomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		NomeColuna = nomeColuna;
	}

	public Integer getChaveColuna() {
		return ChaveColuna;
	}

	public void setChaveColuna(Integer chaveColuna) {
		ChaveColuna = chaveColuna;
	}

	public String getFormulaColuna() {
		return FormulaColuna;
	}

	public void setFormulaColuna(String formulaColuna) {
		FormulaColuna = formulaColuna;
	}
}
