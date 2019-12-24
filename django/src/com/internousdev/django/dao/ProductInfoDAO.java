package com.internousdev.django.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.django.dto.ProductInfoDTO;
import com.internousdev.django.util.DBConnector;

public class ProductInfoDAO {

	//商品IDをもとに商品情報を取得するメソッド
	public ProductInfoDTO getProductInfo(String productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		String sql = "SELECT * FROM product_info where  product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, productId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setProductImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setProductPrice(rs.getInt("price"));
				productInfoDTO.setProductReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setProductReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setProductCategoryId(rs.getInt("category_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTO;
	}

	/**
	 * 関連商品を取得する。
	 *
	 * @param categoryId
	 *            カテゴリーID
	 * @param productId
	 *            商品ID
	 * @param limitOffset
	 *            データを取得する開始位置
	 * @param limitRowCount
	 *            データ取得件数
	 * @return 関連商品情報
	 */
	public List<ProductInfoDTO> getRelatedProductList(int categoryId, int productId, int limitOffset,
			int limitRowCount) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?, ?";

		/**
		 * round():表示順をランダムにする。 limit 0,3:0番目から3件データを取得する
		 */

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, limitOffset);
			preparedStatement.setInt(4, limitRowCount);
			ResultSet resultSet = preparedStatement.executeQuery();
			// System.out.println(preparedStatement);
			while (resultSet.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setProductCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setProductPrice(resultSet.getInt("price"));
				productInfoDTO.setProductImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setProductImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setProductReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setProductReleaseCompany(resultSet.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("size"+productInfoDTOList.size());
		return productInfoDTOList;
	}

	/**
	 * キーワードを条件に商品情報を取得する
	 *
	 * @param keywordsList
	 *            キーワードの配列
	 * @return 商品情報のList
	 */
	public List<ProductInfoDTO> getProductInfoListByKeyword(String[] keywordsList, int productSort) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql = "select * from product_info";

		// Actionで処理しているため、要素として""(空文字)が存在する場合は、
		// 検索ワードを指定していないときのみなので、0番目が空文字の場合のみWhere句を追加しないようにする。
		if (!keywordsList[0].equals("")) {

			// ここで拡張for文を使わない理由：indexを使用した方がわかりやすいため
			for (int i = 0; i < keywordsList.length; i++) {
				if (i == 0) {
					sql += " where (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%"
							+ keywordsList[i] + "%')";
				} else {
					sql += " or (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%"
							+ keywordsList[i] + "%')";
				}
			}
		}

		if(productSort == 1) {
			sql += " order by price asc";
		}else if(productSort == 2){
			sql += " order by price desc";
		}else {
			sql += " order by product_id asc";
		}

//		System.out.println(sql+"カテゴリーなし");

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setProductCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setProductPrice(resultSet.getInt("price"));
				productInfoDTO.setProductImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setProductImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setProductReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setProductReleaseCompany(resultSet.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	/**
	 * カテゴリーIDとキーワードを条件に商品情報を取得する
	 *
	 * @param categoryId
	 *            カテゴリーID
	 * @param keywordsList
	 *            キーワードの配列
	 * @return 商品情報のList
	 */
	public List<ProductInfoDTO> getProductInfoListByCategoryIdAndKeyword(String categoryId, String[] keywordsList, int productSort) {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();
		// System.out.println(categoryId);
		String sql = "select * from product_info where category_id = " + categoryId;
		// System.out.println(sql);
		// Actionで処理しているため、要素として""(空文字)が存在する場合は、
		// 検索ワードを指定していないときのみなので、0番目が空文字の場合のみWhere句を追加しないようにする。
		if (!keywordsList[0].equals("")) {

			// ここで拡張for文を使わない理由：indexを使用した方がわかりやすいため
			for (int i = 0; i < keywordsList.length; i++) {
				if (i == 0) {
					sql += " and ((product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%"
							+ keywordsList[i] + "%')";
				} else {
					sql += " or (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%"
							+ keywordsList[i] + "%')";
				}
			}

			sql += ")";
		}


		if(productSort == 1) {
			sql += " order by price asc";
		}else if(productSort == 2){
			sql += " order by price desc";
		}else {
			sql += " order by product_id asc";
		}

		System.out.println(sql+"カテゴリーあり");

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(resultSet.getInt("id"));
				productInfoDTO.setProductId(resultSet.getInt("product_id"));
				productInfoDTO.setProductName(resultSet.getString("product_name"));
				productInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				productInfoDTO.setProductDescription(resultSet.getString("product_description"));
				productInfoDTO.setProductCategoryId(resultSet.getInt("category_id"));
				productInfoDTO.setProductPrice(resultSet.getInt("price"));
				productInfoDTO.setProductImageFilePath(resultSet.getString("image_file_path"));
				productInfoDTO.setProductImageFileName(resultSet.getString("image_file_name"));
				productInfoDTO.setProductReleaseDate(resultSet.getDate("release_date"));
				productInfoDTO.setProductReleaseCompany(resultSet.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//商品の売り上げ個数を取得
	public List<ProductInfoDTO> getProductInfoRank() {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<ProductInfoDTO> productRankDTOList = new ArrayList<ProductInfoDTO>();
		// System.out.println(categoryId);
		String sql = "select pi.product_id, pi.product_name, pi.product_name_kana, pi.product_description,"
				+ " pi.category_id, pi.price, pi.image_file_path, pi.image_file_name, pi.release_date,"
				+ " pi.release_company, sum(phi.product_count) as productPurchaseTotal from product_info as pi"
				+ " right join purchase_history_info as phi on pi.product_id = phi.product_id group by pi.product_id"
				+ " order by productPurchaseTotal DESC";

		System.out.println(sql);

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProductInfoDTO productRankDTO = new ProductInfoDTO();
				productRankDTO.setProductId(resultSet.getInt("pi.product_id"));
				productRankDTO.setProductName(resultSet.getString("pi.product_name"));
				productRankDTO.setProductNameKana(resultSet.getString("pi.product_name_kana"));
				productRankDTO.setProductDescription(resultSet.getString("pi.product_description"));
				productRankDTO.setProductCategoryId(resultSet.getInt("pi.category_id"));
				productRankDTO.setProductPrice(resultSet.getInt("pi.price"));
				productRankDTO.setProductImageFilePath(resultSet.getString("pi.image_file_path"));
				productRankDTO.setProductImageFileName(resultSet.getString("pi.image_file_name"));
				productRankDTO.setProductReleaseDate(resultSet.getDate("pi.release_date"));
				productRankDTO.setProductReleaseCompany(resultSet.getString("pi.release_company"));
				productRankDTO.setProductPurchaseTotal(resultSet.getString("productPurchaseTotal"));
				productRankDTOList.add(productRankDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productRankDTOList;
	}
}
