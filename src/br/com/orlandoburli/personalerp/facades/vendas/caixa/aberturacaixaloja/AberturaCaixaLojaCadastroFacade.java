package br.com.orlandoburli.personalerp.facades.vendas.caixa.aberturacaixaloja;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.vendas.caixa.AberturaCaixaLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.caixa.AberturaCaixaLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class AberturaCaixaLojaCadastroFacade extends BaseCadastroFlexFacade<AberturaCaixaLojaVO, AberturaCaixaLojaDAO>{

	private static final long serialVersionUID = 1L;
	
	private String DataAberturaCaixa;
	private String HoraAberturaCaixa;
	private String DataFechamentoCaixa;
	private String HoraFechamentoCaixa;

	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getValorAberturaCaixaLoja() == null) {
			getVo().setValorAberturaCaixaLoja(BigDecimal.ZERO);
		}
		
		if (getDataAberturaCaixa() == null) {
			this.messages.add(new MessageVO("Campo Data de Abertura do Caixa é obrigatório!", "DataAberturaCaixa"));
			return false;
		}
		
		if (getHoraAberturaCaixa() == null) {
			this.messages.add(new MessageVO("Campo Hora de Abertura do Caixa é obrigatório!", "HoraAberturaCaixa"));
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try {
			getVo().setDataHoraAberturaCaixa(new Timestamp(sdf.parse(getDataAberturaCaixa() + " " + getHoraAberturaCaixa()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (getVo().getDataHoraAberturaCaixa() == null) {
			this.messages.add(new MessageVO("Campo Data e Hora de Abertura do Caixa é obrigatório!", "DataAberturaCaixa"));
			return false;
		}
		
		if (getVo().getCodigoVendedorAbertura() == null) {
			this.messages.add(new MessageVO("Campo Vendedor de Abertura do Caixa é obrigatório!", "VendedorAbertura"));
			return false;
		}
		
		// Se estiver fechado...
		if (getVo().getStatusAberturaCaixaLoja().equals("F")) {
			if (getDataFechamentoCaixa() == null) {
				this.messages.add(new MessageVO("Campo Data de Fechamento do Caixa é obrigatório!", "DataFechamentoCaixa"));
				return false;
			}
			
			if (getHoraFechamentoCaixa() == null) {
				this.messages.add(new MessageVO("Campo Hora de Fechamento do Caixa é obrigatório!", "HoraFechamentoCaixa"));
				return false;
			}
			
			try {
				getVo().setDataHoraFechamentoCaixa(new Timestamp(sdf.parse(getDataFechamentoCaixa() + " " + getHoraFechamentoCaixa()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (getVo().getDataHoraFechamentoCaixa() == null) {
				this.messages.add(new MessageVO("Campo Data e Hora de Fechamento do Caixa é obrigatório!", "DataFechamentoCaixa"));
				return false;
			}
			
			if (getVo().getCodigoVendedorFechamento() == null) {
				this.messages.add(new MessageVO("Campo Vendedor de Fechamento do Caixa é obrigatório!", "VendedorFechamento"));
				return false;
			}
			
			if (getVo().getValorFechamentoCaixaLoja() == null || getVo().getValorFechamentoCaixaLoja().compareTo(BigDecimal.ZERO) < 0) {
				this.messages.add(new MessageVO("Campo Valor de Fechamento do Caixa é obrigatório!", "ValorFechamento"));
				return false;
			}
		}
		
		return super.doBeforeSave();
	}
	
	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			List<IValueObject> list = getGenericDao().getList(new VendedorVO());
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDataAberturaCaixa(String dataAberturaCaixa) {
		DataAberturaCaixa = dataAberturaCaixa;
	}

	public String getDataAberturaCaixa() {
		return DataAberturaCaixa;
	}

	public void setHoraAberturaCaixa(String horaAberturaCaixa) {
		HoraAberturaCaixa = horaAberturaCaixa;
	}

	public String getHoraAberturaCaixa() {
		return HoraAberturaCaixa;
	}

	public void setDataFechamentoCaixa(String dataFechamentoCaixa) {
		DataFechamentoCaixa = dataFechamentoCaixa;
	}

	public String getDataFechamentoCaixa() {
		return DataFechamentoCaixa;
	}

	public void setHoraFechamentoCaixa(String horaFechamentoCaixa) {
		HoraFechamentoCaixa = horaFechamentoCaixa;
	}

	public String getHoraFechamentoCaixa() {
		return HoraFechamentoCaixa;
	}
}