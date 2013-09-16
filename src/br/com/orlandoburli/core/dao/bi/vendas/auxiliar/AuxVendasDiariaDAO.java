package br.com.orlandoburli.core.dao.bi.vendas.auxiliar;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import br.com.orlandoburli.core.dao.BaseDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.bi.vendas.ProdutoVendaDiariaVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;

public class AuxVendasDiariaDAO extends BaseDAO {

	public RowSet getVendas(Integer CodigoEmpresa, Integer CodigoLoja, Date DataInicial, Date DataFinal) throws SQLException {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT codigoempresa, codigoloja, datavenda, sum(valortotal) AS valortotal \n");
			sb.append("  FROM personalerp.vendasproduto \n");
			sb.append(" WHERE 1=1 \n");

			if (CodigoEmpresa != null) {
				sb.append("   AND CodigoEmpresa = " + CodigoEmpresa + " \n");
			}
			if (CodigoLoja != null) {
				sb.append("   AND CodigoLoja    = " + CodigoLoja + " \n");
			}
			sb.append("   AND DataVenda    >= '" + DataInicial + "' \n");
			sb.append("   AND DataVenda    <= '" + DataFinal + "' \n");
			sb.append(" GROUP BY codigoempresa, codigoloja, datavenda");
			sb.append(" ORDER BY DataVenda");

			return getRowSet(sb.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public RowSet getItemVendasDia(Integer CodigoEmpresa, Integer CodigoLoja, Date Data) throws SQLException {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("WITH vendasitens as ( \n");
			sb.append("SELECT v.codigoempresa, v.codigoloja, v.datavenda, v.codigoempresaproduto, v.codigolojaproduto, v.codigoproduto, v.descricaoproduto, e.estoquefisico, e.quantidadedisplayestoque, e.quantidadegavetaestoque, sum(v.totalpecas) as totalpecas, sum(v.valortotal) AS valortotal \n");
			sb.append("  FROM personalerp.vendasproduto v \n");
			sb.append("  LEFT JOIN personalerp.estoque e ON  \n");
			sb.append("       e.codigoempresa = v.codigoempresaproduto AND \n");
			sb.append("       e.codigoloja = v.codigolojaproduto AND \n");
			sb.append("       e.codigoproduto = v.codigoproduto AND \n");
			sb.append("		  e.CodigoEmpresaEstoque = " + CodigoEmpresa + " AND \n");
			sb.append("       e.CodigoLojaEstoque    = " + CodigoLoja + " \n");
			sb.append(" GROUP BY v.codigoempresa, v.codigoloja, v.datavenda, v.codigoempresaproduto, v.codigolojaproduto, v.codigoproduto, v.descricaoproduto, e.estoquefisico, e.quantidadedisplayestoque, e.quantidadegavetaestoque \n");
			sb.append(")  \n");
			sb.append("SELECT * FROM vendasitens \n");
			sb.append(" WHERE (TotalPecas > 0 OR ValorTotal > 0 And CodigoProduto > 0)  \n");
			sb.append("   AND CodigoEmpresa = " + CodigoEmpresa + " \n");
			sb.append("   AND CodigoLoja    = " + CodigoLoja + " \n");
			sb.append("   AND DataVenda     = '" + Data + "' \n");

			return getRowSet(sb.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProdutoVendaDiariaVO getProdutoByUltimaVenda(ProdutoVO produto, Integer codigoEmpresa, Integer codigoLoja) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM personalerp.ProdutoVendaDiaria a\n");
		sb.append(" WHERE 1=1\n");
		sb.append("   AND a.CodigoEmpresaProduto = " + produto.getCodigoEmpresa() + "\n");
		sb.append("   AND a.CodigoLojaProduto = " + produto.getCodigoLoja() + "\n");
		sb.append("   AND a.CodigoProduto = " + produto.getCodigoProduto() + "\n");
		sb.append("   AND a.CodigoEmpresa = " + codigoEmpresa + "\n");
		sb.append("   AND a.CodigoLoja = " + codigoLoja + "\n");
		sb.append("   AND a.DataVenda = (SELECT MAX(b.DataVenda) FROM personalerp.ProdutoVendaDiaria b\n");
		sb.append("                       WHERE 1=1\n");
		sb.append("                         AND b.CodigoEmpresaProduto = " + produto.getCodigoEmpresa() + "\n");
		sb.append("                         AND b.CodigoLojaProduto = " + produto.getCodigoLoja() + "\n");
		sb.append("                         AND b.CodigoProduto = " + produto.getCodigoProduto() + "\n");
		sb.append("                         AND b.CodigoEmpresa = " + codigoEmpresa + "\n");
		sb.append("                         AND b.CodigoLoja = " + codigoLoja + ")");
		
		List<ProdutoVendaDiariaVO> list = new ArrayList<ProdutoVendaDiariaVO>();
		
		try {
			
			DaoUtils.rowSetToList(ProdutoVendaDiariaVO.class, list , getRowSet(sb.toString()));
			
			if (list.size() > 0) {
				return list.get(0);
			}
			
		} catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (SecurityException ex) {
        } catch (InvocationTargetException ex) {
        } catch (ClassNotFoundException ex) {
        }
		return null;

	}
}