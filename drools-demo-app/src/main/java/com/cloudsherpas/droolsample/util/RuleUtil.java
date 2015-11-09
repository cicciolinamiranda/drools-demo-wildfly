package com.cloudsherpas.droolsample.util;

/**
 * @author CMiranda
 */
public class RuleUtil {

    public static String createPath(String groupId, String artifactId, String version) {
        String groupPath = groupId.replace(".", "/");
        return groupPath
                + "/"
                + artifactId
                + "/"
                + version
                + "/"
                + artifactId + "-" + version + ".jar";
    }
}