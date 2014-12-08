package NetPC.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import NetPC.dto.Contact;
import NetPC.service.ListService;


/**
 * Servlet implementation class UpdateList
 */
@WebServlet("/list")
public class UpdateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateList() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String, Object> clientProperties = new HashMap<String, Object>();
		ListService listService = new ListService();
		String listItem = request.getParameter("itemFromList");
		Contact currentClient = listService.getCurrentClient(listItem);
		clientProperties.put("email",currentClient.getEmail());
		clientProperties.put("name",currentClient.getName());
		clientProperties.put("surname",currentClient.getSurname());
		clientProperties.put("dateofbirth",currentClient.getDateofbirth());
		clientProperties.put("phonenumber",currentClient.getPhonenumber());
		clientProperties.put("listItem",listItem);
		ajaxRespond(response, clientProperties);

	}
	
	private void ajaxRespond(HttpServletResponse response, Map<String, Object> map) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
		
	}
	


}
