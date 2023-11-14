package app.plinko.masterplay.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.plinko.masterplay.Const

class NavigationViewModel : ViewModel() {

    private val _link: MutableLiveData<String> = MutableLiveData(Const.EMPTY)
    val link: LiveData<String> = _link


    fun addData(data: String){
        _link.value = data
    }
}