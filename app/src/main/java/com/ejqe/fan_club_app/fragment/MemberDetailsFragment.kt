package com.ejqe.fan_club_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.R
import com.ejqe.fan_club_app.databinding.FragmentMemberDetailsBinding
import com.ejqe.fan_club_app.model.MembersModel


class MemberDetailsFragment : Fragment() {

    private var _binding: FragmentMemberDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMemberDetailsBinding.inflate(inflater, container, false)

//        val memberList: ArrayList<MembersModel>
//        Glide.with(this).load(memArgs.imageUrl).into(binding.memberImage)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
