/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import java.util.*;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.mycompany.myapp.MyApplication;
import entites.Role;
import entites.Users;
import java.io.IOException;
import services.RoleService;

import services.UserService;

/**
 *
 * @author abdes
 */
public class UsersList extends Form{
  public static  List<Role> list ;
  Container main ;
    public UsersList(){
    setTitle("liste de users");
   //setLayout(BoxLayout.y());
   main = new Container(BoxLayout.y());
         Toolbar toolbar = new Toolbar();
      setToolBar(toolbar);
     // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());
    toolbar.addCommandToLeftSideMenu("Users",null,(e) -> new UsersList().show());
    toolbar.addCommandToLeftSideMenu("Ofre",null,(e) -> new ConnectionPage().show());
    toolbar.addCommandToLeftSideMenu("Proposition",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Quiz",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Events",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Reclamation",null,(e) -> new Inscription().show());
        toolbar.addCommandToLeftSideMenu("Profile",null,(e) -> new ProfilePage(this).show());
        toolbar.addCommandToLeftSideMenu("Log out ",null,(e) -> new Aceuil().show());
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);
 String[] roles = {"Client", "Freelancer","Admin"};
    ComboBox<String> roleComboBox = new ComboBox<>(roles);
     Container cn = new Container(BoxLayout.x());
     Button  filter = new Button("filter");
     
        cn.addAll(roleComboBox,filter);
        add(cn);
      filter.addActionListener(evt -> {
          //Dialog.show("Connection", "You have to make an account!"  , "OK", null);
          removeComponent(main);
            main = new Container(BoxLayout.y());
      
       
                RoleService rs = new RoleService();
               
                 ArrayList<Role> list1 =rs.parseFilter((ArrayList<Role>)UsersList.list ,roleComboBox.getSelectedItem());
      

               for (int i=0; i<list1.size();i++)
                  {
                         //addElement(UsersList.list.get(i));
                      System.out.println("ok"+list.get(i).getIdUser().getId());
                            String url ="http://127.0.0.1/img/"+ list.get(i).getIdUser().getSrcimage();
        System.out.println(url);
      Image img = MyApplication.theme.getImage("logo.png");
    EncodedImage enc = EncodedImage.createFromImage(img,false);
   img=URLImage.createToStorage(enc,list.get(i).getIdUser().getSrcimage(),url).scaled(300, 400);
    ImageViewer imgp= new ImageViewer(img);
   
   
       main.addAll( addElement(list1.get(i)),imgp);
    
    
                  }

                add(main);
            }
            
        );
      

       ConnectionRequest req = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/controle/json/roles";
        System.out.println(url);
        //ConnectionRequest.setCertificateValidation(false);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Boolean result = req.getResponseCode() == 200; //Code HTTP 200 OK
                     String response = new String(req.getResponseData());
                req.removeResponseListener(this);
                RoleService rs = new RoleService();
                System.out.println(rs.parseRoles1(response));
                 UsersList.list =rs.parseRoles1(response);
      

               for (int i=0; i<UsersList.list.size();i++)
                  {
                         //addElement(UsersList.list.get(i));
                      System.out.println("ok"+list.get(i).getIdUser().getId());
                            String url ="http://127.0.0.1/img/"+ list.get(i).getIdUser().getSrcimage();
        System.out.println(url);
      Image img = MyApplication.theme.getImage("logo.png");
    EncodedImage enc = EncodedImage.createFromImage(img,false);
   img=URLImage.createToStorage(enc,list.get(i).getIdUser().getSrcimage(),url).scaled(300, 400);
    ImageViewer imgp= new ImageViewer(img);
     Button btn = new Button("block");
   
       main.addAll( addElement(list.get(i)),imgp,btn);
    
    
                  }

                
            }
        });
    
        NetworkManager.getInstance().addToQueue(req);
   

     addAll(main);

    }
    public Container addElement (Role u){
  
    
   
     Container cn1 = new Container (BoxLayout.x());
     //ImageViewer imgUser=new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
     Container cn2= new Container (BoxLayout.y());
     Label email= new Label(u.getIdUser().getEmail());
       Label name= new Label(u.getIdUser().getLastName()+"\t"+" "+ "\t"+u.getIdUser().getFirstName());
       // System.out.println(Integer.toString(u.getId()));
     Label id= new Label(Integer.toString(u.getIdUser().getId()));  
     Label role= new Label(u.getRoleName()); 
    // email.setSize(new Dimension(10,10));
     id.setSize(new Dimension(10,10));
     cn2.addAll(id,email,name,role);
    
     cn1.addAll(cn2);
      return cn1;
    }
   
}
