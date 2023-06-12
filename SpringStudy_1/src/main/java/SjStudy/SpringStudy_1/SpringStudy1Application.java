package SjStudy.SpringStudy_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   //톰캣 서버 내장
public class SpringStudy1Application {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		System.setProperty("spring.devtools.livereload.enabled", "true");
		SpringApplication.run(SpringStudy1Application.class, args);
	}
}
