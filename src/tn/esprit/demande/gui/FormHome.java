/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import tn.esprit.demande.MyApplication;

/**
 *
 * @author mohamed gabsi
 */
public class FormHome extends Form {
    
    public FormHome(Form previous) {
        setTitle("Home");
        setLayout(new FlowLayout(CENTER, CENTER));
        

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_LOGOUT, ev -> { previous.showBack();});
        
        getToolbar().addCommandToSideMenu("Demandes", null, ev->{ new FormAddDemande(this).show(); });
       getToolbar().addCommandToSideMenu("showdemandes", null, ev->{ new AfficherMyDemandes(this).show(); });
       getToolbar().addCommandToSideMenu("showalldemandes", null, ev->{ new ListeDesDemandes(this).show(); });
       //getToolbar().addCommandToSideMenu("showpropositions", null, ev->{ new MesPropositions(this).show(); });
       getToolbar().addCommandToSideMenu("Demande Salaries", null, ev->{ new statForm(this).show(); });
       
       ImageViewer tunitask = new ImageViewer(MyApplication.theme.getImage("tunitaskimg.png"));
       
       Container cn1 = new Container(new FlowLayout(CENTER, CENTER));
       cn1.addAll(tunitask);
       add(cn1); 
    
       // Label lbMessage = new Label("Welcome !");
       // add(lbMessage);
    }
    
}
