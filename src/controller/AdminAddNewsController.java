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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.LibraryAuth;
import library.LibraryFile;
import model.bean.News;
import model.bean.User;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminAddNewsController
 */
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddNewsController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/admin/addNews.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsDao newsDao = new NewsDao();
		HttpSession session = request.getSession();
		User sobjUser = (User) session.getAttribute("sobjUser");
		String nameNews = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		int slide = 1;
		if(request.getParameter("slide")==null){
			slide = 0;
		}
		int idCat = Integer.parseInt(request.getParameter("list_cat"));
		String preview = new String(request.getParameter("preview").getBytes("ISO-8859-1"), "UTF-8");
		String detail = new String(request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8");
		String picture = "";
		//file 
		final String path = request.getServletContext().getRealPath("files");
		System.out.println(path);
		File dirFile = new File(path);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}

		final Part filePart = request.getPart("picture");
		System.out.println(filePart);
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
		News objNews = new News(0, nameNews, preview, detail, null, sobjUser.getRank(),  picture, "", idCat, 0, slide, 1, 0);
		if(newsDao.addItem(objNews) > 0){
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		} else{
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
	}
}
