package com.ejqe.fan_club_app.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ejqe.fan_club_app.R
import com.ejqe.fan_club_app.databinding.FragmentMemberDetailsBinding
import java.util.concurrent.TimeUnit
import com.ejqe.fan_club_app.model.MembersModel


class MemberDetailsFragment : Fragment() {

    private var _binding: FragmentMemberDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MemberDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentMemberDetailsBinding.inflate(inflater, container, false)

        val animation = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        postponeEnterTransition(200, TimeUnit.MILLISECONDS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(args.membersModel.imageUrl).into(binding.memberImage)
        binding.memberName.text = args.membersModel._name

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





}
