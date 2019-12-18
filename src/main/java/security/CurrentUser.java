package security;

import accounter.model.Accounter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

@SessionScoped
@Named
public class CurrentUser implements Serializable {


    public boolean isUserInRole(String role){  // sprawdza jaka jest rola zalogowanego uzytkownika
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
    }


    public boolean isAdmin(){
        return isUserInRole("ADMIN");
    }
    public boolean isResourceUser(){
        return isUserInRole("RESOURCEUSER");
    }
    public boolean isResourceMenager(){
        return isUserInRole("RESOURCEMENAGER");
    }
    public boolean isBlocked(){
        return isUserInRole("BLOCKED");
    }




    public String getCurrentUserLogin(){
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public String getType() {
        if(isResourceUser()) {
            return "ResourceUser";
        } else if(isResourceMenager()) {
            return "ResourceManager";
        } else return "Admin";

    }

}
