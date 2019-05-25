package lv.announce.spammer

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
class SpammerScheduler(
        private val properties: SpammerProperties,
        private val restTemplate: RestTemplate
) {
    private val log = LoggerFactory.getLogger(SpammerScheduler::class.java)

    @Scheduled(initialDelay = 2000, fixedDelayString = "\${spammer.message-interval-millise}")
    fun spam() {
        val message = UUID.randomUUID().toString()
        try {
            val result = restTemplate.postForEntity(properties.workerNotificationUrl, message, NotificationResult::class.java)
            if (result.statusCode.is2xxSuccessful) {
                val receiver = result.body?.serviceName ?: "NULL"
                log.info("Sent message:[$message]. Receiver instance:[$receiver]")
                return
            }
            log.info("Could not sent message:[$message]. Response code:[${result.statusCodeValue}]")
        } catch (e: RestClientException) {
            log.info("Could not sent message:[$message]. Rest client exception.", e)
        }
    }

    data class NotificationResult(
            val message: String,
            val serviceName: String
    )
}