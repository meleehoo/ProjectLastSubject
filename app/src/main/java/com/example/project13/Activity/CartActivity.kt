package com.example.project13.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project13.Adapter.CartAdapter
import com.example.project13.Helper.ChangeNumberItemsListener
import com.example.project13.Helper.ManagmentCart
import com.example.project13.databinding.ActivityCartBinding
import kotlin.math.round

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private val managmentCart by lazy { ManagmentCart(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        initCartList()
        updateUI()
    }

    override fun onResume() {
        super.onResume()
        // Nếu bạn thêm hàng ở Detail rồi quay lại Cart
        initCartList()
        updateUI()
    }

    private fun initCartList() {
        binding.viewCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            isNestedScrollingEnabled = false // QUAN TRỌNG: RecyclerView nằm trong ScrollView/NestedScrollView
            adapter = CartAdapter(
                managmentCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        updateUI()
                    }
                }
            )
        }
    }

    private fun updateUI() {
        val hasItems = managmentCart.getListCart().isNotEmpty()

        // Hiện/ẩn trạng thái rỗng
        binding.tvEmptyCart.visibility = if (hasItems) View.GONE else View.VISIBLE
        binding.ScrollViewLayout.visibility = if (hasItems) View.VISIBLE else View.VISIBLE
        binding.viewCart.visibility = if (hasItems) View.VISIBLE else View.GONE

        // Tính tiền (đúng 2 số lẻ)
        val percentTax = 0.02
        val delivery = 10.0

        val itemTotal = managmentCart.getTotalFee()
        val tax = round(itemTotal * percentTax * 100) / 100.0
        val itemTotalRounded = round(itemTotal * 100) / 100.0
        val total = round((itemTotal + tax + delivery) * 100) / 100.0

        with(binding) {
            tvSubTotal.text = "$$itemTotalRounded"
            tvTax.text = "$$tax"
            tvFreeDevery.text = "$$delivery"
            tvTotal.text = "$$total"
        }
    }
}
