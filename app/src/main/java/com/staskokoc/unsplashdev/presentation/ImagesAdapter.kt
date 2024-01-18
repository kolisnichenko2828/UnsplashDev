package com.staskokoc.unsplashdev.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.staskokoc.unsplashdev.R
import com.staskokoc.unsplashdev.databinding.LayoutRvListBinding
import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<UnsplashImage, ImagesAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(
        private val binding: LayoutRvListBinding,
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(image: UnsplashImage, listener: OnItemClickListener, position: Int) {
            binding.imageView.load(data = image.smallImageUrl) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_background)
            }
            binding.imageView.setOnClickListener() {
                listener.onItemClick(image.smallImageUrl, position)
            }
            binding.textView.text = "by ${image.authorName}"
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(LayoutRvListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<UnsplashImage>() {
        override fun areItemsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UnsplashImage, newItem: UnsplashImage): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(image = getItem(position), listener = listener, position = position)
    }
}