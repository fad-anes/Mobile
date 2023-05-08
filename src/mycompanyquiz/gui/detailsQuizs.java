package mycompanyquiz.gui;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.Quizs;
import mycompanyquiz.services.ServiceQuizs;



public class detailsQuizs extends Form {
    public detailsQuizs(Form previous, Quizs quiz) {
        super(quiz.getQuizTitle(), BoxLayout.y());

        // Create the UI components
        Label title = new Label(quiz.getQuizTitle());
        Label desc = new Label(quiz.getQuizDescription());
        Label score = new Label(Integer.toString(quiz.getScore()));

       

        Button backButton = new Button("Back");
        backButton.addActionListener(evt -> 
                previous.showBack()
                //new AfficherMyDemandes(this).show()
        );

        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(evt -> {
            // Delete the demande using the service
            ServiceQuizs.getInstance().deleteQuiz(quiz);
            // Show a confirmation message
            Dialog.show("Success", "quiz deleted successfully", "OK", null);
            // Navigate back to the previous form
            previous.showBack();
        });

       Button editButton = new Button("Edit");
        editButton.addActionListener(evt -> {
            // Navigate to the edit form and pass the current demande as a parameter
            new editQuiz(this, quiz).show();
        });

        // Add the UI components to the form
        ComponentGroup componentGroup = ComponentGroup.enclose(
                title,
                desc,
                score
                
        );
        FlowLayout flowLayout = new FlowLayout(Component.CENTER, Component.CENTER);
        add(FlowLayout.encloseCenterMiddle(componentGroup));

        Container buttonsContainer = new Container(new GridLayout(1, 3));
        buttonsContainer.add(backButton);
        buttonsContainer.add(deleteButton);
       buttonsContainer.add(editButton);
        add(buttonsContainer);

        // Set the form's back command to the previous form
        setBackCommand(new BackCommand(previous));
    }

    private static class BackCommand extends com.codename1.ui.Command {
        private final Form previous;

        public BackCommand(Form previous) {
            super("Back");
            this.previous = previous;
        }

        @Override
        public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
            previous.showBack();
        }
    }
}