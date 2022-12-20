package scii.training;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages="scii.training")
@MapperScan("scii.training.mapper")
public class SpringBootProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjApplication.class, args);
	}

}
