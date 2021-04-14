package ir.rahnama.sherapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _id = MutableLiveData<String>()
    private val _books = _id.switchMap { repository.getBooksList(it) }
    val books = _books

    fun getBooksList(category_id:String){_id.value=category_id}

}