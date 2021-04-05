package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {

    private EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, 1l);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        //createquery somente monta a query é preciso o get resulta para disparar para o DB
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        // no lugar de :nome pode usar o ?1, sepois é so apontar a posicao
        String jpql = "SELECT p FROM Produto p WHERE p.nomeProduto = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomedaCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nomeCategoria = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nomeProduto = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

}
