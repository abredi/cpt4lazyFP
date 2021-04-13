package com.cpt4lazy;

import com.cpt4lazy.model.User;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FunctionalUtilsTest {

    private final String FILENAME1 = "userJobSeekerPost.json";

    JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(FILENAME1);
    List<User> users = Helper.parseJson(jsonArr.toJSONString());
    List<String> expected1 = new ArrayList<>(Arrays.asList(new String []{"Nicolas Cage"}));
    List<String> expected2 = new ArrayList<>(Arrays.asList(new String []{"Sharon Stone", "Jack Ryan", "Nicolas Cage"}));
    List<String> expected3 = new ArrayList<>(Arrays.asList(new String []{"Java", "Springboot", "SQL", "Azure", "Terraform", "Microsoft .NET", "React Js", "Python"}));
    List<User> nullUser = null;
    List<String> expected4 = new ArrayList<>();


    @Test
    public void testAlumniWithMostRequestAccepted(){
        Assert.assertEquals(expected1, FunctionalUtils.alumniWithMostRequestAccepted.apply(users,10));

        //test for users list is null
        Assert.assertEquals(expected4, FunctionalUtils.alumniWithMostRequestAccepted.apply(nullUser,10));
    }

    @Test
    public void testAlumniWithMostRequestRejected(){
        Assert.assertEquals(expected2, FunctionalUtils.alumniWithMostRequestRejected.apply(users,10));

        //test for users list is null
        Assert.assertEquals(expected4, FunctionalUtils.alumniWithMostRequestAccepted.apply(nullUser,10));
    }

    @Test
    public void testCommonJobSeekerSkills(){
        Assert.assertEquals(expected3, FunctionalUtils.commonJobSeekerSkills.apply(users, 10));

        //test for users list is null
        Assert.assertEquals(expected4, FunctionalUtils.commonJobSeekerSkills.apply(nullUser,10));
    }

}
