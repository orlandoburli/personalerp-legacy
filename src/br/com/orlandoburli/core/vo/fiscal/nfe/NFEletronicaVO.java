package br.com.orlandoburli.core.vo.fiscal.nfe;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class NFEletronicaVO implements IValueObject {
	
	public static final String STATUS_DIGITACAO = "1";
	public static final String STATUS_VALIDADA = "2";
	public static final String STATUS_ASSINADA = "3";
	public static final String STATUS_TRANSMITIDA = "4";
	public static final String STATUS_AUTORIZADA = "5";
	public static final String STATUS_CANCELADA = "99";
	
	public static final String STATUS_IMPORTADA = "101";
	public static final String STATUS_PROCESSADA = "102";
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFe;
	
	private String StatusNFe;
	private String ChaveNFe;
	private String ProtocoloNFe;
	
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

	public String getStatusNFe() {
		return StatusNFe;
	}

	public void setStatusNFe(String statusNFe) {
		StatusNFe = statusNFe;
	}

	public String getChaveNFe() {
		return ChaveNFe;
	}

	public void setChaveNFe(String chaveNFe) {
		ChaveNFe = chaveNFe;
	}

	public String getProtocoloNFe() {
		return ProtocoloNFe;
	}

	public void setProtocoloNFe(String protocoloNFe) {
		ProtocoloNFe = protocoloNFe;
	}
}