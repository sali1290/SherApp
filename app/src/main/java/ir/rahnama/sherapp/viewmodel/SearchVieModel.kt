package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class SearchVieModel @Inject constructor(val repository: Repository):ViewModel() {

    private val _text = MutableLiveData<String>()
    private val _pos = MutableLiveData<Int>()

    fun getSearchResult(text:String,position:Int){
        _text.value=text
        _pos.value=position
    }

}