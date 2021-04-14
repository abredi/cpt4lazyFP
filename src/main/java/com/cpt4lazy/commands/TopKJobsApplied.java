package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "topKJobsApplied", description = "Top K jobs, which applied by job seekers",
        mixinStandardHelpOptions = true)
public final class TopKJobsApplied implements Runnable{

    Helper helper = new Helper();

    @CommandLine.Option(names = {"-j", "--jobSeeker"}, description = "Filename containing the Job seeker list.")
    String user = "";

    @CommandLine.Option(names = {"-k", "--top"}, description = "search top K jobs.")
    int top;

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;

    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users = Helper.parseJson(jsonArr.toJSONString());

            List<String> topJobs;
    try{
        topJobs = FunctionalUtils.topJobsApplied.apply(users, top);
    }
    catch (IllegalArgumentException e){
        System.out.println("You try to input a negative integer value. Please retry again.");
        return;

    }
            if (verbose) {
                System.out.println("The top " + top + " Jobs, which applied by job seekers:");
                helper.prettyPrint(topJobs);
            }


    }
}
