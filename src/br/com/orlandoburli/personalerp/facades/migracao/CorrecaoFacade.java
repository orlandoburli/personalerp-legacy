package br.com.orlandoburli.personalerp.facades.migracao;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaCadastroFacade;

@IgnoreFacadeAuthentication
public class CorrecaoFacade extends BaseCadastroFlexFacade<RomaneioMadeiraSerradaVO, RomaneioMadeiraSerradaDAO> {

	private static final long serialVersionUID = 1L;
	
	public void execute() {
		limpaestoque();
		processaRomaneios();
	}
	
	public void limpaestoque() {
		GenericDAO _dao = new GenericDAO();
		try {
			_dao.setAutoCommit(false);
			_dao.executeUpdate("UPDATE personalerp.ItemRomaneioMadeiraSerrada " +
					"SET " +
					" CodigoEmpresaEstoque = NULL, " +
					" CodigoLojaEstoque = NULL, " +
					" CodigoMovimentacaoEstoque = NULL");
			
			_dao.executeUpdate("DELETE FROM personalerp.MovimentacaoEstoque ");
			
			_dao.executeUpdate("DELETE FROM personalerp.Estoque ");
			
			_dao.executeUpdate("UPDATE personalerp.RomaneioMadeiraSerrada SET StatusRomaneio = 'N'");
			
			_dao.commit();
		} catch (Exception e) {
			_dao.rollback();
			writeIso88591(e.getMessage());
		}
	}
	
	public void processaRomaneios() {
		try {
			RomaneioMadeiraSerradaCadastroFacade cadastro = new RomaneioMadeiraSerradaCadastroFacade(request, response, context, "processar");
			
			// Buscar romaneios de 2011
			RomaneioMadeiraSerradaVO _filter = new RomaneioMadeiraSerradaVO();
			_filter.setAnoRomaneio(2011);
			RomaneioMadeiraSerradaDAO daoRomaneio = new RomaneioMadeiraSerradaDAO();
			
			List<RomaneioMadeiraSerradaVO> list = daoRomaneio.getList(_filter);
			
			for (RomaneioMadeiraSerradaVO romaneio : list) {
				cadastro.setEmpresasessao(getEmpresasessao());
				cadastro.setLojasessao(getLojasessao());
				cadastro.setMethodName("processar");
				cadastro.setOperacao("processar");
				cadastro.setUsuariosessao(getUsuariosessao());

				cadastro.setVo(romaneio);
				cadastro.processar();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
