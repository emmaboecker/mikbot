plugins {
    `mikbot-module`
    `mikbot-plugin`
    `mikbot-publishing`
}

group = "dev.schlaubi.mikbot"
version = "2.6.0"

dependencies {
    plugin(projects.game.gameApi)
}

mikbotPlugin {
    bundle.set("multiple_choice")
}
