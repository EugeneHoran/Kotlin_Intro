package eugene.com.kotlininro.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class FragmentMainViewModel : ViewModel() {

    val mutableLiveData = MutableLiveData<String>()
    init {
        mutableLiveData.value = "Hello World"

    }

}