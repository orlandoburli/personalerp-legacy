package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiotora.itemromaneiotora;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.core.web.framework.validators.RangeValidator;

public class ItemRomaneioToraCadastroFacade extends BaseCadastroFlexFacade<ItemRomaneioToraVO, ItemRomaneioToraDAO> {

	private static final long serialVersionUID = 1L;

	public ItemRomaneioToraCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NumeroPlaquetaRomaneio", "Número da Plaqueta"));
		addNewValidator(new NotEmptyValidator("CodigoEssencia", "Essência"));
		addNewValidator(new NotEmptyValidator("ComprimentoTora", "Comprimento"));
		addNewValidator(new NotEmptyValidator("DiametroTora", "Diametro"));
		addNewValidator(new RangeValidator("ComprimentoTora", "Comprimento", 0.001, null));
		addNewValidator(new RangeValidator("DiametroTora", "Diametro", 0.0, null));
	}
	
	public void estornar() {
		List<ItemRomaneioToraVO> list;
		try {
			list = dao.getList(getVo());
			if (list.size() == 1) {
				setVo(list.get(0));
				if (getVo().getFlagBaixaTora() != null && getVo().getFlagBaixaTora().equalsIgnoreCase("V")) {
					writeErrorMessage("Plaqueta baixada por venda! Não pode ser estornada!", "NumeroPlaquetaRomaneioTora");
				} else {
					getVo().setFlagBaixaTora("N");
					getVo().setDataHoraBaixaRomaneio(null);
					
					getVo().setCodigoEmpresaUsuarioBaixaRomaneio(null);
					getVo().setCodigoLojaUsuarioBaixaRomaneio(null);
					getVo().setCodigoUsuarioBaixaRomaneio(null);
					
					getVo().setCodigoEmpresaGerente(null);
					getVo().setCodigoLojaGerente(null);
					getVo().setCodigoGerente(null);
					
					dao.persist(getVo());
					write("ok");
				}
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioToraDAO.class;
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getVo().setCodigoLoja(getLojasessao().getCodigoLoja());
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (!super.doBeforeSave()) {
			return false;
		}
		try {
			ItemRomaneioToraVO _vo = dao.get(getVo());
			if (_vo != null && _vo.getFlagBaixaTora() != null && _vo.getFlagBaixaTora().equalsIgnoreCase("S")) {
				this.messages.add(new MessageVO("Essa tora já foi baixada e não pode ser alterada!", "Essencia"));
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getVo().setFlagBaixaTora("N");
		BigDecimal volume = getVo().getDiametroTora()
							.multiply(getVo().getDiametroTora())
							.multiply(getVo().getComprimentoTora())
							.multiply(ItemRomaneioToraVO.CONSTANTE_VOLUME_TORA)
							.setScale(3, BigDecimal.ROUND_HALF_DOWN);
		getVo().setVolumeTora(volume);
		return true;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioToraVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void essencias() {
		EssenciaDAO _dao = new EssenciaDAO();
		
		try {
			List<EssenciaVO> list = _dao.getList(new EssenciaVO(), "NomeEssencia");
			int count = _dao.getListCount(new EssenciaVO());
			String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
			for (EssenciaVO _vo : list) {
				xmlList += Utils.voToXml(_vo, false);
			}
			xmlList += "<count>" + count + "</count>";
			xmlList += "</list>";
			write(xmlList);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}