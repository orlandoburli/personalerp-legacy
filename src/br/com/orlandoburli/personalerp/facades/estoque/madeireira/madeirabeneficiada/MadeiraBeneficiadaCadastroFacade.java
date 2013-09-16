package br.com.orlandoburli.personalerp.facades.estoque.madeireira.madeirabeneficiada;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.TipoMadeiraBeneficiadaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.TipoMadeiraBeneficiadaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MadeiraBeneficiadaCadastroFacade extends BaseCadastroFlexFacade<ProdutoVO, ProdutoDAO> {

	private static final long serialVersionUID = 1L;

	public MadeiraBeneficiadaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DescricaoProduto", "Descrição"));
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		this.getVo().setTipoEstoqueProduto("B"); // Tipo Madeira Beneficiada
		this.getVo().setPermiteVendaFracionadaProduto("N");
		
		// Verifica se ja nao existe o produto cadastrado com as medidas informadas
		ProdutoVO vo2 = new ProdutoVO();
		vo2.setAlturaProduto(getVo().getAlturaProduto());
		vo2.setLarguraProduto(getVo().getLarguraProduto());
		vo2.setComprimentoProduto(getVo().getComprimentoProduto());
		vo2.setCodigoEssencia(getVo().getCodigoEssencia());
		vo2.setCodigoTipoMadeiraBeneficiada(getVo().getCodigoTipoMadeiraBeneficiada());
		vo2.setTipoEstoqueProduto("B"); // Tipo BENEFICIADA
		boolean found = false;
		
		try {
			List<ProdutoVO> list = dao.getList(vo2);
			for (ProdutoVO item : list) {
				if (getVo().IsNew()) {
					found = true;
				} else {
					if (!item.getCodigoProduto().equals(getVo().getCodigoProduto()) || !item.getCodigoLoja().equals(getVo().getCodigoLoja()) || !item.getCodigoEmpresa().equals(getVo().getCodigoEmpresa())) {
						found = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (found) {
			this.messages.add(new MessageVO("Já existe um produto com essas medidas!"));
			return false;
		}
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		// Verifica se possui estoque
		EstoqueVO _filter = new EstoqueVO();
		_filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		_filter.setCodigoLoja(getVo().getCodigoLoja());
		_filter.setCodigoProduto(getVo().getCodigoProduto());
		EstoqueDAO _dao = new EstoqueDAO();
		
		try {
			List<EstoqueVO> list = _dao.getList(_filter);
			for (EstoqueVO estoque : list) {
				_dao.remove(estoque);
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage(), "Essencia"));
		}
		
		return super.doBeforeDelete();
	}
	
	@Override
	public void doAfterInsert() {
		// Cria o estoque para o produto na loja atual
		EstoqueVO estoque = new EstoqueVO();
		estoque.setCodigoEmpresa(getVo().getCodigoEmpresa());
		estoque.setCodigoLoja(getVo().getCodigoLoja());
		estoque.setCodigoProduto(getVo().getCodigoProduto());
		estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
		estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
		estoque.setEstoqueFiscal(BigDecimal.ZERO);
		estoque.setEstoqueFisico(BigDecimal.ZERO);
		estoque.setNewRecord(true);
		
		EstoqueDAO edao = new EstoqueDAO();
		
		try {
			edao.persist(estoque);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return ProdutoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ProdutoVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void essencias() {
		EssenciaDAO _dao = new EssenciaDAO();
		try {
			List<EssenciaVO> list = _dao.getList(new EssenciaVO(), "NomeEssencia");
			int count = _dao.getListCount(new EssenciaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void tipomadeirabeneficiadas() {
		TipoMadeiraBeneficiadaDAO _dao = new TipoMadeiraBeneficiadaDAO();
		try {
			List<TipoMadeiraBeneficiadaVO> list = _dao.getList(new TipoMadeiraBeneficiadaVO(), "NomeTipoMadeiraBeneficiada");
			int count = _dao.getListCount(new TipoMadeiraBeneficiadaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}