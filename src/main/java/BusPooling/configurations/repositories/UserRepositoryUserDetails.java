package BusPooling.configurations.repositories;


import BusPooling.configurations.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserRepositoryUserDetails extends User implements UserDetails {

		private static final long serialVersionUID = 1L;

		public UserRepositoryUserDetails(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getRoles();
		}

		@Override
		public String getUsername() {
			return super.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}