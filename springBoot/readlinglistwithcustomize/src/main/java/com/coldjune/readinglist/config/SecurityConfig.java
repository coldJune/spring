package com.coldjune.readinglist.config;

import com.coldjune.readinglist.service.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@ComponentScan("com.coldjune.readinglist")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")//'/'的请求只有经过身份验证的且拥有READER权限的才能访问
                .antMatchers("/**").permitAll()//其它请求路径向所有用户开放了访问权限
                .and()
                .formLogin()
                .loginPage("/login")//登陆页
                .failureUrl("/login?error=true");//登陆失败
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用于查找指定用户名的用户
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                 UserDetails userDetails = readerRepository.getOne(username);
                 if(userDetails == null){
                     throw  new RuntimeException("have no user");
                 }
                 return userDetails;
            }
        });
    }
}
