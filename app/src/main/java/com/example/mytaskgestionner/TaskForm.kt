package com.example.mytaskgestionner

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import java.util.Date
import kotlin.random.Random

@Composable
fun TaskFormInput(
    value: MutableState<String>,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value.value,
        label = {
            Text(text = label)
        },
        onValueChange = { value.value = it },
        modifier = modifier
            .fillMaxWidth(0.9f)
            .padding(8.dp)
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskForm(modifier: Modifier, navController: NavController, task: Tache? ){
    val isEditing = task != null

    val taskLibelle = remember { mutableStateOf(task?.libelle ?: "") }
    val taskDescription = remember { mutableStateOf(task?.description ?: "") }
    val taskType = remember { mutableStateOf(task?.type ?: "") }
    val taskDateRendu = remember { mutableStateOf(task?.dateRendu ?: "") }
    val isDone = remember { mutableStateOf(task?.faite ?: false) }

    Scaffold(
        modifier = modifier
    ) {
        Column {
            TaskFormInput(taskLibelle, "Libellé")
            TaskFormInput(taskDescription, "Description")
            TaskFormInput(taskType, "Type")
            TaskFormInput(taskDateRendu, "Date de rendu")

            Button(onClick = {
                if (isEditing) {
                    val updatedTask = task!!.copy(
                        libelle = taskLibelle.value,
                        description = taskDescription.value,
                        type = taskType.value,
                        dateRendu = taskDateRendu.value,
                        faite = task.faite,
                        dateMiseAJour = Date()
                    )
                    Tasks.updateTask(updatedTask)
                } else {
                    Tasks.addTask(Tache(
                        id = Random.nextInt(),
                        libelle = taskLibelle.value,
                        description = taskDescription.value,
                        type = taskType.value,
                        faite = mutableStateOf( false),
                        dateCreation = Date(),
                        dateMiseAJour = Date(),
                        dateRendu = taskDateRendu.value
                    ))
                }
                navController.popBackStack()
            }) {
                Text(if (isEditing) "Modifier la tâche" else "Ajouter la tâche")
            }
        }
    }
}
