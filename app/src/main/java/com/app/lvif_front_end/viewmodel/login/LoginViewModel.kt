package com.app.lvif_front_end.viewmodel.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.app.lvif_front_end.models.user.UserModel
import com.app.lvif_front_end.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val _useCase: LoginUseCase,
    @ApplicationContext private val _context: Context
) : ViewModel() {

    //private val _userModel = MutableLiveData<UserModel>()
    //val userModel: LiveData<UserModel> = _userModel

    fun login(
        username: String,
        password: String,
        navController: NavHostController
    ) {
        _useCase.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<UserModel> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(user: UserModel) {
                    navController.navigate("home")
                }

                override fun onError(e: Throwable) {
                    Log.e("error-login", e.message.toString())
                    Toast.makeText(_context, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            })
    }
}