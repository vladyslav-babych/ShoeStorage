package com.otaman.shoestorage.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.FragmentShoeTypesListBinding
import com.otaman.shoestorage.model.shoelist.ShoeTypesList
import com.otaman.shoestorage.view.adapters.OnShoeTypeListClick
import com.otaman.shoestorage.view.adapters.OnShoeTypeListDeleteClick
import com.otaman.shoestorage.view.adapters.ShoeTypesListAdapter
import com.otaman.shoestorage.viewmodel.shoetypeslist.ShoeTypesListViewModel

class ShoeModelTypesListFragment : Fragment(), OnShoeTypeListClick, OnShoeTypeListDeleteClick {
    private lateinit var binding: FragmentShoeTypesListBinding
    private lateinit var adapter: ShoeTypesListAdapter
    private val shoeTypesListViewModel by viewModels<ShoeTypesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeTypesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val recyclerView = binding.rvShoeTypesList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ShoeTypesListAdapter(this, this)
        recyclerView.adapter = adapter

        shoeTypesListViewModel.allShoeTypesList.observe(viewLifecycleOwner) { list ->
            adapter.updateShoeTypesList(list)
        }

        binding.fabAddShoeTypeList.setOnClickListener{
            val fragment = AddShoeTypeListFragment()
            transaction(fragment)
        }
    }

    override fun onShoeTypeListClick(shoeTypesList: ShoeTypesList) {
        val fragment = ShoeListFragment()
        transaction(fragment)
    }

    override fun onShoeTypeListDeleteClick(shoeTypesList: ShoeTypesList) {
        shoeTypesListViewModel.deleteShoeTypeList(shoeTypesList)
    }

    private fun transaction(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction
            .replace(R.id.nav_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}