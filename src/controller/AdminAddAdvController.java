package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.LibraryAuth;
import library.LibraryFile;
import model.bean.Adv;
import model.dao.AdvDao;

/**
 * Servlet implementation class AdminAddNewsController
 */
@MultipartConfig
public class AdminAddAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddAdvController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/addAdv.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdvDao advDao = new AdvDao();
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String link = new String(request.getParameter("link").getBytes("ISO-8859-1"), "UTF-8");
		String picture = "";
		//file 
		final String path = request.getServletContext().getRealPath("files");
		System.out.println(path);
		File dirFile = new File(path);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}

		final Part filePart = request.getPart("picture");
		final String fileName =  LibraryFile.getName(filePart);

		if(!"".equals(fileName)){ // có chọn ảnh
			OutputStream out = null;
			InputStream filecontent = null;
			picture = LibraryFile.rename(fileName) ;
			System.out.println(picture);
			try {
				out = new FileOutputStream(new File(path + File.separator + picture));
				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
		}
		Adv objAdv = new Adv(0, name, picture, link);
		if(advDao.addItem(objAdv) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/adv?msg=1");
			return;
		} else{
			response.sendRedirect(request.getContextPath() + "/admin/adv?msg=0");
			return;
		}
	}
}
