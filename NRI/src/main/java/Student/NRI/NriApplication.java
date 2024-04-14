package Student.NRI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("Student.NRI.model")
public class NriApplication {

	public static void main(String[] args) {
		SpringApplication.run(NriApplication.class, args);
	}

}