package ir.rahnama.sherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import ir.rahnama.sherapp.repository.Repository



class PoemBodyViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {


    private val _id = MutableLiveData<String>()
    private val _poemBody = _id.switchMap { repository.getPoemById(it) }
    val poemBody = _poemBody

    fun getPoemById(poem_id:String){ _id.value=poem_id }


}