/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Offre;
import com.mycompany.services.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListOffreForm extends BaseForm{
    
    Form current;
    public ListOffreForm(Resources res ) {
        

super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mes offres");
        getContentPane().setScrollVisible(false);
        tb.getUnselectedStyle().setBgTransparency(255);
tb.getUnselectedStyle().setBgColor(0x000000);
tb.getUnselectedStyle().setBorder(Border.createEmpty());
tb.getSelectedStyle().setBgTransparency(255);
tb.getSelectedStyle().setBgColor(0x000000);
tb.getSelectedStyle().setBorder(Border.createEmpty());

        createLineSeparator();
      
        //Appel affichage methode
        ArrayList<Offre>list = ServiceOffre.getInstance().affichageOffres();
        
        for(Offre com : list ) {
             String urlImage ="logo.png";
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,com,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
         
// Définir les styles de boutons
Style boutonStyle = UIManager.getInstance().getComponentStyle("Button");
boutonStyle.setBgColor(0x4B0082);
boutonStyle.setFgColor(0xFFFFFF);
boutonStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
boutonStyle.setMargin(Component.BOTTOM, 3);

// Créer les boutons avec les styles définis
Button ajoutOffreBtn = new Button("Ajouter");
ajoutOffreBtn.setUIID("SelectBar");
ajoutOffreBtn.getAllStyles().merge(boutonStyle);

Button retour = new Button("Retour");
retour.setUIID("SelectBar");
retour.getAllStyles().merge(boutonStyle);

ajoutOffreBtn.addActionListener((e) -> {
    InfiniteProgress ip = new InfiniteProgress();
    final Dialog ipDlg = ip.showInifiniteBlocking();
        
    AjoutOffreForm a = new AjoutOffreForm(res);
    a.show();
    refreshTheme();
});

retour.addActionListener((e) -> {
    InfiniteProgress ip = new InfiniteProgress();
    final Dialog ipDlg = ip.showInifiniteBlocking();
        
    Listetousoffre a = new Listetousoffre(res);
    a.show();
    refreshTheme();
});

// Ajouter les boutons avec le GridLayout
add(LayeredLayout.encloseIn(
    GridLayout.encloseIn(3, ajoutOffreBtn, retour)
));

        
        
        
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
        
        swipe.addTab("",res.getImage("back.png"), page1);
        
        
        
        
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

    private void addButton(Image img,Offre com , Resources res) {
        
        // Set up the image button
    int height = Display.getInstance().convertToPixels(11.5f);
    int width = Display.getInstance().convertToPixels(14f);
    Button image = new Button(img.fill(width, height));
    image.setUIID("Label");
    Container cnt = BorderLayout.west(image);
        
    // Set up the text labels
    Label firstName = new Label("Nom: " + com.getFirstName(), "NewsTopLine2");
    Label lastName = new Label("Prenom: " + com.getLastName(), "NewsTopLine2");
    Label title = new Label(com.getTitre(), "NewsTopLine2Bold");
    Label description = new Label(com.getDescription(), "NewsTopLine2");
    Label salary = new Label(com.getSalaireh() + " DH", "NewsTopLine2Bold" );
        
        createLineSeparator();
 
       
        
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette commande ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceOffre.getInstance().deleteOffre(com.getIdoffre())) {
                    new ListOffreForm(res).show();
                }
           
        });
       
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(RIGHT);
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierOffreForm(res , com.getIdoffre()).show();
        });
        
        

        
        
        
        
        
        
        
        ////
        
        
       Container textCnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    textCnt.getStyle().setBgColor(0xEDEDED); // Set the background color
    textCnt.getStyle().setBgTransparency(255); // Make it opaque
    textCnt.getStyle().setBorder(Border.createLineBorder(1, 0xCCCCCC)); // Add a border
        
    // Add the text labels to the container
    textCnt.add(firstName);
    textCnt.add(lastName);
    
    textCnt.add(title);
    
    textCnt.add(description);
    
    textCnt.add(salary);
      textCnt.add(lSupprimer);  
      textCnt.add(lModifier); 
    // Add the container to the main container
    cnt.add(BorderLayout.CENTER, textCnt);
        
    add(cnt);
    }
    
   
   
}
