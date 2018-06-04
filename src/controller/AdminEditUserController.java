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
public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserController() {
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
		UserDao userDao = new UserDao();
		int idUser = Integer.parseInt(request.getParameter("uid"));
	/*	HttpSession session = request.getSession();
		User sobjUser = (User) session.getAttribute("sobjUser");
		if("Admin".equals(sobjUser.getRank()) || idUser == sobjUser.getId()){*/
			request.setAttribute("objUser", userDao.getItem(idUser));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp");
			rd.forward(request, response);
/*		} else{
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=5");
			return;
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		int idUser = Integer.parseInt(request.getParameter("uid"));
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
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
		if("".equals(password)){
			password = userDao.getItem(idUser).getPassword();
		} else{
			password = LibraryString.md5(password);
		}
		String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");
		User objUser = new User(idUser, "", password, fullname, email, rank);
		if(userDao.editItem(objUser) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
			return;
		} else{
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
			return;
		}
	}
}
