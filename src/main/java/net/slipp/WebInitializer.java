package net.slipp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 외장 톰캣을 이용한 war 배포를 위해 webInitializer를 만들어 초기화시키는 과정이 필요함.
// 그 때의 webInitializer는 반드시 springbootservletinitializer를 상속 받아야 한다.
public class WebInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 기존에 있는 application에 있는 소스를 가지고 초기화 하겠다는 의미
		return builder.sources(Application.class);
	}
}
