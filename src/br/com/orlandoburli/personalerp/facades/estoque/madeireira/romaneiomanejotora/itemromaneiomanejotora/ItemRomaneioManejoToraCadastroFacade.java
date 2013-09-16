package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomanejotora.itemromaneiomanejotora;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomanejotora.ItemRomaneioManejoToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomanejotora.ItemRomaneioManejoToraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ItemRomaneioManejoToraCadastroFacade extends BaseCadastroFlexFacade<ItemRomaneioManejoToraVO, ItemRomaneioManejoToraDAO>{

	private static final long serialVersionUID = 1L;
	
	public ItemRomaneioManejoToraCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoEssencia", "Essência"));
		addNewValidator(new NotEmptyValidator("DiametroTora", "Diâmetro"));
		addNewValidator(new NotEmptyValidator("ComprimentoTora", "Comprimento"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (!super.doBeforeSave()) {
			return false;
		}
		if (getVo().getDiametroTora().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("O diâmetro deve ser maior que zero!", "DiametroTora"));
			return false;
		}
		if (getVo().getComprimentoTora().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("O comprimento deve ser maior que zero!", "ComprimentoTora"));
			return false;
		}
		
		BigDecimal volume = getVo().getDiametroTora()
								.multiply(getVo().getDiametroTora())
								.multiply(getVo().getComprimentoTora())
								.multiply(ItemRomaneioToraVO.CONSTANTE_VOLUME_TORA)
								.setScale(3, BigDecimal.ROUND_HALF_DOWN);
		getVo().setVolumeTora(volume);
		
		return true;
	}
	
	@IgnoreMethodAuthentication
	public void essencias() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new EssenciaVO()), getGenericDao().getListCount(new EssenciaVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}