package net.braniumacademy.lesson513.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import net.braniumacademy.lesson513.R
import net.braniumacademy.lesson513.data.model.Country
import net.braniumacademy.lesson513.data.repository.Utils
import net.braniumacademy.lesson513.ui.viewmodel.SharedCountryItemViewModel

class CountryDetailFragment : Fragment() {
    private lateinit var imageFlag: ImageView
    private lateinit var tvCountry: TextView
    private lateinit var tvCapital: TextView
    private lateinit var tvPopulation: TextView
    private lateinit var tvArea: TextView
    private lateinit var tvDensity: TextView
    private lateinit var tvWorldShare: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        fillData()
    }

    private fun setupViews(view: View) {
        imageFlag = view.findViewById(R.id.image_flag_detail)
        tvCountry = view.findViewById(R.id.tv_nation_detail)
        tvCapital = view.findViewById(R.id.tv_capital_detail)
        tvPopulation = view.findViewById(R.id.tv_population_detail)
        tvDensity = view.findViewById(R.id.tv_density_detail)
        tvWorldShare = view.findViewById(R.id.tv_world_share_detail)
        tvArea = view.findViewById(R.id.tv_erea_detail)
        val imageBack = view.findViewById<ImageView>(R.id.image_back)
        imageBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun fillData() {
        val viewModel =
            ViewModelProvider(requireActivity())[SharedCountryItemViewModel::class.java]
        viewModel.selectedCountry.observe(viewLifecycleOwner) {
            showDetailCountry(it)
        }
    }

    private fun showDetailCountry(country: Country) {
        val area = "${getString(R.string.text_area, country.area)} ${getString(R.string.text_km2)}"
        val density =
            "${getString(R.string.text_density, country.density)} ${getString(R.string.text_ppa)}"
        val worldShare = getString(R.string.text_world_share, country.worldShare)
        val population = "${
            getString(
                R.string.text_population,
                country.population
            )
        } ${getString(R.string.text_million)}"
        tvCountry.text = getString(R.string.text_nation, country.name)
        tvCapital.text = getString(R.string.text_capital, country.capital)
        tvPopulation.text = population
        tvArea.text = area
        tvDensity.text = density
        tvWorldShare.text = worldShare
        Glide.with(requireContext())
            .load(Utils.getDrawableId(requireContext(), country.flag))
            .into(imageFlag)
    }
}