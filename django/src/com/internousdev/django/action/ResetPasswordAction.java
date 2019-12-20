package com.internousdev.django.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware {

		private String backFlg;
		private Map<String, Object> session;

		public String execute() {

			if (backFlg == null) {
				// 戻るボタンから戻った際にユーザーID以外は未入力状態
				session.remove("userIdForResetPassword");
			}
			return SUCCESS;
	}

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

