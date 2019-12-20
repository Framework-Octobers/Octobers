package com.internousdev.django.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationConfirmAction extends ActionSupport implements SessionAware {
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String userAddress;
	private String telNumber;
	private String email;
	private int loginFlg;
	private String backFlg;
	private Map<String, Object> session;
	private List<String> familyNameErrorMessageList;
	private List<String> firstNameErrorMessageList;
	private List<String> familyNameKanaErrorMessageList;
	private List<String> firstNameKanaErrorMessageList;
	private List<String> emailErrorMessageList;
	private List<String> telNumberErrorMessageList;
	private List<String> userAddressErrorMessageList;

	//宛先情報入力確認画面にいくためのアクション
	public String execute(){
		String tempLogined = String.valueOf(session.get("loginFlg"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if (logined != 1) {
			return "loginError";
		}

		String result = "ERROR";

		// 処理用の変数
		String tempUserAddress = null;
		if (StringUtils.isBlank(userAddress)){
			//住所が null,""," ","　"の時、処理用の変数に"@@@"を代入
			tempUserAddress = "@@@";
		} else {
			// そうでなければ処理用の変数に入力した値を代入
			tempUserAddress = userAddress;
		}


		if (backFlg == null) {
		session.put("familyName", familyName);
		session.put("firstName", firstName);
		session.put("familyNameKana", familyNameKana);
		session.put("firstNameKana", firstNameKana);
		session.put("email", email);
		session.put("telNumber", telNumber);
		session.put("userAddress", userAddress);
		}

		InputChecker inputChecker = new InputChecker();
		//姓入力チェック制限
		familyNameErrorMessageList = inputChecker.doCheck("姓", familyName, 1, 16, true, true, true, false, true, false);

		//名入力チェック制限
		firstNameErrorMessageList = inputChecker.doCheck("名", firstName, 1, 16, true, true, true, false, true, false);

		//姓かな入力チェック制限
		familyNameKanaErrorMessageList = inputChecker.doCheck("姓かな", familyNameKana, 1, 16, false, false, true, false, false, false);

		//名かな入力チェック制限
		firstNameKanaErrorMessageList = inputChecker.doCheck("名かな", firstNameKana, 1, 16, false, false, true, false, false, false);

		//メール入力チェック制限
		emailErrorMessageList = inputChecker.doCheckForEmail("メールアドレス", email, 10, 32);

		//電話番号入力チェック制限
		telNumberErrorMessageList = inputChecker.doCheck("電話番号", telNumber, 10, 13, false, false, false, true, false, false);

		//住所入力チェック制限
		userAddressErrorMessageList = inputChecker.doCheck("住所", tempUserAddress, 10, 50, true, true, true, true, true, true);

		 //入力チェック
		 if(familyNameErrorMessageList.size() > 0
			 || firstNameErrorMessageList.size() > 0
			 || familyNameKanaErrorMessageList.size() > 0
			 || firstNameKanaErrorMessageList.size() > 0
			 || emailErrorMessageList.size() > 0
			 || telNumberErrorMessageList.size() > 0
			 || userAddressErrorMessageList.size() > 0){
			 return ERROR;
		 }else{
			 result = SUCCESS;
		 }
		 return result;
	}

		//getter.setter
	   public String getFamilyName() {
			return familyName;
	   }

	   public void setFamilyName(String familyName) {
		   this.familyName = familyName;
	   }

	   public String getFirstName() {
		   return firstName;
	   }

	   public void setFirstName(String firstName) {
		   this.firstName = firstName;
	   }

	   public String getFamilyNameKana() {
		   return familyNameKana;
	   }

	   public void setFamilyNameKana(String familyNameKana){
		   this.familyNameKana = familyNameKana;
	   }

	   public String getFirstNameKana() {
		   return firstNameKana;
	   }

	   public void setFirstNameKana(String firstNameKana) {
		   this.firstNameKana = firstNameKana;
	   }

	   public String getUserAddress() {
		   return userAddress;
	   }

	   public void setUserAddress(String userAddress) {
		   this.userAddress = userAddress;
	   }

	   public String getTelNumber() {
		   return telNumber;
	   }

	   public void setTelNumber(String telNumber) {
		   this.telNumber = telNumber;
	   }

	   public String getEmail() {
		   return email;
	   }

	   public void setEmail(String email) {
		   this.email = email;
	   }

	   public List<String> getFamilyNameErrorMessageList() {
		   return familyNameErrorMessageList;
	   }

	   public void setFamilyNameErrorMessageList(List<String> familyNameErrorMessageList) {
		   this.familyNameErrorMessageList = familyNameErrorMessageList;
	   }

	   public List<String> getFirstNameErrorMessageList() {
		   return firstNameErrorMessageList;
	   }

	   public void setFirstNameErrorMessageList(List<String> firstNameErrorMessageList) {
		   this.firstNameErrorMessageList = firstNameErrorMessageList;
	   }

	   public List<String> getFamilyNameKanaErrorMessageList() {
		   return familyNameKanaErrorMessageList;
	   }

	   public void setFamilyNameKanaErrorMessageList(List<String> familyNameKanaErrorMessageList) {
		   this.familyNameKanaErrorMessageList = familyNameKanaErrorMessageList;
	   }

	   public List<String> getFirstNameKanaErrorMessageList() {
		   return firstNameKanaErrorMessageList;
	   }

	   public void setFirstNameKanaErrorMessageList(List<String> firstNameKanaErrorMessageList) {
		   this.firstNameKanaErrorMessageList = firstNameKanaErrorMessageList;
	   }

	   public List<String> getEmailErrorMessageList() {
		   return emailErrorMessageList;
	   }

	   public void setEmailErrorMessageList(List<String> emailErrorMessageList) {
		   this.emailErrorMessageList = emailErrorMessageList;
	   }

	   public List<String> getTelNumberErrorMessageList() {
		   return telNumberErrorMessageList;
	   }

	   public void setTelNumberErrorMessageList(List<String> telNumberErrorMessageList) {
		   this.telNumberErrorMessageList = telNumberErrorMessageList;
	   }

	   public List<String> getUserAddressErrorMessageList() {
		   return userAddressErrorMessageList;
	   }

	   public void setUserAddressErrorMessageList(List<String> userAddressErrorMessageList) {
		   this.userAddressErrorMessageList = userAddressErrorMessageList;
	   }

	   public int getLoginFlg() {
		   return loginFlg;
	   }

	   public void setLoginFlg(int loginFlg) {
		   this.loginFlg = loginFlg;
	   }

	   public String getBackFlg() {
		   return backFlg;
	   }

	   public void setBackFlg(String backFlg) {
		   this.backFlg = backFlg;
	   }

	   public Map<String, Object> getSession() {
		   return this.session;
	   }

	   public void setSession(Map<String, Object> session) {
		   this.session = session;
	   }
}