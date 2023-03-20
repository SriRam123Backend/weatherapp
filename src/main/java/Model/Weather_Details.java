package Model;

import java.util.Date;

public class Weather_Details {
   
	double temperatureIn_C;
	double feelslike_c;
	boolean isDay;
	long Cloudiness;
	long Humidity;
	double Precipitation;
	double pressure;
    double Visibility;
    double uvIndex;
    double wind;
    String direction;
    String Weather_Condition;
    Date time;
    
    public Weather_Details(double temperatureIn_C,Date time,String weather_Condition)
    {
    	this.temperatureIn_C = temperatureIn_C;
    	this.time = time;
    	Weather_Condition = weather_Condition;
    }
    
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Weather_Details(double temperatureIn_C, double feelslike_c, boolean isDay, long cloudiness, long humidity,double precipitation, double pressure, double visibility, double uvIndex, double wind, String direction,String weather_Condition) {
		this.temperatureIn_C = temperatureIn_C;
		this.feelslike_c = feelslike_c;
		this.isDay = isDay;
		Cloudiness = cloudiness;
		Humidity = humidity;
		Precipitation = precipitation;
		this.pressure = pressure;
		Visibility = visibility;
		this.uvIndex = uvIndex;
		this.wind = wind;
		this.direction = direction;
		Weather_Condition = weather_Condition;
	}
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
	public String getWeather_Condition() {
		return Weather_Condition;
	}
	public void setWeather_Condition(String weather_Condition) {
		Weather_Condition = weather_Condition;
	}
	public double getTemperatureIn_C() {
		return temperatureIn_C;
	}
	public void setTemperatureIn_C(double temperatureIn_C) {
		this.temperatureIn_C = temperatureIn_C;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public double getFeelslike_c() {
		return feelslike_c;
	}
	public void setFeelslike_c(double feelslike_c) {
		this.feelslike_c = feelslike_c;
	}
	public boolean isDay() {
		return isDay;
	}
	public void setDay(boolean isDay) {
		this.isDay = isDay;
	}
	public long getCloudiness() {
		return Cloudiness;
	}
	public void setCloudiness(long cloudiness) {
		Cloudiness = cloudiness;
	}
	public long getHumidity() {
		return Humidity;
	}
	public void setHumidity(long humidity) {
		Humidity = humidity;
	}
	public double getPrecipitation() {
		return Precipitation;
	}
	public void setPrecipitation(double precipitation) {
		Precipitation = precipitation;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getVisibility() {
		return Visibility;
	}
	public void setVisibility(double visibility) {
		Visibility = visibility;
	}
	public double getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(double uvIndex) {
		this.uvIndex = uvIndex;
	}
  public String toString()
  {
	  //return temperatureIn_C+", "+feelslike_c+", "+isDay+", "+Cloudiness+", "+Humidity+", "+Precipitation+", "+pressure+", "+Visibility+", "+uvIndex+", "+direction+", "+Weather_Condition+","+wind;
      return temperatureIn_C+", "+time;
  }
}

