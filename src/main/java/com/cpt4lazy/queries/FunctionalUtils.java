package com.cpt4lazy.queries;

import com.cpt4lazy.model.*;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FunctionalUtils {

    /**
     * @author Carl Mapada
     */
    public static Function<List<User>, List<Alumni>> alumniList = (user) ->
            Optional.ofNullable(user)
                    .orElse(List.of())
                    .stream()
                    .filter(u -> u.getRole() instanceof Alumni)
                    .map(u -> (Alumni)u.getRole())
                    .collect(Collectors.toList());

    /**
     * @author Carl Mapada
     */
    public static Function<List<User>, List<JobSeeker>> jobSeekerList = (user) ->
            Optional.ofNullable(user)
                    .orElse(List.of())
                    .stream()
                    .filter(u -> u.getRole() instanceof JobSeeker)
                    .map(u -> (JobSeeker)u.getRole())
                    .collect(Collectors.toList());

    /**
     * @author Carl Mapada
     */
    public static Function<List<User>, List<Request>> referralRequests = (user) ->
            alumniList.apply(user).stream()
                    .flatMap(a -> Optional.ofNullable(a.getPost()).orElse(List.of()).stream())
                    .filter(p -> p instanceof JobReferral && p != null)
                    .map(p -> (JobReferral) p)
                    .flatMap(p -> p.getRequests().stream())
                    .collect(Collectors.toList());


    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>> alumniWithMostRequestAccepted = (user, k) ->
            referralRequests.apply(user).stream()
                    .filter(r -> r.getRefStatus().equals("Accepted"))
                    .collect(Collectors.groupingBy(r -> r.getPostedBy()))
                    .entrySet()
                    .stream()
                    .sorted((a1,a2) -> a2.getValue().size() - a1.getValue().size())
                    .limit(k)
                    .map(a -> a.getKey())
                    .collect(Collectors.toList());

    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>> alumniWithMostRequestRejected = (user, k) ->
            referralRequests.apply(user).stream()
                    .filter(r -> r.getRefStatus().equals("Rejected"))
                    .collect(Collectors.groupingBy(r -> r.getPostedBy()))
                    .entrySet()
                    .stream()
                    .sorted((a1,a2) -> a2.getValue().size() - a1.getValue().size())
                    .limit(k)
                    .map(a -> a.getKey())
                    .collect(Collectors.toList());
    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>> commonJobSeekerSkills = (user, k) ->
            jobSeekerList.apply(user).stream()
                    .flatMap(u -> Optional.ofNullable(u.getSkills()).orElse(List.of()).stream())
                    .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((s1, s2) -> (int)(s2.getValue() - s1.getValue()))
                    .limit(k)
                    .map(s -> s.getKey())
                    .collect(Collectors.toList());


    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>>  topKPreferredStateByJobSeeker = (user, k) ->
            jobSeekerList.apply(user).stream()
                    .map(j -> j.getAddress().split(",")[1].trim())
                    .limit(5)
                    .collect(Collectors.toList());

}
