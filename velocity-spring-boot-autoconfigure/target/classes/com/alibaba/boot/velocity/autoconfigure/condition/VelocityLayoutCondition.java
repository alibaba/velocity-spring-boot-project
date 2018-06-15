package com.alibaba.boot.velocity.autoconfigure.condition;

import org.apache.velocity.app.Velocity;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import static com.alibaba.boot.velocity.VelocityConstants.*;

/**
 * {@link Velocity} Layout {@link Condition}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see SpringBootCondition
 * @since 2017.02.04
 */
public class VelocityLayoutCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment environment = context.getEnvironment();

        return getMatchOutcome(environment);

    }

    protected ConditionOutcome getMatchOutcome(Environment environment) {

        boolean velocityEnabled = environment.getProperty(VELOCITY_ENABLED_PROPERTY_NAME, boolean.class, true);

        if (!velocityEnabled) {
            return ConditionOutcome.noMatch("The velocity layout is disabled , caused by " +
                    VELOCITY_ENABLED_PROPERTY_NAME + " = false");
        }

        boolean velocityLayoutEnabled = environment.getProperty(VELOCITY_LAYOUT_ENABLED_PROPERTY_NAME, boolean.class,
                DEFAULT_VELOCITY_LAYOUT_ENABLED_VALUE);

        if (!velocityLayoutEnabled) {
            return ConditionOutcome.noMatch("The velocity layout is disabled , caused by " +
                    VELOCITY_LAYOUT_ENABLED_PROPERTY_NAME + " = false");
        }

        return ConditionOutcome.match("The velocity layout is enabled !");

    }

}
