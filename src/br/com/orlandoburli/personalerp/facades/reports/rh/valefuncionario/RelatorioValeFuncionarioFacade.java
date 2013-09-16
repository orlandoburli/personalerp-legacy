package br.com.orlandoburli.personalerp.facades.reports.rh.valefuncionario;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.rh.FuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioValeFuncionarioFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresaFuncionario;
	private Integer CodigoLojaFuncionario;
	private Integer CodigoFuncionario;

	private String NomeFuncionario;
	
	private Integer CodigoVerba;
	private String DescricaoVerba;

	private Date DataInicial;
	private Date DataFinal;

	private String FlagTodasLojas;

	@IgnoreMethodAuthentication
	public void dados() {
		try {
			List<FuncionarioVO> funcionarios = new FuncionarioDAO().getList(null, "NomeFuncionario");
			List<VerbaVO> verbas = new VerbaDAO().getList(null, "DescricaoVerba");
			List<IValueObject> list = new ArrayList<IValueObject>();
			list.addAll(funcionarios);
			list.addAll(verbas);
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {

		if (getFlagTodasLojas() != null && getFlagTodasLojas().equals("N")) {
			parameters.put("CodigoEmpresaLotacao", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLojaLotacao", getLojasessao().getCodigoLoja());
		} else {
			parameters.put("CodigoEmpresaLotacao", null);
			parameters.put("CodigoLojaLotacao", null);
		}

		parameters.put("CodigoEmpresaFuncionario", getCodigoEmpresaFuncionario());
		parameters.put("CodigoLojaFuncionario", getCodigoLojaFuncionario());
		parameters.put("CodigoFuncionario", getCodigoFuncionario());

		parameters.put("CodigoVerba", getCodigoVerba());
		parameters.put("DescricaoVerba", getDescricaoVerba());
		
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		String filtroDescricao = "Período de " + sdf.format(getDataInicial()) + " a " + sdf.format(getDataFinal());
		if (getFlagTodasLojas() != null && getFlagTodasLojas().equals("N")) {
			filtroDescricao += "\nSomente loja " + getLojasessao().getNomeLoja();
		} else {
			filtroDescricao += "\nTodas as lojas";
		}
		
		if (getNomeFuncionario() != null && !getNomeFuncionario().trim().equals("")) {
			filtroDescricao += "\nFuncionário: " + getNomeFuncionario();
		}
		
		if (getDescricaoVerba() != null && !getDescricaoVerba().trim().equals("")) {
			filtroDescricao += "\nVerba: " + getDescricaoVerba();
		}
		
		parameters.put("filtroDescricao", filtroDescricao);

		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioAdiantamentoFuncionario.jasper";
	}

	public Integer getCodigoEmpresaFuncionario() {
		return CodigoEmpresaFuncionario;
	}

	public void setCodigoEmpresaFuncionario(Integer codigoEmpresaFuncionario) {
		CodigoEmpresaFuncionario = codigoEmpresaFuncionario;
	}

	public Integer getCodigoLojaFuncionario() {
		return CodigoLojaFuncionario;
	}

	public void setCodigoLojaFuncionario(Integer codigoLojaFuncionario) {
		CodigoLojaFuncionario = codigoLojaFuncionario;
	}

	public Integer getCodigoFuncionario() {
		return CodigoFuncionario;
	}

	public void setCodigoFuncionario(Integer codigoFuncionario) {
		CodigoFuncionario = codigoFuncionario;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public String getFlagTodasLojas() {
		return FlagTodasLojas;
	}

	public void setFlagTodasLojas(String flagTodasLojas) {
		FlagTodasLojas = flagTodasLojas;
	}

	public String getNomeFuncionario() {
		return NomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		NomeFuncionario = nomeFuncionario;
	}

	public Integer getCodigoVerba() {
		return CodigoVerba;
	}

	public void setCodigoVerba(Integer codigoVerba) {
		CodigoVerba = codigoVerba;
	}

	public String getDescricaoVerba() {
		return DescricaoVerba;
	}

	public void setDescricaoVerba(String descricaoVerba) {
		DescricaoVerba = descricaoVerba;
	}

}
