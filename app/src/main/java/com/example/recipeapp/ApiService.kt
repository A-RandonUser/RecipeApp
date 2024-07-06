package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//create a retrofitobject and bi
//the retrofit builder prepares the end point and adds the gson converter
//it provides create method for gaining acesss to service methods like get categories

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

    val recipeService = retrofit.create(ApiService::class.java)

interface ApiService{
    //endpoint of the url
    //suspend keyword its part of koroutine
    //run the slow executions in the background not in the user interface
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse

}