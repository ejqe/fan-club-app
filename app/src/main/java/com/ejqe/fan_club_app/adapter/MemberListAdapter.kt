package com.ejqe.fan_club_app.adapter


import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.activity.MemberOnClick
import com.ejqe.fan_club_app.databinding.SingleMemberBinding
import com.ejqe.fan_club_app.fragment.MemberListFragment
import com.ejqe.fan_club_app.model.MembersModel


class MemberListAdapter(
    private val memberList: ArrayList<MembersModel>,
    private val callback: MemberOnClick
) :
    RecyclerView.Adapter<MemberListAdapter.MLViewHolder>() {

    class MLViewHolder(
        private val itemBinding: SingleMemberBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root){
            fun setData(data: MembersModel, callback: MemberOnClick){
                itemBinding.memberName.text = data._name?.uppercase()
                Glide.with(itemView.context).load(data.imageUrl).into(itemBinding.memberImage)
                itemBinding.root.setOnClickListener{
                    callback.onSelect(data)
                }
            }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MLViewHolder {
        val itemBinding = SingleMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return MLViewHolder(itemBinding)
    }

    override fun getItemCount() = memberList.size

    override fun onBindViewHolder(holder: MLViewHolder, position: Int) {

        holder.setData(memberList[position], callback)
    }





}


