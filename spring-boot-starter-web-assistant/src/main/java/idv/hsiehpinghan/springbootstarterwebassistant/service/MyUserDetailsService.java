package idv.hsiehpinghan.springbootstarterwebassistant.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.RoleEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.entity.UserEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.repository.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOne(username);
		String password = userEntity.getPassword();
		Boolean enabled = userEntity.getEnabled();
		Boolean accountNonExpired = userEntity.getAccountNonExpired();
		Boolean credentialsNonExpired = userEntity.getCredentialsNonExpired();
		Boolean accountNonLocked = userEntity.getAccountNonLocked();
		Collection<? extends GrantedAuthority> authorities = generateGrantedAuthorities(userEntity.getRoles());
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
	}

	private Collection<? extends GrantedAuthority> generateGrantedAuthorities(Collection<RoleEntity> roles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(roles.size());
		for (RoleEntity role : roles) {
			GrantedAuthority grantedAuthoritie = new SimpleGrantedAuthority(role.getRoleId());
			grantedAuthorities.add(grantedAuthoritie);
		}
		return grantedAuthorities;
	}
}
