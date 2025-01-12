package vn.com.saytruyen.admin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Story service application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "vn.com.saytruyen.admin_service",
        "io.github.buianhtai1205.saytruyen_common_service.exception"
})
public class AdminServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }
}
