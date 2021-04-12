package testes;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class PerformanceConsultas {

    //iger= carregamento antecipado
    //lazy carregamento tardio

    public static void main(String[] args) {
        popularDatabase();

        EntityManager em = JPAUtil.getEntityManager();

        PedidoDAO pedidoDAO = new PedidoDAO(em);

        Pedido pedido = pedidoDAO.buscarPedidoComCliente(1l);

        em.close();

        System.out.println("Nome do cliente é: " + pedido.getCliente().getNomeCliente());

        // Pedido pedido = em.find(Pedido.class, 1L);

        //metodo para continuar usando lazy mas carregar os relacionamentos antes query planejada



//        System.out.println("O Id do pedido é: " + pedido.getId());
//        System.out.println("A data do pedido é: " + pedido.getDataPedido());
//        System.out.println("O cliente do pedido é: " + pedido.getCliente().getNomeCliente());

    }

    private static void popularDatabase(){

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        em.getTransaction().begin();

        Produto iphone = produtoDAO.buscarPorId(1L);
        Produto mac = produtoDAO.buscarPorId(2L);
        Cliente bruna = clienteDAO.buscarPorId(1L);

        Pedido pedido1 = new Pedido(bruna);
        pedido1.adicionarItem(new ItemPedido(10, iphone, pedido1));
        pedido1.adicionarItem(new ItemPedido(5, mac, pedido1));

        pedidoDAO.cadastrar(pedido1);

        em.getTransaction().commit();



    }
}
