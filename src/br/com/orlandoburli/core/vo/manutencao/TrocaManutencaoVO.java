package br.com.orlandoburli.core.vo.manutencao;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TrocaManutencaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoTrocaManutencao;
	
	private String NumeroTrocaManutencao;
	private Date DataEntradaTrocaManutencao;
	private String NomeClienteTrocaManutencao;
	
	private String Fone1ClienteTrocaManutencao;
	private String Fone2ClienteTrocaManutencao;
	private String Fone3ClienteTrocaManutencao;
	
	private String TipoFone1ClienteTrocaManutencao;
	private String TipoFone2ClienteTrocaManutencao;
	private String TipoFone3ClienteTrocaManutencao;
	
	private String EmailClienteTrocaManutencao;
	private String ReferenciaTrocaManutencao;
	private String DescricaoTrocaManutencao;
	private Date PrazoTrocaManutencao;
	
	private String Email2TrocaManutencao;
	private String Email3TrocaManutencao;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT c.DescricaoSituacaoTrocaManutencao " +
            "  FROM personalerp.HistoricoTrocaManutencao b " + 
            "  JOIN personalerp.SituacaoTrocaManutencao c ON c.CodigoSituacaoTrocaManutencao = b.CodigoSituacaoTrocaManutencao " +
            " WHERE a.CodigoEmpresa = b.CodigoEmpresa " + 
            "   AND a.CodigoLoja = b.CodigoLoja" + 
            "   AND a.CodigoTrocaManutencao = b.CodigoTrocaManutencao ORDER BY b.DataHoraHistoricoTrocaManutencao DESC LIMIT 1)")
    private String UltimaSituacaoTrocaManutencao;
    
    @Formula(getFormula="(SELECT b.CodigoSituacaoTrocaManutencao " +
            "  FROM personalerp.HistoricoTrocaManutencao b " + 
            " WHERE a.CodigoEmpresa = b.CodigoEmpresa " + 
            "   AND a.CodigoLoja = b.CodigoLoja" + 
            "   AND a.CodigoTrocaManutencao = b.CodigoTrocaManutencao ORDER BY b.DataHoraHistoricoTrocaManutencao DESC LIMIT 1)")
    private Integer CodigoUltimaSituacaoTrocaManutencao;
    
    @Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b WHERE b.CodigoEmpresa = a.CodigoEmpresaProduto AND b.CodigoLoja = a.CodigoLojaProduto AND b.CodigoProduto = a.CodigoProduto)")
    private String DescricaoProduto;

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

	public Integer getCodigoTrocaManutencao() {
		return CodigoTrocaManutencao;
	}

	public void setCodigoTrocaManutencao(Integer codigoTrocaManutencao) {
		CodigoTrocaManutencao = codigoTrocaManutencao;
	}

	public String getNumeroTrocaManutencao() {
		return NumeroTrocaManutencao;
	}

	public void setNumeroTrocaManutencao(String numeroTrocaManutencao) {
		NumeroTrocaManutencao = numeroTrocaManutencao;
	}

	public Date getDataEntradaTrocaManutencao() {
		return DataEntradaTrocaManutencao;
	}

	public void setDataEntradaTrocaManutencao(Date dataEntradaTrocaManutencao) {
		DataEntradaTrocaManutencao = dataEntradaTrocaManutencao;
	}

	public String getNomeClienteTrocaManutencao() {
		return NomeClienteTrocaManutencao;
	}

	public void setNomeClienteTrocaManutencao(String nomeClienteTrocaManutencao) {
		NomeClienteTrocaManutencao = nomeClienteTrocaManutencao;
	}

	public String getFone1ClienteTrocaManutencao() {
		return Fone1ClienteTrocaManutencao;
	}

	public void setFone1ClienteTrocaManutencao(String fone1ClienteTrocaManutencao) {
		Fone1ClienteTrocaManutencao = fone1ClienteTrocaManutencao;
	}

	public String getFone2ClienteTrocaManutencao() {
		return Fone2ClienteTrocaManutencao;
	}

	public void setFone2ClienteTrocaManutencao(String fone2ClienteTrocaManutencao) {
		Fone2ClienteTrocaManutencao = fone2ClienteTrocaManutencao;
	}

	public String getFone3ClienteTrocaManutencao() {
		return Fone3ClienteTrocaManutencao;
	}

	public void setFone3ClienteTrocaManutencao(String fone3ClienteTrocaManutencao) {
		Fone3ClienteTrocaManutencao = fone3ClienteTrocaManutencao;
	}

	public String getTipoFone1ClienteTrocaManutencao() {
		return TipoFone1ClienteTrocaManutencao;
	}

	public void setTipoFone1ClienteTrocaManutencao(String tipoFone1ClienteTrocaManutencao) {
		TipoFone1ClienteTrocaManutencao = tipoFone1ClienteTrocaManutencao;
	}

	public String getTipoFone2ClienteTrocaManutencao() {
		return TipoFone2ClienteTrocaManutencao;
	}

	public void setTipoFone2ClienteTrocaManutencao(String tipoFone2ClienteTrocaManutencao) {
		TipoFone2ClienteTrocaManutencao = tipoFone2ClienteTrocaManutencao;
	}

	public String getTipoFone3ClienteTrocaManutencao() {
		return TipoFone3ClienteTrocaManutencao;
	}

	public void setTipoFone3ClienteTrocaManutencao(String tipoFone3ClienteTrocaManutencao) {
		TipoFone3ClienteTrocaManutencao = tipoFone3ClienteTrocaManutencao;
	}

	public String getEmailClienteTrocaManutencao() {
		return EmailClienteTrocaManutencao;
	}

	public void setEmailClienteTrocaManutencao(String emailClienteTrocaManutencao) {
		EmailClienteTrocaManutencao = emailClienteTrocaManutencao;
	}

	public String getReferenciaTrocaManutencao() {
		return ReferenciaTrocaManutencao;
	}

	public void setReferenciaTrocaManutencao(String referenciaTrocaManutencao) {
		ReferenciaTrocaManutencao = referenciaTrocaManutencao;
	}

	public String getDescricaoTrocaManutencao() {
		return DescricaoTrocaManutencao;
	}

	public void setDescricaoTrocaManutencao(String descricaoTrocaManutencao) {
		DescricaoTrocaManutencao = descricaoTrocaManutencao;
	}

	public Date getPrazoTrocaManutencao() {
		return PrazoTrocaManutencao;
	}

	public void setPrazoTrocaManutencao(Date prazoTrocaManutencao) {
		PrazoTrocaManutencao = prazoTrocaManutencao;
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

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setUltimaSituacaoTrocaManutencao(String ultimaSituacaoTrocaManutencao) {
		UltimaSituacaoTrocaManutencao = ultimaSituacaoTrocaManutencao;
	}

	public String getUltimaSituacaoTrocaManutencao() {
		return UltimaSituacaoTrocaManutencao;
	}

	public void setCodigoUltimaSituacaoTrocaManutencao(Integer codigoUltimaSituacaoTrocaManutencao) {
		CodigoUltimaSituacaoTrocaManutencao = codigoUltimaSituacaoTrocaManutencao;
	}

	public Integer getCodigoUltimaSituacaoTrocaManutencao() {
		return CodigoUltimaSituacaoTrocaManutencao;
	}

	public String getDescricaoProduto() {
		return DescricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}

	public String getEmail2TrocaManutencao() {
		return Email2TrocaManutencao;
	}

	public void setEmail2TrocaManutencao(String email2TrocaManutencao) {
		Email2TrocaManutencao = email2TrocaManutencao;
	}

	public String getEmail3TrocaManutencao() {
		return Email3TrocaManutencao;
	}

	public void setEmail3TrocaManutencao(String email3TrocaManutencao) {
		Email3TrocaManutencao = email3TrocaManutencao;
	}
}