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
import com.mycompany.entities.Commentaire;
import com.mycompany.entities.Offre;
import com.mycompany.services.ServiceOffre;
import entites.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author anes_
 */
public class ajoutcommentaire extends BaseForm{
    
    Form current;
    public ajoutcommentaire(Resources res,int ido ) {
      
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
        
      
        TextField commentaire = new TextField("", "Entrer le commentaire!");
        commentaire.setUIID("TextFieldBlack");
        addStringValue("commentaire",commentaire);
        
        
        
        
        //////////
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                //int tel = Integer.parseInt(telephone.getText());
                if(commentaire.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                 
                }
                
                
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; 
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                   Commentaire c = new Commentaire(
                            
                            String.valueOf(commentaire.getText()).toString()
                           
                    );
                    
                    System.out.println("data  commentaire == "+c.toString());
                    
                    UserSession usersess= UserSession.getInstance();
                     
                    ServiceOffre.getInstance().ajoutcommentaire(c,ido,usersess.getUser().getId());
                    
                    
                    iDialog.dispose(); 
                    
                    
                    new voirscommentaires(res,ido).show();
                    
                    
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
        
    voirscommentaires a = new voirscommentaires(res,ido);
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
