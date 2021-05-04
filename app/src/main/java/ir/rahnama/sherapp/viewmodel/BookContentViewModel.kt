package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject


@HiltViewModel
class BookContentViewModel @Inject constructor(val repository: Repository) : ViewModel() {


    private val _id = MutableLiveData<Int>()
    private val _bookContent = _id.switchMap { repository.getBooksContent(it) }
    val bookContent = _bookContent

    fun getBookContent(category_id:Int){_id.value=category_id}


}