package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject


@HiltViewModel
class PoemBodyViewModel @Inject constructor(val repository: Repository) : ViewModel() {


    private val _id = MutableLiveData<Int>()
    private val _poemBody = _id.switchMap { repository.getPoemById(it) }
    val poemBody = _poemBody

    fun getPoemById(poem_id:Int){ _id.value=poem_id }

}