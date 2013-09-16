package br.com.orlandoburli.personalerp.facades.reports.rh.folhapagamento;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioFolhaPagamento2Facade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	public RelatorioFolhaPagamento2Facade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@Override
	public JRDataSource getDataSource() {
		return new JRDataSource() {
			@Override
			public boolean next() throws JRException {
				return false;
			}
			
			@Override
			public Object getFieldValue(JRField arg0) throws JRException {
				return null;
			}
		};
	}

	@Override
	protected String getJasperFileName() {
		return null;
	}
}