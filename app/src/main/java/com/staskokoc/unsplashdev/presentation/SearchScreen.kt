package com.staskokoc.unsplashdev.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staskokoc.unsplashdev.R
import com.staskokoc.unsplashdev.databinding.FragmentSearchScreenBinding
import com.staskokoc.unsplashdev.domain.models.UnsplashImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchScreen : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentSearchScreenBinding
    private val vm: MainViewModel by viewModel()
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        vm.getImages(q = vm.lastSearchLd.value ?: "hello")
        scrollToLastPosition()

        binding.textInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() && s[s.length - 1] == '\n' && s[0] != ' ' && s[0] != '\n') {
                    val searchText = binding.textInput.text.toString()
                    binding.textInput.setText(searchText.substring(0, searchText.length - 1))
                    vm.getImages(q = searchText)
                } else if(s.isNotEmpty() && (s[0] == ' ' || s[0] == '\n')) {
                    binding.textInput.text?.clear()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        binding.buttonSearch.setOnClickListener {
            if(binding.textInput.text.toString().isNotEmpty()) {
                val searchText = binding.textInput.text.toString()
                vm.getImages(q = searchText)
            }
        }

        vm.imagesLd.observe(this) {
            clearFocusAndHideKeyboard()
            adapter.submitList((vm.imagesLd.value as MutableList<UnsplashImage>))
        }
    }

    private fun initRecyclerView() {
        recyclerview = binding.rvList
        adapter = ImagesAdapter(listener = this)
        recyclerview.layoutManager = GridLayoutManager(context, 3)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(RecyclerViewDecorator())
    }

    override fun onItemClick(url: String, position: Int) {
        vm.saveLastPosition(position)
        val action = SearchScreenDirections.actionSearchScreenToFullscreenImage(url)
        findNavController().navigate(action)
    }

    private fun clearFocusAndHideKeyboard() {
        binding.textInput.clearFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun scrollToLastPosition() {
        recyclerview.scrollToPosition(vm.lastPositionLd.value ?: 0)
    }
}
