package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.rateiocontapagar;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.RateioContaPagarDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.RateioContaPagarVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

public class RateioContaPagarCadastroFacade extends BaseCadastroFlexFacade<RateioContaPagarVO, RateioContaPagarDAO> {

	private static final long serialVersionUID = 1L;

	@OutjectSession
	private List<RateioContaPagarVO> rateios;

	@InstantiateObject
	@OutjectSession
	private ContaPagarVO conta;

	@InstantiateObject
	private RateioContaPagarVO rateio;

	@IgnoreMethodAuthentication
	public void list() {
		try {
			
			conta = new ContaPagarDAO().get(conta);

			if (conta == null) {
				writeErrorMessage("Conta não encontrada!");
				return;
			}
			
			RateioContaPagarVO filter = new RateioContaPagarVO();
			filter.setCodigoEmpresa(conta.getCodigoEmpresa());
			filter.setCodigoLoja(conta.getCodigoLoja());
			filter.setCodigoContaPagar(conta.getCodigoContaPagar());
			
			// Busca os rateios ja preenchidos
			setRateios(dao.getList(filter));

			List<LojaVO> listLojas = new LojaDAO().getListLojasUsuario(getUsuariosessao(), null);

			for (LojaVO loja : listLojas) {
				boolean found = false;
				for (RateioContaPagarVO rateio : getRateios()) {
					if (loja.getCodigoLoja().equals(rateio.getCodigoLojaRateio()) && loja.getCodigoEmpresa().equals(rateio.getCodigoEmpresaRateio())) {
						found = true;
						break;
					}
				}

				if (!found) {
					RateioContaPagarVO novoRateio = new RateioContaPagarVO();
					novoRateio.setNewRecord(true);

					novoRateio.setCodigoEmpresa(conta.getCodigoEmpresa());
					novoRateio.setCodigoLoja(conta.getCodigoLoja());
					novoRateio.setCodigoContaPagar(conta.getCodigoContaPagar());

					novoRateio.setCodigoEmpresaRateio(loja.getCodigoEmpresa());
					novoRateio.setCodigoLojaRateio(loja.getCodigoLoja());

					novoRateio.setValorRateio(BigDecimal.ZERO);
					novoRateio.setNomeLoja(loja.getNomeLoja());

					getRateios().add(novoRateio);
				}
			}

			ArrayList<IValueObject> listFinal = new ArrayList<IValueObject>();

			listFinal.addAll(getRateios());
			listFinal.add(conta);

			write(Utils.voToXml(listFinal));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void alterar() {
		for (RateioContaPagarVO i : getRateios()) {
			if (i.getCodigoEmpresaRateio().equals(rateio.getCodigoEmpresaRateio()) && i.getCodigoLojaRateio().equals(rateio.getCodigoLojaRateio())) {
				i.setValorRateio(rateio.getValorRateio());
			}
		}
	}

	@IgnoreMethodAuthentication
	public void finalizar() {
		try {
			BigDecimal valorTotal = BigDecimal.ZERO;
			valorTotal = valorTotal.setScale(2);
			for (RateioContaPagarVO i : getRateios()) {
				valorTotal = valorTotal.add(i.getValorRateio());
			}

			int compare = conta.getValorContaPagar().compareTo(valorTotal);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			if (compare < 0) {
				writeErrorMessage("Total das contas (" + nf.format(valorTotal) + ") maior que a conta a pagar(" + nf.format(conta.getValorContaPagar()) + ") !");
				return;
			} else if (compare > 0) {
				writeErrorMessage("Total das contas (" + nf.format(valorTotal) + ") menor que a conta a pagar(" + nf.format(conta.getValorContaPagar()) + ") !");
				return;
			}

			// Salvar rateios
			for (RateioContaPagarVO i : getRateios()) {
				i.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				i.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				i.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());

				dao.persist(i);
			}

			write("ok");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public ContaPagarVO getConta() {
		return conta;
	}

	public void setConta(ContaPagarVO conta) {
		this.conta = conta;
	}

	public RateioContaPagarVO getRateio() {
		return rateio;
	}

	public void setRateio(RateioContaPagarVO rateio) {
		this.rateio = rateio;
	}

	public List<RateioContaPagarVO> getRateios() {
		return rateios;
	}

	public void setRateios(List<RateioContaPagarVO> rateios) {
		this.rateios = rateios;
	}
}