package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.bean.Adv;
import model.bean.News;
import model.dao.AdvDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewsDao newsDao = new NewsDao();
		AdvDao advDao = new AdvDao();
		int sumNews = newsDao.countNewsByStatus();
		int sumPage = (int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
		request.setAttribute("sumPage", sumPage);
		int currentPage = 1;
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("currentPage", currentPage);
		int offset = (currentPage -1)*Define.ROW_COUNT_ADMIN;
		request.setAttribute("alNews", newsDao.getItemsPaginationByStatus(offset));
		ArrayList<News> alNewsSlide = newsDao.getItemsBySlide();
		ArrayList<Adv> alAdv = advDao.getItems();
		request.setAttribute("alNewsSlide", alNewsSlide);
		request.setAttribute("alAdv", alAdv);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
