package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomadeirabeneficiada;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.madeireira.GerenteMadeireiraDAO;
import br.com.orlandoburli.core.dao.cadastro.madeireira.MotoristaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.MovimentacaoEstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.madeirabeneficiada.ItemRomaneioBeneficiadaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.madeirabeneficiada.ItemRomaneioBeneficiadaSerradaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.madeirabeneficiada.RomaneioMadeiraBeneficiadaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.MotoristaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada.ItemRomaneioBeneficiadaSerradaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada.ItemRomaneioBeneficiadaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada.RomaneioMadeiraBeneficiadaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class RomaneioMadeiraBeneficiadaCadastroFacade extends BaseCadastroFlexFacade<RomaneioMadeiraBeneficiadaVO, RomaneioMadeiraBeneficiadaDAO> {

	private static final long serialVersionUID = 1L;

	public RomaneioMadeiraBeneficiadaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataRomaneio", "Data do Romaneio"));
		this.setWriteVoOnInsert(true);
		this.setWriteVoOnUpdate(true);
	}
	
	@IgnoreMethodAuthentication
	public void gerentes() {
		GerenteMadeireiraDAO _dao = new GerenteMadeireiraDAO();
		
		try {
			List<GerenteMadeireiraVO> list = _dao.getList(new GerenteMadeireiraVO(), "NomeGerente");
			int count = _dao.getListCount(new GerenteMadeireiraVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoas() {
		EnderecoPessoaDAO _dao = new EnderecoPessoaDAO();
		
		try {
			List<EnderecoPessoaVO> list = _dao.getList(new EnderecoPessoaVO());
			int count = _dao.getListCount(new EnderecoPessoaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void motoristas() {
		MotoristaDAO _dao = new MotoristaDAO();
		
		try {
			List<MotoristaVO> list = _dao.getList(new MotoristaVO());
			int count = _dao.getListCount(new MotoristaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getTipoRomaneio().equalsIgnoreCase(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA)) {
			if (getVo().getCodigoGerente() == null || getVo().getCodigoEmpresaGerente() == null || getVo().getCodigoLojaGerente() == null) {
				this.messages.add(new MessageVO("Para romaneios de entrada, informe o gerente!", "Gerente"));
				return false;
			}
		}
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
		this.getVo().setStatusRomaneio(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_NAOPROCESSADO);
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeUpdate() throws SQLException {
		try {
			RomaneioMadeiraBeneficiadaVO _vo = dao.get(getVo());
			if (_vo.getStatusRomaneio().equals(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_PROCESSADO)) {
				this.messages.add(new MessageVO("Esse romaneio já foi processado e não pode ser alterado!"));
				return false;
//			} else if (_vo.getStatusRomaneio().equals(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_ESTORNADO)) {
//				this.messages.add(new MessageVO("Esse romaneio foi estornado e não pode ser alterado!"));
//				return false;
			}
			this.getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
			this.getVo().setStatusRomaneio(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_NAOPROCESSADO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeUpdate();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		RomaneioMadeiraBeneficiadaVO _vo = new RomaneioMadeiraBeneficiadaVO();
		
		_vo.setCodigoRomaneio(getVo().getCodigoRomaneio());
		_vo.setCodigoEmpresa(getVo().getCodigoEmpresa());
		_vo.setCodigoLoja(getVo().getCodigoLoja());
		
		try {
			_vo = dao.get(_vo);
			if (_vo.getStatusRomaneio().equals(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_PROCESSADO)) {
				this.messages.add(new MessageVO("Esse romaneio já foi processado e não pode ser alterado!"));
				return false;
			} else if (_vo.getStatusRomaneio().equals(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_ESTORNADO)) {
				this.messages.add(new MessageVO("Esse romaneio foi estornado e não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		// Exclui os itens (serradas)
		ItemRomaneioBeneficiadaSerradaVO itemvoSerradaFilter = new ItemRomaneioBeneficiadaSerradaVO();
		itemvoSerradaFilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
		itemvoSerradaFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemvoSerradaFilter.setCodigoLoja(getVo().getCodigoLoja());
		
		ItemRomaneioBeneficiadaSerradaDAO itemdaoSerrada = new ItemRomaneioBeneficiadaSerradaDAO();
		
		try {
			List<ItemRomaneioBeneficiadaSerradaVO> list = itemdaoSerrada.getList(itemvoSerradaFilter);
			for (ItemRomaneioBeneficiadaSerradaVO item : list) {
				itemdaoSerrada.remove(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Exclui os itens (beneficiadas)
		ItemRomaneioBeneficiadaVO itemvofilter = new ItemRomaneioBeneficiadaVO();
		itemvofilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
		itemvofilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemvofilter.setCodigoLoja(getVo().getCodigoLoja());
		
		ItemRomaneioBeneficiadaDAO itemdao = new ItemRomaneioBeneficiadaDAO();
		
		try {
			List<ItemRomaneioBeneficiadaVO> list = itemdao.getList(itemvofilter);
			for (ItemRomaneioBeneficiadaVO item : list) {
				itemdao.remove(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}

	/**
	 * Processamento de romaneio. Efetua alteração no estoque.
	 */
	public void processar() {
		try {
			
			setVo(dao.get(getVo()));
			
			if (getVo().getStatusRomaneio().equalsIgnoreCase(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_PROCESSADO)) {
				writeErrorMessage("Este romaneio já foi processado!");
				return;
			}
			/************* PROCESSAMENTO DOS ITENS - MADEIRA SERRADA *************/
			ItemRomaneioBeneficiadaSerradaVO filterSerrada = new ItemRomaneioBeneficiadaSerradaVO();
			filterSerrada.setCodigoEmpresa(getVo().getCodigoEmpresa());
			filterSerrada.setCodigoLoja(getVo().getCodigoLoja());
			filterSerrada.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioBeneficiadaSerradaDAO daoItemSerrada = new ItemRomaneioBeneficiadaSerradaDAO();
 			List<ItemRomaneioBeneficiadaSerradaVO> listSerradas = daoItemSerrada.getList(filterSerrada);
 			
			if (!getVo().getTipoRomaneio().equalsIgnoreCase(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA) && listSerradas.size() > 0) {
				writeErrorMessage("Esse romaneio não é do tipo ENTRADA,\n mas possui madeiras serradas lançadas no romaneio!\nCorrija antes de prosseguir.");
				return;
			}
			
			EstoqueDAO estDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO daoMov = new MovimentacaoEstoqueDAO();
			
			for (ItemRomaneioBeneficiadaSerradaVO item : listSerradas) {
				
				EstoqueVO estoque = estDao.get(new Object[]{item.getCodigoEmpresaProduto(), item.getCodigoLojaProduto(), item.getCodigoProduto(), getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()});
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
				mov.setNewRecord(true);
				mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				mov.setCodigoLoja(item.getCodigoLojaProduto());
				mov.setCodigoProduto(item.getCodigoProduto());
				mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
				mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
				
				if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Romaneio de entrada de madeiras beneficiadas de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				}
				
				mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
				mov.setOperacaoMovimentacaoEstoque(getVo().getTipoRomaneio());
				
				estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				
				estDao.persist(estoque);
				daoMov.persist(mov);
				
				item.setStatusItemRomaneio(ItemRomaneioBeneficiadaVO.STATUS_PROCESSADO);
				
				item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
				item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
				item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
				
				daoItemSerrada.persist(item);
			}
			
			/************* FIM DO PROCESSAMENTO DOS ITENS - MADEIRA SERRADA **********/
			
			
			/************* PROCESSAMENTO DOS ITENS - MADEIRA BENEFICIADA *************/
			ItemRomaneioBeneficiadaVO filter = new ItemRomaneioBeneficiadaVO();
			
			filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			filter.setCodigoLoja(getVo().getCodigoLoja());
			filter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioBeneficiadaDAO daoItem = new ItemRomaneioBeneficiadaDAO();
			
			List<ItemRomaneioBeneficiadaVO> list = daoItem.getList(filter);
			
			for (ItemRomaneioBeneficiadaVO item : list) {
				
				EstoqueVO estoque = estDao.get(new Object[]{item.getCodigoEmpresaProduto(), item.getCodigoLojaProduto(), item.getCodigoProduto(), getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()});
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
				mov.setNewRecord(true);
				mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				mov.setCodigoLoja(item.getCodigoLojaProduto());
				mov.setCodigoProduto(item.getCodigoProduto());
				mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
				mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
				
				if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Romaneio de entrada de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				} else if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_SAIDA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Romaneio de saida de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				} else if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_INVENTARIO)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(new BigDecimal(item.getQuantidadeItemRomaneio()));
					estoque.setEstoqueFiscal(new BigDecimal(item.getQuantidadeItemRomaneio()));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Romaneio de inventario de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				}
				
				mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
				mov.setOperacaoMovimentacaoEstoque(getVo().getTipoRomaneio());
				
				estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				
				estDao.persist(estoque);
				daoMov.persist(mov);
				
				item.setStatusItemRomaneio(ItemRomaneioBeneficiadaVO.STATUS_PROCESSADO);
				
				item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
				item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
				item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
				
				daoItem.persist(item);
			}
			
			/************* FIM DO PROCESSAMENTO DOS ITENS - MADEIRA BENEFICIADA **********/
			
			getVo().setStatusRomaneio(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_PROCESSADO);
			getVo().setDataHoraProcessamentoRomaneio(Utils.getNow());
			
			getVo().setCodigoEmpresaUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoEmpresa());
			getVo().setCodigoLojaUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoLoja());
			getVo().setCodigoUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoUsuario());
			
			dao.persist(getVo());
			
			write("ok");
		} catch (Exception e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Estorno de processamento de romaneio. Efetua alteração no estoque.
	 */
	public void estornar() {
		try {
			
			setVo(dao.get(getVo()));
			
			if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_INVENTARIO)) {
				writeErrorMessage("Não é possível estornar um romaneio de inventário! Faça outro romaneio para corrigir.");
				return;
			}
			
			if (!getVo().getStatusRomaneio().equalsIgnoreCase(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_PROCESSADO)) {
				writeErrorMessage("Este romaneio não foi processado!");
				return;
			}
			
			/************* PROCESSAMENTO DOS ITENS - MADEIRA SERRADA *************/
			ItemRomaneioBeneficiadaSerradaVO filterSerrada = new ItemRomaneioBeneficiadaSerradaVO();
			filterSerrada.setCodigoEmpresa(getVo().getCodigoEmpresa());
			filterSerrada.setCodigoLoja(getVo().getCodigoLoja());
			filterSerrada.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioBeneficiadaSerradaDAO daoItemSerrada = new ItemRomaneioBeneficiadaSerradaDAO();
 			List<ItemRomaneioBeneficiadaSerradaVO> listSerradas = daoItemSerrada.getList(filterSerrada);
 			
			if (!getVo().getTipoRomaneio().equalsIgnoreCase(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA) && listSerradas.size() > 0) {
				writeErrorMessage("Esse romaneio não é do tipo ENTRADA, mas possui madeiras serradas lançadas no romaneio! Corrija antes de prosseguir.");
				return;
			}
			
			EstoqueDAO estDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO daoMov = new MovimentacaoEstoqueDAO();
			
			for (ItemRomaneioBeneficiadaSerradaVO item : listSerradas) {
				
				EstoqueVO estoque = estDao.get(new Object[]{item.getCodigoEmpresaProduto(), item.getCodigoLojaProduto(), item.getCodigoProduto(), getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()});
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
				mov.setNewRecord(true);
				mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				mov.setCodigoLoja(item.getCodigoLojaProduto());
				mov.setCodigoProduto(item.getCodigoProduto());
				mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
				mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
				
				if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Estorno de Romaneio de entrada de madeiras beneficiadas de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				}
				
				mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
				mov.setOperacaoMovimentacaoEstoque(getVo().getTipoRomaneio());
				
				estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				
				estDao.persist(estoque);
				daoMov.persist(mov);
				
				item.setStatusItemRomaneio(ItemRomaneioBeneficiadaVO.STATUS_PROCESSADO);
				
				item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
				item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
				item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
				
				daoItemSerrada.persist(item);
			}
			
			/************* FIM DO PROCESSAMENTO DOS ITENS - MADEIRA SERRADA **********/
			
			/************* PROCESSAMENTO DOS ITENS - MADEIRA BENEFICIADA *************/
			
			ItemRomaneioBeneficiadaVO filter = new ItemRomaneioBeneficiadaVO();
			
			filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			filter.setCodigoLoja(getVo().getCodigoLoja());
			filter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			ItemRomaneioBeneficiadaDAO daoItem = new ItemRomaneioBeneficiadaDAO();
			
			List<ItemRomaneioBeneficiadaVO> list = daoItem.getList(filter);
			
			for (ItemRomaneioBeneficiadaVO item : list) {
				
				EstoqueVO estoque = estDao.get(new Object[]{item.getCodigoEmpresaProduto(), item.getCodigoLojaProduto(), item.getCodigoProduto(), getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()});
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
				mov.setNewRecord(true);
				mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				mov.setCodigoLoja(item.getCodigoLojaProduto());
				mov.setCodigoProduto(item.getCodigoProduto());
				mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
				mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
				
				if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					mov.setOperacaoMovimentacaoEstoque(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_SAIDA);
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Estorno do Romaneio de entrada de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				} else if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_SAIDA)) {
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					mov.setOperacaoMovimentacaoEstoque(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_ENTRADA);
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(new BigDecimal(item.getQuantidadeItemRomaneio())));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Estorno do Romaneio de saida de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				} else if (getVo().getTipoRomaneio().equals(RomaneioMadeiraBeneficiadaVO.TIPOROMANEIO_INVENTARIO)) {
					// TODO Estorno de Inventario
					// NAO SERA FEITO - PELO MENOS NAO AGORA...
//					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
//					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
//					
//					estoque.setEstoqueFisico(new BigDecimal(item.getQuantidadeItemRomaneio()));
//					estoque.setEstoqueFiscal(new BigDecimal(item.getQuantidadeItemRomaneio()));
//					
//					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
//					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
//					
//					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Estorno do Romaneio de inventario de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
				}
				
				mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
				
				estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				
				estDao.persist(estoque);
				daoMov.persist(mov);
				
				item.setStatusItemRomaneio(ItemRomaneioBeneficiadaVO.STATUS_ESTORNADO);
				
				item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
				item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
				item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
				
				daoItem.persist(item);
			}
			
			/************* FIM DO PROCESSAMENTO DOS ITENS - MADEIRA BENEFICIADA **********/
			
			getVo().setStatusRomaneio(RomaneioMadeiraBeneficiadaVO.STATUS_ROMANEIO_ESTORNADO);
			getVo().setDataHoraProcessamentoRomaneio(Utils.getNow());
			
			getVo().setCodigoEmpresaUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoEmpresa());
			getVo().setCodigoLojaUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoLoja());
			getVo().setCodigoUsuarioProcessamentoRomaneio(getUsuariosessao().getCodigoUsuario());
			
			dao.persist(getVo());
			
			write("ok");
		} catch (Exception e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return RomaneioMadeiraBeneficiadaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioMadeiraBeneficiadaVO.class;
	}
}