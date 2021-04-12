package dao;

import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        return em.find(Produto.class, id);
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
        return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nomeProduto = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    //consultas com parametros nao obrigatorios, dinamicos
    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
        String jpql = "SELECT p FROM Produto p WHERE 1=1";
        if (nome != null && !nome.trim().isEmpty()) {
            jpql = " AND p.nome = :nome";
        }
        if (preco != null) {
            jpql = " AND p.preco = :preco";
        }
        if (dataCadastro != null) {
            jpql = " AND p.dataCadastro = :dataCadastro";
        }

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }
        return query.getResultList();

    }

    //consultas com parametros nao obrigatorios, dinamicos sem codigos duplicados usando o criteria
    public List<Produto> buscaPorProdutoComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = criteriaBuilder.and();

        if (nome != null && !nome.trim().isEmpty()) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nomeProduto"), nome));
        }
        if (preco != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }



}
