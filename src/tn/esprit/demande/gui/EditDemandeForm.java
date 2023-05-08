package tn.esprit.demande.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.services.ServiceDemande;
import com.codename1.ui.Form;
//import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class EditDemandeForm extends Form {
    private final Demande demande;

    public EditDemandeForm(Form previous, Demande demande) {
        super("Edit Demande", BoxLayout.y());

        this.demande = demande;

        // Create the UI components
        TextField titreField = new TextField(demande.getTitre(), "Titre");
        TextField descField = new TextField(demande.getDescription(), "Description");
        TextField salaireField = new TextField(String.valueOf(demande.getSalaire()), "Salaire", 10, TextField.NUMERIC);
        TextField delaiField = new TextField(String.valueOf(demande.getDelai()), "Delai");
        TextField langageField = new TextField(demande.getLangage(), "Langage");

      Button saveButton = new Button("Save");
saveButton.addActionListener(evt -> {
    demande.setTitre(titreField.getText());
    demande.setDescription(descField.getText());
    demande.setSalaire((float) Double.parseDouble(salaireField.getText()));
    demande.setDelai(delaiField.getText());
    demande.setLangage(langageField.getText());

    ServiceDemande.getInstance().modifierDemande(demande, demande.getId(), res -> {
        if (res.getResponseCode() == 200) {
            Dialog.show("Success", "Demande updated successfully", "OK", null);
            previous.showBack();
        } else {
            Dialog.show("Error", "Failed to update demande", "OK", null);
        }
    });
});
        Button returnButton = new Button("Return");
        returnButton.addActionListener(evt -> previous.showBack());

        // Add the UI components to the form
        add(titreField);
        add(descField);
        add(salaireField);
        add(delaiField);
        add(langageField);
        add(saveButton);
        add(returnButton);

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
