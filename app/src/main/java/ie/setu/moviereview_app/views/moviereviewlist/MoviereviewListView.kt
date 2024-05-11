package ie.setu.moviereview_app.views.moviereviewlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.moviereview_app.R
import ie.setu.moviereview_app.databinding.ActivityMoviereviewBinding
import ie.setu.moviereview_app.databinding.ActivityMoviereviewListBinding
import ie.setu.moviereview_app.main.MainApp
import ie.setu.moviereview_app.models.MoviereviewModel

class MoviereviewListView : AppCompatActivity(), MoviereviewListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityMoviereviewListBinding
    lateinit var presenter: MoviereviewListPresenter
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviereviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = MoviereviewListPresenter(this)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
       // loadPlacemarks()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> { presenter.doAddPlacemark() }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onMoviereviewClick(movieReview: MoviereviewModel, position: Int) {
        this.position = position
        presenter.doEditPlacemark(movieReview, this.position)

    }
//    private fun loadPlacemarks() {
//
//        binding.recyclerView.adapter = MoviereviewAdapter(presenter.getPlacemarks(), this )
//        onRefresh()
//    }

    fun onRefresh() {
        binding.recyclerView.adapter?.
        notifyItemRangeChanged(0,presenter.getPlacemarks().size)
    }

    fun onDelete(position : Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}