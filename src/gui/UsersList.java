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
import entites.Users;
import java.io.IOException;

import services.UserService;

/**
 *
 * @author abdes
 */
public class UsersList extends Form{
  public static  List<Users> list ;
    public UsersList(){
    setTitle("liste de users");
   setLayout(BoxLayout.y());
          Toolbar toolbar = new Toolbar();
      
    setToolBar(toolbar); // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());

    toolbar.addCommandToLeftSideMenu("Users",null,(e) -> new UsersList().show());
    toolbar.addCommandToLeftSideMenu("Profile",null,(e) -> new ProfilePage().show());
   
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);

  ConnectionRequest req = new ConnectionRequest();
        String url = "http://127.0.0.1:8000/controle/json/userlist";
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
                UserService us = new UserService();
                System.out.println(us.parseUsers(response));
                 UsersList.list =us.parseUsers(response);
      

               for (int i=0; i<UsersList.list.size();i++)
                  {
                         //addElement(UsersList.list.get(i));
                      System.out.println("ok"+list.get(i).getId());
                            String url ="http://127.0.0.1/img/"+ list.get(i).getSrcimage();
        System.out.println(url);
      Image img = MyApplication.theme.getImage("logo.png");
    EncodedImage enc = EncodedImage.createFromImage(img,false);
   img=URLImage.createToStorage(enc,list.get(i).getSrcimage(),url).scaled(300, 400);
    ImageViewer imgp= new ImageViewer(img);
     Button btn = new Button("block");
     ArrayList listbtn = new ArrayList<Button>();
     listbtn.add(btn);
       addAll( addElement(list.get(i)),imgp,btn);
    
    
                  }

                
            }
        });
    
        NetworkManager.getInstance().addToQueue(req);
   
  /*for (int i=0; i<UsersList.list.size();i++)
                  {
                         addElement(UsersList.list.get(i));
                      //System.out.println(list.get(i));
  
    
    
                  }*/
      /*Container cn1 = new Container (BoxLayout.x());
     ImageViewer imgUser=new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
     Container cn2= new Container (BoxLayout.y());
     Label email= new Label("test");
     Label id= new Label(Integer.toString(3));  
     email.setSize(new Dimension(10,10));
     id.setSize(new Dimension(10,10));
     cn2.addAll(id,email);
     cn1.addAll(imgUser,cn2);
     add(cn1);*/
   /*List<Users> us= new ArrayList();
    UserService s= new UserService();
    System.out.println(s);
        try {
            us= (ArrayList)s.afficherUsers();
            System.out.println(us);
                //us.forEach((Users e)  ->add( addElement(e)));
                for(int i=0;i< us.size();i++)
                {
                    System.out.println(us.get(i));
                    add(addElement(us.get(i)));}
        } catch (IOException ex) {
            System.out.println("problem");
        }
*/
    //addAll(addElement(s.afficherUsers()));
 

    }
    public Container addElement (Users u){
  
    
   
     Container cn1 = new Container (BoxLayout.x());
     //ImageViewer imgUser=new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(300, 400));
     Container cn2= new Container (BoxLayout.y());
     Label email= new Label(u.getEmail());
       Label name= new Label(u.getLastName()+"\t"+" "+ "\t"+u.getFirstName());
       // System.out.println(Integer.toString(u.getId()));
     Label id= new Label(Integer.toString(u.getId()));  
    
    // email.setSize(new Dimension(10,10));
     id.setSize(new Dimension(10,10));
     cn2.addAll(id,email,name);
    
     cn1.addAll(cn2);
      return cn1;
    }
   
}
