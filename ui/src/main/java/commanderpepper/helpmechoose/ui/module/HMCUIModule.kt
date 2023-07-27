package commanderpepper.helpmechoose.ui.module

import commanderpepper.helpmechoose.ui.home.HMCHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  HMCUIModule = module {
    viewModel { HMCHomeViewModel() }
}