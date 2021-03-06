package com.cpt4lazy;

import com.cpt4lazy.model.Alumni;
import com.cpt4lazy.model.JobReferral;
import com.cpt4lazy.model.User;
import com.cpt4lazy.model.UserRole;
import com.cpt4lazy.queries.FunctionalUtils;
import com.cpt4lazy.queries.JobUtils;
import com.cpt4lazy.utility.Helper;
import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import  java.lang.StringBuilder;
import java.util.*;

public class FunctionalUtilsTest {

    private final String FILENAME1 = "document.json";
    JSONArray jsonArr = (JSONArray) Helper.ReadJSONFile(FILENAME1);
    List<User> users = Helper.parseJson(jsonArr.toJSONString());
    private final String FILENAME4 = "user.json";
    JSONArray jsonArr4 = (JSONArray) Helper.ReadJSONFile(FILENAME4);
    List<User> users4 = Helper.parseJson(jsonArr4.toJSONString());
    List<String> expected1 = new ArrayList<>(Arrays.asList("Nicolas Cage","Max Planck"));
    List<String> expected2 = new ArrayList<>(Arrays.asList("Sharon Stone", "Max Planck", "Lord Kelvin", "Adriana C. Ocampo Uria", "Jack Ryan", "Nicolas Cage", "Melissa Franklin"));
    List<String> expected3 = new ArrayList<>(Arrays.asList("Java", "Springboot", "SQL", "React Js", "Git", "MongoDB", "Azure", "Docker", "Terraform", "ReactJs"));
    List<String> expected4 = new ArrayList<>(Arrays.asList("MA", "IL", "NY", "CA", "WA"));
    List<User> nullUser = null;
    List<String> expected5 = new ArrayList<>();
    List<String> expected6 = new ArrayList<>(Arrays.asList("request", "link", "Students"));;
    List<String> expected7 = Arrays.asList("Software Engineer");
    List<String> expected8 = Arrays.asList("Abdulaziz Ali", "Amanuel Chorito", "Carl Mapada", "Haymanot Yimam", "Jawaher Elleuchy");

    Map<String,Integer>almuniPostExpected= new HashMap<>(){{put("Nicolas Cage",2);put("Sharon Stone" ,1);}};
    Map<String,Integer>cityExpected= new HashMap<>(){{put("New York",1);put("Chicago" ,1);}};
    List<String> jobsExpected = new ArrayList<>((Arrays.asList("software engineer", "Computer engineer")));
    Map<String,Integer>nullMap= new HashMap<>(){};

    private final String FILENAME2 = "user.json";
    JSONArray jsonArr2 = (JSONArray) Helper.ReadJSONFile(FILENAME2);
    List<User> users2 = Helper.parseJson(jsonArr2.toJSONString());


    List<String> expectedAlumniList = new ArrayList<>(Arrays.asList("UserRole{name='Tom Cruise', telephoneNumber='+1 412-452-6235', address='New York, NY', roleName='ALUMNI'}",
                                                        "UserRole{name='Nicolas Cage', telephoneNumber='+1 412-452-6235', address='Los Angeles, CA', roleName='ALUMNI'}",
                                                        "UserRole{name='Jennifer Aniston', telephoneNumber='+1 412-452-6235', address='Minneapolis, MN', roleName='ALUMNI'}",
                                                        "UserRole{name='Sharon Stone', telephoneNumber='+1 412-452-6235', address='Las Vegas, NV', roleName='ALUMNI'}",
                                                        "UserRole{name='Jack Ryan', telephoneNumber='+1 412-452-6235', address='Fairfield, IA', roleName='ALUMNI'}"));

    List<String> expectedJobSeekerList = new ArrayList<>(Arrays.asList("JobSeeker{preferredJob='Machine Learning Engineer', preferredCompany='Google', skills=[Java, Springboot, Python, Microsoft .NET, SQL, Azure, Terraform], experience=[Experience{company='Microsoft', position='Software Engineer', responsibility='Test Software', fromDate=2017-01-20, toDate=2018-12-25}], referralRequest=null, jobsApplied=null}",
                                                        "JobSeeker{preferredJob='Frontend Developer', preferredCompany='Apple', skills=[Java, Springboot, React Js, SQL], experience=[Experience{company='Microsoft', position='Software Engineer', responsibility='Test Software', fromDate=2017-01-20, toDate=2018-12-25}], referralRequest=null, jobsApplied=null}",
                                                        "JobSeeker{preferredJob='Software Engineer', preferredCompany='Microsoft', skills=null, experience=[Experience{company='Microsoft', position='Software Engineer', responsibility='Test Software', fromDate=2017-01-20, toDate=2018-12-25}], referralRequest=null, jobsApplied=null}",
                                                        "JobSeeker{preferredJob='Software Engineer', preferredCompany='Amazon', skills=null, experience=[Experience{company='Microsoft', position='Software Engineer', responsibility='Test Software', fromDate=2017-01-20, toDate=2018-12-25}], referralRequest=null, jobsApplied=null}",
                                                        "JobSeeker{preferredJob='Software Developer', preferredCompany='Microsoft', skills=null, experience=[Experience{company='Microsoft', position='Software Engineer', responsibility='Test Software', fromDate=2017-01-20, toDate=2018-12-25}], referralRequest=null, jobsApplied=null}"));

    List<String> expectedReferralRequestList = new ArrayList<>(Arrays.asList("Request{refStatus='Accepted', reqDate=2021-04-11, postedBy='Nicolas Cage', requestSentBy='Haymanot Yimam'}",
                                                        "Request{refStatus='Rejected', reqDate=2021-04-11, postedBy='Nicolas Cage', requestSentBy='Abdulaziz Ali'}",
                                                        "Request{refStatus='Accepted', reqDate=2021-04-11, postedBy='Nicolas Cage', requestSentBy='Carl Mapada'}",
                                                        "Request{refStatus='Rejected', reqDate=2021-04-11, postedBy='Sharon Stone', requestSentBy='Haymanot Yimam'}",
                                                        "Request{refStatus='Rejected', reqDate=2021-04-11, postedBy='Sharon Stone', requestSentBy='Carl Mapada'}",
                                                        "Request{refStatus='Rejected', reqDate=2021-04-11, postedBy='Jack Ryan', requestSentBy='Carl Mapada'}"));

    private final String FILENAME3 = "userJobSeekerPost.json";
    JSONArray jsonArr3 = (JSONArray) Helper.ReadJSONFile(FILENAME3);
    List<User> users3 = Helper.parseJson(jsonArr3.toJSONString());

    /**
     * @author Carl Mapada
     */
    /*************************** START OF CARL MAPADA TEST  ************************************/
    @Test
    public void testAlumniList(){
        Assert.assertEquals(Arrays.toString(expectedAlumniList.toArray()),
                Arrays.toString(FunctionalUtils.alumniList.apply(users2).toArray()));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.alumniList.apply(nullUser));
    }

    @Test
    public void testJobSeekerList(){
        Assert.assertEquals(Arrays.toString(expectedJobSeekerList.toArray()),
                Arrays.toString(FunctionalUtils.jobSeekerList.apply(users2).toArray()));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.jobSeekerList.apply(nullUser));
    }

    @Test
    public void testReferralRequest(){
        Assert.assertEquals(Arrays.toString(expectedReferralRequestList.toArray()),
                Arrays.toString(FunctionalUtils.referralRequests.apply(users2).toArray()));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.referralRequests.apply(nullUser));
    }

    @Test
    public void testAlumniWithMostRequestAccepted(){
        Assert.assertEquals(expected1, FunctionalUtils.alumniWithMostRequestAccepted.apply(users,10));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.alumniWithMostRequestAccepted.apply(nullUser,10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlumniWithMostRequestAccepted_negativeInput() {
        Assert.assertEquals(expected1, FunctionalUtils.alumniWithMostRequestAccepted.apply(users, -10));
    }

    @Test
    public void testAlumniWithMostRequestRejected(){
        Assert.assertEquals(expected2, FunctionalUtils.alumniWithMostRequestRejected.apply(users,10));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.alumniWithMostRequestAccepted.apply(nullUser,10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlumniWithMostRequestRejected_negativeInput() {
        Assert.assertEquals(expected2, FunctionalUtils.alumniWithMostRequestRejected.apply(users, -10));
    }

    @Test
    public void testCommonJobSeekerSkills(){
        Assert.assertEquals(expected3, FunctionalUtils.commonJobSeekerSkills.apply(users, 10));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.commonJobSeekerSkills.apply(nullUser,10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommonJobSeekerSkills_negativeInput() {
        Assert.assertEquals(expected3, FunctionalUtils.commonJobSeekerSkills.apply(users, -10));
    }

    @Test
    public void testTopKPreferredStateByJobSeeker(){
        Assert.assertEquals(expected4, FunctionalUtils.topKPreferredStateByJobSeeker.apply(users2, 10));

        //test for users list is null
        Assert.assertEquals(expected5, FunctionalUtils.topKPreferredStateByJobSeeker.apply(nullUser,10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTopKPreferredStateByJobSeeker_negativeInput() {
        Assert.assertEquals(expected4, FunctionalUtils.topKPreferredStateByJobSeeker.apply(users2, -5));
    }
    /************************************** End of Carl Mapada Test ************************************/

    /**
     * @author Amanuel E.Chorito, TEST start
     */
    @Test
    public void topAlmuniwithMostPosts_NullTest(){


        //test for users list is null and no number entered
        Assert.assertEquals(nullMap, FunctionalUtils.topAlmuniwithMostPosts.apply(nullUser,null));
    }
    @Test
    public void topAlmuniwithMostPosts_negativeValueTest(){


        //test for users list is null and no number entered
        Assert.assertEquals(nullMap, FunctionalUtils.topAlmuniwithMostPosts.apply(nullUser,-1));
    }
    @Test
    public void topAlmuniwithMostPosts_PositiveValueTest(){


        //test for users list is null and no number entered
        Assert.assertEquals(almuniPostExpected, FunctionalUtils.topAlmuniwithMostPosts.apply(users3,2));
    }
    @Test
    public void topCityofSoftwareJobs_NullTest(){


        //test for users list is null and no number entered
        Assert.assertEquals(Map.of(), FunctionalUtils.topCityofSoftwareJobs.apply(nullUser,null));
    }
    @Test
    public void topCityofSoftwareJobs_negativeValueTest(){
        //test for users list is null and no number entered
        Assert.assertEquals(Map.of(), FunctionalUtils.topCityofSoftwareJobs.apply(nullUser,-1));
    }
    @Test
    public void topCityofSoftwareJobs_PositiveValueTest(){

        //test for users list is null and no number entered
        Assert.assertEquals(cityExpected, FunctionalUtils.topCityofSoftwareJobs.apply(users3,2));
    }

    @Test
    public void topJobsApplied_NullTest(){

        //test for users list is null and no number entered
        Assert.assertEquals(expected5, FunctionalUtils.topJobsApplied.apply(nullUser,null));
    }
    @Test
    public void topJobsApplied_negativeValueTest(){

        //test for users list is null and no number entered
        Assert.assertEquals(expected5, FunctionalUtils.topJobsApplied.apply(nullUser,-1));
    }
    @Test
    public void topJobsApplied_PositiveValueTest(){

        //test for users list is null and no number entered
        Assert.assertEquals(jobsExpected, FunctionalUtils.topJobsApplied.apply(users3,2));
    }
    /**
     * End of @author Amanuel E.Chorito  TEST
     */

    /**
     * start @author Haymanot Adane Test
     */
    @Test
    public void testCommonCompanyOfAlumni() {
        List<User> us = users4.subList(0, users4.size());
        List<String> exp = List.of("Bank of America");
        Assert.assertEquals(exp, JobUtils.CommonCompanyOfAlumni.apply(us, 3));
        us.removeAll(List.of(us.get(9),us.get(5),us.get(6),us.get(7),us.get(8)));
        Assert.assertEquals(List.of(), JobUtils.CommonCompanyOfAlumni.apply(us,3));
        Assert.assertEquals(List.of(), JobUtils.CommonCompanyOfAlumni.apply(nullUser,3));
    }

    @Test
    public void testMostRequestedPostReferral() {
        List<JobReferral> jobReferrals = JobUtils.mostRequestedPostReferral.apply(users4, 3);
        assertEquals(jobReferrals.size(), 3);
        List<User> urs = users4.subList(0, 5);
        assertEquals(List.of(), JobUtils.mostRequestedPostReferral.apply(urs,3));
        assertEquals(List.of(), JobUtils.mostRequestedPostReferral.apply(nullUser,3));
    }

    @Test
    public void testMostPreferredCompanyByJobSeeker() {
        List<User> us = users4.subList(0, users4.size());
        List<String> exp = List.of("Microsoft");
        Assert.assertEquals(exp, JobUtils.mostPreferredCompanyByJobSeeker.apply(us, 1));
        us.removeAll(List.of(us.get(0),us.get(1),us.get(2),us.get(3),us.get(4)));
        Assert.assertEquals(List.of(), JobUtils.mostPreferredCompanyByJobSeeker.apply(us,1));
        Assert.assertEquals(List.of(), JobUtils.mostPreferredCompanyByJobSeeker.apply(nullUser,1));
    }
    /**
     * end of @author Haymanot Adane
     */

    /**
     * @author Jawaher Elleuchy, TEST start
     */

    @Test
    public void testMostCommonWords(){
        Assert.assertEquals(expected6, FunctionalUtils.mostCommonWords.apply(users, 3));
   }
    @Test
    public void theMostCommonPosition(){
        Assert.assertEquals(expected7, FunctionalUtils.theMostCommonPosition.apply(users2, 2));
    }

    @Test
    public void testJobSeekerAsPosition(){
        Assert.assertEquals(expected8 , FunctionalUtils.jobSeekerAsPosition.apply(users2, "Software Engineer"));
    }

}
