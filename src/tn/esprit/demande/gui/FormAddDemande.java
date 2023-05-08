package tn.esprit.demande.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.services.ServiceDemande;

public class FormAddDemande extends Form {

    private final TextField titreField;
    private final TextField descriptionField;
    private final TextField salaireField;
    private final TextField delaiField;
    private final TextField langageField;

    private final Button addButton;

    public FormAddDemande(Form previous) {
        super("Add Demande", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        titreField = new TextField("", "Titre", 20, TextField.ANY);
        descriptionField = new TextField("", "Description", 20, TextField.ANY);
        salaireField = new TextField("", "Salaire", 20, TextField.NUMERIC);
        delaiField = new TextField("", "Delai", 20, TextField.ANY);
        langageField = new TextField("", "Langage", 20, TextField.ANY);

        addButton = new Button("Add");

        Validator validator = new Validator();
        validator.addConstraint(titreField, new LengthConstraint(1));
        validator.addConstraint(descriptionField, new LengthConstraint(1));
        validator.addConstraint(salaireField, new NumericConstraint(true));

        addButton.addActionListener(e -> {
            if (validator.isValid()) {
                Demande demande = new Demande();
                demande.setTitre(titreField.getText());
                demande.setDescription(descriptionField.getText());
                demande.setSalaire(Float.parseFloat(salaireField.getText()));
                demande.setDelai(delaiField.getText());
                demande.setLangage(langageField.getText());

                ServiceDemande.getInstance().ajoutDemande(demande);

                Dialog.show("Success", "Demande added successfully", "OK", null);
                titreField.clear();
                descriptionField.clear();
                salaireField.clear();
                delaiField.clear();
                langageField.clear();
            } else {
                Dialog.show("Error", "Please fill in all required fields correctly", "OK", null);
            }
        });

        add(new Label("Titre"));
        add(titreField);
        add(new Label("Description"));
        add(descriptionField);
        add(new Label("Salaire"));
        add(salaireField);
        add(new Label("Delai"));
        add(delaiField);
        add(new Label("Langage"));
        add(langageField);
        add(FlowLayout.encloseCenter(addButton));
    }
}
