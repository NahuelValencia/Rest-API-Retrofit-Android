package com.example.rest_api_retrofit_android.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_api_retrofit_android.Model.GitHubRepository
import com.example.rest_api_retrofit_android.R

class GitHubRepositoryHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.github_repository_holder, parent, false)) {

    private var name: TextView? = null
    private var description: TextView? = null
    private var language: TextView? = null
    private var url: TextView? = null
    private var created: TextView? = null
    private var modified: TextView? = null

    init {
        name = itemView.findViewById(R.id.name)
        description = itemView.findViewById(R.id.description)
        language = itemView.findViewById(R.id.language)
        url = itemView.findViewById(R.id.url)
        created = itemView.findViewById(R.id.created)
        modified = itemView.findViewById(R.id.last_modified)
    }

    fun bind(repository: GitHubRepository) {
        name?.text = repository.repositoryName
        description?.text = repository.repoDescription
        language?.text = repository.languageProgramming
        url?.text = repository.repositoryUrl
        created?.text = repository.creation
        modified?.text = repository.modified
    }
}