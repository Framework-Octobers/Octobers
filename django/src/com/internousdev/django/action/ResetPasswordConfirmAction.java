package com.internousdev.django.action;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.UserInfoDAO;
import com.internousdev.django.util.CommonUtility;
import com.internousdev.django.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware {

	private String userId;
	private String password;
	private String newPassword;
	private String reConfirmation;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> newPasswordErrorMessageList;
	private List<String> reConfirmationErrorMessageList;
	private Map<String,Object> session;
	private String passwordIncorrectErrorMessage;
	private String newPasswordIncorrectErrorMessage;
	private String concealedPassword;

	public String execute() {
		String result = ERROR;
		InputChecker  inputChecker = new InputChecker();

		session.put("userIdForResetPassword", userId);

		userIdErrorMessageList = inputChecker.doCheck( "ユーザーID", userId, 1, 8, true, false, false, true, false, false);
		passwordErrorMessageList = inputChecker.doCheck( "パスワード", password, 1, 16, true, false, false, true, false, false);
		newPasswordErrorMessageList = inputChecker.doCheck( "新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false);
		reConfirmationErrorMessageList = inputChecker.doCheck( "新しいパスワード(再設定）", reConfirmation, 1, 16, true, false, false, true, false, false);

		if (userIdErrorMessageList.size() > 0
		|| passwordErrorMessageList.size() > 0
		|| newPasswordErrorMessageList.size() > 0
		|| reConfirmationErrorMessageList.size() > 0) {

				return result;
		}

		// 新しいパスワードと新しいパスワード(再確認）が一致しているかを確認
		newPasswordIncorrectErrorMessage = inputChecker.doPasswordCheck(newPassword, reConfirmation);
		if (newPasswordIncorrectErrorMessage != null) {
				return result;
		}
		// DBの会員情報テーブルにユーザーが存在するかチェック
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if (!userInfoDAO.isExistUser(userId, password)) {
			passwordIncorrectErrorMessage = "ユーザーIDまたは現在のパスワードが異なります。";
		} else {
		// 再設定パスワードの2桁以降＊で隠す
			CommonUtility commonUtility  = new CommonUtility();
			session.put("newPassword", newPassword);
			newPassword = commonUtility.concealPassword(newPassword);
			result = SUCCESS;
		}

		return result;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReConfirmation() {
		return reConfirmation;
	}

	public void setReConfirmation(String reConfirmation) {
		this.reConfirmation = reConfirmation;
	}

	public List<String> getUserIdErrorMessageList() {
		return userIdErrorMessageList;
	}

	public void setUserIdErrorMessageList(List<String> userIdErrorMessageList) {
		this.userIdErrorMessageList = userIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<String> getNewPasswordErrorMessageList() {
		return newPasswordErrorMessageList;
	}

	public void setNewPasswordErrorMessageList(List<String> newPasswordErrorMessageList) {
		this.newPasswordErrorMessageList = newPasswordErrorMessageList;
	}

	public List<String> getReConfirmationNewPasswordErrorMessageList() {
		return reConfirmationErrorMessageList;
	}

	public void setReConfirmationNewPasswordErrorMessageList(List<String> reConfirmationNewPasswordErrorMessageList) {
		this.reConfirmationErrorMessageList = reConfirmationNewPasswordErrorMessageList;
	}

	public String getPasswordIncorrectErrorMessage() {
		return passwordIncorrectErrorMessage;
	}

	public void setPasswordIncorrectErrorMessage(String passwordIncorectErrorMessage) {
		this.passwordIncorrectErrorMessage = passwordIncorectErrorMessage;
	}

	public String getNewPasswordIncorrectErrorMessage() {
		return newPasswordIncorrectErrorMessage;
	}

	public void setNewpasswordIncorrectErrorMessage(String newPasswordIncorectErrorMessage) {
		this.newPasswordIncorrectErrorMessage = newPasswordIncorectErrorMessage;
	}

	public String getConcealedPassword() {
		return concealedPassword;
	}

	public void setConcealedPassword(String concealedPassword) {
		this.concealedPassword = concealedPassword;
	}

	public Map <String,Object> getSession() {
		return session;
	}

	public void setSession(Map <String,Object> session) {
		this.session = session;
	}

}
