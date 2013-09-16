package br.com.orlandoburli.core.vo.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.vo.IValueObject;

public class TipoSaidaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private String Descricao;
	private String Valor;
	private String ContentType;

	@Override
	public boolean IsNew() {
		return false;
	}
	
	public TipoSaidaVO(String Descricao, String Valor, String ContentType) {
		this.Descricao = Descricao;
		this.Valor = Valor;
		this.ContentType = ContentType;
	}

	@Override
	public void setNewRecord(boolean isNew) {}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public String getValor() {
		return Valor;
	}

	public static List<TipoSaidaVO> getListSaida() {
		List<TipoSaidaVO> list = new ArrayList<TipoSaidaVO>();
		list.add(new TipoSaidaVO("Adobe Reader (Pdf)", Constants.Saida.SAIDA_PDF, "application/pdf"));
		list.add(new TipoSaidaVO("Word (rtf)", Constants.Saida.SAIDA_RTF, "application/rtf"));
		list.add(new TipoSaidaVO("HTML", Constants.Saida.SAIDA_HTML, "text/html"));
		list.add(new TipoSaidaVO("Excel (xls)", Constants.Saida.SAIDA_XLS, "application/vnd.ms-excel"));
		list.add(new TipoSaidaVO("Texto (txt)", Constants.Saida.SAIDA_TXT, "text/plain"));
		return list;
	}
	
	public static TipoSaidaVO getTipoSaidaById(String tipo) {
		for (TipoSaidaVO tipovo : getListSaida()) {
			if (tipovo.getValor().equalsIgnoreCase(tipo)) {
				return tipovo;
			}
		}
		
		return null;
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

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String contentType) {
		ContentType = contentType;
	}
}