package br.com.orlandoburli.personalerp.facades.financeiro.processamentovendas;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.RecebimentoDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasDiaDAO;
import br.com.orlandoburli.core.view.vendas.VendasDiaView;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.RecebimentoVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;

public class ProcessamentoVendasFinanceiroFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	
	private Date DataInicialVencimento;
	private Date DataFinalVencimento;

	private String FlgTodasLojas;
	private String FlgBaixaContas;

	public void execute() {
		try {

			String sb = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			if (DataInicialVencimento != null) {
				sb += " Data >= '" + DataInicialVencimento.toString() + "' ";
			}
			if (DataFinalVencimento != null) {
				sb += (sb.length()==0?"":" AND ");
				sb += " Data <= '" + DataFinalVencimento.toString() + "' ";
			}

			List<LojaVO> lojas;
			LojaVO lojaFilter = new LojaVO();
			
			if (getFlgTodasLojas().equals("N")) {
				lojaFilter.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				lojaFilter.setCodigoLoja(getLojasessao().getCodigoLoja());
			}

			lojas = new LojaDAO().getList(lojaFilter);

			VendasDiaDAO vendasDao = new VendasDiaDAO();
			vendasDao.setAutoCommit(false);

			vendasDao.getConnection();

			ContaReceberDAO contaDao = new ContaReceberDAO();
			contaDao.mergeDAO(vendasDao);

			RecebimentoDAO recebimentoDao = new RecebimentoDAO();
			recebimentoDao.mergeDAO(vendasDao);

			BaixaContaReceberDAO baixaDao = new BaixaContaReceberDAO();
			baixaDao.mergeDAO(vendasDao);

			SimpleDateFormat extenso = new SimpleDateFormat("yyyyMMdd");

			for (LojaVO loja : lojas) {
				VendasDiaView filter = new VendasDiaView();
				filter.setCodigoEmpresa(loja.getCodigoEmpresa());
				filter.setCodigoLoja(loja.getCodigoLoja());

				vendasDao.setSpecialCondition(sb);
				
				// Busca as vendas para esta loja
				List<VendasDiaView> vendas = vendasDao.getList(filter, "Data");
				
				for (VendasDiaView venda : vendas) {
					
					// Verifica se ja existe a conta
					ContaReceberVO conta = new ContaReceberVO();
					conta.setCodigoEmpresa(venda.getCodigoEmpresa());
					conta.setCodigoLoja(venda.getCodigoLoja());
					conta.setDataVencimentoContaReceber(venda.getData());
					
					List<ContaReceberVO> searchContas = contaDao.getList(conta);
					
					if (searchContas.size() > 0) {
						conta = searchContas.get(0);
						
						// Se ja estiver baixada, sai
						if (conta.getSituacaoContaReceber().equals("Q")) {
							continue;
						}
					} else {
						conta = new ContaReceberVO();
						conta.setNewRecord(true);
					}
					
					conta.setCodigoEmpresa(venda.getCodigoEmpresa());
					conta.setCodigoLoja(venda.getCodigoLoja());
					conta.setParcelaContaReceber(1);
					conta.setNumeroParcelasContaReceber(1);
					conta.setDescricaoContaReceber("CONTA GERADA PELAS VENDAS DO DIA " + sdf.format(venda.getData()));

					conta.setDataLancamentoContaReceber(new Timestamp(venda.getData().getTime()));
					conta.setDataEmissaoContaReceber(venda.getData());
					conta.setDataVencimentoContaReceber(venda.getData());
					conta.setNumeroTituloContaReceber(extenso.format(venda.getData()));
					
					if (getFlgBaixaContas().equals("S")) {
						conta.setSituacaoContaReceber("Q"); // Quitado
					} else {
						conta.setSituacaoContaReceber("A"); // Quitado
					}
					
					conta.setValorContaReceber(venda.getValorTotal());

					conta.setCodigoPlanoConta(422); 
					conta.setCodigoCentroCusto(1);
					conta.setCodigoTipoDocumento(1);
					conta.setCodigoPortador(1);

					contaDao.persist(conta);
					
					if (getFlgBaixaContas().equals("S")) {
						// Gerar baixas
						RecebimentoVO recebimento = new RecebimentoVO();
						recebimento.setNewRecord(true);
						recebimento.setCodigoEmpresa(conta.getCodigoEmpresa());
						recebimento.setCodigoLoja(conta.getCodigoLoja());
						recebimento.setCodigoFormaRecebimento(1); // Dinheiro
						recebimento.setDataHoraRecebimento(conta.getDataLancamentoContaReceber());
						recebimento.setValorRecebimento(conta.getValorContaReceber());
	
						recebimentoDao.persist(recebimento);
	
						BaixaContaReceberVO baixa = new BaixaContaReceberVO();
						baixa.setNewRecord(true);
	
						baixa.setCodigoEmpresaContaReceber(conta.getCodigoEmpresa());
						baixa.setCodigoLojaContaReceber(conta.getCodigoLoja());
						baixa.setCodigoContaReceber(conta.getCodigoContaReceber());
	
						baixa.setCodigoEmpresa(recebimento.getCodigoEmpresa());
						baixa.setCodigoLoja(recebimento.getCodigoLoja());
						baixa.setCodigoRecebimento(recebimento.getCodigoRecebimento());
	
						baixa.setValorRecebimento(recebimento.getValorRecebimento());
	
						baixaDao.persist(baixa);
					}
				}
			}

			vendasDao.commit();
			
			write("OK");
		} catch (SQLException e) {
			e.printStackTrace();
			write(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			write(e.getMessage());
		}
	}

	public Date getDataInicialVencimento() {
		return DataInicialVencimento;
	}

	public void setDataInicialVencimento(Date dataInicialVencimento) {
		DataInicialVencimento = dataInicialVencimento;
	}

	public Date getDataFinalVencimento() {
		return DataFinalVencimento;
	}

	public void setDataFinalVencimento(Date dataFinalVencimento) {
		DataFinalVencimento = dataFinalVencimento;
	}

	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}

	public String getFlgBaixaContas() {
		return FlgBaixaContas;
	}

	public void setFlgBaixaContas(String flgBaixaContas) {
		FlgBaixaContas = flgBaixaContas;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}
}
