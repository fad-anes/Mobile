/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Offre;
import com.mycompany.entities.Commentaire;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marni
 */
public class ServiceOffre {
        
    
    public static ServiceOffre instance = null ;
    
    public static boolean resultOk = true;

    
    private ConnectionRequest req;
    
    
    public static ServiceOffre getInstance() {
        if(instance == null )
            instance = new ServiceOffre();
        return instance ;
    }
    
    
    
    public ServiceOffre() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutOffre(Offre Offre) {
        
        String url =Statics.BASE_URL+"/addoffre?description="+Offre.getDescription()+"&titre="+Offre.getTitre()+"&salaireh="+Offre.getSalaireh(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    //affichage
    
    public ArrayList<Offre>affichageOffres() {
        ArrayList<Offre> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/readmonoffre";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapOffres.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Offre Offre = new Offre();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("idoffre").toString());
                        
                        String description = obj.get("description").toString();
                        
                        String titre = obj.get("titre").toString();
                        String firstName = obj.get("firstName").toString();
                        String lastName = obj.get("lastName").toString();
                        String srcimage = obj.get("srcimage").toString();
                        float salaireh = Float.parseFloat(obj.get("salaireh").toString());
                        
                        
                        Offre.setIdoffre((int)id);
                        Offre.setDescription(description);
                        Offre.setTitre(titre);
                        Offre.setSalaireh(salaireh);
                        Offre.setFirstName(firstName);
                        Offre.setLastName(lastName);
                        Offre.setSrcimage(srcimage);
                        ///formater la date
                        
                        
                        //insert data into ArrayList result
                        result.add(Offre);
                        
                       
                
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
       
        return result;
        
        
    }
    
    
    
    
    //Delete 
    public boolean deleteOffre(int idoffre ) {
        String url = Statics.BASE_URL +"/deleteoffre/"+idoffre;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierOffre(Offre Offre,int idoffre) {
        String url = Statics.BASE_URL +"/editoffre/"+idoffre+"?description="+Offre.getDescription()+"&titre="+Offre.getTitre()+"&salaireh="+Offre.getSalaireh();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
  public Offre recupererOffre(int idoffre) {
    

    String url = Statics.BASE_URL + "/Recupoffre/" + idoffre;
    req.setUrl(url);
    Offre offre =new Offre();
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();

            try {
                Map<String, Object> mapOffre = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                float id = Float.parseFloat(mapOffre.get("idoffre").toString());
                String description = mapOffre.get("description").toString();
                String titre = mapOffre.get("titre").toString();
               
                float salaireh = Float.parseFloat(mapOffre.get("salaireh").toString());

                System.out.println("Description: " + description);
                offre.setIdoffre((int) id);
                offre.setDescription(description);
                offre.setTitre(titre);
                offre.setSalaireh(salaireh);
                // formater la date
                
                System.out.println("Offre : " + offre.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return offre;
}

    
       
   
    
}
