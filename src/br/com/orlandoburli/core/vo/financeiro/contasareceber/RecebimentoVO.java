package br.com.orlandoburli.core.vo.financeiro.contasareceber;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.financeiro.ChequeRecebidoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.RecebimentoCartaoVO;

public class RecebimentoVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoRecebimento;
	
	private Integer CodigoFormaRecebimento;
	private Timestamp DataHoraRecebimento;
	@Precision(decimals=2)
	private BigDecimal ValorRecebimento;
	private String ObservacaoRecebimento;
	
	@Formula(getFormula="(SELECT b.NomeFormaRecebimento FROM [schema].FormaRecebimento b WHERE a.CodigoFormaRecebimento = b.CodigoFormaRecebimento)")
	private String NomeFormaRecebimento;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    /* Auxiliares da tela de recebimento **/
    @Ignore
    private MovimentacaoContaBancariaVO movimentacao;
    @Ignore
    private ChequeRecebidoVO cheque;
    @Ignore
    private RecebimentoCartaoVO recebimentocartao;
    
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

	/**
	 * @return the codigoEmpresa
	 */
	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the codigoLoja
	 */
	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	/**
	 * @param codigoLoja the codigoLoja to set
	 */
	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	/**
	 * @return the codigoRecebimento
	 */
	public Integer getCodigoRecebimento() {
		return CodigoRecebimento;
	}

	/**
	 * @param codigoRecebimento the codigoRecebimento to set
	 */
	public void setCodigoRecebimento(Integer codigoRecebimento) {
		CodigoRecebimento = codigoRecebimento;
	}

	/**
	 * @return the codigoFormaRecebimento
	 */
	public Integer getCodigoFormaRecebimento() {
		return CodigoFormaRecebimento;
	}

	/**
	 * @param codigoFormaRecebimento the codigoFormaRecebimento to set
	 */
	public void setCodigoFormaRecebimento(Integer codigoFormaRecebimento) {
		CodigoFormaRecebimento = codigoFormaRecebimento;
	}

	/**
	 * @return the dataHoraRecebimento
	 */
	public Timestamp getDataHoraRecebimento() {
		return DataHoraRecebimento;
	}

	/**
	 * @param dataHoraRecebimento the dataHoraRecebimento to set
	 */
	public void setDataHoraRecebimento(Timestamp dataHoraRecebimento) {
		DataHoraRecebimento = dataHoraRecebimento;
	}

	/**
	 * @return the valorRecebimento
	 */
	public BigDecimal getValorRecebimento() {
		return ValorRecebimento;
	}

	/**
	 * @param valorRecebimento the valorRecebimento to set
	 */
	public void setValorRecebimento(BigDecimal valorRecebimento) {
		ValorRecebimento = valorRecebimento;
	}

	/**
	 * @return the observacaoRecebimento
	 */
	public String getObservacaoRecebimento() {
		return ObservacaoRecebimento;
	}

	/**
	 * @param observacaoRecebimento the observacaoRecebimento to set
	 */
	public void setObservacaoRecebimento(String observacaoRecebimento) {
		ObservacaoRecebimento = observacaoRecebimento;
	}

	public void setNomeFormaRecebimento(String nomeFormaRecebimento) {
		NomeFormaRecebimento = nomeFormaRecebimento;
	}

	public String getNomeFormaRecebimento() {
		return NomeFormaRecebimento;
	}

	public void setMovimentacao(MovimentacaoContaBancariaVO movimentacao) {
		this.movimentacao = movimentacao;
	}

	public MovimentacaoContaBancariaVO getMovimentacao() {
		return movimentacao;
	}

	public void setCheque(ChequeRecebidoVO cheque) {
		this.cheque = cheque;
	}

	public ChequeRecebidoVO getCheque() {
		return cheque;
	}
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
	}

	public void setRecebimentocartao(RecebimentoCartaoVO recebimentocartao) {
		this.recebimentocartao = recebimentocartao;
	}

	public RecebimentoCartaoVO getRecebimentocartao() {
		return recebimentocartao;
	}
}