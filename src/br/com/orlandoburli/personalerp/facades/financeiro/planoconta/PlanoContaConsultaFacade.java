package br.com.orlandoburli.personalerp.facades.financeiro.planoconta;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PlanoContaConsultaFacade extends BaseConsultaFlexFacade<PlanoContaVO, PlanoContaDAO> {

	public static final String EXIBICAO_GRADE = "normal";
	public static final String EXIBICAO_ARVORE = "arvore";
	
	private static final long serialVersionUID = 1L;
	private String TipoExibicao;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomePlanoConta(getFiltro() + "%");
		setOrderFields("NumeroPlanoConta");
	}
	
	@Override
	public void execute() {
		if (getTipoExibicao() == null || getTipoExibicao().trim().equals("") || getTipoExibicao().equals(EXIBICAO_GRADE)) {
			super.execute();
		} else if (getTipoExibicao().equals(EXIBICAO_ARVORE)) {
			try {
				setListSource(this.dao.getList(this.getFilter()));
				
				String retorno = "<?xml version='1.0' encoding='utf-8'?><list>";
				
				for (PlanoContaVO planocontapai : getListSource()) {
					if (planocontapai.getCodigoPlanoContaPai() == null) {
						retorno += "<planoconta NomePlanoConta=\"" + planocontapai.getNumeroPlanoConta() + " - " + planocontapai.getNomePlanoConta() + "\" ";
						retorno += " CodigoPlanoConta=\"" + planocontapai.getCodigoPlanoConta() + "\" ";
						retorno += ">";
						retorno += getChild(getListSource(), planocontapai);
						retorno += "</planoconta>\n";
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
	
	@Override
	public void doBeforeWrite() {
		for (PlanoContaVO i : getListSource()) {
			i.setNumeroPlanoConta(i.getNumeroPlanoConta().replace(".", "*"));
		}
		super.doBeforeWrite();
	}
	
	private String getChild(List<PlanoContaVO> list, PlanoContaVO planocontapai) {
		String retorno = "";
		for (PlanoContaVO planocontachild : list) {
			if (planocontachild.getCodigoPlanoContaPai() != null && planocontachild.getCodigoPlanoContaPai().equals(planocontapai.getCodigoPlanoConta())) {
				
				retorno += "<planoconta NomePlanoConta=\"" + planocontachild.getNumeroPlanoConta() + " - " + planocontachild.getNomePlanoConta() + "\" ";
				retorno += " CodigoPlanoConta=\"" + planocontachild.getCodigoPlanoConta() + "\" ";
				retorno += ">";
				
				if (planocontachild.getCountChild() > 0) {
					retorno += getChild(getListSource(), planocontachild);
				}
				retorno += "</planoconta>\n";
			}
		}
		return retorno;
	}

	@Override
	protected Class<?> getDAOClass() {
		return PlanoContaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PlanoContaVO.class;
	}

	public void setTipoExibicao(String tipoExibicao) {
		TipoExibicao = tipoExibicao;
	}

	public String getTipoExibicao() {
		return TipoExibicao;
	}
}