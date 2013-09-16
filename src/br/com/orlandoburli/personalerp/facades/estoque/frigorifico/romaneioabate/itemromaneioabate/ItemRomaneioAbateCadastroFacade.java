package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneioabate.itemromaneioabate;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.CurralDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.romaneioabate.ItemRomaneioAbateDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.ItemRomaneioAbateVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.RomaneioAbateVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ItemRomaneioAbateCadastroFacade extends BaseCadastroFlexFacade<ItemRomaneioAbateVO, ItemRomaneioAbateDAO>{

	private static final long serialVersionUID = 1L;

	public ItemRomaneioAbateCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto", "Produto"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		try {
			RomaneioAbateVO romaneio = new RomaneioAbateVO();
			romaneio.setCodigoEmpresa(getVo().getCodigoEmpresa());
			romaneio.setCodigoLoja(getVo().getCodigoLoja());
			romaneio.setCodigoRomaneio(getVo().getCodigoRomaneio());
			romaneio = (RomaneioAbateVO) getGenericDao().get(romaneio);
			
			if (romaneio.getStatusRomaneio().equals("P")) {
				this.messages.add(new MessageVO("Romaneio processado, não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		
		if (getVo().getPesoBanda1ItemRomaneio() == null || getVo().getPesoBanda1ItemRomaneio().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Peso da Banda 1 deve ser maior que zero", "PesoBanda1ItemRomaneio"));
			return false;
		}
		
		if (getVo().getPesoBanda2ItemRomaneio() == null || getVo().getPesoBanda2ItemRomaneio().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Peso da Banda 2 deve ser maior que zero", "PesoBanda2ItemRomaneio"));
			return false;
		}
		
		if (getVo().getValorKgItemRomaneio() == null || getVo().getValorKgItemRomaneio().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("O Valor / Kg deve ser maior que zero", "ValorKgItemRomaneio"));
			return false;
		}

		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		try {
			RomaneioAbateVO romaneio = new RomaneioAbateVO();
			romaneio.setCodigoEmpresa(getVo().getCodigoEmpresa());
			romaneio.setCodigoLoja(getVo().getCodigoLoja());
			romaneio.setCodigoRomaneio(getVo().getCodigoRomaneio());
			romaneio = (RomaneioAbateVO) getGenericDao().get(romaneio);
			
			if (romaneio.getStatusRomaneio().equals("P")) {
				this.messages.add(new MessageVO("Romaneio processado, não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		return super.doBeforeDelete();
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoDAO _dao = new ProdutoDAO();
			write(Utils.voToXml(_dao.getList(), _dao.getListCount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void currais() {
		try {
			CurralDAO _dao = new CurralDAO();
			write(Utils.voToXml(_dao.getList(), _dao.getListCount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}