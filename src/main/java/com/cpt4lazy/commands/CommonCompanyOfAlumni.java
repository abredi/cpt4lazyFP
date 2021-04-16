package com.cpt4lazy.commands;

import java.util.ArrayList;
import java.util.List;

import com.cpt4lazy.queries.JobUtils;
import org.json.simple.JSONArray;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

	@Command(name = "commonCompanyOfAlumni", description = "Search the top most companies Alumni work for:",
	        mixinStandardHelpOptions = true)
	final public class CommonCompanyOfAlumni implements Runnable{

	    Helper helper = new Helper();

	    @Option(names = {"-u", "--users"}, description = "Filename containing the user list.")
	    String user = "";

	    @Option(names = {"-t", "--top"}, description = "Search top t company.")
	    int top;

	    @Option(names = {"--verbose"}, description = "Print verbose output.")
	    boolean verbose;


	    @Override
	    public void run() {
	        JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(user);
	        List<User> users4 = Helper.parseJson(jsonArr.toJSONString());
	        List<String> companyName = new ArrayList<>();

	        try{
				companyName = JobUtils.CommonCompanyOfAlumni.apply(users4,top);
			}
	        catch (IllegalArgumentException e){
				System.out.println("You try to input a negative integer value. Please retry again.");
				return;
			}
	        if (verbose) {
				System.out.println("The " + top + " common company of the Alumni are:\n");
	            helper.prettyPrint(companyName);
	        }
	    }
}



