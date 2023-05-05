/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.db.Cursor;
import com.codename1.db.Row;
import java.util.*;
import com.mycompany.myapp.MyApplication;
import entites.Users;
import java.io.IOException;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abdes
 */
public class UserService {
      public ArrayList<Users> users;
    public void ajouterUser(Users u) throws IOException{
    MyApplication.Tunitask.execute("");
    }
    public List<Users> afficherUsers() throws IOException{
        System.out.println("enter");
    List<Users> us= new ArrayList();
    System.out.println(us);
    Cursor cr =MyApplication.Tunitask.executeQuery("SELECT * FROM users");
        System.out.println(cr);
    while(cr.next())
    {
     Row row= cr.getRow();
     Users u= new Users();
     //u.setEmail(row.getString(2));
     u.setId(row.getInteger(0));
     //u.setSrcimage(row.getString(8));
        System.out.println(u);
     us.add(u);
    }
    return us;
    }
        public ArrayList<Users> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Users u = new Users();
                float id = Float.parseFloat(obj.get("id").toString());
                String img =obj.get("srcimage").toString();
                 String email =obj.get("email").toString();
                  String firstname =obj.get("firstName").toString();
                   String lastname =obj.get("lastName").toString();
                 System.out.println("createdAt" + obj.get("createdAt").toString());
                u.setId((int) id);
                System.out.println(img);
                u.setSrcimage(img);
                u.setEmail(email);
                u.setFirstName(firstname);
                u.setLastName(lastname);
            
                //u.setStatus(((Boolean) Float.parseFloat(obj.get("status"))));
               /* if (obj.get("name") == null) {
                    t.setName("null");
                } else {
                    t.setName(obj.get("name").toString());
                }*/
                users.add(u);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
}
