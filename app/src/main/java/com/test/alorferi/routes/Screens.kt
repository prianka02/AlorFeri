package com.test.alorferi.routes

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object LoginScreen: Screens()

    @Serializable
    data object HomeScreen: Screens()

    @Serializable
    data object ProfileScreen: Screens()
}