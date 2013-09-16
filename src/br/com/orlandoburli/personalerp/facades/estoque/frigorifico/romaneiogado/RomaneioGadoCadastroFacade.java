package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneiogado;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.ItemCurralDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.gado.ItemRomaneioGadoDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.gado.RomaneioGadoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.curral.ItemCurralVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado.ItemRomaneioGadoVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado.RomaneioGadoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class RomaneioGadoCadastroFacade extends BaseCadastroFlexFacade<RomaneioGadoVO, RomaneioGadoDAO>{

	private static final long serialVersionUID = 1L;
	
	public RomaneioGadoCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
		
		addNewValidator(new NotEmptyValidator("DataRomaneio", "Data do Romaneio"));
		addNewValidator(new NotEmptyValidator("CodigoPessoaEnderecoFornecedor", "Fornecedor", "Fornecedor"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		this.getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
		this.getVo().setStatusRomaneio("N");
		return super.doBeforeSave();
	}
	
	public void processar() {
		try {
			setVo(dao.get(getVo()));
			
			if (getVo().getStatusRomaneio().equals("P")) {
				writeErrorMessage("Romaneio já processado!");
				return;
			}
			
			ItemRomaneioGadoVO itemFilter = new ItemRomaneioGadoVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioGadoDAO itemDao = new ItemRomaneioGadoDAO();
			
			List<ItemRomaneioGadoVO> list = itemDao.getList(itemFilter);
			
			for (ItemRomaneioGadoVO item : list) {
				ItemCurralVO itemCurralFilter = new ItemCurralVO();
				itemCurralFilter.setCodigoEmpresa(getVo().getCodigoEmpresaEnderecoFornecedor());
				itemCurralFilter.setCodigoLojaPessoa(getVo().getCodigoLojaEnderecoFornecedor());
				itemCurralFilter.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
				
				itemCurralFilter.setCodigoEmpresa(item.getCodigoEmpresaCurral());
				itemCurralFilter.setCodigoLoja(item.getCodigoLojaCurral());
				itemCurralFilter.setCodigoCurral(item.getCodigoCurral());
				
				ItemCurralDAO itemCurralDao = new ItemCurralDAO();
				List<ItemCurralVO> listCurral = itemCurralDao.getList(itemCurralFilter);
				
				ItemCurralVO itemCurral = null;
				
				if (listCurral.size() > 0) {
					itemCurral = listCurral.get(0);
				} else {
					itemCurral = new ItemCurralVO();
					itemCurral.setNewRecord(true);
					itemCurral.setQuantidadeItemCurral(0);
					
					itemCurral.setCodigoEmpresa(getVo().getCodigoEmpresaEnderecoFornecedor());
					itemCurral.setCodigoLojaPessoa(getVo().getCodigoLojaEnderecoFornecedor());
					itemCurral.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
					
					itemCurral.setCodigoEmpresa(item.getCodigoEmpresaCurral());
					itemCurral.setCodigoLoja(item.getCodigoLojaCurral());
					itemCurral.setCodigoCurral(item.getCodigoCurral());
				}
				
				itemCurral.setQuantidadeItemCurral(itemCurral.getQuantidadeItemCurral() + item.getQuantidadeItemRomaneio().intValue());
				itemCurralDao.persist(itemCurral);
			}
			
			getVo().setStatusRomaneio("P"); // Processado
			dao.persist(getVo());
			write("ok");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void estornar() {
		try {
			setVo(dao.get(getVo()));
			
			if (getVo().getStatusRomaneio().equals("N")) {
				writeErrorMessage("Romaneio não processado!");
				return;
			}
			
			ItemRomaneioGadoVO itemFilter = new ItemRomaneioGadoVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioGadoDAO itemDao = new ItemRomaneioGadoDAO();
			
			List<ItemRomaneioGadoVO> list = itemDao.getList(itemFilter);
			
			for (ItemRomaneioGadoVO item : list) {
				ItemCurralVO itemCurralFilter = new ItemCurralVO();
				itemCurralFilter.setCodigoEmpresa(getVo().getCodigoEmpresaEnderecoFornecedor());
				itemCurralFilter.setCodigoLojaPessoa(getVo().getCodigoLojaEnderecoFornecedor());
				itemCurralFilter.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
				
				itemCurralFilter.setCodigoEmpresa(item.getCodigoEmpresaCurral());
				itemCurralFilter.setCodigoLoja(item.getCodigoLojaCurral());
				itemCurralFilter.setCodigoCurral(item.getCodigoCurral());
				
				ItemCurralDAO itemCurralDao = new ItemCurralDAO();
				List<ItemCurralVO> listCurral = itemCurralDao.getList(itemCurralFilter);
				
				ItemCurralVO itemCurral = null;
				
				if (listCurral.size() > 0) {
					itemCurral = listCurral.get(0);
					itemCurral.setQuantidadeItemCurral(itemCurral.getQuantidadeItemCurral() - item.getQuantidadeItemRomaneio().intValue());
					
					if (itemCurral.getQuantidadeItemCurral() <= 0) {
						itemCurralDao.remove(itemCurral);
					} else {
						itemCurralDao.persist(itemCurral);
					}
				}
			}
			
			getVo().setStatusRomaneio("N"); // Não Processado
			dao.persist(getVo());
			write("ok");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoas() {
		try {
			List<IValueObject> list = getGenericDao().getList(new EnderecoPessoaVO());
			int count = getGenericDao().getListCount(new EnderecoPessoaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}