package NetPC.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NetPC.service.EditService;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddtoDatabase
 */
@WebServlet("/add")
public class AddtoDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String, Object> map = new HashMap<String, Object>();
		String[] tokens = request.getParameter("email").split("\\s");
		EditService editService = new EditService();
		Map<String, String[]> parameters = request.getParameterMap();
		for(String parameter : parameters.keySet()) {
		    if (request.getParameter(parameter).equals("") || request.getParameter(parameter) == null){
		    	System.out.println("Puste pole!");
		    	map.put("blankField", parameter);
		    	ajaxRespond(response,map);
		    	return;
		    }
		}
		map.put("blankField", "0");
		if (!editService.isNotOnList(request.getParameter("email"))){
			System.out.println("Email juz znajduje sie na liscie!");
	    	map.put("emailExists", request.getParameter("email"));
	    	ajaxRespond(response,map);
	    	return;
		}
		map.put("emailExists", "0");
		System.out.println("Email nie jest na liscie - mozna dodawac");
		editService.insertToDatabase(parameters, tokens[0]);
		ajaxRespond(response,map);
	}
	
	private void ajaxRespond(HttpServletResponse response, Map<String, Object> map) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
		
	}

	}

