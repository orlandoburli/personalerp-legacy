package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneiogado;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.frigorifico.gado.RomaneioGadoDAO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado.RomaneioGadoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioGadoConsultaFacade extends BaseConsultaFlexFacade<RomaneioGadoVO, RomaneioGadoDAO>{

	private static final long serialVersionUID = 1L;
	
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

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
	}
}