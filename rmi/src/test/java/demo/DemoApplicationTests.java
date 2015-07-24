package demo;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

public class DemoApplicationTests {

    @Configuration
    public static class DemoApplicationClient {

        @Bean
        HttpInvokerProxyFactoryBean client() {
            HttpInvokerProxyFactoryBean client = new HttpInvokerProxyFactoryBean();
            client.setServiceUrl("http://localhost:8080/messageService");
            client.setServiceInterface(MessageService.class);

            return client;
        }
    }

    @Test
    public void contextLoads() throws Exception {

        SpringApplication.run( DemoApplication.class) ;

        AnnotationConfigApplicationContext clientContext =
                new AnnotationConfigApplicationContext(DemoApplicationClient.class);
        MessageService messageService = clientContext.getBean(MessageService.class);
        Message result = messageService.greet("Josh");
        System.out.println(result.toString());
    }

}
