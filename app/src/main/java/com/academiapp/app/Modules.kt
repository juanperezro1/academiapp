package com.academiapp.app

import com.academiapp.content.audits.adapter.AuditsAdapter
import com.academiapp.content.create_pqrs.CreatePQRSVM
import com.academiapp.content.create_pqrs.anonymous.CreatePQRSAnonymousVM
import com.academiapp.content.create_pqrs.no_anonymous.CreatePQRSNoAnonymousVM
import com.academiapp.content.degree_work.adapter.DegreeWorkAdapter
import com.academiapp.content.home.HomeVM
import com.academiapp.content.login.LoginVM
import com.academiapp.content.my_remits.details.PQRSDetailVM
import com.academiapp.content.pqrs.PqrsVM
import com.academiapp.content.pqrs.adapter.PQRSAdapter
import com.academiapp.content.pqrs.edit.EditPqrsVM
import com.academiapp.services.RetrofitClient
import com.academiapp.services.RetrofitConnection
import com.academiapp.services.SharedPreferencesManager
import com.academiapp.widgets.RemitVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {LoginVM(get(),get(),get())}
    viewModel { CreatePQRSVM() }
    viewModel { CreatePQRSAnonymousVM(get(),get()) }
    viewModel { CreatePQRSNoAnonymousVM(get(),get()) }
    viewModel { HomeVM() }
    viewModel { PqrsVM(get(),get(),get()) }
    viewModel { EditPqrsVM(get(),get(),get()) }
    viewModel { PQRSDetailVM(get(),get(),get()) }
    viewModel { RemitVM(get(),get(),get()) }

    factory { PQRSAdapter() }
    factory { AuditsAdapter() }
    factory { DegreeWorkAdapter() }

    single { RetrofitConnection() }
    single { RetrofitClient.createService() }
    single { SharedPreferencesManager(androidContext()) }
}