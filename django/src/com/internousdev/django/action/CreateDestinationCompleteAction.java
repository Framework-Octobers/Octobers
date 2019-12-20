package com.internousdev.django.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.DestinationInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private int loginFlg;

     //宛先情報をデータベースに登録する
	public String execute() {
		String tempLogined = String.valueOf(session.get("loginFlg"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		
		if (logined != 1) {
			return "loginError";
		}
		
	    String result =ERROR;
	    DestinationInfoDAO dao = new DestinationInfoDAO();

		int count = dao.createDestination(
		   session.get("userId").toString(),
		   session.get("familyName").toString(),
		   session.get("firstName").toString(),
		   String.valueOf(session.get("familyNameKana")),
		   String.valueOf(session.get("firstNameKana")),
		   String.valueOf(session.get("email")),
		   session.get("userAddress").toString(),
		   String.valueOf(session.get("telNumber")));

	     if (count > 0) {
	    	 result = SUCCESS;
	     }
	     session.remove("familyName");
	     session.remove("firstName");
	     session.remove("familyNameKana");
	     session.remove("firstNameKana");
	     session.remove("email");
	     session.remove("telNumber");
	     session.remove("userAddress");

	     return result;
	}

	//getter.setter
   public int getLoginFlg() {
	   return loginFlg;
   }
   
   public void setLoginFlg(int loginFlg) {
	   this.loginFlg = loginFlg;
   }

   public Map<String, Object> getSession() {
	   return this.session;
   }
   
   public void setSession(Map<String, Object> session) {
	   this.session = session;
   }
}