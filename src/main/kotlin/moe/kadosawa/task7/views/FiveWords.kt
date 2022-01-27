package moe.kadosawa.task7.views

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import tornadofx.*

class FiveWords : View("Пять слов") {
    private val output = SimpleStringProperty()

    private var inputField: TextField by singleAssign()
    private var outputField: TextField by singleAssign()

    private val firstWord = SimpleBooleanProperty(true)
    private val secondWord = SimpleBooleanProperty()
    private val thirdWord = SimpleBooleanProperty()
    private val fourthWord = SimpleBooleanProperty()
    private val fifthWord = SimpleBooleanProperty()

    override val root = borderpane {
        minHeight = 130.0
        minWidth = 400.0
        paddingAll = 8

        top {
            inputField = textfield()
        }

        left {
            gridpane {
                vgap = 4.0

                borderpaneConstraints {
                    marginTop = 8.0
                    marginBottom = 8.0
                }

                row {
                    checkbox("1 слово", firstWord)
                }
                row {
                    checkbox("2 слово", secondWord)
                }
                row {
                    checkbox("3 слово", thirdWord)
                }
                row {
                    checkbox("4 слово", fourthWord)
                }
                row {
                    checkbox("5 слово", fifthWord)
                }
            }
        }

        right {
            button("Слова") {
                borderpaneConstraints {
                    marginTop = 8.0
                    marginBottom = 8.0
                }

                action {
                    val words = inputField.text.trim().split("\\s+".toRegex())
                    if (words.size != 5) {
                        alert(Alert.AlertType.ERROR, "Вы должны указать ровно 5 слов!")
                        return@action
                    }

                    var result = ""
                    if (firstWord.get()) {
                        result += words[0] + " "
                    }
                    if (secondWord.get()) {
                        result += words[1] + " "
                    }
                    if (thirdWord.get()) {
                        result += words[2] + " "
                    }
                    if (fourthWord.get()) {
                        result += words[3] + " "
                    }
                    if (fifthWord.get()) {
                        result += words[4] + " "
                    }

                    output.set(result)
                }
            }
        }

        bottom {
            borderpaneConstraints {
                marginTop = 8.0
            }
            outputField = textfield(output) {
                isEditable = false
            }
        }
    }
}
