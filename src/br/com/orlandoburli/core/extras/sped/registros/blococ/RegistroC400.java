package br.com.orlandoburli.core.extras.sped.registros.blococ;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC400 extends RegistroSped {

	private String codigoModelo;
	private String modeloEquipamento;
	private String numeroSerieEquipamento;
	private Integer codigoCaixa;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C400";
	}

	@CampoSped(name = "COD_MOD", order = 2)
	public String getCodigoModelo() {
		return codigoModelo;
	}

	@CampoSped(name = "ECF_MOD", order = 3)
	public String getModeloEquipamento() {
		return modeloEquipamento;
	}

	@CampoSped(name = "ECF_FAB", order = 4)
	public String getNumeroSerieEquipamento() {
		return numeroSerieEquipamento;
	}

	@CampoSped(name = "ECF_CX", order = 5)
	public Integer getCodigoCaixa() {
		return codigoCaixa;
	}

	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public void setModeloEquipamento(String modeloEquipamento) {
		this.modeloEquipamento = modeloEquipamento;
	}

	public void setNumeroSerieEquipamento(String numeroSerieEquipamento) {
		this.numeroSerieEquipamento = numeroSerieEquipamento;
	}

	public void setCodigoCaixa(Integer codigoCaixa) {
		this.codigoCaixa = codigoCaixa;
	}

}
