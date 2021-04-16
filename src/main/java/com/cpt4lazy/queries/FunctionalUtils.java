package com.cpt4lazy.queries;

import com.cpt4lazy.model.*;

import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
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
                    .collect(Collectors.groupingBy(Request::getPostedBy))
                    .entrySet()
                    .stream()
                    .sorted((a1,a2) -> a2.getValue().size() - a1.getValue().size())
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>> alumniWithMostRequestRejected = (user, k) ->
            referralRequests.apply(user).stream()
                    .filter(r -> r.getRefStatus().equals("Rejected"))
                    .collect(Collectors.groupingBy(Request::getPostedBy))
                    .entrySet()
                    .stream()
                    .sorted((a1,a2) -> a2.getValue().size() - a1.getValue().size())
                    .limit(k)
                    .map(Map.Entry::getKey)
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
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());


    /**
     * @author Carl Mapada
     */
    public static BiFunction<List<User>, Integer, List<String>>  topKPreferredStateByJobSeeker = (user, k) ->
            jobSeekerList.apply(user).stream()
                    .map(j -> j.getAddress().split(",")[1].trim())
                    .limit(OptionalInt.of(k).orElse(0))
                    .collect(Collectors.toList());

    /**
     * @author Amanuel E.Chorito
     * Top Jobs Applied
     */
    public static BiFunction<List<User>,Integer,List<String>>topJobsApplied=(jobs,k)->
                Optional.ofNullable(jobs).orElse(List.of()).stream()
                .filter(r->r.getRole() instanceof JobSeeker)
                .map(s->(JobSeeker)s.getRole())
                .flatMap(t-> Optional.ofNullable(t.getJobsApplied()).orElse(List.of()).stream())
                .collect(Collectors.groupingBy(Job::getJobTitle))
                .entrySet().stream()
                .sorted((t1,t2)->t2.getValue().size() -t1.getValue().size())
                .limit(k!=null&&k>0?k:0).map(n->n.getKey()).collect(Collectors.toList());


    /**
     * @author Amanuel E.Chorito
     * top City of Software Jobs Applied
     */

    public static BiFunction<List<User>,Integer, Map<String,Integer>>topCityofSoftwareJobs=(jobs, k)->
            Optional.ofNullable(jobs).orElse(List.of()).stream()
            .filter(r->r.getRole() instanceof JobSeeker)
            .map(s->(JobSeeker)s.getRole())
            .flatMap(t-> Optional.ofNullable(t.getJobsApplied()).orElse(List.of()).stream())
            .collect(Collectors.groupingBy(Job::getPlace))
            .entrySet().stream()
            .collect(Collectors.toMap(entry->entry.getKey(), entry->entry.getValue().size()))
            .entrySet().stream()
            .sorted((c1,c2)->c2.getValue()-c1.getValue())
            .limit(k!=null&&k>0?k:0).collect(Collectors.toMap(Map.Entry::getKey,entry->entry.getValue()));

    /**
     * @author Amanuel E.Chorito
     * top Almuni who post the most jobs and referrral
     */


    public static  BiFunction<List<User>, Integer, Map<String,Integer>>topAlmuniwithMostPosts=(user,k)->

            Optional.ofNullable(user).orElse(List.of()).stream()
            .filter(r->r.getRole() instanceof Alumni)
            .map(s->(Alumni)s.getRole())
            .filter(t->(t.getPost() != null))
            .sorted((a1,a2)->(a2.getPost().size())-a1.getPost().size())
            .limit(k!=null&&k>0?k:0)
            .collect(Collectors.toMap(Alumni::getName,entry->entry.getPost().size()));


    /**
     * @author jawaherElleuchy
     */


    public static  BiFunction<List<User> , Integer, List<String>> mostCommonWords=(user,k)->

            Optional.ofNullable(user).orElse(List.of()).stream()
                    .filter(r->r.getRole() instanceof Alumni)
                    .map(s->(Alumni)s.getRole())
                    .filter(a->(a.getPost() != null))
                    .flatMap(p -> p.getPost().stream())
                    .map(p ->p.getPostText().replace(".","").split(" "))
                    .flatMap(s -> Arrays.stream(s).sorted())
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                    .entrySet().stream()
                    .sorted((l1,l2) -> Long.compare(l1.getValue(),l2.getValue()))
                    .map(s -> s.getKey()).limit(k).collect(Collectors.toList());



    
    /**
     * @author jawaherElleuchy
     */
    public static  BiFunction<List<Experience> ,String, List<Experience> >experienceFromPosition=
            (experiences,position) -> experiences
                    .stream()
                    .filter(experience -> experience.getPosition().equals(position))
                    .collect(Collectors.toList());



    public static  BiFunction<List<User> ,String, List<String>> jobSeekerAsPosition=(user,position)->

            Optional.ofNullable(user).orElse(List.of()).stream()
                    .filter(r->r.getRole() instanceof JobSeeker)
                    .map(s->(JobSeeker)s.getRole())
                    .collect(Collectors.groupingBy(jobSeeker -> jobSeeker.getExperience()))
                    .entrySet()
                    .stream()
                    .filter(e->e.getKey().equals(experienceFromPosition.apply(e.getKey(),position)))
                    .flatMap(m -> m.getValue().stream())
                    .map(j -> j.getName()).sorted().collect(Collectors.toList());


    /**
     * @author jawaherElleuchy
     */

    public static  BiFunction<List<User> ,Integer, List<String>> theMostCommonPosition=(user,k)->
            Optional.ofNullable(user).orElse(List.of()).stream()
                    .filter(r->r.getRole() instanceof JobSeeker)
                    .map(s->(JobSeeker)s.getRole())
                    .flatMap(jobSeeker -> jobSeeker.getExperience().stream())
                    .map(experience -> experience.getPosition())
                    .collect(Collectors.groupingBy(experiences -> experiences,Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1,e2)-> (int) (e1.getValue()-e2.getValue()))
                    .map(e-> e.getKey()).limit(k).collect(Collectors.toList());
}
