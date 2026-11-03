/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import entite.Groupe;
import entite.Utilisateur;

/**
 *
 * @author daniel
 */
public class JPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  test2();
    }
        //dans la couche DA0
         private static void test1(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();

        Groupe g1 = new Groupe("Groupe 1", "Premier groupe");

        
        EntityTransaction transaction = em.getTransaction();
        
        try {
            em.persist(g1);
            transaction.begin();

        } catch (Exception e) {
            transaction.rollback();
        }
        
        
        
        }
        
        private static void test2(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();

        Groupe g1 = new Groupe("Groupe 2", "deuxieme groupe");
        Utilisateur utilisateur = new Utilisateur("YANDJA", "Tilate Daniel","TD","9664",g1);
        
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(utilisateur);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
        
        
        
        }

    

}
