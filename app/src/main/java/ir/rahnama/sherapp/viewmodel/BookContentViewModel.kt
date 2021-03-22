package ir.rahnama.sherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import ir.rahnama.sherapp.repository.Repository


class BookContentViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {


    private val _id = MutableLiveData<String>()
    private val _bookContent = _id.switchMap { repository.getBooksContent(it) }
    val bookContent = _bookContent

    fun getBookContent(category_id:String){_id.value=category_id}


}