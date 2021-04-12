package com.cpt4lazy.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.cpt4lazy.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Helper<T>{

    public Helper(){}
    public static Object ReadJSONFile(String filename){

        try{
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> parseJson(String json) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //objectMapper.registerModule(new Jdk8Module());
            List<User> p = objectMapper.readValue(json, new TypeReference<List<User>>(){});
            return p;
        }
        catch(JsonMappingException e){
            e.printStackTrace();
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return null;
    }

    public void prettyPrint(List<T> o){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }
}
