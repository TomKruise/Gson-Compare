package com.tom.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GsonUtils {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static boolean isJsonElementEqualsWithSomeJsonArrayOrder(String x, String y, List<String> keys) {
        return isJsonElementEqualsWithSomeJsonArrayOrder(gson.fromJson(x,JsonElement.class),gson.fromJson(y,JsonElement.class),keys);
    }

    public static boolean isJsonElementEqualsWithSomeJsonArrayOrder(JsonElement x, JsonElement y, List<String> keys) {
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray()&&y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size()==yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrder(xArray.get(i),yArray.get(j))) {
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

        }
        else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size()==ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())&&!keys.contains(xEntry.getKey())){
                            if (isJsonElementEqualsWithoutJsonArrayOrder(xEntry.getValue(),yEntry.getValue())) {
                                flag = true;
                                break;
                            }
                        } else if (xEntry.getKey().equals(yEntry.getKey())&&keys.contains(xEntry.getKey())) {
                            flag = isJsonElementEqualsWithJsonArrayOrder(xEntry.getValue(),yEntry.getValue());
                        }
                    }

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }

    public static boolean isJsonElementEqualsWithoutJsonArrayOrder(String x, String y) {
        return isJsonElementEqualsWithoutJsonArrayOrder(gson.fromJson(x,JsonElement.class), gson.fromJson(y,JsonElement.class));
    }

    public static boolean isJsonElementEqualsWithoutJsonArrayOrder(JsonElement x, JsonElement y) {
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray()&&y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size()==yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = false;

                    for (int j = 0; j < yArray.size(); j++) {
                        if (isJsonElementEqualsWithoutJsonArrayOrder(xArray.get(i),yArray.get(j))) {
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

        }
        else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size()==ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())){
                            if (isJsonElementEqualsWithoutJsonArrayOrder(xEntry.getValue(),yEntry.getValue())) {
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
        }
        else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }

        return true;
    }

    public static boolean isJsonElementEqualsWithJsonArrayOrder(String x, String y) {
        return isJsonElementEqualsWithJsonArrayOrder(gson.fromJson(x,JsonElement.class), gson.fromJson(y,JsonElement.class));
    }

    public static boolean isJsonElementEqualsWithJsonArrayOrder(JsonElement x, JsonElement y) {
        if (x.isJsonNull() && y.isJsonNull()) {
            return true;
        } else if (x.isJsonArray()&&y.isJsonArray()) {
            JsonArray xArray = x.getAsJsonArray();
            JsonArray yArray = y.getAsJsonArray();

            if (xArray.size()==yArray.size()) {
                for (int i = 0; i < xArray.size(); i++) {
                    boolean flag = isJsonElementEqualsWithJsonArrayOrder(xArray.get(i),yArray.get(i));

                    if (!flag) {
                        return false;
                    }
                }
            } else {
                return false;
            }

        }
        else if (x.isJsonObject() && y.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> xSet = x.getAsJsonObject().entrySet();
            Set<Map.Entry<String, JsonElement>> ySet = y.getAsJsonObject().entrySet();

            if (xSet.size()==ySet.size()) {
                for (Map.Entry<String, JsonElement> xEntry : xSet) {
                    boolean flag = false;

                    for (Map.Entry<String, JsonElement> yEntry : ySet) {
                        if (xEntry.getKey().equals(yEntry.getKey())){
                            if (isJsonElementEqualsWithJsonArrayOrder(xEntry.getValue(),yEntry.getValue())) {
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
        }
        else if (x.isJsonPrimitive() && y.isJsonPrimitive()) {
            return x.getAsJsonPrimitive().equals(y.getAsJsonPrimitive());
        } else {
            return false;
        }
        return true;
    }
}
