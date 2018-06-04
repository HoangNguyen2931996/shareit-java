package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.Comment;
import model.dao.CommentDao;

/**
 * Servlet implementation class AdminSearchNewsController
 */
public class AdminSearchCmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchCmtController() {
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
		CommentDao cmtDao = new CommentDao();
		String nameNews = new String(request.getParameter("name_news").getBytes("ISO-8859-1"), "UTF-8");
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String content = new String(request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8");
		if("".equals(username) && "".equals(nameNews) && "".equals(content)){
			response.sendRedirect(request.getContextPath() + "/admin/comment");
			return;
		}
		ArrayList<Comment> alCmt = cmtDao.searchItems(nameNews, username, content);
		request.setAttribute("alCmt", alCmt);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/resultSearchCmt.jsp");
		rd.forward(request, response);
	}

}
