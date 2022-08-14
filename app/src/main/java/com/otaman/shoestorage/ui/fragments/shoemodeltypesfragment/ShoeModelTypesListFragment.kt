package com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.FragmentShoeTypesListBinding
import com.otaman.shoestorage.data.shoelist.ShoeTypesList
import com.otaman.shoestorage.ui.adapters.OnShoeTypeListClick
import com.otaman.shoestorage.ui.adapters.OnShoeTypeListDeleteClick
import com.otaman.shoestorage.ui.adapters.ShoeTypesListAdapter
import com.otaman.shoestorage.ui.fragments.ShoeListFragment
import com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment.AddEditModelTypeFragment.Companion.ADD_EDIT_DIALOG
import com.otaman.shoestorage.viewmodel.shoetypeslist.ShoeTypesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            bundle.putSerializable(MODEL_TYPE_SCREEN, ShoeTypeScreen.ADD)
            AddEditModelTypeFragment().show(parentFragmentManager, ADD_EDIT_DIALOG)
        }
    }

    override fun onShoeTypeListClick(shoeTypesList: ShoeTypesList) {
        val fragment = ShoeListFragment()
        transaction(fragment)
    }

    override fun onShoeTypeEditClick(shoeTypesList: ShoeTypesList) {
        bundle.putSerializable(MODEL_TYPE_SCREEN, ShoeTypeScreen.EDIT)
        bundle.putParcelable(MODEL_TYPE, shoeTypesList)
        AddEditModelTypeFragment().show(parentFragmentManager, ADD_EDIT_DIALOG)
    }

    private fun transaction(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction
            .replace(R.id.nav_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }

    companion object {
        val bundle = Bundle()
        const val MODEL_TYPE = "com.otaman.shoestorage.model_type"
        const val MODEL_TYPE_SCREEN = "com.otaman.shoestorage.model_type_screen"
    }
}