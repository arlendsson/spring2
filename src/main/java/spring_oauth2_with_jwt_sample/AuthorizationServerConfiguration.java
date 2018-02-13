package spring_oauth2_with_jwt_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//http://sgdev-blog.blogspot.kr/2016/04/spring-oauth2-with-jwt-sample.html
@Configuration
@EnableAuthorizationServer	// 토큰을 발행하고 발행한 토큰을 검증
public class AuthorizationServerConfiguration {
	@Value("${resource.id:spring-boot-application}")
    private String resourceId;
    
    @Value("${access_token.validity_period:3600}")
    int accessTokenValiditySeconds = 3600;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
    	return new JwtAccessTokenConverter();
    }
}