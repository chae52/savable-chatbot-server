package com.management.chatbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KakaoChatController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //카카오톡 오픈빌더로 리턴할 스킬 API
    @RequestMapping(value = "/kkoChat/v1" , method= {RequestMethod.POST , RequestMethod.GET },headers = {"Accept=application/json"})
    public String callAPI(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        try{
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
            System.out.println(jsonInString);
            int x = 0;
        }catch (Exception e){

        }
        return "index";
    }

    //카카오톡 오픈빌더로 리턴할 스킬 API
    @RequestMapping(value = "/kkoChat/v2", method = {RequestMethod.POST, RequestMethod.GET}, headers = {"Accept=application/json"})
    public HashMap<String, Object> callAPI2(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> resultJson = new HashMap<>();

        try {

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
            System.out.println(jsonInString);

            List<HashMap<String, Object>> outputs = new ArrayList<>();
            HashMap<String, Object> template = new HashMap<>();
            HashMap<String, Object> simpleText = new HashMap<>();
            HashMap<String, Object> text = new HashMap<>();

            text.put("text", "코딩32 발화리턴입니다.");
            simpleText.put("simpleText", text);
            outputs.add(simpleText);

            template.put("outputs", outputs);

            resultJson.put("version", "2.0");
            resultJson.put("template", template);

        } catch (Exception e) {

        }

        System.out.println("KKORestAPI.callAPI");

        return resultJson;
    }

    //카카오톡 오픈빌더로 리턴할 스킬 API
    @RequestMapping(value = "/new-member", method = {RequestMethod.POST, RequestMethod.GET}, headers = {"Accept=application/json"})
    public HashMap<String, Object> joinMember(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        HashMap<String, Object> resultJson = new HashMap<>();

        try {

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(params);
            System.out.println(jsonInString);

            HashMap<String,Object> userRequest =  (HashMap<String,Object>)params.get("userRequest");
            String utter = userRequest.get("utterance").toString().replace("\n","");

            List<HashMap<String, Object>> outputs = new ArrayList<>();
            HashMap<String, Object> template = new HashMap<>();
            HashMap<String, Object> simpleText = new HashMap<>();
            HashMap<String, Object> text = new HashMap<>();

            text.put("text", utter + "님 안녕하세요. Savable에 오신 것을 환영합니다 :)");
            simpleText.put("simpleText", text);
            outputs.add(simpleText);

            template.put("outputs", outputs);

            resultJson.put("version", "2.0");
            resultJson.put("template", template);

        } catch (Exception e) {

        }

        System.out.println("KKORestAPI.callAPI");

        return resultJson;
    }
}