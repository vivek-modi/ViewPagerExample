package com.example.viewpagerexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<ViewPagerViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter()
        viewpager.adapter = adapter
        lifecycleScope.launch {
            viewModel.dataList.collectLatest {
                adapter.submitData(it)
            }
        }

        next.setOnClickListener {
            viewpager.setCurrentItem(viewpager.currentItem.plus(1), true)
        }

        previous.setOnClickListener {
            viewpager.setCurrentItem(viewpager.currentItem.minus(1), true)
        }
    }
}