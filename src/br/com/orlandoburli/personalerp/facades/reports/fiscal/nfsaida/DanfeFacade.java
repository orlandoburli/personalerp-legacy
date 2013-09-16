package br.com.orlandoburli.personalerp.facades.reports.fiscal.nfsaida;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.fiscal.nfe.LogNFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.TipoRegistroNFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class DanfeFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoNFSaida;
	private String SerieNFSaida;
	
	@Override
	protected String getJasperFileName() {
		return "reports/danfe.jasper";
	}
	
	protected JRDataSource getDataSource2() {
		try {
			GenericDAO dao = new GenericDAO();
			
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getCodigoEmpresa());
			nfsaida.setCodigoLoja(getCodigoLoja());
			nfsaida.setCodigoNFSaida(getCodigoNFSaida());
			nfsaida.setSerieNFSaida(getSerieNFSaida());
			
			nfsaida = (NFSaidaVO) dao.get(nfsaida);
			
			if (nfsaida == null || nfsaida.getCodigoNfe() == null) {
				return null;
			}
			
			LogNFEletronicaVO filterLogNfe = new LogNFEletronicaVO();
			filterLogNfe.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
			filterLogNfe.setCodigoLoja(nfsaida.getCodigoLoja());
			filterLogNfe.setCodigoNFe(nfsaida.getCodigoNfe());
			filterLogNfe.setCodigoTipoRegistroNFe(TipoRegistroNFEletronicaVO.TIPO_XML_NFE_OBJETO);
			
			LogNFEletronicaVO logNfe = null;
			NFe nfe = null;
			
			for (IValueObject i : dao.getList(filterLogNfe)) {
				logNfe = (LogNFEletronicaVO) i;
				String xmlObjeto = logNfe.getDadosLogNFe();
				XStream stream = new XStream();
				nfe = (NFe) stream.fromXML(xmlObjeto);
			}
			
			ArrayList<NFe> list = new ArrayList<NFe>();
			list.add(nfe);
			
			JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(list);
			
			return datasource;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("LOGO_CLIENTE", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_cliente.jpg");
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoNFSaida", getCodigoNFSaida());
		parameters.put("SerieNFSaida", getSerieNFSaida());
		super.doParameter(parameters);
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoNFSaida(Integer codigoNFSaida) {
		CodigoNFSaida = codigoNFSaida;
	}

	public Integer getCodigoNFSaida() {
		return CodigoNFSaida;
	}

	public void setSerieNFSaida(String serieNFSaida) {
		SerieNFSaida = serieNFSaida;
	}

	public String getSerieNFSaida() {
		return SerieNFSaida;
	}
}
