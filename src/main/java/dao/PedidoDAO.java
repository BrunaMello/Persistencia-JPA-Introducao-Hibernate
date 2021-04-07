package dao;

import modelo.Pedido;
import vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public BigDecimal somarValorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    //funcao para retorno para retorno de varios tipos de coluna em uma tabela primeira maneira
    public  List<Object[]> relatorioDeVendas() {
        String jpql = "SELECT produto.nomeProduto, SUM(item.quantidade), MAX(pedido.dataPedido) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nomeProduto ORDER BY produto.nomeProduto DESC";
        return em.createQuery(jpql, Object[].class).getResultList();
    }

    //funcao para retorno para retorno de varios tipos de coluna em uma tabela segunda maneira utilizando select new
    public  List<RelatorioVendasVo> relatorioDeVendasVo() {
        String jpql = "SELECT new vo.RelatorioVendasVo(produto.nomeProduto, SUM(item.quantidade), MAX(pedido.dataPedido)) FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nomeProduto ORDER BY produto.nomeProduto DESC";
        return em.createQuery(jpql, RelatorioVendasVo.class).getResultList();
    }




}
