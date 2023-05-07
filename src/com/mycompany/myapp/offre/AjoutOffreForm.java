/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.offre;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Offre;
import com.mycompany.services.ServiceOffre;
import entites.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class AjoutOffreForm extends BaseForm {
    
    
    Form current;
    public AjoutOffreForm(Resources res ) {
      
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter offres");
        getContentPane().setScrollVisible(false);
        tb.getUnselectedStyle().setBgTransparency(255);
tb.getUnselectedStyle().setBgColor(0x000000);
tb.getUnselectedStyle().setBorder(Border.createEmpty());
tb.getSelectedStyle().setBgTransparency(255);
tb.getSelectedStyle().setBgColor(0x000000);
tb.getSelectedStyle().setBorder(Border.createEmpty());
        
      
        TextField titre = new TextField("", "Entrer le titre du votre offre!");
        titre.setUIID("TextFieldBlack");
        addStringValue("titre",titre);
        
        
        TextField description = new TextField("", "Entrer la description du votre offre!");
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);
        
        
        TextField salaireh = new TextField("", "entrer votre prix d offre",8,TextField.NUMERIC);
        salaireh.setUIID("TextFieldBlack");
        addStringValue("salaireh",salaireh);
        
        //////////
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                //int tel = Integer.parseInt(telephone.getText());
                if(titre.getText().equals("") || description.getText().equals("")|| salaireh.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                 
                }
                
                
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; 
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                   Offre c = new Offre(
                            
                            String.valueOf(description.getText()).toString(),
                            String.valueOf(titre.getText()).toString(),
                           Integer.parseInt( salaireh.getText())
                    );
                    
                    System.out.println("data  Offre == "+c.toString());
                    
                     UserSession usersess= UserSession.getInstance();
                     
                    ServiceOffre.getInstance().ajoutOffre(c,usersess.getUser().getId());
                    
                    
                    iDialog.dispose(); 
                    
                    
                    new ListOffreForm(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
        Button ajoutOffreBtn = new Button("Retour");
ajoutOffreBtn.setUIID("SelectBar");

ajoutOffreBtn.addActionListener((e) -> {
    InfiniteProgress ip = new InfiniteProgress();
    final Dialog ipDlg = ip.showInifiniteBlocking();
        
    ListOffreForm a = new ListOffreForm(res);
    a.show();
    refreshTheme();
});



add(LayeredLayout.encloseIn(
    GridLayout.encloseIn(3, ajoutOffreBtn)
    
));
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("salle-back.jpg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
   
   
    
}
