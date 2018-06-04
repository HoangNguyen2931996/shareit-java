package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.LibraryConnectDb;
import model.bean.Adv;

public class AdvDao {
	private LibraryConnectDb lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public AdvDao(){
		lcdb = new LibraryConnectDb();
	}
	public ArrayList<Adv> getItems() {
		ArrayList<Adv> alAdv = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM advertisement";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idAdv = rs.getInt("id");
				String nameAdv = rs.getString("name");
				String picture = rs.getString("picture");
				String link = rs.getString("link");
				Adv objAdv = new Adv(idAdv, nameAdv, picture, link);
				alAdv.add(objAdv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(rs!=null && st!=null && conn!=null){
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
		return alAdv;
	}
	public int addItem(Adv objAdv) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "INSERT INTO advertisement(name, picture, link) VALUE(?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdv.getNameAdv());
			pst.setString(2, objAdv.getPicture());
			pst.setString(3, objAdv.getLink());
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
	public Adv getItem(int idAdv) {
		Adv objAdv = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM advertisement WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idAdv);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String nameAdv = rs.getString("name");
				String picture = rs.getString("picture");
				String link = rs.getString("link");
				objAdv = new Adv(id, nameAdv, picture, link);
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
		return objAdv;
	}
	public int editItem(Adv objAdv) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE advertisement SET name = ?, picture = ?, link = ? WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objAdv.getNameAdv());
			pst.setString(2, objAdv.getPicture());
			pst.setString(3, objAdv.getLink());
			pst.setInt(4, objAdv.getIdAdv());
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
	public int delItem(int idAdv) {
		// TODO Auto-generated method stub
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM advertisement WHERE id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idAdv);
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
	
}
