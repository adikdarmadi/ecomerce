package com.ecomerce.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecomerce.dao.LoginUserDao;
import com.ecomerce.entities.LoginUser;

/**
 * UserService implements org.springframework.security.core.userdetails.UserDetailsService
 * 
 * @author Roberto
 */
public class UserService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private LoginUserDao loginUserDao;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public UserService() {
		super();
	}

	/**
	 * this method is called for rest authentication from client
	 * 
	 * @author Roberto
	 */
	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		List<LoginUser> loginUsers = loginUserDao.findByNamaUser(username);
		if (loginUsers.isEmpty()) {
			throw new UsernameNotFoundException("user not found");
		}
		LoginUser loginUser = loginUsers.get(0);
		//validasi tambahan lakukan di sini
		
//		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser
//				.getKelompokUser().getKelompokUser());
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		UserDetails userDetails = (UserDetails) new User(loginUser.getNamaUser(), loginUser.getKataSandi(),Arrays.asList(authority));
		detailsChecker.check(userDetails);
		return (User) userDetails;
	}
}