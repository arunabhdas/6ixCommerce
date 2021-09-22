package com.arunabhdas.dascommerce.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arunabhdas.dascommerce.model.Product
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

const val ENDPOINT_URL_HTTP = "https://mountshastaplatform.herokuapp.com/api/products/"

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()

    fun fetchAllProductDetails(productTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val json =
                URL(ENDPOINT_URL_HTTP).readText()
            val list = Gson().fromJson(json, Array<Product>::class.java).toList()
            val product = list.first { it.title == productTitle }

            productDetails.postValue(product)
        }
    }
}
