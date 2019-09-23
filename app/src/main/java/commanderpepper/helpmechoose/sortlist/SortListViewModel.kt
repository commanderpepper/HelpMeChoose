package commanderpepper.helpmechoose.sortlist

import android.util.Log
import androidx.lifecycle.ViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class SortListViewModel(val hmcListDAO: HMCListDAO,
                        val listId: String) :
        ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private lateinit var listOfValue: List<HMCListsValues>

    init {
        launch(Dispatchers.IO) {
            listOfValue = hmcListDAO.getHMCListsValues(listId)
        }
    }

    private var counter = 0
    private var hasMoreOptions = true

    @ExperimentalCoroutinesApi
    private val intBroadcast = BroadcastChannel<Int>(1).also {
        it.offer(counter)
    }

    @ExperimentalCoroutinesApi
    private val listState = BroadcastChannel<Boolean>(1).also {
        it.offer(hasMoreOptions)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    val optionA = flow {
        intBroadcast.consumeEach {
            emit(listOfValue[it].key1)
        }
    }.flowOn(Dispatchers.Default)
            .catch {
                setOptionsToFalse()
            }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    val optionB = flow {
        intBroadcast.consumeEach {
            emit(listOfValue[it].key2)
        }
    }.flowOn(Dispatchers.Default)
            .catch {
                setOptionsToFalse()
            }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    val listStateFlow = flow {
        listState.consumeEach {
            emit(it)
        }
    }


    @ExperimentalCoroutinesApi
    fun increaseCounter() {
        launch {
            counter = counter.plus(1)
            intBroadcast.offer(counter)
        }
    }

    @ExperimentalCoroutinesApi
    fun setOptionsToFalse() {
        launch {
            hasMoreOptions = false
            listState.offer(hasMoreOptions)
        }
    }

    /**
     * Save the result from the method
     */
    @ExperimentalCoroutinesApi
    fun saveResult(result: String) {
        if (counter < listOfValue.size) {
            launch(Dispatchers.IO) {
                Log.i("Humza", "Counter: $counter")
                Log.i("Humza", "List Size: ${listOfValue.size}")
                val value = listOfValue[counter]
                value.value = result
                hmcListDAO.insertValue(value)
                increaseCounter()
            }
        } else {
            Log.i("Humza", "Option should be false")
            setOptionsToFalse()
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

}