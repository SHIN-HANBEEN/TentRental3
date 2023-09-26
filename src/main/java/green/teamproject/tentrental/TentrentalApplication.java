package green.teamproject.tentrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan(basePackages = {"green.teamproject.tentrental.common.security.token"}) // Add the package containing UserTokenStore. UserTokenStore 를 반드시 포함하도록 명시적으로 지시한다. 근데, 이렇게 설정을 하면 일반 로그인은 아예 보이지도 않는 문제가 발생한다.
public class TentrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TentrentalApplication.class, args);
	}

}
