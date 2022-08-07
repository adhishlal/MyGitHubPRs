package com.adhishlal.mygithubprs.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.databinding.ItemPullRequestBinding
import com.adhishlal.mygithubprs.domain.base.App.Companion.context
import com.squareup.picasso.Picasso

class UserPRListAdapter(private var prList: ArrayList<UserPullRequestsResponseModel>) :
    RecyclerView.Adapter<UserPRListAdapter.PRListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PRListViewHolder {
        val binding = ItemPullRequestBinding.inflate(LayoutInflater.from(context), parent, false)
        return PRListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PRListViewHolder, position: Int) {
        val myRepository = prList[position]
        holder.bind(myRepository)
    }

    class PRListViewHolder(private val itemBinding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(myRepository: UserPullRequestsResponseModel) {
            itemBinding.prTitle.text = myRepository.title
            itemBinding.prCreatedOn.text = myRepository.created_at
            itemBinding.prClosedOn.text = myRepository.closed_at
            itemBinding.prStatus.text = myRepository.state
            Picasso.get()
                .load(myRepository.user.avatar_url)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(itemBinding.githubAvatar)
        }
    }

    override fun getItemCount(): Int {
        return prList.size
    }
}
