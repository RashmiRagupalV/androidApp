package redflag.capstone.com.redflag;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * Created by rashm on 5/24/2016.
 */
public class TestSuite {
    public static Test suite(){
        return new TestSuiteBuilder(TestSuite.class).includeAllPackagesUnderHere().build();

    }

    public TestSuite(){
        super();
    }
}
