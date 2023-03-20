package Controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class City_Name
 */
@WebServlet("/City_Name")
public class City_Name extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public City_Name() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        JSONObject dis = new JSONObject();
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		  try
		  {
			String urlLink = "https://api.geoapify.com/v1/geocode/reverse?lat="+latitude+"&lon="+longitude+"&apiKey=9e3850adbf7b467684fd4bf111c7fb22";
			URL url = new URL(urlLink);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			
			if(responseCode != 200){
				
				dis.put("Message","Something went wrong");
				response.getWriter().append(dis.toString());
			}
			else
			{
				StringBuilder weatherCurrentLocation = new StringBuilder();
				Scanner sc = new Scanner(url.openStream());
				
				while(sc.hasNext())
				{
					weatherCurrentLocation.append(sc.nextLine());
				}
				sc.close();
				JSONParser parser = new JSONParser();
				JSONObject data = (JSONObject) parser.parse(String.valueOf(weatherCurrentLocation));
				JSONArray details = (JSONArray) data.get("features");

				JSONObject region = (JSONObject) (details.get(0));
				JSONObject district = (JSONObject) (region.get("properties"));
		        String[] districtClimate = String.valueOf(district.get("state_district")).split(" ");
		        String DistrictName = districtClimate[0].toLowerCase();
		     
		        dis.put("DistrictName",DistrictName);
		        dis.put("Message","Successful");
		        response.getWriter().append(dis.toString());
			}
		  }catch(Exception err)
		  {
			  err.getMessage();
		  }
	}

}
