package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.JobUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "mostPreferredCompanyByJobSeeker", description = "Search the top k most companies JobSeekers wants to work for:",
        mixinStandardHelpOptions = true)
final public class MostPreferredCompanyByJobSeeker implements Runnable{
    Helper helper = new Helper();

    @CommandLine.Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @CommandLine.Option(names = {"-t", "--top"}, description = "Search top t company.")
    int top;

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users = Helper.parseJson(jsonArr.toJSONString());
        List<String> companyName = new ArrayList<>();

        try{
            companyName = JobUtils.mostPreferredCompanyByJobSeeker.apply(users,top);
        }
        catch (IllegalArgumentException e){
            System.out.println("You try to input a negative integer value. Please retry again.");
            return;
        }
        if (verbose) {
            System.out.println("The " + top + " most preferred company by the JobSeeker are:\n");
            helper.prettyPrint(companyName);
        }
    }
}
