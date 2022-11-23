# Gson-Compare
Compare Two JSON Objects with Json

##Depoly
```shell
mvn clean deploy -P release
```
###Maven
```xml
    <dependency>
        <groupId>io.github.tomkruise</groupId>
        <artifactId>gson-compare</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
####Example
```java
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.tom.util.GsonUtils.*;

public class TestGsonUtils {
    String s1 = "{\"name\":\"Tom\", \"noOrder\":[1,2,3,4]}";
    String s11 = "{\"name\":\"Tom\", \"noOrder\":[2,3,4,1]}";
    String s2 = "{\"name\":\"Tom\", \"noOrder\":[3,4,1,2]}";
    String s22 = "{\"name\":\"Jerry\", \"noOrder\":[3,4,1,2]}";

    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithoutJsonArrayOrder(s1,s2));
        Assert.assertFalse(isJsonElementEqualsWithoutJsonArrayOrder(s1,s22));
    }

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithJsonArrayOrder(s1,s11));
        Assert.assertFalse(isJsonElementEqualsWithJsonArrayOrder(s1,s2));
    }

    String order1 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order11 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,1,3,4]}";
    String order2 = "{\"name\":\"Tom\", \"age\":18, \"order\":[2,3,1], \"noOrder\":[2,3,4,1]}";

    @Test
    public void testIsJsonElementEqualsWithSomeJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(order1, order11, Arrays.asList(new String[]{"order"})));
        Assert.assertFalse(isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(order1, order2, Arrays.asList(new String[]{"noOrder"})));

    }

    String order3 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order4 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";


    @Test
    public void testIsJsonElementEqualsWithoutSomeJsonArrayOrder(){
        Assert.assertTrue(isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(order3, order4, Arrays.asList(new String[]{"noOrder"})));
        Assert.assertFalse(isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(order3, order4, Arrays.asList(new String[]{"order"})));
    }

    String order5 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order6 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order66 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[3,4,1,2]}";

    @Test
    public void testIsJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys() {
        Assert.assertTrue(isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order6,Arrays.asList(new String[] {"name"})));
        Assert.assertFalse(isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(order5,order66,Arrays.asList(new String[] {"name"})));
    }

    String order7 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String order8 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";
    String order88 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,4], \"noOrder\":[2,3,4,1]}";


    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys() {
        Assert.assertTrue(isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(order7,order8,Arrays.asList(new String[] {"name"})));
        Assert.assertFalse(isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(order7,order88,Arrays.asList(new String[] {"name"})));
    }

    String s3 = "{\"name\":\"Tom\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[1,2,3,4]}";
    String s4 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[1,2,3], \"noOrder\":[2,3,4,1]}";
    String s44 = "{\"name\":\"Jerry\", \"age\":18, \"order\":[2,3,1], \"noOrder\":[3,4,1,2]}";


    @Test
    public void testIsJsonElementEqualsWithoutSomeJsonArrayOrderAndSkipSomeKeys() {
        boolean bool1 = isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), Arrays.asList(new String[]{"name"}));
        boolean bool2 = isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), null);
        boolean bool3 = isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(s3, s44, Arrays.asList(new String[]{"order"}), Arrays.asList(new String[]{"name"}));
        Assert.assertTrue(bool1);
        Assert.assertFalse(bool2);
        Assert.assertFalse(bool3);
    }

    @Test
    public void testIsJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys() {
        boolean bool1 = isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"order"}), Arrays.asList(new String[]{"name"}));
        Assert.assertTrue(bool1);
        boolean bool2 = isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(s3, s4, Arrays.asList(new String[]{"noOrder"}), Arrays.asList(new String[]{"name"}));
        Assert.assertFalse(bool2);
    }
}

```