package com.test.reactivecomposeapp.ui.student_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.reactivecomposeapp.domain.model.Student
import com.test.reactivecomposeapp.domain.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentViewModel
constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllStudents()
    }

    fun handleIntent(intent: StudentIntent) {
        when (intent) {
            is StudentIntent.InsertNewStudent -> insertNewStudent()
            is StudentIntent.DeleteStudent -> deleteStudent(student = intent.student)
            is StudentIntent.ShowStudent -> showStudent(selectedStudent = intent.selectedStudent)
            is StudentIntent.UpdateStudent -> updateStudent(student = intent.student)
            StudentIntent.ClearStudentDetailInformation -> clearStudentDetailInformation()

            is StudentIntent.OnChangeAge -> onChangeAge(age = intent.age)
            is StudentIntent.OnChangeContact -> onChangeContact(contact = intent.contact)
            is StudentIntent.OnChangeDescription -> onChangeDescription(desc = intent.description)
            is StudentIntent.OnChangeName -> onChangeName(name = intent.name)
        }
    }

    private fun onChangeAge(age: String) {
        _uiState.update {
            it.copy(
                studentDetail = it.studentDetail?.copy(age = age.toInt())
            )
        }
    }

    private fun onChangeContact(contact: String) {
        _uiState.update {
            it.copy(
                studentDetail = it.studentDetail?.copy(contact = contact)
            )
        }
    }

    private fun onChangeDescription(desc: String) {
        _uiState.update {
            it.copy(
                studentDetail = it.studentDetail?.copy(description = desc)
            )
        }
    }

    private fun onChangeName(name: String) {
        _uiState.update {
            it.copy(
                studentDetail = it.studentDetail?.copy(name = name)
            )
        }
    }

    private fun deleteStudent(student: Student) {
        viewModelScope.launch {
            repository.deleteStudent(student = student)
        }
        clearStudentDetailInformation()
    }

    private fun showStudent(selectedStudent: Student) {
        viewModelScope.launch {
            val student = repository.showStudent(id = selectedStudent.id)
            _uiState.update {
                it.copy(studentDetail = student)
            }
        }
    }

    private fun clearStudentDetailInformation() {
        _uiState.update {
            it.copy(studentDetail = null)
        }
    }

    private fun updateStudent(student: Student) {
        viewModelScope.launch {
            repository.updateStudent(student = student)
        }
        clearStudentDetailInformation()
    }

    private fun insertNewStudent() {
        viewModelScope.launch {
            repository.insertStudent(
                student = Student(
                    name = arrayOf("이철수", "이영희").random() + (0..100).random(),
                    age = (0..100).random(),
                    gender = arrayOf("Male", "Female").random(),
                    contact = "010-1234-5678",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "In a nulla id enim commodo ultricies et vitae enim. Nulla sed pulvinar nisi. " +
                            "Pellentesque consectetur auctor facilisis. Proin non augue ac nisi efficitur volutpat. " +
                            "Donec euismod suscipit enim, et pellentesque elit pellentesque sit amet. " +
                            "Aliquam finibus et augue et malesuada. " +
                            "Nunc cursus justo vitae dui posuere, vel sollicitudin sem malesuada. " +
                            "Sed ut purus at odio ultrices ullamcorper eget vel felis. " +
                            "Cras vestibulum risus eu rhoncus luctus."
                )
            )
        }
    }

    private fun getAllStudents() {
        viewModelScope.launch {
            repository.getStudents().collect { students ->
                _uiState.update {
                    it.copy(
                        students = students
                    )
                }
            }
        }
    }
}

data class StudentUiState(
    val students: List<Student> = emptyList(),
    val studentDetail: Student? = null,
)

sealed interface StudentIntent {
    data class ShowStudent(val selectedStudent: Student) : StudentIntent
    object InsertNewStudent : StudentIntent
    data class DeleteStudent(val student: Student) : StudentIntent
    data class UpdateStudent(val student: Student) : StudentIntent
    object ClearStudentDetailInformation : StudentIntent

    data class OnChangeName(val name: String) : StudentIntent
    data class OnChangeAge(val age: String) : StudentIntent
    data class OnChangeContact(val contact: String) : StudentIntent
    data class OnChangeDescription(val description: String) : StudentIntent
}
