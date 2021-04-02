package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    //interface obrigatoria para a conexao de banco de dados
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("lojabruna");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }


}
