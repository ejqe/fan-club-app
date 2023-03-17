package com.ejqe.fan_club_app.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.databinding.SingleMemberBinding
import com.ejqe.fan_club_app.fragment.MemberListFragment
import com.ejqe.fan_club_app.model.MembersModel


class MemberListAdapter(
    private var memberList: ArrayList<MembersModel>,
    private val context: MemberListFragment
) : RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {



    inner class ViewHolder(

        val itemBinding: SingleMemberBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SingleMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )


        return ViewHolder(itemBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = memberList[position]
        Glide.with(context).load(currentItem.imageUrl).into(holder.itemBinding.memberImage)
        holder.itemBinding.memberName.text = "MNL48 " + currentItem._name?.uppercase()
    }


    override fun getItemCount(): Int = memberList.size

}


