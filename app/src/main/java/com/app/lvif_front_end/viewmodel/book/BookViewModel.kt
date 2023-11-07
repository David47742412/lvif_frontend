package com.app.lvif_front_end.viewmodel.book

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(

) : ViewModel() {
    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    private val _description: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = _description

    private val _image: MutableLiveData<ImageBitmap> = MutableLiveData()
    val image: LiveData<ImageBitmap> = _image

    fun setValueProps(
        name: String = _name.value ?: "",
        description: String = _description.value ?: "",
        image: ImageBitmap = _image.value ?: ImageBitmap(1, 1)
    ) {
        _name.value = name
        _description.value = description
        _image.value = image

    }

}