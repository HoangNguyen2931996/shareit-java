package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.News;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminSearchNewsController
 */
public class AdminSearchNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchNewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(LibraryAuth.checkLogin(request, response) == false){
			return;
		}
		NewsDao newsDao = new NewsDao();
		String nameNews = new String(request.getParameter("name_news").getBytes("ISO-8859-1"), "UTF-8");
		int idCat = Integer.parseInt(request.getParameter("cat_list"));
		if("".equals(nameNews) && idCat == 0){
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		ArrayList<News> alNews = newsDao.searchNews(nameNews, idCat);
		request.setAttribute("alNews", alNews);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/resultSearchNews.jsp");
		rd.forward(request, response);
	}

}
