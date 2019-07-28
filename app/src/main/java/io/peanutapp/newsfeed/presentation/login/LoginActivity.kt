package io.peanutapp.newsfeed.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.core.DispatcherProvider
import io.peanutapp.newsfeed.domain.login.Login
import io.peanutapp.newsfeed.domain.work
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Job() + dispatcherProvider.provideMainDispatcher()

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    @Inject
    lateinit var login: Login

    //TODO move to viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            val username = findViewById<EditText>(R.id.login_username).editableText.toString()
            val password = findViewById<EditText>(R.id.login_password).editableText.toString()
            if (username.isNotBlank() and password.isNotBlank()) {
                work(login, Login.Params(username, password))
                startActivity(Intent(this, PostsListActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username and Password can't be empty!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}