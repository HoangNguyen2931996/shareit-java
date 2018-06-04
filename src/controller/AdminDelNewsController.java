package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(LibraryAuth.checkLogin(request, response) == false){
			return;
		}
		NewsDao newsDao = new NewsDao();
		CommentDao cmtDao = new CommentDao();
		int idNews = Integer.parseInt(request.getParameter("nid"));
		String picture = newsDao.getItem(idNews).getPicture();
		if(cmtDao.delItemsByidNews(idNews)>0){
			
		}
		if(newsDao.delItem(idNews) > 0){
			if(!"".equals(picture)){
				String urlPic = request.getServletContext().getRealPath("files") + File.separator + picture;
				File file = new File(urlPic);
				file.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNews[] = request.getParameterValues("id_news");
		CommentDao cmtDao  = new CommentDao();
		NewsDao newsDao = new NewsDao();
		if(idNews == null){
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		for(int i=0; i<idNews.length; i++){
			int idnews = Integer.parseInt(idNews[i]);
			String picture = newsDao.getItem(idnews).getPicture();
			if(cmtDao.delItemsByidNews(idnews)>0){
				
			}
			if(newsDao.delItem(idnews) > 0){
				if(!"".equals(picture)){
					String urlPic = request.getServletContext().getRealPath("files") + File.separator + picture;
					File file = new File(urlPic);
					file.delete();
				}
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
				return;
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
		return;
	}

}
