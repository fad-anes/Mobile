package mycompanyquiz.gui;

import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;

/**
 * This class represents the home screen of the app.
 */
public class home extends Form {
    
    public home() {
        setTitle("Home");
        setLayout(new FlowLayout(CENTER, CENTER));

        // Create a Container to hold the content of the form
        Container content = new Container(new FlowLayout(CENTER, CENTER));

        // Add a Label to the Container
        content.add("Welcome to my app!");

        // Add the Container to the Form
        add(content);
    }

   public home(Form previous) {
   // Create a new Toolbar
   Toolbar toolbar = new Toolbar();

   // Set the Toolbar as the current Toolbar of the Form
   setToolbar(toolbar);

   // Add a Logout button to the Toolbar
   toolbar.addMaterialCommandToRightBar("", FontImage.MATERIAL_LOGOUT, ev -> {
       previous.showBack();
   });

   // Add a Demandes button and a Show Demandes button to the Toolbar's Side Menu
   toolbar.addCommandToSideMenu("add quiz", null, ev -> { 
       new addQuiz(this).show(); 
   });
   toolbar.addCommandToSideMenu("Show quizs", null, ev -> { 
       new listQuiz(this).show(); 
   });
   
}

}