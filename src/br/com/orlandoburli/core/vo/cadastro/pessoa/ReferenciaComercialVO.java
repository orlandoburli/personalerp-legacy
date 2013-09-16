package br.com.orlandoburli.core.vo.cadastro.pessoa;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ReferenciaComercialVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPessoa;
	@Key
	@AutoIncrement
	private Integer CodigoReferenciaComercial;
	
	private String LocalReferenciaComercial;
	private String FoneReferenciaComercial;
	private String ContatoReferenciaComercial;
	private Date DataReferenciaComercial;
	private String FormaPagamentoReferenciaComercial;
	private Double MediaMensalReferenciaComercial;					
	private Integer MediaDiasAtrasoReferenciaComercial;
	private Double MaiorCompraReferenciaComercial;
	private Date DataMaiorCompraReferenciaComercial;
	private Double UltimaCompraReferenciaComercial;
	private Date DataUltimaCompraReferenciaComercial;
	private Integer ConceitoReferenciaComercial;
	private Date DataCadastroReferenciaComercial;
	private String ObservacoesReferenciaComercial;
	
	

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

	public Integer getCodigoPessoa() {
		return CodigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
	}

	public Integer getCodigoReferenciaComercial() {
		return CodigoReferenciaComercial;
	}

	public void setCodigoReferenciaComercial(Integer codigoReferenciaComercial) {
		CodigoReferenciaComercial = codigoReferenciaComercial;
	}

	public String getLocalReferenciaComercial() {
		return LocalReferenciaComercial;
	}

	public void setLocalReferenciaComercial(String localReferenciaComercial) {
		LocalReferenciaComercial = localReferenciaComercial;
	}

	public String getFoneReferenciaComercial() {
		return FoneReferenciaComercial;
	}

	public void setFoneReferenciaComercial(String foneReferenciaComercial) {
		FoneReferenciaComercial = foneReferenciaComercial;
	}

	public String getContatoReferenciaComercial() {
		return ContatoReferenciaComercial;
	}

	public void setContatoReferenciaComercial(String contatoReferenciaComercial) {
		ContatoReferenciaComercial = contatoReferenciaComercial;
	}

	public Date getDataReferenciaComercial() {
		return DataReferenciaComercial;
	}

	public void setDataReferenciaComercial(Date dataReferenciaComercial) {
		DataReferenciaComercial = dataReferenciaComercial;
	}

	public String getFormaPagamentoReferenciaComercial() {
		return FormaPagamentoReferenciaComercial;
	}

	public void setFormaPagamentoReferenciaComercial(
			String formaPagamentoReferenciaComercial) {
		FormaPagamentoReferenciaComercial = formaPagamentoReferenciaComercial;
	}

	public Double getMediaMensalReferenciaComercial() {
		return MediaMensalReferenciaComercial;
	}

	public void setMediaMensalReferenciaComercial(
			Double mediaMensalReferenciaComercial) {
		MediaMensalReferenciaComercial = mediaMensalReferenciaComercial;
	}

	public Integer getMediaDiasAtrasoReferenciaComercial() {
		return MediaDiasAtrasoReferenciaComercial;
	}

	public void setMediaDiasAtrasoReferenciaComercial(
			Integer mediaDiasAtrasoReferenciaComercial) {
		MediaDiasAtrasoReferenciaComercial = mediaDiasAtrasoReferenciaComercial;
	}

	public Double getMaiorCompraReferenciaComercial() {
		return MaiorCompraReferenciaComercial;
	}

	public void setMaiorCompraReferenciaComercial(
			Double maiorCompraReferenciaComercial) {
		MaiorCompraReferenciaComercial = maiorCompraReferenciaComercial;
	}

	public Date getDataMaiorCompraReferenciaComercial() {
		return DataMaiorCompraReferenciaComercial;
	}

	public void setDataMaiorCompraReferenciaComercial(
			Date dataMaiorCompraReferenciaComercial) {
		DataMaiorCompraReferenciaComercial = dataMaiorCompraReferenciaComercial;
	}

	public Double getUltimaCompraReferenciaComercial() {
		return UltimaCompraReferenciaComercial;
	}

	public void setUltimaCompraReferenciaComercial(
			Double ultimaCompraReferenciaComercial) {
		UltimaCompraReferenciaComercial = ultimaCompraReferenciaComercial;
	}

	public Date getDataUltimaCompraReferenciaComercial() {
		return DataUltimaCompraReferenciaComercial;
	}

	public void setDataUltimaCompraReferenciaComercial(
			Date dataUltimaCompraReferenciaComercial) {
		DataUltimaCompraReferenciaComercial = dataUltimaCompraReferenciaComercial;
	}

	public Integer getConceitoReferenciaComercial() {
		return ConceitoReferenciaComercial;
	}

	public void setConceitoReferenciaComercial(Integer conceitoReferenciaComercial) {
		ConceitoReferenciaComercial = conceitoReferenciaComercial;
	}

	public Date getDataCadastroReferenciaComercial() {
		return DataCadastroReferenciaComercial;
	}

	public void setDataCadastroReferenciaComercial(
			Date dataCadastroReferenciaComercial) {
		DataCadastroReferenciaComercial = dataCadastroReferenciaComercial;
	}

	public String getObservacoesReferenciaComercial() {
		return ObservacoesReferenciaComercial;
	}

	public void setObservacoesReferenciaComercial(
			String observacoesReferenciaComercial) {
		ObservacoesReferenciaComercial = observacoesReferenciaComercial;
	}
}
