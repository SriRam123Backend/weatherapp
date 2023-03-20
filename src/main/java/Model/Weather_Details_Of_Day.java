package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather_Details_Of_Day{
	float Highest_temperature;
	float lowest_temperature;
	Date SunRise;
	Date SunSet;
	Date MoonRise;
	Date MoonSet;
	String Weather_Condition;
	
	public Weather_Details_Of_Day(float highest_temperature, float lowest_temperature, Date sunRise, Date sunSet,Date moonRise, Date moonSet,String Weather_Condition) {
		Highest_temperature = highest_temperature;
		this.lowest_temperature = lowest_temperature;
		SunRise = sunRise;
		SunSet = sunSet;
		MoonRise = moonRise;
		MoonSet = moonSet;
		this.Weather_Condition = Weather_Condition;
	}
	
	public String getWeather_Condition() {
		return Weather_Condition;
	}
	public void setWeather_Condition(String weather_Condition) {
		Weather_Condition = weather_Condition;
	}
	
	public float getHighest_temperature() {
		return Highest_temperature;
	}
	public void setHighest_temperature(float highest_temperature) {
		Highest_temperature = highest_temperature;
	}
	public float getLowest_temperature() {
		return lowest_temperature;
	}
	public void setLowest_temperature(float lowest_temperature) {
		this.lowest_temperature = lowest_temperature;
	}
	public Date getSunRise() {
		return SunRise;
	}
	public void setSunRise(Date sunRise) {
		SunRise = sunRise;
	}
	public Date getSunSet() {
		return SunSet;
	}
	public void setSunSet(Date sunSet) {
		SunSet = sunSet;
	}
	public Date getMoonRise() {
		return MoonRise;
	}
	public void setMoonRise(Date moonRise) {
		MoonRise = moonRise;
	}
	public Date getMoonSet() {
		return MoonSet;
	}
	public void setMoonSet(Date moonSet) {
		MoonSet = moonSet;
	}
	
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return Highest_temperature+", "+lowest_temperature+", "+sdf.format(SunRise)+", "+sdf.format(SunSet)+", "+sdf.format(MoonRise)+", "+sdf.format(MoonSet)+", "+Weather_Condition;              
	}
}
