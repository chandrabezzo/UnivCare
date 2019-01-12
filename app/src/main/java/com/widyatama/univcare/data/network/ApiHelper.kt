package com.widyatama.coreandroid.data.network

import com.widyatama.core.data.session.SessionHelper
import com.widyatama.core.util.SchedulerProviderUtil
import com.widyatama.coreandroid.data.network.repository.CompanyRepo

/**
 * Created by bezzo on 11/01/18.
 */

class ApiHelper
constructor(val schedulerProvider: SchedulerProviderUtil) {

    lateinit var session : SessionHelper

    var companyRepo = CompanyRepo(schedulerProvider)
}
