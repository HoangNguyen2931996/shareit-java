package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import constant.Define;
import library.LibraryConnectDb;
import model.bean.Comment;

public class CommentDao {
	private LibraryConnectDb lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public CommentDao(){
		lcdb = new LibraryConnectDb();
	}
	public int addItem(Comment objComment){
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "INSERT INTO comment(content, username, email, website, id_parent, id_news, status_cmt) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objComment.getContent());
			pst.setString(2, objComment.getUsername());
			pst.setString(3, objComment.getEmail());
			pst.setString(4, objComment.getWebsite());
			pst.setInt(5, objComment.getId_parent());
			pst.setInt(6, objComment.getId_news());
			pst.setInt(7, objComment.getStatus_cmt());
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
	public ArrayList<Comment> getItemsComment(){
		ArrayList<Comment> alComment = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news WHERE id_parent = 0 && status_cmt = 1 ORDER BY date_cmt DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String content = rs.getString("content");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objComment = new Comment(id_cmt, content, username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alComment.add(objComment);
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
		return alComment;
	}
	public ArrayList<Comment> getItems(){
		ArrayList<Comment> alComment = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news ORDER BY date_cmt DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String content = rs.getString("content");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objComment = new Comment(id_cmt, content, username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alComment.add(objComment);
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
		return alComment;
	}
	public ArrayList<Comment> getItemsReply() {
		ArrayList<Comment> alReply = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news WHERE id_parent != 0 && status_cmt = 1 ORDER BY date_cmt ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String content = rs.getString("content");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objReply = new Comment(id_cmt, content, username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alReply.add(objReply);
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
		return alReply;
	}
	public ArrayList<Comment> getCommentRecent(){
		ArrayList<Comment> alComment = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news WHERE id_parent = 0 && status_cmt = 1 ORDER BY date_cmt DESC LIMIT 10";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String content = rs.getString("content");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objComment = new Comment(id_cmt, content, username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alComment.add(objComment);
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
		return alComment;
	}
	public int countComment(int idNews){
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT COUNT(*) As count FROM comment WHERE id_parent = 0 && id_news = ? && status_cmt = 1";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			if(rs.next()){
				result = rs.getInt("count");
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
		return result;
	}
	public int delItems(int idCmt) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM comment WHERE id_cmt = ? OR id_parent = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCmt);
			pst.setInt(2, idCmt);
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
	public int updateStatus(int idCmt, int status) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE comment SET status_cmt = ? WHERE id_cmt = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, idCmt);
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
	public int delItemsByidNews(int idNews) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM comment WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
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
	public int countCmt() {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT COUNT(*) As countCmt FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("countCmt");
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
		return result;
	}
	public ArrayList<Comment> getItemsPagination(int offset) {
		ArrayList<Comment> alComment = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news ORDER BY date_cmt DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String content = rs.getString("content");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objComment = new Comment(id_cmt, content, username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alComment.add(objComment);
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
		return alComment;
	}
	public ArrayList<Comment> searchItems(String nameNews, String username, String content) {
		ArrayList<Comment> alComment = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news WHERE (n.name LIKE ? OR ? = '') AND (username LIKE ? OR ? = '') AND (content LIKE ? OR ? = '')";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+nameNews+"%");
			pst.setString(2, nameNews);
			pst.setString(3, "%"+username+"%");
			pst.setString(4, username);
			pst.setString(5, "%"+content+"%");
			pst.setString(6, content);
			rs = pst.executeQuery();
			while(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String Content = rs.getString("content");
				String Username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				Comment objComment = new Comment(id_cmt, Content, Username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
				alComment.add(objComment);
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
		return alComment;
	}
	public Comment checkIdCmt(int idCmt) {
		Comment objCmt = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM comment As c INNER JOIN news As n ON c.id_news = n.id_news WHERE id_cmt = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCmt);
			rs = pst.executeQuery();
			if(rs.next()){
				int id_cmt = rs.getInt("id_cmt");
				String Content = rs.getString("content");
				String Username = rs.getString("username");
				String email = rs.getString("email");
				String website = rs.getString("website");
				Timestamp date_cmt = rs.getTimestamp("date_cmt");
				int id_parent = rs.getInt("id_parent");
				int id_news = rs.getInt("id_news");
				String name_news = rs.getString("name");
				int status_cmt = rs.getInt("status_cmt");
				objCmt = new Comment(id_cmt, Content, Username, email, website, date_cmt, id_parent, id_news, name_news, status_cmt);
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
		return objCmt;
	}
}
