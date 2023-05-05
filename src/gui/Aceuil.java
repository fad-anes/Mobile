/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.codename1.components.ImageViewer;
import com.codename1.ui.Command;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author abdes
 */
public class Aceuil extends Form {
  public Aceuil() {
    setTitle("Acceuil");
   
    Image img = MyApplication.theme.getImage("logo.png");
    ImageViewer imglogo = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(800, 800));
          
    Toolbar toolbar = new Toolbar();
    setToolBar(toolbar); // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());

    toolbar.addCommandToLeftSideMenu("Connect",null,(e) -> new ConnectionPage().show());
    toolbar.addCommandToLeftSideMenu("Inscription",null,(e) -> new Inscription().show());
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);
    Container cn1 = new Container(new FlowLayout(CENTER,CENTER));
cn1.setUIID("Container");
cn1.setPreferredH(getHeight() - getToolbar().getHeight() - 200 );
cn1.setPreferredW(getWidth());
    cn1.addAll(imglogo);
     // Add "Connect" button to toolbar
//inscriptionCmd.addActionListener((e) -> new ConnectionPage().show());
/*
////// Add "Inscription" button to toolbar

inscriptionCmd.addActionListener((e) -> new InscriptionPage().show());*/
    addAll(cn1);
}


    private void setLayout(BoxLayout y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
