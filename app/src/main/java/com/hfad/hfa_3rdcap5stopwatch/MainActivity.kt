package com.hfad.hfa_3rdcap5stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

private const val BASE_KEY = "base"
private const val OFFSET_KEY = "offset"
private const val RUNNING_KEY = "running"

class MainActivity : BaseActivity() {

    private lateinit var stopwatch: Chronometer //The stopwatch
    private var isRunning = false //Is the stopwatch running
    private var offset: Long = 0 //The base offset for the stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to the stopwatch
        stopwatch = findViewById(R.id.stopwatch)

        //Restore the previous state
//        savedInstanceState?.let {
//            offset = it.getLong(OFFSET_KEY)
//            isRunning = it.getBoolean(RUNNING_KEY)
//            if (isRunning) {
//                stopwatch.apply {
//                    base = it.getLong(BASE_KEY)
//                    start()
//                }
//            } else {
//                setBaseTime()
//            }
//        }

        //The start button starts the stopwatch id it's not running
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if (!isRunning) {
                setBaseTime()
                stopwatch.start()
                isRunning = true
            }
        }

        //The pause button pauses the stopwatch if it's running
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if (isRunning) {
                saveOffset()
                stopwatch.stop()
                isRunning = false
            }
        }

        //The reset button sets the offset and stopwatch to 0
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isRunning) {
            saveOffset()
            stopwatch.stop()
        }
    }


    override fun onResume() {
        super.onResume()
        if (isRunning) {
            setBaseTime()
            stopwatch.start()
            offset = 0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putLong(OFFSET_KEY, offset)
            putBoolean(RUNNING_KEY, isRunning)
            putLong(BASE_KEY, stopwatch.base)
        }
        super.onSaveInstanceState(outState)
    }

    // This method is called after the onCreate(savedInstanceState: Bundle?)
    // and represent an alternative to this code offering the separation of responsibility
    // If possible from the implementation point of view, is recommended to use this method.
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            offset = it.getLong(OFFSET_KEY)
            isRunning = it.getBoolean(RUNNING_KEY)
            if (isRunning) {
                stopwatch.apply {
                    base = it.getLong(BASE_KEY)
                    start()
                }
            } else {
                setBaseTime()
            }
        }
    }

    //Update the stopwatch.base time, allowing for any offset
    private fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    //Record the offset
    private fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
}