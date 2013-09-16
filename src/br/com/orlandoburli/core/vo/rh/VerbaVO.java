package br.com.orlandoburli.core.vo.rh;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class VerbaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Ignore
	public static final String TIPO_VERBA_CREDITO = "C";
	@Ignore
	public static final String TIPO_VERBA_DEBITO = "D";
	@Ignore
	public static final String TIPO_VERBA_AUXILIAR = "A";
	
	@Ignore
	public static final String TIPO_CALCULO_VALOR_FIXO = "V";
	@Ignore
	public static final String TIPO_CALCULO_SISTEMA = "S";
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoVerba;
	
	private String DescricaoVerba;
	private String DescricaoCurtaVerba;
	
	private String TipoVerba;
	
	private String FlagSalarioBaseVerba;
	private String FlagAdiantamentoVerba;
	private String FlagBaseInssVerba;
	private String FlagBaseIrrfVerba;
	private String FlagFolhaVerba;
	
	private String TipoCalculoVerba;
	
	private String ClasseSistemaVerba;

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

	public Integer getCodigoVerba() {
		return CodigoVerba;
	}

	public void setCodigoVerba(Integer codigoVerba) {
		CodigoVerba = codigoVerba;
	}

	public String getDescricaoVerba() {
		return DescricaoVerba;
	}

	public void setDescricaoVerba(String descricaoVerba) {
		DescricaoVerba = descricaoVerba;
	}

	public String getDescricaoCurtaVerba() {
		return DescricaoCurtaVerba;
	}

	public void setDescricaoCurtaVerba(String descricaoCurtaVerba) {
		DescricaoCurtaVerba = descricaoCurtaVerba;
	}

	public String getTipoVerba() {
		return TipoVerba;
	}

	public void setTipoVerba(String tipoVerba) {
		TipoVerba = tipoVerba;
	}

	public String getFlagSalarioBaseVerba() {
		return FlagSalarioBaseVerba;
	}

	public void setFlagSalarioBaseVerba(String flagSalarioBaseVerba) {
		FlagSalarioBaseVerba = flagSalarioBaseVerba;
	}

	public String getFlagAdiantamentoVerba() {
		return FlagAdiantamentoVerba;
	}

	public void setFlagAdiantamentoVerba(String flagAdiantamentoVerba) {
		FlagAdiantamentoVerba = flagAdiantamentoVerba;
	}

	public String getFlagBaseInssVerba() {
		return FlagBaseInssVerba;
	}

	public void setFlagBaseInssVerba(String flagBaseInssVerba) {
		FlagBaseInssVerba = flagBaseInssVerba;
	}

	public String getFlagBaseIrrfVerba() {
		return FlagBaseIrrfVerba;
	}

	public void setFlagBaseIrrfVerba(String flagBaseIrrfVerba) {
		FlagBaseIrrfVerba = flagBaseIrrfVerba;
	}

	public String getTipoCalculoVerba() {
		return TipoCalculoVerba;
	}

	public void setTipoCalculoVerba(String tipoCalculoVerba) {
		TipoCalculoVerba = tipoCalculoVerba;
	}

	public void setClasseSistemaVerba(String classeSistemaVerba) {
		ClasseSistemaVerba = classeSistemaVerba;
	}

	public String getClasseSistemaVerba() {
		return ClasseSistemaVerba;
	}

	public void setFlagFolhaVerba(String flagFolhaVerba) {
		FlagFolhaVerba = flagFolhaVerba;
	}

	public String getFlagFolhaVerba() {
		return FlagFolhaVerba;
	}
}