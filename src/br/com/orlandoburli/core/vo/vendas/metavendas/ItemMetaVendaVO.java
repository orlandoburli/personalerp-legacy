package br.com.orlandoburli.core.vo.vendas.metavendas;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemMetaVendaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	@Ignore
	public static final String TIPO_META_VALOR = "V";
	@Ignore
	public static final String TIPO_META_QUANTIDADE = "Q";
	
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoMetaVenda;
	@Key
	@AutoIncrement
	private Integer CodigoItemMetaVenda;
	
	private Integer ReferenciaMetaVenda;
	
	private String TipoItemMetaVenda;
	
	private Integer CodigoNivelMetaVenda;
	
	@Precision(decimals=2)
	private BigDecimal ValorMetaVenda;
	@Precision(decimals=2)
	private BigDecimal PremioMetaVenda;
	
	@Join(entityName="NivelMetaVenda", foreignColumnName="NomeNivelMetaVenda", foreignKeys="CodigoNivelMetaVenda", localKeys="CodigoNivelMetaVenda")
	private String NomeNivelMetaVenda;
	@Join(entityName="NivelMetaVenda", foreignColumnName="PesoNivelMetaVenda", foreignKeys="CodigoNivelMetaVenda", localKeys="CodigoNivelMetaVenda")
	private Integer PesoNivelMetaVenda;
	
	@Join(entityName="Grupo", foreignColumnName="NomeGrupo", foreignKeys="CodigoGrupo", localKeys="ReferenciaMetaVenda")
	private String NomeGrupo;
	
	@Ignore
	private String DescricaoReferenciaMetaVenda;
	
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

	public Integer getCodigoMetaVenda() {
		return CodigoMetaVenda;
	}

	public void setCodigoMetaVenda(Integer codigoMetaVenda) {
		CodigoMetaVenda = codigoMetaVenda;
	}

	public Integer getCodigoItemMetaVenda() {
		return CodigoItemMetaVenda;
	}

	public void setCodigoItemMetaVenda(Integer codigoItemMetaVenda) {
		CodigoItemMetaVenda = codigoItemMetaVenda;
	}

	public Integer getReferenciaMetaVenda() {
		return ReferenciaMetaVenda;
	}

	public void setReferenciaMetaVenda(Integer referenciaMetaVenda) {
		ReferenciaMetaVenda = referenciaMetaVenda;
	}

	public String getTipoItemMetaVenda() {
		return TipoItemMetaVenda;
	}

	public void setTipoItemMetaVenda(String tipoItemMetaVenda) {
		TipoItemMetaVenda = tipoItemMetaVenda;
	}

	public Integer getCodigoNivelMetaVenda() {
		return CodigoNivelMetaVenda;
	}

	public void setCodigoNivelMetaVenda(Integer codigoNivelMetaVenda) {
		CodigoNivelMetaVenda = codigoNivelMetaVenda;
	}

	public BigDecimal getValorMetaVenda() {
		return ValorMetaVenda;
	}

	public void setValorMetaVenda(BigDecimal valorMetaVenda) {
		ValorMetaVenda = valorMetaVenda;
	}

	public BigDecimal getPremioMetaVenda() {
		return PremioMetaVenda;
	}

	public void setPremioMetaVenda(BigDecimal premioMetaVenda) {
		PremioMetaVenda = premioMetaVenda;
	}

	public void setNomeNivelMetaVenda(String nomeNivelMetaVenda) {
		NomeNivelMetaVenda = nomeNivelMetaVenda;
	}

	public String getNomeNivelMetaVenda() {
		return NomeNivelMetaVenda;
	}

	public void setDescricaoReferenciaMetaVenda(String descricaoReferenciaMetaVenda) {
		DescricaoReferenciaMetaVenda = descricaoReferenciaMetaVenda;
	}

	public String getDescricaoReferenciaMetaVenda() {
		return DescricaoReferenciaMetaVenda;
	}

	public void setNomeGrupo(String nomeGrupo) {
		NomeGrupo = nomeGrupo;
	}

	public String getNomeGrupo() {
		return NomeGrupo;
	}

	public void setPesoNivelMetaVenda(Integer pesoNivelMetaVenda) {
		PesoNivelMetaVenda = pesoNivelMetaVenda;
	}

	public Integer getPesoNivelMetaVenda() {
		return PesoNivelMetaVenda;
	}
}