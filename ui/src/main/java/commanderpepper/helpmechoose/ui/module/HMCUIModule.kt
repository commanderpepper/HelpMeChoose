package commanderpepper.helpmechoose.ui.module

import commanderpepper.helpmechoose.ui.detail.HMCDetailViewModel
import commanderpepper.helpmechoose.ui.home.HMCHomeViewModel
import commanderpepper.helpmechoose.ui.newlist.HMCNewListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HMCUIModule = module {
    viewModel { HMCHomeViewModel(hmcListDataSource = get()) }
    viewModel { HMCNewListViewModel(hmcListDataSource = get()) }
    viewModel { HMCDetailViewModel(savedStateHandle = get(), hmcListDataSource = get()) }
}