package cl.awakelab.newphone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.awakelab.newphone.databinding.ItemListBinding
import cl.awakelab.newphone.model.data.local.list.PhoneEntity
import coil.load

class AdapterList: RecyclerView.Adapter<AdapterList.ViewHolder>() {

    private lateinit var binding : ItemListBinding
    private val listItemPhone = mutableListOf<PhoneEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemPhone.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phone = listItemPhone[position]
        holder.bind(phone)
    }

    class ViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(phone: PhoneEntity) {
        val bundle = Bundle()

            binding.imgItem.load(phone.image)
            binding.textname.text = phone.name.toString()
            binding.textPrice.text = phone.price.toString()
            binding.cardViewItem.setOnClickListener {

            }

        }

    }
}