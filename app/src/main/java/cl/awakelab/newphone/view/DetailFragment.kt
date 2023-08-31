package cl.awakelab.newphone.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import cl.awakelab.newphone.R
import cl.awakelab.newphone.databinding.FragmentDetailBinding
import cl.awakelab.newphone.viewmodel.PhoneViewModel
import coil.load


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val viewModel: PhoneViewModel by activityViewModels()

    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("id")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        initComponents()
        initListeners()
        return binding.root
    }

    private fun initComponents() {
        viewModel.getPhoneDetailsViewModel(param1.toString().toLong())
        viewModel.phoneDetailLiveData(param1.toString().toLong()).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imgDetail.load(it.image)
                binding.txtNombreDetail.text = it.name
                binding.txtPriceDetalle.text = getString(R.string.ahora) + it.price
                binding.txtLastPriceDetail.text = getString(R.string.antes) + it.lastPrice
                binding.txtDescription.text = getString(R.string.descripcion) + it.description
                if (!it.credit) {
                    binding.txtCreditoDetalle.text = getString(R.string.solo_efectivo)
                } else {
                    binding.txtCreditoDetalle.text = getString(R.string.credito)
                }
            }
        }

    }

    private fun initListeners() {
        viewModel.phoneDetailLiveData(param1.toString().toLong())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val asunto = getString(R.string.consulta_asunto, it.name, it.id)
                    val message = getString(R.string.consulta_message, it.name, it.id)

                    binding.floatingActionButton.setOnClickListener {
                        val mail = getString(R.string.info_email)
                        val intentMail = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$mail"))
                        intentMail.putExtra(Intent.EXTRA_SUBJECT, asunto)
                        intentMail.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(intentMail, getString(R.string.send_mail_chooser_title)))
                    }
                }
            }
    }



}