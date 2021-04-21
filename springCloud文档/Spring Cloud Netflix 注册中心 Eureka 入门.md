# Spring Cloud Netflix 注册中心 Eureka 入门

## 1.Eureka概述

1. **什么是服务治理**　　
   Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务治理。在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

2.  **什么是服务注册与发现**

   Eureka采用了CS的设计架构，Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。
   在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息 比如 服务地址通讯地址等以别名方式注册到注册中心上。另一方（消费者|服务提供者），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用RPC远程调用框架核心设计思想：在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系(服务治理概念)。在任何rpc远程框架中，都会有一个注册中心(存放服务地址相关信息(接口地址))

3. **Eureka两组件**

   - Eureka Server 提供服务注册：各个微服务节点通过配置启动后，会在Eureka-Server中进行注册，服务节点的信息会在Eureka-Server的运维界面进行直观展示
   - Eureka Client 服务客户端：Java客户端，用于简化Eureka-Server的交互，在应用启动后，向Eureka-Server注册服务。

Eureka是Netfilx开源的注册中心组件,`spring-cloud-starter-netflix-eureka-client`和`spring-cloud-starter-netflix-eureka-server`组件分别构成了Eureka-Server和Eureka-Client两个角色。两个角色构成的构架图如下所示：

![](D:\Learn_Java_Demor\springCloud文档\eureka_architecture.png)

- Eureka-Server：通过REST协议暴露服务，提供服务的**注册和发现**。
- Application -Server:服务的**提供者**，内嵌**Eureka-Client**,通过他想注册中心**Eureka-Server**注册自身服务。
- Application- Client:服务**消费者**,内嵌**Eureka-Client**，通过他从注册中心**Eureka-Server**获取服务列表。

> **注**: Application-Server 和Application-Client强调的是角色，实现可以在同一个JVM进程中，即使服务提供者也是服务消费者。

Eureka-Server 在CAP定理中，选择了AP：

- 一致性（Consistency）:等同于所有节点访问同一份最新的数据副本。
- 可用性（Availability）:每次请求都获取**非错**响应，但是**不**保证获取的数据是最新的数据。

## 2.注册中心原理

注册中心在使用时，有三种角色，Server Provider(服务提供者)、Server Consumer(服务消费者)、Registry(注册中心)。他们的之间交互如图所示:

![](D:\Learn_Java_Demor\springCloud文档\test.png.png)

- Provider: 服务提供者，启动时，将自己的IP等信息作为一个服务实例向**Registry**进行注册，同时定期向**注册中心（Registry）**发送心跳，告诉注册中心他还存活
- Comsumer:服务消费者，启动时，向**注册中心(Registry)**订阅需要使用的服务，并缓存服务的实例列表到内存中。
- Registry：注册中心，当服务超过一定时间没有心跳时，将服务从实例列表中移除

## 3.代码实现

> 本章节我们主要来搭建一个Eureka的实例，步骤如下：

- 使用`spring-cloud-starter-netflix-eureka-server`依赖，搭建Eurekr-Server注册中心。
- 使用`spring-cloud-starter-netflix-eureka-client`搭建服务的提供者，注册到Eureka-Server.
- 使用`spring-cloud-starter-netflix-eureka-client`搭建一个服务的消费者，从Eureka-Server中获取服务提供者的服务实例列表，选择其中的一个实例，进行Http远程调用。

### 3.1 搭建服务注册中心

> 创建Eureka-server-demo8001项目，作为**注册中心**

#### 3.1.1 引入注册中心依赖

创建pom.xml文件，主要引入Eureka-Server的相关依赖，实现如下：

```
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

通过`spring-cloud-starter-netflix-eureka-server`包，引入Eureka-Server相关依赖，将Eureka作为注册中心。

#### 3.1.2 服务提供者配置文件

创建application.yml配置文件，添加Eureka相关配置，代码如下:

```
server:
  port: 8001
spring:
  application:
    name: eureka-server
eureka:
  client:
    #false 表示不向注册中心注册自己 ，默认true，
    register-with-eureka: false
    # 默认true,false表示自己端就是注册中心，职责是维护服务实例，不需要检索服务
    fetch-registry: false
```

#### 3.1.3 启动类实现

创建``EurekaServerDemo8001Application``类实现注册中心的启动类，代码如下:

```
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerDemo8001Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemo8001Application.class, args);
    }

}
```

**注**：@EnableEurekaServer用于声明Eureka-Server

#### 3.1.4 注册中心测试

启动EurekaServerDemo8001Application用于启动注册中心，控制台打印Eureka相关日志如下：

```
2021-04-06 19:19:30.510  INFO 11968 --- [      Thread-52] e.s.EurekaServerInitializerConfiguration : Started Eureka Server

```

使用浏览器访问http://localhost:8001 ,可以查看Eureka-Server运维界面，如下图所示:

![](D:\Learn_Java_Demor\springCloud文档\wureka运维界面.png)

### 3.2 搭建服务提供者

> 创建Eureka-client-provider8002项目，实现服务提供者的搭建

#### 3.2.1 服务搭建的依赖

创建pom.xml，主要引入`spring-cloud-starter-netflix-eureka-client`依赖，代码如下:

```
<properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Hoxton.SR10</spring-cloud.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

#### 3.2.2 服务提供者的配置文件

创建application.yml，用于添加服务提供者向注册中心注册的配置，代码如下:

```
server:
  port: 8002
spring:
  application:
    name: eureka-clinet-provider
eureka:
  client:
    register-with-eureka: true #注册到Eureka-Server
    fetch-registry: true # 从Eureka-server获取注册表
    service-url:
      defaultZone: http://localhost:8001/eureka/ #注册表地址


```

**service-url**配置项，设置**注册中心地址**

#### 3.2.3 服务提供者类的实现

- 创建服务的Service层

```
public interface IProviderService {



    public String getProviderInfo( Long id);
}

```

```
@Service
public class ProviderServiceImpl implements IProviderService {
    public String getProviderInfo(Long id) {
        return  "provider: "+Thread.currentThread().getName()+"  ID: "+id;

    }
}

```

- 创建服务的controller层

```
@RestController
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @Value("${server.port}")
    private  String serverport;



    @GetMapping(value = "/provider/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){

        String info = providerService.getProviderInfo(id);

        return "Server.Port:"+ serverport+"  "+info;
    }

}

```

- 启动项目类

```

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientProvider8002Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProvider8002Application.class, args);
    }

}

```

启动项目后打开，注册中心运维界面，如图所示：

![](D:\Learn_Java_Demor\springCloud文档\提供者.png)

### 3.3 搭建服务消费者

> 创建Eureka-client-consumer8003项目，作为服务的消费者

#### 3.3.1 服务消费者项目依赖

和【3.2.1】依赖相同

#### 3.3.2 服务消费者配置文件

创建application.yml配置文件，添加服务消费者响应的配置,配置如下:

```
server:
  port: 8003
spring:
  application:
    name: eureka-client-consumer8003
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8001/eureka/

```

#### 3.3.3 服务消费者的类实现

- config层的实现

  ```
  @Configuration
  public class ConsumerConfig {
  
  
      @LoadBalanced
      @Bean
      public RestTemplate restTemplate(){
          return new RestTemplate();
      }
  }
  ```

- controller层的实现

```
@RestController
public class ConsumerController {

    private static final String PAYMENT_URL="http://EUREKA-CLINET-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private  String serverport;



    @GetMapping(value = "/consumer/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){

        return restTemplate.getForObject(PAYMENT_URL+"/provider/get/"+id,String.class);

    }

}
```

- 启动项目类

```
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientConsumer8003Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumer8003Application.class, args);
    }

}
```

启动EurekaClientConsumer8003Application后打开运维界面，如图所示:

![](D:\Learn_Java_Demor\springCloud文档\火狐截图_2021-04-06T07-30-17.659Z.png)

**访问** http://localhost:8003/consumer/get/3  返回结果为**Server.Port:8002  provider: http-nio-8002-exec-3  ID: 3** 说明调用远程的服务提供者成功。

## 4.Eureka集群

> 结合上面章节的Eureka-Provider、Eureka-Consumer和Registry在添加多个注册中心和服务提供者节点实现Eureka注册中心的高可用集群。

### 4.1  搭建eureka-server-demo8004注册中心项目

> eureka-server-demo8004项目和eureka-server-demo8001项目几乎一样只有**application.yml不相同**

#### 4.1.1 修改两个注册中心的application.yml的配置

- **eureka-server-demo8004**配置

```
server:
  port: 8004
spring:
  application:
    name: eureka-server
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://replica1:8001/eureka/
  instance:
    hostname: eureka-replica2
```

- **eureka0server-demo8001配置**

```
server:
  port: 8001
spring:
  application:
    name: eureka-server

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://replica2:8004/eureka/
  instance:
    hostname: eureka-replica1
#eureka:
#  client:
#    #false 表示不向注册中心注册自己 ，默认true，
#    register-with-eureka: false
#    # 默认true,false表示自己端就是注册中心，职责是维护服务实例，不需要检索服务
#    fetch-registry: false

```

修改本地**host**(C:\Windows\System32\drivers\etc)文件，设置两个主机名映射的IP地址。

```
127.0.0.1 replica1
127.0.0.1 replica2
```

### 4.2 搭建eureka-client-provider8005服务提供者

> 项目eureka-client-provider8005和eureka-client-provider8002项目除了application.yml配置不同为其他全部相同

- **eureka-client-provider8005** 的application.yml配置

```
server:
  port: 8005
spring:
  application:
    name: eureka-clinet-provider
eureka:
  client:
    register-with-eureka: true #注册到Eureka-Server
    fetch-registry: true # 从Eureka-server获取注册表
    service-url:
      defaultZone: http://replica1:8001/eureka/,http://replica2:8004/eureka/ #注册表地址
  instance:
    instance-id: provider8005
```

- **eureka-client-provider8002** 的application.yml配置

```
server:
  port: 8002
spring:
  application:
    name: eureka-clinet-provider
eureka:
  client:
    register-with-eureka: true #注册到Eureka-Server
    fetch-registry: true # 从Eureka-server获取注册表
    service-url:
      defaultZone: http://replica1:8001/eureka/,http://replica2:8004/eureka/ #注册表地址
  instance:
    instance-id: provider8002
```

### 4.3 修改服务消费者eureka-client-consumer8003的application.yml

```
server:
  port: 8003
spring:
  application:
    name: eureka-client-consumer8003
eureka:
  client:
    register-with-eureka: true #注册到Eureka-Server
    fetch-registry: true # 从Eureka-server获取注册表
    service-url:
      defaultZone: http://replica1:8001/eureka/,http://replica2:8004/eureka/ #注册表地址
```

### 4.4 测试

> 启动这五个项目，注册中心运维界面如下:

![](D:\Learn_Java_Demor\springCloud文档\集群.png)

**注**:使用浏览器运行消费者服务接口连接http://localhost:8003/consumer/get/3 得到的结果为

```
Server.Port:8002  provider: http-nio-8002-exec-1  ID: 3

Server.Port:8005  provider: http-nio-8005-exec-3  ID: 3
```

**表明消费者通过轮询服务的提供者**

