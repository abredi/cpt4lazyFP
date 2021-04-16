package com.cpt4lazy.utility;

import com.cpt4lazy.model.JobPost;
import com.cpt4lazy.model.User;
import com.cpt4lazy.model.job.Company;
import com.cpt4lazy.model.job.Job;
import com.cpt4lazy.model.job.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.*;

public class JobHelper {
    static Faker faker = new Faker();
    public static JobPost populateData(int k){
        List<Job> jobs = new ArrayList<>();
        JobPost jobPost =  new JobPost(jobs);
        for (int i = 0; i < k; i++) {
            Address address = faker.address();
            String[] area = { address.country(), address.state(), address.city()};
            Location location = new Location(address.city() + " "  + address.state(), area);
            com.github.javafaker.Company fakeCompany = faker.company();
            Job job = new Job(
                    fakeCompany.profession(), fakeCompany.catchPhrase(),
                    String.valueOf(faker.number().numberBetween(90000, 120000)),
                    location, new Company(fakeCompany.name())
            );
            jobs.add(job);
        }
        return jobPost;
    }
    public static Object ReadJSONFile(String filename){
        try{
            FileReader reader = new FileReader(filename);
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        }
        catch(FileNotFoundException e){
           out.println("File not found error: Please check if file is existing ");
        }
        catch(IOException e){
           out.println("IO Exception Error.");
        }
        catch (ParseException e) {
           out.println("Parsing error: Please check your json file.");
        }

        return null;
    }

    public static List<Job> parseJson(String json) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(json, new TypeReference<List<Job>>(){});
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public static List<Job> getDataFromFile(String filename) {
        JSONArray json = (JSONArray) ReadJSONFile(filename);
        return parseJson(json.toJSONString());
    }
}
