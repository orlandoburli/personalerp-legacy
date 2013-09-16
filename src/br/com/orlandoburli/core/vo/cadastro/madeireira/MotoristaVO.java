package br.com.orlandoburli.core.vo.cadastro.madeireira;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class MotoristaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoMotorista;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private String NomeMotorista;
	private String VeiculoMotorista;
	private String PlacaVeiculoMotorista;
	private String EnderecoMotorista;
	private String BairroMotorista;
	private String Fone1Motorista;
	private String Fone2Motorista;
	private Integer CodigoCidade;
	private String ObservacoesMotorista;
	
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

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCodigoMotorista() {
		return CodigoMotorista;
	}

	public void setCodigoMotorista(Integer codigoMotorista) {
		CodigoMotorista = codigoMotorista;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public String getNomeMotorista() {
		return NomeMotorista;
	}

	public void setNomeMotorista(String nomeMotorista) {
		NomeMotorista = nomeMotorista;
	}

	public String getVeiculoMotorista() {
		return VeiculoMotorista;
	}

	public void setVeiculoMotorista(String veiculoMotorista) {
		VeiculoMotorista = veiculoMotorista;
	}

	public String getPlacaVeiculoMotorista() {
		return PlacaVeiculoMotorista;
	}

	public void setPlacaVeiculoMotorista(String placaVeiculoMotorista) {
		PlacaVeiculoMotorista = placaVeiculoMotorista;
	}

	public String getEnderecoMotorista() {
		return EnderecoMotorista;
	}

	public void setEnderecoMotorista(String enderecoMotorista) {
		EnderecoMotorista = enderecoMotorista;
	}

	public String getBairroMotorista() {
		return BairroMotorista;
	}

	public void setBairroMotorista(String bairroMotorista) {
		BairroMotorista = bairroMotorista;
	}

	public String getFone1Motorista() {
		return Fone1Motorista;
	}

	public void setFone1Motorista(String fone1Motorista) {
		Fone1Motorista = fone1Motorista;
	}

	public String getFone2Motorista() {
		return Fone2Motorista;
	}

	public void setFone2Motorista(String fone2Motorista) {
		Fone2Motorista = fone2Motorista;
	}

	public Integer getCodigoCidade() {
		return CodigoCidade;
	}

	public void setCodigoCidade(Integer codigoCidade) {
		CodigoCidade = codigoCidade;
	}

	public String getObservacoesMotorista() {
		return ObservacoesMotorista;
	}

	public void setObservacoesMotorista(String observacoesMotorista) {
		ObservacoesMotorista = observacoesMotorista;
	}
}