package lv.announce.spammer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class SpammerConfig {
    @Bean
    fun restTemplate() = RestTemplate()
}