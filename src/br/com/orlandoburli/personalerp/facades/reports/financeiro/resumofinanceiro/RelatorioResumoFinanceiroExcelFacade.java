package br.com.orlandoburli.personalerp.facades.reports.financeiro.resumofinanceiro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class RelatorioResumoFinanceiroExcelFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private Date DataInicialVencimento;
	private Date DataFinalVencimento;

	private String FlgTodasLojas;
	private String FlgPesoDespesa;
	private String FlgContasOcultas;
	
	private String Lojas;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private UsuarioVO usuariosessao;
	
	public void execute() {
		try {
			Integer CodigoEmpresa = null;
			Integer CodigoLoja = null;
			Integer CodigoEmpresaGlobal = null;
			Integer CodigoLojaGlobal = null;
			
//			if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
//				CodigoEmpresa = getEmpresasessao().getCodigoEmpresa();
//				CodigoLoja = getLojasessao().getCodigoLoja();
//				
//				if (getFlgPesoDespesa() == null || getFlgPesoDespesa().equals("1")) {
//					CodigoEmpresaGlobal = getEmpresasessao().getCodigoEmpresa();
//					CodigoLojaGlobal = getLojasessao().getCodigoLoja();
//				}
//			}
			
			// Lojas Selecionadas
			List<LojaVO> listLojas = new ArrayList<LojaVO>();
			LojaDAO lojaDao = new LojaDAO();
			
			String strLojaSelecionada = "";
			for (String s : getLojas().split("\\;")) {
				Integer codigoEmpresa = Integer.parseInt(s.split("\\.")[0]);
				Integer codigoLoja = Integer.parseInt(s.split("\\.")[1]);

				//parameters.put("CodigoEmpresa", codigoEmpresa);
				//parameters.put("CodigoLoja", codigoLoja);
				
				CodigoEmpresa = codigoEmpresa;
				CodigoLoja = codigoLoja;
				
				if (getFlgPesoDespesa() == null || getFlgPesoDespesa().equals("1")) {
					CodigoEmpresaGlobal = getEmpresasessao().getCodigoEmpresa();
					CodigoLojaGlobal = getLojasessao().getCodigoLoja();
				} else {
					CodigoEmpresaGlobal = null;
					CodigoLojaGlobal = null;
				}
				
				LojaVO loja = new LojaVO();
				loja.setCodigoEmpresa(codigoEmpresa);
				loja.setCodigoLoja(codigoLoja);
				
				try {
					loja = lojaDao.get(loja);
					listLojas.add(loja);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if (!strLojaSelecionada.equals("")) {
					strLojaSelecionada += " UNION ALL ";
					// Se tem mais de uma, vai nulo
					CodigoEmpresa = null;
					CodigoLoja = null;
					CodigoEmpresaGlobal = null;
					CodigoLojaGlobal = null;
				}
				
				strLojaSelecionada += "SELECT " + codigoEmpresa + ", " + codigoLoja;
				
//				Lojas = strLojaSelecionada;

			}
			
			XSSFWorkbook wb = new XSSFWorkbook();
			CreationHelper helper = wb.getCreationHelper();
			
			XSSFSheet sheet = wb.createSheet();
			
			ResultSet result = null;
			try {
				
				result = new ContaPagarDAO().getListContasResumo(CodigoEmpresa, CodigoLoja, CodigoEmpresaGlobal, CodigoLojaGlobal, getDataInicialVencimento(), getDataFinalVencimento(), getFlgContasOcultas(), strLojaSelecionada);
				
				int linha = 0;
				XSSFRow rowCabecalho = sheet.createRow(linha);
				
				rowCabecalho.createCell(0);
				rowCabecalho.getCell(0).setCellValue("Numero Plano de Contas");
				rowCabecalho.createCell(1);
				rowCabecalho.getCell(1).setCellValue("Nome Plano de Contas");
				rowCabecalho.createCell(2);
				rowCabecalho.getCell(2).setCellValue("Crédito");
				rowCabecalho.createCell(3);
				rowCabecalho.getCell(3).setCellValue("% Total Crédito");
				rowCabecalho.createCell(4);
				rowCabecalho.getCell(4).setCellValue("Débito");
				rowCabecalho.createCell(5);
				rowCabecalho.getCell(5).setCellValue("% Total Débito");
				rowCabecalho.createCell(6);
				rowCabecalho.getCell(6).setCellValue("% Sobre Receita");

				while (result.next()) {
					linha++;
					XSSFRow rowDetalhe = sheet.createRow(linha);
					
					// Cria todas as celulas
					for (int i = 0; i < 7; i++) {
						rowDetalhe.createCell(i);
					}
					
					CellStyle styleNumero = wb.createCellStyle();
					styleNumero.setDataFormat(helper.createDataFormat().getFormat("0.00"));
					
					CellStyle stylePercentual = wb.createCellStyle();
					stylePercentual.setDataFormat(helper.createDataFormat().getFormat("0.00%"));
					
					rowDetalhe.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					rowDetalhe.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					
					rowDetalhe.getCell(0).setCellValue(result.getString("numeroplanoconta"));
					rowDetalhe.getCell(1).setCellValue(result.getString("nomeplanoconta"));
					
					rowDetalhe.getCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
					rowDetalhe.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
					rowDetalhe.getCell(4).setCellType(Cell.CELL_TYPE_NUMERIC);
					rowDetalhe.getCell(5).setCellType(Cell.CELL_TYPE_NUMERIC);
					rowDetalhe.getCell(6).setCellType(Cell.CELL_TYPE_NUMERIC);
					
					rowDetalhe.getCell(2).setCellStyle(styleNumero);
					rowDetalhe.getCell(4).setCellStyle(styleNumero);
					
					rowDetalhe.getCell(3).setCellStyle(stylePercentual);
					rowDetalhe.getCell(5).setCellStyle(stylePercentual);
					rowDetalhe.getCell(6).setCellStyle(stylePercentual);
					
					if (result.getBigDecimal("valorcredito") != null) {
						rowDetalhe.getCell(2).setCellValue(result.getBigDecimal("valorcredito").doubleValue());
					} else {
						rowDetalhe.getCell(2).setCellValue(0.0);
					}
					
					if (result.getBigDecimal("percentualcredito") != null) {
						rowDetalhe.getCell(3).setCellValue(result.getBigDecimal("percentualcredito").doubleValue());
					} else {
						rowDetalhe.getCell(3).setCellValue(0.0);
					}
					
					if (result.getBigDecimal("valordebito") != null) {
						rowDetalhe.getCell(4).setCellValue(result.getBigDecimal("valordebito").doubleValue());
					} else {
						rowDetalhe.getCell(4).setCellValue(0.0);
					}
					
					if (result.getBigDecimal("percentualdebito") != null) {
						rowDetalhe.getCell(5).setCellValue(result.getBigDecimal("percentualdebito").doubleValue());
					} else {
						rowDetalhe.getCell(5).setCellValue(0.0);
					}
					
					if (result.getBigDecimal("percentualdebitosobrereceita") != null) {
						rowDetalhe.getCell(6).setCellValue(result.getBigDecimal("percentualdebitosobrereceita").doubleValue());
					} else {
						rowDetalhe.getCell(6).setCellValue(0.0);
					}
				}
				
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			wb.write(baos);

			ServletOutputStream ouputStream = response.getOutputStream();
			response.setContentLength(baos.toByteArray().length);
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename=financeiro.xlsx;");
			
			ouputStream.write(baos.toByteArray());
			ouputStream.flush();
			ouputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
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

	public String getFlgPesoDespesa() {
		return FlgPesoDespesa;
	}

	public void setFlgPesoDespesa(String flgPesoDespesa) {
		FlgPesoDespesa = flgPesoDespesa;
	}

	public String getFlgContasOcultas() {
		return FlgContasOcultas;
	}

	public void setFlgContasOcultas(String flgContasOcultas) {
		FlgContasOcultas = flgContasOcultas;
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

	public String getLojas() {
		return Lojas;
	}

	public void setLojas(String lojas) {
		Lojas = lojas;
	}
}
