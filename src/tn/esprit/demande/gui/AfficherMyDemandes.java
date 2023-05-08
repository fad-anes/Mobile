 package tn.esprit.demande.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.HashSet;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.services.ServiceDemande;

public class AfficherMyDemandes extends Form {

    // Define a TextField to use for the search filter
    private TextField searchField = new TextField("", "Search");

    public AfficherMyDemandes(Form previous) {
        super("My Demands", new BorderLayout());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        
        // Load user's demands
        ArrayList<Demande> mesDemandes = ServiceDemande.getInstance().afficherDemandes();
        
        // Create a ComponentGroup to hold the list of demands
        ComponentGroup group = new ComponentGroup();
        for (Demande demande : mesDemandes) {
            MultiButton mb = new MultiButton(demande.getTitre());
            mb.setTextLine2(demande.getDescription());
            mb.setTextLine3(demande.getSalaire() + " DT");
            group.addComponent(mb);
            mb.addActionListener(e -> {
                // Show details of the selected demand
                new DemandeDetailsForm(this, demande).show();
            });
        }
        
        // Create a container to hold the ComponentGroup with a scrollbar
        Container scrollableContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        scrollableContainer.setScrollableY(true);
        scrollableContainer.addComponent(group);

        // Add the scrollable container to the form
        add(BorderLayout.CENTER, scrollableContainer);

        // Add the search field to the form
        add(BorderLayout.NORTH, searchField);
        
        // Set the listener to filter the demands when the search text changes
        searchField.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                filterDemands(searchField.getText().toLowerCase(), group, mesDemandes);
            }
        });

        // Set the form's back command to the previous form
        setBackCommand(new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        });
    }

    // Define a method to filter the demands based on the search text
    private void filterDemands(String searchText, ComponentGroup group, ArrayList<Demande> mesDemandes) {
        // Create a HashSet to store the filtered demands
        HashSet<Demande> filteredDemandes = new HashSet<>();

        // Filter the demands based on the search text
        for (Demande demande : mesDemandes) {
            if (demande.getTitre().toLowerCase().contains(searchText)) {
                filteredDemandes.add(demande);
            }
        }

        // Clear the ComponentGroup
        group.removeAll();

        // Add each filtered demand as a MultiButton to the ComponentGroup
        for (Demande demande : filteredDemandes) {
            MultiButton mb = new MultiButton(demande.getTitre());
            mb.setTextLine2(demande.getDescription());
mb.setTextLine3(demande.getSalaire() + " DT");
group.addComponent(mb);
mb.addActionListener(e -> {
// Show details of the selected demand
new DemandeDetailsForm(this, demande).show();
});
}// Repaint the form to update the filtered demands
    repaint();
}
}