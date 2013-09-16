package br.com.orlandoburli.personalerp.facades.manutencao.trocamanutencao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.manutencao.FlagTrocaManutencaoDAO;
import br.com.orlandoburli.core.dao.manutencao.TipoFlagTrocaManutencaoDAO;
import br.com.orlandoburli.core.dao.manutencao.TrocaManutencaoDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.manutencao.FlagTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.HistoricoTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.TipoFlagTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.TrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.EmailValidator;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TrocaManutencaoCadastroFacade extends BaseCadastroFlexFacade<TrocaManutencaoVO, TrocaManutencaoDAO> {

	private static final long serialVersionUID = 1L;
	private Integer CodigoTipoFlagTrocaManutencaoSelecionado;
	private String Selecionado;
	
	@OutjectSession
	private List<FlagTrocaManutencaoVO> listFlags;

	public TrocaManutencaoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DataEntradaTrocaManutencao", "Data da Entrada"));
		addNewValidator(new NotEmptyValidator("NomeClienteTrocaManutencao", "Nome do Cliente"));
		addNewValidator(new NotEmptyValidator("DescricaoTrocaManutencao", "Descrição do Problema"));
		addNewValidator(new EmailValidator("EmailClienteTrocaManutencao", "Email do Cliente"));
		addNewValidator(new EmailValidator("Email2TrocaManutencao", "Email 2"));
		addNewValidator(new EmailValidator("Email3TrocaManutencao", "Email 3"));
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		
		FlagTrocaManutencaoVO flagFilter = new FlagTrocaManutencaoVO();
		
		flagFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		flagFilter.setCodigoLoja(getVo().getCodigoLoja());
		flagFilter.setCodigoTrocaManutencao(getVo().getCodigoTrocaManutencao());
		
		for (IValueObject item : getGenericDao().getList(flagFilter)) {
			getGenericDao().remove(item);
		}
		
		return super.doBeforeDelete();
	}
	
	@Override
	public void doAfterInsert() throws SQLException {
		// Gera o primeiro histórico
		HistoricoTrocaManutencaoVO hist = new HistoricoTrocaManutencaoVO();
		hist.setNewRecord(true);
		
		hist.setCodigoEmpresa(getVo().getCodigoEmpresa());
		hist.setCodigoLoja(getVo().getCodigoLoja());
		hist.setCodigoTrocaManutencao(getVo().getCodigoTrocaManutencao());
		hist.setDataHoraHistoricoTrocaManutencao(Utils.getNow());
		
		hist.setDescricaoHistoricoTrocaManutencao("Abertura da Ordem de Serviço.");
		hist.setCodigoSituacaoTrocaManutencao(new ParametroLojaDAO().getIntegerParametro(Constants.Parameters.Geral.SITUACAO_INICIAL_TROCA_MERCADORIAS, 0, 0));
		hist.setFlagEmailEnviadoTrocaManutencao("N");
		
		getGenericDao().persist(hist);
		
		super.doAfterInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		getVo().setFone1ClienteTrocaManutencao(Utils.filtro(getVo().getFone1ClienteTrocaManutencao(), "1234567890"));
		getVo().setFone2ClienteTrocaManutencao(Utils.filtro(getVo().getFone2ClienteTrocaManutencao(), "1234567890"));
		getVo().setFone3ClienteTrocaManutencao(Utils.filtro(getVo().getFone3ClienteTrocaManutencao(), "1234567890"));
		return super.doBeforeSave();
	}
	
	@Override
	public void doAfterSave() throws SQLException {
		for (FlagTrocaManutencaoVO item : listFlags) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setCodigoTrocaManutencao((getVo().getCodigoTrocaManutencao()));
			getGenericDao().persist(item);
		}
		super.doAfterSave();
	}

	@IgnoreMethodAuthentication
	public void clearitens() {
		try {
			getGenericDao().setAutoCommit(false);
			getGenericDao().getConnection();
			
			FlagTrocaManutencaoDAO flagDao = new FlagTrocaManutencaoDAO();
			flagDao.mergeDAO(getGenericDao());
			
			listFlags = new ArrayList<FlagTrocaManutencaoVO>();
			
			if (getVo().getCodigoTrocaManutencao() != null) {
				setVo((TrocaManutencaoVO) getGenericDao().get(getVo()));
			}
			
			if (getVo() != null && getVo().getCodigoTrocaManutencao() != null) {
				FlagTrocaManutencaoVO flagFilter = new FlagTrocaManutencaoVO();
				flagFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
				flagFilter.setCodigoLoja(getVo().getCodigoLoja());
				flagFilter.setCodigoTrocaManutencao(getVo().getCodigoTrocaManutencao());
				
				listFlags = flagDao.getList(flagFilter);
				
				Collections.sort(listFlags, new Comparator<FlagTrocaManutencaoVO>() {
					@Override
					public int compare(FlagTrocaManutencaoVO o1, FlagTrocaManutencaoVO o2) {
						return o1.getNomeTipoFlagTrocaManutencao().compareTo(o2.getNomeTipoFlagTrocaManutencao());
					}
				});
			}
			itens();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				getGenericDao().commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void itens() {
		if (listFlags == null || listFlags.size() == 0) {
			listFlags = new ArrayList<FlagTrocaManutencaoVO>();
			
			try {
				List<TipoFlagTrocaManutencaoVO> listTipoFlags = new TipoFlagTrocaManutencaoDAO().getList(null, "NomeTipoFlagTrocaManutencao");
				
				for (TipoFlagTrocaManutencaoVO item : listTipoFlags) {
					FlagTrocaManutencaoVO flag = new FlagTrocaManutencaoVO();
					flag.setNewRecord(true);
					flag.setCodigoTipoFlagTrocaManutencao(item.getCodigoTipoFlagTrocaManutencao());
					flag.setNomeTipoFlagTrocaManutencao(item.getNomeTipoFlagTrocaManutencao());
					flag.setValorFlagTrocaManutencao("N");
					
					listFlags.add(flag);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		write(Utils.voToXml(listFlags));
	}

	@IgnoreMethodAuthentication
	public void changeflag() {
		for (FlagTrocaManutencaoVO item : listFlags) {
			if (item.getCodigoTipoFlagTrocaManutencao().equals(getCodigoTipoFlagTrocaManutencaoSelecionado())) {
				item.setValorFlagTrocaManutencao(getSelecionado());
				break;
			}
		}
	}
	
	public void setListFlags(List<FlagTrocaManutencaoVO> listFlags) {
		this.listFlags = listFlags;
	}

	public List<FlagTrocaManutencaoVO> getListFlags() {
		return listFlags;
	}

	public void setCodigoTipoFlagTrocaManutencaoSelecionado(Integer codigoTipoFlagTrocaManutencaoSelecionado) {
		CodigoTipoFlagTrocaManutencaoSelecionado = codigoTipoFlagTrocaManutencaoSelecionado;
	}

	public Integer getCodigoTipoFlagTrocaManutencaoSelecionado() {
		return CodigoTipoFlagTrocaManutencaoSelecionado;
	}

	public void setSelecionado(String selecionado) {
		Selecionado = selecionado;
	}

	public String getSelecionado() {
		return Selecionado;
	}
}