package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel :ViewModel() {

    //definirame val koito sadurja steita na receptite
    private val _categorieState = mutableStateOf(RecipeState())
    //public variable that we will use
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }



    //function that fetches the categories
    //we do the try and catch when we fetch data from the internet for many reasons
    //internet could not be working the website could be dead , the api could be changed
    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val response = recipeService.getCategories()
                _categorieState.value = categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null

                )

            }catch (e : Exception){
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }



    //dataclass to take care of the state do we have a list or not
    data class RecipeState (
        val loading: Boolean =true,
        //we define it as an empty string in the begining
        val list: List<Category> = emptyList(),
        val error:String? = null

    )


}