package demo;

import com.sendgrid.SendGrid;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @ConditionalOnBean(SendGrid.class)
    @Bean
    CommandLineRunner runner(SendGrid sendGrid) {
        return args -> {
            SendGrid.Email email = new SendGrid.Email();
            email.setHtml("<hi>Hello Josh</h1>");
            email.setTo(new String[]{"jlong@pivotal.io"});
            email.setToName(new String[]{"Josh"});
            SendGrid.Response send = sendGrid.send(email);
            System.out.println("received " + send.getCode());
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
