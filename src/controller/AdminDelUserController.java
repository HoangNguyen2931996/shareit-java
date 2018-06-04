package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelUserController() {
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
/*		HttpSession session = request.getSession();
		User sobjUser = (User) session.getAttribute("sobjUser");
		if("Admin".equals(sobjUser.getRank())){*/
			if(userDao.delItem(idUser) > 0){
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
				return;
			}
/*		} else{
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=5");
			return;
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
