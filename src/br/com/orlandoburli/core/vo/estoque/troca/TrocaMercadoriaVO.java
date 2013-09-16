package br.com.orlandoburli.core.vo.estoque.troca;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class TrocaMercadoriaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoTroca;
	
	private Timestamp DataHoraTroca;
	private String StatusTroca;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private String MotivoTroca;
	private String ObservacaoTroca;
	
	@Join(entityName="Vendedor", foreignColumnName="NomeVendedor", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoVendedor"}, 
			localKeys={"CodigoEmpresaVendedor", "CodigoLojaVendedor", "CodigoVendedor"})
	private String NomeVendedor;
	
	@Formula(getFormula="" +
			"(SELECT SUM(ValorTrocaSaida) " +
			"   FROM personalerp.TrocaMercadoriaSaida b " +
			"  WHERE a.CodigoEmpresa = b.CodigoEmpresa" +
			"    AND a.CodigoLoja = b.CodigoLoja" +
			"    AND a.CodigoTroca = b.CodigoTroca) " +
			" - " +
			"(SELECT SUM(ValorTrocaEntrada) " +
			"   FROM personalerp.TrocaMercadoriaEntrada c" +
			"  WHERE a.CodigoEmpresa = c.CodigoEmpresa" +
			"    AND a.CodigoLoja = c.CodigoLoja" +
			"    AND a.CodigoTroca = c.CodigoTroca)")
	private BigDecimal ValorDiferencaTroca;
	
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

	public Integer getCodigoTroca() {
		return CodigoTroca;
	}

	public void setCodigoTroca(Integer codigoTroca) {
		CodigoTroca = codigoTroca;
	}

	public Timestamp getDataHoraTroca() {
		return DataHoraTroca;
	}

	public void setDataHoraTroca(Timestamp dataHoraTroca) {
		DataHoraTroca = dataHoraTroca;
	}

	public String getStatusTroca() {
		return StatusTroca;
	}

	public void setStatusTroca(String statusTroca) {
		StatusTroca = statusTroca;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public String getMotivoTroca() {
		return MotivoTroca;
	}

	public void setMotivoTroca(String motivoTroca) {
		MotivoTroca = motivoTroca;
	}

	public String getObservacaoTroca() {
		return ObservacaoTroca;
	}

	public void setObservacaoTroca(String observacaoTroca) {
		ObservacaoTroca = observacaoTroca;
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return NomeVendedor;
	}

	public void setValorDiferencaTroca(BigDecimal valorDiferencaTroca) {
		ValorDiferencaTroca = valorDiferencaTroca;
	}

	public BigDecimal getValorDiferencaTroca() {
		return ValorDiferencaTroca;
	}
}