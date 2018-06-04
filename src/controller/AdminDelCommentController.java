package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.dao.CommentDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelCommentController() {
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
		CommentDao cmtDao = new CommentDao();
		int idCmt = Integer.parseInt(request.getParameter("idCmt"));
		if(cmtDao.delItems(idCmt) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/comment?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/comment?msg=0");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcmt[] = request.getParameterValues("id_cmt");
		if(idcmt == null){
			response.sendRedirect(request.getContextPath()+"/admin/comment");
			return;
		}
		CommentDao cmtDao = new CommentDao();
		for(int i=0; i<idcmt.length; i++){
			int idCmt = Integer.parseInt(idcmt[i]);
			if(cmtDao.checkIdCmt(idCmt) != null){
				if(cmtDao.delItems(idCmt)>0){
					
				}else{
					response.sendRedirect(request.getContextPath() + "/admin/comment?msg=0");
					return;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/comment?msg=3");
		return;
	}

}
