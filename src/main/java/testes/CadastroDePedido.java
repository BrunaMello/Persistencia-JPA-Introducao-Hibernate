package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.*;
import util.JPAUtil;
import vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        //popularDatabase();
        fazerPedido();



    }

    private static void popularDatabase(){

        //ciando categorias
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");


        //cirando produtos
        Produto celular = new Produto("Iphone 12pro", "256gb", new BigDecimal("1200"), celulares);
        Produto videogame = new Produto("Playstation 5", "1 tera ", new BigDecimal("2500"), videogames);
        Produto macbook = new Produto("Macbookpro 13", "512gb", new BigDecimal("4500"), informatica);

        //criando clientes
        Cliente cliente = new Cliente("Bruna", "123456");
        Cliente cliente2 = new Cliente("Marcelo", "54321");


        //criando um entitymanager para chamar a class jpautil
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        //inserir obj no banco de dados
        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        categoriaDAO.cadastrar(videogames);
        categoriaDAO.cadastrar(informatica);

        produtoDAO.cadastrar(celular);
        produtoDAO.cadastrar(macbook);
        produtoDAO.cadastrar(videogame);

        clienteDAO.cadastrar(cliente);
        clienteDAO.cadastrar(cliente2);


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
        Produto produto2 = produtoDAO.buscarPorId(2l);
        Produto produto3 = produtoDAO.buscarPorId(3l);
        Cliente cliente1 = clienteDAO.buscarPorId(1l);
        Cliente cliente2 = clienteDAO.buscarPorId(2l);

        em.getTransaction().begin();


        Pedido pedido1 = new Pedido(cliente1);
        pedido1.adicionarItem(new ItemPedido(10, produto2, pedido1));
        pedido1.adicionarItem(new ItemPedido(2, produto3, pedido1));

        Pedido pedido2 = new Pedido(cliente2);
        pedido2.adicionarItem(new ItemPedido(2, produto2, pedido2));
        pedido2.adicionarItem(new ItemPedido(2, produto1, pedido2));

        Pedido pedido3 = new Pedido(cliente2);
        pedido3.adicionarItem(new ItemPedido(5, produto3, pedido3));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido1);
        pedidoDAO.cadastrar(pedido2);
        pedidoDAO.cadastrar(pedido3);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.somarValorTotalVendido();
        System.out.println("Valor Total: " + totalVendido);

        List<RelatorioVendasVo> relatorio =  pedidoDAO.relatorioDeVendasVo();
        relatorio.forEach(System.out::println);
    }
}
