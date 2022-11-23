package io.github.tomkruise.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestGsonUtils {
    String s1 = "{\"name\":\"Tom\", \"noOrder\":[1,2,3,4]}";
    String s11 = "{\"name\":\"Tom\", \"noOrder\":[1,2,3,4]}";
    String s2 = "{\"name\":\"Tom\", \"noOrder\":[3,4,1,2]}";
    String s22 = "{\"name\":\"Jerry\", \"noOrder\":[3,4,1,2]}";

    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrder(){
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrder(s1,s2));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrder(s1,s22));
    }

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrder(){
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithJsonArrayOrder(s1,s11));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithJsonArrayOrder(s1,s2));
    }

    String order1 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order11 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,1,3,4]}";
    String order2 = "{\"name\":\"Tom\", \"age\":18, \"order\":[2,3,1], \"noOrder\":[2,3,4,1]}";

    @Test
    public void testIsJsonElementEqualsWithSomeJsonArrayOrder(){
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(order1, order11, Arrays.asList(new String[]{"order"})));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(order1, order2, Arrays.asList(new String[]{"noOrder"})));

    }

    String order3 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order4 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";


    @Test
    public void testIsJsonElementEqualsWithoutSomeJsonArrayOrder(){
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(order3, order4, Arrays.asList(new String[]{"noOrder"})));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(order3, order4, Arrays.asList(new String[]{"order"})));
    }

    String order5 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order6 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order66 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[3,4,1,2]}";

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys() {
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order6,Arrays.asList(new String[] {"name"})));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order66,Arrays.asList(new String[] {"name"})));
    }

    String order7 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order8 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";
    String order88 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,4], \"noOrder\":[2,3,4,1]}";


    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys() {
        Assert.assertTrue(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(order7,order8,Arrays.asList(new String[] {"name"})));
        Assert.assertFalse(GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(order7,order88,Arrays.asList(new String[] {"name"})));
    }

    String s3 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String s4 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";
    String s44 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[2,3,1], \"noOrder\":[3,4,1,2]}";


    @Test
    public void testIsJsonElementEqualsWithoutSomeJsonArrayOrderAndSkipSomeKeys() {
        boolean bool1 = GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), Arrays.asList(new String[]{"name"}));
        boolean bool2 = GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), null);
        boolean bool3 = GsonUtils.isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s44, Arrays.asList(new String[]{"order"}), Arrays.asList(new String[]{"name"}));
        Assert.assertTrue(bool1);
        Assert.assertFalse(bool2);
        Assert.assertFalse(bool3);
    }

    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys() {
        boolean bool1 = GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"order"}), Arrays.asList(new String[]{"name"}));
        Assert.assertTrue(bool1);
        boolean bool2 = GsonUtils.isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), Arrays.asList(new String[]{"name"}));
        Assert.assertFalse(bool2);
    }
}
