package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.LibraryConnectDb;
import model.bean.User;

public class UserDao {
	private LibraryConnectDb lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDao(){
		lcdb = new LibraryConnectDb();
	}
	public ArrayList<User> getItems() {
		ArrayList<User> alUser = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM user";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String rank = rs.getString("rank");
				User objUser = new User(id, username, password, fullname, email, rank);
				alUser.add(objUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && st != null && conn != null){
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return alUser;
	}
	public int addItem(User objUser) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "INSERT INTO user(username, password, fullname, email, rank) VALUES(?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			pst.setString(3, objUser.getFullname());
			pst.setString(4, objUser.getEmail());
			pst.setString(5, objUser.getRank());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
	}
	public User getItem(int idUser) {
		User objUser = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String rank = rs.getString("rank");
				objUser = new User(id, username, password, fullname, email, rank);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return objUser;
	}
	public int editItem(User objUser) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE user SET password = ?, fullname = ?, email = ?, rank = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getPassword());
			pst.setString(2, objUser.getFullname());
			pst.setString(3, objUser.getEmail());
			pst.setString(4, objUser.getRank());
			pst.setInt(5, objUser.getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
	}
	public int delItem(int idUser) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM user WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(pst != null && conn != null){
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public User getUserLogin(User objUser) {
		User objUserLogin = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM user WHERE username = ? && password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String rank = rs.getString("rank");
				objUserLogin = new User(id, username, password, fullname, email, rank);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return objUserLogin;
	}
	public User checkUser(String username) {
		User objUser = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM user WHERE username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String Username = rs.getString("username");
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String rank = rs.getString("rank");
				objUser = new User(id, Username, password, fullname, email, rank);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs != null && pst != null && conn != null){
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return objUser;
	}
}
