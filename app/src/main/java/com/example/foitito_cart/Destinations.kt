package com.example.foitito_cart

interface Destinations {
    val root : String
}

object Home:Destinations{
    override val root = "Home"
}

object Cart:Destinations{
    override val root = "Cart"
}