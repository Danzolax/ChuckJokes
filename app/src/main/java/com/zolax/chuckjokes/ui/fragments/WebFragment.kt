package com.zolax.chuckjokes.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zolax.chuckjokes.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_web.*


@AndroidEntryPoint
class WebFragment : Fragment(R.layout.fragment_web) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState == null) {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl("http://www.icndb.com/api/")
            }
        } else {
           webView.restoreState(savedInstanceState)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = "Api info"
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (webView.canGoBack()){
                    webView.goBack()
                } else{
                    Snackbar.make(requireView(),"You can't go back", Snackbar.LENGTH_LONG).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}