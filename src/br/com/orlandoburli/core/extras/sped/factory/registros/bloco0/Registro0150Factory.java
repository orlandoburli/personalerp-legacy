package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.base.EstadoDAO;
import br.com.orlandoburli.core.dao.base.PaisDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0150;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.base.EstadoVO;
import br.com.orlandoburli.core.vo.base.PaisVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.web.framework.validators.CpfCnpjValidator;

public class Registro0150Factory {

	private static Registro0150Factory factory;
	
	private Registro0150Factory() {
		
	}
	
	public static Registro0150Factory getFactory() {
		if (factory == null) {
			factory = new Registro0150Factory();
		}
		return factory;
	}
	
	public void buildRegistro0150(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException, SpedException {
		EnderecoPessoaDAO enderecoDao = new EnderecoPessoaDAO();
		enderecoDao.mergeDAO(dao);

		PessoaDAO pessoaDao = new PessoaDAO();
		pessoaDao.mergeDAO(dao);

		CidadeDAO cidadeDao = new CidadeDAO();
		cidadeDao.mergeDAO(dao);

		EstadoDAO estadoDao = new EstadoDAO();
		estadoDao.mergeDAO(dao);

		PaisDAO paisDao = new PaisDAO();
		paisDao.mergeDAO(dao);

		// Lista os participantes
		EnderecoPessoaVO enderecoFilter = new EnderecoPessoaVO();

		StringBuilder filterEnderecoPessoa = new StringBuilder();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		filterEnderecoPessoa.append("\n(a.CodigoEmpresa, a.CodigoLoja, a.CodigoPessoa, a.CodigoEnderecoPessoa) IN (");
		filterEnderecoPessoa.append("SELECT n1.CodigoEmpresaEmitenteNFEntrada, n1.CodigoLojaEmitenteNFEntrada, n1.CodigoPessoaEmitenteNFEntrada, n1.CodigoEnderecoPessoaEmitenteNFEntrada \n");
		filterEnderecoPessoa.append("  FROM personalerp.NFEntrada n1 \n");
		filterEnderecoPessoa.append(" WHERE n1.CodigoEmpresa = " + arquivo.getLoja().getCodigoEmpresa() + " \n");
		filterEnderecoPessoa.append("   AND n1.CodigoLoja = " + arquivo.getLoja().getCodigoLoja() + " \n");
		filterEnderecoPessoa.append("   AND n1.DataEmissaoNFEntrada BETWEEN '" + sdf.format(arquivo.getDataInicial()) + "' AND '" + sdf.format(arquivo.getDataFinal()) + "' \n");
		filterEnderecoPessoa.append("UNION ALL \n");
		filterEnderecoPessoa.append("SELECT n2.CodigoEmpresaDestinatarioNFSaida, n2.CodigoLojaDestinatarioNFSaida, n2.CodigoPessoaDestinatarioNFSaida, n2.CodigoEnderecoPessoaDestinatarioNFSaida");
		filterEnderecoPessoa.append("  FROM personalerp.NFSaida n2 \n");
		filterEnderecoPessoa.append(" WHERE n2.CodigoEmpresa = " + arquivo.getLoja().getCodigoEmpresa() + " \n");
		filterEnderecoPessoa.append("   AND n2.CodigoLoja = " + arquivo.getLoja().getCodigoLoja() + " \n");
		filterEnderecoPessoa.append("   AND n2.DataEmissaoNFSaida BETWEEN '" + sdf.format(arquivo.getDataInicial()) + "' AND '" + sdf.format(arquivo.getDataFinal()) + "' \n");
		filterEnderecoPessoa.append(")");
		
		enderecoDao.setSpecialCondition(filterEnderecoPessoa.toString());

		List<EnderecoPessoaVO> listEnderecos = enderecoDao.getList(enderecoFilter);

		enderecoDao.setSpecialCondition(null);
		for (EnderecoPessoaVO endereco : listEnderecos) {
			PessoaVO pessoa = new PessoaVO();

			pessoa.setCodigoEmpresa(endereco.getCodigoEmpresa());
			pessoa.setCodigoLoja(endereco.getCodigoLoja());
			pessoa.setCodigoPessoa(endereco.getCodigoPessoa());

			pessoa = pessoaDao.get(pessoa);

			CidadeVO cidade = new CidadeVO();
			cidade.setCodigoCidade(endereco.getCodigoCidadeEnderecoPessoa());
			cidade = cidadeDao.get(cidade);

			if (cidade == null) {
				throw new SpedException("Cidade do participante " + endereco.getNomeEnderecoPessoa() + " não informado!");
			}

			EstadoVO estado = new EstadoVO();
			estado.setCodigoEstado(cidade.getCodigoEstado());
			estado = estadoDao.get(estado);

			if (estado == null) {
				throw new SpedException("Estado da cidade " + cidade.getNomeCidade() + " não informado!");
			}

			PaisVO pais = new PaisVO();
			pais.setCodigoPais(estado.getCodigoPais());
			pais = paisDao.get(pais);

			if (pais == null) {
				throw new SpedException("País do estado " + estado.getNomeEstado() + " não informado!");
			}

			if (pais.getCodigoIbgePais() == null || pais.getCodigoIbgePais().trim().length() != 4) {
				throw new SpedException("Código IBGE do país " + pais.getNomePais() + " não configurado corretamente!");
			}

			CpfCnpjValidator cpfCnpjValidator = new CpfCnpjValidator("CpfCnpjEnderecoPessoa");
			cpfCnpjValidator.setVo(endereco);

			if (!cpfCnpjValidator.validate()) {
				throw new SpedException("CPF / CNPJ do participante " + endereco.getNomeEnderecoPessoa() + " é inválido!");
			}

			Registro0150 reg0150 = new Registro0150();

			String codigoParticipante = Utils.fillString(endereco.getCodigoEmpresa(), "0", 3, 1) + Utils.fillString(endereco.getCodigoLoja(), "0", 3, 1) + Utils.fillString(endereco.getCodigoPessoa(), "0", 8, 1) + Utils.fillString(endereco.getCodigoEnderecoPessoa(), "0", 8, 1);
			reg0150.setCodigoParticipante(codigoParticipante);

			reg0150.setNome(endereco.getNomeEnderecoPessoa());

			try {
				reg0150.setCodigoPais(Integer.parseInt(pais.getCodigoIbgePais()));
			} catch (NumberFormatException e) {
				throw new SpedException("O código IBGE do país " + pais.getNomePais() + " não está configurado corretamente!");
			}

			if (pessoa.getNaturezaPessoa().equals("F")) {
				// Pessoa física
				if (endereco.getCpfCnpjEnderecoPessoa().length() != 11) {
					throw new SpedException("CPF do participante " + endereco.getNomeEnderecoPessoa() + " é inválido!");
				}
				reg0150.setCpf(endereco.getCpfCnpjEnderecoPessoa());
			} else if (pessoa.getNaturezaPessoa().equals("J")) {
				// Pessoa jurídica
				if (endereco.getCpfCnpjEnderecoPessoa().length() != 14) {
					throw new SpedException("CNPJ do participante " + endereco.getNomeEnderecoPessoa() + " é inválido!");
				}
				reg0150.setCnpj(endereco.getCpfCnpjEnderecoPessoa());
			}

			reg0150.setIe(endereco.getInscricaoEstadualEnderecoPessoa());
			try {
				reg0150.setCodigoMunicipio(Integer.parseInt(estado.getCodigoIbgeEstado() + cidade.getCodigoIbgeCidade()));
				if (reg0150.getCodigoMunicipio().toString().length() != 7) {
					throw new SpedException("Código IBGE da cidade " + cidade.getNomeCidade() + " é inválido!");
				}
			} catch (NumberFormatException e) {
				throw new SpedException("Código IBGE da cidade " + cidade.getNomeCidade() + " é inválido!");
			}

			reg0150.setSuframa(""); // Nao tenho no cadastro
			reg0150.setEndereco(endereco.getLogradouroEnderecoPessoa());
			reg0150.setNumero(""); // Nao tenho no cadastro
			reg0150.setComplemento(""); // Nao tenho no cadastro
			reg0150.setBairro(endereco.getBairroEnderecoPessoa());

			arquivo.addRegistro(reg0150);
		}
	}
}
