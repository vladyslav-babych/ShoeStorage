package com.otaman.shoestorage.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.FragmentAddShoeTypeListBinding
import com.otaman.shoestorage.model.shoelist.ShoeTypesList
import com.otaman.shoestorage.viewmodel.shoetypeslist.AddShoeTypesListViewModel

class AddShoeTypeListFragment : Fragment() {
    private lateinit var binding: FragmentAddShoeTypeListBinding
    private lateinit var bAddShoeTypesList: Button
    private lateinit var edModelType: EditText
    private val addShoeTypesListViewModel by viewModels<AddShoeTypesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoeTypeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initClickListener()
    }

    private fun initUi() {
        edModelType = binding.edModelType
        bAddShoeTypesList = binding.bAddModelType
    }

    private fun initClickListener() {
        bAddShoeTypesList.setOnClickListener {
            val modelType = edModelType.text.toString()
            if(modelType.isNotEmpty()) {
                addShoeTypesListViewModel.addShoeTypeList(ShoeTypesList(model_type = modelType))
                Toast.makeText(activity, "Додано тип моделі $modelType", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(activity, "Введіть тип моделі!", Toast.LENGTH_SHORT).show()
            }
            val fragment = ShoeModelTypesListFragment()
            transaction(fragment)
        }
    }

    private fun transaction(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction
            .replace(R.id.nav_container, fragment)
            .commit()
    }
}