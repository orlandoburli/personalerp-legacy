package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiotora.itemromaneiotora;

import java.math.BigDecimal;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemRomaneioToraConsultaFacade extends BaseConsultaFlexFacade<ItemRomaneioToraVO, ItemRomaneioToraDAO> {

	private static final long serialVersionUID = 1L;
	
	@Precision(decimals=3)
	private BigDecimal DiametroDe;
	@Precision(decimals=3)
	private BigDecimal DiametroAte;
	@Precision(decimals=3)
	private BigDecimal ComprimentoDe;
	@Precision(decimals=3)
	private BigDecimal ComprimentoAte;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeEssencia(getFiltro() + "%");
		
		StringBuilder sb = new StringBuilder();
		
		if (DiametroDe != null && DiametroDe.compareTo(BigDecimal.ZERO) > 0) {
			sb.append(sb.length()==0?"":" AND ");
			sb.append(" DiametroTora >= " + DiametroDe.toString());
		}
		if (DiametroAte != null && DiametroAte.compareTo(BigDecimal.ZERO) > 0) {
			sb.append(sb.length()==0?"":" AND ");
			sb.append(" DiametroTora <= " + DiametroAte.toString());
		}
		if (ComprimentoDe != null && ComprimentoDe.compareTo(BigDecimal.ZERO) > 0) {
			sb.append(sb.length()==0?"":" AND ");
			sb.append(" ComprimentoTora >= " + ComprimentoDe.toString());
		}
		if (ComprimentoAte != null && ComprimentoAte.compareTo(BigDecimal.ZERO) > 0) {
			sb.append(sb.length()==0?"":" AND ");
			sb.append(" ComprimentoTora <= " + ComprimentoAte.toString());
		}
		this.dao.setSpecialCondition(sb.toString());
		setOrderFields("NumeroPlaquetaRomaneio");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioToraVO.class;
	}

	public void setDiametroDe(BigDecimal diametroDe) {
		DiametroDe = diametroDe;
	}

	public BigDecimal getDiametroDe() {
		return DiametroDe;
	}

	public void setDiametroAte(BigDecimal diametroAte) {
		DiametroAte = diametroAte;
	}

	public BigDecimal getDiametroAte() {
		return DiametroAte;
	}

	public void setComprimentoDe(BigDecimal comprimentoDe) {
		ComprimentoDe = comprimentoDe;
	}

	public BigDecimal getComprimentoDe() {
		return ComprimentoDe;
	}

	public void setComprimentoAte(BigDecimal comprimentoAte) {
		ComprimentoAte = comprimentoAte;
	}

	public BigDecimal getComprimentoAte() {
		return ComprimentoAte;
	}
}