package com.otaman.shoestorage.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var cvShoeCodes: CardView
    private lateinit var cvShoeStorage: CardView
    private lateinit var cvAccount: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initClickListeners()
    }

    private fun initUi() {
        cvShoeCodes = binding.cvShoeCodes
        cvShoeStorage = binding.cvStorage
        cvAccount = binding.cvAccount
    }

    private fun initClickListeners() {
        cvShoeCodes.setOnClickListener {
            val fragment = ShoeModelTypesListFragment()
            transaction(fragment)
        }
        cvShoeStorage.setOnClickListener {
            val fragment = ShoeStorageFragment()
            transaction(fragment)
        }
        cvAccount.setOnClickListener {
            val fragment = AccountFragment()
            transaction(fragment)
        }
    }

    private fun transaction(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction
            .replace(R.id.nav_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}