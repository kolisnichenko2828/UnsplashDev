package com.staskokoc.unsplashdev.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.staskokoc.unsplashdev.R
import com.staskokoc.unsplashdev.databinding.FragmentFullscreenImageBinding

class FullscreenImage : Fragment() {
    private lateinit var binding: FragmentFullscreenImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullscreenImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val args: FullscreenImageArgs by navArgs()
        val url = args.url

        binding.imageView.load(data = url) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_background)
        }

        binding.imageView.setOnClickListener() {
            findNavController().popBackStack()
        }
    }
}