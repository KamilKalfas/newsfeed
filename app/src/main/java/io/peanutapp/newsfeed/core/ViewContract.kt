package io.peanutapp.newsfeed.core

import androidx.appcompat.app.AppCompatActivity

interface ViewContract<State> {
    fun bind(activity: AppCompatActivity, callback: Callback)
    fun changeState(newState: State)
    fun unbind()

    interface Callback {
        fun onViewBind()
    }
}