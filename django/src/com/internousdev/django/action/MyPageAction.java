package com.internousdev.django.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.UserInfoDAO;
import com.internousdev.django.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

//truts2が持つActionSupportクラスを継承する
public class MyPageAction extends ActionSupport implements SessionAware {

	UserInfoDTO userInfoDTO;
	//フィールド変数
	private Map<String, Object> session;

	public String execute() {

		/* valueOfとtoStringの違い */
        /* どちらも数値型をString型へ変換出来るが、値がnullの場合は動作が異なる*/

		String tempLogined = String.valueOf(session.get("loginFlg"));
		int loginFlg = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if (loginFlg != 1) {
			return "loginError";
		}
		//
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		userInfoDTO = userInfoDAO.getUserInfo(String.valueOf(session.get("userId")));

		return SUCCESS;
	}

	public UserInfoDTO getUserInfoDTO() {
		return userInfoDTO;
	}
	
	public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
		this.userInfoDTO = userInfoDTO;
	}
	
	public Map<String,Object> getSession() {
		return this.session;
	}
	
	public void setSession(Map<String,Object> session) {
		this.session = session;
	}
}