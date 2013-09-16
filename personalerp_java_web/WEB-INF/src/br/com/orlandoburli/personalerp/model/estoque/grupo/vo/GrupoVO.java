package br.com.orlandoburli.personalerp.model.estoque.grupo.vo;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class GrupoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoGrupo;
	private String NomeGrupo;
	private String TipoGrupo;
	private Integer QuantidadeMinimaUnitariaGrupo;
	private Double QuantidadeMinimaMetroGrupo;
	private Double PercentualComissaoGrupo;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
	
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}

	public Integer getCodigoGrupo() {
		return CodigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		CodigoGrupo = codigoGrupo;
	}

	public String getNomeGrupo() {
		return NomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		NomeGrupo = nomeGrupo;
	}

	public String getTipoGrupo() {
		return TipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		TipoGrupo = tipoGrupo;
	}

	public Integer getQuantidadeMinimaUnitariaGrupo() {
		return QuantidadeMinimaUnitariaGrupo;
	}

	public void setQuantidadeMinimaUnitariaGrupo(
			Integer quantidadeMinimaUnitariaGrupo) {
		QuantidadeMinimaUnitariaGrupo = quantidadeMinimaUnitariaGrupo;
	}

	public Double getQuantidadeMinimaMetroGrupo() {
		return QuantidadeMinimaMetroGrupo;
	}

	public void setQuantidadeMinimaMetroGrupo(Double quantidadeMinimaMetroGrupo) {
		QuantidadeMinimaMetroGrupo = quantidadeMinimaMetroGrupo;
	}

	public Double getPercentualComissaoGrupo() {
		return PercentualComissaoGrupo;
	}

	public void setPercentualComissaoGrupo(Double percentualComissaoGrupo) {
		PercentualComissaoGrupo = percentualComissaoGrupo;
	}
}
