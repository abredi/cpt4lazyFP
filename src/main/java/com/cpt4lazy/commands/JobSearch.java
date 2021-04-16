package com.cpt4lazy.commands;

import com.cpt4lazy.model.job.Job;
import com.cpt4lazy.queries.JobFunctionalUtils;
import com.cpt4lazy.utility.Helper;
import com.cpt4lazy.utility.JobHelper;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "jobSearch", description = "find Top K Hiring Companies In Specified City",
        mixinStandardHelpOptions = true)
public class JobSearch implements Runnable {
    Helper helper = new Helper();
    //findTopKHiringCompaniesInSpecifiedCity(jobs, position, city, k)
    @CommandLine.Option(names = {"-p", "--position"}, description = "Filename containing the user list.")
    String position = "Software Engineer";

    @CommandLine.Option(names = {"-c", "--city"}, description = "input the position.")
    String city = "San Francisco";

    @CommandLine.Option(names = {"-k", "--top"}, description = "input the position.")
    String top = "3";

    @CommandLine.Option(names = {"--verbose"}, description = "Print verbose output.")
    boolean verbose;


    @Override
    public void run() {
        List<Job> jobs = JobHelper.getDataFromFile("jobs.json");
        List<String> companies;
        try {
            System.out.println("Hello");
            companies = JobFunctionalUtils.findTopKHiringCompaniesInSpecifiedCity.apply(jobs, position, city, Integer.parseInt(top));
            helper.prettyPrint(companies);
        } catch (IllegalArgumentException e) {
            System.out.println("You try to input a negative integer value. Please retry again.");
            return;
        }



    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new JobSearch()).execute(args);
        System.exit(exitCode);
    }
}

