package lv.announce.spammer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SpammerController(private val properties: SpammerProperties) {

    @GetMapping("/hello")
    fun hello() = "Hello from service: " + properties.applicationName
}