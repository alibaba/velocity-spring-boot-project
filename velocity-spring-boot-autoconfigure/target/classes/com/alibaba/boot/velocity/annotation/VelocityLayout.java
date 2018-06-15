package com.alibaba.boot.velocity.annotation;

import org.apache.velocity.app.Velocity;

import java.lang.annotation.*;

/**
 * {@link Velocity} Layout for Spring Web MVC
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Velocity
 * @since 1.0.0 2016.12.21
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VelocityLayout {

    /**
     * The value of {@link Velocity} Layout's URL
     *
     * @return value of {@link Velocity} Layout's URL
     */
    String value();
}
