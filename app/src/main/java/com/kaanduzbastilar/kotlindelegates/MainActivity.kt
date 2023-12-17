package com.kaanduzbastilar.kotlindelegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity(), LifecyclerLogger by LifecycleLoggerImplementation() {


    //property delegates
    private val myVariable by lazy {
        println("lazy implementation")
        10
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerLifecyclerOwner(this)

        println(myVariable)

    }
}

interface LifecyclerLogger{
    fun registerLifecyclerOwner(owner: LifecycleOwner)
}

class LifecycleLoggerImplementation: LifecyclerLogger, LifecycleEventObserver{
    override fun registerLifecyclerOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> println("on resume executed")
            Lifecycle.Event.ON_PAUSE -> println("on pause executed")
            else -> Unit
        }
    }

}