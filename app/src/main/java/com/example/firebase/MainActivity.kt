package com.example.firebase

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebase.ui.theme.FireBaseTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(db)
                }
            }
        }
    }
}

@Composable
fun App(db : FirebaseFirestore){
    var nome by remember {
        mutableStateOf("")
    }
    var telefone by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

        }
        Row (
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Text(text = "App FireBase Firestore")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){

        }
        Row (
            Modifier
                .fillMaxWidth()
        ){
            Column(
                Modifier
                    .fillMaxWidth(0.3f)
            ){
                Text(text = "Nome:")
            }
            Column(

            ){
                TextField(
                    value = nome,
                    onValueChange = {nome = it }
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
        ){
            Column (
                Modifier
                    .fillMaxWidth(0.3f)
            ){
                TextField(
                    value = telefone,
                    onValueChange = {telefone = it}
                )
            }
        }
        Row (
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Button(onClick = {

                val city = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                db.collection("Clientes").document("PrimeiroCliente")
                    .set(city)
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot Successfully written!") }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writting document", e) }

            }){
                Text(text = "Cadastrar")
            }
    }
    }
}



