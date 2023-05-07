/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


/**
 *
 * @author abdes
 */
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entites.UserSession;
import java.util.Map;


public class ProfilePage extends Form {
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private Button saveButton;
    
    public ProfilePage(Form previous) {
        super("My Profile");
         UserSession usersess= UserSession.getInstance();
        
                 Toolbar toolbar = new Toolbar();
      setToolBar(toolbar);
     // set the toolbar for the form
    toolbar.addCommandToOverflowMenu("Back",null, (ActionListener) (ActionEvent evt) -> {
            previous.showBack();
        });
    

        // Initialize UI components
        firstNameField = new TextField(usersess.getUser().getFirstName());
        lastNameField = new TextField(usersess.getUser().getLastName());
        emailField = new TextField(usersess.getUser().getEmail());
        passwordField = new TextField();
       
        confirmPasswordField = new TextField();
        saveButton = new Button("Save");
        
        // Set layout and padding
        setLayout(new BorderLayout());
        setScrollableY(true);
       // setPadding(new ComponentPadding(10, 10, 10, 10));
        
        // Create container for profile picture
        Container profilePictureContainer = new Container(new FlowLayout(Component.CENTER));
              
        String url ="http://127.0.0.1/img/"+ usersess.getUser().getSrcimage();
        
      Image img = MyApplication.theme.getImage("logo.png");
    EncodedImage enc = EncodedImage.createFromImage(img,false);
   img=URLImage.createToStorage(enc,usersess.getUser().getSrcimage(),url).scaled(300, 400);
       ImageViewer profilePictureViewer = new ImageViewer(img.scaled(600,600));
       
        profilePictureContainer.add(profilePictureViewer);
        add(BorderLayout.NORTH, profilePictureContainer);
        
        // Create container for form fields
        Container formContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        formContainer.setScrollableY(true);
        add(BorderLayout.CENTER, formContainer);
        
        // Add form fields
        formContainer.add(new Label("First Name"));
        formContainer.add(firstNameField);
        formContainer.add(new Label("Last Name"));
        formContainer.add(lastNameField);
        formContainer.add(new Label("Email"));
        formContainer.add(emailField);
        formContainer.add(new Label("Password"));
        formContainer.add(passwordField);
        formContainer.add(new Label("Confirm Password"));
        formContainer.add(confirmPasswordField);
        
        // Add save button
        Container saveButtonContainer = new Container(new FlowLayout(Component.CENTER));
        saveButtonContainer.add(saveButton);
        add(BorderLayout.SOUTH, saveButtonContainer);
        
        // Set styles
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        firstNameField.getStyle().setFont(font);
        lastNameField.getStyle().setFont(font);
        emailField.getStyle().setFont(font);
        passwordField.getStyle().setFont(font);
        confirmPasswordField.getStyle().setFont(font);
        saveButton.getStyle().setBgTransparency(255);
        saveButton.getStyle().setBgColor(0x2196F3);
        saveButton.getStyle().setFgColor(0xFFFFFF);
        saveButton.getStyle().setPadding(5, 5, 5, 5);
        saveButton.getStyle().setBorder(
               RoundBorder.create().rectangle(true).color(0x2196F3));
    
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                
                   if (email.isEmpty()  || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()   ) {
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
                          String pwd;
                          if (confirmPassword.equals("") && confirmPassword==null)
                          { pwd= usersess.getUser().getPassword();}
                          else
                          {pwd=confirmPassword;}
            endpoint = "/json/user/update/"+usersess.getUser().getId() + "/"+firstName + "/" + lastName + "/" + email +"/"+pwd;
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
                   
                    if(tasksListJson.get("update").equals("ok"))
                    {
                        Dialog.show("Update", "User updating successful!"  , "OK", null);
                    }
                    else {
                        Dialog.show("Update", "User updating Failed!"  , "OK", null);
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
        
}
}