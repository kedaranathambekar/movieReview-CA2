package ie.setu.moviereview_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Telephony.Threads
import ie.setu.moviereview_app.R

class FlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            startMoviereviewActivity()
        }, SPLASH_DELAY)
    }

    private fun startMoviereviewActivity() {
        val intent = Intent(this, MoviereviewListActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val SPLASH_DELAY = 5000L // 5 seconds delay (adjust as needed)
    }
}