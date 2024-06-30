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
    public Response getInfoService(HttpServletRequest request, String name){
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }
        else {
            // X-Forwarded-For can contain multiple IP addresses, the first one is the client's real IP
            clientIp = clientIp.split(",")[0];
        }
        String city = getLocation(clientIp, API_TOKEN);
        
        return new Response(clientIp, name, city != null ? city : "City not found");
    }

    public String getLocation(String ip, String token){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("ip", ip);
        uriVariables.put("token", token);

        Map<String, Object> response = restTemplate.getForObject(API_URL,Map.class, uriVariables);
        return response != null ? (String) response.get("city") : null;
    }
}
