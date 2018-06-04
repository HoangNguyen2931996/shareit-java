package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.Cat;
import model.dao.CatDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
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
		request.setAttribute("alCat", catDao.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CatDao catDao = new CatDao();
		
		String nameCat = new String(request.getParameter("name_cat").getBytes("ISO-8859-1"),"UTF-8");
		int idParent = Integer.parseInt(request.getParameter("cat_parent"));
		Cat objCat = new Cat(0, nameCat, idParent);
		if(catDao.addCat(objCat) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=1");
			return;
		} else{
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
			return;
		}
	}

}
