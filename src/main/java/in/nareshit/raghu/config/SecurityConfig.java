package in.nareshit.raghu.config;

import in.nareshit.raghu.constants.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Authentication Object
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/patient/register", "/patient/save").permitAll()
                .antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/appointment/register", "/appointment/save", "/appointment/all").hasAuthority(UserRoles.ADMIN.name())
                .antMatchers("/appointment/view", "/appointment/viewSlot").hasAuthority(UserRoles.PATIENT.name())
                .antMatchers("/user/login","/login").permitAll()


                .anyRequest().authenticated()

                .and()
                .formLogin()
                //Show Login Page(GET)
                .loginPage("/user/login")
                //Do Login(POST)
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user/setup", true)
                .failureUrl("/user/login?error=true")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/user/login?logout=true")

        ;

    }

}
