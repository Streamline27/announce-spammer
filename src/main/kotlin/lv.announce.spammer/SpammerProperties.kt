package lv.announce.spammer

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class SpammerProperties(
    @Value("\${spring.application.name}") val applicationName: String,
    @Value("\${spammer.worker-notification-url}") val workerNotificationUrl: String
)