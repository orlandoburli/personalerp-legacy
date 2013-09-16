package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneioabate;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.frigorifico.romaneioabate.RomaneioAbateDAO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.RomaneioAbateVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioAbateConsultaFacade extends BaseConsultaFlexFacade<RomaneioAbateVO, RomaneioAbateDAO>{

	private static final long serialVersionUID = 1L;
	
	private String TipoRomaneio;
	private String StatusRomaneio;
	private Date DataInicial;
	private Date DataFinal;

	@Override
	protected void doBeforeFilter() {
		String sb = "";
		if (getDataInicial() != null) {
			sb += " DataRomaneio >= '" + getDataInicial().toString() + "' ";
		}
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataRomaneio <= '" + DataFinal.toString() + "' ";
		}
		dao.setSpecialCondition(sb);
		
		if (getStatusRomaneio() != null && !getStatusRomaneio().equals("T")) {
			this.getFilter().setStatusRomaneio(getStatusRomaneio());
		}
		this.getFilter().setNomeUsuarioRomaneio(getFiltro() + "%");
		
		// Filtro para aparecer dados somente da empresa / loja em sessao
		this.getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		setOrderFields("DataRomaneio DESC");
	}
	
	public String getTipoRomaneio() {
		return TipoRomaneio;
	}

	public void setTipoRomaneio(String tipoRomaneio) {
		TipoRomaneio = tipoRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}
}