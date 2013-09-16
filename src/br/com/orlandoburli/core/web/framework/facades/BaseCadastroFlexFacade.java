package br.com.orlandoburli.core.web.framework.facades;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.filters.InjectionFilter;
import br.com.orlandoburli.core.web.framework.validators.AbstractValidator;

public abstract class BaseCadastroFlexFacade<E extends IValueObject, F extends BaseCadastroDAO<E>> extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private E vo;
	protected F dao;
	private GenericDAO genericDao;

	protected List<MessageVO> messages;
	protected List<String> info;
	protected List<AbstractValidator> listvalidators;

	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private GrupoEmpresaVO grupoempresasessao;

	private UsuarioVO usuariosessao;

	private boolean writeVoOnInsert = false;
	private boolean writeVoOnUpdate = false;

	private String operacao;

	@SuppressWarnings("unchecked")
	public BaseCadastroFlexFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		try {
			dao = (F) getDAOClass().newInstance();
			setVo((E) getVOClass().newInstance());
			setGenericDao(new GenericDAO());

			this.messages = new ArrayList<MessageVO>();
			this.info = new ArrayList<String>();
			this.listvalidators = new ArrayList<AbstractValidator>();

		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (SecurityException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public BaseCadastroFlexFacade() {
		super();
		try {
			dao = (F) getDAOClass().newInstance();
			setVo((E) getVOClass().newInstance());
			setGenericDao(new GenericDAO());

			this.messages = new ArrayList<MessageVO>();
			this.info = new ArrayList<String>();
			this.listvalidators = new ArrayList<AbstractValidator>();

		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (SecurityException e) {
		}
	}

	protected Class<?> getDAOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	protected Class<?> getVOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public void addNewValidator(AbstractValidator validator) {
		if (validator != null) {
			this.listvalidators.add(validator);
		}
	}

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

	public boolean doBeforeSave() throws SQLException {
		getVo().setCodigoUsuarioLog(usuariosessao.getCodigoUsuario());
		getVo().setCodigoEmpresaUsuarioLog(usuariosessao.getCodigoEmpresa());
		getVo().setCodigoLojaUsuarioLog(usuariosessao.getCodigoLoja());

		for (AbstractValidator validator : listvalidators) {
			validator.setVo(getVo());
			if (!validator.validate()) {
				this.messages.add(new MessageVO(validator.getMessage(), validator.getFieldFocus()));
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public void inserir() {
		// try {
		// if
		// (!SystemManager.isLicenciado(getEmpresasessao().getCodigoEmpresa(),
		// getLojasessao().getCodigoLoja())) {
		// writeErrorMessage("Sistema não licenciado! Impossível inserir dados!");
		// return;
		// }
		// } catch (SQLException e1) {
		// writeErrorMessage(e1.getMessage());
		// e1.printStackTrace();
		// return;
		// }

		getVo().setNewRecord(true);
		if (isPost()) {
			try {
				if (doBeforeInsert() && doBeforeSave()) {

					getGenericDao().setAutoCommit(false);
					if (getGenericDao().persist(getVo())) {
						// Executa um "get" so para buscar dados relacionados...
						setVo((E) getGenericDao().get(getVo()));

						doAfterInsert();
						doAfterSave();

						if (isWriteVoOnInsert()) {
							// Devolve o vo preenchido
							write(Utils.voToXml(getVo()));
						} else {
							write("ok");
						}
						getGenericDao().commit();
						doAfterCommit(Constants.INSERIR);
					}

				} else {
					write(Utils.voToXml(messages));
				}
			} catch (SQLException e) {
				getGenericDao().rollback();
				doBeforeWriteSqlErro(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void alterar() {
		// try {
		// if
		// (!SystemManager.isLicenciado(getEmpresasessao().getCodigoEmpresa(),
		// getLojasessao().getCodigoLoja())) {
		// writeErrorMessage("Sistema não licenciado! Impossível alterar dados!");
		// return;
		// }
		// } catch (SQLException e1) {
		// writeErrorMessage(e1.getMessage());
		// e1.printStackTrace();
		// return;
		// }
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

		getVo().setNewRecord(false);

		if (isPost()) {
			try {
				if (doBeforeUpdate() && doBeforeSave()) {

					getGenericDao().setAutoCommit(false);
					if (getGenericDao().persist(getVo())) {
						// Executa um "get" so para buscar dados relacionados...
						setVo((E) getGenericDao().get(getVo()));
						doAfterUpdate();
						doAfterSave();
						if (isWriteVoOnUpdate()) {
							// Devolve o vo preenchido
							write(Utils.voToXml(getVo()));
						} else {
							write("ok");
						}
						getGenericDao().commit();
						doAfterCommit(Constants.ALTERAR);
					}
				} else {
					write(Utils.voToXml(messages));
				}
			} catch (SQLException e) {
				getGenericDao().rollback();
				doBeforeWriteSqlErro(e);
			}
		}
	}

	public void visualizar() {
		try {
			this.setVo(this.dao.get(getVo()));
		} catch (SQLException e) {
			doBeforeWriteSqlErro(e);
		}
		doBeforeWriteVo(getVo());
		write(Utils.voToXml(getVo()));
		dispatch();
	}

	public void doBeforeWriteVo(E vo) {}

	public void consultar() {
		visualizar();
	}

	public void excluir() {
		// try {
		// if
		// (!SystemManager.isLicenciado(getEmpresasessao().getCodigoEmpresa(),
		// getLojasessao().getCodigoLoja())) {
		// writeErrorMessage("Sistema não licenciado! Impossível remover dados!");
		// return;
		// }
		// } catch (SQLException e1) {
		// writeErrorMessage(e1.getMessage());
		// e1.printStackTrace();
		// return;
		// }
		try {
			getGenericDao().setAutoCommit(false);
			if (doBeforeDelete()) {
				if (this.getGenericDao().remove(getVo())) {
					write("ok");
					doAfterDelete();
					dispatch();
					getGenericDao().commit();
					doAfterCommit(Constants.EXCLUIR);
				} else {
					getGenericDao().rollback();
				}
			} else {
				getGenericDao().rollback();
				write(Utils.voToXml(messages));
			}
		} catch (SQLException e) {
			getGenericDao().rollback();
			doBeforeWriteSqlErro(e);
		}
	}

	public boolean doBeforeUpdate() throws SQLException {
		return true;
	}

	public boolean doBeforeInsert() throws SQLException {
		// Chave padrao
		if (Utils.getproperty(getVo(), "CodigoEmpresa") == null || Utils.getproperty(getVo(), "CodigoEmpresa").equals(0)) {
			InjectionFilter.setproperty(getVo(), "CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
		}
		if (Utils.getproperty(getVo(), "CodigoLoja") == null || Utils.getproperty(getVo(), "CodigoLoja").equals(0)) {
			InjectionFilter.setproperty(getVo(), "CodigoLoja", getLojasessao().getCodigoLoja());
		}
		return true;
	}

	public boolean doBeforeDelete() throws SQLException {
		// Verifica se o vo tem alguma chave nula
		if (DaoUtils.hasNullKey(getVo())) {
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
	public void doAfterInsert() throws SQLException {

	}

	public void doAfterUpdate() throws SQLException {

	}

	public void doAfterSave() throws SQLException {

	}

	public void doAfterDelete() throws SQLException {

	}

	public void doAfterCommit(String operacao) throws SQLException {

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

	public E getVo() {
		return vo;
	}

	public void setVo(E vo) {
		this.vo = vo;
	}

	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	public GrupoEmpresaVO getGrupoempresasessao() {
		return grupoempresasessao;
	}

	public void setGrupoempresasessao(GrupoEmpresaVO grupoempresasessao) {
		this.grupoempresasessao = grupoempresasessao;
	}
}