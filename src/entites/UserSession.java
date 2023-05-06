/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.io.Serializable;

/**
 *
 * @author abdes
 */

public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Long id;
    
    private String code;
    private static UserSession instance;
    private String role ;
    private String email ;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    private Users user ;

   private UserSession() {}

   public static synchronized UserSession getInstance() {
      if (instance == null) {
         instance = new UserSession();
      }
      return instance;
   }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
  public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSession)) {
            return false;
        }
        UserSession other = (UserSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gui.UserSession[ id=" + id + " ]";
    }
    
}
