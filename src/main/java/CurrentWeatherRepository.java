
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class CurrentWeatherRepository {
	
	public CurrentWeatherReport getCurrentWeather(WeatherRequest request) {
		return getCurrentWeather(request.getCurrentWeatherRequest());
	}
	
	public CurrentWeatherReport getCurrentWeather(String json) {
		Map<String, String> result = parseCurrentWeatherRequest(json);
		
		return new CurrentWeatherReport(
				result.get("city"),
				result.get("country"),
				Double.parseDouble(result.get("longitude")),
				Double.parseDouble(result.get("latitude")),
				Double.parseDouble(result.get("currentTemp")));
	}
	
	public Map<String, String> parseCurrentWeatherRequest(String json) {
		Map<String, String> result = new HashMap<>();
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject mainObj = (JSONObject) parser.parse(json);
			result.put("city", (String) mainObj.get("name"));
			
			JSONObject sys = (JSONObject) mainObj.get("sys");
			result.put("country", (String) sys.get("country"));
			
			JSONObject coordinates = (JSONObject) mainObj.get("coord");
			String longitude = String.valueOf(coordinates.get("lon"));
			String latitude = String.valueOf(coordinates.get("lat"));
			result.put("longitude", longitude);
			result.put("latitude", latitude);
			
			JSONObject currentTempObj = (JSONObject) mainObj.get("main");
			String currentTemp = String.valueOf(currentTempObj.get("temp"));
			result.put("currentTemp", currentTemp);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}