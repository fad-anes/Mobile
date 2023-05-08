/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.services.ServiceDemande;
/**
 *
 * @author mohamed gabsi
 */
public class DemandeInfoForm extends Form {
    
    public DemandeInfoForm(Form previous, Demande demande) {
        super(demande.getTitre(), BoxLayout.y());

        // Create the UI components
        Label titreLabel = new Label(demande.getTitre());
        Label descLabel = new Label(demande.getDescription());
        Label salaireLabel = new Label(demande.getSalaire() + " DT");
        Label delaiLabel = new Label("Delai: " + demande.getDelai() + " jours");
        Label langageLabel = new Label("Langage: " + demande.getLangage());

        Button backButton = new Button("Back");
        backButton.addActionListener(evt -> previous.showBack());

       
        // Add the UI components to the form
        ComponentGroup componentGroup = ComponentGroup.enclose(
                titreLabel,
                descLabel,
                salaireLabel,
                delaiLabel,
                langageLabel
        );
        FlowLayout flowLayout = new FlowLayout(Component.CENTER, Component.CENTER);
        add(FlowLayout.encloseCenterMiddle(componentGroup));

        Container buttonsContainer = new Container(new GridLayout(1, 3));
        buttonsContainer.add(backButton);
        add(buttonsContainer);

        // Set the form's back command to the previous form
        setBackCommand(new BackCommand(previous));
    }

    private static class BackCommand extends com.codename1.ui.Command {
        private final Form previous;

        public BackCommand(Form previous) {
            super("Back");
            this.previous = previous;
        }

        @Override
        public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
            previous.showBack();
        }
    }
}
