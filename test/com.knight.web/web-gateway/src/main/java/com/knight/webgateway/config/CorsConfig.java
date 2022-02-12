package com.knight.webgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 *
 * @author TortoiseKnightB
 * @date 2021/12/27
 */
@Configuration
public class CorsConfig {

//    注意拦截器的加载顺序，请求会先进入到自定义拦截器中
//    重写WebMvcConfigurer接口的addCorsMappings方法行不通
//    CrossOrigin也是拦截器在自定义拦截器之后。所以在自定义拦截器失败后，处理跨域的拦截器未处理，造成跨域失败

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // ”/**“ 的意思是所有文件夹及里面的子文件夹，配置对所有接口都有效
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }


    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域的域名，可以用*表示允许任何域名使用
//        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8090", "http://localhost:8091"));
        //允许请求头
        corsConfiguration.setAllowedHeaders(Arrays.asList("Content-Type"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
        //带上cookie信息
        corsConfiguration.setAllowCredentials(true);
        //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }
}
