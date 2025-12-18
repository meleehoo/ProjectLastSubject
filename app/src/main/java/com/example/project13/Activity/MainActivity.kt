package com.example.project13.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.project13.Adapter.BestSellerAdapter
import com.example.project13.Adapter.CategoryAdapter
import com.example.project13.Adapter.SliderApdapter
import com.example.project13.Model.SliderModel
import com.example.project13.R
import com.example.project13.ViewModel.MainViewModel
import com.example.project13.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initBestSeller()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    //    private fun initBestSeller() {
//        binding.progressBarBestSeller.visibility = View.VISIBLE
//
//        binding.RecyclerViewBestSeller.layoutManager = GridLayoutManager(this, 2)
//
//        viewModel.bestSeller.observe(this) { list ->
//            binding.RecyclerViewBestSeller.adapter = BestSellerAdapter(list)
//            binding.progressBarBestSeller.visibility = View.GONE
//        }
//
//        viewModel.loadbestSeller()
//    }
    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility = View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.RecyclerViewBestSeller.layoutManager= GridLayoutManager(this@MainActivity,2)
            binding.RecyclerViewBestSeller.adapter = BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility=View.GONE
        })
        viewModel.loadbestSeller()
    }

    private fun banners(image: List<SliderModel>) {
        binding.viewPager2.adapter = SliderApdapter(image, binding.viewPager2)
        binding.viewPager2.clipToPadding=false
        binding.viewPager2.clipChildren=false
        binding.viewPager2.offscreenPageLimit=3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer= CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))

        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if(image.size>1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }
    private fun initBanner(){
        binding.progressBarSlider.visibility= View.VISIBLE
        viewModel.banner.observe(this, Observer{
            banners(it)
            binding.progressBarSlider.visibility = View.GONE
        })
        viewModel.loadBanner()
    }
    private fun initCategory(){
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer{
            binding.RecyclerViewCategory.layoutManager= LinearLayoutManager(this@MainActivity,
                LinearLayoutManager.HORIZONTAL,false)
            binding.RecyclerViewCategory.adapter= CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE

        })
        viewModel.loadcategory()
    }
}
