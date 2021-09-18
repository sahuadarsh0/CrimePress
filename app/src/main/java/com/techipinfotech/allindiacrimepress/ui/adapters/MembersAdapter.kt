package com.techipinfotech.allindiacrimepress.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.techipinfotech.allindiacrimepress.databinding.ListMemberBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem


class MembersAdapter(private val onItemClicked: (MemberItem) -> Unit) : ListAdapter<MemberItem, MembersAdapter
.MembersViewHolder>(DIFFUTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder =
         MembersViewHolder(ListMemberBinding
             .inflate(LayoutInflater.from(parent.context), parent, false))


    companion object {
        private val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<MemberItem>() {
            override fun areItemsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class MembersViewHolder(private val binding: ListMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memberItem: MemberItem, onItemClicked: (MemberItem) -> Unit) {
            binding.member = memberItem
            binding.root.setOnClickListener { onItemClicked(memberItem) }
        }
    }
}