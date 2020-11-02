package love.mmjj.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author YuJian
 * @description
 * @since 2020/10/23
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 配置用户信息服务
//     *
//     * @return UserDetailsService
//     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("yujian").password("$2a$10$y.4n.gIOIqAZIbUyLj5mpeUxfjmkm2fM65qCBT1DcCYWw6gu216zK").authorities("p1").build());
//        manager.createUser(User.withUsername("cmm").password("$2a$10$y.4n.gIOIqAZIbUyLj5mpeUxfjmkm2fM65qCBT1DcCYWw6gu216zK").authorities("p2").build());
//        return manager;
//    }

    /**
     * 密码编码器
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     *
     * @param http http请求
     * @throws Exception 错误
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable() csrf跨站请求
                .authorizeRequests()
                //.antMatchers("/resource/resource1").hasAuthority("p1") 基于url的鉴权
                //.antMatchers("/resource/resource2").hasAuthority("p2")
                .antMatchers("/resource/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                //.loginPage("") 登录页面
                //.loginProcessingUrl("") 处理登录的url
                .successForwardUrl("/login/success")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        // 访问/login?logout直接退出登录
    }
}
