package com.tunitask.gui.front.reclamation;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.tunitask.entities.Reclamation;
import com.tunitask.entities.User;
import com.tunitask.services.ReclamationService;
import com.tunitask.services.UserService;
import entites.UserSession;

import java.util.ArrayList;

public class ModifierReclamation extends Form {


    Reclamation currentReclamation;

    TextField nomTF;
    TextField prenomTF;
    TextField emailTF;
    TextField descriptionTF;
    TextField etatTF;
    Label nomLabel;
    Label prenomLabel;
    Label emailLabel;
    Label descriptionLabel;
    Label etatLabel;


    


    Button manageButton;

    Form previous;

    public ModifierReclamation(Form previous) {
        super("Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReclamation = AfficherToutReclamation.currentReclamation;

        addGUIs();
        addActions();


        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

     


        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");


        prenomLabel = new Label("Prenom : ");
        prenomLabel.setUIID("labelDefault");
        prenomTF = new TextField();
        prenomTF.setHint("Tapez le prenom");


        emailLabel = new Label("Email : ");
        emailLabel.setUIID("labelDefault");
        emailTF = new TextField();
        emailTF.setHint("Tapez le email");


        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");


        etatLabel = new Label("Etat : ");
        etatLabel.setUIID("labelDefault");
        etatTF = new TextField();
        etatTF.setHint("Tapez le etat");


        nomTF.setText(currentReclamation.getNom());
        prenomTF.setText(currentReclamation.getPrenom());
        emailTF.setText(currentReclamation.getEmail());
        descriptionTF.setText(currentReclamation.getDescription());
        etatTF.setText(currentReclamation.getEtat());

        

        manageButton = new Button("Modifier");
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomLabel, nomTF,
                prenomLabel, prenomTF,
                emailLabel, emailTF,
                descriptionLabel, descriptionTF,
                etatLabel, etatTF,
                
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
UserSession usersess= UserSession.getInstance();
        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = ReclamationService.getInstance().edit(
                        new Reclamation(
                                currentReclamation.getId(),


                                
                                nomTF.getText(),
                                prenomTF.getText(),
                                emailTF.getText(),
                                descriptionTF.getText(),
                                etatTF.getText()

                        ),usersess.getUser().getId()
                );
                if (responseCode == 200) {
                    Dialog.show("Succés", "Reclamation modifié avec succes", new Command("Ok"));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur de modification de reclamation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutReclamation) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Nom vide", new Command("Ok"));
            return false;
        }


        if (prenomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Prenom vide", new Command("Ok"));
            return false;
        }


        if (emailTF.getText().equals("")) {
            Dialog.show("Avertissement", "Email vide", new Command("Ok"));
            return false;
        }


        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }


        if (etatTF.getText().equals("")) {
            Dialog.show("Avertissement", "Etat vide", new Command("Ok"));
            return false;
        }


       


        return true;
    }
}