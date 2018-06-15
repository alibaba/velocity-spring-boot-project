package com.alibaba.boot.velocity.autoconfigure.condition;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.mock.env.MockEnvironment;

/**
 * {@link VelocityToolsBasePackagesCondition} Test
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityToolsBasePackagesCondition
 * @since 2017.02.04
 */
public class VelocityToolsBasePackagesConditionTest {

    @Test
    public void testGetMatchOutcome() {

        VelocityToolsBasePackagesCondition condition = new VelocityToolsBasePackagesCondition();

        MockEnvironment environment = new MockEnvironment();

        MockConditionContext conditionContext = new MockConditionContext();

        conditionContext.setEnvironment(environment);

        ConditionOutcome conditionOutcome = condition.getMatchOutcome(conditionContext, null);

        Assert.assertFalse(conditionOutcome.isMatch());

        environment.setProperty("spring.velocity.tools-base-packages", "com.alibaba.boot.velocity.tools");

        conditionOutcome = condition.getMatchOutcome(conditionContext, null);

        Assert.assertTrue(conditionOutcome.isMatch());

    }

}
