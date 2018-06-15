package com.alibaba.boot.velocity.autoconfigure.condition;

import org.apache.velocity.app.Velocity;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import static com.alibaba.boot.velocity.VelocityConstants.VELOCITY_TOOLS_BASE_PACKAGES_PROPERTY_NAME;

/**
 * {@link Velocity} Tools base packages {@link Condition}
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Velocity
 * @see SpringBootCondition
 * @since 2017.02.04
 */
public class VelocityToolsBasePackagesCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment environment = context.getEnvironment();

        String toolsBasePackages = environment.getProperty(VELOCITY_TOOLS_BASE_PACKAGES_PROPERTY_NAME);

        if (!StringUtils.hasText(toolsBasePackages)) {
            return ConditionOutcome.noMatch("Velocity Tools scanning is disabled , since its base packages is empty !");
        }

        return ConditionOutcome.match();

    }
}
