package tn.esprit.mb;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.interfaces.ForumServiceLocal;
import tn.esprit.persistance.Swapper;
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Swapper user;
	private Swapper UserCurrent;
	
	private String message;
	
	public LoginBean() {

	}
	@PostConstruct
	public void initModel() {
		user = new Swapper();
		
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@EJB
	ForumServiceLocal service;

	public Swapper getUser() {
		return user;
	}

	public void setUser(Swapper user) {
		this.user = user;
	}
	
	
	public String DoLogin(){
		String navigateTo=null;		
		user=service.authenticate(user.getLogin(), user.getPassword());
				if (user!=null){
			 //navigateTo="/gestionPerso?faces-redirect=true";
			 navigateTo="/gestionPerso";
			// navigateTo="/gestion?faces-redirect=true";	
			 
			 }
				else message="Erreur D'authentification";
				return navigateTo;
	}
	
	
	public String DoLogin2(){
		String navigateTo=null;		
		user=service.authenticate(user.getLogin(), user.getPassword());
				if (user!=null){
					
					if(user.getLogin().equals("admin") && user.getPassword().equals("admin")){
						navigateTo="/admin/gestionDic?faces-redirect=true";
					}
					else{
			 //navigateTo="/gestionPerso?faces-redirect=true";
			 navigateTo="/gestionPerso";
			// navigateTo="/gestion?faces-redirect=true";	
					}
			 }
				else message="Erreur D'authentification";
				return navigateTo;
	}
	
	
	public String doLogout() {
		user=new Swapper();
		String navigateTo=null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		navigateTo="/login?faces-redirect=true";
		return navigateTo;
	}

	
	public Swapper getUserCurrent() {
		return UserCurrent;
	}

	public void setUserCurrent(Swapper userCurrent) {
		UserCurrent = userCurrent;
	}

	
}



