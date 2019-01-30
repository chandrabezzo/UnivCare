package com.widyatama.univcare.features.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widyatama.core.base.BaseActivity
import com.widyatama.univcare.R
import com.widyatama.univcare.constanta.ApiConstans
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        val url = dataReceived?.getString(ApiConstans.DATA)
        if (url != null){
            webview.settings.javaScriptEnabled = true
            webview.loadUrl(url)
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_web_view
    }
}
