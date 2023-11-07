package com.app.lvif_front_end.viewmodel.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.lvif_front_end.models.book.BookModel
import com.app.lvif_front_end.models.template.ResponseApi
import com.app.lvif_front_end.room.entity.UserEntity
import com.app.lvif_front_end.room.service.UserService
import com.app.lvif_front_end.usecase.book.BookUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Response
import okhttp3.WebSocket
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val _service: UserService
) : ViewModel() {
    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> = _users

    private val _bookUseCase = BookUseCase()
    private val _gson = Gson()

    private val _currentUser: MutableLiveData<UserEntity> = MutableLiveData()
    val currentUser: LiveData<UserEntity> = _currentUser

    private val _books: MutableLiveData<List<BookModel>> = MutableLiveData(emptyList())
    val books: LiveData<List<BookModel>> = _books

    private var ws: WebSocket? = null

    init {
        getUser()
    }

    fun getUser() {
        this._service.getAllUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<List<UserEntity>>() {
                override fun onSuccess(result: List<UserEntity>) {
                    if (result.isNotEmpty()) {
                        val tempUser = result[0]
                        _users.value = result
                        _currentUser.postValue(tempUser)
                        ws = _bookUseCase.onInit(
                            "book",
                            tempUser.token,
                            onOpenWs = { ws, res -> onOpenWs(ws, res) },
                            onErrorWs = { ws, t, res -> onErrorWs(ws, t, res) },
                            onMessageWs = { ws, text -> onMessageWs(ws, text) },
                        )
                        return;
                    }
                    _users.value = emptyList();
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun onOpenWs(ws: WebSocket, response: Response) {
        Log.i("connect-ws-success", "ws-connected-success ${response.code}")
    }

    fun onErrorWs(ws: WebSocket, t: Throwable, res: Response?) {
        Log.i("connect-ws-err", "ws-connected-errr ${ws.request().header("Authorization")}")
    }

    fun onMessageWs(ws: WebSocket, text: String) {
        val type = object : TypeToken<ResponseApi<BookModel>>() {}.type
        val resJson = _gson.fromJson<ResponseApi<BookModel>>(text, type)

        _books.postValue(resJson.body)

        Log.i("connect-ws-msg", "ws-connected-msg ${_books.value?.size}")
    }
}