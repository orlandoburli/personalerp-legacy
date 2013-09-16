package br.com.orlandoburli.core.vo.rh.folha;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class FolhaPagamentoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Ignore
	public static final String STATUS_ABERTA = "A";
	@Ignore
	public static final String STATUS_PROCESSADO = "P";
	@Ignore
	public static final String STATUS_ESTORNADA = "E";
	@Ignore
	public static final String STATUS_FECHADA = "F";
	@Ignore
	public static final String STATUS_PROCESSANDO = "N";
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoFolhaPagamento;
	
	private Integer MesCompetenciaFolhaPagamento;
	private Integer AnoCompetenciaFolhaPagamento;
	
	private Integer CodigoEmpresaLotacao;
	private Integer CodigoLojaLotacao;
	
	private String StatusFolhaPagamento;
	
	private BigDecimal PercentualProcessadoFolhaPagamento;
	
	@Formula(getFormula="(SELECT b.NomeLoja FROM [schema].Loja b WHERE a.CodigoEmpresaLotacao = b.CodigoEmpresa AND a.CodigoLojaLotacao = b.CodigoLoja)")
	private String NomeLojaLotacao;

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

	public Integer getCodigoFolhaPagamento() {
		return CodigoFolhaPagamento;
	}

	public void setCodigoFolhaPagamento(Integer codigoFolhaPagamento) {
		CodigoFolhaPagamento = codigoFolhaPagamento;
	}

	public Integer getMesCompetenciaFolhaPagamento() {
		return MesCompetenciaFolhaPagamento;
	}

	public void setMesCompetenciaFolhaPagamento(Integer mesCompetenciaFolhaPagamento) {
		MesCompetenciaFolhaPagamento = mesCompetenciaFolhaPagamento;
	}

	public Integer getAnoCompetenciaFolhaPagamento() {
		return AnoCompetenciaFolhaPagamento;
	}

	public void setAnoCompetenciaFolhaPagamento(Integer anoCompetenciaFolhaPagamento) {
		AnoCompetenciaFolhaPagamento = anoCompetenciaFolhaPagamento;
	}

	public String getStatusFolhaPagamento() {
		return StatusFolhaPagamento;
	}

	public void setStatusFolhaPagamento(String statusFolhaPagamento) {
		StatusFolhaPagamento = statusFolhaPagamento;
	}

	public void setPercentualProcessadoFolhaPagamento(
			BigDecimal percentualProcessadoFolhaPagamento) {
		PercentualProcessadoFolhaPagamento = percentualProcessadoFolhaPagamento;
	}

	public BigDecimal getPercentualProcessadoFolhaPagamento() {
		return PercentualProcessadoFolhaPagamento;
	}

	public Integer getCodigoEmpresaLotacao() {
		return CodigoEmpresaLotacao;
	}

	public void setCodigoEmpresaLotacao(Integer codigoEmpresaLotacao) {
		CodigoEmpresaLotacao = codigoEmpresaLotacao;
	}

	public Integer getCodigoLojaLotacao() {
		return CodigoLojaLotacao;
	}

	public void setCodigoLojaLotacao(Integer codigoLojaLotacao) {
		CodigoLojaLotacao = codigoLojaLotacao;
	}

	public String getNomeLojaLotacao() {
		return NomeLojaLotacao;
	}

	public void setNomeLojaLotacao(String nomeLojaLotacao) {
		NomeLojaLotacao = nomeLojaLotacao;
	}
}
