package br.com.orlandoburli.personalerp.facades.manutencao.trocamanutencao;

import java.sql.SQLException;

import org.apache.commons.mail.EmailException;

import br.com.orlandoburli.core.dao.manutencao.HistoricoTrocaManutencaoDAO;
import br.com.orlandoburli.core.dao.manutencao.TrocaManutencaoDAO;
import br.com.orlandoburli.core.utils.SendMail;
import br.com.orlandoburli.core.vo.manutencao.HistoricoTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.TrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class EnviaEmailFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	public void execute() {

		synchronized (HistoricoTrocaManutencaoVO.class) {

			HistoricoTrocaManutencaoDAO dao = new HistoricoTrocaManutencaoDAO();
			TrocaManutencaoDAO trocaDao = new TrocaManutencaoDAO();

			HistoricoTrocaManutencaoVO filter = new HistoricoTrocaManutencaoVO();

			filter.setFlagEmailEnviadoTrocaManutencao("N");
			filter.setFlagEnviaEmailSituacaoTrocaManutencao("S");

			try {
				for (HistoricoTrocaManutencaoVO item : dao.getList(filter, "CodigoHistoricoTrocaManutencao")) {

					TrocaManutencaoVO troca = new TrocaManutencaoVO();
					troca.setCodigoEmpresa(item.getCodigoEmpresa());
					troca.setCodigoLoja(item.getCodigoLoja());
					troca.setCodigoTrocaManutencao(item.getCodigoTrocaManutencao());

					troca = trocaDao.get(troca);

					SendMail send = new SendMail();
					send.sendEmailHistoricoTroca(troca, item);

					item.setFlagEmailEnviadoTrocaManutencao("S");

					dao.persist(item);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}
}
