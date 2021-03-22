package ir.rahnama.sherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import ir.rahnama.sherapp.repository.Repository

class SubscriptionViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

    private  val _data = MutableLiveData<Boolean>()
    private val _sub = _data.switchMap { repository.getSubs() }
    val sub = _sub

    fun getSubscription () {
        _data.value= true
    }

}