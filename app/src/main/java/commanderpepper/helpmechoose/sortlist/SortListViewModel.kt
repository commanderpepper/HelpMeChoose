package commanderpepper.helpmechoose.sortlist

import android.util.Log
import androidx.lifecycle.ViewModel
import commanderpepper.helpmechoose.data.Room.HMCListDAO
import commanderpepper.helpmechoose.data.model.HMCListsValues
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import java.lang.Exception
import kotlin.properties.Delegates

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
    private val intBroadcast = BroadcastChannel<Int>(100).also {
        it.trySend(counter).isSuccess
    }

    @ExperimentalCoroutinesApi
    private val listState = BroadcastChannel<Boolean>(1).also {
        it.trySend(hasMoreOptions).isSuccess
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    val optionA = flow {
        counterFlowFun().collect {
            emit(listOfValue[it].key1)
        }
    }.flowOn(Dispatchers.Default)
            .catch {
                setOptionsToFalse()
            }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    val optionB = flow {
        counterFlowFun().collect {
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
            counter++
        }
    }

    fun counterFlowFun(): Flow<Int> =
            flow { emit(counter) }

    @ExperimentalCoroutinesApi
    fun setOptionsToFalse() {
        launch {
            hasMoreOptions = false
            listState.trySend(hasMoreOptions).isSuccess
        }
    }

    /**
     * Save the result from the method
     */
    @ExperimentalCoroutinesApi
    fun saveResult(result: String) {
        if (counter < listOfValue.size) {
            launch(Dispatchers.IO) {
                if (counter < listOfValue.size) {
                    Log.i("Humza", "Counter: $counter")
                    Log.i("Humza", "Counter: ${listOfValue[counter]}")
                    Log.i("Humza", "List Size: ${listOfValue.size}")
                    val value = listOfValue[counter]
                    value.value = result
                    hmcListDAO.insertValue(value)
                }
            }
            increaseCounter()
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
