window.onload = function(){
	getLocation();
}

var count = 1;
console.log(document.getElementById("Location"));

function weatherDetails(location)
{
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 &&  this.status == 200)
		{   
			var responseObject = JSON.parse(this.responseText);
			if(responseObject[0].Message == "Successful")
			{
			count = 1;
			document.getElementById("subDiv").innerHTML="";
			document.getElementById("subDiv1").innerHTML="";
			document.getElementById("phoneSize").style.display="block";
			document.getElementById("placeName").innerText = responseObject[2].City_Name+", "+responseObject[2].Region;
			document.getElementById("condition").innerText = responseObject[1].Weather_Condition;
			document.getElementById("cel").innerText = Math.floor(responseObject[1].temperatureIn_C)+"°";
			document.getElementById("highLow").innerText = "H:"+Math.floor(responseObject[3].Highest_temperature)+"° L:"+Math.floor(responseObject[3].lowest_temperature)+"°";
			document.getElementById("sunrise").innerText = responseObject[3].SunRise
			document.getElementById("sunset").innerText = responseObject[3].SunSet;
			document.getElementById("cloudiness").innerText = responseObject[1].Cloudiness+"%";
			document.getElementById("humidity").innerText = responseObject[1].Humidity+"%";
			document.getElementById("wind").innerText = responseObject[1].direction+" "+ Math.floor(responseObject[1].wind)+" "+"Km/h"
			document.getElementById("feelslike").innerText = Math.floor(responseObject[1].feelslike_c)+"°";
			document.getElementById("visibility").innerText = responseObject[1].Visibility+".0 km";
			document.getElementById("pressure").innerText = responseObject[1].pressure+" hpa";
			document.getElementById("weatherRegion").innerText = "Weather for "+responseObject[2].Region;
			if(responseObject[1].Precipitation == 0)
			{ 
			   document.getElementById("precipitation").innerText = responseObject[1].Precipitation+".0 mm";	
			}
			else
			{
			   document.getElementById("precipitation").innerText = responseObject[1].Precipitation+" mm";				
		 	}
			document.getElementById("uvIndex").innerText = responseObject[1].uvIndex;
			
		    for(let j=4;j<responseObject.length-1;j++)
            {
				weekweather(responseObject[j]);
			}
			
			var timingOfWeather = responseObject[responseObject.length-1]
			for(let k=0;k<timingOfWeather.length;k++)
			{
				monthweather(timingOfWeather[k]);
			}
			}
			else if(responseObject[0].Message == "Something went wrong"){
				alert("Please Enter Correct City Name")
			}            
        }     
    }
   xhr.open("POST","./weatherDetails");
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send("location=" + location);
}

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
  	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 &&  this.status == 200)
		{
			var responseObject = JSON.parse(this.responseText);
			if(responseObject.DistrictName != null)
			{
				weatherDetails(responseObject.DistrictName);
			}
			else{
				alert("Some thing went wrong");
			}
        }     
    }
   xhr.open("POST","./City_Name");
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send("latitude=" + position.coords.latitude + "&longitude=" + position.coords.longitude);
}

function getDistrictNameFromUser()
{
     var location = document.getElementById("Location").value;
     weatherDetails(location);
}
document.getElementById("Location").addEventListener("input",() =>
{
	if(document.getElementById("Location").value.trim()=="")
	{
		getLocation();
	}
})



function monthweather(arrayDetails)
{
	var firstDiv = document.createElement("div");
	document.getElementById("subDiv").appendChild(firstDiv);
	firstDiv.setAttribute("class","firstDiv");
	
	var date = document.createElement("div");
	firstDiv.appendChild(date);
	date.setAttribute("class","date");
	date.innerText = arrayDetails.timing;
	
	var icon = document.createElement("div");
	firstDiv.appendChild(icon);
	if(arrayDetails.weatherCondition=="Sunny")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-sun icon'></i>"
	}
	else if(arrayDetails.weatherCondition=="Partly cloudy" || arrayDetails.weatherCondition=="Cloudy")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud icon'></i>"
	}
	else if(arrayDetails.weatherCondition=="Heavy rain" || arrayDetails.weatherCondition=="Moderate rain at times" || arrayDetails.weatherCondition=="Moderate rain" || arrayDetails.weatherCondition=="Light rain shower" || arrayDetails.weatherCondition=="Moderate or heavy rain shower")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-showers-heavy icon'></i>"
	}
	else if(arrayDetails.weatherCondition=="shower" || arrayDetails.weatherCondition=="Light rain" || arrayDetails.weatherCondition=="Patchy rain possible" || arrayDetails.weatherCondition=="Patchy light rain" || arrayDetails.weatherCondition=="Patchy light drizzle")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-showers-heavy icon'></i>";
	}
	else if(arrayDetails.weatherCondition=="thunder")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-bolt icon'></i>";
	}
	else if(arrayDetails.weatherCondition=="snow")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-snow icon'></i>";
	}
	else if(arrayDetails.weatherCondition=="pellets")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-rain icon'></i>";
	}
	else if(arrayDetails.weatherCondition=="Clear")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud icon'></i>";
	}
	else if(arrayDetails.weatherCondition=="sleet")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-sleet icon'></i>";
	}	
	
	var temp_c = document.createElement("div");
	firstDiv.appendChild(temp_c);
	temp_c.setAttribute("class","avgtemp");
	temp_c.innerHTML = Math.floor(arrayDetails.temp_c)+"°";
}

function weekweather(arrayDetails)
{
	var daysDiv = document.createElement("div");
	document.getElementById("subDiv1").appendChild(daysDiv);
	daysDiv.setAttribute("class","daysDiv");
	
	console.log(arrayDetails);
	
	var daysArray = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	var day = document.createElement("div");
	daysDiv.appendChild(day);
	day.setAttribute("class","day");
	var d = new Date();
	var index =((d.getDay()+count)%7);
	day.innerText = daysArray[index]
	count++;
	
	var icon = document.createElement("div");
	daysDiv.appendChild(icon);
	if(arrayDetails.Weather_Condition=="Sunny")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-sun icon'></i>"
	}
	else if(arrayDetails.Weather_Condition=="Partly cloudy" || arrayDetails.Weather_Condition=="Cloudy")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud icon'></i>"
	}
	else if(arrayDetails.Weather_Condition=="Heavy rain" || arrayDetails.Weather_Condition=="Moderate rain" || arrayDetails.Weather_Condition=="Moderate rain at times" || arrayDetails.Weather_Condition=="Light rain shower" || arrayDetails.Weather_Condition=="Moderate or heavy rain shower")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-showers-heavy icon'></i>"
	}
	else if(arrayDetails.Weather_Condition=="shower" || arrayDetails.Weather_Condition=="Light rain" || arrayDetails.Weather_Condition=="Patchy rain possible")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-showers-heavy icon'></i>";
	}
	else if(arrayDetails.Weather_Condition=="thunder")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-bolt icon'></i>";
	}
	else if(arrayDetails.Weather_Condition=="snow")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-snow icon'></i>";
	}
	else if(arrayDetails.Weather_Condition=="pellets")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-rain icon'></i>";
	}
	else if(arrayDetails.Weather_Condition=="Clear")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud icon'></i>";
	}
	else if(arrayDetails.Weather_Condition=="sleet")
	{
		icon.innerHTML="<i class='fa-solid fa-cloud-sleet icon'></i>";
	}	
	
	var temp = document.createElement("div");
	daysDiv.appendChild(temp);
	temp.setAttribute("class","temp");
	temp.innerHTML =Math.floor(arrayDetails.Highest_temperature);
	
	var mintemp = document.createElement("div");
	daysDiv.appendChild(mintemp);
	mintemp.setAttribute("class","temp");
	mintemp.innerHTML =Math.floor(arrayDetails.lowest_temperature);
}










