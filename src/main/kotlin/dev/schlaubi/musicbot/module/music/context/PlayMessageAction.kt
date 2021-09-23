package dev.schlaubi.musicbot.module.music.context

import com.kotlindiscord.kord.extensions.commands.application.message.EphemeralMessageCommandContext
import com.kotlindiscord.kord.extensions.extensions.ephemeralMessageCommand
import com.kotlindiscord.kord.extensions.interactions.editingPaginator
import com.kotlindiscord.kord.extensions.interactions.respond
import dev.schlaubi.musicbot.module.music.MusicModule
import dev.schlaubi.musicbot.module.music.player.MusicPlayer
import dev.schlaubi.musicbot.module.music.player.queue.QueueOptions
import dev.schlaubi.musicbot.module.music.player.queue.queueTracks

suspend fun MusicModule.playMessageAction() = ephemeralMessageCommand {
    name = "Play as track"

    action {
        val arguments = PlayMessageActionArguments(event.interaction.messages!!.values.first().content)

        queue(arguments, musicPlayer)
    }
}

class PlayMessageActionArguments(override val query: String) : QueueOptions {
    override val force: Boolean = false
    override val top: Boolean = false
    override val soundcloud: Boolean = false
}

private suspend fun EphemeralMessageCommandContext.queue(
    arguments: PlayMessageActionArguments,
    musicPlayer: MusicPlayer
) = queueTracks(musicPlayer, true, arguments, { respond { it() } }) {
    editingPaginator { it() }
}