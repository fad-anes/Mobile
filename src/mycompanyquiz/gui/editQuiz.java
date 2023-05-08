package mycompanyquiz.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;

import com.codename1.ui.Form;
//import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Quizs;
import mycompanyquiz.services.ServiceQuizs;



public class editQuiz extends Form {
    private final Quizs quiz;

    public editQuiz(Form previous, Quizs quiz) {
        super("Edit quiz", BoxLayout.y());

        this.quiz = quiz;

        // Create the UI components
        TextField title = new TextField(quiz.getQuizTitle(), "title");
        TextField description = new TextField(quiz.getQuizDescription(), "description");
        TextField score = new TextField(String.valueOf(quiz.getScore()), "score",10, TextField.NUMERIC);

 Button saveButton = new Button("Save");
saveButton.addActionListener(evt -> {
  quiz.setQuizTitle(title.getText());
quiz.setQuizDescription(description.getText());
quiz.setScore(Integer.parseInt(score.getText()));


    ServiceQuizs.getInstance().editQuiz(quiz, quiz.getIdQuiz(), res -> {
        if (res.getResponseCode() == 200) {
            Dialog.show("Success", "quiz updated successfully", "OK", null);
            previous.showBack();
        } else {
            Dialog.show("Error", "Failed to update quiz", "OK", null);
        }
    });
});

        Button returnButton = new Button("Return");
        returnButton.addActionListener(evt -> previous.showBack());

        // Add the UI components to the form
        add(title);
        add(description);
        add(score);
     
        add(saveButton);
        add(returnButton);

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