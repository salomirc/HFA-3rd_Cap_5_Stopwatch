package com.hfad.hfa_3rdcap5stopwatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

private const val TAG : String = "LifeCycle"

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "${javaClass.simpleName} OnCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "${javaClass.simpleName} OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "${javaClass.simpleName} OnResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "${javaClass.simpleName} OnPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "${javaClass.simpleName} OnStop")
        super.onStop()
    }

    override fun onRestart() {
        Log.d(TAG, "${javaClass.simpleName} OnRestart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d(TAG, "${javaClass.simpleName} OnDestroy")
        super.onDestroy()
    }
}