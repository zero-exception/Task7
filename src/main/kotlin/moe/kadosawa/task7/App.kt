package moe.kadosawa.task7

import javafx.stage.Stage
import moe.kadosawa.task7.views.FiveWords
import moe.kadosawa.task7.views.Menus
import moe.kadosawa.task7.views.SortingWords
import moe.kadosawa.task7.views.TextFiles
import tornadofx.*

class MyApp : App(Menus::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}
