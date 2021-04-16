package com.cpt4lazy;

import com.cpt4lazy.commands.*;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "cpt4lazyCLI", description = "...",
        mixinStandardHelpOptions = true, subcommands = {AlumniWithMostRequestAccepted.class, AlumniWithMostRequestRejected.class,
        TopKCommonSkills.class, TopKPreferredStateByJobSeeker.class, TopKJobsApplied.class, TopKCityofSoftwareJob.class,
        TopKAlmuniWithMostPost.class,CommonCompanyOfAlumni.class,MostRequestedPostReferal.class,MostPreferredCompanyByJobSeeker.class})
public class Cpt4lazyCLICommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(Cpt4lazyCLICommand.class, args);
    }

    public void run() {
        if (verbose) {
            System.out.println("Hi Lazy! This is a CLI application to query CPT4Lazy application. Enjoy!!!");
        }
    }
}
