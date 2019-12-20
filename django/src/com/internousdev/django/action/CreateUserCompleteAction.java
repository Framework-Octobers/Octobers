package com.internousdev.django.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

     //ユーザーをデータベースに登録する
	public String execute() throws SQLException {
         String result =ERROR;
         String sex = null;
         //性別を判断
         if ("女性".equals(String.valueOf(session.get("sex")))) {
        	 sex = "1";
         } else {
        	 sex = "0";
         }

         UserInfoDAO userInfoDAO = new UserInfoDAO();
         int count = userInfoDAO.createUser(
    		 session.get("familyName").toString(),
    		 session.get("firstName").toString(),
    		 String.valueOf(session.get("familyNameKana")),
    		 String.valueOf(session.get("firstNameKana")),
    		 sex,
    		 String.valueOf(session.get("email")),
    		 session.get("userIdForCreateUser").toString(),
    		 session.get("password").toString());

         if(count > 0){
        	 result = SUCCESS;
        	 session.put("createUserFlg", "1");
         }

         session.remove("familyName");
         session.remove("firstName");
         session.remove("familyNameKana");
         session.remove("firstNameKana");
         session.remove("sex");
         session.remove("sexList");
         session.remove("email");

	    return result;
	}

	//getter.setter
   public Map<String, Object> getSession() {
	   return this.session;
   }
   
   public void setSession(Map<String, Object> session) {
	   this.session = session;
   }
}