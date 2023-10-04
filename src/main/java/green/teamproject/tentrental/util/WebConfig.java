package green.teamproject.tentrental.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 첨부 파일의 경로를 맵핑하기 위한 설정 클래스 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    String webpath = "file:/C:\\Users\\user\\Documents\\uploadfile\\"; //첨부 폴더 경로

    //스프링 보안 문제로 외부 폴더에 바로 접근할 수 없습니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //폴더와 상대 경로 맵핑
        registry.addResourceHandler("/uploadfile/**").addResourceLocations(webpath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
