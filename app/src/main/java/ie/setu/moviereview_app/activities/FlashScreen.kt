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
        setContentView(R.layout.activity_flash_screen) // Create a layout file for the splash screen

        // Optional: You can add additional setup code or delay here if needed

        // Start the MoviereviewActivity after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            startMoviereviewActivity()
        }, SPLASH_DELAY)
    }

    private fun startMoviereviewActivity() {
        val intent = Intent(this, MoviereviewListActivity::class.java)
        startActivity(intent)
        finish() // Finish the splash activity so that it's not in the back stack
    }

    companion object {
        private const val SPLASH_DELAY = 5000L // 2 seconds delay (adjust as needed)
    }
}