package commanderpepper.helpmechoose.util

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

/**
 * Equivalent to [launch] but return [Unit] instead of [Job].
 *
 * Mainly for usage when you want to lift [launch] to return. Example:
 *
 * ```
 * override fun loadData() = launchSilent {
 *     // code
 * }
 * ```
 */
fun launchCoroutine(
        context: CoroutineContext = Dispatchers.Default,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        parent: Job? = null,
        block: suspend CoroutineScope.() -> Unit
) {
    GlobalScope.launch(context, start) { block }
}

fun asyncCoroutine(
        context: CoroutineContext = Dispatchers.Default,
        start: CoroutineStart = CoroutineStart.DEFAULT,
//        parent: Job? = null,
        block: suspend CoroutineScope.() -> Unit
) {
    GlobalScope.async (context, start){ block }
}

/**
 * Equivalent to [runBlocking] but return [Unit] instead of [T].
 *
 * Mainly for usage when you want to lift [runBlocking] to return. Example:
 *
 * ```
 * override fun loadData() = runBlockingSilent {
 *     // code
 * }
 * ```
 */
fun <T> runBlockingSilent(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T) {
    runBlocking(context, block)
}