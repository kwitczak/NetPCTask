package NetPC.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NetPC.service.LoginService;

import com.google.gson.Gson;


/**
 * Servlet implementation class UpdateUsername
 */
@WebServlet("/update")
public class UpdateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsername() {
        super();

    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String, Object> map = new HashMap<String, Object>();
		boolean isValid = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginService loginService = new LoginService();
		isValid = loginService.authenticate(username, password);
		
		if(isValid){
			map.put("username", username);
		} else {
			boolean[] whyWrong = loginService.getNiepoprawne();
			map.put("login",whyWrong[0]);
			map.put("password",whyWrong[1]);
		}
		map.put("isValid", isValid);
		ajaxRespond(response, map);
		
	}

	private void ajaxRespond(HttpServletResponse response, Map<String, Object> map) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
		
	}

}
