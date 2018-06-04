package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryString;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		UserDao userDao = new UserDao();
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String password = LibraryString.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8"));
		User objUser = new User(0, username, password, "", "", "");
		User objUserLogin = userDao.getUserLogin(objUser);
		if(objUserLogin != null){
			HttpSession session = request.getSession();
			session.setAttribute("sobjUser", objUserLogin);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		} else{
			response.sendRedirect(request.getContextPath() + "/login?msg=1");
			return;
		}
	}

}
