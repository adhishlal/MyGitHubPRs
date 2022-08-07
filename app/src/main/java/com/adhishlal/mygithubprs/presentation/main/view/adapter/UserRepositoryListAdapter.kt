package com.adhishlal.mygithubprs.presentation.main.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants
import com.adhishlal.mygithubprs.data.utils.Constants.REPO
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS
import com.adhishlal.mygithubprs.data.utils.Constants.USER_ID
import com.adhishlal.mygithubprs.databinding.ItemRepositoryBinding
import com.adhishlal.mygithubprs.domain.base.App.Companion.context
import com.adhishlal.mygithubprs.presentation.main.view.activities.PullRequestsActivity

class UserRepositoryListAdapter(private var repositoryList: ArrayList<UserRepositoriesResponseModel>) :
    RecyclerView.Adapter<UserRepositoryListAdapter.UserRepositoryListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRepositoryListViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserRepositoryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserRepositoryListViewHolder, position: Int) {
        val userRepository = repositoryList[position]
        holder.bind(userRepository)
    }

    class UserRepositoryListViewHolder(private val itemBinding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(userRepository: UserRepositoriesResponseModel) {
            itemBinding.prTitle.text = userRepository.name
            itemBinding.defaultBranch.text = userRepository.default_branch
            itemBinding.isLocked.setImageDrawable(
                if (userRepository.private) {
                    AppCompatResources.getDrawable(context, R.drawable.ic_baseline_lock_24)
                } else {
                    AppCompatResources.getDrawable(context, R.drawable.ic_baseline_unlock_24)
                }
            )
            itemBinding.root.setOnClickListener {
                val intent = Intent(context, PullRequestsActivity::class.java)
                intent.putExtra(USER_ID, userRepository.owner.login)
                intent.putExtra(REPO, userRepository.name)
                intent.putExtra(STATUS, Constants.STATUS_CLOSED)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}
