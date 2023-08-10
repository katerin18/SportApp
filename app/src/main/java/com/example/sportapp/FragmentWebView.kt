package com.example.sportapp

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.sportapp.databinding.FragmentWebViewBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

open class FragmentWebView : Fragment() {
    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 1234
    private var arg = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater)
        arg = arguments?.getString("url").toString()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = binding.webView
        val webSettings = webView.settings
        val cookieManager = CookieManager.getInstance()

        webSettings.javaScriptEnabled = true
        cookieManager.setAcceptCookie(true)

        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.loadUrl(arg)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webView.canGoBack()) {
                        webView.goBack()
                    } else {
                        requireActivity().finish()
                    }
                }
            })

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                webView.loadUrl(request?.url?.toString() ?: "")
                return true
            }
        }

        webView.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                mFilePathCallback = filePathCallback
                takePhoto()

                return true
            }

            private fun takePhoto() {
                //Adjust the camera in a way that specifies the storage location for taking pictures
                val photoFile: File?
                val authorities: String = requireActivity().packageName + ".provider"
                try {
                    photoFile = createImageFile()
                    imageUri = FileProvider.getUriForFile(requireActivity(), authorities, photoFile)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                val Photo = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                val chooserIntent = Intent.createChooser(Photo, "Image Chooser")
                chooserIntent.putExtra(
                    Intent.EXTRA_INITIAL_INTENTS,
                    arrayOf<Parcelable>(captureIntent)
                )
                startActivityForResult(chooserIntent, REQUEST_CODE)
            }

            @SuppressLint("SimpleDateFormat")
            @Throws(IOException::class)
            private fun createImageFile(): File {
                val imageStorageDir = File(
                    Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                    ), "AndroidExampleFolder"
                )
                if (!imageStorageDir.exists()) {
                    // Create AndroidExampleFolder at sdcard
                    imageStorageDir.mkdirs()
                }

                val imageFileName = "JPEG_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                return File(imageStorageDir, File.separator + imageFileName + ".jpg")
            }
        }

        val mWebSettings = webView.settings
        mWebSettings.javaScriptEnabled = true
        mWebSettings.javaScriptCanOpenWindowsAutomatically = true
        mWebSettings.loadWithOverviewMode = true
        mWebSettings.domStorageEnabled = true
        mWebSettings.databaseEnabled = true
        mWebSettings.setSupportZoom(false)
        mWebSettings.allowFileAccess = true
        mWebSettings.allowContentAccess = true
        mWebSettings.useWideViewPort = true
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            val uri = data?.data ?: return
            mFilePathCallback?.onReceiveValue(arrayOf(uri))
            mFilePathCallback = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}