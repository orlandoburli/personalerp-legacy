package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomanejotora;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioManejoToraVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoRomaneio;
	
	private Integer NumeroRomaneio;
	private Date DataRomaneio;
	private Timestamp DataHoraLancamentoRomaneio;
	
	private Integer CodigoEmpresaMotorista;
	private Integer CodigoLojaMotorista;
	private Integer CodigoMotorista;
	
	private Integer CodigoEmpresaVeiculo;
	private Integer CodigoLojaVeiculo;
	private Integer CodigoVeiculo;
	
	private Integer CodigoEmpresaEnderecoCliente;
	private Integer CodigoLojaEnderecoCliente;
	private Integer CodigoPessoaCliente;
	private Integer CodigoEnderecoCliente;
	
	private Integer CodigoEmpresaEsplanada;
	private Integer CodigoLojaEsplanada;
	private Integer CodigoEsplanada;
	
	private Integer CodigoEmpresaManejo;
	private Integer CodigoLojaManejo;
	private Integer CodigoManejo;
	
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT \n" +
    		" b.NomeRazaoSocialPessoa || ' - ' || c.LogradouroEnderecoPessoa || ' ' || e.NomeCidade || '/' || f.SiglaEstado \n" +    		
    		"     FROM [schema].Pessoa b \n" +
    		
    		"     JOIN [schema].EnderecoPessoa c\n" +
    		"       ON c.CodigoEmpresa = b.CodigoEmpresa\n" +
    		"      AND c.CodigoLoja = b.CodigoLoja\n" +
    		"      AND c.CodigoPessoa = b.CodigoPessoa\n" +
    		
    		"LEFT JOIN [schema].Cidade e ON c.CodigoCidadeEnderecoPessoa = e.CodigoCidade \n" +
    		
			"LEFT JOIN [schema].Estado f ON e.CodigoEstado = f.CodigoEstado \n" +
			
    		"    WHERE b.CodigoEmpresa = a.CodigoEmpresaEnderecoCliente\n" +
    		"      AND b.CodigoLoja = a.CodigoLojaEnderecoCliente\n" +
    		"      AND b.CodigoPessoa = a.CodigoPessoaCliente\n" +
    		"      AND c.CodigoEnderecoPessoa = a.CodigoEnderecoCliente)")
    private String NomeCliente;
    
    @Join(entityName="ManejoTora", 
    		foreignColumnName="DescricaoManejo", 
    		foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoManejo"}, 
    		localKeys={"CodigoEmpresaManejo", "CodigoLojaManejo", "CodigoManejo"})
    private String DescricaoManejo;
    
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

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getNumeroRomaneio() {
		return NumeroRomaneio;
	}

	public void setNumeroRomaneio(Integer numeroRomaneio) {
		NumeroRomaneio = numeroRomaneio;
	}

	public Date getDataRomaneio() {
		return DataRomaneio;
	}

	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	public Timestamp getDataHoraLancamentoRomaneio() {
		return DataHoraLancamentoRomaneio;
	}

	public void setDataHoraLancamentoRomaneio(Timestamp dataHoraLancamentoRomaneio) {
		DataHoraLancamentoRomaneio = dataHoraLancamentoRomaneio;
	}

	public Integer getCodigoEmpresaMotorista() {
		return CodigoEmpresaMotorista;
	}

	public void setCodigoEmpresaMotorista(Integer codigoEmpresaMotorista) {
		CodigoEmpresaMotorista = codigoEmpresaMotorista;
	}

	public Integer getCodigoLojaMotorista() {
		return CodigoLojaMotorista;
	}

	public void setCodigoLojaMotorista(Integer codigoLojaMotorista) {
		CodigoLojaMotorista = codigoLojaMotorista;
	}

	public Integer getCodigoMotorista() {
		return CodigoMotorista;
	}

	public void setCodigoMotorista(Integer codigoMotorista) {
		CodigoMotorista = codigoMotorista;
	}

	public Integer getCodigoEmpresaVeiculo() {
		return CodigoEmpresaVeiculo;
	}

	public void setCodigoEmpresaVeiculo(Integer codigoEmpresaVeiculo) {
		CodigoEmpresaVeiculo = codigoEmpresaVeiculo;
	}

	public Integer getCodigoLojaVeiculo() {
		return CodigoLojaVeiculo;
	}

	public void setCodigoLojaVeiculo(Integer codigoLojaVeiculo) {
		CodigoLojaVeiculo = codigoLojaVeiculo;
	}

	public Integer getCodigoVeiculo() {
		return CodigoVeiculo;
	}

	public void setCodigoVeiculo(Integer codigoVeiculo) {
		CodigoVeiculo = codigoVeiculo;
	}

	public Integer getCodigoEmpresaEnderecoCliente() {
		return CodigoEmpresaEnderecoCliente;
	}

	public void setCodigoEmpresaEnderecoCliente(Integer codigoEmpresaEnderecoCliente) {
		CodigoEmpresaEnderecoCliente = codigoEmpresaEnderecoCliente;
	}

	public Integer getCodigoLojaEnderecoCliente() {
		return CodigoLojaEnderecoCliente;
	}

	public void setCodigoLojaEnderecoCliente(Integer codigoLojaEnderecoCliente) {
		CodigoLojaEnderecoCliente = codigoLojaEnderecoCliente;
	}

	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	public void setCodigoPessoaCliente(Integer codigoPessoaCliente) {
		CodigoPessoaCliente = codigoPessoaCliente;
	}

	public Integer getCodigoEnderecoCliente() {
		return CodigoEnderecoCliente;
	}

	public void setCodigoEnderecoCliente(Integer codigoEnderecoCliente) {
		CodigoEnderecoCliente = codigoEnderecoCliente;
	}

	public Integer getCodigoEmpresaEsplanada() {
		return CodigoEmpresaEsplanada;
	}

	public void setCodigoEmpresaEsplanada(Integer codigoEmpresaEsplanada) {
		CodigoEmpresaEsplanada = codigoEmpresaEsplanada;
	}

	public Integer getCodigoLojaEsplanada() {
		return CodigoLojaEsplanada;
	}

	public void setCodigoLojaEsplanada(Integer codigoLojaEsplanada) {
		CodigoLojaEsplanada = codigoLojaEsplanada;
	}

	public Integer getCodigoEsplanada() {
		return CodigoEsplanada;
	}

	public void setCodigoEsplanada(Integer codigoEsplanada) {
		CodigoEsplanada = codigoEsplanada;
	}

	public Integer getCodigoEmpresaManejo() {
		return CodigoEmpresaManejo;
	}

	public void setCodigoEmpresaManejo(Integer codigoEmpresaManejo) {
		CodigoEmpresaManejo = codigoEmpresaManejo;
	}

	public Integer getCodigoLojaManejo() {
		return CodigoLojaManejo;
	}

	public void setCodigoLojaManejo(Integer codigoLojaManejo) {
		CodigoLojaManejo = codigoLojaManejo;
	}

	public Integer getCodigoManejo() {
		return CodigoManejo;
	}

	public void setCodigoManejo(Integer codigoManejo) {
		CodigoManejo = codigoManejo;
	}

	public String getObservacaoRomaneio() {
		return ObservacaoRomaneio;
	}

	public void setObservacaoRomaneio(String observacaoRomaneio) {
		ObservacaoRomaneio = observacaoRomaneio;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setDescricaoManejo(String descricaoManejo) {
		DescricaoManejo = descricaoManejo;
	}

	public String getDescricaoManejo() {
		return DescricaoManejo;
	}
}