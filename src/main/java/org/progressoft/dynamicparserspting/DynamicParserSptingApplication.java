package org.progressoft.dynamicparserspting;

import org.progressoft.dynamicparserspting.connection.History;
import org.progressoft.dynamicparserspting.connection.HistoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DynamicParserSptingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicParserSptingApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(HistoryRepo history){
//        return args -> {
//            history.save(
//                    new History("deyafilec.csv",
//                            "1592",
//                            "1592"
//                    )
//            );
//        };
//    }
}
