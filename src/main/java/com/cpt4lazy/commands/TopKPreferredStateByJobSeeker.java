package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "topKPreferredStateByJobSeeker", description = "Search top most preferred US State by the Jobseeker.",
        mixinStandardHelpOptions = true)
public class TopKPreferredStateByJobSeeker implements Runnable{

    Helper helper = new Helper();

    @CommandLine.Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @CommandLine.Option(names = {"-t", "--top"}, description = "Search top t alumni.")
    int top;

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users = Helper.parseJson(jsonArr.toJSONString());
        List<String> state = FunctionalUtils.topKPreferredStateByJobSeeker.apply(users,top);

        if (verbose) {
            System.out.println("The top " + top + " Alumni with most referral requests accepted are:");
            helper.prettyPrint(state);
        }
    }
}
