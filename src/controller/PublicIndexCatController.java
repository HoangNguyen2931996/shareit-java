package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexCatController
 */
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexCatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsDao newsDao = new NewsDao();
		CatDao catDao = new CatDao();
		int idCat = Integer.parseInt(request.getParameter("cid"));
		int sumNews = newsDao.countNewsByID(idCat);
		int sumPage = (int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
		request.setAttribute("sumPage", sumPage);
		int currentPage = 1;
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("currentPage", currentPage);
		int offset = (currentPage -1)*Define.ROW_COUNT_ADMIN;
		request.setAttribute("alNews", newsDao.getItemsByIDPagination(offset, idCat));
		request.setAttribute("objCat", catDao.getItem(idCat));
		request.setAttribute("listCat", catDao.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("category.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


}
