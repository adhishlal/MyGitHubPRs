package com.adhishlal.mygithubprs.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.databinding.ItemRepositoryBinding
import com.adhishlal.mygithubprs.domain.base.App.Companion.context

class RepositoryListAdapter(var repositoryList: MyRepositoriesResponseModel) :
    PagingDataAdapter<MyRepositoriesResponseModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    private lateinit var itemRepositoryBinding: ItemRepositoryBinding

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(repositoryList) {
            itemRepositoryBinding.prTitle.text = this.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return RepositoryViewHolder(binding)
    }

    class RepositoryViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MyRepositoriesResponseModel>() {
        override fun areItemsTheSame(
            oldItem: MyRepositoriesResponseModel,
            newItem: MyRepositoriesResponseModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: MyRepositoriesResponseModel,
            newItem: MyRepositoriesResponseModel
        ): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.html_url == newItem.html_url
        }

    }
}