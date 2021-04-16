package com.cpt4lazy.commands;

import java.util.ArrayList;
import java.util.List;

import com.cpt4lazy.queries.JobUtils;
import org.json.simple.JSONArray;

import com.cpt4lazy.model.JobReferral;
import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "mostRequestedPostReferal", description = "Search post referral with most request asked.",
mixinStandardHelpOptions = true)
final public class MostRequestedPostReferal implements Runnable{
	
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
	        List<User> users4 = Helper.parseJson(jsonArr.toJSONString());
	        List<JobReferral> joblist = new ArrayList<>();
	        try{
				joblist = JobUtils.mostRequestedPostReferral.apply(users4,top);
			}
	        catch (IllegalArgumentException e){
				System.out.println("You try to input a negative integer value. Please retry again.");
				return;
			}

	        if (verbose) {
				System.out.println("The " + top + " most requested post referrals by the JobSeeker are:\n");
	            helper.prettyPrint(joblist);
	        }
	    }
	

}
