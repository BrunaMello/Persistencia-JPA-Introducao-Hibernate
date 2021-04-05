package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularDatabase();
        fazerPedido();

    }

    private static void popularDatabase(){

        //ciando categorias
        Categoria celulares = new Categoria("CELULARES");


        //cirando produtos
        Produto celular = new Produto("Iphone 12pro", "256gb", new BigDecimal("1200"), celulares);

        //criando clientes
        Cliente cliente = new Cliente("Bruna", "123456");


        //criando um entitymanager para chamar a class jpautil
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        //inserir obj no banco de dados
        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }

    public static void fazerPedido(){

        //criando um entitymanager para chamar a class jpautil
        EntityManager em = JPAUtil.getEntityManager();

        //recuperando dados do banco
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Produto produto1 = produtoDAO.buscarPorId(1l);
        Cliente cliente1 = clienteDAO.buscarPorId(1l);

        em.getTransaction().begin();


        Pedido pedido1 = new Pedido(cliente1);
        pedido1.adicionarItem(new ItemPedido(10, produto1, pedido1));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido1);

        em.getTransaction().commit();
    }
}
