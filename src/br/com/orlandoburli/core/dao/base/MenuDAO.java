package br.com.orlandoburli.core.dao.base;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.base.MenuVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends BaseCadastroDAO<MenuVO> {

	private List<MenuVO> listok;
	
    public List<MenuVO> getListMenusPerfil(PerfilVO perfil) throws SQLException {
    	setAutoCommit(false);
        //List<MenuVO> list = new ArrayList<MenuVO>();
    	listok = new ArrayList<MenuVO>();
        List<MenuVO> listAux = null;

        // Busca o menu principal
        MenuVO filter = new MenuVO();
        filter.setCodigoMenu(perfil.getCodigoMenu());
        listAux = getList(filter, "ordemmenu");
        listok.addAll(listAux);

        filter.setCodigoMenu(0);
        filter.setCodigoMenuPai(perfil.getCodigoMenu());
        //listok.addAll(getListSub(filter));
        getListSub(filter);
        
        commit();
        return listok;
    }

    public List<MenuVO> getListSub(MenuVO filter) throws SQLException {
        List<MenuVO> list = getList(filter, "ordemmenu");
        listok.addAll(list);
        try {
        for (MenuVO menu : list) {
            if (menu.getCountChild() > 0) {
                MenuVO filter2 = new MenuVO();
                filter2.setCodigoMenuPai(menu.getCodigoMenu());
                getListSub(filter2);
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}