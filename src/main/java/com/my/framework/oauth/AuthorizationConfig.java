package com.my.framework.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

// http://jsonobject.tistory.com/363
//@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//				.withClient("client_id")
//				.secret("client_secret")
//				.scopes("read:current_user", "read:users")
//				.authorizedGrantTypes("client_credentials");

//		clients.withClientDetails(clientDetailsService());
	}

	@Bean
	public ClientDetailsService clientDetailsService() {
//		return new JdbcClientDetailsService(someDateSource);
		return new JdbcClientDetailsService(null);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
	}

	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(jedisConnectionFactory());
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(6379);
		factory.setPassword("");
		factory.setDatabase(1);
		factory.setUsePool(true);

		return factory;
	}


}
