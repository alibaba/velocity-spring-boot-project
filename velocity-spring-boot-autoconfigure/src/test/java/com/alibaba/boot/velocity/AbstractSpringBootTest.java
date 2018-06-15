package com.alibaba.boot.velocity;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * {@link AbstractSpringBootTest}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @version 1.0.0
 * @see AbstractSpringBootTest
 * @since 1.0.0 2016-07-18
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestSpringBootConfiguration.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractSpringBootTest {


    protected <V> V getValue(Object instance, String fieldName) {
        V value = null;
        Field field = null;
        try {
            field = ReflectionUtils.findField(instance.getClass(), fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            value = (V) field.get(instance);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return value;
    }
}
