package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.dao.AdvDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminDelAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelAdvController() {
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
		AdvDao advDao = new AdvDao();
		int idAdv = Integer.parseInt(request.getParameter("aid"));
		String picture = advDao.getItem(idAdv).getPicture();
		if(advDao.delItem(idAdv) > 0){
			if(!"".equals(picture)){
				String urlPic = request.getServletContext().getRealPath("files") + File.separator + picture;
				File file = new File(urlPic);
				file.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/adv?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/adv?msg=0");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
