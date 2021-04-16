package com.cpt4lazy.queries;

import com.cpt4lazy.interfaces.QuadFunction;
import com.cpt4lazy.interfaces.TriFunction;
import com.cpt4lazy.model.job.Job;
import com.cpt4lazy.model.job.Location;

import java.util.*;
import java.util.function.BiPredicate;

import static java.util.stream.Collectors.*;

/**
 * @Author Abdulaziz Ali
 */
public abstract class JobFunctionalUtils {

    static BiPredicate<Location, String> compareCity = (location, city) -> location.getArea()[2].equals(city);
    static BiPredicate<Location, String> compareState = (location, city) -> location.getArea()[1].equals(city);
    static BiPredicate<String, String> findPosition = String::contains;

    public static TriFunction<List<Job>, String, Integer, List<String>>
            findTopKStatesForSpecificPosition = (jobs, position, k) ->
            Optional.ofNullable(jobs)
                    .orElse(List.of())
                    .stream()
                    .collect(filtering(job -> job.getJobPosition().contains(position),
                            groupingBy(j -> j.getLocation().getArea()[1], counting()))).entrySet()
                    .stream()
                    .sorted((e1, e2) -> (int) (e2.getValue() - e1.getValue()))
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(toList());

    public static QuadFunction<List<Job>, String, String, Double, List<Job>> findPreferredCompany = (jobs, state, position, salary) ->
            Optional.ofNullable(jobs)
                    .orElse(List.of())
                    .stream()
                    .filter(j -> compareState.test(j.getLocation(), state))
                    .filter(j -> findPosition.test(j.getJobPosition(), position))
                    .filter(j -> Double.compare(Double.parseDouble(j.getBenefits()), salary) >= 0)
                    .sorted(Comparator.comparingDouble(j -> Double.parseDouble(j.getBenefits())))
                    .collect(toList());

    public static QuadFunction<List<Job>, String, String, Integer, List<String>> findTopKHiringCompaniesInSpecifiedCity = (jobs, position, city, k) ->
            Optional.ofNullable(jobs)
                    .orElse(List.of())
                    .stream()
                    .collect(groupingBy(j -> j.getJobPlace().getDisplay_name(), filtering(
                            job -> compareCity.test(job.getLocation(), city) && findPosition.test(job.getJobPosition(), position), toList()
                    ))).entrySet()
                    .stream()
                    .filter(e -> !e.getValue().isEmpty())
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(List::size)))
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(toList());
}
