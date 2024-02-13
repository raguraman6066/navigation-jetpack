package com.example.navapp

sealed class Screens(val route:String) {
    object FirstScreen: Screens("First Screen")
    object SecondScreen: Screens("Second Screen")
}