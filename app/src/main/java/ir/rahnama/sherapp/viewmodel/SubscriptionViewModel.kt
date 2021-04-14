package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private  val _data = MutableLiveData<Boolean>()
    private val _sub = _data.switchMap { repository.getSubs() }
    val sub = _sub

    fun getSubscription () {
        _data.value= true
    }
}