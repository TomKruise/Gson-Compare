package com.tom.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.tom.util.GsonUtils.*;

public class TestGsonUtils {
    String s1 = "{\"name\":\"Tom\", \"age\":18}";
    String s2 = "{\"name\":\"Tom\", \"age\":19}";

    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithoutJsonArrayOrder(s1,s1));
        Assert.assertFalse(isJsonElementEqualsWithoutJsonArrayOrder(s1,s2));
    }

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithJsonArrayOrder(s1,s1));
        Assert.assertFalse(isJsonElementEqualsWithJsonArrayOrder(s1,s2));
    }

    String order1 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order2 = "{\"name\":\"Tom\", \"age\":18, \"order\":[2,3,1], \"noOrder\":[2,3,4,1]}";

    @Test
    public void testIsJsonElementEqualsWithSomeJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithSomeJsonArrayOrder(order1, order1, Arrays.asList(new String[]{"order"})));
        Assert.assertFalse(isJsonElementEqualsWithSomeJsonArrayOrder(order1, order2, Arrays.asList(new String[]{"order"})));

    }

    String order3 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order4 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";

    @Test
    public void testIsJsonElementEqualsWithoutSomeJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithoutSomeJsonArrayOrder(order3, order3, Arrays.asList(new String[]{"noOrder"})));
        Assert.assertFalse(isJsonElementEqualsWithoutSomeJsonArrayOrder(order3, order4, Arrays.asList(new String[]{"order"})));
    }

    String order5 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order6 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys() {
        Assert.assertTrue(isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order6,Arrays.asList(new String[] {"name"})));
        Assert.assertFalse(isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order6,null));
    }
}
