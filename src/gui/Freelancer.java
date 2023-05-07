/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import static com.mycompany.myapp.MyApplication.theme;
import com.mycompany.myapp.offre.Listetousoffre;

/**
 *
 * @author abdes
 */

public class Freelancer extends Form {

 
  public  Freelancer(){
       
       // setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Toolbar toolbar = new Toolbar();
      setToolBar(toolbar);
     // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());

    toolbar.addCommandToLeftSideMenu("Offre",null,(e) ->  new Listetousoffre(theme).show());
    toolbar.addCommandToLeftSideMenu("Proposition",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Quiz",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Events",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Reclamation",null,(e) -> new Inscription().show());
         toolbar.addCommandToLeftSideMenu("Profile ",null,(e) -> new ProfilePage(this).show());
        toolbar.addCommandToLeftSideMenu("Log out ",null,(e) -> new Aceuil().show());
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);
        // Create text fields for username and password
       
 
  }
    
}
