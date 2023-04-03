package Controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.Weather_Details;
import Model.Weather_Details_Of_Day;
import Model.Weather_Location;
import Service.Weather_Conditions;

/**
 * Servlet implementation class weatherDetails
 */
@WebServlet("/weatherDetails")
public class weatherDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public weatherDetails() {
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
			
	    String location = request.getParameter("location");
	    location = location.toLowerCase();
		JSONArray weather = new JSONArray();
				
		  try
		  {
			URL url = new URL("https://api.weatherapi.com/v1/forecast.json?key=c83e081ba660405ea1b41846230304&q="+location+"&days=8");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			
			if(responseCode != 200){
				JSONObject details = new JSONObject();
				details.put("Message","Something went wrong");
				weather.add(details);
                response.getWriter().append(weather.toString());
			}
			else
			{
				StringBuilder weatherInformation = new StringBuilder();
				Scanner sc = new Scanner(url.openStream());
				
				while(sc.hasNext())
				{
					weatherInformation.append(sc.nextLine());
				}
				
				sc.close();
				
				JSONParser parser = new JSONParser();
				JSONObject data = (JSONObject) parser.parse(String.valueOf(weatherInformation));
			    JSONObject Weather_Details = (JSONObject) parser.parse(String.valueOf(data.get("current")));
			    JSONObject forecast = (JSONObject) parser.parse(String.valueOf(data.get("forecast")));
				JSONArray Weather_Details_Of_A_Day = (JSONArray) parser.parse(String.valueOf(forecast.get("forecastday")));
				JSONObject Weather_Locations = (JSONObject) parser.parse(String.valueOf(data.get("location")));
				
				Weather_Details result = Weather_Conditions.getInstance().getWeather(Weather_Details);
				ArrayList<Weather_Details_Of_Day>result1 = Weather_Conditions.getInstance().getAweekWeather(Weather_Details_Of_A_Day);
				Weather_Location result2 = Weather_Conditions.getInstance().getLocation(Weather_Locations);
				ArrayList<Weather_Details> result3 = Weather_Conditions.getInstance().getTwoHoursWeather(Weather_Details_Of_A_Day);			

				
				JSONObject details = new JSONObject();
				details.put("Message","Successful");
				weather.add(details);
				
                JSONObject Weather_details = new JSONObject();
                Weather_details.put("temperatureIn_C",result.getTemperatureIn_C());
                Weather_details.put("feelslike_c",result.getFeelslike_c());
                Weather_details.put("Cloudiness",result.getCloudiness());
                Weather_details.put("Humidity",result.getHumidity());
                Weather_details.put("Precipitation",result.getPrecipitation());
                Weather_details.put("pressure",result.getPressure());
                Weather_details.put("Visibility",result.getVisibility());
                Weather_details.put("uvIndex",result.getUvIndex());
                Weather_details.put("direction",result.getDirection());
                Weather_details.put("Weather_Condition",result.getWeather_Condition());
                Weather_details.put("wind",result.getWind());

                weather.add(Weather_details);
                
                JSONObject Weather_locations = new JSONObject();
                Weather_locations.put("country",result2.getCountry());
                Weather_locations.put("City_Name",result2.getCity_Name());
                Weather_locations.put("Region",result2.getRegion());
                weather.add(Weather_locations);
                
                for(Weather_Details_Of_Day wea : result1)
                {
                	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    JSONObject Weather_Details_Of_A_day = new JSONObject();
                	Weather_Details_Of_A_day.put("Highest_temperature",wea.getHighest_temperature());
                	Weather_Details_Of_A_day.put("lowest_temperature",wea.getLowest_temperature());
                	Weather_Details_Of_A_day.put("SunRise",sdf.format(wea.getSunRise()));
                	Weather_Details_Of_A_day.put("SunSet",sdf.format(wea.getSunSet()));
                	Weather_Details_Of_A_day.put("Weather_Condition",wea.getWeather_Condition());
                	
                	weather.add(Weather_Details_Of_A_day);
                }
                
                JSONArray hourWeatherDetails = new JSONArray();
                
                for(Weather_Details detail: result3)
                {
                	JSONObject weda = new JSONObject();
                	SimpleDateFormat sdf = new SimpleDateFormat("HH");
                	Date date = new Date();
                	if(date.compareTo(detail.getTime()) < 0)
                	{
                    	weda.put("temp_c",detail.getTemperatureIn_C());
                    	weda.put("timing",sdf.format(detail.getTime()));
                    	weda.put("weatherCondition",detail.getWeather_Condition());
                    	hourWeatherDetails.add(weda);
                	}
                }
                
                weather.add(hourWeatherDetails);
                response.getWriter().append(weather.toString());
                
    	  }
		}catch(Exception er) {
			er.printStackTrace();
		}
	}

}
