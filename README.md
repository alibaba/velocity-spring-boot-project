# Velocity Spring Boot Project

A Spring Boot Starter for velocity including Spring's official and Alibaba's implementation , e.g, Layout , Tools supports.


## [Release Notes](release-notes.md)

## [中文文档](README_CN.md)


## Released version


```xml
    <dependencies>
        ...
        <!-- Spring Boot Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>velocity-spring-boot-starter</artifactId>
            <version>1.0.4.RELEASE</version>
        </dependency>
        ...
    </dependencies>
```

If your project failed to resolve the dependency, try to add the following repository:
```xml
    <repositories>
        <repository>
            <id>sonatype-nexus</id>
            <url>https://oss.sonatype.org/content/repositories/releases</url>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>
    </repositories>
```


## Features




### Velocity Common support




### Velocity Layout support




### Velocity Tools support




## Dependencies & Compatibility

| Dependencies   | Compatibility |
| -------------- | ------------- |
| Java           | 1.7 +         |
| Servlet        | 3.0 +         |
| Spring Boot    | 1.4 +         |
| [Alibaba Spring Velocity Context Support](https://github.com/alibaba/spring-velocity-support/)| 1.4.1.8 + |
| [Alibaba Spring Boot Web Support](https://github.com/alibaba/spring-boot-web-support) | 1.0.0 + |
| [Alibaba Spring WebMVC Support](https://github.com/alibaba/spring-webmvc-support) | 1.0.0 + |
| [Alibaba Spring Context Support](https://github.com/alibaba/spring-context-support) | 1.0.0 + |




## Downstream Projects

* [Alibaba Spring Boot Web Support](https://github.com/alibaba/spring-boot-web-support)
    * [Alibaba Spring WebMVC Support](https://github.com/alibaba/spring-webmvc-support)
        * [Alibaba Spring Context Support](https://github.com/alibaba/spring-context-support)
* [Alibaba Spring Velocity Support](https://github.com/alibaba/spring-velocity-support/)
    * [Alibaba Spring Velocity Web MVC Support](https://github.com/alibaba/spring-velocity-support/tree/master/spring-webmvc-velocity)
        * [Alibaba Spring Velocity Context Support](https://github.com/alibaba/spring-velocity-support/tree/master/spring-context-velocity)





