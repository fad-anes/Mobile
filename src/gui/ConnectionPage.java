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
import com.codename1.ui.*;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.MyApplication;
import entites.Role;
import entites.UserSession;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import services.RoleService;


/**
 *
 * @author abdes
 */
public class ConnectionPage extends Form {
    public ConnectionPage() {
    setTitle("Login");
    setLayout(BoxLayout.y());
       // setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         Toolbar toolbar = new Toolbar();
      
    setToolBar(toolbar); // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");
toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());

    toolbar.addCommandToLeftSideMenu("Connect",null,(e) -> new ConnectionPage().show());
    toolbar.addCommandToLeftSideMenu("Inscription",null,(e) -> new Inscription().show());
    Label title = new Label("TuniTask");
toolbar.setTitleComponent(title);
        // Create text fields for username and password
        TextField usernameField = new TextField("", "Username", 15, TextField.ANY);
        TextField passwordField = new TextField("", "Password", 15, TextField.PASSWORD);
        
        // Create login button
        Button loginButton = new Button("Login");
        loginButton.addActionListener(evt -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
           // Dialog.show("test",username + password,"ok",null);
           ConnectionRequest req = new ConnectionRequest();
            String baseUrl = "http://127.0.0.1:8000";
String endpoint;
        // Set the endpoint for the desired route, with the email and pwd parameters
        
        try {
            endpoint = "/controle/json/connect/" + username + "/" + password;
             String url = baseUrl + endpoint;
             
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Boolean result = req.getResponseCode() == 200; //Code HTTP 200 OK
                 String response = new String(req.getResponseData());
                 System.out.println(response);
                 if(response.equals("{\"connect\":\"error\"}"))
                 { Dialog.show("Connection", "You have to make an account!"  , "OK", null);}
                 else{
                 System.out.println(new RoleService().parseRoles(response));
                 ArrayList<Role> roles=new RoleService().parseRoles(response);
                 UserSession usersess= UserSession.getInstance();
                 usersess.setUser(roles.get(0).getIdUser());
                 usersess.setRole(roles.get(0).getRoleName());
                 
                 if (roles.get(0).getRoleName().equals("Admin"))
                 {
                     new Admin().show();
                 }
                  if (roles.get(0).getRoleName().equals("Freelancer"))
                 {
                     new Freelancer().show();
                 }
                     if (roles.get(0).getRoleName().equals("Client"))
                 {
                     new Client().show();
                 }
                req.removeResponseListener(this);
                 } }
        });
        NetworkManager.getInstance().addToQueue(req);
      
        
        } catch (Exception ex) {
          
        }

        // Construct the full URL for the request
       

        });
        
        // Add components to the form
           Image img = MyApplication.theme.getImage("logo.png");
           ImageViewer imglogo = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(700,700));
            Container cn1 = new Container(new FlowLayout(CENTER,CENTER));
                      cn1.setUIID("Container");
cn1.setPreferredH(getHeight() - getToolbar().getHeight() - 1000);
cn1.setPreferredW(getWidth());
    Container cn2 = new Container(new FlowLayout(CENTER,CENTER));
              cn1.addAll(imglogo);
    
              
        addAll(cn1,usernameField, passwordField, loginButton);
    }
    
    private void showMainPage() {
       /* MainPage mainPage = new MainPage();
        mainPage.show();*/
    }
    
}

    
    
    

