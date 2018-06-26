package com.alibaba.boot.velocity.util;

import org.junit.Assert;
import org.junit.Test;

import static com.alibaba.boot.velocity.util.PathUtils.normalize;
import static com.alibaba.boot.velocity.util.PathUtils.removeHeadSlash;

/**
 * {@link PathUtils} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @since 1.0.4
 */
public class PathUtilsTest {

    @Test
    public void testNormalize() {

        Assert.assertEquals("/", normalize("//"));
        Assert.assertEquals("/", normalize("///"));
        Assert.assertEquals("/abc/", normalize("/abc///"));
    }

    @Test
    public void testRemoveHeadSlash() {

        Assert.assertEquals("home/", removeHeadSlash("/home/"));
        Assert.assertEquals("home/", removeHeadSlash("/home//"));
        Assert.assertEquals("home/user/", removeHeadSlash("//home/user//"));
    }
}
