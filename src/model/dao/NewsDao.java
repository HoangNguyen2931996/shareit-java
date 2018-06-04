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
import model.bean.News;

public class NewsDao {
	private LibraryConnectDb lcdb;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	public NewsDao(){
		lcdb = new LibraryConnectDb();
	}
	public ArrayList<News> getItems(){
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat ORDER BY n.id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public int updateStatus(int idNews, int status) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE news SET status = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, idNews);
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
	public int addItem(News objNews) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "INSERT INTO news(name, preview, detail, created_by, picture, id_cat, is_slide, status, read_news) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getNameNews());
			pst.setString(2, objNews.getPreview());
			pst.setString(3, objNews.getDetail());
			pst.setString(4, objNews.getCreateBy());
			pst.setString(5, objNews.getPicture());
			pst.setInt(6, objNews.getIdCat());
			pst.setInt(7, objNews.getIsSlide());
			pst.setInt(8, objNews.getStatus());
			pst.setInt(9, objNews.getReadNews());
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
	public News getItem(int idNews) {
		News objNews = null;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				objNews = new News(id, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
			}
		} catch (SQLException e) {
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
		return objNews;
	}
	public int editItem(News objNews) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "UPDATE news SET name = ?, preview = ?, detail = ?, picture = ?, id_cat = ?, is_slide = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getNameNews());
			pst.setString(2, objNews.getPreview());
			pst.setString(3, objNews.getDetail());
			pst.setString(4, objNews.getPicture());
			pst.setInt(5, objNews.getIdCat());
			pst.setInt(6, objNews.getIsSlide());
			pst.setInt(7, objNews.getIdNews());
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
	public int delItem(int idNews) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM news WHERE id_news = ?";
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
	public ArrayList<News> getItemsByID(int idCat) {
		// TODO Auto-generated method stub
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE n.id_cat = ? && status = 1 ORDER BY n.id_news DESC";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int id = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, id, idParent, isSlide, status, readNews);
				alNews.add(objNews);
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
		
		return alNews;
	}
	public ArrayList<News> getItemsByStatus() {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 ORDER BY n.id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public ArrayList<News> getItemsLQ(int idNews, int idCat) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE id_news != ? && n.id_cat = ? && status = 1 ORDER BY n.id_news DESC LIMIT 4";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			pst.setInt(2, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				int idnews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idcat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idnews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idcat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public ArrayList<News> getItemsBySlide() {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE is_slide = 1 && status = 1 ORDER BY n.id_news DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null && st !=null && conn!=null){
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
		return alNews;
	}
	public ArrayList<News> getNewsByRead(){
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 ORDER BY n.read_news DESC LIMIT 10";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public ArrayList<News> getNewsByDate(){
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 ORDER BY n.date_created DESC LIMIT 10";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null && st != null && conn != null){	
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		return alNews;
	}
	public ArrayList<News> getNewsRand(){
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 ORDER BY RAND() LIMIT 3";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public int delItemByIdCat(int idCat) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "DELETE FROM news WHERE id_cat = ?";
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
	public int countNews() {
		// TODO Auto-generated method stub
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT COUNT(*) As sumNews FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("sumNews");
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
	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat ORDER BY n.id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public int countNewsByStatus() {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT COUNT(*) As sumNews FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("sumNews");
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
	public ArrayList<News> getItemsPaginationByStatus(int offset) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 ORDER BY n.id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idCat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idCat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public int countNewsByID(int idCat) {
		int result = 0;
		conn = lcdb.getConnectMySql();
		String sql = "SELECT COUNT(*) As sumNews FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 AND n.id_cat = ? OR c.id_parent = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.setInt(2, idCat);
			rs = pst.executeQuery();
			if(rs.next()){
				result = rs.getInt("sumNews");
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
	public Object getItemsByIDPagination(int offset, int idCat) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE status = 1 AND n.id_cat = ? OR c.id_parent = ? ORDER BY n.id_news DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.setInt(2, idCat);
			pst.setInt(3, offset);
			pst.setInt(4, Define.ROW_COUNT_ADMIN);
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idcat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idcat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public void updateRead(News objNews) {
		conn = lcdb.getConnectMySql();
			String sql = "UPDATE news SET read_news = ? WHERE id_news = ?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, objNews.getReadNews()+1);
				pst.setInt(2, objNews.getIdNews());
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
	public ArrayList<News> searchItems(String search) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE name LIKE ? OR preview LIKE ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+search+"%");
			pst.setString(2, "%"+search+"%");
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String nameNews = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idcat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, nameNews, preview, detail, dateCreate, createBy, picture, nameCat, idcat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
	public ArrayList<News> searchNews(String nameNews, int idCat) {
		ArrayList<News> alNews = new ArrayList<>();
		conn = lcdb.getConnectMySql();
		String sql = "SELECT * FROM News As n INNER JOIN category As c ON n.id_cat = c.id_cat WHERE (name LIKE ? OR ? = '') AND (n.id_cat = ? OR ? = 0)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+nameNews+"%");
			pst.setString(2, nameNews);
			pst.setInt(3, idCat);
			pst.setInt(4, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				int idNews = rs.getInt("id_news");
				String name = rs.getString("name");
				String preview = rs.getString("preview");
				String detail = rs.getString("detail");
				Timestamp dateCreate = rs.getTimestamp("date_created");
				String createBy = rs.getString("created_by");
				String picture = rs.getString("picture");
				String nameCat = rs.getString("name_cat");
				int idcat = rs.getInt("id_cat");
				int idParent = rs.getInt("id_parent");
				int isSlide = rs.getInt("is_slide");
				int status = rs.getInt("status");
				int readNews = rs.getInt("read_news");
				News objNews = new News(idNews, name, preview, detail, dateCreate, createBy, picture, nameCat, idcat, idParent, isSlide, status, readNews);
				alNews.add(objNews);
			}
		} catch (SQLException e) {
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
		return alNews;
	}
}
