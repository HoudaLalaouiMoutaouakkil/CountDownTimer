package com.example.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val time_in_mill:Long=25*60*1000
    var remainingTime:Long=time_in_mill
    var timer:CountDownTimer?=null
    var isTimerRunning= false
    lateinit var title: TextView
    lateinit var time: TextView
    lateinit var btnstart: Button
    lateinit var reset: TextView
    lateinit var pb: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title=findViewById(R.id.takepomodoro)
        time=findViewById(R.id.time)
        btnstart=findViewById(R.id.btnstart)
        reset=findViewById(R.id.reset)
        pb=findViewById(R.id.progressBar)
        btnstart.setOnClickListener{
            if (!isTimerRunning){
            startTimer()
            title.text=resources.getText(R.string.keep_going)
        }}
        reset.setOnClickListener { resetTimer() }
    }

    private fun startTimer() {
         timer = object : CountDownTimer(time_in_mill, 1 * 1000) {
            override fun onTick(timeLeft: Long) {
                remainingTime=timeLeft
                updateTimerText()
                pb.progress=remainingTime.toDouble().div(time_in_mill.toDouble()).times(100).toInt()

            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "finish!!", Toast.LENGTH_LONG).show()
                isTimerRunning=false
            }
        }.start()
      isTimerRunning=true
    }
   private fun updateTimerText(){
       val minute=remainingTime.div(1000).div(60)
       val second=remainingTime.div(1000)%60
       val fomatedtime=String.format("%02d:%02d",minute,second)
       time.text=fomatedtime
   }
    private fun resetTimer(){
        timer?.cancel()
        remainingTime=time_in_mill
        updateTimerText()
        title.text=resources.getText(R.string.take_pomodoro)
        isTimerRunning=false
    }


}