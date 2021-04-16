package com.cpt4lazy.commands;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.JobUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "MostPreferredCompanyByJobSeeker", description = "Search the top most companies JobSeekers wants to work for:",
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
        List<String> companyName = JobUtils.mostPreferredCompanyByJobSeeker.apply(users,top);

        if (verbose) {
            helper.prettyPrint(companyName);
        }
    }
}
