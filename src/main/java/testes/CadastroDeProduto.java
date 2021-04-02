package testes;

import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {

        Produto celular = new Produto();
        celular.setNome("Iphone 12pro");
        celular.setDescricao("256gb");
        celular.setPreco(new BigDecimal("1200"));

        //interface obrigatoria para a conexao de banco de dados
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lojabruna");
        EntityManager em = factory.createEntityManager();

        //inserir obj no banco de dados
        em.getTransaction().begin();
        em.persist(celular);
        em.getTransaction().commit();
        em.close();
    }
}
