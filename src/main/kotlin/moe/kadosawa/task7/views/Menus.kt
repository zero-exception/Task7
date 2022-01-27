package moe.kadosawa.task7.views

import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyEvent
import javafx.scene.layout.*
import javafx.scene.text.Text
import tornadofx.*

class Menus : View("Работа с меню") {
    override val root: BorderPane by fxml("/views/Menus.fxml")

    private val menuItemSort: MenuItem by fxid()
    private val menuItemCharCount: MenuItem by fxid()

    private val source = SimpleStringProperty("")
    private val fieldSource: TextField by fxid()

    private val result = SimpleStringProperty("")
    private val fieldResult: TextField by fxid()

    private val sortToggleGroup = ToggleGroup()
    private val radioSortDescending: RadioButton by fxid()
    private val radioSortAscending: RadioButton by fxid()

    private val convertToggleGroup = ToggleGroup()
    private val radioConvertFull: RadioButton by fxid()
    private val radioConvertWords: RadioButton by fxid()

    private val spinnerWordNumber: Spinner<Int> by fxid()

    private var info = SimpleStringProperty("...")
    private val textInfo: Text by fxid()

    init {
        fieldSource.bind(source)
        fieldSource.onKeyPressed = EventHandler(this::onFieldKeyPressed)

        fieldResult.bind(result)

        radioSortDescending.toggleGroup = sortToggleGroup
        radioSortAscending.toggleGroup = sortToggleGroup
        radioSortDescending.isSelected = true

        radioConvertFull.toggleGroup = convertToggleGroup
        radioConvertWords.toggleGroup = convertToggleGroup
        radioConvertFull.isSelected = true

        spinnerWordNumber.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1)

        textInfo.bind(info)

        menuItemSort.action(this::onSortClick)
        menuItemCharCount.action(this::onCharCountClick)
    }

    private fun <T : Comparable<T>> sortOrder() =
        if (sortToggleGroup.selectedToggle == radioSortDescending) reverseOrder<T>() else naturalOrder()

    private fun onFieldKeyPressed(e: KeyEvent) {
        val wordCount = source.get().trim().split(Regex("\\s+")).size

        if (wordCount <= 0) {
            spinnerWordNumber.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1)
        } else {
            spinnerWordNumber.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, wordCount)
        }
    }

    private fun onSortClick() {
        val isEachWord = convertToggleGroup.selectedToggle == radioConvertWords

        val sorted = source.get().split("\\s+".toRegex())
            .let { s ->
                if (isEachWord) {
                    s.joinToString(" ") { it.toList().sortedWith(sortOrder()).joinToString("") }
                } else {
                    s.sortedWith(sortOrder()).joinToString(" ")
                }
            }

        result.set(sorted)
    }

    private fun onCharCountClick() {
        val idx = spinnerWordNumber.value - 1
        val selectedWord = source.get().split("\\s+".toRegex())[idx]
        val count = selectedWord.count()
        info.set("$count символов")
    }
}