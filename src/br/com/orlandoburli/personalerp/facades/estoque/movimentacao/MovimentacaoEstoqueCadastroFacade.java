package br.com.orlandoburli.personalerp.facades.estoque.movimentacao;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.MovimentacaoEstoqueDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class MovimentacaoEstoqueCadastroFacade extends BaseCadastroFlexFacade<MovimentacaoEstoqueVO, MovimentacaoEstoqueDAO> {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal QuantidadeDisplay;
	private BigDecimal QuantidadeGaveta;
	
	public MovimentacaoEstoqueCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	public void alterar() {
		
		if (getQuantidadeDisplay() == null) {
			setQuantidadeDisplay(BigDecimal.ZERO);
		}
		if (getQuantidadeGaveta() == null) {
			setQuantidadeGaveta(BigDecimal.ZERO);
		}
		
//		vo.setQuantidadeFisicoMovimentacaoEstoque(getQuantidadeDisplay().add(getQuantidadeGaveta()));
//		vo.setQuantidadeFiscalMovimentacaoEstoque(getQuantidadeDisplay().add(getQuantidadeGaveta()));
		
		if (getVo().getCodigoEmpresaEstoque() == null || getVo().getCodigoEmpresaEstoque() <= 0) {
			getVo().setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
		}
		
		if (getVo().getCodigoLojaEstoque() == null || getVo().getCodigoLojaEstoque() <= 0) {
			getVo().setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
		}
		
		getVo().setDataHoraMovimentacaoEstoque(Utils.getNow());
		
//		if (vo.getQuantidadeFiscalMovimentacaoEstoque().compareTo(BigDecimal.ZERO) <= 0 && !vo.getOperacaoMovimentacaoEstoque().equals("I")) {
//			writeErrorMessage("Informe uma quantidade maior que zero!", "Quantidade");
//			return;
//		}
		if (getVo().getObservacaoMovimentacaoEstoque().trim().equals("")) {
			writeErrorMessage("Coloque uma observação", "Observacao");
			return;
		}
		EstoqueDAO estoqueDao = new EstoqueDAO();
		try {
			EstoqueVO estoque = estoqueDao.get(new Object[] {getVo().getCodigoEmpresa(), getVo().getCodigoLoja(), getVo().getCodigoProduto(), getVo().getCodigoEmpresaEstoque(), getVo().getCodigoLojaEstoque()});
			
			if (estoque == null) {
				estoque = new EstoqueVO();
				estoque.setCodigoEmpresa(getVo().getCodigoEmpresa());
				estoque.setCodigoLoja(getVo().getCodigoLoja());
				estoque.setCodigoProduto(getVo().getCodigoProduto());
				estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaEstoque());
				estoque.setCodigoLojaEstoque(getVo().getCodigoLojaEstoque());
				estoque.setDataUltimaEntradaEstoque(Utils.getToday());
				estoque.setEstoqueFiscal(BigDecimal.ZERO);
				estoque.setEstoqueFisico(BigDecimal.ZERO);
				estoque.setNewRecord(true);
			}
			
			BigDecimal totalMov = getQuantidadeDisplay().add(getQuantidadeGaveta());
			
			if (getVo().getOperacaoMovimentacaoEstoque().equalsIgnoreCase(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR)) {
				
				getVo().setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(totalMov));
				estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(totalMov));
				
				getVo().setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setQuantidadeDisplayEstoque(estoque.getQuantidadeDisplayEstoque().add(getQuantidadeDisplay()));
				estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().add(getQuantidadeGaveta()));
				
			} else if (getVo().getOperacaoMovimentacaoEstoque().equalsIgnoreCase(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR)) {
				getVo().setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(totalMov));
				estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(totalMov));
				
				getVo().setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setQuantidadeDisplayEstoque(estoque.getQuantidadeDisplayEstoque().subtract(getQuantidadeDisplay()));
				estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().subtract(getQuantidadeGaveta()));
				
			} else if (getVo().getOperacaoMovimentacaoEstoque().equalsIgnoreCase(MovimentacaoEstoqueVO.OPERACAO_INVENTARIO)) {
				getVo().setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setEstoqueFisico(totalMov);
				estoque.setEstoqueFiscal(totalMov);
				
				getVo().setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
				getVo().setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				estoque.setQuantidadeDisplayEstoque(getQuantidadeDisplay());
				estoque.setQuantidadeGavetaEstoque(getQuantidadeGaveta());
			}
			
			estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
			estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
			estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
			
			estoqueDao.persist(estoque);
			
			inserir();
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public void excluir() {
		// Não se exclui...
		throw new NotImplementedException();
	}

	@Override
	public Class<?> getDAOClass() {
		return MovimentacaoEstoqueDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return MovimentacaoEstoqueVO.class;
	}

	public void setQuantidadeDisplay(BigDecimal quantidadeDisplay) {
		QuantidadeDisplay = quantidadeDisplay;
	}

	public BigDecimal getQuantidadeDisplay() {
		return QuantidadeDisplay;
	}

	public void setQuantidadeGaveta(BigDecimal quantidadeGaveta) {
		QuantidadeGaveta = quantidadeGaveta;
	}

	public BigDecimal getQuantidadeGaveta() {
		return QuantidadeGaveta;
	}
}