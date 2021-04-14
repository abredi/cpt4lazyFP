package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CommandLine.Command(name = "alumniWithMostRequestRejected", description = "Search alumni with most referral request rejected.",
        mixinStandardHelpOptions = true)
public final class AlumniWithMostRequestRejected implements Runnable{
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
        List<String> alumniName = new ArrayList<>();
        try{
            alumniName = FunctionalUtils.alumniWithMostRequestRejected.apply(users,top);
        }
        catch (IllegalArgumentException e){
            System.out.println("You try to input a negative integer value. Please retry again.");
            return;
        }

        if (verbose) {
            System.out.println("The top " + top + " Alumni with most referral requests rejected are: \n");
            helper.prettyPrint(alumniName);
        }
    }
}
