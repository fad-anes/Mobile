package tn.esprit.demande.gui;

//import com.codename1.components.MultiButton;
//import com.codename1.io.NetworkManager;
//import com.codename1.io.rest.RequestBuilder;
//import com.codename1.ui.Component;
//import com.codename1.ui.Container;
//import com.codename1.ui.Form;
//import com.codename1.ui.Label;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.list.DefaultListModel;
//import com.codename1.ui.list.ListCellRenderer;
//import java.util.ArrayList;
//import java.util.List;
//import tn.esprit.demande.entities.Proposition;
//import tn.esprit.demande.services.ServiceProposition;
//
//public class MesPropositions extends Form {
//    
//    private ArrayList<Proposition> propositionsList;
//    
//    public MesPropositions() {
//        super("My Propositions");
//        
//        // initialize the list view for displaying the propositions
//        final List<Proposition> list = new List<>();
//        list.setRenderer(new PropositionListRenderer());
//        list.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                Proposition proposition = list.getSelectedItem();
//                // handle selection of a proposition
//            }
//        });
//        
//        // add the list view to the form
//        addComponent(list);
//        
//        // fetch the propositions from the server and update the list view
//        NetworkManager.getInstance().addToQueueAndWait(new RequestBuilder().readPropositions(new ActionListener<ArrayList<Proposition>>() {
//            public void actionPerformed(ActionEvent evt) {
//                propositionsList = (ArrayList<Proposition>) evt.getSource();
//                if (propositionsList != null && !propositionsList.isEmpty()) {
//                    list.setModel(new DefaultListModel<>(propositionsList));
//                } else {
//                    list.setModel(new DefaultListModel<>(new Proposition[] { new Proposition(-1, "No propositions found") }));
//                }
//            }
//        }));
//    }
//    
//    private static class PropositionListRenderer extends Container implements ListCellRenderer<Proposition> {
//        
//        private final Label titleLabel;
//        private final Label nameLabel;
//        
//        public PropositionListRenderer() {
//            super(new BorderLayout());
//            titleLabel = new Label();
//            nameLabel = new Label();
//            addComponent(BorderLayout.CENTER, titleLabel);
//            addComponent(BorderLayout.SOUTH, nameLabel);
//        }
//        
//        public Component getListCellRendererComponent(List list, Proposition proposition, int index, boolean isSelected) {
//            titleLabel.setText(proposition.getTitre());
//            nameLabel.setText(proposition.getFirstName() + " " + proposition.getLastName());
//            return this;
//        }
//    }
//}
