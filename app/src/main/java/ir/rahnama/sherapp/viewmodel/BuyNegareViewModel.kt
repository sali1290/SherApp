package ir.rahnama.sherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import ir.rahnama.sherapp.model.Negare
import ir.rahnama.sherapp.repository.Repository

class BuyNegareViewModel @ViewModelInject constructor( val repository: Repository) : ViewModel() {

    private val _data = MutableLiveData<Boolean>()
    private val _negare = _data.switchMap { repository.getNegar() }
    val negare = _negare


    fun getNegare(){
        _data.value=true
    }
}