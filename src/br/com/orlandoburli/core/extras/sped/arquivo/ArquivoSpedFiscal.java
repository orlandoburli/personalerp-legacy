package br.com.orlandoburli.core.extras.sped.arquivo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

/**
 * Entidade que irá gerar o arquivo do SPED Fiscal
 * 
 * @author Orlando
 * 
 */
public class ArquivoSpedFiscal {

	public static final String FORMATO_DATA_SPED = "ddMMyyyy";

	private List<RegistroSped> registros;
	private LojaVO loja;
	private Date dataInicial;
	private Date dataFinal;
	
	private List<IValueObject> listVo;

	/**
	 * Cria um novo arquivo do SPED Fiscal
	 * 
	 * @param loja
	 *            Loja referenciada
	 * @param dataInicial
	 *            Data inicial do arquivo
	 * @param dataFinal
	 *            Data final do arquivo
	 */
	public ArquivoSpedFiscal(LojaVO loja, Date dataInicial, Date dataFinal) {
		this.registros = new ArrayList<RegistroSped>();
		this.listVo = new ArrayList<IValueObject>();
		this.loja = loja;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String buildConteudoArquivo() {
		StringBuilder sb = new StringBuilder();

		// Ordena os registros pela chave (REG)
		Collections.sort(registros, new Comparator<RegistroSped>() {

			@Override
			public int compare(RegistroSped o1, RegistroSped o2) {
				if (o1 != null && o2 != null) {
					return o1.getRegistro().compareTo(o2.getRegistro());
				}
				return 0;
			}
		});

		for (RegistroSped registro : registros) {
			sb.append(registro.toString() + "\n");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return buildConteudoArquivo();
	}

	public void addRegistro(RegistroSped registro) {
		this.registros.add(registro);
	}

	public List<RegistroSped> getRegistros() {
		return registros;
	}

	public LojaVO getLoja() {
		return this.loja;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public List<IValueObject> getListVo() {
		return listVo;
	}

}
