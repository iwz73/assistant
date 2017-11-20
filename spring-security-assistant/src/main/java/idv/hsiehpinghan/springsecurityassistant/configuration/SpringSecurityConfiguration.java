package idv.hsiehpinghan.springsecurityassistant.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import idv.hsiehpinghan.springsecurityassistant.entity.RoleEntity;
import idv.hsiehpinghan.springsecurityassistant.entity.UserEntity;
import idv.hsiehpinghan.springsecurityassistant.service.UserService;

@EnableWebSecurity
public class SpringSecurityConfiguration {

	@Order(0)
	@Configuration
	public static class DatabaseSpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
		private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		@Autowired
		private UserDetailsService userDetailsService;
		@Autowired
		private UserService userService;
		@Autowired
		private DaoAuthenticationProvider authenticationProvider;
		
		@PostConstruct
		public void postConstruct() {
			if(userService.findOne("user") == null) {
				String password = passwordEncoder.encode("user");
				UserEntity user = new UserEntity("user", password, true, true, true, true, Arrays.asList(new RoleEntity("ROLE_USER", "user role name", null)));
				userService.saveOrUpdate(user);
			}
			if(userService.findOne("admin") == null) {
				String password = passwordEncoder.encode("admin");
				UserEntity admin = new UserEntity("admin", password, true, true, true, true, Arrays.asList(new RoleEntity("ROLE_ADMIN", "admin role name", null)));
				userService.saveOrUpdate(admin);
			}
		}
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		    authProvider.setUserDetailsService(userDetailsService);
		    authProvider.setPasswordEncoder(passwordEncoder);
		    return authProvider;
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(userDetailsService);
			authenticationManagerBuilder.authenticationProvider(authenticationProvider);
		}
		
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.authorizeRequests()
				.antMatchers("/common").permitAll()
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and().formLogin().loginProcessingUrl("/loginProcessingUrl").loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username").passwordParameter("password").permitAll()
				.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/common/logoutPage").permitAll();
		}
	}
	
	@Configuration
	public static class MemorySpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
		private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.authorizeRequests()
				.antMatchers("/common").permitAll()
				.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and().formLogin().loginProcessingUrl("/loginProcessingUrl").loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username").passwordParameter("password")
				.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/common/logoutPage")
				.permitAll();
		}
	
		@Override
		public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			String userPassword = passwordEncoder.encode("user");
			authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password(userPassword).roles("USER");
			String adminPassword = passwordEncoder.encode("admin");
			authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password(adminPassword).roles("ADMIN");
		}
	}
}
