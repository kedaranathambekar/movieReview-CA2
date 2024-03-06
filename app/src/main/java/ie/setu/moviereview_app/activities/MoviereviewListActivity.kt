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
        binding.recyclerView.adapter = MoviereviewAdapter(app.movieReviews.findAll(),this)
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
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0,app.movieReviews.findAll().size)
        }
    }
    override fun onMoviereviewClick(placemark: MoviereviewModel) {
        val launcherIntent = Intent(this, MoviereviewActivity::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.movieReviews.findAll().size)
            }
        }
}

//class MoviereviewAdapter constructor(private var moviereviews: List<MoviereviewModel>) :
//    RecyclerView.Adapter<MoviereviewAdapter.MainHolder>() {

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
//        val binding = ListcardPlacemarkBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return MainHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MainHolder, position: Int) {
//        val placemark = moviereviews[holder.adapterPosition]
//        holder.bind(placemark)
//    }
//
//    override fun getItemCount(): Int = moviereviews.size

//    class MainHolder(private val binding: ListcardPlacemarkBinding) :
//        RecyclerView.ViewHolder(binding.root) {

//        fun bind(moviereview: MoviereviewModel) {
//            binding.movieName.text = moviereview.tittle
//            binding.movieDescription.text = moviereview.description
//            binding.Reviewer.text = moviereview.reviewer
//        }
//    }
//}