package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomadeiraserrada;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioMadeiraSerradaConsultaFacade extends BaseConsultaFlexFacade<RomaneioMadeiraSerradaVO, RomaneioMadeiraSerradaDAO> {

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
		
		if (getTipoRomaneio() != null && !getTipoRomaneio().equals("T")) {
			this.getFilter().setTipoRomaneio(getTipoRomaneio());
		}
		if (getStatusRomaneio() != null && !getStatusRomaneio().equals("T")) {
			this.getFilter().setStatusRomaneio(getStatusRomaneio());
		}
		this.getFilter().setNomeUsuarioRomaneio(getFiltro() + "%");
		
		// Filtro para aparecer dados somente da empresa / loja em sessao
		this.getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		setOrderFields("DataRomaneio DESC");
	}

	@Override
	protected Class<?> getDAOClass() {
		return RomaneioMadeiraSerradaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioMadeiraSerradaVO.class;
	}

	public void setTipoRomaneio(String tipoRomaneio) {
		TipoRomaneio = tipoRomaneio;
	}

	public String getTipoRomaneio() {
		return TipoRomaneio;
	}

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}
}