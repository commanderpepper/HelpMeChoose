package commanderpepper.helpmechoose.database.module

import androidx.room.Room
import commanderpepper.helpmechoose.database.HMCListDataSource
import commanderpepper.helpmechoose.database.HMCListDataSourceImpl
import commanderpepper.helpmechoose.database.room.HMCListDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val HMCDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            HMCListDatabase::class.java,
            "database.db"
        ).build()
    }

    single { get<HMCListDatabase>().hmcDao() }

    single<HMCListDataSource> { HMCListDataSourceImpl(hmcListDAO = get()) }
}