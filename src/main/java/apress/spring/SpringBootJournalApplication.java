package apress.spring;

import apress.spring.service.JournalEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootJournalApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(JournalEntryService journalEntryService) { // 스프링 부트 시동이 끝나면 실행됨
		return args -> {
			log.info("@@ 데이터 생성... ");
			log.info("@@ findAll() 호출");
			System.out.println("시작");
			journalEntryService.findAll().forEach(entry -> {
				System.out.print("entry : " + entry.toString());
				log.info(entry.toString());
			});
		System.out.println("완료");
		};
	}
}
