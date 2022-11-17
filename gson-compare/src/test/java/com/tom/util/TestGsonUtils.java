package com.tom.util;

import org.junit.Assert;
import org.junit.Test;

import static com.tom.util.GsonUtils.isJsonElementEqualsWithJsonArrayOrder;
import static com.tom.util.GsonUtils.isJsonElementEqualsWithoutJsonArrayOrder;

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
}
