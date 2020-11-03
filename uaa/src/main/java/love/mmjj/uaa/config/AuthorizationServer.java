package love.mmjj.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * @author YuJian
 * @description 授权服务
 * @since 2020/11/3
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer implements AuthorizationServerConfigurer {
    private final AuthorizationServerTokenServices authorizationServerTokenServices;
    private final AuthorizationCodeServices authorizationCodeServices;
    private final AuthenticationManager authenticationManager;

    public AuthorizationServer(
            AuthorizationServerTokenServices authorizationServerTokenServices,
            AuthorizationCodeServices authorizationCodeServices,
            AuthenticationManager authenticationManager) {
        this.authorizationServerTokenServices = authorizationServerTokenServices;
        this.authorizationCodeServices = authorizationCodeServices;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 配置令牌端点的安全约束
     *
     * @param securityConfigurer AuthorizationServerSecurityConfigurer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) {
        securityConfigurer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                // 允许表单申请令牌
                .allowFormAuthenticationForClients();
    }

    /**
     * 客户端详情服务，用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里初始化
     *
     * @param clientDetailsServiceConfigurer ClientDetailsServiceConfigurer
     * @throws Exception 错误
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer.inMemory()
                // client_id
                .withClient("client1")
                // 客户端秘钥
                .secret(new BCryptPasswordEncoder().encode("123456"))
                // 资源列表
                .resourceIds("resource1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                // 允许的授权范围
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");
    }

    /**
     * 用来配置令牌的访问端点和令牌服务
     *
     * @param endpointsConfigurer AuthorizationServerEndpointsConfigurer
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) {
        endpointsConfigurer
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理服务
                .tokenServices(authorizationServerTokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
