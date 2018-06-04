package library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LibraryAuth {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		if(session.getAttribute("sobjUser") == null){
			response.sendRedirect(request.getContextPath()+ "/login");
			return false;
		}
		return true;
	}
}
