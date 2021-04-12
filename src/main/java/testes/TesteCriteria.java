package testes;

import dao.ProdutoDAO;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class TesteCriteria {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        produtoDAO.buscaPorProdutoComCriteria(null, null, LocalDate.now());

    }


}
