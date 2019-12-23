package com.internousdev.django.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.UnsubscribeDAO;
import com.internousdev.django.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UnsubscribeCompleteAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private UnsubscribeDAO unsubscribeDAO = new UnsubscribeDAO();
	private ArrayList<UserInfoDTO> unsubscribeList = new ArrayList<UserInfoDTO>();

	public String execute() throws SQLException {
		String result = ERROR;
		String tempLogined = String.valueOf(session.get("loginFlg"));
		int res = "null".equals(tempLogined) ? 0 : Integer.parseInt(tempLogined);
		if (res != 1) {
			result = "loginError";
		}

		String userId = String.valueOf(session.get("userId"));
		int deleteResult = unsubscribeDAO.userDelete(userId);
		if (deleteResult == 1) {
			result = SUCCESS;
		}
		session.clear();
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public UnsubscribeDAO getUnsubscribeDAO() {
		return unsubscribeDAO;
	}

	public void setUnsubscribeDAO(UnsubscribeDAO unsubscribeDAO) {
		this.unsubscribeDAO = unsubscribeDAO;
	}

	public ArrayList<UserInfoDTO> getUnsubscribeList() {
		return unsubscribeList;
	}

	public void setUnsubscribeList(ArrayList<UserInfoDTO> unsubscribeList) {
		this.unsubscribeList = unsubscribeList;
	}

}
