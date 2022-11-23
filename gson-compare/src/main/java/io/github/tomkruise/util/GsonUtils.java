package io.github.tomkruise.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonUtils {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * All arrays doesn't need to compare in order.
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrder(String x, String y) {
        return isJsonElementEqualsWithoutJsonArrayOrder(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class));
    }

    /**
     * All arrays doesn't need to compare in order.
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrder(JsonElement x, JsonElement y) {
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrder(xArray.get(i), yArray.get(j))) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (isJsonElementEqualsWithoutJsonArrayOrder(xEntry.getValue(), yEntry.getValue())) {
                                flag = true;
                                break;
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }



    /**
     * All arrays doesn't need to compare in order, will skip some keys in list.
     *
     * @param x
     * @param y
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(String x, String y, List<String> skipKeys) {
        return isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class), skipKeys);
    }

    /**
     * All arrays doesn't need to compare in order, will skip some keys in list.
     *
     * @param x
     * @param y
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(JsonElement x, JsonElement y, List<String> skipKeys) {
        if (null == skipKeys || skipKeys.size() == 0) {
            return isJsonElementEqualsWithoutJsonArrayOrder(x, y);
        }
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(xArray.get(i), yArray.get(j), skipKeys)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (skipKeys.contains(xEntry.getKey())) {
                                flag = true;
                                break;
                            }

                            if (isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(), skipKeys)) {
                                flag = true;
                                break;
                            }
                        }

                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }

    /**
     * Most of arrays don't need to compare with order, only some keys in list will compare in order.
     *
     * @param x
     * @param y
     * @param keys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(String x, String y, List<String> keys) {
        return isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class), keys);
    }

    /**
     * Most of arrays don't need to compare with order, only some keys in list will compare in order.
     *
     * @param x
     * @param y
     * @param keys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(JsonElement x, JsonElement y, List<String> keys) {
        if (null == keys || keys.size() == 0) {
            return isJsonElementEqualsWithoutJsonArrayOrder(x, y);
        }
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(xArray.get(i), yArray.get(j), keys)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (keys.contains(xEntry.getKey())) {
                                if (isJsonElementEqualsWithJsonArrayOrder(xEntry.getValue(), yEntry.getValue())) {
                                    flag = true;
                                    break;
                                }
                            } else {
                                if (isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(xEntry.getValue(), yEntry.getValue(), keys)) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }

    /**
     * Most of arrays compare without order, only keys in list will compare in order, and ignore keys's value in skipKeys.
     * @param x
     * @param y
     * @param withArrayKeys
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(String x, String y, List<String> withArrayKeys, List<String> skipKeys) {
        return isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class),withArrayKeys,skipKeys);
    }

    /**
     * Most of arrays compare without order, only keys in list will compare in order, and ignore keys's value in skipKeys.
     * @param x
     * @param y
     * @param withArrayKeys
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(JsonElement x, JsonElement y, List<String> withArrayKeys, List<String> skipKeys) {
        if (null == withArrayKeys || withArrayKeys.size() == 0) {
            return isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(x, y,skipKeys);
        }
        if (null == skipKeys || skipKeys.size() == 0) {
            return isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrder(x,y,withArrayKeys);
        }
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(xArray.get(i), yArray.get(j), withArrayKeys,skipKeys)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (skipKeys.contains(xEntry.getKey())) {
                                flag = true;
                                break;
                            }

                            if (withArrayKeys.contains(xEntry.getKey())) {
                                if (isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(),skipKeys)) {
                                    flag = true;
                                    break;
                                }
                            } else {
                                if (isJsonElementEqualsWithoutJsonArrayOrderAndWithSomeJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(), withArrayKeys,skipKeys)) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }




















    /**
     * All arrays need to compare with order.
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrder(String x, String y) {
        return isJsonElementEqualsWithJsonArrayOrder(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class));
    }

    /**
     * All arrays need to compare with order.
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrder(JsonElement x, JsonElement y) {
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {

                    boolean flag = false;
                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithJsonArrayOrder(xArray.get(i), yArray.get(i))) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (isJsonElementEqualsWithJsonArrayOrder(xEntry.getValue(), yEntry.getValue())) {
                                flag = true;
                                break;
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }
        return true;
    }

    /**
     * Most of arr compare by order, only keys in list without order.
     *
     * @param x
     * @param y
     * @param withoutArrayKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(String x, String y, List<String> withoutArrayKeys) {
        return isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class), withoutArrayKeys);
    }

    /**
     * Most of arr compare by order, only keys in list without order.
     *
     * @param x
     * @param y
     * @param keys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(JsonElement x, JsonElement y, List<String> keys) {
        if (null == keys || keys.size() == 0) {
            return isJsonElementEqualsWithJsonArrayOrder(x, y);
        }
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();


            if (xArray.size() == yArray.size()) {

                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;
                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(xArray.get(i), yArray.get(i), keys)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (keys.contains(xEntry.getKey())) {
                                if (isJsonElementEqualsWithoutJsonArrayOrder(xEntry.getValue(), yEntry.getValue())) {
                                    flag = true;
                                    break;
                                }
                            } else {
                                if (isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(xEntry.getValue(), yEntry.getValue(), keys)) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                    }


                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }
        return true;

    }



    /**
     * All arrays need to compare in order, will skip some keys in skipKes list to check value's equation.
     *
     * @param x
     * @param y
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(String x, String y, List<String> skipKeys) {
        return isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class), skipKeys);
    }

    /**
     * All arrays need to compare in order, will skip some keys in skipKes list to check value's equation.
     *
     * @param x
     * @param y
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(JsonElement x, JsonElement y, List<String> skipKeys) {
        if (null == skipKeys || skipKeys.size() == 0) {
            return isJsonElementEqualsWithJsonArrayOrder(x, y);
        }

        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size() == yArray.size()) {

                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;
                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(xArray.get(i), yArray.get(i), skipKeys)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }

            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (skipKeys.contains(xEntry.getKey())) {
                                flag = true;
                                break;
                            }
                            if (isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(), skipKeys)) {
                                flag = true;
                                break;
                            }
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }
        return true;
    }

    /**
     * Most of arrays are need to compare in order, only some keys in arrKeys list could compare without order.
     *
     * @param x
     * @param y
     * @param arrKeys
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(String x, String y, List<String> arrKeys, List<String> skipKeys) {
        return isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(gson.fromJson(x, JsonElement.class), gson.fromJson(y, JsonElement.class), arrKeys, skipKeys);
    }

    /**
     * Most of arrays are need to compare in order, only some keys in arrKeys list could compare without order, some keys' value in skipKeys list will ignore.
     *
     * @param x
     * @param y
     * @param withoutArrayKeys
     * @param skipKeys
     * @return
     */
    public static boolean isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(JsonElement x, JsonElement y, List<String> withoutArrayKeys, List<String> skipKeys) {
        if (null == skipKeys || skipKeys.size() == 0) {
            return isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrder(x, y, withoutArrayKeys);
        } else if (null == withoutArrayKeys || withoutArrayKeys.size() == 0) {
            return isJsonElementEqualsWithJsonArrayOrderAndSkipSomeKeys(x, y, skipKeys);
        }

        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray() && y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();


            if (xArray.size() == yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;
                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(xArray.get(i), yArray.get(i),withoutArrayKeys,skipKeys)) {
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        } else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size() == ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {

                        if (xEntry.getKey().equals(yEntry.getKey())) {
                            if (skipKeys.contains(xEntry.getKey())) {
                                flag = true;
                                break;
                            }

                            if (withoutArrayKeys.contains(xEntry.getKey())){
                                if (isJsonElementEqualsWithoutJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(),skipKeys)) {
                                    flag = true;
                                    break;
                                }
                            } else {
                                if (isJsonElementEqualsWithJsonArrayOrderAndWithoutSomeJsonArrayOrderAndSkipSomeKeys(xEntry.getValue(), yEntry.getValue(),withoutArrayKeys,skipKeys)) {
                                    flag = true;
                                    break;
                                }
                            }


                        }

                    }


                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }
        return true;
    }


}
