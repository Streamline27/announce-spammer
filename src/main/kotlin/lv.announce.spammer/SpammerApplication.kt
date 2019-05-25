package lv.announce.spammer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpammerApplication

fun main(args: Array<String>) {
	runApplication<SpammerApplication>(*args)
}
