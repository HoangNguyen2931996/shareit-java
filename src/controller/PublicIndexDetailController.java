package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.bean.News;
import model.dao.CatDao;
import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
@WebServlet("/PublicIndexController")
public class PublicIndexDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		NewsDao newsDao = new NewsDao();
		CatDao catDao = new CatDao();
		CommentDao commentDao = new CommentDao();
		int idNews = Integer.parseInt(request.getParameter("nid"));
		String scountId = "scountID"+idNews;
		News objNews = newsDao.getItem(idNews);
		if(session.getAttribute("scountId") == null){
			newsDao.updateRead(objNews);
			session.setAttribute(scountId, idNews);
		}
		ArrayList<News> alNewsLQ = newsDao.getItemsLQ(idNews, newsDao.getItem(idNews).getIdCat());
		request.setAttribute("alNewsLQ", alNewsLQ);
		ArrayList<Comment> alComment = commentDao.getItemsComment();
		ArrayList<Comment> alReply = commentDao.getItemsReply();
		request.setAttribute("alReply", alReply);
		request.setAttribute("alComment", alComment);
		request.setAttribute("objNews", objNews);
		request.setAttribute("listCat", catDao.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
