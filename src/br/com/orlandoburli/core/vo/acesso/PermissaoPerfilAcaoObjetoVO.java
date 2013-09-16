package br.com.orlandoburli.core.vo.acesso;

import br.com.orlandoburli.core.vo.*;

public class PermissaoPerfilAcaoObjetoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private int CodigoEmpresa;
	@Key
	private int CodigoLoja;
	@Key
	private int CodigoPerfil;
	@Key
	private int CodigoObjeto;
	@Key
	private int CodigoAcaoObjeto;
	
	@Formula(getFormula="(SELECT NomeAcaoObjeto FROM [schema].AcaoObjeto b WHERE b.CodigoObjeto = a.CodigoObjeto AND b.CodigoAcaoObjeto = a.CodigoAcaoObjeto)")
	private String NomeAcaoObjeto;
	
	@Formula(getFormula="(SELECT NomeObjeto FROM [schema].Objeto c WHERE c.CodigoObjeto = a.CodigoObjeto)")
	private String NomeObjeto;
	
	@Ignore
	private int Marcado;
	
	@Ignore
	private String Marcar;
	
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

	public int getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(int codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public int getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(int codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public int getCodigoPerfil() {
		return CodigoPerfil;
	}

	public void setCodigoPerfil(int codigoPerfil) {
		CodigoPerfil = codigoPerfil;
	}

	public int getCodigoObjeto() {
		return CodigoObjeto;
	}

	public void setCodigoObjeto(int codigoObjeto) {
		CodigoObjeto = codigoObjeto;
	}

	public int getCodigoAcaoObjeto() {
		return CodigoAcaoObjeto;
	}

	public void setCodigoAcaoObjeto(int codigoAcaoObjeto) {
		CodigoAcaoObjeto = codigoAcaoObjeto;
	}

	public void setNomeAcaoObjeto(String nomeAcaoObjeto) {
		NomeAcaoObjeto = nomeAcaoObjeto;
	}

	public String getNomeAcaoObjeto() {
		return NomeAcaoObjeto;
	}

	public void setMarcado(int marcado) {
		Marcado = marcado;
		this.Marcar = (this.Marcado == 0?"Negado":"Permitido");
	}

	public int getMarcado() {
		return Marcado;
	}

	public void setMarcar(String marcar) {
		Marcar = marcar;
	}

	public String getMarcar() {
		return Marcar;
	}

	public void setNomeObjeto(String nomeObjeto) {
		NomeObjeto = nomeObjeto;
	}

	public String getNomeObjeto() {
		return NomeObjeto;
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