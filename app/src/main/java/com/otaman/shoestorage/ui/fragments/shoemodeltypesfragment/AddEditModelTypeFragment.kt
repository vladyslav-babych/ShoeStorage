package com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.otaman.shoestorage.data.shoelist.ShoeTypesList
import com.otaman.shoestorage.databinding.FragmentAddEditModelTypeBinding
import com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment.ShoeModelTypesListFragment.Companion.MODEL_TYPE
import com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment.ShoeModelTypesListFragment.Companion.MODEL_TYPE_SCREEN
import com.otaman.shoestorage.ui.fragments.shoemodeltypesfragment.ShoeModelTypesListFragment.Companion.bundle
import com.otaman.shoestorage.viewmodel.shoetypeslist.ShoeTypesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditModelTypeFragment : DialogFragment() {
    private lateinit var binding: FragmentAddEditModelTypeBinding
    private lateinit var ibDelete: ImageButton
    private lateinit var bDeny: Button
    private lateinit var bSave: Button
    private lateinit var edModelType: EditText
    private lateinit var tvAddModelType: TextView
    private lateinit var cbMale: CheckBox
    private lateinit var cbFemale: CheckBox
    private lateinit var modelTypeScreen: ShoeTypeScreen
    private val modelTypeViewModel by viewModels<ShoeTypesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditModelTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modelTypeScreen = bundle.getSerializable(MODEL_TYPE_SCREEN) as ShoeTypeScreen

        setLayout()
        initUI()
        getModelType()
        setClickListeners()
    }

    private fun getModelType() {
        if(modelTypeScreen == ShoeTypeScreen.EDIT) {
            val modelType = bundle.getParcelable<ShoeTypesList>(MODEL_TYPE)
            val modelTypeName = modelType?.model_type
            val modelTypeGender = modelType?.model_gender
            if(modelTypeGender == "Жіночі") {
                cbFemale.isChecked = true
            }
            else cbMale.isChecked = true
            edModelType.setText(modelTypeName)
            ibDelete.visibility = View.VISIBLE
            bSave.text = "Оновити"
        }
        else {
            ibDelete.visibility = View.GONE
            bSave.text = "Додати"
        }
    }

    private fun setLayout() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun initUI() {
        ibDelete = binding.ibDeleteModelType
        bDeny = binding.bDenyAddEditModelType
        bSave = binding.bSaveModelType
        cbMale = binding.cbMale
        cbFemale = binding.cbFemale
        edModelType = binding.edModelType
        tvAddModelType = binding.tvAddModelType
    }

    private fun setClickListeners() {
        bDeny.setOnClickListener { dismiss() }

        if(modelTypeScreen == ShoeTypeScreen.EDIT) {
            val modelType = bundle.getParcelable<ShoeTypesList>(MODEL_TYPE)
            ibDelete.setOnClickListener {
                modelTypeViewModel.deleteShoeTypeList(modelType!!)
                dismiss()
            }
        }

        bSave.setOnClickListener {
            val modelType = edModelType.text.toString()
            val modelGender: String
            if(modelTypeScreen == ShoeTypeScreen.ADD) {
                if(modelType.isNotEmpty() && (cbMale.isChecked || cbFemale.isChecked)) {
                    if(cbMale.isChecked && !cbFemale.isChecked) {
                        modelGender = cbMale.text.toString()
                        modelTypeViewModel.addShoeTypeList(ShoeTypesList(model_type = modelType, model_gender = modelGender))
                        Toast.makeText(context, "Модель типу $modelType $modelGender було додано", Toast.LENGTH_LONG).show()
                        dismiss()
                    }
                    else if(cbFemale.isChecked && !cbMale.isChecked) {
                        modelGender = cbFemale.text.toString()
                        modelTypeViewModel.addShoeTypeList(ShoeTypesList(model_type = modelType, model_gender = modelGender))
                        Toast.makeText(context, "Модель типу $modelType $modelGender було додано", Toast.LENGTH_LONG).show()
                        dismiss()
                    }
                    else {
                        Toast.makeText(context, "Виберіть один тип моделі!", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(context, "Заповніть всі поля!", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                if (modelType.isNotEmpty() && (cbMale.isChecked || cbFemale.isChecked)) {
                    if(cbMale.isChecked && !cbFemale.isChecked) {
                        modelGender = cbMale.text.toString()
                        modelTypeViewModel.updateShoeModelType(ShoeTypesList(model_type = modelType, model_gender = modelGender))
                        Toast.makeText(context, "Модель типу $modelType $modelGender було оновлено", Toast.LENGTH_LONG).show()
                        dismiss()
                    }
                    else if(cbFemale.isChecked && !cbMale.isChecked) {
                        modelGender = cbFemale.text.toString()
                        modelTypeViewModel.updateShoeModelType(ShoeTypesList(model_type = modelType, model_gender = modelGender))
                        Toast.makeText(context, "Модель типу $modelType $modelGender було оновлено", Toast.LENGTH_LONG).show()
                        dismiss()
                    }
                    else {
                        Toast.makeText(context, "Виберіть один тип моделі!", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(context, "Заповніть всі поля!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    companion object {
        const val ADD_EDIT_DIALOG = "com.otaman.shoestorage.add_edit_dialog"
    }
}