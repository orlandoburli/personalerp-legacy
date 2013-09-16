package br.com.orlandoburli.core.vo.financeiro.contaresumo;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ContaResumoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoContaResumo;
	private String DescricaoContaResumo;
	private Integer CodigoTipoContaResumo;
	private Integer CodigoContaResumoPai;
	private Integer CodigoContaResumoTotal;
	private String NegritoContaResumo;
	private Integer OrdemContaResumo;
	
	@Formula(getFormula="(SELECT b.DescricaoTipoContaResumo FROM [schema].TipoContaResumo b WHERE a.CodigoTipoContaResumo = b.CodigoTipoContaResumo)")
	private String DescricaoTipoContaResumo;
	
	public Integer getCodigoContaResumo() {
		return CodigoContaResumo;
	}

	public void setCodigoContaResumo(Integer codigoContaResumo) {
		CodigoContaResumo = codigoContaResumo;
	}

	public String getDescricaoContaResumo() {
		return DescricaoContaResumo;
	}

	public void setDescricaoContaResumo(String descricaoContaResumo) {
		DescricaoContaResumo = descricaoContaResumo;
	}

	public Integer getCodigoTipoContaResumo() {
		return CodigoTipoContaResumo;
	}

	public void setCodigoTipoContaResumo(Integer codigoTipoContaResumo) {
		CodigoTipoContaResumo = codigoTipoContaResumo;
	}

	public Integer getCodigoContaResumoPai() {
		return CodigoContaResumoPai;
	}

	public void setCodigoContaResumoPai(Integer codigoContaResumoPai) {
		CodigoContaResumoPai = codigoContaResumoPai;
	}

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

	public String getDescricaoTipoContaResumo() {
		return DescricaoTipoContaResumo;
	}

	public void setDescricaoTipoContaResumo(String descricaoTipoContaResumo) {
		DescricaoTipoContaResumo = descricaoTipoContaResumo;
	}

	public Integer getCodigoContaResumoTotal() {
		return CodigoContaResumoTotal;
	}

	public void setCodigoContaResumoTotal(Integer codigoContaResumoTotal) {
		CodigoContaResumoTotal = codigoContaResumoTotal;
	}

	public String getNegritoContaResumo() {
		return NegritoContaResumo;
	}

	public void setNegritoContaResumo(String negritoContaResumo) {
		NegritoContaResumo = negritoContaResumo;
	}

	public Integer getOrdemContaResumo() {
		return OrdemContaResumo;
	}

	public void setOrdemContaResumo(Integer ordemContaResumo) {
		OrdemContaResumo = ordemContaResumo;
	}
}
