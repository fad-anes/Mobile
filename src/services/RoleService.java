/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import entites.Role;
import entites.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author abdes
 */

public class RoleService implements Serializable {

  
public ArrayList<Role> roles;
   

    public ArrayList<Role> parseRoles(String jsonText) {
        try {
            roles = new ArrayList<>();
            JSONParser parser = new JSONParser();
            Map<String, Object> json = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            int roleId = (int) Float.parseFloat(json.get("idRole").toString());
            String roleName = (String) json.get("roleName");
            
            Map<String, Object> idUser = (Map<String, Object>) json.get("idUser");
            Integer userId = (int) Float.parseFloat(idUser.get("id").toString());
            String email = (String) idUser.get("email");
            String firstName = (String) idUser.get("firstName");
            String lastName = (String) idUser.get("lastName");
            String createdAt = (String) idUser.get("createdAt");
            String srcImage = (String) idUser.get("srcimage");
            String password =(String) idUser.get("password");
            
            //Users u = new Users(userId,email,firstName,lastName,new Date(createdAt),srcImage);
            Users us = new Users();
            us.setId(userId);
            us.setEmail(email);
            us.setFirstName(firstName);
            us.setLastName(lastName);
            us.setSrcimage(srcImage);
            us.setPassword(password);
            //us.setCreatedAt(new Date(createdAt));
            Role role = new Role(roleId, roleName,us);
            System.out.println("Role" + role );
            roles.add(role);
              
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return roles;
    }
 public ArrayList<Role> parseRoles1(String jsonText) {
        try {
            roles = new ArrayList<>();
            JSONParser j = new JSONParser();
                 Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
       
            int roleId = (int) Float.parseFloat( obj.get("idRole").toString());
            String roleName = (String)  obj.get("roleName");
            
            Map<String, Object> idUser = (Map<String, Object>)  obj.get("idUser");
            Integer userId = (int) Float.parseFloat(idUser.get("id").toString());
            String email = (String) idUser.get("email");
            String firstName = (String) idUser.get("firstName");
            String lastName = (String) idUser.get("lastName");
            String createdAt = (String) idUser.get("createdAt");
            String srcImage = (String) idUser.get("srcimage");
            String password =(String) idUser.get("password");
            
            //Users u = new Users(userId,email,firstName,lastName,new Date(createdAt),srcImage);
            Users us = new Users();
            us.setId(userId);
            us.setEmail(email);
            us.setFirstName(firstName);
            us.setLastName(lastName);
            us.setSrcimage(srcImage);
            us.setPassword(password);
            //us.setCreatedAt(new Date(createdAt));
            Role role = new Role(roleId, roleName,us);
            System.out.println("Role" + role );
            roles.add(role);
            }   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return roles;
    }
  public ArrayList<Role> parseFilter(ArrayList<Role> roles,String filter) {
    
        ArrayList roles1= new ArrayList<Role>() ;
      
    for (int i=0 ; i < roles.size(); i++ )
    {
        if(roles.get(i).getRoleName().equals(filter))
        {
         roles1.add(roles.get(i));
        }
    }
      System.out.println(roles1);
        return roles1;
    }

    }
    

