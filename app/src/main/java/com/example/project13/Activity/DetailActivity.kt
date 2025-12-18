package com.example.project13.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project13.Adapter.PicListAdapter
import com.example.project13.Adapter.SelectWeightAdapter
import com.example.project13.Helper.ManagmentCart
import com.example.project13.Model.ItemsModel
import com.example.project13.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel

    private var numberOrder = 1


    private val managerCart by lazy { ManagmentCart(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        getBundle()
        initList()
    }

    private fun getBundle() {
        // lấy object an toàn
        item = intent.getParcelableExtra("object") ?: return

        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.description
            tvPrice.text = "$${item.price}"
            tvRating.text = item.rating.toString()
            SellerName.text = item.sellerName

            btnAddToCart.setOnClickListener {
                item.numberInCart = numberOrder
                managerCart.insertFood(item)
            }

            btnBack.setOnClickListener { finish() }

            btnCart.setOnClickListener {
                startActivity(Intent(this@DetailActivity, CartActivity::class.java))
            }
        }
    }

    private fun initList() {

        val weightList = ArrayList(item.size)
        weightList.addAll(item.size)

        binding.weightList.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = SelectWeightAdapter(weightList)
        }

        // ===== Image list =====
        val picList = ArrayList(item.picUrl)
        if (picList.isNotEmpty()) {
            Glide.with(this)
                .load(picList[0])
                .into(binding.PicMain)
        }

        binding.picList.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = PicListAdapter(picList) { selectedImageUrl ->
                Glide.with(this@DetailActivity)
                    .load(selectedImageUrl)
                    .into(binding.PicMain)
            }
        }
    }
}
