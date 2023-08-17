package cl.awakelab.newphone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.awakelab.newphone.R
import cl.awakelab.newphone.databinding.FragmentListBinding
import cl.awakelab.newphone.viewmodel.PhoneViewModel


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private val viewModel: PhoneViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        viewModel.getPhoneViewModel()
        val adapter = AdapterList()
        binding.rvList.adapter = adapter
        viewModel.phonesLiveData().observe(viewLifecycleOwner){
        adapter.setData(it)
        }
    }
}