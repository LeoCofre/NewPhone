package cl.awakelab.newphone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.awakelab.newphone.R
import cl.awakelab.newphone.databinding.FragmentListBinding


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    //private val viewModel: phoneViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}