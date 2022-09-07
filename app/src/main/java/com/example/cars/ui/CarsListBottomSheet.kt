package com.example.cars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.cars.databinding.BottomSheetCarListBinding
import com.example.cars.model.CarView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_car_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarsListBottomSheet(
    private val carListCallBack: CarListCallBack,
    private val dialogDismissCallback: DialogDismissCallback
) :
    BottomSheetDialogFragment() {
    private val vm: CarsViewModel by viewModels()
    private lateinit var binding: BottomSheetCarListBinding
    private lateinit var carsListAdapter: CarsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCarListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        showList()
    }

    private fun setupRecyclerView() {
        carsListAdapter = CarsListAdapter { adapterOnClick(it) }
        rv_cars.adapter = carsListAdapter
        rv_cars.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun adapterOnClick(carView: CarView) {
        this.dismiss()
        carListCallBack.onCarClicked(carView)
    }


    private fun showList() {
        lifecycleScope.launch(Dispatchers.Main) {
            vm.myList.observe(this@CarsListBottomSheet) {
                carsListAdapter.submitList(it)
            }

            vm.showLoading.observe(this@CarsListBottomSheet) {
                pb_waiting.visibility = if (it) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            }
            vm.error.observe(viewLifecycleOwner) {
                it?.let {
                    this@CarsListBottomSheet.dismiss()
                    dialogDismissCallback.onError(it.error)
                }
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}