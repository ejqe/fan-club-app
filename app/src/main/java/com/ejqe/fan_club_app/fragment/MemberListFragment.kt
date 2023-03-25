package com.ejqe.fan_club_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.ejqe.fan_club_app.adapter.MemberListAdapter
import com.ejqe.fan_club_app.databinding.FragmentMemberListBinding
import com.ejqe.fan_club_app.model.MembersModel

import com.google.firebase.database.*

class MemberListFragment : Fragment() {

    private var _binding: FragmentMemberListBinding? = null
    private val binding get() = _binding!!

    private lateinit var databaseReference: DatabaseReference
    private lateinit var memberList: ArrayList<MembersModel>

    private lateinit var userRecyclerview: RecyclerView
    private lateinit var mRecyclerViewAdapter: MemberListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMemberListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Setting the Toolbar
        val toolbar = binding.toolbar
        toolbar.title = "MEMBERS"
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

        sharedElementReturnTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        getUserData()
        setupRecyclerView()

        refreshList()

        return view
    }


    private fun refreshList() {
        binding.swipeToRefresh.setOnRefreshListener {
            memberList.clear()
            getUserData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }


    private fun setupRecyclerView() {

        userRecyclerview = binding.rvMemberList
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMemberList.layoutManager = layoutManager
        binding.rvMemberList.setHasFixedSize(true)
        memberList = arrayListOf()
    }

    //Get Firebase Data
    private fun getUserData() {

        databaseReference = FirebaseDatabase
            .getInstance()
            .getReference("Members")
        databaseReference.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (userSnapshot in snapshot.children) {
                        val member = userSnapshot.getValue(MembersModel::class.java)
                        memberList.add(member!!)
                    }

                    val membersModelListener = MemberListAdapter.OnClickClass { membersModel, imageView ->

                            val directions: NavDirections = MemberListFragmentDirections
                                    .actionMemberListFragmentToMemberDetailsFragment(membersModel)
                            val extras = FragmentNavigatorExtras(
                                imageView to membersModel.imageUrl!!
                            )
                            findNavController().navigate(directions, extras)
                        }


                    mRecyclerViewAdapter =
                        MemberListAdapter(this@MemberListFragment, memberList, membersModelListener)
                    userRecyclerview.adapter = mRecyclerViewAdapter
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}