package com.adhishlal.mygithubprs.presentation.main.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants.REPO
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS_CLOSED
import com.adhishlal.mygithubprs.data.utils.Constants.USER_ID
import com.adhishlal.mygithubprs.databinding.ItemRepositoryBinding
import com.adhishlal.mygithubprs.domain.base.App.Companion.context
import com.adhishlal.mygithubprs.presentation.main.view.activities.PullRequestsActivity

class RepositoryListAdapter(private var repositoryList: ArrayList<MyRepositoriesResponseModel>) :
    RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val myRepository = repositoryList[position]
        holder.bind(myRepository)
    }

    class RepositoryViewHolder(private val itemBinding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(myRepository: MyRepositoriesResponseModel) {
            itemBinding.prTitle.text = myRepository.name
            itemBinding.defaultBranch.text = myRepository.default_branch
            itemBinding.isLocked.setImageDrawable(
                if (myRepository.private) {
                    AppCompatResources.getDrawable(context, R.drawable.ic_baseline_lock_24)
                } else {
                    AppCompatResources.getDrawable(context, R.drawable.ic_baseline_unlock_24)
                }
            )
            itemBinding.root.setOnClickListener {
                val intent = Intent(context, PullRequestsActivity::class.java)
                intent.putExtra(USER_ID, myRepository.owner.login)
                intent.putExtra(REPO, myRepository.name)
                intent.putExtra(STATUS, STATUS_CLOSED)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}
