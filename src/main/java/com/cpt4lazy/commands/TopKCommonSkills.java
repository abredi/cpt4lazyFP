package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.List;
import java.util.Optional;

@CommandLine.Command(name = "topKCommonSkills", description = "Search top k skills for all job seeker.",
        mixinStandardHelpOptions = true)
public final class TopKCommonSkills implements Runnable{

    Helper helper = new Helper();

    @CommandLine.Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @CommandLine.Option(names = {"-t", "--top"}, description = "Search top t skills.")
    int top;

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;

    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users = Helper.parseJson(jsonArr.toJSONString());

        List<String> commonSkills = FunctionalUtils.commonJobSeekerSkills.apply(users,top);

        if (verbose) {
            System.out.println("The top " + top + " common skills of MIU COMPRO students are:");
            helper.prettyPrint(commonSkills);
        }
    }
}
