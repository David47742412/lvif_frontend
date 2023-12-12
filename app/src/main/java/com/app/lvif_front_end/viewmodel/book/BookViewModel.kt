package com.app.lvif_front_end.viewmodel.book

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.app.lvif_front_end.viewmodel.splash.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(

) : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    private val _description: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = _description

    fun setName(name: String) {
        this._name.value = name
    }

    fun setDescription(description: String) {
        this._description.value = description
    }

    fun save(
        bookId: String,
        action: Int,
        mainViewModel: MainViewModel,
        navController: NavController,
        name: String,
        description: String,
    ) {
        val obj = mapOf(
            "bookId" to bookId,
            "action" to action,
            "name" to name,
            "description" to description
        )
        val objJson = Gson().toJson(obj)
        mainViewModel.onSendMessage(objJson)
        navController.navigate("home")
    }

    fun delete(
        bookId: String,
        mainViewModel: MainViewModel
    ) {
        val obj = mapOf(
            "bookId" to bookId,
            "action" to 3,
        )

        val objJson = Gson().toJson(obj);

        mainViewModel.onSendMessage(objJson)
    }

}