package com.internousdev.django.action;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class OneBuyItemAction extends ActionSupport implements SessionAware{

	public String execute() {
		String tempLogined = String.valueOf(session.get("loginFlg"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if (logined != 1) {
			return "loginError";
		} else {

			purchaseHistoryList = PurcahaseHistoryInfoDAO.OnebuySupportPurcahse(String.valueOf(session.get("userId")));
			

		}
	}
}
