package com.ejqe.fan_club_app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.activity.MainActivity
import com.ejqe.fan_club_app.databinding.FragmentMemberDetailsBinding
import com.ejqe.fan_club_app.model.MembersModel

class MemberDetailsFragment: Fragment() {

    private var _binding: FragmentMemberDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MemberDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMemberDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.memberName.text = args.membersModelArgs._name
        Glide.with(requireActivity()).load(args.membersModelArgs.imageUrl).into(binding.memberImage)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).setToolBarTitle("DETAILS")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}