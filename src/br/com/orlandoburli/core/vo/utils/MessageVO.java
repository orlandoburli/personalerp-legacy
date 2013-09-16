package br.com.orlandoburli.core.vo.utils;

import br.com.orlandoburli.core.vo.IValueObject;

public class MessageVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	private String Message;
	private String FieldName;
	
	public MessageVO(String Message) {
		setMessage(Message);
	}
	
	public MessageVO(String Message, String FieldName) {
		setMessage(Message);
		setFieldName(FieldName);
	}
	
	@Override
	public boolean IsNew() { return false; }

	@Override
	public void setNewRecord(boolean isNew) { }

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getFieldName() {
		return FieldName;
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
}