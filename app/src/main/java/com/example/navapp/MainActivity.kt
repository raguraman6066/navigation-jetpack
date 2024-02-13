package com.example.navapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.navapp.ui.theme.NavAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayNav()
                }
            }
        }
    }
}

@Composable
fun DisplayNav(){
 //nav controller
    var navController= rememberNavController()
    //nav host
    NavHost(navController = navController, startDestination = Screens.FirstScreen.toString()){
        composable(Screens.FirstScreen.toString()){
            FirstScreen(navController)
        }
        composable(Screens.SecondScreen.toString()+"/{username}"){
            val user=it.arguments?.getString("username")
            SecondScreen(user,navController)
        }
        composable("profile/{usercity}", arguments = listOf(navArgument("usercity"){
            type= NavType.StringType
            defaultValue="0"
        })){
            val user=it.arguments?.getString("usercity","tpt")

            ThirdScreen(userCity = user, navController = navController)
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
    Column {
        var userName by remember {
            mutableStateOf("")
        }
        TextField(value = userName, onValueChange = {newUser->userName=newUser})
        Button(onClick = {
            navController.navigate(Screens.SecondScreen.toString()+"/$userName")

        }) {
            Text(text = "Welcome to First Screen")
        }
    }
}

@Composable
fun SecondScreen(user:String?, navController: NavController){
    Column {
        Button(onClick = {
           // navController.navigate(Screens.FirstScreen.toString())
            navController.popBackStack(Screens.SecondScreen.toString(),true)
        }) {
            Text(text = "Welcome $user to Second Screen")
        }
        Button(onClick = {
            navController.navigate("profile/chennai")
        }) {
            Text(text = "Go to Third Screen")
        }
    }
}

@Composable
fun ThirdScreen(userCity:String?,navController: NavController){
    Column {
        Text(text = "This is thrid screen")
    }
}