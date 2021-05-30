package com.example.readdletestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.readdletestapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment () {
    val args: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Picasso.with(ivDetailed.context).load(args.item.imageUrl).into(ivDetailed)
            tvDetailedTitle.text = args.item.text
            tvEmail.text = args.item.email
            tvStatus.text = if (args.item.status) "Online" else "Offline"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}