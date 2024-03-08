package ie.setu.moviereview_app.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.adapters.MoviereviewAdapter
import ie.setu.moviereview_app.adapters.MoviereviewListener
import ie.setu.moviereview_app.databinding.ActivityMoviereviewListBinding
import ie.setu.moviereview_app.databinding.ListcardPlacemarkBinding
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel

class MoviereviewListActivity : AppCompatActivity(), MoviereviewListener {


    lateinit var app: MainApp
    private lateinit var binding: ActivityMoviereviewListBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviereviewListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_moviereview_list)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MoviereviewAdapter(app.movieReviewss.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, MoviereviewActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

        if (it.resultCode == Activity.RESULT_OK){
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0,app.movieReviewss.findAll().size)
        }
    }
    override fun onMoviereviewClick(placemark: MoviereviewModel ,pos : Int) {
        val launcherIntent = Intent(this, MoviereviewActivity::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.movieReviewss.findAll().size)
            }
            else
                if (it.resultCode == 99)     (binding.recyclerView.adapter)?.notifyItemRemoved(position)
            //binding.recyclerView.adapter = MoviereviewAdapter(app.movieReviewss.findAll(),this)
        }
}


