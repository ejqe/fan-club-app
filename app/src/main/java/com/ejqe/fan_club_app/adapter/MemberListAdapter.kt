package com.ejqe.fan_club_app.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.databinding.SingleMemberBinding
import com.ejqe.fan_club_app.fragment.MemberListFragment
import com.ejqe.fan_club_app.model.MembersModel


class MemberListAdapter(
    private val memberList: ArrayList<MembersModel>,
    private val onItemClicked: (position: Int) -> Unit,
    private val context: MemberListFragment
) :
    RecyclerView.Adapter<MemberListAdapter.MLViewHolder>() {

    class MLViewHolder(
        val itemBinding: SingleMemberBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        init {
            itemBinding.memberImage.setOnClickListener(this)
        }

        override fun onClick(p0: View) {
            val position = absoluteAdapterPosition
            onItemClicked(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MLViewHolder {
        val itemBinding = SingleMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return MLViewHolder(itemBinding, onItemClicked)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MLViewHolder, position: Int) {

        val currentItem = memberList[position]
        Glide.with(context).load(currentItem.imageUrl)
            .into(holder.itemBinding.memberImage)

        holder.itemBinding.memberName.text = "MNL48 " + currentItem._name?.uppercase()


    }

    override fun getItemCount(): Int = memberList.size

}


