package net.braniumacademy.lesson513.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import net.braniumacademy.lesson513.R
import net.braniumacademy.lesson513.data.model.Country
import net.braniumacademy.lesson513.data.repository.CountryRepositoryImpl
import net.braniumacademy.lesson513.ui.adapter.CountryAdapter
import net.braniumacademy.lesson513.ui.viewmodel.CountryViewModel
import net.braniumacademy.lesson513.ui.viewmodel.CountryViewModelFactory
import net.braniumacademy.lesson513.ui.viewmodel.SharedCountryItemViewModel

class ListCountryFragment : Fragment() {
    private lateinit var rvCountry: RecyclerView
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var sharedCountryViewModel: SharedCountryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        setupViewModels()
    }

    private fun setupRecyclerView(view: View) {
        rvCountry = view.findViewById(R.id.rv_country)
        countryAdapter = CountryAdapter(requireContext(),
            object : CountryAdapter.OnItemClickListener {
                override fun onItemClick(country: Country) {
                    sharedCountryViewModel.setSelectedCountry(country)
                    showDetail()
                }
            })
        rvCountry.adapter = countryAdapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        rvCountry.addItemDecoration(divider)
        rvCountry.setHasFixedSize(true)
    }

    private fun setupViewModels() {
        val repository = CountryRepositoryImpl(requireContext())
        countryViewModel = ViewModelProvider(
            this,
            CountryViewModelFactory(repository)
        )[CountryViewModel::class.java]
        countryViewModel.country.observe(viewLifecycleOwner) {
            countryAdapter.setCountries(it)
        }
        sharedCountryViewModel =
            ViewModelProvider(requireActivity())[SharedCountryItemViewModel::class.java]
    }

    private fun showDetail() {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.fragment_container_view, CountryDetailFragment::class.java, null)
            .addToBackStack(null)
            .commit()
    }
}