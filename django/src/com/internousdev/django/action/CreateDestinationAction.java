package com.internousdev.django.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
//決済確認画面から宛先情報入力画面に移動
	//ログインを継続するかしないかの分岐
public class CreateDestinationAction extends ActionSupport implements SessionAware {
	private String backFlg;
	private Map<String, Object> session;

	public String execute() {
		String tempLogined = String.valueOf(session.get("loginFlg"));
		int logined = "null".equals(tempLogined)? 0 :Integer.parseInt(tempLogined);
		if (logined != 1) {
			return "loginError";
		}
		
		//戻るボタン以外でこのページに戻るとき入力されている値を空にする
		if (backFlg == null) {
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("email");
			session.remove("telNumber");
			session.remove("userAddress");
		}
		return SUCCESS;
	}

	 //getter.setter
	public String getBackFlg() {
		return backFlg;
	}
	
	public void setBackFlg(String backFlg) {
		this.backFlg = backFlg;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}