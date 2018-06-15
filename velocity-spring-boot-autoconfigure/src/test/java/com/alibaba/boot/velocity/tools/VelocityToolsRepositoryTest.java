package com.alibaba.boot.velocity.tools;//package com.alibaba.boot.velocity.util;
//
//import com.alibaba.boot.velocity.tools.VelocityToolsRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.mock.web.MockServletContext;
//
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * {@link VelocityToolsRepository} Test
// *
// * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
// * @see VelocityToolsRepository
// * @since 2017.07.21
// */
//public class VelocityToolsRepositoryTest {
//
//    @Test
//    public void testServletContext() {
//
//        MockServletContext servletContext = new MockServletContext();
//
//        VelocityToolsClassScannerComposite composite = new VelocityToolsClassScannerComposite(
//                Arrays.<VelocityToolsBeanDefinitionScanner>asList(new VelocityToolsBeanDefinitionScannerAdapter()));
//
//        VelocityToolsRepository repository = new VelocityToolsRepository(composite);
//
//        repository.setServletContext(servletContext);
//
//        Assert.assertEquals(repository, VelocityToolsRepository.get(servletContext));
//
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//
//        final Map<String, Class<?>> classesMap = new LinkedHashMap<String, Class<?>>();
//
//        classesMap.put("string", String.class);
//
//        VelocityToolsClassScannerComposite composite = new VelocityToolsClassScannerComposite(
//
//                Arrays.<VelocityToolsBeanDefinitionScanner>asList(new VelocityToolsBeanDefinitionScannerAdapter()));
//
//        VelocityToolsRepository repository = new VelocityToolsRepository(composite);
//
//        repository.init();
//
//        Map<String, Object> tools = repository.findAll();
//
//        Assert.assertEquals("", tools.get("string"));
//
//        repository.afterPropertiesSet();
//
//        tools = repository.findAll();
//
//        Assert.assertEquals("", tools.get("string"));
//
//        tools = repository.resolve();
//
//        Assert.assertEquals("", tools.get("string"));
//
//    }
//
//}
