package com.javaproject.ankush.tourisam;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class LoginManager {
	
	private String userName;
	
	private String password;
	
	public List<UserSummaryBean> fetchAllUser() {
		List<UserSummaryBean> dbUserList = new ArrayList<>();

		// user1
		UserSummaryBean user1 = new UserSummaryBean();
		user1.setUserName("ankush");
		user1.setPassword("ankush123");
		dbUserList.add(user1);

		// user2
		UserSummaryBean user2 = new UserSummaryBean();
		user2.setUserName("pal");
		user2.setPassword("pal123");
		dbUserList.add(user2);

		// user3
		UserSummaryBean user3 = new UserSummaryBean();
		user3.setUserName("ram");
		user3.setPassword("ram123");
		dbUserList.add(user3);
		
		return dbUserList;
	}
	
	public String validateUserCredentials() {
		System.out.println("user: " + userName + ", password: " + password);
		List<UserSummaryBean> dbUserList = fetchAllUser();
		System.out.println(dbUserList);
		boolean isValid = false;
		String redirectPageName = "login.xhtml?faces-redirect=true";
		
		for(UserSummaryBean user: dbUserList) {
			if(user.getUserName().equals(userName)) {
				if(user.getPassword().equals(password)) {
					System.out.println("credentials ok");
					isValid = true;
					break;
				}
			}
		}
		
		if(!isValid) {
			addErrorMessage(FacesMessage.SEVERITY_ERROR, "Username and password not matched");
			System.out.println("credentials not ok");			
		} else {
			redirectPageName = "userSummary.xhtml?faces-redirect=true";
		}
		
		return redirectPageName;
		
	}
	
	
	public static void addErrorMessage(FacesMessage.Severity Severity, String errorMessage) {
		FacesContext context = FacesContext.getCurrentInstance ();
		FacesMessage facesMsg = new FacesMessage(Severity, errorMessage, null);
		context.addMessage(null, facesMsg);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
	 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
