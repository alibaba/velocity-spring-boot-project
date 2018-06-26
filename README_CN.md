# Velocity Spring Boot Project

Spring Boot Starter 支持模板引擎 velocity 以及 velocity tools，该Starter是官方Starter的补充。




## [发布日志](release-notes.md)




## [English Document](README.md)




## 发布版本

````xml
<dependencies>

    ......
    
    <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>velocity-spring-boot-starter</artifactId>
        <version>1.0.4.RELEASE</version>
    </dependency>

    ......

</dependencies>
````




## 依赖和兼容性

| 依赖   | 兼容性 |
| -------------- | ------------- |
| Java           | 1.7 +         |
| Velocity       | 1.7 +         |
| Velocity Tools | 2.0 +         |
| Servlet        | 3.0 +         |
| Spring Boot    | 1.4 +         |
| [Alibaba Spring Boot Web Support](https://github.com/alibaba/spring-boot-web-support) | 1.0.0 + |
| [Alibaba Spring WebMVC Support](https://github.com/alibaba/spring-webmvc-support) | 1.0.0 + |
| [Alibaba Spring Context Support](https://github.com/alibaba/spring-context-support) | 1.0.0 + |
| [Alibaba Spring Velocity Web MVC Support](https://github.com/alibaba/spring-velocity-support/tree/master/spring-webmvc-velocity) | 1.4.3.18 + |




## 功能特性




### 模板页面设置




#### 模板页面资源根路径

````properties
# Velocity 页面资源路径（可选），默认值："classpath:/templates/"
spring.velocity.resource-loader-path = classpath:/templates/velocity

# 可选方式
# spring.velocity.resourceLoaderPath = classpath:/templates/velocity
````




#### 模板页面文件前缀

````properties
# Velocity  页面文件前缀（可选），默认值：""
spring.velocity.prefix = /default/
````




#### 模板页面文件后缀

````properties
# Velocity  页面文件后缀（可选），默认值：".vm"
spring.velocity.suffix = .vm
````




#### 举例说明

* 配置项

````properties
spring.velocity.resource-loader-path = classpath:/templates/velocity
spring.velocity.prefix = /default/
spring.velocity.suffix = .vm
````

* Java 代码

````java
@RequestMapping(value = {"/", ""})
public String index(Model model) {
    return "index";
}
````

根据以上配置和Java代码为例，
当前请求（URL："/"）访问时，Spring MVC 会通过路径 `classpath:/templates/velocity/default/index.vm` 来寻找Velocity资源页面。




### Spring Boot 外部化配置模式


配置项模式

````properties
spring.velocity.properties.${velocity-property-name} = ${velocity-property-value}
````

例如，传统 Velocity Properties 配置

````properties
velocimacro.library = global_library.vm
velocimacro.permissions.allow.inline = true
velocimacro.permissions.allow.inline.to.replace.global = false
velocimacro.context.localscope = false
velocimacro.library.autoreload = false
#...
````

Spring Boot Velocity 配置

````properties
spring.velocity.properties.velocimacro.library = global_library.vm
spring.velocity.properties.velocimacro.permissions.allow.inline = true
spring.velocity.properties.velocimacro.permissions.allow.inline.to.replace.global = false
spring.velocity.properties.velocimacro.context.localscope = false
spring.velocity.properties.velocimacro.library.autoreload = false
#...
````

若需了解更多 Velocity 宏内容，请参考[官方 Velocity 宏章节](http://velocity.apache.org/engine/1.7/developer-guide.html#velocimacro)




### 多资源加载器（`ResourceLoader`）

Velocity 支持多资源加载器（`ResourceLoader`）配置，每种资源加载器均为
`org.apache.velocity.runtime.resource.loader.ResourceLoader` 的实例，其
意图在于提供多种Velocity模板资源方式，以突破传统渲染引擎资源加载单一的局限。




#### 资源加载器（`ResourceLoader`）类层次关系

资源加载器（`ResourceLoader`）类层次关系如下：

* `org.apache.velocity.runtime.resource.loader.ResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.FileResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.JarResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.StringResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.URLResourceLoader`
    * `org.apache.velocity.runtime.resource.loader.DataSourceResourceLoader`
    * `org.apache.velocity.tools.view.WebappResourceLoader` ( Velocity Tools 提供 ）
    * `org.springframework.ui.velocity.SpringResourceLoader` ( Spring Framework 提供 ）




#### Spring Boot 多资源加载器（`ResourceLoader`）配置


当读者了解资源加载器（`ResourceLoader`）类层次关系后，还需回顾传统的Velocity
Properties 配置方式。


* 传统的 Velocity Properties 配置方式

````properties
# ResourceLoader 名称设置，多值以","分割
resource.loader = file,classpath

# 配置 "file" ResourceLoader
file.resource.loader.class = org.apache.velocity.runtime.resource.loader.FileResourceLoader
## 是否缓存资源，默认值为: false
file.resource.loader.cache = false
## 配置 "file" ResourceLoader 根路径，多值以","分割
file.resource.loader.path = ${user.home}/templates,${user.dir}/templates
## 配置 "file" ResourceLoader 修改检查时间间隔，时间单位为秒
file.resource.loader.modificationCheckInterval = 2

# 配置 "classpath" ResourceLoader
classpath.resource.loader.class = org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
````

更多传统配置细节，请参考[官方资源管理章节](http://velocity.apache.org/engine/1.7/developer-guide.html#resource-management)


* 等效的 Spring Boot Properties 配置方式

````properties
# ResourceLoader 名称设置，多值以","分割
spring.velocity.properties.resource.loader = file,classpath

# 配置 "file" ResourceLoader
spring.velocity.properties.file.resource.loader.class = org.apache.velocity.runtime.resource.loader.FileResourceLoader
## 是否缓存资源，默认值为: false
spring.velocity.properties.file.resource.loader.cache = false
## 配置 "file" ResourceLoader 根路径，多值以","分割
spring.velocity.properties.file.resource.loader.path = ${user.home}/templates,${user.dir}/templates
## 配置 "file" ResourceLoader 修改检查时间间隔，时间单位为秒
spring.velocity.properties.file.resource.loader.modificationCheckInterval = 2

# 配置 "classpath" ResourceLoader
spring.velocity.properties.classpath.resource.loader.class = org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
````


* 特别提醒

Spring Boot 继承了 Spring Framework 中的特性，其中
`org.springframework.ui.velocity.VelocityEngineFactory`将
`org.springframework.ui.velocity.SpringResourceLoader`作为
默认资源加载器（`ResourceLoader`）配置到
`org.apache.velocity.app.VelocityEngine`，因此，在配置过程中，请不要重复配置。
`org.springframework.ui.velocity.VelocityEngineFactory`初始化后，

等价传统 Velocity Properties 配置方式：

````properties
resource.loader = spring
spring.resource.loader.class = org.springframework.ui.velocity.SpringResourceLoader
spring.resource.loader.cache = true
````

等价 Spring Boot Properties 配置方式：

````properties
spring.velocity.properties.resource.loader = spring
spring.velocity.properties.spring.resource.loader.class = org.springframework.ui.velocity.SpringResourceLoader
spring.velocity.properties.spring.resource.loader.cache = true
````


### Velocity Layout 支持


官方默认自动装配 org.springframework.web.servlet.view.velocity.VelocityViewResolver  , 
当前 Starter 将自动装配 org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver，
代替VelocityViewResolver , 对Layout 支持。


#### Layout 配置项

为了与保持官方继承配置项前缀 "spring.velocity."，因此其他Velocity配置项与官方保持一致，

例如：
````properties
spring.velocity.resource-loader-path = classpath:/templates/velocity

# 可选方式
# spring.velocity.resourceLoaderPath = classpath:/templates/velocity
````


##### 布局页面资源位置URL

layoutUrl : layout VM页面的URL路径。

配置项
````properties
# Velocity 布局页面资源路径（可选，相对于classpath目录下，不需要增加协议头"classpath:"），默认值：layout.vm
spring.velocity.layout-url = /layout/default.vm

# 可选方法
# spring.velocity.layoutUrl = /layout/default.vm
````


##### 布局页面资源位置的渲染上下文名称

layoutKey : 布局页面资源位置渲染上下文名称

配置项
````properties
# Velocity 布局页面资源位置渲染上下文名称（可选），默认值：layout
spring.velocity.layout-key = layout_key

# 可选方法
# spring.velocity.layoutKey = layout_key
````

Java代码
````java
@RequestMapping(value = {"/layout"})
public String layout(Model model) {
    // spring.velocity.layoutKey = layout_key
    return "index";
}
````


##### MVC View 渲染HTML内容的渲染上下文名称

screenContentKey : MVC View 渲染HTML内容的渲染上下文名称

配置项
````properties
# Velocity View 渲染HTML内容的渲染上下文名称（可选），默认值：screen_content
spring.velocity.screen-content-key = body_content

# 可选方式
# spring.velocity.screenContentKey = body_content
````

Layout Velocity 代码
````html
<html>
    <head>
        <title>$!pageTitle</title>
    </head>
    <body>
        <!-- MVC View 渲染HTML内容的渲染上下文名称 -->
        <!-- spring.velocity.screenContentKey = body_content -->
        $body_content
    </body>
</html>
````


#### `@VelocityLayout` 支持

velocity-spring-boot-starter 1.0.0.RELEASE 版本开始提供Annotation
`com.alibaba.boot.velocity.annotation.VelocityLayout`，该Annotation
与Spring Web MVC 中的`@Controller`配合使用，覆盖默认velocity layout 页面URL
（即配置项`spring.velocity.layout-url`)。


##### `@VelocityLayout` 定义

````java
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
````

@VelocityLayout 可以定义在 @Controller 类或其处理方法上。


#### `@VelocityLayout` 处理逻辑

* 当 @Controller 处理方法中，将配置项`spring.velocity.layout-key`（默认值:"layout"）
的值作为上下文名称，如果该上下文名称在渲染上下文中或者模型（例如：org.springframework.ui.Model)
已存在的话，@VelocityLayout 处理将被忽略。

* 否则，当@VelocityLayout 标记在 @Controller 处理方法上时，
将@VelocityLayout#value()值作为Velocity Layout URL。

* 否则，当@VelocityLayout 标记在 @Controller 类上时，其所有的处理方法均继承
@VelocityLayout#value()值作为Velocity Layout URL。

* 否则，配置项`spring.velocity.layout-url`中定义的值作为Velocity Layout URL。


#### `@VelocityLayout` 实例代码分析

````java
@Controller
@VelocityLayout("/layout/default.vm")
public class VelocityLayoutController extends BaseController {

    @Autowired
    private VelocityLayoutProperties velocityLayoutProperties;

    @RequestMapping(value = {"/layout"})
    @VelocityLayout("/layout/layout2.vm")
    public String layout(Model model) {
        // 布局页面资源位置的渲染上下文名称
        // spring.velocity.layoutKey = layout_key
        model.addAttribute(velocityLayoutProperties.getLayoutKey(), "/layout/layout.vm");
        return "index";
    }

    @RequestMapping("/layout2")
    @VelocityLayout("/layout/layout2.vm")
    public String layout2(Model model) {
        return "index";
    }

    @RequestMapping("/layout3")
    public String layout3(Model model) {
        return "index";
    }

}
````

根据 `@VelocityLayout` 处理逻辑，

* "/layout1" 的Velocity Layou URL 使用了 Model 中的 "/layout/layout.vm"
(Java Code : `model.addAttribute(velocityLayoutProperties.getLayoutKey(), "/layout/layout.vm")`)

* "/layout2" 的Velocity Layou URL 使用了 处理方法layout2上的 @VelocityLayout("/layout/layout2.vm")

* "/layout3" 的Velocity Layou URL 使用了 VelocityLayoutController 上的 @VelocityLayout("/layout/default.vm")


#### Layout 激活


配置项
````properties
# Velocity Layout 激活（可选，"true"表示激活，“false”表示失效），默认值：true
spring.velocity.layout-enabled = false
````



### Velocity 渲染支持


#### 独占 VelocityViewResolver 配置

Spring Boot 自动装配 Spring WebMVC 所提供的
`org.springframework.web.servlet.view.ContentNegotiatingViewResolver`组
件，`org.springframework.web.servlet.view.ContentNegotiatingViewResolver`
的作用是协调`org.springframework.web.servlet.ViewResolver`列表，其中
`com.alibaba.boot.velocity.web.servlet.view.EmbeddedVelocityLayoutViewResolver`
（`org.springframework.web.servlet.ViewResolver`实例）。



Velocity `org.springframework.web.servlet.ViewResolver` 层次关系

* `org.springframework.web.servlet.ViewResolver` ( spring-webmvc )
    * `org.springframework.web.servlet.view.velocity.VelocityViewResolver` ( spring-webmvc )
        * `org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver`  ( spring-webmvc )
        * `org.springframework.boot.web.servlet.view.velocity.EmbeddedVelocityViewResolver`  ( spring-boot )
            * `com.alibaba.boot.velocity.web.servlet.view.EmbeddedVelocityLayoutViewResolver`  ( velocity-spring-boot-starter )

Spring WebMVC 页面渲染执行时，
`org.springframework.web.servlet.view.ContentNegotiatingViewResolver`将
`org.springframework.web.servlet.ViewResolver`列表顺序（通过顺序添加或者实现
`org.springframework.core.Ordered`接口来决定次序）地处理，当获取
`org.springframework.web.servlet.View`后，再执行渲染。


因此，`org.springframework.web.servlet.view.velocity.VelocityViewResolver`
或其派生类无法保障在
`org.springframework.web.servlet.view.ContentNegotiatingViewResolver`中
最优先执行。由此，`velocity-spring-boot-starter` 1.0.1 版本开始，支持单一
Velocity 渲染配置，配置项相当简单：

````properties
# 配置 ViewResolver Bean 名称，
# 如 org.springframework.web.servlet.view.velocity.VelocityViewResolver
# 的名称为 "velocityViewResolver"
web-support.exclusive-view-resolver = velocityViewResolver
````

当`web-support.exclusive-view-resolver`配置为`velocityViewResolver`时，
`org.springframework.web.servlet.view.ContentNegotiatingViewResolver`
的`org.springframework.web.servlet.ViewResolver`列表成员中仅存在
`org.springframework.web.servlet.view.velocity.VelocityViewResolver`或其
派生类实例。


### Velocity Tools 支持


Velocity Spring Boot Starter 支持 Tools 2.0 功能，通用功能文档，请参考：
http://velocity.apache.org/tools/2.0/generic.html


#### Tools 配置项


Velocity Tools 配置是以XML文件为载体，XML文件的搜索路径：

首先，搜索 ServletContext 根目录，如果找不到的话，搜索ClassPath。

配置项如下：

````properties
spring.velocity.toolbox-config-location=/toolbox/tools.xml

# 可选方式
# spring.velocity.toolboxConfigLocation=/toolbox/tools.xml
````


#### Tools XML 配置


````xml
<tools>
    <data type="number" key="TOOLS_VERSION" value="2.0"/>
    <data type="boolean" key="GENERIC_TOOLS_AVAILABLE" value="true"/>
    <toolbox scope="application">
        <tool class="org.apache.velocity.tools.generic.AlternatorTool"/>
        <tool class="org.apache.velocity.tools.generic.ClassTool"/>
        <tool class="org.apache.velocity.tools.generic.ComparisonDateTool"/>
        <tool class="org.apache.velocity.tools.generic.ConversionTool"/>
        <tool class="org.apache.velocity.tools.generic.DisplayTool"/>
        <tool class="org.apache.velocity.tools.generic.EscapeTool"/>
        <tool class="org.apache.velocity.tools.generic.FieldTool"/>
        <tool class="org.apache.velocity.tools.generic.MathTool"/>
        <tool class="org.apache.velocity.tools.generic.NumberTool"/>
        <tool class="org.apache.velocity.tools.generic.ResourceTool"/>
        <tool class="org.apache.velocity.tools.generic.SortTool"/>
        <tool class="org.apache.velocity.tools.generic.XmlTool"/>
    </toolbox>
    <toolbox scope="request">
        <tool class="org.apache.velocity.tools.generic.ContextTool"/>
        <tool class="org.apache.velocity.tools.generic.LinkTool"/>
        <tool class="org.apache.velocity.tools.generic.LoopTool"/>
        <tool class="org.apache.velocity.tools.generic.RenderTool"/>
        <tool class="org.apache.velocity.tools.view.CookieTool"/>
        <tool class="org.apache.velocity.tools.view.ImportTool"/>
        <tool class="org.apache.velocity.tools.view.IncludeTool"/>
        <tool class="org.apache.velocity.tools.view.PagerTool"/>
        <tool class="org.apache.velocity.tools.view.ParameterTool"/>
        <tool class="org.apache.velocity.tools.view.ViewContextTool"/>
        <!--
        This is not directly useable.
        <tool class="org.apache.velocity.tools.view.AbstractSearchTool"/>
        -->
        <!-- move this to request scope -->
        <tool class="org.apache.velocity.tools.generic.ResourceTool"/>
        <!--
        This is not useful in its default form.
        But, if it were, it'd be request-scoped.
        <tool class="org.apache.velocity.tools.generic.ValueParser"/>
        -->
    </toolbox>
</tools>
````


#### Tools Annotation 配置

从 1.0.1 版本开始，`velocity-spring-boot-starter` 新增Tools Annotation 配置
方式，以简化配置方式（相对于 XML 方式）


##### 实现 Tool


`velocity-spring-boot-starter` 使用了 Velocity Tools 2.0 ，该版本使用
Annotation 的方式来申明 Tool 对象，即在实现类上标记
`org.apache.velocity.tools.config.DefaultKey`，其中属性
`org.apache.velocity.tools.config.DefaultKey#value()`来决定渲染上下文的变量
名称。

例如：

* Java Code

    ````java
    package com.alibaba.boot.velocity.tools

    import org.apache.commons.logging.Log;
    import org.apache.commons.logging.LogFactory;
    import org.apache.velocity.tools.config.DefaultKey;
    import org.apache.velocity.tools.generic.SafeConfig;

    @DefaultKey("echo")
    public class EchoTool extends SafeConfig {

        private final Log logger = LogFactory.getLog(this.getClass());

        public void echo(String message) {

            logger.info(message);

        }

    }
    ````


#### 装配 Tool

当 Tool 实现后，仍可以使用传统的 XML 方式来装配，推荐使用类似于 Spring
`@ComponentScan` 方式将指定 base packages 下的 Tool 实现装配到 Velocity 渲染
上下文中，配置如下：

````properties
# Velocity Tools 扫描 base packages，多值配置通过","分割，默认值为 : null
spring.velocity.tools-base-packages = org.apache.velocity.tools.generic,com.alibaba.boot.velocity.tools
````


#### 执行 Tool


当 `com.alibaba.boot.velocity.tools.EchoTool` 实现和配置完成后，Velocity 页
面中可直接使用该 Tool 对象，例如：

* Velocity Code

    ````html
    $echo.echo("Hello,World")
    ````

* 日志输出

    ````
    Hello,World
    ````

## 下游工程

* [Alibaba Spring Boot Web Support](https://github.com/alibaba/spring-boot-web-support)
    * [Alibaba Spring WebMVC Support](https://github.com/alibaba/spring-webmvc-support)
        * [Alibaba Spring Context Support](https://github.com/alibaba/spring-context-support)
* [Alibaba Spring Velocity Support](https://github.com/alibaba/spring-velocity-support/)
    * [Alibaba Spring Velocity Web MVC Support](https://github.com/alibaba/spring-velocity-support/tree/master/spring-webmvc-velocity)
        * [Alibaba Spring Velocity Context Support](https://github.com/alibaba/spring-velocity-support/tree/master/spring-context-velocity)