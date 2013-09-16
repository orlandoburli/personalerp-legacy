package br.com.orlandoburli.personalerp.facades.reports.cadastros;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.vo.utils.AuxiliarVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioUsuarioFacade extends BaseRelatorioFacade {
	
	public RelatorioUsuarioFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	private static final long serialVersionUID = 1L;
	private Integer OpcaoAtivo;

	public List<AuxiliarVO> getListOpcaoAtivo() {
		List<AuxiliarVO> list = new ArrayList<AuxiliarVO>();
		list.add(new AuxiliarVO("Somente ativos", "1"));
		list.add(new AuxiliarVO("Somente inativos", "2"));
		list.add(new AuxiliarVO("Todos", "3"));
		return list;
	}
	
	@Override
	protected String getJasperFileName() {
		return "reports/usuarios.jasper";
	}

	public void setOpcaoAtivo(Integer opcaoAtivo) {
		OpcaoAtivo = opcaoAtivo;
	}

	public Integer getOpcaoAtivo() {
		return OpcaoAtivo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("Ativo", getOpcaoAtivo());
		super.doParameter(parameters);
	}
}