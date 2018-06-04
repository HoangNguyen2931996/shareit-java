package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDao;

/**
 * Servlet implementation class CommentController
 */
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		CommentDao commentDao = new CommentDao();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("Name");
		String email = request.getParameter("Email");
		String website = request.getParameter("Website");
		String content = request.getParameter("Content");
		int idNews = Integer.parseInt(request.getParameter("Idnews"));
		int idParent = Integer.parseInt(request.getParameter("Idparent"));
		Comment objComment = new Comment(0, content, username, email, website, null, idParent, idNews, "",1);
		if(commentDao.addItem(objComment) > 0){
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			tf.format(date.getTime());
			String str = "";
			str = 	"<div class='media response-info'>"+
					"<div class='media-left response-text-left'>"+
					"<a href=''>"+ 
						"<img class='media-object' src='"+ request.getContextPath() + "/templates/public/images/noavatar.png' alt='' />"+
					"</a>"+
					"<h5>"+
						"<a href=''>"+ objComment.getUsername() +"</a>"+
					"</h5>"+
					"</div>"+
					"<div class='media-body response-text-right'>"+
					"<p>"+ objComment.getContent() +"</p>"+
					"<ul>"+
					"<li>"+tf.format(date.getTime())+"</li>"+
				
					"</ul>"+
					"</div>"+
					"<div class='clearfix'></div>"+
					"</div>";
			out.print(str);
		}
	}

}
