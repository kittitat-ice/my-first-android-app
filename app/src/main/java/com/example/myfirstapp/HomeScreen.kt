package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.myfirstapp.databinding.HomeScreenBinding
import com.squareup.picasso.Picasso

class HomeScreen : Fragment() {

    private var _binding: HomeScreenBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        //val view = inflater.inflate(R.layout.home_screen, container, false)

        Picasso.get().load("https://placeimg.com/640/480/people").into(binding.homeUserImage)

        binding.listButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.goToListScreen)
        }

        // set title
        val activity = (requireActivity() as AppCompatActivity)
        val navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
        activity.supportActionBar?.title = navController.currentDestination?.label

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}