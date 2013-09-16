package br.com.orlandoburli.core.vo.estoque.transferencia;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class TransferenciaEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoTransferenciaEstoque;
	
	private Timestamp DataHoraSaidaTransferenciaEstoque;
	private Timestamp DataHoraEntradaTransferenciaEstoque;
	
	private Integer CodigoEmpresaDestino;
	private Integer CodigoLojaDestino;
	
	private String StatusTransferenciaEstoque;
	
	private String ObservacaoTransferenciaEstoque;
	
	@Join(entityName="Loja", foreignColumnName="NomeLoja", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja"}, 
			localKeys={"CodigoEmpresaDestino", "CodigoLojaDestino"})
	private String NomeLojaDestino;
	
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

	public Integer getCodigoTransferenciaEstoque() {
		return CodigoTransferenciaEstoque;
	}

	public void setCodigoTransferenciaEstoque(Integer codigoTransferenciaEstoque) {
		CodigoTransferenciaEstoque = codigoTransferenciaEstoque;
	}

	public Timestamp getDataHoraSaidaTransferenciaEstoque() {
		return DataHoraSaidaTransferenciaEstoque;
	}

	public void setDataHoraSaidaTransferenciaEstoque(Timestamp dataHoraSaidaTransferenciaEstoque) {
		DataHoraSaidaTransferenciaEstoque = dataHoraSaidaTransferenciaEstoque;
	}

	public Timestamp getDataHoraEntradaTransferenciaEstoque() {
		return DataHoraEntradaTransferenciaEstoque;
	}

	public void setDataHoraEntradaTransferenciaEstoque(Timestamp dataHoraEntradaTransferenciaEstoque) {
		DataHoraEntradaTransferenciaEstoque = dataHoraEntradaTransferenciaEstoque;
	}

	public Integer getCodigoEmpresaDestino() {
		return CodigoEmpresaDestino;
	}

	public void setCodigoEmpresaDestino(Integer codigoEmpresaDestino) {
		CodigoEmpresaDestino = codigoEmpresaDestino;
	}

	public Integer getCodigoLojaDestino() {
		return CodigoLojaDestino;
	}

	public void setCodigoLojaDestino(Integer codigoLojaDestino) {
		CodigoLojaDestino = codigoLojaDestino;
	}

	public String getObservacaoTransferenciaEstoque() {
		return ObservacaoTransferenciaEstoque;
	}

	public void setObservacaoTransferenciaEstoque(String observacaoTransferenciaEstoque) {
		ObservacaoTransferenciaEstoque = observacaoTransferenciaEstoque;
	}

	public void setStatusTransferenciaEstoque(String statusTransferenciaEstoque) {
		StatusTransferenciaEstoque = statusTransferenciaEstoque;
	}

	public String getStatusTransferenciaEstoque() {
		return StatusTransferenciaEstoque;
	}

	public void setNomeLojaDestino(String nomeLojaDestino) {
		NomeLojaDestino = nomeLojaDestino;
	}

	public String getNomeLojaDestino() {
		return NomeLojaDestino;
	}
}