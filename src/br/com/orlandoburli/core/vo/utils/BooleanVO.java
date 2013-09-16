package br.com.orlandoburli.core.vo.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.vo.IValueObject;

public class BooleanVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	private boolean Value;
	private String Description;

	@Override
	public boolean IsNew() {
		return false;
	}

	@Override
	public void setNewRecord(boolean isNew) {}

	public void setValue(boolean value) {
		Value = value;
	}

	public boolean getValue() {
		return Value;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}
	
	public static List<BooleanVO> getListSimNao() {
		List<BooleanVO> list = new ArrayList<BooleanVO>();
		
		BooleanVO sim = new BooleanVO();
		sim.setValue(true);
		sim.setDescription("Sim");
		
		BooleanVO nao = new BooleanVO();
		nao.setValue(false);
		nao.setDescription("Não");
		
		list.add(sim);
		list.add(nao);
		
		return list;
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