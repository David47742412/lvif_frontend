package com.app.lvif_front_end.viewmodel.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.lvif_front_end.models.book.BookModel
import com.app.lvif_front_end.room.entity.UserEntity
import com.app.lvif_front_end.room.service.UserService
import com.app.lvif_front_end.usecase.book.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Response
import okhttp3.WebSocket
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val _service: UserService,
    @ApplicationContext private val _context: Context
) : ViewModel() {

    private val _bookUseCase = BookUseCase()

    private val _currentUser: MutableLiveData<UserEntity> = MutableLiveData()
    val currentUser: LiveData<UserEntity> = _currentUser

    private val _books: MutableLiveData<List<BookModel>> = MutableLiveData()
    val books: LiveData<List<BookModel>> = _books

    private var ws: WebSocket? = null

    init {
        getUser()
    }

    private fun getUser() {
        this._service.getAllUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<List<UserEntity>>() {
                override fun onSuccess(result: List<UserEntity>) {
                    val tempUser = result[0]
                    _currentUser.value = tempUser
                    ws = _bookUseCase.onInit(
                        "book",
                        tempUser.token,
                        onOpenWs = { ws, response -> onOpenWs(ws, response) },
                        onErrorWs = { ws, t, response -> onErrorWs(ws, t, response) },
                        onMessageWs = { ws, text -> onMessageWs(ws, text) }
                    )
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun onOpenWs(ws: WebSocket, response: Response) {
        Log.i("connect-ws-success", "ws-connected-success ${response.code}")
    }

    fun onErrorWs(ws: WebSocket, t: Throwable, response: Response?) {
        Log.i("connect-ws-err", "ws-connected-errr ${ws.request().header("Authorization")}")
    }

    fun onMessageWs(ws: WebSocket, text: String) {
        Log.i("connect-ws-msg", "ws-connected-msg $text")
    }

}