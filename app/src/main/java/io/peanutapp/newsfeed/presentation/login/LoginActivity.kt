package io.peanutapp.newsfeed.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.peanutapp.newsfeed.R
import io.peanutapp.newsfeed.presentation.postslist.PostsListActivity

class LoginActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            val username = findViewById<EditText>(R.id.login_username).editableText.toString()
            val password = findViewById<EditText>(R.id.login_password).editableText.toString()
            if (username.isNotBlank() and password.isNotBlank()) {
                startActivity(Intent(this, PostsListActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username and Password can't be empty!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}