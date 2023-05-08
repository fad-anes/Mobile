/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.demande.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tn.esprit.demande.entities.Demande;
import tn.esprit.demande.utils.DataSource;

/**
 *
 * @author mohamed gabsi
 */
public class ServiceDemande {
    
    
      public static ServiceDemande instance = null ;
    
    public static boolean resultOk = true;

    
    private final ConnectionRequest req;
    
    
    public static ServiceDemande getInstance() {
        if(instance == null )
            instance = new ServiceDemande();
        return instance ;
    }
    
    
    
    public ServiceDemande() {
        req = new ConnectionRequest();
        
    }
    
  
    
    
    //ajout 
    public void ajoutDemande(Demande demande) {
    String url = DataSource.BASE_URL + "/addDemandeJSON/new?titre=" + demande.getTitre() + "&description=" + demande.getDescription() + "&salaire=" + demande.getSalaire() + "&delai=" + demande.getDelai() + "&langage=" + demande.getLangage();

    req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    //affichage
    
    public ArrayList<Demande>afficherDemandes() {
        ArrayList<Demande> result = new ArrayList<>();
        
        String url = DataSource.BASE_URL+"/readMyDemandesJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapDemandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapDemandes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Demande Demande = new Demande();
                        
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String titre = obj.get("titre").toString();
                        String description = obj.get("description").toString();
                        
                        float salaire = Float.parseFloat(obj.get("salaire").toString());
                        String delai = obj.get("delai").toString();
                        
                        String langage = obj.get("langage").toString();

                        String firstName = obj.get("firstName").toString();
                        String lastName = obj.get("lastName").toString();
                        
                        
                        Demande.setId((int)id);
                        Demande.setTitre(titre);
                        Demande.setDescription(description);
                        Demande.setSalaire(salaire);
                        Demande.setDelai(delai);
                        Demande.setLangage(langage);
                        Demande.setFirstName(firstName);
                        Demande.setLastName(lastName);
                        
                  
                        //insert data into ArrayList result
                        result.add(Demande);
                        
                       
                
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
       
        return result;
        
        
    }
    

    
    public ArrayList<Demande>Affichetous() {
        ArrayList<Demande> result = new ArrayList<>();
        
        String url = DataSource.BASE_URL+"/readDemandesJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapDemandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapDemandes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Demande Demande = new Demande();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String titre = obj.get("titre").toString();
                        String description = obj.get("description").toString();
                        
                        float salaire = Float.parseFloat(obj.get("salaire").toString());
                        String delai = obj.get("delai").toString();
                        
                        String langage = obj.get("langage").toString();

                        String firstName = obj.get("firstName").toString();
                        String lastName = obj.get("lastName").toString();
                        
                        
                          Demande.setId((int)id);
                        Demande.setTitre(titre);
                        Demande.setDescription(description);
                        Demande.setSalaire(salaire);
                        Demande.setDelai(delai);
                        Demande.setLangage(langage);
                        Demande.setFirstName(firstName);
                        Demande.setLastName(lastName);
                        
                        
                        //insert data into ArrayList result
                        result.add(Demande);
                        
                       
                
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);
       
        return result;
        
        
    }
    
    
    
    
    
    public boolean deleteDemande(Demande demande) {
    String url = DataSource.BASE_URL + "/deleteDemandeJSON/" + demande.getId();
    
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

    
    
   

   public void modifierDemande(Demande demande, int id, ActionListener<NetworkEvent> listener) {
    String url = DataSource.BASE_URL + "/updateDemandeJSON/" + id 
            + "?titre=" + demande.getTitre() 
            + "&description=" + demande.getDescription() 
            + "&salaire=" + demande.getSalaire() 
            + "&delai=" + demande.getDelai() 
            + "&langage=" + demande.getLangage();
    req.setUrl(url);

    req.addResponseListener(listener);

    NetworkManager.getInstance().addToQueueAndWait(req);
} 
    
  public Demande recupererDemande(int id) {
    

    String url = DataSource.BASE_URL + "/readDemandeById/" + id;
    req.setUrl(url);
    Demande demande =new Demande();
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();

            try {
                Map<String, Object> mapDemande = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                float id = Float.parseFloat(mapDemande.get("id").toString());
                
                String titre = mapDemande.get("titre").toString();
               
                String description = mapDemande.get("description").toString();

                float salaire = Float.parseFloat(mapDemande.get("salaire").toString());

                String delai = mapDemande.get("delai").toString();

                String langage = mapDemande.get("langage").toString();

                System.out.println("Description: " + description);
                demande.setId((int) id);
                demande.setTitre(titre);
                demande.setDescription(description);
                demande.setSalaire(salaire);
                demande.setDelai(delai);
                demande.setLangage(langage);
               
                // formater la date
                
                System.out.println("Demande : " + demande.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return demande;
}
  
  
  
  
    
}
