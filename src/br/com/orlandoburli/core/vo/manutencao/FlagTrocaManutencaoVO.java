package br.com.orlandoburli.core.vo.manutencao;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FlagTrocaManutencaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoTipoFlagTrocaManutencao;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoTrocaManutencao;
	
	private String ValorFlagTrocaManutencao;
	
	@Formula(getFormula="(SELECT b.NomeTipoFlagTrocaManutencao FROM [schema].TipoFlagTrocaManutencao b WHERE a.CodigoTipoFlagTrocaManutencao = b.CodigoTipoFlagTrocaManutencao)")
	private String NomeTipoFlagTrocaManutencao;
	
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

	public void setCodigoTipoFlagTrocaManutencao(Integer codigoTipoFlagTrocaManutencao) {
		CodigoTipoFlagTrocaManutencao = codigoTipoFlagTrocaManutencao;
	}

	public Integer getCodigoTipoFlagTrocaManutencao() {
		return CodigoTipoFlagTrocaManutencao;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoTrocaManutencao(Integer codigoTrocaManutencao) {
		CodigoTrocaManutencao = codigoTrocaManutencao;
	}

	public Integer getCodigoTrocaManutencao() {
		return CodigoTrocaManutencao;
	}

	public void setValorFlagTrocaManutencao(String valorFlagTrocaManutencao) {
		ValorFlagTrocaManutencao = valorFlagTrocaManutencao;
	}

	public String getValorFlagTrocaManutencao() {
		return ValorFlagTrocaManutencao;
	}

	public void setNomeTipoFlagTrocaManutencao(String nomeTipoFlagTrocaManutencao) {
		NomeTipoFlagTrocaManutencao = nomeTipoFlagTrocaManutencao;
	}

	public String getNomeTipoFlagTrocaManutencao() {
		return NomeTipoFlagTrocaManutencao;
	}
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
	}
}