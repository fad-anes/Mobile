/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompanyquiz.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.entities.Quizs;
import mycompanyquiz.services.ServiceQuizs;


/**
 *
 * @author Ahmed
 */
public class addQuiz extends Form {
    public addQuiz(Form previous){
        getAllStyles().setBgColor(0xebfff8);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        setTitle("Gestion de Station");
        //UIBuilder uib1 =new UIBuilder();
        //setLayout(new FlowLayout(CENTER, CENTER));
        setLayout(BoxLayout.y());
//        add(new Label("ma liste de station"));
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceStation.getInstance().getAllStations().toString());
//        add(sp);
//        
        TextField tfName1 = new TextField("","title");
        TextField tfName2 = new TextField("","Description");
        TextField tfName3 = new TextField("","score");
        Button btnValider = new Button("Add quiz");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName1.getText().length()==0)||(tfName2.getText().length()==0)||(tfName3.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                     
                        Quizs s = new Quizs(tfName1.getText(),tfName2.getText(),    Integer.parseInt(tfName3.getText()));
                        if( ServiceQuizs.getInstance().addTask(s))
                        {
                           Dialog.show("Success","quiz added",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName1,tfName2,tfName3,btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
