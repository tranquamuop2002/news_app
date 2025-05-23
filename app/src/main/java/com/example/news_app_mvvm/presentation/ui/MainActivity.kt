package com.example.news_app_mvvm.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_mvvm.R
import com.example.news_app_mvvm.databinding.ActivityMainBinding
import com.example.news_app_mvvm.domain.model.News
import com.example.news_app_mvvm.presentation.common.BaseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter = object : BaseAdapter<News>(R.layout.item_news) {
        @SuppressLint("SetTextI18n")
        override fun bind(holder: RecyclerView.ViewHolder, item: News) {
            val userIdTextView: TextView = holder.itemView.findViewById(R.id.user_id)
            val idTextView: TextView = holder.itemView.findViewById(R.id.id)
            val titleTextView: TextView = holder.itemView.findViewById(R.id.title)
            val bodyTextView: TextView = holder.itemView.findViewById(R.id.body)

            userIdTextView.text = "User ID: ${item.userId}"
            idTextView.text = "ID: ${item.id}"
            titleTextView.text = item.title
            bodyTextView.text = item.body
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.myButton.setOnClickListener {
            binding.flProgressbar.visibility = View.VISIBLE
            viewModel.getNews()
        }

        viewModel.newLiveData.observe(this) {
            adapter.setData(viewModel.newsList.toList())
            binding.flProgressbar.visibility = View.GONE
        }

    }
}