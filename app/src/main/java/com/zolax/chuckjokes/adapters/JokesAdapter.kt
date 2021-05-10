package com.zolax.chuckjokes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zolax.chuckjokes.R
import com.zolax.chuckjokes.models.Joke
import kotlinx.android.synthetic.main.jokes_elem.view.*

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {
    inner class JokesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var jokes: List<Joke>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.jokes_elem,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = jokes[position]
        holder.itemView.apply {
            jokeText.text = joke.joke
        }
    }

    override fun getItemCount() = jokes.size

}