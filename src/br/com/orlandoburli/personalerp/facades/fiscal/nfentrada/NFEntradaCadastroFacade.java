package br.com.orlandoburli.personalerp.facades.fiscal.nfentrada;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

import br.com.orlandoburli.core.dao.fiscal.nfentrada.NFEntradaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.extras.nfe.utils.NfeImport;
import br.com.orlandoburli.core.extras.nfe.utils.NfeUtils;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.EntradaEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.ItemEntradaEstoqueVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.contapagar.NFEntradaContaPagarVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.estoque.NFEntradaEntradaEstoqueVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.facades.fiscal.nfsaida.NFSaidaCadastroFacade;

@SuppressWarnings("deprecation")
public class NFEntradaCadastroFacade extends BaseCadastroFlexFacade<NFEntradaVO, NFEntradaDAO>{

	private static final long serialVersionUID = 1L;
	
	private String FlagImportarContas;
	private String FlagImportarEstoque;
	private String NomeArquivoXml;
	
	public NFEntradaCadastroFacade() {
		super();
	}
	
	public void devolucao() {
		try {
			setVo(this.dao.get(getVo()));
			
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
			nfsaida.setCodigoLoja(getVo().getCodigoLoja());
			nfsaida.setCodigoNFSaida(NFSaidaCadastroFacade.getNextNFSaida(getVo().getCodigoEmpresa(), getVo().getCodigoLoja()));
			nfsaida.setSerieNFSaida("1");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void operacao() {
		try {
			OperacaoTributacaoVO _filter = new OperacaoTributacaoVO();
			write(Utils.voToXml(getGenericDao().getList(_filter), getGenericDao().getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoas() {
		try {
			EnderecoPessoaVO _filter = new EnderecoPessoaVO();
			write(Utils.voToXml(getGenericDao().getList(_filter), getGenericDao().getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void uploadxml() {
		try {
			System.out.println("---------------------");
			System.out.println("INICIANDO UPLOAD");
			
			String pathUpload = System.getProperty("java.io.tmpdir") + File.separator;
	
			if (FileUpload.isMultipartContent(request)) {
				
				File dir = new File(pathUpload);
				
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				DiskFileUpload upload = new DiskFileUpload();
				upload.setRepositoryPath(pathUpload);
				
				try {
					
					List<?> items = upload.parseRequest(request);
					Iterator<?> it = items.iterator();
					
					while (it.hasNext()) {
						
						FileItem fitem = (FileItem) it.next();
						
						if (!fitem.isFormField()) {
							
							String finalPathUpload = dir.getAbsolutePath() + File.separator;
							
							File file = new File(finalPathUpload + getNomeArquivoXml());
							System.out.println(file.getAbsolutePath());
							
							FileOutputStream fout = new FileOutputStream(file);
							fout.write(fitem.get());
							fout.close();
							
							System.out.println("ok");
						}
						
						fitem.delete();
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
			} else {
				response.getWriter().print("e agora???");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIM UPLOAD");
		System.out.println("---------------------");

	}
	
	public void importarxml() {
		System.out.println("Iniciando importacao xml...");
		
		String xmlFile = System.getProperty("java.io.tmpdir") + File.separator + getNomeArquivoXml();
		
		System.out.println(xmlFile);
		
		String xmlData = NfeUtils.getXmlContent(xmlFile);
		
		try {
			
			NfeImport.importNfe(xmlData, getUsuariosessao(), getFlagImportarContas().equals("S"), getFlagImportarEstoque().equals("S"), this.request, this.response, this.context);
			
			writeErrorMessage("XML Importado com sucesso!");
		} catch (NfeException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
		
		System.out.println("Finalizando importacao xml");
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		if (getVo().getCodigoEmpresa() == null || getVo().getCodigoLoja() == null || getVo().getCodigoNFEntrada() == null || getVo().getCodigoEmpresaEmitenteNFEntrada() == null || getVo().getCodigoLojaEmitenteNFEntrada() == null || getVo().getCodigoPessoaEmitenteNFEntrada() == null || getVo().getCodigoEnderecoPessoaEmitenteNFEntrada() == null) {
			return false;
		}
		
		// Valida se pode excluir as contas a pagar
		NFEntradaContaPagarVO filterConta = new NFEntradaContaPagarVO();
		filterConta.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filterConta.setCodigoLoja(getVo().getCodigoLoja());
		filterConta.setCodigoNFEntrada(getVo().getCodigoNFEntrada());
		
		filterConta.setCodigoEmpresaEmitenteNFEntrada(getVo().getCodigoEmpresaEmitenteNFEntrada());
		filterConta.setCodigoLojaEmitenteNFEntrada(getVo().getCodigoLojaEmitenteNFEntrada());
		filterConta.setCodigoPessoaEmitenteNFEntrada(getVo().getCodigoPessoaEmitenteNFEntrada());
		filterConta.setCodigoEnderecoPessoaEmitenteNFEntrada(getVo().getCodigoEnderecoPessoaEmitenteNFEntrada());
		
		List<IValueObject> listNFConta = getGenericDao().getList(filterConta);
		List<IValueObject> listContaPagar = new ArrayList<IValueObject>();
		
		for (IValueObject i : listNFConta) {
			NFEntradaContaPagarVO item = (NFEntradaContaPagarVO) i;
			ContaPagarVO conta = new ContaPagarVO();
			conta.setCodigoEmpresa(item.getCodigoEmpresaContaPagar());
			conta.setCodigoLoja(item.getCodigoLojaContaPagar());
			conta.setCodigoContaPagar(item.getCodigoContaPagar());
		
			conta = (ContaPagarVO) getGenericDao().get(conta);
			
			listContaPagar.add(conta);
			
			if (conta.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_ABERTO) || conta.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_CANCELADO) || conta.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_PREVISTO)) {
				// Verifica se nao tem baixa parcial
				BaixaContaPagarVO baixaFilter = new BaixaContaPagarVO();
				baixaFilter.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
				baixaFilter.setCodigoLojaContaPagar(conta.getCodigoLoja());
				baixaFilter.setCodigoContaPagar(conta.getCodigoContaPagar());
				
				if (getGenericDao().getListCount(baixaFilter) > 0) {
					this.messages.add(new MessageVO("Nota não pode ser excluída, um dos títulos vinculados está com BAIXA PARCIAL (Conta número " + conta.getNumeroTituloContaPagar() + ", parcela " + conta.getParcelaContaPagar() + ")."));
					return false;
				}
			} else if (conta.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_QUITADO)) {
				this.messages.add(new MessageVO("Nota não pode ser excluída, um dos títulos vinculados está QUITADO (Conta número " + conta.getNumeroTituloContaPagar() + ", parcela " + conta.getParcelaContaPagar() + ")."));
				return false;
			}
		}
		
		// Verifica se existem Entradas de mercadorias para excluir
		NFEntradaEntradaEstoqueVO filterEntrada = new NFEntradaEntradaEstoqueVO();
		filterEntrada.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filterEntrada.setCodigoLoja(getVo().getCodigoLoja());
		filterEntrada.setCodigoNFEntrada(getVo().getCodigoNFEntrada());
		
		filterEntrada.setCodigoEmpresaEmitenteNFEntrada(getVo().getCodigoEmpresaEmitenteNFEntrada());
		filterEntrada.setCodigoLojaEmitenteNFEntrada(getVo().getCodigoLojaEmitenteNFEntrada());
		filterEntrada.setCodigoPessoaEmitenteNFEntrada(getVo().getCodigoPessoaEmitenteNFEntrada());
		filterEntrada.setCodigoEnderecoPessoaEmitenteNFEntrada(getVo().getCodigoEnderecoPessoaEmitenteNFEntrada());
		
		List<IValueObject> listNFEntrada = getGenericDao().getList(filterEntrada);
		List<IValueObject> listEntrada = new ArrayList<IValueObject>();
		List<IValueObject> listItemEntrada = new ArrayList<IValueObject>();
		
		for (IValueObject i : listNFEntrada) {
			NFEntradaEntradaEstoqueVO item = (NFEntradaEntradaEstoqueVO) i;
			
			EntradaEstoqueVO entrada = new EntradaEstoqueVO();
			entrada.setCodigoEmpresa(item.getCodigoEmpresaEntradaEstoque());
			entrada.setCodigoLoja(item.getCodigoLojaEntradaEstoque());
			entrada.setCodigoEntradaEstoque(item.getCodigoEntradaEstoque());
			
			entrada = (EntradaEstoqueVO) getGenericDao().get(entrada);
			
			if (entrada.getStatusEntradaEstoque().equals("P")) {
				this.messages.add(new MessageVO("Nota não pode ser excluída, a entrada de número " + entrada.getCodigoEntradaEstoque() + " está processada, estorne-a primeiro."));
				return false;
			}
			
			// Busca os itens
			ItemEntradaEstoqueVO itemFilter = new ItemEntradaEstoqueVO();
			
			itemFilter.setCodigoEmpresa(entrada.getCodigoEmpresa());
			itemFilter.setCodigoLoja(entrada.getCodigoLoja());
			itemFilter.setCodigoEntradaEstoque(entrada.getCodigoEntradaEstoque());
			
			List<IValueObject> listAux = getGenericDao().getList(itemFilter);
			
			// Adiciona na lista de itens para posterior exclusao.
			listItemEntrada.addAll(listAux);
			
			// Adiciona entrada na lista para exclusao.
			listEntrada.add(entrada);
		}
		
		// Apaga os vinculos das contas
		for (IValueObject item : listNFConta) {
			getGenericDao().remove(item);
		}
		
		// Apaga as contas
		for (IValueObject item : listContaPagar) {
			getGenericDao().remove(item);
		}
		
		// Apaga os vinculos das entradas
		for (IValueObject item : listNFEntrada) {
			getGenericDao().remove(item);
		}
		
		// Apaga os itens das entradas
		for (IValueObject item : listItemEntrada) {
			getGenericDao().remove(item);
		}
		
		// Apaga as entradas
		for (IValueObject item : listEntrada) {
			getGenericDao().remove(item);
		}
		
		// Exclui os itens
		ItemNFEntradaVO itemFilter = new ItemNFEntradaVO();
		
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoNFEntrada(getVo().getCodigoNFEntrada());
		
		itemFilter.setCodigoEmpresaEmitenteNFEntrada(getVo().getCodigoEmpresaEmitenteNFEntrada());
		itemFilter.setCodigoLojaEmitenteNFEntrada(getVo().getCodigoLojaEmitenteNFEntrada());
		itemFilter.setCodigoPessoaEmitenteNFEntrada(getVo().getCodigoPessoaEmitenteNFEntrada());
		itemFilter.setCodigoEnderecoPessoaEmitenteNFEntrada(getVo().getCodigoEnderecoPessoaEmitenteNFEntrada());
		
		List<IValueObject> itens = getGenericDao().getList(itemFilter);
		
		for (IValueObject item : itens) {
			getGenericDao().remove(item);
		}
		
		return super.doBeforeDelete();
	}

	public String getFlagImportarContas() {
		return FlagImportarContas==null?"N":FlagImportarContas;
	}

	public void setFlagImportarContas(String flagImportarContas) {
		FlagImportarContas = flagImportarContas;
	}

	public String getFlagImportarEstoque() {
		return FlagImportarEstoque==null?"N":FlagImportarEstoque;
	}

	public void setFlagImportarEstoque(String flagImportarEstoque) {
		FlagImportarEstoque = flagImportarEstoque;
	}

	public String getNomeArquivoXml() {
		return NomeArquivoXml;
	}

	public void setNomeArquivoXml(String nomeArquivoXml) {
		NomeArquivoXml = nomeArquivoXml;
	};
}