package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomadeiraserrada.itemromaneiomadeiraserrada;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomadeiraserrada.ItemRomaneioMadeiraSerradaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.ItemRomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ItemRomaneioMadeiraSerradaCadastroFacade extends BaseCadastroFlexFacade<ItemRomaneioMadeiraSerradaVO, ItemRomaneioMadeiraSerradaDAO> {

	private static final long serialVersionUID = 1L;
	private Integer CodigoEssenciaPesquisa;
	@Precision(decimals=3)
	private BigDecimal ComprimentoProdutoPesquisa;
	@Precision(decimals=3)
	private BigDecimal LarguraProdutoPesquisa;
	@Precision(decimals=3)
	private BigDecimal AlturaProdutoPesquisa;

	public ItemRomaneioMadeiraSerradaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto"));
		addNewValidator(new NotEmptyValidator("QuantidadeItemRomaneio", "Quantidade"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getCodigoProduto() == null || getVo().getCodigoProduto() <= 0) {
			this.messages.add(new MessageVO("Produto n�o encontrado!", "Essencia"));
		}
		if (getVo().getQuantidadeItemRomaneio() != null && getVo().getQuantidadeItemRomaneio() <= 0) {
			this.messages.add(new MessageVO("Informe a quantidade!", "Quantidade"));
		}
		RomaneioMadeiraSerradaVO _vo = new RomaneioMadeiraSerradaVO();
		
		_vo.setCodigoRomaneio(getVo().getCodigoRomaneio());
		_vo.setCodigoEmpresa(getVo().getCodigoEmpresa());
		_vo.setCodigoLoja(getVo().getCodigoLoja());
		
		RomaneioMadeiraSerradaDAO _dao = new RomaneioMadeiraSerradaDAO();
		try {
			_vo = _dao.get(_vo);
			if (_vo.getStatusRomaneio().equals(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_PROCESSADO)) {
				this.messages.add(new MessageVO("Esse romaneio j� foi processado e n�o pode ser alterado!"));
				return false;
//			} else if (_vo.getStatusRomaneio().equals(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_ESTORNADO)) {
//				this.messages.add(new MessageVO("Esse romaneio foi estornado e n�o pode ser alterado!"));
//				return false;
			}
		} catch (SQLException e) {
			if (e.getMessage().indexOf("itemromaneiomadeiraserrada_produto_idx") >= 0) {
				this.messages.add(new MessageVO("Este produto j� foi lan�ado!"));
			} else {
				this.messages.add(new MessageVO(e.getMessage()));
				e.printStackTrace();
			}
			return false;
		}
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		RomaneioMadeiraSerradaVO _vo = new RomaneioMadeiraSerradaVO();
		
		_vo.setCodigoRomaneio(getVo().getCodigoRomaneio());
		_vo.setCodigoEmpresa(getVo().getCodigoEmpresa());
		_vo.setCodigoLoja(getVo().getCodigoLoja());
		
		RomaneioMadeiraSerradaDAO _dao = new RomaneioMadeiraSerradaDAO();
		try {
			// Verifica se o romaneio ja foi processado
			_vo = _dao.get(_vo);
			if (_vo.getStatusRomaneio().equals(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_PROCESSADO)) {
				this.messages.add(new MessageVO("Esse romaneio j� foi processado e n�o pode ser alterado!"));
				return false;
			} else if (_vo.getStatusRomaneio().equals(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_ESTORNADO)) {
				this.messages.add(new MessageVO("Esse romaneio foi estornado e n�o pode ser alterado!"));
				return false;
			}
			
			// Verifica se nao existe outro item de romaneio com este codigo
			
		} catch (SQLException e) {
			if (e.getMessage().indexOf("itemromaneiomadeiraserrada_produto_idx") >= 0) {
				this.messages.add(new MessageVO("Este produto j� foi lan�ado!"));
			} else {
				this.messages.add(new MessageVO(e.getMessage()));
				e.printStackTrace();
			}
			return false;
		}
		return super.doBeforeDelete();
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("itemromaneiomadeiraserrada_produto_idx") >= 0) {
			this.messages.add(new MessageVO("Este produto j� foi lan�ado!", "Essencia"));
		}
		super.doBeforeWriteSqlErro(e);
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setDataHoraLancamentoItemRomaneio(Utils.getNow());
		this.getVo().setStatusItemRomaneio(ItemRomaneioMadeiraSerradaVO.STATUS_NAOPROCESSADO);
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeUpdate() throws SQLException {
		// Se for alterado, a data do lancamento tambem muda
		this.getVo().setDataHoraLancamentoItemRomaneio(Utils.getNow());
		this.getVo().setStatusItemRomaneio(ItemRomaneioMadeiraSerradaVO.STATUS_NAOPROCESSADO);
		return super.doBeforeUpdate();
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioMadeiraSerradaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioMadeiraSerradaVO.class;
	}
	
	public static void calculaVolumeItem(ItemRomaneioMadeiraSerradaVO itemromaneio) {
		BigDecimal cem = new BigDecimal(100);
		BigDecimal volume = itemromaneio.getComprimentoProduto().multiply(itemromaneio.getAlturaProduto().divide(cem)).multiply(itemromaneio.getLarguraProduto().divide(cem));
		volume = volume.multiply(new BigDecimal(itemromaneio.getQuantidadeItemRomaneio())).setScale(3, BigDecimal.ROUND_HALF_EVEN);
		itemromaneio.setVolumeTotalItemRomaneio(volume);
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
	
	@IgnoreMethodAuthentication
	public void produto() {
		ProdutoVO _filter = new ProdutoVO();
		_filter.setCodigoEssencia(getCodigoEssenciaPesquisa());
		_filter.setLarguraProduto(getLarguraProdutoPesquisa());
		_filter.setAlturaProduto(getAlturaProdutoPesquisa());
		_filter.setComprimentoProduto(getComprimentoProdutoPesquisa());
		_filter.setTipoEstoqueProduto("S"); // Tipo SERRADA
		ProdutoDAO _dao = new ProdutoDAO();
		
		try {
			List<ProdutoVO> _list = _dao.getList(_filter);
			if (_list.size() == 1) {
				write(Utils.voToXml(_list.get(0)));
			} else {
				if (_list.size() > 1){
					writeErrorMessage("Produto2 duplicado! Corrija o cadastro antes de prosseguir!");
				} else {
					writeErrorMessage("Produto n�o encontrado!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getCodigoEssenciaPesquisa() {
		return CodigoEssenciaPesquisa;
	}

	public void setCodigoEssenciaPesquisa(Integer codigoEssenciaPesquisa) {
		CodigoEssenciaPesquisa = codigoEssenciaPesquisa;
	}

	public BigDecimal getComprimentoProdutoPesquisa() {
		return ComprimentoProdutoPesquisa;
	}

	public void setComprimentoProdutoPesquisa(BigDecimal comprimentoProdutoPesquisa) {
		ComprimentoProdutoPesquisa = comprimentoProdutoPesquisa;
	}

	public BigDecimal getLarguraProdutoPesquisa() {
		return LarguraProdutoPesquisa;
	}

	public void setLarguraProdutoPesquisa(BigDecimal larguraProdutoPesquisa) {
		LarguraProdutoPesquisa = larguraProdutoPesquisa;
	}

	public BigDecimal getAlturaProdutoPesquisa() {
		return AlturaProdutoPesquisa;
	}

	public void setAlturaProdutoPesquisa(BigDecimal alturaProdutoPesquisa) {
		AlturaProdutoPesquisa = alturaProdutoPesquisa;
	}
}