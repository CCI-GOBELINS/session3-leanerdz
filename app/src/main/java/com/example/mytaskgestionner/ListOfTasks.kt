package com.example.mytaskgestionner

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.TaskStackBuilder
import androidx.navigation.NavController
import com.example.mytaskgestionner.Tache
import java.util.Date
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(modifier: Modifier, navController: NavController,){
    val taskList = Tasks.taskList
    Scaffold {
        Column {
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    navController.navigate("addTask")
                }) {
                Text(text = "+")
            }
            LazyColumn {
                items(taskList.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                navController.navigate("detail/${taskList[index].id}")
                            }
                    ){
                        Row {
                            Checkbox(
                                checked = taskList[index].faite.value,
                                onCheckedChange = {
                                    taskList[index].dateMiseAJour = Date()
                                    taskList[index].faite.value = it
                                }
                            )
                            Text(text = taskList[index].libelle)
                            //Text(text = taskList[index].description)
                            //taskList[index]
                        }

                    }

                }

            }
        }

    }

}