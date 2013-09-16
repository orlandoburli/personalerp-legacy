package br.com.orlandoburli.core.vo.acesso;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class PermissaoPerfilObjetoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPerfil;
	@Key
	private Integer CodigoObjeto;
	
	@Formula(getFormula="(SELECT b.NomeObjeto FROM [schema].Objeto b WHERE a.CodigoObjeto = b.CodigoObjeto)")
	private String NomeObjeto;

	@Formula(getFormula="(SELECT c.DescricaoObjeto FROM [schema].Objeto c WHERE a.CodigoObjeto = c.CodigoObjeto)")
	private String DescricaoObjeto;
	
	@Formula(getFormula="(SELECT d.NomePerfil FROM [schema].Perfil d WHERE a.CodigoPerfil = d.CodigoPerfil AND a.CodigoEmpresa = d.CodigoEmpresa AND a.CodigoLoja = d.CodigoLoja)")
	private String NomePerfil;
	
	@Ignore
	private String Permitido;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
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

	public void setCodigoPerfil(Integer codigoPerfil) {
		CodigoPerfil = codigoPerfil;
	}

	public Integer getCodigoPerfil() {
		return CodigoPerfil;
	}

	public void setCodigoObjeto(Integer codigoObjeto) {
		CodigoObjeto = codigoObjeto;
	}

	public Integer getCodigoObjeto() {
		return CodigoObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		NomeObjeto = nomeObjeto;
	}

	public String getNomeObjeto() {
		return NomeObjeto;
	}

	public void setNomePerfil(String nomePerfil) {
		NomePerfil = nomePerfil;
	}

	public String getNomePerfil() {
		return NomePerfil;
	}

	public void setDescricaoObjeto(String descricaoObjeto) {
		DescricaoObjeto = descricaoObjeto;
	}

	public String getDescricaoObjeto() {
		return DescricaoObjeto;
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

	public void setPermitido(String permitido) {
		Permitido = permitido;
	}

	public String getPermitido() {
		return Permitido;
	}
}