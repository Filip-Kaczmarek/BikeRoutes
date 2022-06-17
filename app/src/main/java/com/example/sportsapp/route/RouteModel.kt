package com.example.sportsapp.route

data class RouteModel(
    val routeId: Int = 0,
    val category: String = "easy",
    val pictureUrl: String = "url",
    val name: String = "route name",
    val length: Double = 10.2,
    val description: String = "route description"
)

