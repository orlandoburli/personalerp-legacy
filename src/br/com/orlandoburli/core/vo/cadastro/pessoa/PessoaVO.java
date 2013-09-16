package br.com.orlandoburli.core.vo.cadastro.pessoa;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PessoaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	public static final String FISICA = "F";
	public static final String JURIDICA = "J";
	
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoPessoa;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private String NomeRazaoSocialPessoa;
	private String EmailPessoa;
	private String ObservacoesPessoa;
	private String NaturezaPessoa;

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
	}

	public Integer getCodigoPessoa() {
		return CodigoPessoa;
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

	public void setNomeRazaoSocialPessoa(String nomeRazaoSocialPessoa) {
		NomeRazaoSocialPessoa = nomeRazaoSocialPessoa;
	}

	public String getNomeRazaoSocialPessoa() {
		return NomeRazaoSocialPessoa;
	}

	public void setEmailPessoa(String emailPessoa) {
		EmailPessoa = emailPessoa;
	}

	public String getEmailPessoa() {
		return EmailPessoa;
	}

	public void setObservacoesPessoa(String observacoesPessoa) {
		ObservacoesPessoa = observacoesPessoa;
	}

	public String getObservacoesPessoa() {
		return ObservacoesPessoa;
	}

	public void setNaturezaPessoa(String naturezaPessoa) {
		NaturezaPessoa = naturezaPessoa;
	}

	public String getNaturezaPessoa() {
		return NaturezaPessoa;
	}
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
	
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}
}