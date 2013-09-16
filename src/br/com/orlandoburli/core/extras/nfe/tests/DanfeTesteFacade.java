package br.com.orlandoburli.core.extras.nfe.tests;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class DanfeTesteFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getJasperFileName() {
		return "reports/danfe.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("LOGO_CLIENTE", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_cliente.jpg");
		parameters.put("PROTOCOLO_NFE", "12312312312312");
		super.doParameter(parameters);
	}
	
	@Override
	public JRDataSource getDataSource() {
		NFe nfe = new NfeTesteFacade().getNfe();
		return new JRBeanArrayDataSource(new NFe[]{nfe});
	}
}
