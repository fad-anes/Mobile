package mycompanyquiz.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Quizs;

import java.util.ArrayList;
import java.util.HashSet;
import mycompanyquiz.services.ServiceQuizs;

public class listQuiz extends Form {

    // Define a TextField to use for the search filter
    private TextField searchField = new TextField("", "Search");

   public listQuiz(Form previous) {
    super("Quizs", new BorderLayout());

    // Define a TextField to use for the search filter
    TextField searchField = new TextField("", "Search");

    // Load user's demands
    ArrayList<Quizs> quizs = ServiceQuizs.getInstance().getAllQuizs();

    // Create a ComponentGroup to hold the list of demands
    ComponentGroup group = new ComponentGroup();
    for (Quizs quiz : quizs) {
        MultiButton mb = new MultiButton(quiz.getQuizTitle());
        mb.setTextLine2(quiz.getQuizDescription());
        mb.setTextLine3(String.valueOf(quiz.getScore()));
        group.addComponent(mb);
         mb.addActionListener(e -> {
                // Show details of the selected demand
                new detailsQuizs(this, quiz).show();
            });
    }

    // Create a container to hold the ComponentGroup with a scrollbar
    Container scrollableContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    scrollableContainer.setScrollableY(true);
    scrollableContainer.addComponent(group);

    // Add the scrollable container to the form
    add(BorderLayout.CENTER, scrollableContainer);

    // Add the search field to the form
    add(BorderLayout.NORTH, searchField);

   // Set the listener to filter the quizs when the search text changes
searchField.addDataChangedListener(new DataChangedListener() {
    @Override
    public void dataChanged(int type, int index) {
        filterQuizs(searchField.getText().toLowerCase(), group, quizs);
    }
});

    // Set the form's back command to the previous form
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

  private void filterQuizs(String searchText, ComponentGroup group, ArrayList<Quizs> allQuizs) {
    // Create an ArrayList to store the filtered quizs
    ArrayList<Quizs> filteredQuizs = new ArrayList<>();

    // Filter the quizs based on the search text
    for (Quizs quiz : allQuizs) {
        if (quiz.getQuizTitle().toLowerCase().contains(searchText)) {
            filteredQuizs.add(quiz);
        }
    
    }
    
    // Clear the ComponentGroup
    group.removeAll();

    // Add each filtered quiz as a MultiButton to the ComponentGroup
    for (Quizs quiz : filteredQuizs) {
        MultiButton mb = new MultiButton(quiz.getQuizTitle());
        mb.setTextLine2(quiz.getQuizDescription());
        mb.setTextLine3(String.valueOf(quiz.getScore()));
        group.addComponent(mb);
    }

    // Repaint the form to update the filtered quizzes
    repaint();
}


}