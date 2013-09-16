package br.com.orlandoburli.personalerp.facades.financeiro.reciboavulso;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.utils.ConvertToReais;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.financeiro.ReciboAvulsoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class ReciboAvulsoFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private String TextoRecibo;
	private String NomeDestinatario;
	private String ReferenteA;
	private String Emitente;
	private Date DataRecibo;
	@Precision(decimals=2)
	private BigDecimal ValorRecibo;
	
	@Override
	public JRDataSource getDataSource() {
		CidadeDAO _dao = new CidadeDAO();
		ConvertToReais convert = new ConvertToReais();
		
		ReciboAvulsoVO recibo = new ReciboAvulsoVO();
		
		recibo.setEmpresa(getEmpresasessao());
		recibo.setLoja(getLojasessao());
		recibo.setTextoRecibo(TextoRecibo);
		recibo.setNomeDestinatario(NomeDestinatario);
		recibo.setReferenteA(ReferenteA);
		recibo.setDataRecibo(DataRecibo);
		recibo.setValorRecibo(ValorRecibo);
		recibo.setEmitente(Emitente);
		
		recibo.setValorReciboExtenso(convert.porExtenso(recibo.getValorRecibo()));
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<DESTINATARIO>", recibo.getNomeDestinatario()));
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<EMITENTE>", recibo.getEmitente()));
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<VALOR>", numberFormat.format(recibo.getValorRecibo())));
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<VALOR_EXTENSO>", recibo.getValorReciboExtenso()));
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<REFERENTE>", recibo.getReferenteA()));
		recibo.setTextoRecibo(recibo.getTextoRecibo().replace("<DATA>", sdf.format(recibo.getDataRecibo())));
		
		try {
			recibo.setCidade(_dao.get(getLojasessao().getCodigoCidadeLoja()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<Object> list = new ArrayList<Object>();
		list.add(recibo);
		list.add(recibo.clone()); // Adiciona 2 vezes para ter a cópia (2 vias)
		
		return new JRBeanCollectionDataSource(list);
	}
	
	@Override
	protected String getJasperFileName() {
		return "reports/reciboAvulso.jasper";
	}
}