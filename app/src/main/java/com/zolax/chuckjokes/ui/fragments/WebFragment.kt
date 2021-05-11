package com.zolax.chuckjokes.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zolax.chuckjokes.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_web.*


@AndroidEntryPoint
class WebFragment : Fragment(R.layout.fragment_web) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        startWebView(webView,savedInstanceState)
        
        requireActivity().onBackPressedDispatcher.addCallback{
            if (webView.canGoBack()){
                webView.goBack()
            } else{
                findNavController().popBackStack()
            }
        }

    }

    private fun startWebView(webView: WebView, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl("https://www.icndb.com/api/")
            }
        } else {
            webView.restoreState(savedInstanceState)
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        (requireActivity() as AppCompatActivity).supportActionBar?.let {
            it.title = "Api info"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
    }




}