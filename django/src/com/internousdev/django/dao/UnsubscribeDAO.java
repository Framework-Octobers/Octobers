package com.internousdev.django.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.django.dto.UserInfoDTO;
import com.internousdev.django.util.DBConnector;

public class UnsubscribeDAO {
	public ArrayList<UserInfoDTO> getUserInfo(String userId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<UserInfoDTO> UserInfoDTOList = new ArrayList<UserInfoDTO>();

		String sql = "select * from user_info where user_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserInfoDTO dto = new UserInfoDTO();
				dto.setUserId(rs.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return UserInfoDTOList;
	}

	public int userDelete(String userId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "delete from user_info where user_id = ?";
		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}

}
