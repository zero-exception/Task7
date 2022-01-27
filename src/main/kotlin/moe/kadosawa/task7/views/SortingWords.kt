package moe.kadosawa.task7.views

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.BorderPane
import tornadofx.*

class SortingWords : View("Сортировка слов в строке") {
    override val root: BorderPane by fxml("/views/SortingWords.fxml")

    private val output = SimpleStringProperty("")

    private val inputField: TextField by fxid()
    private val outputField: TextField by fxid()

    private val toggleGroup = ToggleGroup()
    private val linearRadioBtn: RadioButton by fxid()
    private val reversedRadioBtn: RadioButton by fxid()

    private fun isReverse() = toggleGroup.selectedToggle == reversedRadioBtn

    init {
        outputField.bind(output)
        linearRadioBtn.toggleGroup = toggleGroup
        reversedRadioBtn.toggleGroup = toggleGroup
    }

    fun onSortClick() {
        val sortedInput = inputField.text
            .split("\\s+".toRegex())
            .sorted()
            .let { if (isReverse()) it.reversed() else it }
            .joinToString(" ")

        output.set(sortedInput)
    }
}