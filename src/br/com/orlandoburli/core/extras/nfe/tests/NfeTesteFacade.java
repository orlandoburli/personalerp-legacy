package br.com.orlandoburli.core.extras.nfe.tests;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.thoughtworks.xstream.XStream;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.fiscal.nfe.LogNFEletronicaDAO;
import br.com.orlandoburli.core.dao.fiscal.nfe.NFEletronicaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.IServico.Ambiente;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.extras.nfe.model.details.Det;
import br.com.orlandoburli.core.extras.nfe.services.NfeCancelamentoService;
import br.com.orlandoburli.core.extras.nfe.services.NfeConsultaNFService;
import br.com.orlandoburli.core.extras.nfe.services.NfeRecepcaoService;
import br.com.orlandoburli.core.extras.nfe.services.NfeRetRecepcaoService;
import br.com.orlandoburli.core.extras.nfe.utils.NfeSigner;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.extras.nfe.utils.NfeSigner.TagSign;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.fiscal.nfe.LogNFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.TipoRegistroNFEletronicaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class NfeTesteFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private String enviaNfe = "N";
	
	public void execute() {
		try {
			NFe nfe = getNfe();
			
			NFEletronicaDAO nfeDao = new NFEletronicaDAO();
			
			NFEletronicaVO nfeVo = new NFEletronicaVO();
			
			nfeVo.setNewRecord(true);
			nfeVo.setCodigoEmpresa(1);
			nfeVo.setCodigoLoja(1);
			nfeVo.setCodigoNFe(Integer.valueOf(nfe.getInfNfe().getIde().getnNF()));
			nfeVo.setStatusNFe(NFEletronicaVO.STATUS_VALIDADA);
			nfeVo.setChaveNFe(nfe.getInfNfe().getId());
			
			nfeDao.persist(nfeVo);
			
			//if (getEnviaNfe().equals("S")) {
				transmiteNfe(nfe, nfeVo);
			//}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public NFe getNfe() {
		NFe nfe = NFe.getInstance();
		
		Integer proximaNota = 0;
		try {
			proximaNota = new NFEletronicaDAO().getNextNFEletronica(1, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} //Integer.valueOf((int) (Math.random() * 2345));
		
		// Identificação da Nota Fiscal
		nfe.getInfNfe().getIde().setcMunFG("5108402");
		nfe.getInfNfe().getIde().setcNF("093749528");
		nfe.getInfNfe().getIde().setnNF(proximaNota.toString());
		nfe.getInfNfe().getIde().setcUF("51");
		nfe.getInfNfe().getIde().setdEmi("2011-02-10");
		nfe.getInfNfe().getIde().setdSaiEnt("2011-02-10");
		nfe.getInfNfe().getIde().setFinNFe("1");
		nfe.getInfNfe().getIde().setIndPag("0");
		nfe.getInfNfe().getIde().setMod("55");
		nfe.getInfNfe().getIde().setNatOp("VENDA DE MERCADORIAS");
		nfe.getInfNfe().getIde().setProcEmi("0");
		nfe.getInfNfe().getIde().setSerie("1");
		nfe.getInfNfe().getIde().setTpAmb("2");
		nfe.getInfNfe().getIde().setTpEmis("1");
		nfe.getInfNfe().getIde().setTpNF("1");
		nfe.getInfNfe().getIde().setTpImp("1");
		nfe.getInfNfe().getIde().setVerProc("PersonalERP 1.0.2.0");
		
		// Dados do Emitente
		nfe.getInfNfe().getEmit().setCNPJ("04164155000114");
		nfe.getInfNfe().getEmit().setxNome("RONDOMAQ MAQUINAS E VEICULOS LTDA");
		nfe.getInfNfe().getEmit().setIE("131994670");
		nfe.getInfNfe().getEmit().setCRT("1");
		
		// Endereco do Emitente
		nfe.getInfNfe().getEmit().getEnderEmit().setxLgr("AVENIDA DA FEB, 505");
		nfe.getInfNfe().getEmit().getEnderEmit().setNro("0");
		nfe.getInfNfe().getEmit().getEnderEmit().setxBairro("MANGA");
		nfe.getInfNfe().getEmit().getEnderEmit().setcMun("5108402");
		nfe.getInfNfe().getEmit().getEnderEmit().setxMun("VARZEA GRANDE");
		nfe.getInfNfe().getEmit().getEnderEmit().setUF("MT");
		nfe.getInfNfe().getEmit().getEnderEmit().setCEP("78115000");
		nfe.getInfNfe().getEmit().getEnderEmit().setFone("6533881212");
		
		// Dados do destinatário
		nfe.getInfNfe().getDest().setCNPJ("03843190001411");
		nfe.getInfNfe().getDest().setxNome("RONDOMAQ MAQUINAS E VEICULOS LTDA");
		nfe.getInfNfe().getDest().setIE("00132077566");
		
		// Endereco do destinatário
		nfe.getInfNfe().getDest().getEnderDest().setxLgr("AV TANCREDO NEVES, 6064");
		nfe.getInfNfe().getDest().getEnderDest().setNro("0");
		nfe.getInfNfe().getDest().getEnderDest().setxBairro("SETOR INDUSTRIAL");
		nfe.getInfNfe().getDest().getEnderDest().setcMun("5105622");
		nfe.getInfNfe().getDest().getEnderDest().setxMun("MIRASSOL D'OESTE");
		nfe.getInfNfe().getDest().getEnderDest().setUF("MT");
		nfe.getInfNfe().getDest().getEnderDest().setCEP("78280000");
		nfe.getInfNfe().getDest().getEnderDest().setFone("6532413575");
		
		// Itens da nota
		for (int i = 0; i < 40; i++) {
			Det det = Det.getInstance(nfe);
			
			det.getProd().setcProd("040961R1");
			det.getProd().setxProd("PARAFUSO");
			det.getProd().setcEAN("");
			det.getProd().setNCM("73181500");
			det.getProd().setCFOP("5102");
			det.getProd().setuCom("UN");
			det.getProd().setqCom(new BigDecimal(1.0));
			det.getProd().setuTrib("UN");
			det.getProd().setvUnCom(new BigDecimal(12.34));
			det.getProd().setvProd(new BigDecimal(12.34));
			det.getProd().setqTrib(new BigDecimal(1.0000));
			det.getProd().setcEANTrib("");
			det.getProd().setvUnTrib(new BigDecimal(12.34));
			det.getProd().setIndTot("1");
			det.getProd().setxPed("654321");
			det.getProd().setnItemPed("001");
			
			// Icms do item
			det.getImposto().getICMS().setCSTICMS("00");
			det.getImposto().getICMS().getICMS00().setModBC("0");
			det.getImposto().getICMS().getICMS00().setOrig("0");
			det.getImposto().getICMS().getICMS00().setpICMS(new BigDecimal(17.00));
			det.getImposto().getICMS().getICMS00().setvBC(new BigDecimal(12.34));
			det.getImposto().getICMS().getICMS00().setvICMS(new BigDecimal(2.19));
			
			// Pis do Item
			det.getImposto().getPIS().setCSTPIS("01");
			det.getImposto().getPIS().getPISAliq().setCST("01");
			det.getImposto().getPIS().getPISAliq().setpPIS(new BigDecimal(6.00));
			det.getImposto().getPIS().getPISAliq().setvBC(new BigDecimal(12.34));
			det.getImposto().getPIS().getPISAliq().setvPIS(new BigDecimal(0.74));
			
			// Cofins do Item
			det.getImposto().getCOFINS().setCSTCOFINS("01");
			det.getImposto().getCOFINS().getCOFINSAliq().setCST("01");
			det.getImposto().getCOFINS().getCOFINSAliq().setpCOFINS(new BigDecimal(6.00));
			det.getImposto().getCOFINS().getCOFINSAliq().setvBC(new BigDecimal(12.34));
			det.getImposto().getCOFINS().getCOFINSAliq().setvCOFINS(new BigDecimal(0.74));
		}
		// Totais da nota
		nfe.getInfNfe().getTotal().getICMSTot().setvBC(new BigDecimal(12.34));
		nfe.getInfNfe().getTotal().getICMSTot().setvBCST(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvCOFINS(new BigDecimal(0.74));
		nfe.getInfNfe().getTotal().getICMSTot().setvDesc(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvFrete(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvICMS(new BigDecimal(2.09));
		nfe.getInfNfe().getTotal().getICMSTot().setvII(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvIPI(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvNF(new BigDecimal(12.34));
		nfe.getInfNfe().getTotal().getICMSTot().setvOutro(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvPIS(new BigDecimal(0.74));
		nfe.getInfNfe().getTotal().getICMSTot().setvProd(new BigDecimal(12.34));
		nfe.getInfNfe().getTotal().getICMSTot().setvSeg(new BigDecimal(0.00));
		nfe.getInfNfe().getTotal().getICMSTot().setvST(new BigDecimal(0.00));
		
		nfe.getInfNfe().getTransp().setModFrete("9");
//		nfe.getInfNfe().getTransp().getTransporta().setCPF("70289018153");
//		nfe.getInfNfe().getTransp().getTransporta().setIE("ISENTO");
//		nfe.getInfNfe().getTransp().getTransporta().setUF("MT");
//		nfe.getInfNfe().getTransp().getTransporta().setxEnder("AAAAAA AAAAASSSSSS");
//		nfe.getInfNfe().getTransp().getTransporta().setxMun("CUIABA");
//		nfe.getInfNfe().getTransp().getTransporta().setxNome("JOAO DA SILVA");
		
		// Por ultimo seta o Id... depois de calculado
		nfe.getInfNfe().setId("NFe" + NfeUtils.buildNfeKey(nfe));
		// O método setId já seta o cDV (Digito verificador)
		return nfe;
	}
	
	public void transmiteNfe(NFe nfe, NFEletronicaVO nfeVo) {
	 	String xmlNfe = null;
	 	
		try {
			
			LogNFEletronicaDAO logNfeDao = new LogNFEletronicaDAO();
			
			XStream stream = new XStream();
			
			LogNFEletronicaVO log1 = new LogNFEletronicaVO();
			log1.setNewRecord(true);
			log1.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log1.setCodigoLoja(nfeVo.getCodigoLoja());
			log1.setCodigoNFe(nfeVo.getCodigoNFe());
			log1.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_OBJETO);
			log1.setDataHoraLogNFe(Utils.getNow());
			log1.setDadosLogNFe(stream.toXML(nfe));
			
			logNfeDao.persist(log1);
			
			Ambiente ambiente = Ambiente.Homologacao;
			
			write("Id da Nf-e: " + nfe.getInfNfe().getId() + "\n\n");
			
			// Constrói o Xml da NF-e
			xmlNfe = NfeUtils.NfeToXml(nfe);
			
			LogNFEletronicaVO log2 = new LogNFEletronicaVO();
			log2.setNewRecord(true);
			log2.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log2.setCodigoLoja(nfeVo.getCodigoLoja());
			log2.setCodigoNFe(nfeVo.getCodigoNFe());
			log2.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_GERADO);
			log2.setDataHoraLogNFe(Utils.getNow());
			log2.setDadosLogNFe(xmlNfe);
			
			logNfeDao.persist(log2);
			
			String xmlAssinado = NfeSigner.sign(xmlNfe, "D:\\desenvolvimento\\PersonalErp\\nfe\\cfm.pfx", "cfm6970", TagSign.infNFe);
			
			LogNFEletronicaVO log3 = new LogNFEletronicaVO();
			log3.setNewRecord(true);
			log3.setCodigoEmpresa(nfeVo.getCodigoEmpresa());
			log3.setCodigoLoja(nfeVo.getCodigoLoja());
			log3.setCodigoNFe(nfeVo.getCodigoNFe());
			log3.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_ASSINADO);
			log3.setDataHoraLogNFe(Utils.getNow());
			log3.setDadosLogNFe(xmlAssinado);
			
			logNfeDao.persist(log3);
			
			setEnviaNfe("N");
			
			if (getEnviaNfe().equals("S")) {
				
				NfeRecepcaoService recepcaoService = new NfeRecepcaoService();
			
				if (recepcaoService.execute(xmlAssinado, ambiente, null)) {
					write("Lote Recebido.\n");
				} else {
					write("Erro no envio do lote!\n");
					write(recepcaoService.getLastMessages()[0]);
				}
				
				String codigoRetorno = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "cStat");
				
				if (codigoRetorno.equals(Constants.NfeParameters.Retornos.LOTE_RECEBIDO_SUCESSO)) {
					String numeroRecibo = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "nRec");
					String dataHoraRecibo = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "dhRecbto");
					write("\n");
					write("Numero do Recibo: " + numeroRecibo + "\n");
					write("Data / Hora do Recibo: " + dataHoraRecibo + "\n");
					write("\n");
					write("Buscando retorno...\n\n");
					
					// Enquanto retornar o codigo 105 (em processamento), fica buscando...
					String codigoStatusProcessamento = Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO;
					
					while (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)) {
						
						NfeRetRecepcaoService retornoService = new NfeRetRecepcaoService();
						
						if (retornoService.execute(numeroRecibo, ambiente, null)) {
							
							codigoStatusProcessamento = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "cStat");
							
							if (!codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)) {
								
								String codigoRetornoNfe = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt", "cStat");
								String mensagemProtocoloNfe = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt", "xMotivo");
								
								write("Dados do processamento\n");
								write("Codigo de Retorno do processamento da Sefaz: " + codigoStatusProcessamento);
								write("\n");
								write("Mensagem de Retorno do processamento da Sefaz: " + NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "xMotivo"));
								write("\n");
								write("\n");
								
								write("Codigo de Retorno da Sefaz: " + codigoRetornoNfe);
								write("\n");
								write("Motivo: " + mensagemProtocoloNfe);
								write("\n");
								write("\n");
								write("\n");
								
								if (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_PROCESSADO)) {
									
									if (codigoRetornoNfe.equals(Constants.NfeParameters.Retornos.NFE_AUTORIZADA)) {
										
										String numeroProtocoloNfe = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "infProt",  "nProt");
										
										// Agora vamos tentar consultar a nota...
										write("Consultando a nota...\n");
										NfeConsultaNFService consultaService = new NfeConsultaNFService();
										consultaService.execute(nfe.getInfNfe().getId().replace("NFe", ""), ambiente, null);
										String codigoRetornoConsultaNfe = NfeUtils.readXmlTag(consultaService.getLastMessages()[0], "cStat");
										String mensagemRetornoConsultaNfe = NfeUtils.readXmlTag(consultaService.getLastMessages()[0], "xMotivo");
										write("Retorno da consulta: " + codigoRetornoConsultaNfe + "\n");
										write("Mensagem de retorno da consulta: " + mensagemRetornoConsultaNfe + "\n");
										write("\n\n");
										
										// Agora vamos CANCELAR a nota!!!
										write("Cancelando a nota...\n");
										NfeCancelamentoService cancelamentoService = new NfeCancelamentoService();
										String xmlCanc = cancelamentoService.buildXmlCancelamento(nfe.getInfNfe().getId(), numeroProtocoloNfe, "Nota de teste...", ambiente, null);
										cancelamentoService.execute(xmlCanc, ambiente, null);
										
										String codigoRetornoCancelamento = NfeUtils.readXmlTag(cancelamentoService.getLastMessages()[0], "cStat");
										String mensagemRetornoCancelamento = NfeUtils.readXmlTag(cancelamentoService.getLastMessages()[0], "xMotivo");
										write("Retorno do cancelamento: " + codigoRetornoCancelamento + "\n");
										write("Mensagem de retorno do cancelamento: " + mensagemRetornoCancelamento + "\n");
										
									}
								}
							}
						} else {
							codigoStatusProcessamento = "";
							write("Sefaz retornou erro na consulta do lote!\n");
							String msgRetorno = NfeUtils.readXmlTag(retornoService.getLastMessages()[0], "xMotivo");
							write(codigoRetorno + " - " + msgRetorno);
						}
						
						// Pausa por 10 segundos...
						if (codigoStatusProcessamento.equals(Constants.NfeParameters.Retornos.LOTE_EM_PROCESSAMENTO)){
							Thread.sleep(10000);
						}
					}
				} else {
					write("Sefaz retornou erro no lote!\n");
					String msgRetorno = NfeUtils.readXmlTag(recepcaoService.getLastMessages()[0], "xMotivo");
					write(codigoRetorno + " - " + msgRetorno);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEnviaNfe(String enviaNfe) {
		this.enviaNfe = enviaNfe;
	}

	public String getEnviaNfe() {
		return enviaNfe;
	}
}
