package br.com.orlandoburli.personalerp.facades.geral;

import java.text.SimpleDateFormat;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class UtilsFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	@IgnoreMethodAuthentication
	public void currentdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		write(sdf.format(Utils.getToday()));
	}
}