/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.GroupeDao;
import entite.Groupe;
import java.util.List;

/**
 *
 * @author daniel
 */
public class GroupeService {

    private final GroupeDao dao;

    public GroupeService() {
        this.dao = new GroupeDao();
    }

    public void ajouter(Groupe groupe) {
        this.dao.ajouter(groupe);
    }

    public Groupe modifier(Groupe groupe) {
        return dao.modifier(groupe);
    }

    public List<Groupe> lister() {
        return dao.lister();
    }

    public void supprimer(Integer id) {
        this.dao.supprimer(id);
    }

    public void fermer() {
        this.dao.fermer();
    }
}
