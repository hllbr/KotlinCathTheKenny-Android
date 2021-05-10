package com.hllbr.kotlincaththekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //İnsivibility operation in İmages = imageView.visibility = View.INVISIBLE


       // ImageArray ==

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)

        hideImages()

        //CountDown Timer
        object : CountDownTimer(15700,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time : ${millisUntilFinished/1000}"

            }

            override fun onFinish() {
                timeText.text ="Time : 0"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                var alert = AlertDialog.Builder(this@MainActivity)//applicationContext appi çökertti
                alert.setTitle("Game Over !")
                alert.setMessage("Restart the Game?")
                alert.setPositiveButton("Yes"){dialog,which ->
                    //Restart
                    val intent = intent
                    finish()//Destroyed flag
                    startActivity(intent)
                }
                alert.setNegativeButton("No"){dialog,which ->
                    //Destroyed or game information show
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()

                }
                alert.show()
            }

        }.start()
    }
    fun increaseScore(view : View){
        score ++
        scoreText.text = "Score : ${score}"

    }
    fun hideImages(){
        runnable = object :Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
            }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,300)
        }

        }
        handler.post(runnable)

    }
}