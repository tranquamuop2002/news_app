package com.example.news_app_mvvm.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app_mvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.news_app_mvvm.databinding.ItemNewsBinding
import com.example.news_app_mvvm.domain.model.News
import com.example.news_app_mvvm.presentation.common.BaseAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    private val newsAdapter =
        object : BaseAdapter<News, ItemNewsBinding>(ItemNewsBinding::inflate) {
            @SuppressLint("SetTextI18n")
            override fun bind(binding: ItemNewsBinding, item: News, position: Int) {
                binding.userId.text = item.userId.toString()
                binding.id.text = item.id.toString()
                binding.title.text = item.title
                binding.body.text = item.body
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myButton.setOnClickListener {
            Log.d("VietTD", "onCreate: ${binding.myButton.text}")
            viewModel.getNews()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = newsAdapter

        viewModel.newLiveData.observe(this) {
            newsAdapter.submitList(viewModel.newsList.toList())
        }

    }
}