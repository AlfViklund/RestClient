package com.example.RestClient;

import com.example.RestClient.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpringRestClient {
    private static final String GET_USERS_URL = "http://91.241.64.178:7081/api/users";
    private static final String POST_USER_URL = "http://91.241.64.178:7081/api/users";
    private static final String PUT_USER_URL = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_USER_URL = "http://91.241.64.178:7081/api/users/{id}";
    private static RestTemplate restTemplate = new RestTemplate();
    private static List<String> cookies;

    public static void main(String[] args) {
        getUsers();
        postUser();
        putUser();
        deleteUser();
    }

    private static void getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_USERS_URL, HttpMethod.GET, entity, String.class);
        cookies = result.getHeaders().get("Set-Cookie");
        System.out.println(result);
    }

    private static void postUser() {
        User user = new User(3l, "James", "Brown", (byte) 33);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.exchange(POST_USER_URL,HttpMethod.POST, entity, String.class);
        System.out.println(result);
    }

    private static void putUser() {
        User user = new User(3l, "Thomas", "Shelby", (byte) 33);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.exchange(PUT_USER_URL,HttpMethod.PUT, entity, String.class);
        System.out.println(result);
    }

    private static void deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<User> entity = new HttpEntity<>(headers);

        Map<String, Long> id = new HashMap<String, Long>();
        id.put("id", 3l);
        ResponseEntity<String> result = restTemplate.exchange(DELETE_USER_URL,HttpMethod.DELETE, entity, String.class, id);
        System.out.println(result);
    }

}
