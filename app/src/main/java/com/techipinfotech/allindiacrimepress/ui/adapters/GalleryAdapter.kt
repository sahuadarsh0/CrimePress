package com.techipinfotech.allindiacrimepress.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techipinfotech.allindiacrimepress.databinding.ListGalleryBinding
import com.techipinfotech.allindiacrimepress.databinding.ListMemberBinding
import com.techipinfotech.allindiacrimepress.model.GalleryItem


class GalleryAdapter(private val onItemClicked: (GalleryItem) -> Unit) : ListAdapter<GalleryItem, GalleryAdapter
.MembersViewHolder>(DIFFUTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
         MembersViewHolder(ListGalleryBinding
             .inflate(LayoutInflater.from(parent.context), parent, false))


    companion object {
        private val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<GalleryItem>() {
            override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
                oldItem.galleryId == newItem.galleryId

            override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class MembersViewHolder(private val binding: ListGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(galleryItem: GalleryItem, onItemClicked: (GalleryItem) -> Unit) {
            binding.gallery = galleryItem
            binding.root.setOnClickListener { onItemClicked(galleryItem) }
        }
    }
}