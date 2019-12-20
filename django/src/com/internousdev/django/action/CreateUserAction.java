package com.internousdev.django.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//新規ユーザー入力画面に移動する
public class CreateUserAction extends ActionSupport implements SessionAware {
	private String backFlg;
	private Map<String, Object> session;

	public String execute() {
		//戻るボタンから戻るときのみ入力情報を保持する
		//戻るボタン以外から戻ったとき入力情報は消した状態にする
		if (backFlg == null) {
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("sex");
			session.remove("sexList");
			session.remove("email");
			session.remove("userIdForCreateUser");
			session.remove("password");
		}

		//画面表示時に選択されている性別を取得
		if (!session.containsKey("sex")) {
			session.put("sex", "男性");
		} else {
			session.put("sex", String.valueOf(session.get("sex")));
		}
		//選択肢を生成
		if (!session.containsKey("sexList")) {
			List<String> sexList = new ArrayList<String>();
			sexList.add("男性");
			sexList.add("女性");
			session.put("sexList", sexList);
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