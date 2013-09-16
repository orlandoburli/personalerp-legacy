package br.com.orlandoburli.core.vo;

/**
 * Interface de log
 * @author orlandoburli
 */
public interface ILog {
	void setCodigoUsuarioLog(Integer codigoUsuarioLog);
	Integer getCodigoUsuarioLog();
	Integer getCodigoEmpresaUsuarioLog();
	void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog);
	Integer getCodigoLojaUsuarioLog();
	void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog);
}