package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.repository.Repository
import javax.inject.Inject

@HiltViewModel
class PosterViewModel @Inject constructor(repository: Repository):ViewModel() {

     val posterModel=repository.getPoster()

}

