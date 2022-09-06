package com.example.cars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.cars.R
import com.example.cars.model.CarView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_car_list.*
import kotlinx.android.synthetic.main.bottom_sheet_car_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarsListBottomSheet(private val carListCallBack: CarListCallBack): BottomSheetDialogFragment() {
    private val vm: CarsViewModel by viewModels()
    private lateinit var carsListAdapter: CarsListAdapter
    lateinit var myView :View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView = inflater.inflate(R.layout.bottom_sheet_car_list, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        showList()
    }

    private fun setupRecyclerView() {
        carsListAdapter = CarsListAdapter { adapterOnClick(it) }
        myView.rv_cars.adapter = carsListAdapter
        myView.rv_cars.addItemDecoration(
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
        lifecycleScope.launch(Dispatchers.Main){
            vm.myList.observe(this@CarsListBottomSheet) {
                carsListAdapter.submitList(it)
            }
            vm.showLoading.observe(this@CarsListBottomSheet) {
                if (it) {
                    pb_waiting.visibility = View.VISIBLE
                } else {
                    pb_waiting.visibility = View.INVISIBLE
                }
            }
            vm.error.collect {
                val message = it.error.message
                if (message?.isNotBlank() == true) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}