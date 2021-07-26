package com.gorlah.game.api

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotBlank

@ConstructorBinding
@ConfigurationProperties("games-api")
data class GameApiProperties(
    val igdb: IgdbProperties,
    val twitch: TwitchProperties,
) {

    data class IgdbProperties(

        @field:NotBlank
        var webhookSecret: String?,

        @field:NotBlank
        var webhookCallbackUrl: String?,
    )

    data class TwitchProperties(

        @field:NotBlank
        var clientId: String?,

        @field:NotBlank
        var clientSecret: String?,
    )
}