package io.peanutapp.newsfeed.core

import androidx.appcompat.app.AppCompatActivity

interface ViewContract<State> {
    fun bind(activity: AppCompatActivity, callback: Callback)
    fun unbind()
    fun changeState(newState: State)

    interface Callback {
        fun onViewBind()
    }
}