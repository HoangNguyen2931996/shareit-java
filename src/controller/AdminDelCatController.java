package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.News;
import model.dao.CatDao;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCatController() {
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
		CatDao catDao = new CatDao();
		CommentDao cmtDao = new CommentDao();
		int idCat = Integer.parseInt(request.getParameter("cid"));
		int idParent = catDao.getItem(idCat).getIdParent();
		NewsDao newsDao = new NewsDao();
		ArrayList<News> alNews = newsDao.getItemsByID(idCat);
		if(alNews.size()>0){
			for(News objNews: alNews){
				if(newsDao.delItemByIdCat(objNews.getIdCat())>0){
						if(!"".equals(objNews.getPicture())){
							String filePath = request.getServletContext().getRealPath("files")+ File.separator + objNews.getPicture();
							File file = new File(filePath);
							file.delete();
						}
					}
				if(cmtDao.delItemsByidNews(objNews.getIdNews())>0){
					
				}
			}
		}
		if(catDao.delItem(idCat) > 0){
			catDao.updateItem(idCat, idParent);
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
