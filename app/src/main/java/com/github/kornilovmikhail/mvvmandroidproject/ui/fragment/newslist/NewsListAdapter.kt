package com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.model.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(
    private val clickListener: (News) -> Unit
) : ListAdapter<News, NewsListAdapter.NewsHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean = oldItem == newItem

    }

    class NewsHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(news: News, clickListener: (News) -> Unit) {
            with(containerView) {
                tv_list_item_name.text = news.title
                setOnClickListener { clickListener(news) }
            }
        }
    }
}
