package com.nanaten.try_livedata_builder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.nanaten.try_livedata_builder.adapter.RecyclerViewAdapter
import com.nanaten.try_livedata_builder.adapter.bindItems
import com.nanaten.try_livedata_builder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.recyclerView.layoutManager = LinearLayoutManager(this)
            it.recyclerView.adapter = RecyclerViewAdapter()
        }

        // observe時に livedata { ... } 内の処理が実行される
        viewModel.items.observe(this, Observer {
            binding.recyclerView.bindItems(it)
        })
    }
}
