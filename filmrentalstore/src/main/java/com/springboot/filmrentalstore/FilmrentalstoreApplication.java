package com.springboot.filmrentalstore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.springboot.filmrentalstore.filter.JwtFilter;
import com.springboot.filmrentalstore.service.CustomUserDetailsService;

@SpringBootApplication
public class FilmrentalstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(FilmrentalstoreApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@DependsOn("userDetailsService")
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		CustomUserDetailsService service = userDetailsService();

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public CustomUserDetailsService userDetailsService() {

		return new CustomUserDetailsService(); // Implement your own user details service
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Use a password encoder of your choice
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("invoked");
		http.csrf().disable() // Disable CSRF protection
				.authorizeRequests()
				.requestMatchers("/api/auth").permitAll()
				.requestMatchers("/api/user/register").permitAll()
				.requestMatchers(HttpMethod.POST, "/api/actors/post").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/actors/lastname/{ln}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/actors/firstname/{fn}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/actors/{actorId}/film").hasAnyRole("STAFF", "ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/actors/{actorId}/films").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/actors/toptenbyfilmcount").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/actors/update/").hasAnyRole("STAFF", "ADMIN")

				.requestMatchers(HttpMethod.POST, "/api/customers/post").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/lastname/{lastName}").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/firstname/{firstName}").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/email/{email}").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/customers/{customerId}/address/{addressId}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/city/{city}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/country/{country}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/active").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/inactive").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/customers/phone/{phone}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/customers/update/{customerId}/**").hasAnyRole("STAFF")

				.requestMatchers(HttpMethod.POST, "/api/staff/create").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/lastname/{lastName}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/firstname/{firstName}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/email/{email}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/city/{city}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/country/{country}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/staff/phone/{phone}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/update/firstName/{id}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/update/lastName/{id}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/update/email/{id}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/update/phone/{id}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/update/address/{staffId}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/staff/{id}/update-store").hasAnyRole("ADMIN")


				.requestMatchers(HttpMethod.POST, "/api/stores/add").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/stores/{storeId}/address/{addressId}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stores/city/{city}").hasAnyRole("USER", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/stores/country/{country}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/stores/phone/{phone}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/stores/update/{storeId}/{phone}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/stores/{storeId}/manager/{managerStaffId}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stores/staff/{storeId}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stores/customer/{storeId}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/stores/manager/{storeId}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/stores/managers").hasAnyRole("ADMIN")

				.requestMatchers(HttpMethod.POST, "/api/inventory/add").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/inventory/films").hasAnyRole("ADMIN", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/inventory/store/{id}").hasAnyRole("USER", "STAFF")
				.requestMatchers(HttpMethod.GET, "/api/inventory/film/{id}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/inventory/film/{filmId}/store/{storeId}").hasAnyRole("USER", "STAFF")

				.requestMatchers(HttpMethod.POST, "/api/rentals/add").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/rentals/customer/{id}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/rentals/toptenfilms").hasAnyRole("STAFF", "ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/rentals/toptenfilms/store/{id}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/rentals/due/store/{id}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.PUT, "/api/rentals/update/returndate/{id}").hasAnyRole("STAFF")


				.requestMatchers(HttpMethod.POST, "/api/payment/add").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/payment/revenue/datewise").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/payment/revenue/datewise/store/{id}").hasAnyRole("STAFF","ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/payment/revenue/filmwise").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/payment/revenue/film/{id}").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/api/payment/revenue/films/store/{id}").hasAnyRole("ADMIN")

				.requestMatchers(HttpMethod.POST, "/api/films/post").hasAnyRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/api/films/**").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/title/{title}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/year/{year}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/duration/gt/{rd}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/rate/gt/{rate}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/length/gt/{length}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/betweenyear/{from}/{to}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/rating/lt/{rating}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/rating/gt/{rating}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/language/{lang}").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/countbyyear").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/{id}/actors").hasAnyRole("STAFF")
				.requestMatchers(HttpMethod.GET, "/api/films/category/{category}").hasAnyRole("STAFF")

				.requestMatchers(HttpMethod.PUT, "/api/films/update/**").hasAnyRole("ADMIN", "STAFF")

				.anyRequest().authenticated().and()
				.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().sessionManagement().disable()

				.authenticationManager(new ProviderManager(daoAuthenticationProvider()));

		return http.build();
	}
}
