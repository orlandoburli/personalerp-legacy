package br.com.orlandoburli.personalerp.facades.manutencao.trocamanutencao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.manutencao.TrocaManutencaoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.manutencao.SituacaoTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.TrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class TrocaManutencaoConsultaFacade extends BaseConsultaFlexFacade<TrocaManutencaoVO, TrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;
	
	private String Recibo;
	private String Referencia;
	private Integer Situacao;
	private Date DataEntradaInicial;
	private Date DataEntradaFinal;
	private Date DataEntregaInicial;
	private Date DataEntregaFinal;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeClienteTrocaManutencao("%" + getFiltro() + "%");
		getFilter().setNumeroTrocaManutencao("%" + getRecibo() + "%");
		getFilter().setReferenciaTrocaManutencao("%" + getReferencia() + "%");
		
		if (getReferencia() == null || getReferencia().trim().equals("")) {
			getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
			getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		}
		
		String sb = "";
		
		if (getDataEntradaInicial() != null) {
			sb += " DataEntradaTrocaManutencao >= '" + getDataEntradaInicial().toString() + "' ";
		}
		if (getDataEntradaFinal() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataEntradaTrocaManutencao <= '" + getDataEntradaFinal().toString() + "' ";
		}
		
		if (getDataEntregaInicial() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " PrazoTrocaManutencao >= '" + getDataEntregaInicial().toString() + "' ";
		}
		if (getDataEntregaFinal() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " PrazoTrocaManutencao <= '" + getDataEntregaFinal().toString() + "' ";
		}
		
		getFilter().setCodigoUltimaSituacaoTrocaManutencao(getSituacao());
		
		setOrderFields("DataEntradaTrocaManutencao DESC");
	}
	
	@IgnoreMethodAuthentication
	public void situacoes() {
		try {
			List<IValueObject> list = _dao.getList(new SituacaoTrocaManutencaoVO());
			
			SituacaoTrocaManutencaoVO empty = new SituacaoTrocaManutencaoVO();
			empty.setDescricaoSituacaoTrocaManutencao("[TODOS]");
			
			list.add(0, empty);
			
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getRecibo() {
		return Recibo;
	}

	public void setRecibo(String recibo) {
		Recibo = recibo;
	}

	public Date getDataEntradaInicial() {
		return DataEntradaInicial;
	}

	public void setDataEntradaInicial(Date dataEntradaInicial) {
		DataEntradaInicial = dataEntradaInicial;
	}

	public Date getDataEntradaFinal() {
		return DataEntradaFinal;
	}

	public void setDataEntradaFinal(Date dataEntradaFinal) {
		DataEntradaFinal = dataEntradaFinal;
	}

	public Date getDataEntregaInicial() {
		return DataEntregaInicial;
	}

	public void setDataEntregaInicial(Date dataEntregaInicial) {
		DataEntregaInicial = dataEntregaInicial;
	}

	public Date getDataEntregaFinal() {
		return DataEntregaFinal;
	}

	public void setDataEntregaFinal(Date dataEntregaFinal) {
		DataEntregaFinal = dataEntregaFinal;
	}

	public void setSituacao(Integer situacao) {
		Situacao = situacao;
	}

	public Integer getSituacao() {
		return Situacao;
	}

	public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

}