package com.alibaba.boot.velocity.autoconfigure.condition;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.mock.env.MockEnvironment;

/**
 * {@link VelocityLayoutCondition} Test
 *
 * @author <a href="mailto:taogu.mxx@alibaba-inc.com">taogu.mxx</a> (Office)
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityLayoutCondition
 * @since 2017.02.04
 */
public class VelocityLayoutConditionTest {


    @Test
    public void testGetMatchOutcome() {

        VelocityLayoutCondition condition = new VelocityLayoutCondition();

        MockEnvironment environment = new MockEnvironment();

        MockConditionContext conditionContext = new MockConditionContext();

        conditionContext.setEnvironment(environment);

        ConditionOutcome conditionOutcome = condition.getMatchOutcome(conditionContext, null);

        Assert.assertTrue(conditionOutcome.isMatch());

        environment.setProperty("spring.velocity.enabled", "true");
        environment.setProperty("spring.velocity.layout-enabled", "true");

        conditionOutcome = condition.getMatchOutcome(conditionContext, null);
        Assert.assertTrue(conditionOutcome.isMatch());

        environment.setProperty("spring.velocity.enabled", "false");

        conditionOutcome = condition.getMatchOutcome(conditionContext, null);

        Assert.assertFalse(conditionOutcome.isMatch());

        environment.setProperty("spring.velocity.enabled", "true");
        environment.setProperty("spring.velocity.layout-enabled", "false");

        conditionOutcome = condition.getMatchOutcome(conditionContext, null);

        Assert.assertFalse(conditionOutcome.isMatch());

    }

}
