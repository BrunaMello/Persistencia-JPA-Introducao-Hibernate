package testes;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import modelo.Categoria;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {

        cadastrarProduto();

        //criando um entitymanager para chamar a class jpautil
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

//        Produto p = produtoDAO.buscarPorId(1l);
//        System.out.println("Nome do produto: " + p.getNome());
//        System.out.println("Valor do produto: " +p.getPreco());
//
//        List<Produto> todos = produtoDAO.buscarTodos();
//        todos.forEach(p2 -> System.out.println("Lista de Produtos: " + p2.getNome() + " ID: " + p2.getId()));
//
//        List<Produto> buscarNome = produtoDAO.buscarPorNome("Iphone 12pro");
//        buscarNome.forEach(p3 -> System.out.println("O produto é: " + p3.getNome()));

        List<Produto> buscarNomeDaCategoria = produtoDAO.buscarPorNomedaCategoria("CELULARES");
        buscarNomeDaCategoria.forEach(p4 -> System.out.println("O produto é: " + p4.getNome()));

        BigDecimal preco = produtoDAO.buscarPrecoDoProdutoComNome("Iphone 12pro");
        System.out.println("O preco do Produto é: " + preco);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");


        Produto celular = new Produto("Iphone 12pro", "256gb", new BigDecimal("1200"), celulares);


        //criando um entitymanager para chamar a class jpautil
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        //inserir obj no banco de dados
        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
