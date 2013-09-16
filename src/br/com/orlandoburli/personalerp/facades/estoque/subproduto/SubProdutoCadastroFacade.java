package br.com.orlandoburli.personalerp.facades.estoque.subproduto;

import java.sql.SQLException;
import br.com.orlandoburli.core.dao.estoque.SubProdutoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubProdutoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.core.web.framework.validators.NumericNotZeroValidator;

public class SubProdutoCadastroFacade extends BaseCadastroFlexFacade<SubProdutoVO, SubProdutoDAO> {

	private static final long serialVersionUID = 1L;

	public SubProdutoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto"));
		addNewValidator(new NotEmptyValidator("CodigoSubProduto", "SubProduto"));
		addNewValidator(new NumericNotZeroValidator("QuantidadeSubProduto", "Quantidade"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		boolean sucesso = super.doBeforeSave();
		if (!sucesso) {
			return false;
		}
		if (getVo().getCodigoProduto().equals(getVo().getCodigoSubProduto()) && getVo().getCodigoEmpresa().equals(getVo().getCodigoEmpresaSubProduto()) && getVo().getCodigoLoja().equals(getVo().getCodigoLojaSubProduto())) {
			this.messages.add(new MessageVO("Um produto não pode ser subproduto dele mesmo!", "SubProduto"));
			return false;
		}
		return true;
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("subproduto_pk") >= 0) {
			writeErrorMessage("Este Sub Produto já está cadastrado para este produto!");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoVO filter = new ProdutoVO();
			write(Utils.voToXml(getGenericDao().getList(filter), getGenericDao().getListCount(filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}