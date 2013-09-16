package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomanejotora;

import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomanejotora.RomaneioManejoToraDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomanejotora.RomaneioManejoToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioManejoToraConsultaFacade extends BaseConsultaFlexFacade<RomaneioManejoToraVO, RomaneioManejoToraDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		StringBuilder sb = new StringBuilder();
		try {
			RomaneioManejoToraVO filter2 = new RomaneioManejoToraVO();
			try {
				Integer numeroRomaneio = Integer.valueOf(getFiltro());
				if (numeroRomaneio > 0) {
					filter2.setNumeroRomaneio(numeroRomaneio);
				}
			} catch(NumberFormatException ex2) {}
			
			filter2.setNomeCliente(getFiltro() + "%");
			filter2.setDescricaoManejo(getFiltro() + "%");
			
			
			DaoUtils.voToFilter(filter2, sb, true, false);
			
			this.dao.setSpecialCondition(sb.toString());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}