/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.services;

import tn.esprit.demande.utils.DataSource;
import tn.esprit.demande.entities.Proposition;
import java.util.List;
import java.util.Map;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.entities.Users;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

//import tn.esprit.demande.utils.DataSource;

/**
 *
 * @author mohamed gabsi
 */
public class ServiceProposition {
    
         public static ServiceProposition instance = null ;
    
    public static boolean resultOk = true;

    
    private final ConnectionRequest req;
    
    
    public static ServiceProposition getInstance() {
        if(instance == null )
            instance = new ServiceProposition();
        return instance ;
    }
    
    
    
    public ServiceProposition() {
        req = new ConnectionRequest();
        
    }
    
    
//       public ArrayList<Proposition>afficherPropositions() {
//        ArrayList<Proposition> result = new ArrayList<>();
//        
//        String url = DataSource.BASE_URL+"/readPropositionJSON";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapPropositions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPropositions.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Proposition Proposition = new Proposition();
//                        
//                        //dima id fi codename one float 5outhouha
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        float id_freelancer = Float.parseFloat(obj.get("id_freelancer").toString());
//                        float id_demande = Float.parseFloat(obj.get("id_demande").toString());
//                        //String titre = obj.get("titre").toString();
//                        String firstName = obj.get("firstName").toString();
//                        String lastName = obj.get("lastName").toString();
//                        //String email = obj.get("email").toString();
//                        
//                        
//                        Proposition.setId((int)id);
//                        //Proposition.setTitre(titre);
//                        Proposition.setId_freelancer((int)id_freelancer);
//                        Proposition.setId_demande((int)id_demande);
//                        //Proposition.setEmail(email);
//                        Proposition.setFirstName(firstName);
//                        Proposition.setLastName(lastName);
//                        
//                  
//                        //insert data into ArrayList result
//                        result.add(Proposition);
//                        
//                       
//                
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//       
//        return result;
//        
//        
//    }
//    //this is my true methode
//    public ArrayList<Proposition> readPropositions() {
//    ArrayList<Proposition> result = new ArrayList<>();
//    String url = DataSource.BASE_URL + "/readPropositionJSON";
//    req.setUrl(url);
//    
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            JSONParser jsonp = new JSONParser();
//            try {
//                // Parse the JSON response from the server
//                Map<String,Object> mapPropositions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                
//                // Extract the list of propositions from the JSON response
//                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPropositions.get("propositions");
//                
//                // Iterate over the list of propositions and convert each one to a Proposition object
//                for(Map<String, Object> obj : listOfMaps) {
//                    Proposition proposition = new Proposition();
//                    
//                    float id = Float.parseFloat(obj.get("id").toString());
//                    float id_demande = Float.parseFloat(obj.get("id_demande").toString());
//                    String firstName = obj.get("firstName").toString();
//                    String lastName = obj.get("lastName").toString();
//                    String email = obj.get("email").toString();
//                    String titre = obj.get("titre").toString();
//                    
//                    proposition.setId((int) id);
//                    proposition.setId_demande((int) id_demande);
//                    proposition.setFirstName(firstName);
//                    proposition.setLastName(lastName);
//                    proposition.setEmail(email);
//                    proposition.setTitre(titre);
//                    
//                    // Add the Proposition object to the result list
//                    result.add(proposition);
//                }
//            } catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    });
//    
//    // Send the request and wait for a response
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    
//    // Return the list of Proposition objects
//    return result;
//}


    
  public boolean deleteProposition(Proposition proposition) {
    String url = DataSource.BASE_URL + "/deletePropositionJSON/" + proposition.getId();
    
    req.setUrl(url);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = true;
            req.removeResponseListener(this);
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}  
}
