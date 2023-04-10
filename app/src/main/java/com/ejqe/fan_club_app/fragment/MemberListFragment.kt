package com.ejqe.fan_club_app.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ejqe.fan_club_app.activity.MainActivity
import com.ejqe.fan_club_app.activity.MemberOnClick
import com.ejqe.fan_club_app.adapter.MemberListAdapter
import com.ejqe.fan_club_app.databinding.FragmentMemberListBinding
import com.ejqe.fan_club_app.model.MembersModel
import com.google.firebase.database.*

class MemberListFragment : Fragment(), MemberOnClick {

    private var _binding: FragmentMemberListBinding? = null
    private val binding get() = _binding!!

    private lateinit var databaseReference: DatabaseReference
    private lateinit var memberList: ArrayList<MembersModel>

    private lateinit var userRecyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMemberListBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerView()
        getUserData()
        refreshList()


        return view
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
                    //setAdapter
                    userRecyclerview.adapter =
                        MemberListAdapter(memberList, this@MemberListFragment)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun refreshList() {
        binding.swipeToRefresh.setOnRefreshListener {
            getUserData()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).setToolBarTitle("MEMBERS")
    }

    override fun onDestroy() {

        super.onDestroy()
        _binding = null
    }

    override fun onSelect(member: MembersModel) {
//        Toast.makeText(requireContext(), "${member._name} Clicked", Toast.LENGTH_SHORT).show(


val action = MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(member)
        findNavController().navigate(action)
    }
}