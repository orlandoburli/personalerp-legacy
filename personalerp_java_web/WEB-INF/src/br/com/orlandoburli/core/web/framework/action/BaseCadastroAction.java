package br.com.orlandoburli.core.web.framework.action;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.be.exceptions.persistence.ExcluirBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.SalvarBeException;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.filters.InjectionFilter;
import br.com.orlandoburli.core.web.framework.retorno.RetornoAction;
import br.com.orlandoburli.core.web.framework.validators.AbstractValidator;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;
import br.com.orlandoburli.personalerp.model.sistema.loja.vo.LojaVO;

public abstract class BaseCadastroAction<E extends IValueObject, F extends BaseCadastroDAO<E>, G extends BaseBe<E, F>> extends BaseAction {

	private static final long serialVersionUID = 1L;

	protected List<MessageVO> messages;
	protected List<String> info;
	protected List<AbstractValidator> listvalidators;

	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	
	private UsuarioVO usuariosessao;
	
	private boolean writeVoOnInsert = false;
	private boolean writeVoOnUpdate = false;
	
	private String operacao;
	
	private String term;

	protected Class<?> getDAOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	protected Class<?> getVOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	protected Class<?> getBEClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2]);
	}

	public abstract String getJspCadastro();

	@SuppressWarnings("unchecked")
	protected E getNewVo() {
		try {
			return (E) getVOClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected G getNewBe() {
		try {
			return (G) getBEClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean doBeforeSave(E vo) {
		vo.setCodigoUsuarioLog(usuariosessao.getCodigoUsuario());
		vo.setCodigoEmpresaUsuarioLog(usuariosessao.getCodigoEmpresa());
		vo.setCodigoLojaUsuarioLog(usuariosessao.getCodigoLoja());

		return true;
	}

	public void inserir() {

		try {
			E vo = getNewVo();
			G be = getNewBe();
			
			injectVo(vo);

			doBeforeInsert(vo);
			doBeforeSave(vo);

			be.inserir(vo);
			
			doAfterInsert();
			doAfterSave();
			
			write(new Gson().toJson(new RetornoAction(true, "Registro inserido com sucesso!", "")));
			
		} catch (SalvarBeException e) {
			write(new Gson().toJson(new RetornoAction(false, e.getMessage(), e.getCampo())));
		}

	}

	public void alterar() {
		try {
			E vo = getNewVo();
			G be = getNewBe();

			injectVo(vo);

			be.alterar(vo);

			write(new Gson().toJson(new RetornoAction(true, "Registro alterado com sucesso!", "")));

		} catch (SalvarBeException e) {
			write(new Gson().toJson(new RetornoAction(false, e.getMessage(), e.getCampo())));
		}
	}
	
	public void excluir() {
		try {
			E vo = getNewVo();
			G be = getNewBe();

			injectVo(vo);

			be.excluir(vo);
			
			write(new Gson().toJson(new RetornoAction(true, "Registro excluído com sucesso!", "")));

		} catch (ExcluirBeException e) {
			write(new Gson().toJson(new RetornoAction(false, e.getMessage(), null)));
		}
	}

	public void visualizar() {
		try {

			G be = getNewBe();

			E vo = getNewVo();

			injectVo(vo);

			doBeforeLoad(getRequest(), getResponse(), vo, be);

			vo = be.get(vo);

			doBeforeWriteVo(vo);

			getRequest().setAttribute("vo", vo);

			getRequest().getSession().setAttribute(getVoSessionId(), vo);

			forward(getJspCadastro());

		} catch (ListException e) {
			new Gson().toJson(new RetornoAction(false, e.getMessage(), null));
		}
	}

	private void injectVo(E vo) {
		InjectionFilter filter = new InjectionFilter();
		filter.setContext(getContext());
		filter.setRequest(getRequest());
		filter.setResponse(getResponse());

		try {
			filter.doFilter(vo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void doBeforeLoad(HttpServletRequest request, HttpServletResponse response, E vo, G be) {
		
	}

	public void doBeforeWriteVo(E vo) {
	}

	public void consultar() {
		visualizar();
	}

	public boolean doBeforeUpdate(E vo) throws SQLException {
		return true;
	}

	public boolean doBeforeInsert(E vo) {
		// Chave padrao
		if (Utils.getproperty(vo, "CodigoEmpresa") == null || Utils.getproperty(vo, "CodigoEmpresa").equals(0)) {
			InjectionFilter.setproperty(vo, "CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
		}
		if (Utils.getproperty(vo, "CodigoLoja") == null || Utils.getproperty(vo, "CodigoLoja").equals(0)) {
			InjectionFilter.setproperty(vo, "CodigoLoja", getLojasessao().getCodigoLoja());
		}
		return true;
	}

	public boolean doBeforeDelete(E vo) {
		// Verifica se o vo tem alguma chave nula
		if (DaoUtils.hasNullKey(vo)) {
			return false;
		}
		return true;
	}

	/**
	 * Sobreescreva este método para tratar qualquer exceção de sql na
	 * alteracao, exclusao, inclusao ou alteracao
	 * 
	 * @param ex
	 *            Excecao disparada
	 */
	public void doBeforeWriteSqlErro(Exception e) {
		if (messages.size() <= 0) {
			writeErrorMessage(e.getMessage());
		} else {
			write(Utils.voToXml(messages));
		}
	}

	/**
	 * Sobrescrever o metodo para operacoes realizadas apos inserir um registro
	 */
	public void doAfterInsert() {

	}

	public void doAfterUpdate() {

	}

	public void doAfterSave() {

	}

	public void doAfterDelete() {

	}

	public void doAfterCommit(String operacao) {

	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
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

	/**
	 * Essa propriedade indica se vai ser escrito um "ok" ou o próprio VO, no
	 * caso de sucesso num insert.
	 * 
	 * @param writeVoOnInsert
	 */
	public void setWriteVoOnInsert(boolean writeVoOnInsert) {
		this.writeVoOnInsert = writeVoOnInsert;
	}

	/**
	 * Essa propriedade indica se vai ser escrito um "ok" ou o próprio VO, no
	 * caso de sucesso num insert.
	 * 
	 * @return
	 */
	public boolean isWriteVoOnInsert() {
		return writeVoOnInsert;
	}

	/**
	 * Essa propriedade indica se vai ser escrito um "ok" ou o próprio VO, no
	 * caso de sucesso num update.
	 * 
	 * @param writeVoOnUpdate
	 */
	public void setWriteVoOnUpdate(boolean writeVoOnUpdate) {
		this.writeVoOnUpdate = writeVoOnUpdate;
	}

	/**
	 * Essa propriedade indica se vai ser escrito um "ok" ou o próprio VO, no
	 * caso de sucesso num update.
	 * 
	 * @return
	 */
	public boolean isWriteVoOnUpdate() {
		return writeVoOnUpdate;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getOperacao() {
		return operacao;
	}

	protected String getNomeEntidade() {
		return getVOClass().getSimpleName().replace("VO", "").toLowerCase();
	}

	private String getVoSessionId() {
		return getNomeEntidade() + "_cadastro_vo";
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
