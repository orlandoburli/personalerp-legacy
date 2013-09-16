package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.DependenteDAO;
import br.com.orlandoburli.core.dao.rh.FaltaFuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.FeriadoDAO;
import br.com.orlandoburli.core.dao.rh.HorarioTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.TabelaInssDAO;
import br.com.orlandoburli.core.dao.rh.TabelaIrrfDAO;
import br.com.orlandoburli.core.dao.rh.TabelaIrrfDeducaoDependenteDAO;
import br.com.orlandoburli.core.dao.rh.TabelaSalarioFamiliaDAO;
import br.com.orlandoburli.core.dao.rh.TabelaSalarioMinimoDAO;
import br.com.orlandoburli.core.dao.rh.ValeFuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.VerbaContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.dao.rh.folha.ContratoTrabalhoFolhaPagamentoDAO;
import br.com.orlandoburli.core.dao.rh.folha.FolhaPagamentoDAO;
import br.com.orlandoburli.core.dao.rh.folha.VerbaFolhaPagamentoDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.DependenteVO;
import br.com.orlandoburli.core.vo.rh.FaltaFuncionarioVO;
import br.com.orlandoburli.core.vo.rh.FeriadoVO;
import br.com.orlandoburli.core.vo.rh.HorarioTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.TabelaInssVO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfDeducaoDependenteVO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfVO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioFamiliaVO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioMinimoVO;
import br.com.orlandoburli.core.vo.rh.ValeFuncionarioVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.rh.folha.ContratoTrabalhoFolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.VerbaFolhaPagamentoVO;

public class ProcessamentoFolhaAction {

	private FolhaPagamentoVO folha;
	private FolhaPagamentoDAO folhaDao = new FolhaPagamentoDAO();
	
	private String formaCalculoProporcao;
	private Integer numeroHorasMes;
	private String impactaFaltaParcialDSR;
	
	private VerbaVO verbaINSS;
	private VerbaVO verbaIRRF;
	private VerbaVO verbaSalarioFamilia;
	private VerbaVO verbaFalta;
	private VerbaVO verbaFaltaParcial;
	private VerbaVO verbaFaltaDSR;
	private VerbaVO verbaVale;
	
	private VerbaVO verbaComissaoDSR;
	
	private BigDecimal horasDia;
	
	private BigDecimal aliquotaFGTS;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void execute() {
		try {
			
			setFolha(folhaDao.get(getFolha()));
			
			if (getFolha() == null) {
				return;
			}
			
			zeraFolha(getFolha());
			
			// Busca parametros necessarios para a folha
			ParametroLojaDAO paramDao = new ParametroLojaDAO();
			
			// Indica qual a forma de calculo proporcional da folha, se em horas ou dias (H (horas) / D (dias)).
			setFormaCalculoProporcao(paramDao.getStringParametro(Constants.Parameters.Folha.FORMA_CALCULO_PROPORCAO_FOLHA, null, null));
			// Parametro que indica o numero de horas / mes a ser considerado para calculo
			setNumeroHorasMes(paramDao.getIntegerParametro(Constants.Parameters.Folha.NUMERO_HORAS_MES_FOLHA, null, null));
			// Parametro (S (sim)/N (nao)) que indica se as faltas parciais impactam ou nao no DSR (Descanso Semanal Remunerado)
			setImpactaFaltaParcialDSR(paramDao.getStringParametro(Constants.Parameters.Folha.IMPACTA_FALTA_PARCIAL_DSR, null, null));
			// Aliquota do FGTS
			setAliquotaFGTS(paramDao.getBigDecimalParametro(Constants.Parameters.Folha.ALIQUOTA_FGTS, null, null));
			
			// Busca verbas do sistema
			VerbaDAO verbaDao = new VerbaDAO();
			
			// Verba do INSS
			String keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_INSS, null, null);
			setVerbaINSS(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba do IRRF
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_IRRF, null, null);
			setVerbaIRRF(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba do Salario Familia
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_SALARIO_FAMILIA, null, null);
			setVerbaSalarioFamilia(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba da Falta
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_FALTA, null, null);
			setVerbaFalta(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba da Falta Parcial
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_FALTA_PARCIAL, null, null);
			setVerbaFaltaParcial(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba da Falta DSR (Descanso semanal remunerado)
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_FALTA_DSR, null, null);
			setVerbaFaltaDSR(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba do Adiantamento / Vale
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_VALE, null, null);
			setVerbaVale(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Verba o impacto das comissoes sobre o D.S.R.
			keys = paramDao.getStringParametro(Constants.Parameters.Folha.CODIGO_VERBA_COMISSAO_DSR, null, null);
			setVerbaComissaoDSR(verbaDao.get(new Object[]{ Integer.parseInt(keys.split(";")[0]), Integer.parseInt(keys.split(";")[1]), Integer.parseInt(keys.split(";")[2])}));
			
			// Busca contratos ativos
			ContratoTrabalhoDAO contratoDao = new ContratoTrabalhoDAO();
			ContratoTrabalhoVO filter = new ContratoTrabalhoVO();
			
			filter.setCodigoEmpresaLotacao(folha.getCodigoEmpresaLotacao());
			filter.setCodigoLojaLotacao(folha.getCodigoLojaLotacao());
			
			List<ContratoTrabalhoVO> contratos = contratoDao.getList(filter);
			
			ContratoTrabalhoFolhaPagamentoDAO contratofolhaDao = new ContratoTrabalhoFolhaPagamentoDAO();
			VerbaContratoTrabalhoDAO verbacontratoDao = new VerbaContratoTrabalhoDAO();
			VerbaFolhaPagamentoDAO verbafolhaDao = new VerbaFolhaPagamentoDAO();
			
			DependenteDAO dependenteDao = new DependenteDAO();
			TabelaSalarioFamiliaDAO salarioFamiliaDao = new TabelaSalarioFamiliaDAO();
			TabelaInssDAO tabelaInssDao = new TabelaInssDAO();
			TabelaIrrfDAO tabelaIrrfDao = new TabelaIrrfDAO();
			HorarioTrabalhoDAO horarioDao = new HorarioTrabalhoDAO();
			
			// Busca tabela de deducao por dependente para irrf
			TabelaIrrfDeducaoDependenteVO tabelaIrrfDeducao = new TabelaIrrfDeducaoDependenteDAO().get(getDataBase());
			
			// Busca tabela de salario minimo vigente
			TabelaSalarioMinimoVO tabelaSalarioMinimo = new TabelaSalarioMinimoDAO().get(getDataBase());
			
			BigDecimal cem = new BigDecimal(100);
			BigDecimal progresso = BigDecimal.ZERO;
			int count = 0;
			
			// Loop dos contratos
			for (ContratoTrabalhoVO contrato : contratos) {
				
				// Verifica se o contrato estava ativo no periodo da folha
				boolean flgOk = true;
				if (contrato.getStatusContratoTrabalho().equalsIgnoreCase(ContratoTrabalhoVO.STATUS_INATIVO)) {
					if (contrato.getDataInicioContratoTrabalho().after(getUltimoDiaMes())) {
						flgOk = false;
					} else if (contrato.getDataFimContratoTrabalho().before(getDataBase())) {
						flgOk = false;
					}
				} else if (contrato.getDataInicioContratoTrabalho().after(getUltimoDiaMes())) {
					flgOk = false;
				}
				if (flgOk) { 
					// Busca horario de trabalho
					HorarioTrabalhoVO horario = horarioDao.get(new Object[]{contrato.getCodigoEmpresaHorarioTrabalho(), contrato.getCodigoLojaHorarioTrabalho(), contrato.getCodigoHorarioTrabalho()});
					
					ContratoTrabalhoFolhaPagamentoVO contratoFolha = new ContratoTrabalhoFolhaPagamentoVO();
					contratoFolha.setNewRecord(true);
					contratoFolha.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
					contratoFolha.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
					contratoFolha.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
					
					contratoFolha.setCodigoEmpresa(folha.getCodigoEmpresa());
					contratoFolha.setCodigoLoja(folha.getCodigoLoja());
					contratoFolha.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
					
					// Salva a primeira vez, para ter referencia...
					contratofolhaDao.persist(contratoFolha);
					
					// Busca verbas do contrato
					VerbaContratoTrabalhoVO filterVerbas = new VerbaContratoTrabalhoVO();
					filterVerbas.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
					filterVerbas.setCodigoEmpresa(contrato.getCodigoEmpresa());
					filterVerbas.setCodigoLoja(contrato.getCodigoLoja());
					
					List<VerbaContratoTrabalhoVO> listVerbaContrato = verbacontratoDao.getList(filterVerbas);
					
					BigDecimal basePrevidencia = BigDecimal.ZERO;
					BigDecimal baseIrrf = BigDecimal.ZERO;
					
					// Calcula numero de horas trabalhadas do dia
					Timestamp timeTotal = new Timestamp(1, 1, 1, 0, 0, 0, 0);
					if (horario.getHoraFim1HorarioTrabalho() != null && horario.getHoraInicio1HorarioTrabalho() != null) {
						timeTotal = new Timestamp(timeTotal.getTime() +  horario.getHoraFim1HorarioTrabalho().getTime() - horario.getHoraInicio1HorarioTrabalho().getTime());
					}
					if (horario.getHoraFim2HorarioTrabalho() != null && horario.getHoraInicio2HorarioTrabalho() != null) {
						timeTotal = new Timestamp(timeTotal.getTime() +  horario.getHoraFim2HorarioTrabalho().getTime() - horario.getHoraInicio2HorarioTrabalho().getTime());
					}
					if (horario.getHoraFim3HorarioTrabalho() != null && horario.getHoraInicio3HorarioTrabalho() != null) {
						timeTotal = new Timestamp(timeTotal.getTime() +  horario.getHoraFim3HorarioTrabalho().getTime() - horario.getHoraInicio3HorarioTrabalho().getTime());
					}
					if (horario.getHoraFim4HorarioTrabalho() != null && horario.getHoraInicio4HorarioTrabalho() != null) {
						timeTotal = new Timestamp(timeTotal.getTime() +  horario.getHoraFim4HorarioTrabalho().getTime() - horario.getHoraInicio4HorarioTrabalho().getTime());
					}
					
					setHorasDia(new BigDecimal(timeTotal.getHours() + (timeTotal.getMinutes() / 60.0)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					
					// Busca horas de faltas
					BigDecimal horasFaltas = getHorasFaltas(contrato, folha);
					BigDecimal horasFaltasParcial = getHorasFaltasParcial(contrato, folha);
					BigDecimal horasFaltasDSR = getHorasFaltasDSR(contrato, folha);
					
					// Busca horas uteis e horas dsr
					BigDecimal horasDSR = getHorasDSR(contrato, folha);
					BigDecimal horasUteisMes = getHorasUteisMes(contrato, folha);
					
					// Inicializa as bases
					contratoFolha.setSalarioBase(BigDecimal.ZERO);
					contratoFolha.setBaseFgts(BigDecimal.ZERO);
					contratoFolha.setBaseInss(BigDecimal.ZERO);
					contratoFolha.setBaseIrrf(BigDecimal.ZERO);
					contratoFolha.setValorFgts(BigDecimal.ZERO);
					contratoFolha.setFaixaIrrf(BigDecimal.ZERO);
					
					// Loop das verbas de salario (base)
					for (VerbaContratoTrabalhoVO verbacontrato : listVerbaContrato) {
						
						VerbaVO verba = verbaDao.get(new Object[]{verbacontrato.getCodigoEmpresaVerba(), verbacontrato.getCodigoLojaVerba(), verbacontrato.getCodigoVerba()});
						
						// Somente as verbas de salario base 
						// e os descontos fixos, como assistencia medica e odontologica e outros
						// Pula esses if's, nao faz nem sentido...
//						if (1 == 1) {
							VerbaFolhaPagamentoVO verbafolha = new VerbaFolhaPagamentoVO();
							verbafolha.setNewRecord(true);
							
							verbafolha.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
							verbafolha.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
							verbafolha.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
							
							verbafolha.setCodigoEmpresa(folha.getCodigoEmpresa());
							verbafolha.setCodigoLoja(folha.getCodigoLoja());
							verbafolha.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
							
							verbafolha.setCodigoEmpresaVerba(verbacontrato.getCodigoEmpresaVerba());
							verbafolha.setCodigoLojaVerba(verbacontrato.getCodigoLojaVerba());
							verbafolha.setCodigoVerba(verbacontrato.getCodigoVerba());
							
							// Calcula o valor
							if (verba.getTipoCalculoVerba().equalsIgnoreCase(VerbaVO.TIPO_CALCULO_VALOR_FIXO)) {
								if (verba.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
									calculaProporcionalValorVerba(contrato, null, verbacontrato.getValorVerba(), tabelaSalarioMinimo, verbafolha);
									// Adiciona ao salario base
									contratoFolha.setSalarioBase(contratoFolha.getSalarioBase().add(verbafolha.getValorVerba()));
								} else {
									// Descontos não são proporcionalizados
									verbafolha.setValorVerba(verbacontrato.getValorVerba());
									verbafolha.setReferenciaVerba(verbacontrato.getValorVerba());
								}
							} else if (verba.getTipoCalculoVerba().equalsIgnoreCase(VerbaVO.TIPO_CALCULO_SISTEMA)) {
								// Busca a classe
								try {
									// Tem que implementar a interface ICalculoVerbaFolha, 
									// senão não é processada
									Class<ICalculoVerbaFolha> klass = (Class<ICalculoVerbaFolha>) Class.forName("br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.calculos." + verba.getClasseSistemaVerba() + "Action");
									ICalculoVerbaFolha calculo = klass.newInstance();
									
									BigDecimal valor = calculo.calcular(contrato, folha, verba, verbacontrato, verbafolha, horasDSR, horasUteisMes);
									
									if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
										verbafolha.setValorVerba(valor);
										verbafolhaDao.persist(verbafolha);
									}
									
									// Se for calculo de comissao, tem impacto no D.S.R.
									// E tem que ser contrato na carteira
									if (calculo instanceof ICalculoComissao && contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
										BigDecimal valorImpactoComissaoDSR = ((ICalculoComissao) calculo).calcularImpactoDSR(contrato, folha, valor, horasDSR, horasUteisMes);
										if (valorImpactoComissaoDSR != null && valorImpactoComissaoDSR.compareTo(BigDecimal.ZERO) > 0) {
											VerbaFolhaPagamentoVO verbaFolhaImpactoDSRComissao = new VerbaFolhaPagamentoVO();
											verbaFolhaImpactoDSRComissao.setNewRecord(true);
											
											verbaFolhaImpactoDSRComissao.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
											verbaFolhaImpactoDSRComissao.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
											verbaFolhaImpactoDSRComissao.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
											
											verbaFolhaImpactoDSRComissao.setCodigoEmpresa(folha.getCodigoEmpresa());
											verbaFolhaImpactoDSRComissao.setCodigoLoja(folha.getCodigoLoja());
											verbaFolhaImpactoDSRComissao.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
											
											verbaFolhaImpactoDSRComissao.setCodigoEmpresaVerba(getVerbaComissaoDSR().getCodigoEmpresa());
											verbaFolhaImpactoDSRComissao.setCodigoLojaVerba(getVerbaComissaoDSR().getCodigoLoja());
											verbaFolhaImpactoDSRComissao.setCodigoVerba(getVerbaComissaoDSR().getCodigoVerba());
											
											verbaFolhaImpactoDSRComissao.setValorVerba(valorImpactoComissaoDSR);
											verbaFolhaImpactoDSRComissao.setReferenciaVerba(horasDSR);
											
											verbafolhaDao.persist(verbaFolhaImpactoDSRComissao);
											
											// Soma / subtrai para base da previdencia
											if (getVerbaComissaoDSR().getFlagFolhaVerba().equalsIgnoreCase("S")) {
												if (getVerbaComissaoDSR().getFlagBaseInssVerba().equalsIgnoreCase("S")) {
													if (getVerbaComissaoDSR().getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
														basePrevidencia = basePrevidencia.add(verbaFolhaImpactoDSRComissao.getValorVerba());
													} else if (getVerbaComissaoDSR().getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
														basePrevidencia = basePrevidencia.subtract(verbaFolhaImpactoDSRComissao.getValorVerba());
													}
												}
												
												// Soma / subtrai para base irrf
												if (getVerbaComissaoDSR().getFlagBaseIrrfVerba().equalsIgnoreCase("S")) {
													if (getVerbaComissaoDSR().getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
														baseIrrf = baseIrrf.add(verbaFolhaImpactoDSRComissao.getValorVerba());
													} else if (getVerbaComissaoDSR().getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
														baseIrrf = baseIrrf.subtract(verbaFolhaImpactoDSRComissao.getValorVerba());
													}
												}
											}
										}
									}
								} catch (ClassNotFoundException e) {
									System.out.println("Classe " + verba.getClasseSistemaVerba() + " não encontrada!");
								} catch (InstantiationException e) {
									System.out.println("Erro ao instanciar a classe " + verba.getClasseSistemaVerba() + "!");
								} catch (IllegalAccessException e) {
									System.out.println("Erro de acesso ao instanciar a classe " + verba.getClasseSistemaVerba() + "!");
								}
							}
							
							if (verbafolha.getValorVerba() != null && verbafolha.getValorVerba().compareTo(BigDecimal.ZERO) > 0) {
								if (verba.getFlagFolhaVerba().equalsIgnoreCase("S")) {
									// Soma / subtrai para base da previdencia
									if (verba.getFlagBaseInssVerba().equalsIgnoreCase("S")) {
										if (verba.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
											basePrevidencia = basePrevidencia.add(verbafolha.getValorVerba());
										} else if (verba.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
											basePrevidencia = basePrevidencia.subtract(verbafolha.getValorVerba());
										}
									}
									
									// Soma / subtrai para base irrf
									if (verba.getFlagBaseIrrfVerba().equalsIgnoreCase("S")) {
										if (verba.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
											baseIrrf = baseIrrf.add(verbafolha.getValorVerba());
										} else if (verba.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
											baseIrrf = baseIrrf.subtract(verbafolha.getValorVerba());
										}
									}
								}
								verbafolhaDao.persist(verbafolha);
							}
						}
//					}
					
					// Calculo das Faltas
					if (horasFaltas.compareTo(BigDecimal.ZERO) > 0 || horasFaltasParcial.compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal valorVerbaFaltas = BigDecimal.ZERO;
						BigDecimal valorVerbaFaltasParcial = BigDecimal.ZERO;
						BigDecimal valorVerbaFaltasDSR = BigDecimal.ZERO;
						
						// Cria a verba de faltas
						VerbaFolhaPagamentoVO verbaFolhaFalta = new VerbaFolhaPagamentoVO();
						
						verbaFolhaFalta.setNewRecord(true);
						
						verbaFolhaFalta.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
						verbaFolhaFalta.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
						verbaFolhaFalta.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
						
						verbaFolhaFalta.setCodigoEmpresa(folha.getCodigoEmpresa());
						verbaFolhaFalta.setCodigoLoja(folha.getCodigoLoja());
						verbaFolhaFalta.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
						
						verbaFolhaFalta.setCodigoEmpresaVerba(getVerbaFalta().getCodigoEmpresa());
						verbaFolhaFalta.setCodigoLojaVerba(getVerbaFalta().getCodigoLoja());
						verbaFolhaFalta.setCodigoVerba(getVerbaFalta().getCodigoVerba());
						
						// Cria a verba de faltas parciais
						VerbaFolhaPagamentoVO verbaFolhaFaltaParcial = new VerbaFolhaPagamentoVO();
						
						verbaFolhaFaltaParcial.setNewRecord(true);
						
						verbaFolhaFaltaParcial.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
						verbaFolhaFaltaParcial.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
						verbaFolhaFaltaParcial.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
						
						verbaFolhaFaltaParcial.setCodigoEmpresa(folha.getCodigoEmpresa());
						verbaFolhaFaltaParcial.setCodigoLoja(folha.getCodigoLoja());
						verbaFolhaFaltaParcial.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
						
						verbaFolhaFaltaParcial.setCodigoEmpresaVerba(getVerbaFaltaParcial().getCodigoEmpresa());
						verbaFolhaFaltaParcial.setCodigoLojaVerba(getVerbaFaltaParcial().getCodigoLoja());
						verbaFolhaFaltaParcial.setCodigoVerba(getVerbaFaltaParcial().getCodigoVerba());
						
						// Cria a verba de reflexo de faltas no D.S.R.
						VerbaFolhaPagamentoVO verbaFolhaFaltaDSR = new VerbaFolhaPagamentoVO();
						
						verbaFolhaFaltaDSR.setNewRecord(true);
						
						verbaFolhaFaltaDSR.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
						verbaFolhaFaltaDSR.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
						verbaFolhaFaltaDSR.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
						
						verbaFolhaFaltaDSR.setCodigoEmpresa(folha.getCodigoEmpresa());
						verbaFolhaFaltaDSR.setCodigoLoja(folha.getCodigoLoja());
						verbaFolhaFaltaDSR.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
						
						verbaFolhaFaltaDSR.setCodigoEmpresaVerba(getVerbaFaltaDSR().getCodigoEmpresa());
						verbaFolhaFaltaDSR.setCodigoLojaVerba(getVerbaFaltaDSR().getCodigoLoja());
						verbaFolhaFaltaDSR.setCodigoVerba(getVerbaFaltaDSR().getCodigoVerba());
						
						if (getFormaCalculoProporcao().equalsIgnoreCase("D")) { // Dias
							// Calcula valor do dia
							BigDecimal valorDia = basePrevidencia.divide(new BigDecimal(30));
							
							// ----------------------------------------------------- //
							
							// Número de dias equivalentes para faltas
							BigDecimal diasFalta = horasFaltas.divide(getHorasDia()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Valor das faltas
							valorVerbaFaltas = valorDia.multiply(diasFalta).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Referencia
							verbaFolhaFalta.setReferenciaVerba(diasFalta);
							
							// ----------------------------------------------------- //
							
							// Número de dias equivalentes para faltas parciais
							BigDecimal diasFaltaParcial = horasFaltasParcial.divide(getHorasDia()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Valor das faltas
							valorVerbaFaltasParcial = valorDia.multiply(diasFaltaParcial).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Referencia
							verbaFolhaFaltaParcial.setReferenciaVerba(diasFaltaParcial);
							
							// ----------------------------------------------------- //
							
							// Número de dias equivalentes para faltas (DSR)
							BigDecimal diasFaltaDSR = horasFaltasDSR.divide(getHorasDia()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Valor das faltas (DSR)
							valorVerbaFaltasDSR = valorDia.multiply(diasFaltaDSR).setScale(2, BigDecimal.ROUND_HALF_EVEN);
							
							// Referencia (DSR)
							verbaFolhaFaltaDSR.setReferenciaVerba(diasFaltaDSR);
							
						} else if (getFormaCalculoProporcao().equalsIgnoreCase("H")) { // Horas
							// Calcula valor da hora
							BigDecimal valorHora = basePrevidencia.divide(new BigDecimal(this.getNumeroHorasMes()), 2, BigDecimal.ROUND_HALF_EVEN);
							
							// ----------------------------------------------------- //
							
							// Valor das faltas
							valorVerbaFaltas = valorHora.multiply(horasFaltas);
							
							// Referencia
							verbaFolhaFalta.setReferenciaVerba(horasFaltas);
							
							// ----------------------------------------------------- //
							
							// Valor das faltas (parcial)
							valorVerbaFaltasParcial = valorHora.multiply(horasFaltasParcial);
							
							// Referencia
							verbaFolhaFaltaParcial.setReferenciaVerba(horasFaltasParcial);
							
							// ----------------------------------------------------- //
							
							// Valor das faltas (DSR)
							valorVerbaFaltasDSR = valorHora.multiply(horasFaltasDSR);
							
							// Referencia (DSR)
							verbaFolhaFaltaDSR.setReferenciaVerba(horasFaltasDSR);
						}
						
						// Lança a verba de faltas 
						if (valorVerbaFaltas.compareTo(BigDecimal.ZERO) > 0) {
							verbaFolhaFalta.setValorVerba(valorVerbaFaltas);
							verbafolhaDao.persist(verbaFolhaFalta);
							
							// Abate os valores de desconto das faltas nas bases de previdencia e irrf
							basePrevidencia = basePrevidencia.subtract(verbaFolhaFalta.getValorVerba());
							baseIrrf = baseIrrf.subtract(verbaFolhaFalta.getValorVerba());
						}
						
						// Lança a verba de faltas (parcial)
						if (valorVerbaFaltasParcial.compareTo(BigDecimal.ZERO) > 0) {
							verbaFolhaFaltaParcial.setValorVerba(valorVerbaFaltasParcial);
							verbafolhaDao.persist(verbaFolhaFaltaParcial);
							
							// Abate os valores de desconto das faltas nas bases de previdencia e irrf
							basePrevidencia = basePrevidencia.subtract(verbaFolhaFaltaParcial.getValorVerba());
							baseIrrf = baseIrrf.subtract(verbaFolhaFaltaParcial.getValorVerba());
						}
						
						// Lança a verba de faltas (DSR)
						if (valorVerbaFaltasDSR.compareTo(BigDecimal.ZERO) > 0) {
							verbaFolhaFaltaDSR.setValorVerba(valorVerbaFaltasDSR);
							verbafolhaDao.persist(verbaFolhaFaltaDSR);
							
							basePrevidencia = basePrevidencia.subtract(verbaFolhaFaltaDSR.getValorVerba());
							baseIrrf = baseIrrf.subtract(verbaFolhaFaltaDSR.getValorVerba());
						}
					}
					
					// Lançamentos de vales
					ValeFuncionarioVO filterVales = new ValeFuncionarioVO();
					filterVales.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
					filterVales.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
					filterVales.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
					
					filterVales.setAnoReferenciaValeFuncionario(folha.getAnoCompetenciaFolhaPagamento());
					filterVales.setMesReferenciaValeFuncionario(folha.getMesCompetenciaFolhaPagamento());
					
					ValeFuncionarioDAO _dao = new ValeFuncionarioDAO();
					
					List<ValeFuncionarioVO> vales = _dao.getList(filterVales);
					
					for (ValeFuncionarioVO vale : vales) {
						VerbaVO verbaVale = verbaDao.get(new Object[]{vale.getCodigoEmpresaVerba(), vale.getCodigoLojaVerba(), vale.getCodigoVerba()});
						
						VerbaFolhaPagamentoVO verbaFolhaVale = verbafolhaDao.get(new Object[]{
								folha.getCodigoEmpresa(), folha.getCodigoLoja(), folha.getCodigoFolhaPagamento(),
								contrato.getCodigoEmpresa(), contrato.getCodigoLoja(), contrato.getCodigoContratoTrabalho(),
								verbaVale.getCodigoEmpresa(), verbaVale.getCodigoLoja(), verbaVale.getCodigoVerba()
						});
						if (verbaFolhaVale == null) {
							verbaFolhaVale = new VerbaFolhaPagamentoVO();
							verbaFolhaVale.setValorVerba(BigDecimal.ZERO);
							verbaFolhaVale.setNewRecord(true);
						}
						
						verbaFolhaVale.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
						verbaFolhaVale.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
						verbaFolhaVale.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
						
						verbaFolhaVale.setCodigoEmpresa(folha.getCodigoEmpresa());
						verbaFolhaVale.setCodigoLoja(folha.getCodigoLoja());
						verbaFolhaVale.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
						
						verbaFolhaVale.setCodigoEmpresaVerba(vale.getCodigoEmpresaVerba());
						verbaFolhaVale.setCodigoLojaVerba(vale.getCodigoLojaVerba());
						verbaFolhaVale.setCodigoVerba(vale.getCodigoVerba());
						
						verbaFolhaVale.setValorVerba(verbaFolhaVale.getValorVerba().add(vale.getValorValeFuncionario()));
						verbaFolhaVale.setReferenciaVerba(verbaFolhaVale.getValorVerba());
						
						verbafolhaDao.persist(verbaFolhaVale);
						
						// Bases IRRF, IRPF, FGTS, e outras...
						// Soma / subtrai para base da previdencia
						// Verifica se a verba aparece no holerite... senao nao mexe com as bases
						if (verbaVale.getFlagFolhaVerba().equalsIgnoreCase("S")) {
							if (verbaVale.getFlagBaseInssVerba().equalsIgnoreCase("S")) {
								if (verbaVale.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
									basePrevidencia = basePrevidencia.add(vale.getValorValeFuncionario());
								} else if (verbaVale.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
									basePrevidencia = basePrevidencia.subtract(vale.getValorValeFuncionario());
								}
							}
							
							// Soma / subtrai para base irrf
							if (verbaVale.getFlagBaseIrrfVerba().equalsIgnoreCase("S")) {
								if (verbaVale.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_CREDITO)) {
									baseIrrf = baseIrrf.add(vale.getValorValeFuncionario());
								} else if (verbaVale.getTipoVerba().equalsIgnoreCase(VerbaVO.TIPO_VERBA_DEBITO)) {
									baseIrrf = baseIrrf.subtract(vale.getValorValeFuncionario());
								}
							}
						}
					}
					
					// Busca os dependentes para calculo de salario familia e IRRF
					DependenteVO filterDependente = new DependenteVO();
					filterDependente.setCodigoEmpresa(contrato.getCodigoEmpresaFuncionario());
					filterDependente.setCodigoLoja(contrato.getCodigoLojaFuncionario());
					filterDependente.setCodigoFuncionario(contrato.getCodigoFuncionario());
					
					List<DependenteVO> dependentes = dependenteDao.getList(filterDependente);
					
					// Calculo Salario Familia
					VerbaFolhaPagamentoVO verbaFolhaInss = new VerbaFolhaPagamentoVO();
					
					if (getVerbaSalarioFamilia() == null) {
						//throw new Exception("Verba do salario familia nao encontrada!");
						System.out.println("Verba do salario familia nao encontrada!");
						
					} else if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")){
						// Somente os contratos de carteira assinada
						
						// Busca faixa de valores para salario familia
						TabelaSalarioFamiliaVO tabelaSalarioFamilia = salarioFamiliaDao.get(basePrevidencia, getDataBase());
						
						// Se retornar nulo, o funcionario nao tem direito a salario-familia
						if (tabelaSalarioFamilia != null) {
							
							BigDecimal valorSalarioFamilia = BigDecimal.ZERO;
							
							VerbaFolhaPagamentoVO verbaFolhaSalFamilia = new VerbaFolhaPagamentoVO();
							verbaFolhaSalFamilia.setNewRecord(true);
							
							verbaFolhaSalFamilia.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
							verbaFolhaSalFamilia.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
							verbaFolhaSalFamilia.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
							
							verbaFolhaSalFamilia.setCodigoEmpresa(folha.getCodigoEmpresa());
							verbaFolhaSalFamilia.setCodigoLoja(folha.getCodigoLoja());
							verbaFolhaSalFamilia.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
							
							verbaFolhaSalFamilia.setCodigoEmpresaVerba(getVerbaSalarioFamilia().getCodigoEmpresa());
							verbaFolhaSalFamilia.setCodigoLojaVerba(getVerbaSalarioFamilia().getCodigoLoja());
							verbaFolhaSalFamilia.setCodigoVerba(getVerbaSalarioFamilia().getCodigoVerba());
							
							for (DependenteVO dependente : dependentes) {
								// Verifica se a vigencia da dependencia se enquadra neste mes
								if (dependente.getDataInicialDependente().compareTo(getDataBase()) <= 0) {
									if (dependente.getDataFinalDependente() == null || dependente.getDataFinalDependente().compareTo(getUltimoDiaMes()) >= 0) {
										// Adiciona o valor da verba ao total
										// Envia a base da previdencia para comparar
										valorSalarioFamilia = valorSalarioFamilia.add(calculaProporcionalValorVerba(contrato, basePrevidencia, tabelaSalarioFamilia.getValorSalarioFamilia(), tabelaSalarioMinimo, null));
									}
								}
							}
							verbaFolhaSalFamilia.setValorVerba(valorSalarioFamilia);
							verbaFolhaSalFamilia.setReferenciaVerba(valorSalarioFamilia);
							
							// Só salva se tiver salario familia. Se der zero, é pq nao tem dependentes 
							// com direito.
							if (verbaFolhaSalFamilia.getValorVerba().compareTo(BigDecimal.ZERO) > 0) {
								verbafolhaDao.persist(verbaFolhaSalFamilia);
							}
						}
					}
					// Calculo INSS
					VerbaVO verbaInss = getVerbaINSS();
					if (verbaInss == null) {
						System.out.println("Verba do INSS nao encontrada!");
					} else if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
						// Somente contratos com carteira assinada tem INSS
						TabelaInssVO tabelaInss = tabelaInssDao.get(basePrevidencia, getDataBase());
						if (tabelaInss != null) {
							verbaFolhaInss.setNewRecord(true);
							
							verbaFolhaInss.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
							verbaFolhaInss.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
							verbaFolhaInss.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
							
							verbaFolhaInss.setCodigoEmpresa(folha.getCodigoEmpresa());
							verbaFolhaInss.setCodigoLoja(folha.getCodigoLoja());
							verbaFolhaInss.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
							
							verbaFolhaInss.setCodigoEmpresaVerba(verbaInss.getCodigoEmpresa());
							verbaFolhaInss.setCodigoLojaVerba(verbaInss.getCodigoLoja());
							verbaFolhaInss.setCodigoVerba(verbaInss.getCodigoVerba());
							
							BigDecimal valorTetoDescontoInss = tabelaInss.getValorFinalTabelaInss().multiply(tabelaInss.getAliquotaTabelaInss()).divide(cem);
							BigDecimal valorDescontoInss = basePrevidencia.multiply(tabelaInss.getAliquotaTabelaInss()).divide(cem);
							
							// O valor do desconto não pode ser maior que este teto estipulado.
							if (valorDescontoInss.compareTo(valorTetoDescontoInss) > 0) {
								valorDescontoInss = valorTetoDescontoInss;
							}
							
							verbaFolhaInss.setValorVerba(valorDescontoInss);
							verbaFolhaInss.setReferenciaVerba(tabelaInss.getAliquotaTabelaInss());
							
							// O INSS é subtraído da base do IRRF
							baseIrrf = baseIrrf.subtract(valorDescontoInss);
							
							verbafolhaDao.persist(verbaFolhaInss);
							
							// Seta o valor de base da previdencia
							contratoFolha.setBaseInss(basePrevidencia);
						} else {
							System.out.println("Não foi encontrada tabela do INSS correspondente!");
						}
						
						// Somente contratos com carteira assinada possuem INSS patronal
						if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
							// Calcula o INSS Patronal
							// Busca a ultima faixa de valores, para saber o teto
							TabelaInssVO tabelaInssPatronal = tabelaInssDao.get(new BigDecimal(999999), getDataBase());
							if (tabelaInssPatronal != null && tabelaInssPatronal.getAliquotaPatronalTabelaInss() != null && tabelaInssPatronal.getAliquotaPatronalTabelaInss().compareTo(BigDecimal.ZERO) > 0) {
								BigDecimal valorInssPatronal = basePrevidencia.multiply(tabelaInssPatronal.getAliquotaPatronalTabelaInss()).divide(cem, 2, BigDecimal.ROUND_HALF_EVEN);
								BigDecimal valorTetoDescontoInssPatronal = tabelaInssPatronal.getValorFinalTabelaInss().multiply(tabelaInssPatronal.getAliquotaTabelaInss()).divide(cem);
								if (valorInssPatronal.compareTo(valorTetoDescontoInssPatronal) > 0) {
									valorInssPatronal = valorTetoDescontoInssPatronal;
								}
								contratoFolha.setValorInssPatronal(valorInssPatronal);
							} else {
								System.out.println("Não foi encontrada tabela do INSS (Patronal) correspondente!");
							}
						}
					}
					
					// Calculo IRRF
					if (getVerbaIRRF() == null) {
						System.out.println("Não foi encontrada a verba para IRRF!");
					} else if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")){
						// Somente contratos com carteira assinada possuem IRRF
						VerbaFolhaPagamentoVO verbaFolhaIrrf = new VerbaFolhaPagamentoVO();
						
						verbaFolhaIrrf.setNewRecord(true);
						
						verbaFolhaIrrf.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
						verbaFolhaIrrf.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
						verbaFolhaIrrf.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
						
						verbaFolhaIrrf.setCodigoEmpresa(folha.getCodigoEmpresa());
						verbaFolhaIrrf.setCodigoLoja(folha.getCodigoLoja());
						verbaFolhaIrrf.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
						
						verbaFolhaIrrf.setCodigoEmpresaVerba(getVerbaIRRF().getCodigoEmpresa());
						verbaFolhaIrrf.setCodigoLojaVerba(getVerbaIRRF().getCodigoLoja());
						verbaFolhaIrrf.setCodigoVerba(getVerbaIRRF().getCodigoVerba());
						
						// Calculo do IRRF
						
						// Aplica desconto para cada dependente na base de calculo
						BigDecimal valorDeducaoDependente = BigDecimal.ZERO;
						for (DependenteVO dependente : dependentes) {
							// Verifica se a vigencia da dependencia se enquadra neste mes
							if (dependente.getDataInicialDependente().compareTo(getDataBase()) <= 0) {
								if (dependente.getDataFinalDependente() == null || dependente.getDataFinalDependente().compareTo(getUltimoDiaMes()) >= 0) {
									// Adiciona o valor da verba ao total
									valorDeducaoDependente = valorDeducaoDependente.add(tabelaIrrfDeducao.getValorDeducaoTabelaIrrf());
								}
							}
						}
						
						// Diminui o valor por dependente da base de calculo
						baseIrrf = baseIrrf.subtract(valorDeducaoDependente);
						
						TabelaIrrfVO tabelaIrrf = tabelaIrrfDao.get(baseIrrf, getDataBase());
						if (tabelaIrrf != null) {
							
							// Aplica a aliquota
							BigDecimal valorDescontoIrrf = baseIrrf.multiply(tabelaIrrf.getAliquotaTabelaIrrf()).divide(cem).setScale(2, BigDecimal.ROUND_HALF_EVEN);;
							
							valorDescontoIrrf = valorDescontoIrrf.subtract(tabelaIrrf.getValorParcelaDeducaoTabelaIrrf());
							
							verbaFolhaIrrf.setValorVerba(valorDescontoIrrf);
							verbaFolhaIrrf.setReferenciaVerba(tabelaIrrf.getAliquotaTabelaIrrf());
							
							// Se a aliquota for zero, então é ISENTO
							if (tabelaIrrf.getAliquotaTabelaIrrf().compareTo(BigDecimal.ZERO) > 0) {
								verbafolhaDao.persist(verbaFolhaIrrf);
							}
							
							// Seta valores para IRRF
							contratoFolha.setBaseIrrf(baseIrrf);
							contratoFolha.setFaixaIrrf(tabelaIrrf.getAliquotaTabelaIrrf());
						} else {
							System.out.println("Não foi encontrada tabela do Irrf correspondente!");
						}
					}
					// Calculo do FGTS
					// Somente contratos com carteira assinada possuem FGTS
					if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
						BigDecimal valorFgts = basePrevidencia.multiply(getAliquotaFGTS()).divide(cem);
						contratoFolha.setValorFgts(valorFgts);
						contratoFolha.setBaseFgts(basePrevidencia);
					}
					
					contratofolhaDao.persist(contratoFolha);
				} // for Contratos
				
				count++;
				progresso = new BigDecimal((double)count / (double)contratos.size() * 100).setScale(0, BigDecimal.ROUND_HALF_EVEN);
				folha.setPercentualProcessadoFolhaPagamento(progresso);
				folha.setStatusFolhaPagamento(FolhaPagamentoVO.STATUS_PROCESSANDO);
				folhaDao.persist(folha);
			}
			
			// Final
			folha.setPercentualProcessadoFolhaPagamento(new BigDecimal(100));
			folha.setStatusFolhaPagamento(FolhaPagamentoVO.STATUS_PROCESSADO);
			folhaDao.persist(folha);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Retorna a data base da folha
	 * @return data base para folha
	 */
	private Date getDataBase() {
		return Date.valueOf(folha.getAnoCompetenciaFolhaPagamento().toString() + "-" + Utils.fillString(folha.getMesCompetenciaFolhaPagamento(), "0", 2, 1) + "-01");
	}
	
	@SuppressWarnings("deprecation")
	private Date getUltimoDiaMes() {
		Date database = getDataBase();
		Calendar cal = Calendar.getInstance();
		cal.set(database.getYear(), database.getMonth(), database.getDate());
		int maxDiasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		return Date.valueOf(folha.getAnoCompetenciaFolhaPagamento().toString() + "-" + Utils.fillString(folha.getMesCompetenciaFolhaPagamento(), "0", 2, 1) + "-" + Utils.fillString(maxDiasMes, "0", 2, 1));
	}
	
	@SuppressWarnings("deprecation")
	private BigDecimal getHorasFaltas(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		FaltaFuncionarioDAO faltaDao = new FaltaFuncionarioDAO();
		FaltaFuncionarioVO filter = new FaltaFuncionarioVO();
		filter.setCodigoEmpresa(contrato.getCodigoEmpresa());
		filter.setCodigoLoja(contrato.getCodigoLoja());
		filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		filter.setAnoReferenciaFaltaFuncionario(folha.getAnoCompetenciaFolhaPagamento());
		filter.setMesReferenciaFaltaFuncionario(folha.getMesCompetenciaFolhaPagamento());
		
		// Filtra somente as faltas completas (não parciais)
		filter.setFlagParcialMotivoFaltaFuncionario("N");
		
		List<FaltaFuncionarioVO> faltas = faltaDao.getList(filter);
		
		BigDecimal totalHoras = BigDecimal.ZERO;
		
		for (FaltaFuncionarioVO falta : faltas) {
			totalHoras = totalHoras.add(new BigDecimal(falta.getHorasFaltaFuncionario().getHours() + (falta.getHorasFaltaFuncionario().getMinutes() / 60.0)));
		}
		
		return totalHoras;
	}
	
	@SuppressWarnings("deprecation")
	private BigDecimal getHorasFaltasParcial(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		FaltaFuncionarioDAO faltaDao = new FaltaFuncionarioDAO();
		FaltaFuncionarioVO filter = new FaltaFuncionarioVO();
		filter.setCodigoEmpresa(contrato.getCodigoEmpresa());
		filter.setCodigoLoja(contrato.getCodigoLoja());
		filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		filter.setAnoReferenciaFaltaFuncionario(folha.getAnoCompetenciaFolhaPagamento());
		filter.setMesReferenciaFaltaFuncionario(folha.getMesCompetenciaFolhaPagamento());
		
		// Filtra somente as faltas parciais (atrasos, ausencias, etc)
		filter.setFlagParcialMotivoFaltaFuncionario("S");
		
		List<FaltaFuncionarioVO> faltas = faltaDao.getList(filter);
		
		BigDecimal totalHoras = BigDecimal.ZERO;
		
		for (FaltaFuncionarioVO falta : faltas) {
			totalHoras = totalHoras.add(new BigDecimal(falta.getHorasFaltaFuncionario().getHours() + (falta.getHorasFaltaFuncionario().getMinutes() / 60.0)));
		}
		
		return totalHoras;
	}
	
	/**
	 * Retorna uma lista com os DSR's (Domingos) e os feriados do mes.
	 * @param folha
	 * @return
	 * @throws SQLException
	 */
	private TreeSet<Calendar> getListDSR(FolhaPagamentoVO folha) throws SQLException {
		TreeSet<Calendar> list = new TreeSet<Calendar>(getCalendarComparator());
		
		FeriadoVO _filter = new FeriadoVO();
		_filter.setMesFeriado(folha.getMesCompetenciaFolhaPagamento());
		FeriadoDAO _dao = new FeriadoDAO();
		_dao.setSpecialCondition(" COALESCE(AnoFeriado, " + folha.getAnoCompetenciaFolhaPagamento() + ") = " + folha.getAnoCompetenciaFolhaPagamento());
		List<FeriadoVO> feriados = _dao.getList(_filter);
		
		for (FeriadoVO feriado : feriados) {
			Calendar calFeriado = Calendar.getInstance();
			calFeriado.set(folha.getAnoCompetenciaFolhaPagamento(), feriado.getMesFeriado(), feriado.getDiaFeriado());
			list.add(calFeriado);
		}
		
		// Busca os domingos
		// Varre o calendario buscando os D.S.R.'s
		Calendar dia = Utils.dateToCalendar(getDataBase());
		while ((dia.get(Calendar.MONTH) + 1) == folha.getMesCompetenciaFolhaPagamento()) {
			if (dia.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				list.add((Calendar) dia.clone());
			}
			dia.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}
	
	/**
	 * Calcula o reflexo das faltas no DSR
	 * @param contrato Contrato a ser analisado
	 * @return Número de horas / faltas
	 * @throws SQLException
	 */
	private BigDecimal getHorasFaltasDSR(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		FaltaFuncionarioDAO faltaDao = new FaltaFuncionarioDAO();
		FaltaFuncionarioVO filter = new FaltaFuncionarioVO();
		filter.setCodigoEmpresa(contrato.getCodigoEmpresa());
		filter.setCodigoLoja(contrato.getCodigoLoja());
		filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		filter.setAnoReferenciaFaltaFuncionario(folha.getAnoCompetenciaFolhaPagamento());
		filter.setMesReferenciaFaltaFuncionario(folha.getMesCompetenciaFolhaPagamento());
		
		// Se não for pra considerar as faltas parciais no DSR...
		if (getImpactaFaltaParcialDSR().equalsIgnoreCase("N")) {
			// ... então filtra somente as faltas não - parciais para cálculo
			filter.setFlagParcialMotivoFaltaFuncionario("N");
		}
		
		List<FaltaFuncionarioVO> faltas = faltaDao.getList(filter);
		
		BigDecimal totalHoras = BigDecimal.ZERO;
		
		TreeSet<Calendar> listDSR = getListDSR(folha);
		
		Calendar databaseFolha = Utils.dateToCalendar(getDataBase());
		
		for (FaltaFuncionarioVO falta : faltas) {
			Calendar diaFalta = Utils.dateToCalendar(falta.getDataFaltaFuncionario());
			
			SortedSet<Calendar> sublistDsr = listDSR.subSet(databaseFolha, diaFalta);
			
			for (Calendar diaDSR : sublistDsr) {
				// Verifica se o DSR é da mesma semana...
				if (diaDSR.get(Calendar.WEEK_OF_YEAR) == ( diaFalta.get(Calendar.WEEK_OF_YEAR) - 1 )) {
					// ..se for, perde este DSR. 
					//listDSR.remove(diaDSR);
					totalHoras = totalHoras.add(getHorasDia());
				}
			}
		}
		
		return totalHoras;
	}
	
	/**
	 * Rotina que calcula o número de horas de DSR do mes, desconsiderando as faltas e perdas de DSR.
	 * @param contrato Contrato de Trabalho
	 * @param folha Folha de Pagamento
	 * @return Numero de horas do mes
	 * @throws SQLException
	 */
	private BigDecimal getHorasDSR(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		FaltaFuncionarioDAO faltaDao = new FaltaFuncionarioDAO();
		FaltaFuncionarioVO filter = new FaltaFuncionarioVO();
		filter.setCodigoEmpresa(contrato.getCodigoEmpresa());
		filter.setCodigoLoja(contrato.getCodigoLoja());
		filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		filter.setAnoReferenciaFaltaFuncionario(folha.getAnoCompetenciaFolhaPagamento());
		filter.setMesReferenciaFaltaFuncionario(folha.getMesCompetenciaFolhaPagamento());
		
		// Se não for pra considerar as faltas parciais no DSR...
		if (getImpactaFaltaParcialDSR().equalsIgnoreCase("N")) {
			// ... então filtra somente as faltas não parciais para cálculo
			filter.setFlagParcialMotivoFaltaFuncionario("N");
		}
		
		List<FaltaFuncionarioVO> faltas = faltaDao.getList(filter);
		
		BigDecimal totalHoras = BigDecimal.ZERO;
		
		TreeSet<Calendar> listDSR = getListDSR(folha);
		
		Calendar databaseFolha = Utils.dateToCalendar(getDataBase());
		
		for (FaltaFuncionarioVO falta : faltas) {
			Calendar diaFalta = Utils.dateToCalendar(falta.getDataFaltaFuncionario());
			
			SortedSet<Calendar> sublistDsr = listDSR.subSet(databaseFolha, diaFalta);
			
			for (Calendar diaDSR : sublistDsr) {
				// Verifica se o DSR é da mesma semana...
				if (diaDSR.get(Calendar.WEEK_OF_YEAR) == diaFalta.get(Calendar.WEEK_OF_YEAR)) {
					// ..se for, perde este DSR. 
					listDSR.remove(diaDSR);
				}
			}
		}
		
		for (int i = 0; i < listDSR.size(); i++) {
			// Joga o equivalente em horas a um dia de trabalho
			totalHoras = totalHoras.add(getHorasDia());
		}
		
		return totalHoras;
	}
	
	/**
	 * 
	 * @param contrato
	 * @param folha
	 * @return
	 * @throws SQLException 
	 */
	private BigDecimal getHorasUteisMes(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		Calendar diaInicio = Utils.dateToCalendar(contrato.getDataInicioContratoTrabalho());
		Calendar diaFim = null;
		
		if (contrato.getDataFimContratoTrabalho() != null) {
			diaFim = Utils.dateToCalendar(contrato.getDataFimContratoTrabalho());
		} else {
			diaFim = Utils.dateToCalendar(getUltimoDiaMes());
			diaFim.add(Calendar.YEAR, 1); // Joga lááááá pra frente, só pra ter uma data fim de comparacao...
		}
		
		TreeSet<Calendar> listDSR = getListDSR(folha);
		TreeSet<Calendar> listDiasUteis = new TreeSet<Calendar>(getCalendarComparator());
		
		// Varre o calendario buscando os D.S.R.'s
		Calendar dia = Utils.dateToCalendar(getDataBase());
		while ((dia.get(Calendar.MONTH) + 1) == folha.getMesCompetenciaFolhaPagamento()) {
			// Verifica se o dia em questão está entre a vigencia do contrato
			if (dia.compareTo(diaInicio) >= 0 && dia.compareTo(diaFim) <= 0) {
				// Verifica se o dia é um DSR
				if (!listDSR.contains(dia)) {
					listDiasUteis.add((Calendar) dia.clone());
				}
			}
			dia.add(Calendar.DAY_OF_MONTH, 1);
		}
		// Filtra somente os dias compreendidos entre 
		// inicio e fim do contrato de trabalho
		SortedSet<Calendar> subList = listDiasUteis.subSet(diaInicio, diaFim);
		BigDecimal horasUteisMes = getHorasDia().multiply(new BigDecimal(subList.size())).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return horasUteisMes;
	}
	
	/**
	 * Busca os vales concedidos para o funcionário no mês
	 * @param contrato Contrato de Trabalho
	 * @param folha Folha de pagamento processada
	 * @return Total em Reais
	 * @throws SQLException
	 * @Deprecated Função não será mais usada.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private BigDecimal getValorValesFuncionario(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha) throws SQLException {
		ValeFuncionarioVO filter = new ValeFuncionarioVO();
		filter.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
		filter.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
		filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		filter.setAnoReferenciaValeFuncionario(folha.getAnoCompetenciaFolhaPagamento());
		filter.setMesReferenciaValeFuncionario(folha.getMesCompetenciaFolhaPagamento());
		
		ValeFuncionarioDAO _dao = new ValeFuncionarioDAO();
		
		List<ValeFuncionarioVO> vales = _dao.getList(filter);
		
		BigDecimal totalVales = BigDecimal.ZERO;
		
		for (ValeFuncionarioVO vale : vales) {
			totalVales = totalVales.add(vale.getValorValeFuncionario());
		}
		
		return totalVales;
	}
	
	/**
	 * Procedimento que calcula o valor de uma verba proporcional aos
	 * dias do mes que o contrato vigora
	 * @param contrato Contrato de trabalho
	 * @param salarioBase Valor do salario base para comparacao. Se não for aplicável, enviar nulo.
	 * @param valorVerba Valor da verba
	 * @param salarioMinimo Salario minimo em vigor
	 * @param referencia Valor de referencia, vai ser retornado.
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("deprecation")
	private BigDecimal calculaProporcionalValorVerba(ContratoTrabalhoVO contrato, BigDecimal salarioBase, BigDecimal valorVerba, TabelaSalarioMinimoVO salarioMinimo, VerbaFolhaPagamentoVO verbaFolha) throws SQLException {
		// Verifica quantos dias tem o mes
		Date database = getDataBase();
		Calendar cal = Calendar.getInstance();
		cal.set(database.getYear(), database.getMonth(), database.getDate());
		
		int maxDiasMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		BigDecimal salarioComparacao = salarioBase == null? valorVerba : salarioBase;
		
		// Verifica se a verba é menor que o salario mínimo.
		// Se for menor, o numero do dias do mes a ser considerado
		// é de 30 dias
		if (salarioMinimo != null && salarioMinimo.getValorSalarioMinimo().compareTo(salarioComparacao) >= 0) {
			maxDiasMes = 30;
		}
		int diasMes = maxDiasMes;
		
		// Ultimo dia do mes
		Date ultimoDiaMes = new Date(database.getYear(), database.getMonth(), diasMes);
		
		// Se o contrato comecou no meio do mes...
		if (contrato.getDataInicioContratoTrabalho().compareTo(database) > 0) {
			diasMes -= contrato.getDataInicioContratoTrabalho().getDate();
		}
		
		// Se o contrato termina no meio do mes...
		if (contrato.getDataFimContratoTrabalho() != null && contrato.getDataFimContratoTrabalho().compareTo(ultimoDiaMes) <= 0) {
			diasMes -= ultimoDiaMes.getDate() - contrato.getDataFimContratoTrabalho().getDate();
		}
		
		BigDecimal fator = new BigDecimal((double) diasMes / maxDiasMes);
		
		BigDecimal valor = valorVerba;
		
		// Proporcionaliza
		if (fator.compareTo(BigDecimal.ONE) != 0) {
			valor = valor.multiply(fator).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			if (verbaFolha != null) {
				if (getFormaCalculoProporcao().equalsIgnoreCase("D")) { // Dias
					verbaFolha.setReferenciaVerba(new BigDecimal(diasMes));
				} else if (getFormaCalculoProporcao().equalsIgnoreCase("H")) { // Horas
					verbaFolha.setReferenciaVerba(fator.multiply(new BigDecimal(getNumeroHorasMes())));
				}
			}
		} else {
			if (verbaFolha != null) {
				if (getFormaCalculoProporcao().equalsIgnoreCase("D")) { // Dias
					verbaFolha.setReferenciaVerba(new BigDecimal(30));
				} else if (getFormaCalculoProporcao().equalsIgnoreCase("H")) { // Horas
					verbaFolha.setReferenciaVerba(new BigDecimal(getNumeroHorasMes()));
				}
			}
		}
		
		if (verbaFolha != null) {
			verbaFolha.setValorVerba(valor);
		}
		
		return valor;
	}
	
	private Comparator<Calendar> getCalendarComparator() {
		return new Comparator<Calendar>() {
			@Override
			public int compare(Calendar o1, Calendar o2) {
				return o1.compareTo(o2);
			}
		};
	}
	
	/**
	 * "Zera" um processamento de folha
	 * @param folha Folha a ser zerada
	 */
	private void zeraFolha(FolhaPagamentoVO folha) {
		try {
			VerbaFolhaPagamentoDAO verbaFolhaDao = new VerbaFolhaPagamentoDAO();
			VerbaFolhaPagamentoVO verbaFolhaFilter = new VerbaFolhaPagamentoVO();
			
			verbaFolhaFilter.setCodigoEmpresa(folha.getCodigoEmpresa());
			verbaFolhaFilter.setCodigoLoja(folha.getCodigoLoja());
			verbaFolhaFilter.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
			
			List<VerbaFolhaPagamentoVO> listVerbasFolhas = verbaFolhaDao.getList(verbaFolhaFilter);
			
			// Zera verbas da folha
			for (VerbaFolhaPagamentoVO verbafolha : listVerbasFolhas) {
				verbaFolhaDao.remove(verbafolha);
			}
			
			ContratoTrabalhoFolhaPagamentoDAO contratoFolhaDao = new ContratoTrabalhoFolhaPagamentoDAO();
			ContratoTrabalhoFolhaPagamentoVO contratoFolhaFilter = new ContratoTrabalhoFolhaPagamentoVO();
			
			contratoFolhaFilter.setCodigoEmpresa(folha.getCodigoEmpresa());
			contratoFolhaFilter.setCodigoLoja(folha.getCodigoLoja());
			contratoFolhaFilter.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
			
			List<ContratoTrabalhoFolhaPagamentoVO> listContratosFolha = contratoFolhaDao.getList(contratoFolhaFilter);
			
			// Zera contratos da folha
			for (ContratoTrabalhoFolhaPagamentoVO contratoFolha : listContratosFolha) {
				contratoFolhaDao.remove(contratoFolha);
			}
			
			// Zera percentual de processamento da folha
			folha.setPercentualProcessadoFolhaPagamento(BigDecimal.ZERO);
			folha.setStatusFolhaPagamento(FolhaPagamentoVO.STATUS_PROCESSANDO);
			folhaDao.persist(folha);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setFolha(FolhaPagamentoVO folha) {
		this.folha = folha;
	}

	public FolhaPagamentoVO getFolha() {
		return folha;
	}
	public void setFormaCalculoProporcao(String formaCalculoProporcao) {
		this.formaCalculoProporcao = formaCalculoProporcao;
	}
	public String getFormaCalculoProporcao() {
		return formaCalculoProporcao;
	}
	public void setNumeroHorasMes(Integer numeroHorasMes) {
		this.numeroHorasMes = numeroHorasMes;
	}
	public Integer getNumeroHorasMes() {
		return numeroHorasMes;
	}
	public void setVerbaINSS(VerbaVO verbaINSS) {
		this.verbaINSS = verbaINSS;
	}
	public VerbaVO getVerbaINSS() {
		return verbaINSS;
	}
	public void setVerbaIRRF(VerbaVO verbaIRRF) {
		this.verbaIRRF = verbaIRRF;
	}
	public VerbaVO getVerbaIRRF() {
		return verbaIRRF;
	}
	public void setVerbaSalarioFamilia(VerbaVO verbaSalarioFamilia) {
		this.verbaSalarioFamilia = verbaSalarioFamilia;
	}
	public VerbaVO getVerbaSalarioFamilia() {
		return verbaSalarioFamilia;
	}
	public void setVerbaFalta(VerbaVO verbaFalta) {
		this.verbaFalta = verbaFalta;
	}
	public VerbaVO getVerbaFalta() {
		return verbaFalta;
	}
	public void setVerbaFaltaDSR(VerbaVO verbaFaltaDSR) {
		this.verbaFaltaDSR = verbaFaltaDSR;
	}
	public VerbaVO getVerbaFaltaDSR() {
		return verbaFaltaDSR;
	}
	public void setHorasDia(BigDecimal horasDia) {
		this.horasDia = horasDia;
	}
	public BigDecimal getHorasDia() {
		return horasDia;
	}
	public void setImpactaFaltaParcialDSR(String impactaFaltaParcialDSR) {
		this.impactaFaltaParcialDSR = impactaFaltaParcialDSR;
	}
	public String getImpactaFaltaParcialDSR() {
		return impactaFaltaParcialDSR;
	}
	public void setVerbaFaltaParcial(VerbaVO verbaFaltaParcial) {
		this.verbaFaltaParcial = verbaFaltaParcial;
	}
	public VerbaVO getVerbaFaltaParcial() {
		return verbaFaltaParcial;
	}
	public void setVerbaVale(VerbaVO verbaVale) {
		this.verbaVale = verbaVale;
	}
	public VerbaVO getVerbaVale() {
		return verbaVale;
	}
	public void setVerbaComissaoDSR(VerbaVO verbaComissaoDSR) {
		this.verbaComissaoDSR = verbaComissaoDSR;
	}
	public VerbaVO getVerbaComissaoDSR() {
		return verbaComissaoDSR;
	}
	public void setAliquotaFGTS(BigDecimal aliquotaFGTS) {
		this.aliquotaFGTS = aliquotaFGTS;
	}
	public BigDecimal getAliquotaFGTS() {
		return aliquotaFGTS;
	}
}
