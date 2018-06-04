package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.LibraryConnectDb;
import model.bean.Cat;

public class CatDao {
	private LibraryConnectDb lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public CatDao(){
		lcdb = new LibraryConnectDb();
	}
	public ArrayList<Cat> getItems(){
		ArrayList<Cat> alCat = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM category";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idCat = rs.getInt("id_cat");
				String nameCat = rs.getString("name_cat");
				int idParent = rs.getInt("id_parent");
				Cat objCat = new Cat(idCat, nameCat, idParent);
				alCat.add(objCat);
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
		return alCat;
	}
	public int addCat(Cat objCat) {
		// TODO Auto-generated method stub
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "INSERT INTO category(name_cat, id_parent) VALUE(?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getNameCat());
			pst.setInt(2, objCat.getIdParent());
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
	public Cat getItem(int idCat) {
		// TODO Auto-generated method stub
		Cat objCat = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM category WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id_cat");
				String nameCat = rs.getString("name_cat");
				int idParent = rs.getInt("id_parent");
				objCat = new Cat(id, nameCat, idParent);
			}
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
		return objCat;
	}
	public ArrayList<Cat> checkChild(int idCat){
		ArrayList<Cat> alCat = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM category WHERE id_parent = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id_cat");
				String nameCat = rs.getString("name_cat");
				int idParent = rs.getInt("id_parent");
				Cat objCat = new Cat(id, nameCat, idParent);
				alCat.add(objCat);
			}
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
		return alCat;
	}
	public int editCat(Cat objCat) {
		// TODO Auto-generated method stub
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE category SET name_cat = ?, id_parent = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getNameCat());
			pst.setInt(2, objCat.getIdParent());
			pst.setInt(3, objCat.getIdCat());
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
	public int delItem(int idCat) {
		// TODO Auto-generated method stub
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM category WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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
	public void updateItem(int idCat, int idParent) {
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE category SET id_parent = ? WHERE id_parent = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idParent);
			pst.setInt(2, idCat);
			pst.executeUpdate();
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
	}
}
