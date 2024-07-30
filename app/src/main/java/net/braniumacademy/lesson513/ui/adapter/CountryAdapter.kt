package net.braniumacademy.lesson513.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.braniumacademy.lesson513.R
import net.braniumacademy.lesson513.data.model.Country
import net.braniumacademy.lesson513.data.repository.Utils.getDrawableId

class CountryAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private val countries: MutableList<Country> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setCountries(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position], listener)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageFlag: ImageView
        private val textCountry: TextView
        private val textCapital: TextView

        init {
            imageFlag = itemView.findViewById(R.id.image_flag)
            textCountry = itemView.findViewById(R.id.text_name)
            textCapital = itemView.findViewById(R.id.text_capital)
        }

        fun bind(country: Country, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onItemClick(country) }
            textCapital.text = country.capital
            textCountry.text = country.name
            setImage(country)
        }

        private fun setImage(country: Country) {
            Glide.with(itemView)
                .load(getDrawableId(itemView.context, country.flag))
                .into(imageFlag)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(country: Country)
    }
}