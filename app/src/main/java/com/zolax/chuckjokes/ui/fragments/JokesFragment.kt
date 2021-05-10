package com.zolax.chuckjokes.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zolax.chuckjokes.R
import com.zolax.chuckjokes.adapters.JokesAdapter
import com.zolax.chuckjokes.ui.viewmodels.JokesViewModel
import com.zolax.chuckjokes.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_jokes.*

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val jokesViewModel: JokesViewModel by viewModels()
    private lateinit var jokesAdapter: JokesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.isVisible = false
        setHasOptionsMenu(true)
        initAdapter(jokes)
        subscribeObserver()
        initButton()

    }

    private fun initButton() {
        requesButton.setOnClickListener {
            val count = Integer.parseInt(countEditText.text.toString())
            jokesViewModel.getJokes(count)
        }
    }

    private fun initAdapter(rv: RecyclerView) {
        jokesAdapter = JokesAdapter()
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jokesAdapter
        }
    }

    private fun subscribeObserver() {
        jokesViewModel.jokesData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    progressBar.isVisible = false
                    result.data?.let {
                        jokesAdapter.jokes = it.value!!
                    }

                }
                is Resource.Error -> {
                    progressBar.isVisible = false

                    Snackbar.make(requireView(), "Loading error", Snackbar.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    progressBar.isVisible = true

                }
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = "Jokes"
        }
    }
}