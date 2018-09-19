package apress.spring;

import apress.spring.domain.Journal;
import apress.spring.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}

}
