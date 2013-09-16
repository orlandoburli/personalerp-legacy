package br.com.orlandoburli.core.vo.estoque.entradaestoque;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class EntradaEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoEntradaEstoque;
	
	private Timestamp DataHoraEntradaEstoque;
	private Timestamp DataHoraProcessamentoEntradaEstoque;
	private String StatusEntradaEstoque;
	private String NumeroDocumentoEntradaEstoque;
	
	private Integer CodigoEmpresaPessoa;
	private Integer CodigoLojaPessoa;
	private Integer CodigoPessoa;
	private Integer CodigoEnderecoPessoa;
	
	private String ObservacoesEntradaEstoque;
	
	@Join(entityName="Pessoa", 
			foreignColumnName="NomeRazaoSocialPessoa", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoPessoa"}, 
			localKeys={"CodigoEmpresaPessoa", "CodigoLojaPessoa", "CodigoPessoa"})
	private String NomeFornecedor;
	
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

	public Integer getCodigoEntradaEstoque() {
		return CodigoEntradaEstoque;
	}

	public void setCodigoEntradaEstoque(Integer codigoEntradaEstoque) {
		CodigoEntradaEstoque = codigoEntradaEstoque;
	}

	public Timestamp getDataHoraEntradaEstoque() {
		return DataHoraEntradaEstoque;
	}

	public void setDataHoraEntradaEstoque(Timestamp dataHoraEntradaEstoque) {
		DataHoraEntradaEstoque = dataHoraEntradaEstoque;
	}

	public String getStatusEntradaEstoque() {
		return StatusEntradaEstoque;
	}

	public void setStatusEntradaEstoque(String statusEntradaEstoque) {
		StatusEntradaEstoque = statusEntradaEstoque;
	}

	public String getNumeroDocumentoEntradaEstoque() {
		return NumeroDocumentoEntradaEstoque;
	}

	public void setNumeroDocumentoEntradaEstoque(String numeroDocumentoEntradaEstoque) {
		NumeroDocumentoEntradaEstoque = numeroDocumentoEntradaEstoque;
	}

	public Integer getCodigoEmpresaPessoa() {
		return CodigoEmpresaPessoa;
	}

	public void setCodigoEmpresaPessoa(Integer codigoEmpresaPessoa) {
		CodigoEmpresaPessoa = codigoEmpresaPessoa;
	}

	public Integer getCodigoLojaPessoa() {
		return CodigoLojaPessoa;
	}

	public void setCodigoLojaPessoa(Integer codigoLojaPessoa) {
		CodigoLojaPessoa = codigoLojaPessoa;
	}

	public Integer getCodigoPessoa() {
		return CodigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
	}

	public Integer getCodigoEnderecoPessoa() {
		return CodigoEnderecoPessoa;
	}

	public void setCodigoEnderecoPessoa(Integer codigoEnderecoPessoa) {
		CodigoEnderecoPessoa = codigoEnderecoPessoa;
	}

	public String getObservacoesEntradaEstoque() {
		return ObservacoesEntradaEstoque;
	}

	public void setObservacoesEntradaEstoque(String observacoesEntradaEstoque) {
		ObservacoesEntradaEstoque = observacoesEntradaEstoque;
	}

	public void setDataHoraProcessamentoEntradaEstoque(Timestamp dataHoraProcessamentoEntradaEstoque) {
		DataHoraProcessamentoEntradaEstoque = dataHoraProcessamentoEntradaEstoque;
	}

	public Timestamp getDataHoraProcessamentoEntradaEstoque() {
		return DataHoraProcessamentoEntradaEstoque;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor;
	}
}