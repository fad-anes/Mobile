/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.services.ServiceDemande;
import java.util.ArrayList;
/**
 *
 * @author mohamed gabsi
 */
public class ListeDesDemandes extends Form {
    
    public ListeDesDemandes(Form previous) {
        super("Les Demandes", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        // Load user's demands
        ArrayList<Demande> lesDemandes = ServiceDemande.getInstance().Affichetous();

        // Create a ComponentGroup to hold the list of demands
        ComponentGroup group = new ComponentGroup();

        // Add each demand as a MultiButton to the ComponentGroup
        for (Demande demande : lesDemandes) {
            MultiButton mb = new MultiButton(demande.getTitre());
            mb.setTextLine2(demande.getDescription());
            mb.setTextLine3(demande.getSalaire() + " DT");
            group.addComponent(mb);
            mb.addActionListener(e -> {
                // Show details of the selected demand
                new DemandeInfoForm(this, demande).show();
            });
        }

        // Add the ComponentGroup to the form
        add(BorderLayout.center(group));

        // Set the form's back command to the previous form
        setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        });
    }
    
}
