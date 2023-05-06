/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.DateSpinner;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.MyApplication;
import entites.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import services.RoleService;

/**
 *
 * @author abdes
 */
public class Inscription extends Form {
    public Inscription() {
        setTitle("Inscription");
        setLayout(BoxLayout.y());
        Toolbar toolbar = new Toolbar();
        Image img = MyApplication.theme.getImage("logo.png");
         ImageViewer imglogo = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(500, 500));
    ImageViewer cap = new ImageViewer(MyApplication.theme.getImage("logo.png").scaled(100, 100));

    setToolBar(toolbar); // set the toolbar for the form
    Command tuniTaskCmd = new Command("TuniTask");

   toolbar.addComponentToSideMenu(imglogo);
 
   toolbar.addCommandToLeftSideMenu("TuniTask",null,(e) -> new Aceuil().show());
    toolbar.addCommandToLeftSideMenu("Connect",null,(e) -> new ConnectionPage().show());
    toolbar.addCommandToLeftSideMenu("Inscription",null,(e) -> new Inscription().show());
    Label title = new Label("TuniTask");
      Image capturedImage ;
    
 toolbar.setTitleComponent(title);
        // Create fields for user registration form
        TextField firstNameField = new TextField("", "First Name", 15, TextField.ANY);
        TextField lastNameField = new TextField("", "Last Name", 15, TextField.ANY);
        TextField emailField = new TextField("", "Email", 15, TextField.EMAILADDR);
        DateSpinner dateOfBirthField = new DateSpinner();
        TextField passwordField = new TextField("", "Password", 15, TextField.PASSWORD);
        TextField confirmPasswordField = new TextField("", "Confirm Password", 15, TextField.PASSWORD);
        String[] roles = {"Client", "Freelancer"};
        ComboBox<String> roleComboBox = new ComboBox<>(roles);
        Button takePictureButton = new Button("Take Picture");
       
takePictureButton.addActionListener(e -> {
    try {
       
Capture.capturePhoto(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        try {
           
            // Get the captured image
            Image capturedImage = (Image) evt.getSource();
           ImageViewer cap = new ImageViewer(capturedImage.scaled(400, 400));
            // Display the captured image on the form
           // Create the Dialog
Dialog dialog = new Dialog("Image Dialog");

// Create a Container to hold the image
Container container = new Container(new BorderLayout());
container.add(BorderLayout.CENTER, cap);
// Add the Container to the Dialog and show it
dialog.setLayout(new BorderLayout());
dialog.add(BorderLayout.CENTER, container);
dialog.show();


          
        } catch (Exception e) {
           
        }
    }
});

    } catch (Exception ex) {
        // Error capturing image
        ex.printStackTrace();
    }
});
        // Create registration button
        Button registerButton = new Button("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                int day = dateOfBirthField.getCurrentDay();
                int month = dateOfBirthField.getCurrentMonth();
                int year = dateOfBirthField.getCurrentYear();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String selectedRole = roleComboBox.getSelectedItem();
                   if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ) {
        Dialog.show("Error", "Please fill in all the fields", "OK", null);
    } else if (!password.equals(confirmPassword)) {
        Dialog.show("Error", "Passwords do not match", "OK", null);
    } else {
        
                // TODO: Validate user input and submit registration form to server
          ConnectionRequest req = new ConnectionRequest();
            String baseUrl = "http://127.0.0.1:8000";
String endpoint;
                // Example of how to display a message to the user
                      try {
            endpoint = "/controle/json/inscrit/" + firstName + "/" + lastName + "/" + email +"/"+day+"/"+month+"/"+year+"/"+confirmPassword+"/"+selectedRole;
             String url = baseUrl + endpoint;
             
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Boolean result = req.getResponseCode() == 200; //Code HTTP 200 OK
                 String response = new String(req.getResponseData());
                 JSONParser j = new JSONParser();
                 System.out.println(response);
                try {
                    Map<String, Object> tasksListJson
                            = j.parseJSON(new CharArrayReader(response.toCharArray()));
                    System.out.println(tasksListJson.get("inscription"));
                    if(tasksListJson.get("inscription").equals("ok"))
                    {
                        Dialog.show("Registration", "User registration successful!"  , "OK", null);
                    }
                    else {
                        Dialog.show("Registration", "User registration Failed!"  , "OK", null);
                    }
                    
                } catch (Exception ex) {
                    System.out.println(response);
                }
                 
           
            }
        });
        NetworkManager.getInstance().addToQueue(req);
      
        
        } catch (Exception ex) {
          
        }     
                
            }}
        });
        
        // Add components to the form
        addAll(
            firstNameField, 
            lastNameField, 
            emailField, 
            dateOfBirthField,
            roleComboBox,
            passwordField, 
            confirmPasswordField,
            cap,
            takePictureButton,
            registerButton
        );
    }
    
}
