package com.example.climby.ui.publish.viewmodel


import android.app.Activity
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climby.R
import com.example.climby.data.model.trip.TripModel
import com.example.climby.data.model.user.UserModel
import com.example.climby.domain.province.GetAllProvinces
import com.example.climby.domain.trip.Insert
import com.example.climby.domain.type.Get
import com.example.climby.utils.Commons
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublishViewModel @Inject constructor(private val getAllProvinces: GetAllProvinces, private val getAllTypes: Get, private val insert: Insert) : ViewModel() {

    var provincesModel = MutableLiveData<List<String>>()
    var typesModel = MutableLiveData<List<String>>()
    var tripCreated = MutableLiveData<Boolean>()

    fun getProvince() {
        viewModelScope.launch {
            val result = getAllProvinces()
            val resultName: MutableList<String> = arrayListOf()
            result.forEach {
                it.name?.let { it1 -> resultName.add(it1) }
            }
            if (!result.isNullOrEmpty())
                provincesModel.postValue(resultName)
        }
    }

    fun saveTrip(tripModel: TripModel) {
        viewModelScope.launch {
            val result: TripModel = insert(tripModel)
            tripCreated.postValue(true)
            /* saveTripOnFireBase(result)*/
        }
    }

    /*private fun saveTripOnFireBase(result: TripModel) {


     }*/

    fun getTypes() {
        viewModelScope.launch {
            val result = getAllTypes()
            val resultName: MutableList<String> = arrayListOf()
            result.forEach {
                it.name?.let { it1 -> resultName.add(it1) }
            }
            if (!result.isNullOrEmpty())
                typesModel.postValue(resultName)
        }
    }
}