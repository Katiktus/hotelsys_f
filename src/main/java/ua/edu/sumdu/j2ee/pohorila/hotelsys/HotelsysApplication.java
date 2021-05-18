package ua.edu.sumdu.j2ee.pohorila.hotelsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ua.edu.sumdu.j2ee.pohorila.hotelsys.dao.DAOInterfaceOracleImpl;

@SpringBootApplication
public class HotelsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelsysApplication.class, args);
    }

}
