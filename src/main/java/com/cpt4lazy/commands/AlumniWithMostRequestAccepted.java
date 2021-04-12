package com.cpt4lazy.commands;

import com.cpt4lazy.model.Alumni;
import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Command(name = "alumniWithMostRequestAccepted", description = "Search alumni with most referral request accepted.",
        mixinStandardHelpOptions = true)
final public class AlumniWithMostRequestAccepted implements Runnable{

    Helper helper = new Helper();

    @Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
    String user = "";

    @Option(names = {"-t", "--top"}, description = "Search top t alumni.")
    int top;

    @Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
        List<User> users = Helper.parseJson(jsonArr.toJSONString());
        List<String> alumniName = FunctionalUtils.alumniWithMostRequestAccepted.apply(users,top);

        if (verbose) {
            helper.prettyPrint(alumniName);
        }
    }
}
