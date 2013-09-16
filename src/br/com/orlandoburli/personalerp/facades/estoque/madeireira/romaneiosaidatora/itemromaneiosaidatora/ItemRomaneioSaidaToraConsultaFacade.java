package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiosaidatora.itemromaneiosaidatora;

import java.math.BigDecimal;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiosaidatora.ItemRomaneioSaidaToraDAO;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora.ItemRomaneioSaidaToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemRomaneioSaidaToraConsultaFacade extends BaseConsultaFlexFacade<ItemRomaneioSaidaToraVO, ItemRomaneioSaidaToraDAO> {

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
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioSaidaToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioSaidaToraVO.class;
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