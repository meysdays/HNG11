package com.meysdays.restapi.service;

import com.meysdays.restapi.entity.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GetInfoService {

    private final String API_URL = "http://ipinfo.io/{ip}/geo?token={token}";
    private final String API_TOKEN = "9c8367e0d40335";

    private final String API_URL2 = "http://api.weatherapi.com/v1/current.json?key=358f90de982a48f69d0184008231605&q={clientIp}&aqi=no";
//    private final String API_KEY = "358f90de982a48f69d0184008231605";
    public Response getInfoService(HttpServletRequest request, String name){
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        else {
            // X-Forwarded-For can contain multiple IP addresses, the first one is the client's real IP
            clientIp = clientIp.split(",")[0];
        }



        //String city = getLocation(clientIp, API_TOKEN);
        String region = getCity(clientIp);
        Map<String, Object> res = new HashMap<>();
        res.put("region", region);
//        Double c = city != null ? city : Double.valueOf("City not found");

        Double temperature = getTemperature(clientIp);
        Map<String, Object> response = new HashMap<>();
        response.put("temperature", temperature);

        String f = "Hello, "+name+"!, the temperature is "+temperature+" degrees Celcius in "+region;

        String a = String.valueOf(region);
        return new Response(clientIp, region, f);
    }

//    public String getLocation(String ip, String token){
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("ip", ip);
//        uriVariables.put("token", token);
//
//        Map<String, Object> response = restTemplate.getForObject(API_URL,Map.class, uriVariables);
//        return response != null ? (String) response.get("city") : null;
//    }

    public String getCity(String ip) {
        Map<String, Object> weatherData = getWeather(ip);
        return weatherData.containsKey("region") ? (String) weatherData.get("region") : null;
    }
    public Double getTemperature(String ip) {
        Map<String, Object> weatherData = getWeather(ip);
        return weatherData.containsKey("temperature") ? (Double) weatherData.get("temperature") : null;
    }
    public Map<String, Object> getWeather(String ip){

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL2, Map.class, ip);

        Map<String, Object> result = new HashMap<>();
        if (response != null) {
            Map<String, Object> location = (Map<String, Object>) response.get("location");
            Map<String, Object> current = (Map<String, Object>) response.get("current");

            if (location != null) {
                result.put("cityName", location.get("name"));
                result.put("region", location.get("region"));
                result.put("country", location.get("country"));
                result.put("latitude", location.get("lat"));
                result.put("longitude", location.get("lon"));
                result.put("timezone", location.get("tz_id"));
                result.put("localtime", location.get("localtime"));
            }

            if (current != null) {
                result.put("temperature", current.get("temp_c"));
                result.put("humidity", current.get("humidity"));
            }
        }

        return result;
    }


}
