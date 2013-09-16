package br.com.orlandoburli.personalerp.facades.fiscal.sped;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.factory.arquivo.ArquivoSpedFactory;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class GerarSpedFiscalFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	public void execute() {
		LojaVO loja = new LojaVO();
		loja.setCodigoEmpresa(3);
		loja.setCodigoLoja(1);
		
		try {
			loja = new LojaDAO().get(loja);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicial = null;
		try {
			dataInicial = new Date(sdf.parse("01/04/2013").getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date dataFinal = null;
		
		try {
			dataFinal = new Date(sdf.parse("30/04/2013").getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			ArquivoSpedFiscal arquivo = ArquivoSpedFactory.getFactory().buildArquivoSped(loja, dataInicial, dataFinal);
			
			String conteudo = arquivo.buildConteudoArquivo();
			write(conteudo);
			NfeUtils.salvaArquivo("c:\\temp\\sped.txt", conteudo);
			
			
		} catch (SpedException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
}
