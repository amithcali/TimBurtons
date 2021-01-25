package com.tim.burtons.ui.products

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tim.burtons.R
import com.tim.burtons.data.api.Resource
import com.tim.burtons.model.Product
import com.tim.burtons.ui.products.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*


@AndroidEntryPoint
class ProductsFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var adapter: ProductsAdapter
    private lateinit var productsViewModel: ProductsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        productsViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        activity?.actionBar?.hide()
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        rv_products.layoutManager = LinearLayoutManager(context)
        adapter = ProductsAdapter(arrayListOf())
        rv_products.addItemDecoration(
            DividerItemDecoration(
                rv_products.context,
                (rv_products.layoutManager as LinearLayoutManager).orientation
            )
        )
        rv_products.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView =
            searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search Products"
        searchView.setQuery(productsViewModel.searchText, false)
    }


    private fun setupObservers() {
        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
                it?.let { products -> updateAdapter(products) }
        })

        productsViewModel.getError().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        productsViewModel.isLoading().observe(viewLifecycleOwner, Observer {
            if (it)  { progressBar.visibility = View.VISIBLE } else { progressBar.visibility = View.GONE }
        })

    }

    private fun updateAdapter(products: List<Product>) {
        adapter.apply {
            addUsers(products)
            notifyDataSetChanged()
        }
    }

    override fun onQueryTextSubmit(s: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(s: String?): Boolean {
        productsViewModel.searchText = s
        return false
    }
}
