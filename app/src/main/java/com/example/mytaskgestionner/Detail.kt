package com.example.mytaskgestionner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, id: Int, modifier: Modifier = Modifier, task: Tache?){
    val tache = task
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column {
            Button(
                onClick = {
                    navController.navigate("list")
                }
            ) {
                Text(text = "Retour")
            }
            Text(
                text = "${tache?.libelle}",
                modifier = Modifier.padding(innerPadding)
            )
            Text(
                text = "Description : ${tache?.description}",
                modifier = Modifier.padding(innerPadding)
            )
            Text(
                text = "Date de création :${tache?.dateCreation}",
                modifier = Modifier.padding(innerPadding)
            )
            Text(
                text = "Date de mise a jour :${tache?.dateMiseAJour}",
                modifier = Modifier.padding(innerPadding)
            )
            Text(
                text = "Date de rendu :${tache?.dateRendu}",
                modifier = Modifier.padding(innerPadding)
            )
            Button(
                onClick = {
                    navController.navigate("edit/${tache?.id}")
                }
            ) {
                Text(text = "Modifier")
            }
            Button(
                onClick = {
                    Tasks.removeTask(tache)
                    navController.navigate("list")
                }
            ) {
                Text(text = "Supprimer")
            }

        }


    }
}