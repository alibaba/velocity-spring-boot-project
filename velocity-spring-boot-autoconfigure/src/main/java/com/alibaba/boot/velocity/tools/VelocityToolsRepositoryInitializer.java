package com.alibaba.boot.velocity.tools;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collection;
import java.util.Map;

/**
 * {@link VelocityToolsRepository} Initializer
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see VelocityToolsRepository
 * @since 1.0.3
 */
@Component
public class VelocityToolsRepositoryInitializer implements ServletContextInitializer,
        ApplicationListener<ContextRefreshedEvent>, Ordered {

    private final VelocityToolsRepository velocityToolsRepository;

    private final Collection<VelocityToolsScanner> scanners;

    public VelocityToolsRepositoryInitializer(VelocityToolsRepository velocityToolsRepository,
                                              Collection<VelocityToolsScanner> scanners) {
        this.velocityToolsRepository = velocityToolsRepository;
        this.scanners = scanners;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        for (VelocityToolsScanner scanner : scanners) {
            Map<String, VelocityTool> velocityToolsMap = scanner.scan();
            for (Map.Entry<String, VelocityTool> entry : velocityToolsMap.entrySet()) {
                velocityToolsRepository.put(entry.getKey(), entry.getValue());
            }
        }

    }

    @Override
    public int getOrder() {
        return ViewToolManagerInitializer.ORDER + 1;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Initialize VelocityToolsRepository
        velocityToolsRepository.init();
    }
}
