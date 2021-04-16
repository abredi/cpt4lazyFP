package com.cpt4lazy.commands;


import com.cpt4lazy.model.JobSeeker;
import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "jobSeekerAsPosition", description = "Search for the jobSeeker who was working as a position ",
        mixinStandardHelpOptions = true)
public class JobSeekerAsPosition  implements  Runnable{
    Helper helper = new Helper();

    @CommandLine.Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @CommandLine.Option(names = {"-p", "--pos"}, description = "input the position.")
    String pos = "";

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users2 = Helper.parseJson(jsonArr.toJSONString());
        List<String> js  = new ArrayList<>();
        try {
            js = FunctionalUtils.jobSeekerAsPosition.apply(users2, pos);
        } catch (IllegalArgumentException e) {
            System.out.println("You try to input a negative integer value. Please retry again.");
            return;
        }

        if (verbose) {
            helper.prettyPrint(js);
        }

    }
}

