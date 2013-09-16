package br.com.orlandoburli.core.vo.fiscal.nfe;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class TipoRegistroNFEletronicaVO implements IValueObject {
	
	@Ignore
	public static final String TIPO_XML_NFE_OBJETO = "nfeobjeto";
	@Ignore
	public static final String TIPO_XML_NFE_GERADO = "xmlnfeger";
	@Ignore
	public static final String TIPO_XML_NFE_ASSINADO = "xmlnfeass";
	@Ignore
	public static final String TIPO_XML_ENVIO_RECEPCAO = "xmlenvrec";
	@Ignore
	public static final String TIPO_XML_ENVIO_RETRECEPCAO = "xmlenvret";
	@Ignore
	public static final String TIPO_XML_RETORNO_RECEPCAO = "xmlretrec";
	@Ignore
	public static final String TIPO_XML_RETORNO_RETRECEPCAO = "xmlretret";
	@Ignore
	public static final String TIPO_XML_ENVIO_CANCELAMENTO = "xmlenvcanc";
	@Ignore
	public static final String TIPO_XML_RETORNO_CANCELAMENTO = "xmlretcanc";
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private String CodigoTipoRegistroNFe;
	private String DescricaoTipoRegistroNFe;
	
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

	public void setCodigoTipoRegistroNFe(String codigoTipoRegistroNFe) {
		CodigoTipoRegistroNFe = codigoTipoRegistroNFe;
	}

	public String getCodigoTipoRegistroNFe() {
		return CodigoTipoRegistroNFe;
	}

	public void setDescricaoTipoRegistroNFe(String descricaoTipoRegistroNFe) {
		DescricaoTipoRegistroNFe = descricaoTipoRegistroNFe;
	}

	public String getDescricaoTipoRegistroNFe() {
		return DescricaoTipoRegistroNFe;
	}
}