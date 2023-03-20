package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Weather_Details;
import Model.Weather_Details_Of_Day;
import Model.Weather_Location;

public class Weather_Conditions {
   
	private static Weather_Conditions weather = null;
	
	private Weather_Conditions() {};
	
	public static Weather_Conditions getInstance() {
		if(weather==null) {
			weather = new Weather_Conditions();
		}
		return weather;
	}
	
	public Weather_Details getWeather(JSONObject APIWeatherDetails) {
		
		double temperatureIn_C = (Double) APIWeatherDetails.get("temp_c");
		double feelslike_c = (Double) APIWeatherDetails.get("feelslike_c");
		boolean isDay = true;
		long Cloudiness = (Long) APIWeatherDetails.get("cloud");
		long Humidity = (Long) APIWeatherDetails.get("humidity");
		double Precipitation = (Double) APIWeatherDetails.get("precip_mm");
		double pressure = (Double) APIWeatherDetails.get("pressure_mb");
	    double Visibility = (Double) APIWeatherDetails.get("vis_km");
	    double uvIndex = (Double) APIWeatherDetails.get("uv");
	    double wind = (Double) APIWeatherDetails.get("wind_kph");
	    String direction = String.valueOf((String) APIWeatherDetails.get("wind_dir"));
	    JSONObject Condition = (JSONObject) APIWeatherDetails.get("condition");
	    String Weather_Condition = String.valueOf(Condition.get("text"));
		Weather_Details weather= new Weather_Details(temperatureIn_C,feelslike_c,isDay,Cloudiness,Humidity,Precipitation,pressure,Visibility,uvIndex,wind,direction,Weather_Condition);
		
		return weather;
		
	}
	public ArrayList<Weather_Details_Of_Day> getAweekWeather(JSONArray APIWeatherForecast)
	{
		ArrayList<Weather_Details_Of_Day> weathers =  new ArrayList<Weather_Details_Of_Day>();
				 
		 for(int i = 0;i < APIWeatherForecast.size();i++) {
			 
			 JSONObject job = (JSONObject) APIWeatherForecast.get(i);
			 JSONObject astro = (JSONObject) job.get("astro");
			 JSONObject day = (JSONObject) job.get("day");
			 float Highest_temperature = Float.parseFloat(String.valueOf(day.get("maxtemp_c")));
			 float lowest_temperature = Float.parseFloat(String.valueOf(day.get("mintemp_c")));
			 SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
			 try {
				Date SunRise = sdf.parse(String.valueOf(astro.get("sunrise")));
				Date SunSet = sdf.parse(String.valueOf(astro.get("sunset")));
				Date MoonRise = sdf.parse(String.valueOf(astro.get("moonrise")));
				Date MoonSet = sdf.parse(String.valueOf(astro.get("moonset")));
			    JSONObject Condition = (JSONObject) day.get("condition");
			    String Weather_Condition = String.valueOf(Condition.get("text"));
				Weather_Details_Of_Day object = new Weather_Details_Of_Day(Highest_temperature, lowest_temperature, SunRise, SunSet, MoonRise, MoonSet,Weather_Condition);
				weathers.add(object);
			 } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
	return weathers;
	}
	public Weather_Location getLocation(JSONObject APIWeatherLocation)
	{
		
		String country = (String) APIWeatherLocation.get("country");
		String City_Name = (String) APIWeatherLocation.get("name");
		String Region = (String) APIWeatherLocation.get("region");
		String tz_ID = (String) APIWeatherLocation.get("tz_id");
		float langitude = Float.parseFloat(String.valueOf(APIWeatherLocation.get("lat")));
		float longitude = Float.parseFloat(String.valueOf(APIWeatherLocation.get("lon")));
		Weather_Location location = new Weather_Location(country,City_Name,Region,tz_ID,langitude,longitude);
		
		return location;
	}
	
	public ArrayList<Weather_Details> getTwoHoursWeather(JSONArray APIWeatherForecast)
	{
		ArrayList<Weather_Details> weathers =  new ArrayList<Weather_Details>();
		
		for(int i = 0;i < 2;i++) {
			
			 JSONObject job = (JSONObject) APIWeatherForecast.get(i);
			 JSONArray  HourWeather = (JSONArray) job.get("hour");
			 
			 for(int j=0;j<HourWeather.size();j++)
			 {
				 JSONObject weatherDetails= (JSONObject) HourWeather.get(j);
				 JSONObject Condition = (JSONObject) weatherDetails.get("condition");
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			     try {
					Weather_Details wd = new Weather_Details((Double) weatherDetails.get("temp_c"),sdf.parse(String.valueOf(weatherDetails.get("time"))),String.valueOf(Condition.get("text")));
					weathers.add(wd);
				} catch (ParseException e) {
					e.printStackTrace();
				}		 
			 }
		}
		
		return weathers;
	}
 
}
