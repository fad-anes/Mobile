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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Offre;
import com.mycompany.services.ServiceOffre;
import com.mycompany.entities.Commentaire;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
/**
 *
 * @author anes_
 */
public class modifiercommentaire extends BaseForm{
    
    
    Form current;
    public modifiercommentaire(Resources res, int id ) {
      
        
        Commentaire Commentaire =new Commentaire();
         Commentaire = ServiceOffre.getInstance().recuperecommentaire(id);
        
        
        
      
        TextField commentaire = new TextField("", "Entrer le commentaire!");
        commentaire.setUIID("TextFieldBlack");
        addStringValue("commentaire",commentaire);
        commentaire.setText(String.valueOf(Commentaire.getCommentaire()));
        
       

        
        //////////
        Button btnAjouter = new Button("Modifier");
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
                    
                    System.out.println("data  Commentaire == "+c.toString());
                    
                    
                     
                    ServiceOffre.getInstance().modifierCommentaire(c,id);
                    
                    
                    iDialog.dispose(); 
                    
                    
                    new mescommentaire(res,id).show();
                    
                    
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
        
    mescommentaire a = new mescommentaire(res,id);
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
