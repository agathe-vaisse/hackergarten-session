package net.hackergarten.sessionapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebMvcConfigurer {

    @Bean
    @Throws(Exception::class)
    fun userDetailsService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()
        manager.createUser(
                User.withUsername("user")
                        .password("{bcrypt}$2a$10$4TBro/KP6ci56On9ZMScPeMgteXG4vCE8UyWuAoLY.Oz8gJGh55XO")
                        .roles("LEAD")
                        .build())
        return manager
    }

}