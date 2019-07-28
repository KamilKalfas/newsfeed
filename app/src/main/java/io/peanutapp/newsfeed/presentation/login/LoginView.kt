package io.peanutapp.newsfeed.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import io.peanutapp.newsfeed.core.ViewContract
import java.lang.ref.WeakReference
import javax.inject.Inject
import kotlin.properties.Delegates

class LoginView @Inject constructor() : ViewContract<LoginView.State> {
    private lateinit var activityRef: WeakReference<AppCompatActivity>
    private var username = ""
    private var password = ""
    val loginAction = ObservableField<() -> Unit>()

    override fun bind(activity: AppCompatActivity, callback: ViewContract.Callback) {
        activityRef = WeakReference(activity)
        callback.onViewBind()
    }

    override fun unbind() = activityRef.clear()

    override fun changeState(newState: State) {
        state = newState
    }

    private var state by Delegates.observable<State>(
        initialValue = State.Uninitialised,
        onChange = { _, _, newState -> onChange(newState) }
    )

    private fun onChange(newState: State) {
        when (newState) {
            is State.Init -> {
                loginAction.set {
                    if (username.isNotBlank() and password.isNotBlank()) {
                        newState.loginAction.invoke(username, password)
                    } else {
                        state = State.Failure
                    }
                }
            }
            is State.Success -> {
                activityRef.get()?.run {
                    startActivity(newState.intent)
                    finish()
                }
            }
            State.Failure -> {
                activityRef.get()?.run {
                    Toast.makeText(this, "Username and Password can't be empty!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun usernameEditTextCallback(s: CharSequence, start: Int, before: Int, count: Int) {
        username = s.toString()
    }

    fun passwordEditTextCallback(s: CharSequence, start: Int, before: Int, count: Int) {
        password = s.toString()
    }

    sealed class State {
        object Uninitialised : State()
        data class Init(val loginAction: (user: String, pwd: String) -> Unit) : State()
        data class Success(val intent: Intent) : State()
        object Failure : State()
    }
}