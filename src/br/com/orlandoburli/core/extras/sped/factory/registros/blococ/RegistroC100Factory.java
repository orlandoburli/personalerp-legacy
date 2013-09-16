package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.NFEntradaDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC100;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;

public class RegistroC100Factory {

	private static RegistroC100Factory factory;

	private RegistroC100Factory() {

	}

	public static RegistroC100Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC100Factory();
		}
		return factory;
	}
	
	public void buildRegistroC100(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException {
		NFEntradaDAO nfEntradaDao = new NFEntradaDAO();
		nfEntradaDao.mergeDAO(dao);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String filterDataNFEntrada = " a.DataEmissaoNFEntrada BETWEEN '" + sdf.format(arquivo.getDataInicial()) + "' AND '" + sdf.format(arquivo.getDataFinal()) + "' \n";
		
		NFEntradaVO nfentradaFilter = new NFEntradaVO();
		
		nfentradaFilter.setCodigoEmpresa(arquivo.getLoja().getCodigoEmpresa());
		nfentradaFilter.setCodigoLoja(arquivo.getLoja().getCodigoLoja());
		
		nfEntradaDao.setSpecialCondition(filterDataNFEntrada);
		
		List<NFEntradaVO> listNFEntrada = nfEntradaDao.getList(nfentradaFilter);
		
		for (NFEntradaVO nfentrada : listNFEntrada) {
			String codigoParticipante = Utils.fillString(nfentrada.getCodigoEmpresaEmitenteNFEntrada(), "0", 3, 1) + Utils.fillString(nfentrada.getCodigoLojaEmitenteNFEntrada(), "0", 3, 1) + Utils.fillString(nfentrada.getCodigoPessoaEmitenteNFEntrada(), "0", 8, 1) + Utils.fillString(nfentrada.getCodigoEnderecoPessoaEmitenteNFEntrada(), "0", 8, 1);

			RegistroC100 regc100 = new RegistroC100();
			
			regc100.setIndicadorOperacao("0"); // Entrada
			regc100.setIndicadorEmitente("1"); // Emissao de terceiros???
			
			regc100.setCodigoParticipante(codigoParticipante);
			regc100.setCodigoModelo(nfentrada.getModeloNFEntrada());
			regc100.setCodigoSituacao("00"); // Se esta aqui no sistema é porque está regular.
			
			regc100.setSerie(nfentrada.getSerieNFEntrada());
			regc100.setNumeroDocumento(nfentrada.getCodigoNFEntrada());
			regc100.setChaveNfe(nfentrada.getChaveNFeEntrada());
			
			regc100.setDataDocumento(nfentrada.getDataEmissaoNFEntrada());
			
			if (nfentrada.getDataHoraEntradaNFEntrada() != null) {
				regc100.setDataEntradaSaida(new Date(nfentrada.getDataHoraEntradaNFEntrada().getTime()));
			}
			
			regc100.setValorDocumento(nfentrada.getValorTotalNFEntrada());
			regc100.setIndicadorPagamento(nfentrada.getFormaPagamentoNFEntrada());
			
			regc100.setValorDesconto(nfentrada.getValorTotalDescontoNFEntrada());
			regc100.setValorAbatimento(BigDecimal.ZERO);// Nao tenho esse campo
			
			regc100.setValorMercadorias(nfentrada.getValorTotalProdutosNFEntrada());
			regc100.setIndicadorFrete(nfentrada.getModalidadeFreteNFEntrada());
			
			regc100.setValorFrete(nfentrada.getValorFreteNFEntrada());
			regc100.setValorSeguro(nfentrada.getValorSeguroNFEntrada());
			regc100.setValorOutrasDespesas(nfentrada.getValorOutrasDespesasNFEntrada());
			
			regc100.setValorBaseIcms(nfentrada.getValorBaseCalculoIcmsNFEntrada());
			regc100.setValorBaseIcmsSt(nfentrada.getValorBaseCalculoIcmsSTNFEntrada());
			regc100.setValorIcms(nfentrada.getValorIcmsNFEntrada());
			regc100.setValorIcmsSt(nfentrada.getValorIcmsSTNFEntrada());
			
			regc100.setValorIpi(nfentrada.getValorTotalIpiNFEntrada());
			regc100.setValorPis(nfentrada.getValorTotalPisNFEntrada());
			regc100.setValorCofins(nfentrada.getValorTotalCofinsNFEntrada());
			
			regc100.setValorPisSt(BigDecimal.ZERO);
			regc100.setValorCofinsSt(BigDecimal.ZERO);
			
			arquivo.addRegistro(regc100);
			
			// Adiciona a nota à lista de itens auxiliares
			arquivo.getListVo().add(nfentrada);
			
			// Itens da nota
			RegistroC170Factory.getFactory().buildRegistroC170(arquivo, dao, nfentrada, regc100);
		}
	}
}
