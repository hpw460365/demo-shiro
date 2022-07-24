package com.example.shiro.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * 创建INI安全数据域
     * @return
     */
//    @Bean
    public Realm creatRealm(){
        IniRealm realm = new IniRealm("classpath:auth.ini");
        realm.setCredentialsMatcher(new PasswordMatcher());
        return  realm;
    }

    /**
     * 创建JDBC安全数据域
     */
//    @Bean     //声明其为Bean实例
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl("jdbc:mysql://localhost:3306/new_schema");
        datasource.setUsername("root");
        datasource.setPassword("root");
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return datasource;
    }

    @Bean
    public Realm creatJdbcRealm(DataSource dataSource){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        HashedCredentialsMatcher cm = new HashedCredentialsMatcher();
        cm.setHashAlgorithmName("MD5");
        cm.setHashIterations(1);
        jdbcRealm.setCredentialsMatcher(cm);
        jdbcRealm.setSaltStyle(JdbcRealm.SaltStyle.COLUMN);
        return jdbcRealm;
    }


    /**
     * 配置session共享Manager
     */
    @Bean
    public SessionManager createSessionManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");

        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager);

        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        return sessionManager;
    }



    /**
     * 传教概念安全管理器
     */
    @Bean
    public DefaultWebSecurityManager creatDefaultWebSecurityManager(Realm realm, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }




    /**
     * 创建shiroFilter
     */

    @Bean
    public ShiroFilterFactoryBean createShiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filter=new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);

        Map<String,String> filterMap=new HashMap<>();

        //无需认证
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
//        filterMap.put("/static/**","anon");

        //需认证
        filterMap.put("/**","authc");

        //未认证
//        filter.setLoginUrl("/denglu.html");



        //角色授权
        filterMap.put("/register/re","roles[delete]");
        //未授权
        filter.setUnauthorizedUrl("/register");


        filter.setFilterChainDefinitionMap(filterMap);

        return filter;
    }


}
