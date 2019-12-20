package com.internousdev.django.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.PurchaseHistoryInfoDAO;
import com.internousdev.django.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware {

	private String deleteFlg;
	private PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();
	private Map<String, Object> session;

	public String execute() {
		String result = null;
		if (deleteFlg.equals("1")) {
			result = delete();
		}
		return result;
	}

	public String delete(){
		if (session.get("loginFlg").toString().equals("0")) {
			return "loginError";
		}
		int res = purchaseHistoryInfoDAO.PurchaseHistoryDelete(String.valueOf(session.get("userId")));

		if (res > 0) {
			purchaseHistoryInfoDTOList = null;
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}