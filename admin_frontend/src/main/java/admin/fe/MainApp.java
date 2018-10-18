package admin.fe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*

Author Muhammad Burhanudin

 */


@SpringBootApplication
@Controller
public class MainApp extends SpringBootServletInitializer{
   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
   }
   @GetMapping("/index")
   public String mvvmExample() {
      return "index";
   }

   @GetMapping("/resources")
   public String resourcesExample() {
      return "resources";
   }
}
