package com.app.lvif_front_end.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.lvif_front_end.room.entity.UserEntity
import com.app.lvif_front_end.room.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val _service: UserService
) : ViewModel() {
    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> = _users

    fun getAllUser(onErr: () -> Unit) {
        _service.getAllUser().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<UserEntity>>() {
                override fun onSuccess(result: List<UserEntity>) {
                    _users.value = result
                }

                override fun onError(ex: Throwable) {
                    onErr()
                }
            })
    }
}