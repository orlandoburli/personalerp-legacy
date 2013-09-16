package br.com.orlandoburli.core.vo.estoque.levantamento;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class LevantamentoEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoLevantamentoEstoque;
	
	private Timestamp DataHoraInicioLevantamentoEstoque;
	private Timestamp DataHoraFimLevantamentoEstoque;
	
	private Integer CodigoEmpresaUsuarioLevantamentoEstoque;
	private Integer CodigoLojaUsuarioLevantamentoEstoque;
	private Integer CodigoUsuarioLevantamentoEstoque;
	
	private String StatusLevantamentoEstoque;
	
	private Timestamp DataHoraProcessamentoLevantamentoEstoque;
	
	private String ObservacaoLevantamentoEstoque;
	
	@Join(entityName="Usuario", foreignColumnName="NomeUsuario", 
			localKeys={"CodigoEmpresaUsuarioLevantamentoEstoque", "CodigoLojaUsuarioLevantamentoEstoque", "CodigoUsuarioLevantamentoEstoque"}, 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoUsuario"})
	private String NomeUsuarioLevantamentoEstoque;
	
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

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLevantamentoEstoque() {
		return CodigoLevantamentoEstoque;
	}

	public void setCodigoLevantamentoEstoque(Integer codigoLevantamentoEstoque) {
		CodigoLevantamentoEstoque = codigoLevantamentoEstoque;
	}

	public Timestamp getDataHoraInicioLevantamentoEstoque() {
		return DataHoraInicioLevantamentoEstoque;
	}

	public void setDataHoraInicioLevantamentoEstoque(
			Timestamp dataHoraInicioLevantamentoEstoque) {
		DataHoraInicioLevantamentoEstoque = dataHoraInicioLevantamentoEstoque;
	}

	public Timestamp getDataHoraFimLevantamentoEstoque() {
		return DataHoraFimLevantamentoEstoque;
	}

	public void setDataHoraFimLevantamentoEstoque(
			Timestamp dataHoraFimLevantamentoEstoque) {
		DataHoraFimLevantamentoEstoque = dataHoraFimLevantamentoEstoque;
	}

	public Integer getCodigoEmpresaUsuarioLevantamentoEstoque() {
		return CodigoEmpresaUsuarioLevantamentoEstoque;
	}

	public void setCodigoEmpresaUsuarioLevantamentoEstoque(
			Integer codigoEmpresaUsuarioLevantamentoEstoque) {
		CodigoEmpresaUsuarioLevantamentoEstoque = codigoEmpresaUsuarioLevantamentoEstoque;
	}

	public Integer getCodigoLojaUsuarioLevantamentoEstoque() {
		return CodigoLojaUsuarioLevantamentoEstoque;
	}

	public void setCodigoLojaUsuarioLevantamentoEstoque(
			Integer codigoLojaUsuarioLevantamentoEstoque) {
		CodigoLojaUsuarioLevantamentoEstoque = codigoLojaUsuarioLevantamentoEstoque;
	}

	public Integer getCodigoUsuarioLevantamentoEstoque() {
		return CodigoUsuarioLevantamentoEstoque;
	}

	public void setCodigoUsuarioLevantamentoEstoque(
			Integer codigoUsuarioLevantamentoEstoque) {
		CodigoUsuarioLevantamentoEstoque = codigoUsuarioLevantamentoEstoque;
	}

	public String getStatusLevantamentoEstoque() {
		return StatusLevantamentoEstoque;
	}

	public void setStatusLevantamentoEstoque(String statusLevantamentoEstoque) {
		StatusLevantamentoEstoque = statusLevantamentoEstoque;
	}

	public void setNomeUsuarioLevantamentoEstoque(
			String nomeUsuarioLevantamentoEstoque) {
		NomeUsuarioLevantamentoEstoque = nomeUsuarioLevantamentoEstoque;
	}

	public String getNomeUsuarioLevantamentoEstoque() {
		return NomeUsuarioLevantamentoEstoque;
	}

	public void setObservacaoLevantamentoEstoque(
			String observacaoLevantamentoEstoque) {
		ObservacaoLevantamentoEstoque = observacaoLevantamentoEstoque;
	}

	public String getObservacaoLevantamentoEstoque() {
		return ObservacaoLevantamentoEstoque;
	}

	public void setDataHoraProcessamentoLevantamentoEstoque(Timestamp dataHoraProcessamentoLevantamentoEstoque) {
		DataHoraProcessamentoLevantamentoEstoque = dataHoraProcessamentoLevantamentoEstoque;
	}

	public Timestamp getDataHoraProcessamentoLevantamentoEstoque() {
		return DataHoraProcessamentoLevantamentoEstoque;
	}
}
