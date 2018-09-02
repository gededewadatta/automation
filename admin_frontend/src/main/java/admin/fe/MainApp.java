package admin.fe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"admin.fe.engine","admin.fe.config","admin.fe.controller"})
@SpringBootApplication
public class MainApp extends SpringBootServletInitializer{
   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
   }
}
