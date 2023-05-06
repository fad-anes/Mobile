/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author abdes
 */

public  class Client extends Form {


  public Client(){
       
       // setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Toolbar toolbar = new Toolbar();
      setToolBar(toolbar);
     // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());

    toolbar.addCommandToLeftSideMenu("Ofre",null,(e) -> new ConnectionPage().show());
    toolbar.addCommandToLeftSideMenu("Demmande",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Reclamation",null,(e) -> new Inscription().show());
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);
 toolbar.addCommandToLeftSideMenu("Profile ",null,(e) -> new ProfilePage(this).show());
        // Create text fields for username and password
        toolbar.addCommandToLeftSideMenu("Log out ",null,(e) -> new Aceuil().show());
       

  }

 
    
}
