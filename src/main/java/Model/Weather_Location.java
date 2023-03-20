package Model;

public class Weather_Location {
    
	String country;
	String City_Name;
	String Region;
	String tz_ID;
	float latitude;
	float longitude;
	
	public Weather_Location(String country, String city_Name, String region, String tz_ID,float latitude, float longitude) {
		this.country = country;
		City_Name = city_Name;
		Region = region;
		this.tz_ID = tz_ID;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity_Name() {
		return City_Name;
	}
	public void setCity_Name(String city_Name) {
		City_Name = city_Name;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getTz_ID() {
		return tz_ID;
	}
	public void setTz_ID(String tz_ID) {
		this.tz_ID = tz_ID;
	}
	public float getlatitude() {
		return latitude;
	}
	public void setlatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	  public String toString()
	  {
		  return country+", "+City_Name+", "+Region+", "+tz_ID+", "+latitude+", "+longitude;
	  }
}
