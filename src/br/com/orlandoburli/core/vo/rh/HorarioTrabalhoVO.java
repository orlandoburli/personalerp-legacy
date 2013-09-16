package br.com.orlandoburli.core.vo.rh;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class HorarioTrabalhoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoHorarioTrabalho;
	
	private String DescricaoHorarioTrabalho;
	
	private Timestamp HoraInicio1HorarioTrabalho;
	private Timestamp HoraFim1HorarioTrabalho;
	private Timestamp HoraInicio2HorarioTrabalho;
	private Timestamp HoraFim2HorarioTrabalho;
	private Timestamp HoraInicio3HorarioTrabalho;
	private Timestamp HoraFim3HorarioTrabalho;
	private Timestamp HoraInicio4HorarioTrabalho;
	private Timestamp HoraFim4HorarioTrabalho;

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

	public Integer getCodigoHorarioTrabalho() {
		return CodigoHorarioTrabalho;
	}

	public void setCodigoHorarioTrabalho(Integer codigoHorarioTrabalho) {
		CodigoHorarioTrabalho = codigoHorarioTrabalho;
	}

	public Timestamp getHoraInicio1HorarioTrabalho() {
		return HoraInicio1HorarioTrabalho;
	}

	public void setHoraInicio1HorarioTrabalho(Timestamp horaInicio1HorarioTrabalho) {
		HoraInicio1HorarioTrabalho = horaInicio1HorarioTrabalho;
	}

	public Timestamp getHoraFim1HorarioTrabalho() {
		return HoraFim1HorarioTrabalho;
	}

	public void setHoraFim1HorarioTrabalho(Timestamp horaFim1HorarioTrabalho) {
		HoraFim1HorarioTrabalho = horaFim1HorarioTrabalho;
	}

	public Timestamp getHoraInicio2HorarioTrabalho() {
		return HoraInicio2HorarioTrabalho;
	}

	public void setHoraInicio2HorarioTrabalho(Timestamp horaInicio2HorarioTrabalho) {
		HoraInicio2HorarioTrabalho = horaInicio2HorarioTrabalho;
	}

	public Timestamp getHoraFim2HorarioTrabalho() {
		return HoraFim2HorarioTrabalho;
	}

	public void setHoraFim2HorarioTrabalho(Timestamp horaFim2HorarioTrabalho) {
		HoraFim2HorarioTrabalho = horaFim2HorarioTrabalho;
	}

	public Timestamp getHoraInicio3HorarioTrabalho() {
		return HoraInicio3HorarioTrabalho;
	}

	public void setHoraInicio3HorarioTrabalho(Timestamp horaInicio3HorarioTrabalho) {
		HoraInicio3HorarioTrabalho = horaInicio3HorarioTrabalho;
	}

	public Timestamp getHoraFim3HorarioTrabalho() {
		return HoraFim3HorarioTrabalho;
	}

	public void setHoraFim3HorarioTrabalho(Timestamp horaFim3HorarioTrabalho) {
		HoraFim3HorarioTrabalho = horaFim3HorarioTrabalho;
	}

	public Timestamp getHoraInicio4HorarioTrabalho() {
		return HoraInicio4HorarioTrabalho;
	}

	public void setHoraInicio4HorarioTrabalho(Timestamp horaInicio4HorarioTrabalho) {
		HoraInicio4HorarioTrabalho = horaInicio4HorarioTrabalho;
	}

	public Timestamp getHoraFim4HorarioTrabalho() {
		return HoraFim4HorarioTrabalho;
	}

	public void setHoraFim4HorarioTrabalho(Timestamp horaFim4HorarioTrabalho) {
		HoraFim4HorarioTrabalho = horaFim4HorarioTrabalho;
	}

	public void setDescricaoHorarioTrabalho(String descricaoHorarioTrabalho) {
		DescricaoHorarioTrabalho = descricaoHorarioTrabalho;
	}

	public String getDescricaoHorarioTrabalho() {
		return DescricaoHorarioTrabalho;
	}
}