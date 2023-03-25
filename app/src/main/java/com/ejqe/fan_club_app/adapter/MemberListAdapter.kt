package com.ejqe.fan_club_app.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.databinding.SingleMemberBinding
import com.ejqe.fan_club_app.fragment.MemberListFragment
import com.ejqe.fan_club_app.model.MembersModel


class MemberListAdapter(
    private val context: MemberListFragment,
    private val memberList: ArrayList<MembersModel>,
    private val onClickListener: OnClickClass
) :
    RecyclerView.Adapter<MemberListAdapter.MLViewHolder>() {

    class MLViewHolder(
        val itemBinding: SingleMemberBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root){
            private val icon: ImageView = itemBinding.memberImage
            fun bind(member: MembersModel, onClickListener: OnClickClass) {
                itemBinding.root.setOnClickListener{
                    onClickListener.onClick(member, icon)
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

        val currentItem = memberList[position]
        Glide.with(context).load(currentItem.imageUrl)
            .into(holder.itemBinding.memberImage)
        holder.itemBinding.apply {
            memberName.text = currentItem._name?.uppercase()
            memberImage.transitionName = currentItem._name
            }
        holder.bind(currentItem, onClickListener)
        }



    class OnClickClass( val clickListener: (MembersModel, ImageView) -> Unit) {
        fun onClick(member: MembersModel, icon: ImageView) = clickListener(member, icon)
    }

}


