package s23.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import s23.crm.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
			new AntPathRequestMatcher("/h2-console/**"),
			new AntPathRequestMatcher("/api/**"), 
			new AntPathRequestMatcher("/css/**"),
			new AntPathRequestMatcher("/meetings/**"),
			new AntPathRequestMatcher("/meeting/**"),
			new AntPathRequestMatcher("/meeting1/**")
			};
	
	private static final AntPathRequestMatcher[] USER_LIST_URLS = {
			};
	
	private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
			new AntPathRequestMatcher("/delete/**") };
			
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequest -> authorizeRequest
				.requestMatchers(WHITE_LIST_URLS).permitAll()
				.requestMatchers(USER_LIST_URLS).hasAnyAuthority("USER", "ADMIN")
				.requestMatchers(ADMIN_LIST_URLS).hasAuthority("ADMIN")
				.anyRequest().authenticated()).formLogin(formlogin -> formlogin
					    .loginPage("/login")
					    .failureUrl("/login?error=true")
					    .defaultSuccessUrl("/crm", true)
					    .permitAll())
				.logout(logout -> logout.permitAll())
				.csrf(csrf -> csrf.disable()); // not for production, only for development
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	  @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}