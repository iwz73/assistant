package idv.hsiehpinghan.springbootstarterwebassistant.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.RoleEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.entity.UserEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
//			.antMatchers("/actuator/**").permitAll()
			.antMatchers("/common/**").permitAll()
			.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and().formLogin().loginProcessingUrl("/loginProcessingUrl").loginPage("/common/loginPage").failureUrl("/common/loginFailPage").usernameParameter("username").passwordParameter("password")
			.and().logout().logoutUrl("/logoutUrl").logoutSuccessUrl("/common/logoutPage")
			.permitAll();
	}

	@PostConstruct
	public void postConstruct() {
		if(userService.findOne("user") == null) {
			UserEntity user = new UserEntity("user", "user", true, true, true, true, Arrays.asList(new RoleEntity("ROLE_USER", "user role name", null)));
			userService.save(user);
		}
		if(userService.findOne("admin") == null) {
			UserEntity admin = new UserEntity("admin", "admin", true, true, true, true, Arrays.asList(new RoleEntity("ROLE_ADMIN", "admin role name", null)));
			userService.save(admin);
		}
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}
}
