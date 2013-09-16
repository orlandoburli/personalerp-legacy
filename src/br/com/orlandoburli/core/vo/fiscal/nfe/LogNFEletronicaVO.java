package br.com.orlandoburli.core.vo.fiscal.nfe;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class LogNFEletronicaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFe;
	@Key
	@AutoIncrement
	private Integer SequencialLogNFe;
	private String CodigoTipoRegistroNFe;
	private Timestamp DataHoraLogNFe;
	private String DadosLogNFe;
	
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

	public Integer getCodigoNFe() {
		return CodigoNFe;
	}

	public void setCodigoNFe(Integer codigoNFe) {
		CodigoNFe = codigoNFe;
	}

	public Integer getSequencialLogNFe() {
		return SequencialLogNFe;
	}

	public void setSequencialLogNFe(Integer sequencialLogNFe) {
		SequencialLogNFe = sequencialLogNFe;
	}

	public String getCodigoTipoRegistroNFe() {
		return CodigoTipoRegistroNFe;
	}

	public void setCodigoTipoRegistroNFe(String codigoTipoRegistroNFe) {
		CodigoTipoRegistroNFe = codigoTipoRegistroNFe;
	}

	public Timestamp getDataHoraLogNFe() {
		return DataHoraLogNFe;
	}

	public void setDataHoraLogNFe(Timestamp dataHoraLogNFe) {
		DataHoraLogNFe = dataHoraLogNFe;
	}

	public String getDadosLogNFe() {
		return DadosLogNFe;
	}

	public void setDadosLogNFe(String dadosLogNFe) {
		DadosLogNFe = dadosLogNFe;
	}
}