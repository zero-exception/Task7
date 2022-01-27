package moe.kadosawa.task7.views

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*
import kotlin.system.exitProcess

class StringManipulations : View("Манипуляции со строками") {
    override val root: BorderPane by fxml("/views/StringManipulations.fxml")

    private val output = SimpleStringProperty("")

    private val inputField: TextField by fxid()
    private val outputField: TextField by fxid()

    init {
        outputField.bind(output)
    }

    fun onCountClick() {
        val wordCount = inputField.text.trim().split(Regex("\\s+")).size
        output.set("Количество слов: $wordCount")
    }

    fun onSortClick() {
        val sortedInput = inputField.text.trim().split(Regex("\\s+")).sorted().joinToString(" ")
        output.set(sortedInput)
    }

    fun onExitClick() {
        exitProcess(0)
    }
}
