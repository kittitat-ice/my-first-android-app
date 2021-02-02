package com.example.myfirstapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.myfirstapp.databinding.ProductListScreenBinding
import org.json.JSONArray
import java.text.NumberFormat

class ProductListScreen : Fragment() {

    private lateinit var navController: NavController
    private lateinit var activity: AppCompatActivity
    private var _binding: ProductListScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ProductListScreenBinding.inflate(inflater, container, false)
        activity = (requireActivity() as AppCompatActivity)
        navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
        // set title
        activity.supportActionBar?.title = navController.currentDestination?.label

        val url = "https://jsonplaceholder.typicode.com/users"

        val queue = Volley.newRequestQueue(binding.root.context)

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                binding.productList.adapter = ProductAdapter(response)
                binding.productList.layoutManager = LinearLayoutManager(container?.context)
            },
            { error ->
                Log.d("ERROR", error.toString())
            }
        )

        queue.add(request)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private class ProductAdapter(private val dataSet: JSONArray) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val productName: TextView = view.findViewById(R.id.product_list_product_name)
            val productPrice: TextView = view.findViewById(R.id.product_list_product_price)
            val email: TextView = view.findViewById(R.id.product_list_email)
            val website: TextView = view.findViewById(R.id.product_list_website)
            val clickable: ViewGroup = view.findViewById(R.id.product_list_clickable)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_listitem, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = dataSet.getJSONObject(position)

            holder.productName.text = item.optString("name")
            holder.email.text = item.optString("email")
            holder.website.text = item.optString("website")
            holder.productPrice.text = "฿${NumberFormat.getNumberInstance().format((1000..10000).random())}"

            holder.clickable.setOnClickListener{ view ->
                val action = ProductListScreenDirections.goToProductDetailScreen(item.toString())
                view.findNavController().navigate(action)
            }
        }

        override fun getItemCount() = dataSet.length()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}