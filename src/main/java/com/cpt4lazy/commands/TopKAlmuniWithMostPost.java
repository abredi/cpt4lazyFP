package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommandLine.Command(name = "topKAlmuniWithMostPost", description = "Top K Almuni, who post the most jobs and referral",
        mixinStandardHelpOptions = true)
public final class TopKAlmuniWithMostPost implements Runnable {

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
        Map<String, Integer> topAlmuni;
        try {
            topAlmuni = FunctionalUtils.topAlmuniwithMostPosts.apply(users, top);
        }
           catch (IllegalArgumentException e){
               System.out.println("You try to input a negative integer value. Please retry again.");
               return;

           }

            if (verbose) {
                System.out.println("The top " + top + " Alumni with the most Post:");
                helper.prettyPrint(Collections.singletonList(topAlmuni));
            }

    }
}
