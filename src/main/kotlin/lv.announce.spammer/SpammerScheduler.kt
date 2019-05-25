package lv.announce.spammer

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
class SpammerScheduler(
        private val properties: SpammerProperties,
        private val restTemplate: RestTemplate
) {
    private val log = LoggerFactory.getLogger(SpammerScheduler::class.java)

    @Scheduled(initialDelay = 5000, fixedDelay = 20)
    fun spam() {
        val message = UUID.randomUUID().toString()
        val result = restTemplate.postForEntity(properties.workerNotificationUrl, "", NotificationResult::class.java)
        if (result.statusCode.is2xxSuccessful) {
            val receiver = result.body?.serviceName ?: "NULL"
            log.info("Sent message:[$message]. Receiver instance:[$receiver]")
        }
        else log.info("Sent message:[$message]. Response code:[${result.statusCodeValue}]")
    }

    data class NotificationResult(
            val message: String,
            val serviceName: String
    )
}