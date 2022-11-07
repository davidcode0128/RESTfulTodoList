package com.Davidcode.RESTfulMyWeb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // 啟用 Security 過濾器
public class WebSecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
	 InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 manager.createUser(User.withUsername("user")			 
	 .password(bCryptPasswordEncoder.encode("userpass"))
	 .roles("USER")
	 .build());
	 manager.createUser(User.withUsername("admin")
	 .password(bCryptPasswordEncoder.encode("adminpass"))
	 .roles("USER", "ADMIN")
	 .build());
	 return manager;
	 }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				
        http // 定義哪些url需要被保護
        	.cors().and().csrf().disable()
            .authorizeHttpRequests()
            	.antMatchers("/todos").hasRole("ADMIN") // 定義匹配到"/" 不需要驗證
            	.antMatchers("/swagger-ui/index.html","/api/login","/api/hello").permitAll() // 匹配到"/swagger-ui/index.html", 不需要身份驗證                
            	.anyRequest().authenticated(); // 其他尚未匹配到的url都需要身份驗證                       
        return http.build(); //產生基本表單
    }
}
