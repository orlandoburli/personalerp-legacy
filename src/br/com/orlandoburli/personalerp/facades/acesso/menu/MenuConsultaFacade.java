package br.com.orlandoburli.personalerp.facades.acesso.menu;


import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.base.MenuDAO;
import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MenuConsultaFacade extends BaseConsultaFlexFacade<MenuVO, MenuDAO>{

	public static final String EXIBICAO_GRADE = "normal";
	public static final String EXIBICAO_ARVORE = "arvore";
	
	private static final long serialVersionUID = 1L;
	private String TipoExibicao;
	
	@Override
	public void execute() {
		if (getTipoExibicao() == null || getTipoExibicao().trim().equals("") || getTipoExibicao().equals(EXIBICAO_GRADE)) {
			super.execute();
		} else if (getTipoExibicao().equals(EXIBICAO_ARVORE)) {
			try {
				setListSource(this.dao.getList(this.getFilter(), "OrdemMenu"));
				
				String retorno = "<?xml version='1.0' encoding='utf-8'?><list>";
				
				for (MenuVO menupai : getListSource()) {
					if (menupai.getCodigoMenuPai() == null) {
						retorno += "<menu NomeMenu=\"" + menupai.getNomeMenu() + "\" ";
						retorno += " CodigoMenu=\"" + menupai.getCodigoMenu() + "\" ";
						retorno += ">";
						retorno += getChild(getListSource(), menupai);
						retorno += "</menu>\n";
					}
				}
				
				retorno += "</list>";
				
				write(retorno);
				
			} catch (SQLException e1) {
				writeErrorMessage(e1.getMessage());
				dispatch();
				return;
			}
		}
	}
	
	private String getChild(List<MenuVO> list, MenuVO menupai) {
		String retorno = "";
		for (MenuVO menuchild : list) {
			if (menuchild.getCodigoMenuPai() != null && menuchild.getCodigoMenuPai().equals(menupai.getCodigoMenu())) {
				
				retorno += "<menu NomeMenu=\"" + menuchild.getNomeMenu() + "\" ";
				retorno += " CodigoMenu=\"" + menuchild.getCodigoMenu() + "\" ";
				retorno += ">";
				
				if (menuchild.getCountChild() > 0) {
					retorno += getChild(getListSource(), menuchild);
				}
				retorno += "</menu>\n";
			}
		}
		return retorno;
	}

	@Override
	protected Class<?> getDAOClass() {
		return MenuDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MenuVO.class;
	}

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeMenu("%" + getFiltro() + "%");
		setOrderFields("OrdemMenu");
	}

	public void setTipoExibicao(String tipoExibicao) {
		TipoExibicao = tipoExibicao;
	}

	public String getTipoExibicao() {
		return TipoExibicao;
	}
}