package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import library.LibraryAuth;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexNewsController
 */
public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexNewsController() {
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
		NewsDao newsDao = new NewsDao();
		int sumNews = newsDao.countNews();
		int sumPage = (int)Math.ceil((float)sumNews/Define.ROW_COUNT_ADMIN);
		request.setAttribute("sumPage", sumPage);
		int currentPage = 1;
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("currentPage", currentPage);
		int offset = (currentPage -1)*Define.ROW_COUNT_ADMIN;
		request.setAttribute("alNews", newsDao.getItemsPagination(offset));
		request.setAttribute("alCat", catDao.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexNews.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int idNews = Integer.parseInt(request.getParameter("idnews"));
		int status = Integer.parseInt(request.getParameter("status"));
		NewsDao newsDao = new NewsDao();
		String str = "";
		if(newsDao.updateStatus(idNews, status)>0){
			if(status == 1){
				str = "<img id='"+ idNews +"' class='turnOn' style='padding-left: 40px;' alt='' src='"+ request.getContextPath() +"/templates/admin/images/notification-tick.gif' onclick='return turnOn(this.id)'>";
				out.print(str);
				return;
			} else{
				str = "<img id='"+ idNews +"' class='turnOff' style='padding-left: 40px;' alt='' src='"+ request.getContextPath() +"/templates/admin/images/minus-circle.gif' onclick='return turnOff(this.id)'>";
				out.print(str);
				return;
			}
		}
	}

}
