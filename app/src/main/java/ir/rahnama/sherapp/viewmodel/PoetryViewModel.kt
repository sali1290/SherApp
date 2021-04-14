package ir.rahnama.sherapp.viewmodel


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class PoetryViewModel @Inject constructor(var repository: Repository): ViewModel() {


    private val _id = MutableLiveData<String>()
    private val _poetryNames = _id.switchMap { repository.getPoetryNames(it) }
    val poetryNames = _poetryNames

    fun getPoetryNames(category_id:String) { _id.value = category_id }


     val selectionPoetry = repository.getSelectionPoetry()


    private val _lastSeenId = MutableLiveData<String>()
    val lastPoem = _lastSeenId.switchMap { repository.getLastSeenPoem(it) }
    //val lastPoem = _lastPoem
    fun getLastSeenPoem(poem_id:String) {_lastSeenId.value=poem_id}


}