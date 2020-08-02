package com.example.rest_api_retrofit_android.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_api_retrofit_android.Model.GitHubRepository

class GitHubRepositoryAdapter(
    private val repositoryList: List<GitHubRepository>
) : RecyclerView.Adapter<GitHubRepositoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepositoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GitHubRepositoryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: GitHubRepositoryHolder, position: Int) {
        val repository: GitHubRepository = repositoryList[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}