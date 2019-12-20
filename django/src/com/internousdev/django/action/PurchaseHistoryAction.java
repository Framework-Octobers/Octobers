package com.internousdev.django.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.PurchaseHistoryInfoDAO;
import com.internousdev.django.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private String deleteFlg;
	private String message;
	private String userId;

	public String execute() {
		if (session.get("loginFlg").toString().equals("0")) {
			return "loginError";
		}

		purchaseHistoryInfoDTOList = purchaseHistoryInfoDAO.getPurchaseHistoryInfoDTOList(String.valueOf(session.get("userId")));

		if (purchaseHistoryInfoDTOList.size() == 0) {
			setMessage("商品購入履歴情報なし");
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public PurchaseHistoryInfoDAO getPurchaseHistoryInfoDAO() {
		return purchaseHistoryInfoDAO;
	}

	public void setPurchaseHistoryInfoDAO(PurchaseHistoryInfoDAO purchaseHistoryInfoDAO) {
		this.purchaseHistoryInfoDAO = purchaseHistoryInfoDAO;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}