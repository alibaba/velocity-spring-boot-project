package com.alibaba.boot.velocity.util;

import junit.framework.TestCase;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.RuntimeConstants;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link VelocimacroUtils} Test Case
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocimacroUtils
 * @since 2016.12.19
 */
public class VelocimacroUtilsTest extends TestCase {

    /**
     * {@link VelocimacroUtils#appendVelocimacroLibrary(Map, String)}
     */
    @Test
    public void testAppendVelocimacroLibrary() {

        Map<String, String> velocityProperties = new HashMap<>();

        List<String> librariesList = Arrays.asList("/macros/main.vm", "/macros/index.vm");

        for (String library : librariesList) {
            VelocimacroUtils.appendVelocimacroLibrary(velocityProperties, library);
        }


        String vmLibrary = StringUtils.collectionToCommaDelimitedString(librariesList);

        Assert.assertEquals(vmLibrary, velocityProperties.get(RuntimeConstants.VM_LIBRARY));

    }


    /**
     * {@link VelocimacroUtils#toSingletonExtendedProperties(Map, String)}
     */
    @Test
    public void testToSingletonExtendedProperties() {

        Map<String, String> velocityProperties = new HashMap<>();

        List<String> pathsList = Arrays.asList("/macros/main.vm", "/macros/index.vm");

        String vmLibrary = StringUtils.collectionToCommaDelimitedString(pathsList);

        String propertyName = RuntimeConstants.VM_LIBRARY;

        velocityProperties.put(propertyName, vmLibrary);

        ExtendedProperties extendedProperties = VelocimacroUtils.toSingletonExtendedProperties(velocityProperties, propertyName);

        Assert.assertEquals(pathsList, extendedProperties.getList(propertyName));


    }
}
