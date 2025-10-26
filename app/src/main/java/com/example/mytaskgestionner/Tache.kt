package com.example.mytaskgestionner

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.util.Date

data class Tache(
    var id: Int,
    var libelle: String,
    var description: String,
    var faite: MutableState<Boolean> = mutableStateOf(false),
    var type: String,
    var dateCreation: Date,
    var dateMiseAJour: Date,
    var dateRendu: String
)

object Tasks {
    val taskList = mutableStateListOf<Tache>()
    fun addTask(task: Tache) {
        taskList.add(task)
    }
    fun removeTask(task: Tache?) {
        taskList.remove(task)
    }
    fun updateTask(task: Tache) {
        taskList.find { it.id == task.id }?.apply {
            libelle = task.libelle
            description = task.description
            faite = task.faite
            dateCreation = task.dateCreation
            dateMiseAJour = task.dateMiseAJour
            dateRendu = task.dateRendu
        }
    }
    fun getAllTasks(): List<Tache> {
        return taskList
    }
    fun findById(id: Int): Tache? {
        return taskList.find { it.id == id }
    }

}
