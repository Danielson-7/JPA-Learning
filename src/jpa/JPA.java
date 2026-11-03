/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jpa;
import presentation.controleur.GroupeControleur;
import presentation.controleur.UtilisateurControleur;

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

        GroupeControleur controleurgroupe = new GroupeControleur();
        UtilisateurControleur controleurUser = new UtilisateurControleur();
 
        
        
       controleurgroupe.HomeGroupeUI();
      //controleurUser.HomeUserUI();

    }
}

