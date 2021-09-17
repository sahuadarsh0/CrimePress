package com.techipinfotech.allindiacrimepress.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techipinfotech.allindiacrimepress.databinding.ListMemberBinding
import com.techipinfotech.allindiacrimepress.model.MemberItem
import com.techipinfotech.allindiacrimepress.model.Members


class MembersAdapter : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {

    private val memberList = Members()

    fun setList(members: ArrayList<MemberItem>?) {
        memberList.clear()
        memberList.addAll(members!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        val binding = ListMemberBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MembersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.bind(memberList[position])
    }

    inner class MembersViewHolder(private val binding: ListMemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memberItem: MemberItem) {
            binding.member = memberItem
        }
    }
}