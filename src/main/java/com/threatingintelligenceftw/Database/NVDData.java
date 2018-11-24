package com.threatingintelligenceftw.Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class NVDData {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static void openDatabase() {
        emf = Persistence.createEntityManagerFactory("nvd.odb");
        em = emf.createEntityManager();
    }

    private static void closeDatabase() {
        em.close();
        emf.close();
    }

    public static void saveData(List<NVDCVE> data) {
        openDatabase();
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
        closeDatabase();
    }

    public static List<NVDCVE> getVulnerabilities() {
        openDatabase();
        em.getTransaction().begin();
        TypedQuery<NVDCVE> query = em.createQuery("SELECT s FROM NVDCVE s WHERE s instanceof Score", NVDCVE.class);

        return query.getResultList();
    }
}
