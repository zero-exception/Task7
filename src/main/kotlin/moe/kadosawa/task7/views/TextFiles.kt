package moe.kadosawa.task7.views

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class TextFiles : View("Работа с текстовыми файлами") {
    override val root: GridPane by fxml("/views/TextFiles.fxml")

    private val extensionFilter = FileChooser.ExtensionFilter("Text files", "*.txt")

    private val selectedFilePath = SimpleStringProperty()
    private val selectedFilePathField: TextField by fxid()
    private var selectedFile: File? by Delegates.observable(null) { _, _, new ->
        selectedFilePath.set(new?.absolutePath)
    }

    private val fileContents = SimpleStringProperty()
    private val fileContentsArea: TextArea by fxid()

    private val lineSpinner: Spinner<Int> by fxid()

    private val lineEditing = SimpleStringProperty()
    private val lineEditingField: TextField by fxid()

    init {
        selectedFilePathField.bind(selectedFilePath)
        fileContentsArea.bind(fileContents)
        lineEditingField.bind(lineEditing)

        lineSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1)
    }

    fun onChooseFileClick() {
        selectedFile =
            chooseFile("Выбрать файл", arrayOf(extensionFilter), mode = FileChooserMode.Single).singleOrNull()
    }

    fun onReadFileClick() {
        if (selectedFile == null) {
            alert(Alert.AlertType.ERROR, "Ошибка!", "Вы не выбрали файл!")
            return
        }

        fileContents.set(selectedFile?.readText())
        lineSpinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(1, selectedFile!!.readLines().size)
    }

    fun onSelectLine() {
        if (selectedFile == null) {
            alert(Alert.AlertType.ERROR, "Ошибка!", "Вы не выбрали файл!")
            return
        }

        val lineToEdit = lineSpinner.value - 1
        val lines = fileContents.get().lines()

        lineEditing.set(lines[lineToEdit])
    }

    fun onSaveLineClick() {
        if (selectedFile == null) {
            alert(Alert.AlertType.ERROR, "Ошибка!", "Вы не выбрали файл!")
            return
        }

        val lineToUpdate = lineSpinner.value - 1
        val lines = fileContents.get().lines().toMutableList()

        lines[lineToUpdate] = lineEditing.get()
        fileContents.set(lines.joinToString("\n"))
    }

    fun onSaveFileClick() {
        if (selectedFile == null) {
            alert(Alert.AlertType.ERROR, "Ошибка!", "Вы не выбрали файл!")
            return
        }

        selectedFile!!.writeText(fileContents.get()).run {
            information("Успех!", "Изменения созранены.")
        }
    }

    fun onExitClick() {
        System.gc()
        exitProcess(0)
    }
}
