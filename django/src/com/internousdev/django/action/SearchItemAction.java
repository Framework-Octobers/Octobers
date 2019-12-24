package com.internousdev.django.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.django.dao.MCategoryDAO;
import com.internousdev.django.dao.ProductInfoDAO;
import com.internousdev.django.dto.MCategoryDTO;
import com.internousdev.django.dto.ProductInfoDTO;
import com.internousdev.django.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware {

	private String categoryId;
	private String keywords;
	private int productSort;
	private List<String> keywordsErrorMessageList;
	private List<ProductInfoDTO> productInfoDTOList;
	private List<ProductInfoDTO> productRankDTOList;
	private Map<String, Object> session;

	public String execute() {

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		if (productSort == 0) {
			if (StringUtils.isBlank(keywords)) {
				keywords = "";
			} else {
				InputChecker inputChecker = new InputChecker();
				keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords,0,50, true, true, true, true, true, true);

				if (keywordsErrorMessageList.size() > 0) {
					return SUCCESS;
				}
				keywords = keywords. replaceAll("　", " "). replaceAll("\\s{2,}", " ").trim();
			}
			session.put("keywords", keywords);
		} else {
			keywords =  String.valueOf(session.get("keywords"));
		}

		if (productSort > 0) {
			categoryId = String.valueOf(session.get("categoryId"));
		} else if (categoryId == null) {
			categoryId = "1";
		}

		session.put("categoryId", categoryId);

		//ランキング用
		if(productSort == 3) {
			productRankDTOList = productInfoDAO.getProductInfoRank();
		}
		if (categoryId == null) {
			categoryId = "1";
		}

		switch (categoryId) {
		case "1":
			productInfoDTOList = productInfoDAO.getProductInfoListByKeyword(keywords.split(" "), productSort);
			break;
		default:
			productInfoDTOList = productInfoDAO.getProductInfoListByCategoryIdAndKeyword(categoryId, keywords.split(" "),productSort);
			break;
		}

		if (!session.containsKey("mCategoryDTOList")) {
			List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryInfo();

			session.put("mCategoryDTOList", mCategoryDTOList);
		}

		return SUCCESS;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getProductSort() {
		return productSort;
	}

	public void setProductSort(int productSort) {
		this.productSort = productSort;
	}

	public List<String> getKeywordsErrorMessageList() {
		return keywordsErrorMessageList;
	}

	public void setKeywordsErrorMessageList(List<String> keywordsErrorMessageList) {
		this.keywordsErrorMessageList = keywordsErrorMessageList;
	}

	public List<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<ProductInfoDTO> getProductRankDTOList() {
		return productRankDTOList;
	}

	public void setProductRankDTOList(List<ProductInfoDTO> productRankDTOList) {
		this.productRankDTOList = productRankDTOList;
	}

}