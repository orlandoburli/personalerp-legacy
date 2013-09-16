package br.com.orlandoburli.core.web.framework.facades;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.TipoSaidaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;

public abstract class BaseRelatorioFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private GrupoEmpresaVO grupoempresasessao;
	
	private UsuarioVO usuariosessao;

	private String tipoSaida;
	
	protected abstract String getJasperFileName();
	
	public BaseRelatorioFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	public BaseRelatorioFacade() {
		super();
	}
	
	public void execute() {
		view();
	}

	public void view() {
		try {
			JRAbstractExporter saida = null; 
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	
			//JasperReport relatorio = (JasperReport) JRLoader.loadObject(context.getRealPath(getJasperFileName()));
			JasperReport relatorio = (JasperReport) JRLoader.loadObject(new FileInputStream(context.getRealPath(getJasperFileName())));
	
			JasperPrint impressao = new JasperPrint();
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			doParameter(parameters);
			JRDataSource ds = getDataSource();
			if (ds == null) {
				impressao = JasperFillManager.fillReport(relatorio, parameters, new GenericDAO().getConnection());
			} else {
				impressao = JasperFillManager.fillReport(relatorio, parameters, ds);
			}
			
			if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_HTML)) {
				saida = new JRHtmlExporter();
				saida.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
				saida.setParameter(JRHtmlExporterParameter.IMAGES_URI, "temp/");
				saida.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, SystemManager.INITIAL_APP_DIRECTORY + "temp/"); 
				saida.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, true);
				
			} else if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_PDF)) {
				saida = new JRPdfExporter();
			} else if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_RTF)) {
				saida = new JRRtfExporter();
			} else if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_XLS)) {
				saida = new JRXlsExporter();
			} else if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_TXT)) {
				saida = new JRTextExporter();
			} else {
				saida = new JRPdfExporter(); // O padrao e PDF
			}
			
			//saida.setParameter(JRExporterParameter.
			saida.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");                
			saida.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
			saida.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			
			if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_TXT)) {
				saida.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(200));
				saida.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(50));
			}
			
			saida.exportReport();
	
			byte[] bytes = baos.toByteArray(); 
			response.setContentLength(bytes.length);
//			if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_PDF)) {
//				response.setContentType("application/pdf");
//			} else if (getTipoSaida().equalsIgnoreCase(Constants.Saida.SAIDA_XLS)) {
//				response.setContentType("application/vnd.ms-excel");
//			}
			
			response.setContentType(TipoSaidaVO.getTipoSaidaById(getTipoSaida()).getContentType());
			
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Para fornecer uma fonte de dados diferente (um arraylist, por exemplo)
	 * sobrescrever este método. 
	 * @return DataSource populado
	 */
	public JRDataSource getDataSource() {
		return null;
	}

	public void setTipoSaida(String tipoSaida) {
		this.tipoSaida = tipoSaida;
	}

	public String getTipoSaida() {
		return tipoSaida == null?"pdf":tipoSaida;
	}
	
	public List<TipoSaidaVO> getListSaida() {
		return TipoSaidaVO.getListSaida();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doParameter(Map parameters) {
		// Se precisar, sobrescrever este metodo, mantendo o original...
		parameters.put("SUBREPORT_DIR", SystemManager.INITIAL_APP_DIRECTORY + "reports/");
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		
		// Buscar a logo dos parametros
		ParametroLojaDAO paramDao = new ParametroLojaDAO();

		try {
			String logo = paramDao.getStringParametro(Constants.Parameters.Geral.LOGO_RELATORIO, getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja());
			parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + logo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public GrupoEmpresaVO getGrupoempresasessao() {
		return grupoempresasessao;
	}

	public void setGrupoempresasessao(GrupoEmpresaVO grupoempresasessao) {
		this.grupoempresasessao = grupoempresasessao;
	}
}
