package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class BuyNegareViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _data = MutableLiveData<Boolean>()
    private val _negare = _data.switchMap { repository.getNegar() }
    val negare = _negare


    fun getNegare(){
        _data.value=true
    }
}