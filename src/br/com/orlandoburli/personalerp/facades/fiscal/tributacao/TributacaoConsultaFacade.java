package br.com.orlandoburli.personalerp.facades.fiscal.tributacao;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.fiscal.TributacaoDAO;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.fiscal.TipoTributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.TributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.UFTipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TributacaoConsultaFacade extends BaseConsultaFlexFacade<TributacaoVO, TributacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void execute() {
		try {
			setListSource(dao.getList());
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<?xml version='1.0' encoding='utf-8'?>");
			sb.append("<list>");
			
			List<IValueObject> listTipoTributacao = _dao.getList(new TipoTributacaoVO());
			for (IValueObject item : listTipoTributacao) {
				TipoTributacaoVO tipo = (TipoTributacaoVO) item;
				sb.append("<tributacao CodigoTipoTributacao=\"" + tipo.getCodigoTipoTributacao() + "\"" +
							" IdentificacaoTipoTributacao=\"" + tipo.getIdentificacaoTipoTributacao() + "\" " +
							" descricao=\"" + tipo.getIdentificacaoTipoTributacao() + "\" >");
				
				// Itens (UF's de Origem)
				UFTipoTributacaoVO _filter = new UFTipoTributacaoVO();
				_filter.setCodigoTipoTributacao(tipo.getCodigoTipoTributacao());
				
				List<IValueObject> listUFOrigem = _dao.getList(_filter);
				
				for (IValueObject ufItem : listUFOrigem) {
					UFTipoTributacaoVO uf = (UFTipoTributacaoVO) ufItem;
					sb.append("<tributacao CodigoTipoTributacao=\"" + uf.getCodigoTipoTributacao() + "\"" +
							" IdentificacaoTipoTributacao=\"" + tipo.getIdentificacaoTipoTributacao() + "\" " +
							" UFOrigemTributacao=\"" + uf.getUFOrigemTributacao() +  "\"" +
							" descricao=\"Origem: " + uf.getNomeUFTipoTributacao() + "\" " +
							">");
					
					for (TributacaoVO tributacao : getListSource()) {
						if (tributacao.getCodigoTipoTributacao().equals(uf.getCodigoTipoTributacao()) && tributacao.getUFOrigemTributacao().equals(uf.getUFOrigemTributacao())) {
							String descricao = "Destino: " + tributacao.getNomeUFDestinoTributacao();
							descricao += " / Operação: " + tributacao.getDescricaoOperacaoTributacao();
							if (tributacao.getComplementoTributacao().equals("SN")) {
								descricao += " - Simples Nacional";
							}
							
							sb.append("<tributacao CodigoTipoTributacao=\"" + tributacao.getCodigoTipoTributacao() + "\"" +
									" IdentificacaoTipoTributacao=\"" + tipo.getIdentificacaoTipoTributacao() + "\" " +
									" UFOrigemTributacao=\"" + tributacao.getUFOrigemTributacao() +  "\"" +
									" UFDestinoTributacao=\"" + tributacao.getUFDestinoTributacao() + "\"" +
									" ComplementoTributacao=\"" + tributacao.getComplementoTributacao() + "\"" +
									" OperacaoTributacao=\"" + tributacao.getOperacaoTributacao() + "\" " +
									" descricao=\"" + descricao + "\" />");
						}
					}
					
					sb.append("</tributacao>");
				}
				
				sb.append("</tributacao>");
			}
			
			sb.append("</list>");
			
			write(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doBeforeFilter() {
		
	}
}