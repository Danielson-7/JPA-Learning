/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation.controleur;

import entite.Groupe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presentation.vue.GroupeUI;
import service.GroupeService;

/**
 *
 * @author daniel
 */
public class GroupeControleur {
    
    private final GroupeService service;
    
    public GroupeControleur() {
        this.service = new GroupeService();   
    }
    
    public void ajouter(GroupeUI groupeUI) {
        Groupe groupe = new Groupe();
        GroupeUI popup = new GroupeUI(groupe, this);
        popup.chargerGroupes(service.lister());
        
        popup.getBtnEnregistrer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.modifier();
                Groupe g = popup.getGroupe();
                service.ajouter(g);
                popup.dispose();
                groupeUI.chargerGroupes(service.lister());
            }
        });
        popup.setVisible(true);
        
    }
    
    public void modifier(Groupe groupe, GroupeUI groupeUI) {
        if (groupe == null) {
            JOptionPane.showMessageDialog(null, "Aucun groupe sélectionné");
            return;
        }
        GroupeUI popup = new GroupeUI(groupe, this);
        popup.chargerGroupes(service.lister());
        popup.getBtnEnregistrer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.modifier();
                Groupe g = popup.getGroupe();
                service.modifier(g);
                popup.dispose();
                groupeUI.chargerGroupes(service.lister());
            }
        });
        popup.setVisible(true);
        
    }
    
    public void HomeGroupeUI() {
        Groupe groupe = new Groupe();
        GroupeUI groupeUI = new GroupeUI(groupe, this);
        groupeUI.chargerGroupes(service.lister());
        
        groupeUI.getBtnAjouter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouter(groupeUI);
            }
        });
        
        groupeUI.getBtnModifier().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Groupe g = groupeUI.getGpSelect();
                modifier(g, groupeUI);
            }
        });
        
        groupeUI.getBtnSupprimer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Groupe g = groupeUI.getGpSelect();
                if (g == null) {
                    JOptionPane.showMessageDialog(null, "Aucun groupe selectionné");
                    return;
                }
                supprimer(g.getId());
                groupeUI.chargerGroupes(service.lister());
            }
        });
        groupeUI.setVisible(true);
    }
    
    public void supprimer(Integer id) {
        service.supprimer(id);
        JOptionPane.showMessageDialog(null, "Groupe supprimé");
    }
    
    public void fermer() {
        this.service.fermer();
    }
}
