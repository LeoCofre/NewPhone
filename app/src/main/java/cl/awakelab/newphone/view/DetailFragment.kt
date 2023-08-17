package cl.awakelab.newphone.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
                binding.txtPriceDetalle.text = "AHORA $ " + it.price
                binding.txtLastPriceDetail.text = "ANTES $ " + it.lastPrice
                binding.txtDescription.text = "DESCRIPCIÓN \n" + it.description
                if (!it.credit){
                    binding.txtCreditoDetalle.text ="SOLO EFECTIVO"
                }else{
                    binding.txtCreditoDetalle.text = "CREDITO"
                }
            }
        }

    }
    private fun initListeners() {
        viewModel.phoneDetailLiveData(param1.toString().toLong())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val asunto = "Consulta ${it.name} id ${it.id}"
                    val message =
                        "Hola, \nVi este teléfono ${it.name} de código ${it.id} y me gustaría que me contactaran a este correo o al siguiente número ____________. \nQuedo atento."

                    binding.floatingActionButton.setOnClickListener {
                        val mail = "info@prueba.cl"
                        val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(mail))
                        intentMail.type = "text/plain"
                        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                        intentMail.putExtra(Intent.EXTRA_SUBJECT, asunto)
                        intentMail.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(intentMail, "Send Mail"))
                    }
                }
            }
    }


}