package com.cpt4lazy;

import com.cpt4lazy.model.job.Job;
import com.cpt4lazy.queries.JobFunctionalUtils;
import com.cpt4lazy.utility.JobHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author Abdulaziz Ali
 */
class JobFunctionalUtilsTest {
    private final String FILENAME = "jobs.json";
    List<Job> jobs = JobHelper.getDataFromFile(FILENAME);

    @Test
    void test_findTopKStatesForSpecificPosition() {
        String state = "Texas";
        String position = jobs.get(0).getJobPosition();
        List<String> states = JobFunctionalUtils.findTopKStatesForSpecificPosition
                .apply(jobs, position, 3);
        assertEquals(state, states.get(0));
        assertEquals(List.of(), JobFunctionalUtils.findTopKStatesForSpecificPosition.apply(null, "Software Engineer", 3));
    }

    @Test
    void test_findTopKHiringCompaniesInSpecifiedCity() {
        String city = jobs.get(0).getLocation().getArea()[2];
        String company = jobs.get(0).getJobPlace().getDisplay_name();
        String position = jobs.get(0).getJobPosition();
        List<String> companies = JobFunctionalUtils.findTopKHiringCompaniesInSpecifiedCity
                .apply(jobs, position, city,  3);
        assertEquals(company, companies.get(0));
        assertEquals(List.of(), JobFunctionalUtils.findTopKHiringCompaniesInSpecifiedCity.apply(null, position, city, 3));
    }

    @Test
    void test_findPreferredCompany() {
        String state = "Washington";
        String position = jobs.get(0).getJobPosition();
        List<Job> preferredJobs = JobFunctionalUtils.findPreferredCompany
                .apply(jobs, state, position,  90000.0);
        assertEquals(position, preferredJobs.get(0).getJobPosition());
        assertEquals(state, preferredJobs.get(0).getLocation().getArea()[1]);
        assertEquals(List.of(), JobFunctionalUtils.findPreferredCompany.apply(jobs, state, position, Double.MAX_VALUE));
        assertEquals(List.of(), JobFunctionalUtils.findPreferredCompany.apply(null, state, position, 90000.0));
    }
}
