package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneiogado.itemromaneiogado;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.CurralDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.gado.ItemRomaneioGadoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado.ItemRomaneioGadoVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado.RomaneioGadoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ItemRomaneioGadoCadastroFacade extends BaseCadastroFlexFacade<ItemRomaneioGadoVO, ItemRomaneioGadoDAO>{

	private static final long serialVersionUID = 1L;

	public ItemRomaneioGadoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto", "Produto"));
		addNewValidator(new NotEmptyValidator("CodigoCurral", "Curral", "Curral"));
		addNewValidator(new NotEmptyValidator("QuantidadeItemRomaneio", "Quantidade"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (this.getVo().getQuantidadeItemRomaneio() != null && this.getVo().getQuantidadeItemRomaneio().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Quantidade deve ser maior que zero!", "QuantidadeItemRomaneio"));
			return false;
		}
		try {
			RomaneioGadoVO romaneio = new RomaneioGadoVO();
			romaneio.setCodigoEmpresa(getVo().getCodigoEmpresa());
			romaneio.setCodigoLoja(getVo().getCodigoLoja());
			romaneio.setCodigoRomaneio(getVo().getCodigoRomaneio());
			romaneio = (RomaneioGadoVO) getGenericDao().get(romaneio);
			
			if (romaneio.getStatusRomaneio().equals("P")) {
				this.messages.add(new MessageVO("Romaneio processado, não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		try {
			RomaneioGadoVO romaneio = new RomaneioGadoVO();
			romaneio.setCodigoEmpresa(getVo().getCodigoEmpresa());
			romaneio.setCodigoLoja(getVo().getCodigoLoja());
			romaneio.setCodigoRomaneio(getVo().getCodigoRomaneio());
			romaneio = (RomaneioGadoVO) getGenericDao().get(romaneio);
			
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