package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.LibraryString;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminAddNewsController
 */
@MultipartConfig
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
		String rank = "";
		int idRank = Integer.parseInt(request.getParameter("rank"));
		if(idRank == 1){
			rank = "Admin";
		} else if(idRank == 2){
			rank = "Mod";
		} else{
			rank = "Trial Mod";
		}
		String password = LibraryString.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8"));
		String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
		User objUser = new User(0, username, password, fullname, email, rank);
		if(userDao.checkUser(username) != null){
			response.sendRedirect(request.getContextPath() + "/admin/user/add?msg=0");
			return;
		} else{
			if(userDao.addItem(objUser) > 0){
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=1");
				return;
			} else{
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
				return;
			}
		}
	}
}
