package ir.rahnama.sherapp.viewmodel


import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class PoetryViewModel @Inject constructor(var repository: Repository): ViewModel() {


    private val _id = MutableLiveData<Int>()
    private val _poetryNames = _id.switchMap { repository.getPoetryNames(it) }
    val poetryNames = _poetryNames

    fun getPoetryNames(category_id:Int) { _id.value = category_id }


     val selectionPoetry = repository.getSelectionPoetry()


    private val _lastSeenId = MutableLiveData<Int>()
    val lastPoem = _lastSeenId.switchMap { repository.getLastSeenPoem(it) }
    //val lastPoem = _lastPoem
    fun getLastSeenPoem(poem_id:Int) {_lastSeenId.value=poem_id}


}