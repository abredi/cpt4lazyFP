package com.cpt4lazy.queries;

import com.cpt4lazy.model.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class JobUtils {
    /**
     * @author Haymanot Adane
     */
    public static BiFunction<List<User>, Integer, List<String>> CommonCompanyOfAlumni = (user, k) ->
            Optional.ofNullable(user)
                    .orElse(List.of())
                    .stream()
                    .filter(u -> u.getRole() instanceof Alumni)
                    .map(u -> (Alumni) u.getRole())
                    .collect(Collectors.groupingBy(r -> r.getCurrentCompany()))
                    .entrySet()
                    .stream()
                    .sorted((a1, a2) -> a2.getValue().size() - a1.getValue().size())
                    .limit(k)
                    .map(a -> a.getKey())
                    .collect(Collectors.toList());


    /**
     * @author Haymanot Adane
     */
    public static BiFunction<List<User>, Integer, List<JobReferral>> mostRequestedPostReferral = (user, k) ->
            Optional.ofNullable(user).orElse(List.of())
                    .stream().filter(u -> u.getRole() instanceof Alumni)
                    .map(u -> (Alumni) u.getRole())
                    .flatMap(a -> Optional.ofNullable(a.getPost()).orElse(List.of()).stream())
                    .filter(p -> p instanceof JobReferral)
                    .map(p -> (JobReferral) p)
                    .sorted((a1, a2) -> (a2.getRequests().size() - (a1.getRequests().size())))
                    .limit(k)
                    .collect(Collectors.toList());

    /**
     * @author Haymanot Adane
     */
    public static BiFunction<List<User>, Integer, List<String>> mostPreferredCompanyByJobSeeker = (user, k) ->
            Optional.ofNullable(user).orElse(List.of())
                    .stream().filter(u -> u.getRole() instanceof JobSeeker)
                    .map(u -> (JobSeeker) u.getRole())
                    .map(a -> Optional.ofNullable(a.getPreferredCompany()).orElse(null))
                    .sorted((a1, a2) -> (a2.compareToIgnoreCase(a1)))
                    .limit(k)
                    .collect(Collectors.toList());


}
