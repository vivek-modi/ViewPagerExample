package com.example.viewpagerexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE
import com.example.viewpagerexample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ViewPagerViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter()
        lifecycleScope.launchWhenCreated {
            viewModel.dataList.collectLatest {
                adapter.submitData(it)
            }
        }
        binding.viewpager.adapter = adapter

        binding.next.setOnClickListener {
            if (binding.viewpager.scrollState == SCROLL_STATE_IDLE) {
                binding.viewpager.setCurrentItem(binding.viewpager.currentItem.plus(1), true)
            }
        }

        binding.previous.setOnClickListener {
            if (binding.viewpager.scrollState == SCROLL_STATE_IDLE) {
                binding.viewpager.setCurrentItem(binding.viewpager.currentItem.minus(1), true)
            }
        }
    }
}