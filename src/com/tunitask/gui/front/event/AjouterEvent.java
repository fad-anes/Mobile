package com.tunitask.gui.front.event;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.tunitask.entities.Event;
import com.tunitask.services.EventService;

import java.io.IOException;

public class AjouterEvent extends Form {


    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;


    TextField nomTF;
    TextField lieuTF;
    TextField nbrPlaceTF;
    TextField imageTF;
    Label nomLabel;
    Label lieuLabel;
    Label nbrPlaceLabel;
    Label imageLabel;
    PickerComponent dateTF;


    ImageViewer imageIV;
    Button selectImageButton;

    Button manageButton;

    Form previous;

    public AjouterEvent(Form previous) {
        super("Ajouter", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {


        nomLabel = new Label("Nom : ");
        nomLabel.setUIID("labelDefault");
        nomTF = new TextField();
        nomTF.setHint("Tapez le nom");


        dateTF = PickerComponent.createDate(null).label("Date");


        lieuLabel = new Label("Lieu : ");
        lieuLabel.setUIID("labelDefault");
        lieuTF = new TextField();
        lieuTF.setHint("Tapez le lieu");


        nbrPlaceLabel = new Label("NbrPlace : ");
        nbrPlaceLabel.setUIID("labelDefault");
        nbrPlaceTF = new TextField();
        nbrPlaceTF.setHint("Tapez le nbrPlace");


        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");


        imageIV = new ImageViewer(theme.getImage("default.jpg").fill(1100, 500));


        manageButton = new Button("Ajouter");
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
                imageLabel, imageIV,
                selectImageButton,
                nomLabel, nomTF,
                dateTF,
                lieuLabel, lieuTF,
                nbrPlaceLabel, nbrPlaceTF,


                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });

        manageButton.addActionListener(action -> {
            if (controleDeSaisie()) {
                int responseCode = EventService.getInstance().add(
                        new Event(


                                nomTF.getText(),
                                dateTF.getPicker().getDate(),
                                lieuTF.getText(),
                                (int) Float.parseFloat(nbrPlaceTF.getText()),
                                selectedImage
                        )
                );
                if (responseCode == 200) {
                    Dialog.show("Succés", "Event ajouté avec succes", new Command("Ok"));
                    showBackAndRefresh();
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de event. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            }
        });
    }

    private void showBackAndRefresh() {
        ((AfficherToutEvent) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomTF.getText().equals("")) {
            Dialog.show("Avertissement", "Nom vide", new Command("Ok"));
            return false;
        }


        if (dateTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la date", new Command("Ok"));
            return false;
        }


        if (lieuTF.getText().equals("")) {
            Dialog.show("Avertissement", "Lieu vide", new Command("Ok"));
            return false;
        }


        if (nbrPlaceTF.getText().equals("")) {
            Dialog.show("Avertissement", "NbrPlace vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbrPlaceTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbrPlaceTF.getText() + " n'est pas un nombre valide (nbrPlace)", new Command("Ok"));
            return false;
        }


        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }


        return true;
    }
}