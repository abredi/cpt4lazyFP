package com.cpt4lazy.commands;


import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;

@CommandLine.Command(name = "theMostCommonPosition", description = "the most common position of jobSeekers",
        mixinStandardHelpOptions = true)
public class TheMostCommonPosition implements Runnable  {
    Helper helper = new Helper();

    @CommandLine.Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @CommandLine.Option(names = {"-t", "--top"}, description = "Search top t positions.")
    int top;

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users2 = Helper.parseJson(jsonArr.toJSONString());
        List<String> positions = new ArrayList<>();
        try {
            positions = FunctionalUtils.theMostCommonPosition.apply(users2, top);
        } catch (IllegalArgumentException e) {
            System.out.println("You try to input a negative integer value. Please retry again.");
            return;
        }

        if (verbose) {
            helper.prettyPrint(positions);
        }

    }
}
