package com.alibaba.boot.velocity.util;

import org.apache.commons.lang.StringUtils;

/**
 * Path Utilities class
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.4
 */
public abstract class PathUtils {

    /**
     * Slash
     */
    public static final String SLASH = "/";

    /**
     * Double Slash
     */
    public static final String DOUBLE_SLASH = SLASH + SLASH;

    /**
     * Normalizes path
     *
     * @param path the path is required to normalize
     * @return normalize path
     */
    public static String normalize(String path) {

        String normalizedPath = path;

        while (normalizedPath.indexOf(DOUBLE_SLASH) > -1) {
            normalizedPath = StringUtils.replace(normalizedPath, DOUBLE_SLASH, SLASH);
        }

        return normalizedPath;
    }


    /**
     * Remove head slash
     *
     * @param path the path is required to remove its head slash
     * @return path without head slash
     */
    public static String removeHeadSlash(String path) {

        String normalizedPath = normalize(path);

        if (normalizedPath.startsWith(SLASH)) {
            normalizedPath = normalizedPath.substring(SLASH.length());
        }

        return normalizedPath;
    }

}
