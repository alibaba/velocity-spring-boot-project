package com.alibaba.boot.velocity.util;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * {@link Velocity Velocimacro} Utilities Class
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Velocity
 * @see RuntimeConstants
 * @since 2016.12.19
 */
public abstract class VelocimacroUtils {


    /**
     * Append Velocimacro Library into {@link Velocity} {@link Properties}
     *
     * @param velocityProperties {@link Velocity} {@link Properties}
     * @param libraryPath        the path of Velocimacro Library
     */
    public static void appendVelocimacroLibrary(Map<String, String> velocityProperties, String libraryPath) {
        String propertyName = RuntimeConstants.VM_LIBRARY;
        ExtendedProperties extendedProperties = toSingletonExtendedProperties(velocityProperties, propertyName);
        extendedProperties.addProperty(propertyName, libraryPath);
        List<String> listValue = extendedProperties.getList(propertyName);
        String propertyValue = StringUtils.collectionToCommaDelimitedString(listValue);
        velocityProperties.put(propertyName, propertyValue);
    }


    /**
     * Convert Singleton element {@link ExtendedProperties} from {@link Velocity} {@link Properties}
     *
     * @param velocityProperties {@link Velocity} {@link Properties}
     * @param propertyName       the name of property
     * @return non-null {@link ExtendedProperties}
     */
    protected static ExtendedProperties toSingletonExtendedProperties(Map<String, String> velocityProperties, String propertyName) {
        String library = velocityProperties.get(propertyName);
        ExtendedProperties extendedProperties = new ExtendedProperties();
        if (StringUtils.hasText(library)) {
            extendedProperties.setProperty(propertyName, library);
        }
        return extendedProperties;
    }


}
