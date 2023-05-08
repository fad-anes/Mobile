/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author mohamed gabsi
 */
public class FormDetails extends Form {
    
    public FormDetails(Form previous, String titre, String description, String salaire, String delai, String language) {
        
        setTitle("Details");
        setLayout(BoxLayout.y());
        
        getToolbar().addCommandToLeftBar("return", null, ev->{
            previous.showBack();
        });
        
        Label lbTitre = new Label(titre);
        Label lbDescription = new Label(description);
        Label lbSalaire = new Label(salaire);
        Label lbDelai = new Label(delai);
        Label lbLanguage = new Label(language);
        
        addAll(lbTitre, lbDescription, lbSalaire, lbDelai, lbLanguage);
        
        
    }
    
    
}
