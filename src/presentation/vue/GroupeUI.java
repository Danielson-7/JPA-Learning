/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation.vue;

import entite.Groupe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import presentation.controleur.GroupeControleur;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author daniel
 */
public class GroupeUI extends JFrame {

    private final Groupe groupe;
    private final GroupeControleur groupeControleur;

    private final JTextField nom;
    private final JTextArea description;
    private final JButton btnEnregistrer;
    private final DefaultTableModel Model;
    private final JTable table;

    private List<Groupe> listeGroupes;
    private JButton btnAjouter = new JButton("Ajouter");
    private JButton btnModifier = new JButton(" Modifier");
    private JButton btnSupprimer = new JButton("Supprimer");

    public GroupeUI(Groupe groupe, GroupeControleur groupeControleur) {
        this.groupe = groupe;
        this.groupeControleur = groupeControleur;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1024, 1024);
        this.setTitle("Gestion des Groupes");
        this.setLocationRelativeTo(null);

        JPanel panelH = new JPanel(new BorderLayout(5, 5));
        JLabel Titre = new JLabel("Liste des Groupes", JLabel.CENTER);
        Titre.setForeground(Color.BLUE);
        Titre.setFont(new Font("sansserif", Font.BOLD, 24));
        panelH.add(Titre, BorderLayout.NORTH);

        this.setLayout(new BorderLayout(10, 10));
        String[] Tableau = {"ID", "Nom", "Description"};
        Model = new DefaultTableModel(Tableau, 0) {
            @Override
            public boolean isCellEditable(int ligne, int col) {
                return false;
            }
        };

        table = new JTable(Model);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        panelH.add(scrollPane, BorderLayout.CENTER);
        this.add(panelH, BorderLayout.NORTH);
        JPanel panelBtn = new JPanel();
        panelBtn.add(btnAjouter);
        panelBtn.add(btnModifier);
        panelBtn.add(btnSupprimer);
        this.add(panelBtn, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        layout.setConstraints(panel, c);
        panel.setLayout(layout);

        nom = new JTextField(40);
        description = new JTextArea(5, 40);
        description.setBorder(nom.getBorder());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setBackground(Color.ORANGE);

        btnModifier.setForeground(Color.WHITE);
        btnSupprimer.setForeground(Color.WHITE);
        btnAjouter.setBackground(Color.GREEN);
        btnModifier.setBackground(Color.BLUE);
        btnSupprimer.setBackground(Color.RED);

        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Nom : "), c);

        c.gridx = 1;
        panel.add(nom, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Description : "), c);;

        c.gridx = 1;
        panel.add(description, c);

        c.gridx = 1;
        c.gridy = 2;
        panel.add(btnEnregistrer, c);

        this.add(panel, BorderLayout.CENTER);
        this.afficher();
    }

    public void chargerGroupes(List<Groupe> groupe) {
        this.listeGroupes = groupe;
        Model.setRowCount(0);
        if (groupe == null) {
            return;
        }
        for (Groupe g : groupe) {
            Object[] ligne = {g.getId(), g.getNom(), g.getDescription()
            };
            Model.addRow(ligne);
        }
    }

    public Groupe getGpSelect() {
        int ligne = table.getSelectedRow();
        if (ligne != -1 && listeGroupes != null) {
            return listeGroupes.get(ligne);
        }
        return null;
    }

    public void modifier() {
        this.groupe.setNom(nom.getText());
        this.groupe.setDescription(description.getText());
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnModifier() {
        return btnModifier;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public JButton getBtnEnregistrer() {
        return btnEnregistrer;
    }

    private void afficher() {
        this.nom.setText(this.groupe.getNom());
        this.description.setText(this.groupe.getDescription());
    }
}
