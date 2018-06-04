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
import model.dao.CommentDao;

/**
 * Servlet implementation class AdminIndexCommentController
 */
public class AdminIndexCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(LibraryAuth.checkLogin(request, response) == false){
			return;
		}
		CommentDao cmtDao = new CommentDao();
		int sumCmt = cmtDao.countCmt();
		int sumPage = (int)Math.ceil((float)sumCmt/Define.ROW_COUNT_ADMIN);
		request.setAttribute("sumPage", sumPage);
		int currentPage = 1;
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		request.setAttribute("currentPage", currentPage);
		int offset = (currentPage -1)*Define.ROW_COUNT_ADMIN;
		request.setAttribute("alCmt", cmtDao.getItemsPagination(offset));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexComment.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int idCmt = Integer.parseInt(request.getParameter("idcmt"));
		int status = Integer.parseInt(request.getParameter("status"));
		CommentDao cmtDao  = new CommentDao();
		String str = "";
		if(cmtDao.updateStatus(idCmt, status)>0){
			if(status == 1){
				str = "<img class='turnOn' style='padding-left: 40px;' alt='' src='"+ request.getContextPath() +"/templates/admin/images/notification-tick.gif' onclick='return turnOn("+idCmt+")'>";
				out.print(str);
				return;
			} else{
				str = "<img class='turnOff' style='padding-left: 40px;' alt='' src='"+ request.getContextPath() +"/templates/admin/images/minus-circle.gif' onclick='return turnOff("+idCmt+")'>";
				out.print(str);
				return;
			}
		}
	}

}
