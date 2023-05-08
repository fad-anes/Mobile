/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompanyquiz.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Questions;
import com.mycompany.entities.Quizs;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


 
/**
 *
 * @author Ahmed
 */
public class ServiceQuizs  {
    public ArrayList<Quizs> quizzes;
    public ArrayList<Quizs> tasks;
 private ArrayList<Questions> questions;
    public static ServiceQuizs instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public static boolean resultOk = true;

    private ServiceQuizs() {
        req = new ConnectionRequest();
    }

    public static ServiceQuizs getInstance() {
        if (instance == null) {
            instance = new ServiceQuizs();
        }
        return instance;
    }
///////////////////////////////////////////////////////
    public boolean addTask(Quizs t) {

        String title = t.getQuizTitle();
        String description =  t.getQuizDescription();
        int score=t.getScore();
         
        String url = Statics.BASE_URL + "/add?quizTitle=" + title + "&quizDescription=" + description+ "&score="+ score;
       // String url = Statics.BASE_URL + "create/" + title + "/" + score;
                                       //create?status=0&name=houssem
                                       //modified
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Quizs> getAllQuizs() {
        String url = Statics.BASE_URL + "/quizsjson";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jsonText = new String(req.getResponseData());
                quizzes = parseQuizzes(jsonText);
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return quizzes;
    }

private ArrayList<Quizs> parseQuizzes(String jsonText) {
   if (quizzes == null) {
    quizzes = new ArrayList<>();
} else {
    quizzes.clear();
}

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> quizsMap = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        Object quizsObj = quizsMap.get("root");
        if (quizsObj instanceof ArrayList) {
            ArrayList<Object> quizsJson = (ArrayList<Object>) quizsObj;
            for (Object quizObj : quizsJson) {
                if (quizObj instanceof Map) {
                    Map<String, Object> quizJson = (Map<String, Object>) quizObj;
                    Quizs quiz = new Quizs();
                    quiz.setIdQuiz((int) Float.parseFloat(quizJson.get("idQuiz").toString()));
                    quiz.setQuizTitle(quizJson.get("quizTitle").toString());
                    quiz.setQuizDescription(quizJson.get("quizDescription").toString());
                    quiz.setScore((int) Float.parseFloat(quizJson.get("score").toString()));
                    quizzes.add(quiz);
                }
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return quizzes;
}
public boolean deleteQuiz(Quizs quiz) {
    String url = Statics.BASE_URL + "/quizs/" + quiz.getIdQuiz()+"/deletejson";
    req.setUrl(url);
    req.setHttpMethod("DELETE");

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 204; //Code HTTP 204 No Content
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
      public ArrayList<Questions> getQuizQuestions(Quizs quiz) {
    ArrayList<Questions> questions = new ArrayList<>();

    String url = Statics.BASE_URL + "/quizs/" + quiz.getIdQuiz() + "/openjson";
    ConnectionRequest connectionRequest = new ConnectionRequest();
    connectionRequest.setUrl(url);
    connectionRequest.setContentType("application/json");
    connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            try {
                String jsonText = new String(connectionRequest.getResponseData());
                JSONParser j = new JSONParser();
                Map<String, Object> questionsMap = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                Object questionsObj = questionsMap.get("root");

                if (questionsObj instanceof ArrayList) {
                    ArrayList<Object> questionsJson = (ArrayList<Object>) questionsObj;

                    for (Object questionObj : questionsJson) {
                        if (questionObj instanceof Map) {
                            Map<String, Object> questionJson = (Map<String, Object>) questionObj;
                            Questions question = new Questions();
                            question.setIdQuestion((int) Float.parseFloat(questionJson.get("idQuestion").toString()));
                            question.setQuestionText(questionJson.get("questionText").toString());
                            question.setIdQuiz((int) Float.parseFloat(questionJson.get("idQuiz").toString()));
                            questions.add(question); } }
                    resultOK = true; } }
            catch (IOException ex) { ex.printStackTrace(); } } });
    NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    return questions;
      }
      public void editQuiz(Quizs quiz, int id, ActionListener<NetworkEvent> listener) {
    String url = Statics.BASE_URL +  "/quizs/" + id + "/editjson"
            + "?quizTitle=" + quiz.getQuizTitle() 
            + "&quizDescription=" + quiz.getQuizDescription() 
            + "&score=" + quiz.getScore();
            
    req.setUrl(url);

    req.addResponseListener(listener);

    NetworkManager.getInstance().addToQueueAndWait(req);
} 
     
}


