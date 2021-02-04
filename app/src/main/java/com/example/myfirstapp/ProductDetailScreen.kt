package com.example.myfirstapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myfirstapp.api.product.Address
import com.example.myfirstapp.api.product.Company
import com.example.myfirstapp.api.product.ProductListJsonItem
import com.example.myfirstapp.databinding.ProductDetailScreenBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class ProductDetailScreen : Fragment() {

    private lateinit var navController: NavController
    private lateinit var activity: AppCompatActivity
    private var _binding: ProductDetailScreenBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ProductDetailScreenBinding.inflate(inflater, container, false)
        activity = (requireActivity() as AppCompatActivity)
        navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
        // set title
        activity.supportActionBar?.title = navController.currentDestination?.label

        Picasso.get().load("https://placeimg.com/480/360/people").into(binding.userDetailImage)

        val productDetail = args.productDetail
        val companyInfo: Company = productDetail.company
        val addressInfo: Address = productDetail.address
        binding.userDetailHeaderName.text = productDetail.name
        binding.userDetailEmail.text = productDetail.email
        binding.userDetailWebsite.text = productDetail.website
        binding.userDetailPhone.text = productDetail.phone
        binding.userDetailAddress.text = getString(
            R.string.full_address_string,
            addressInfo.street,
            addressInfo.suite,
            addressInfo.city,
            addressInfo.zipcode
        )

        return binding.root
    }
}