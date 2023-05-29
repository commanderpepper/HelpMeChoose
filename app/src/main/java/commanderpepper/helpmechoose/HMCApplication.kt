package commanderpepper.helpmechoose

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HMCApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Init Koin
        startKoin {
            androidContext(this@HMCApplication)
        }
    }
}