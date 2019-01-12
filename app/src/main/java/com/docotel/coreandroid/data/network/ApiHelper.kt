package com.docotel.coreandroid.data.network

import com.docotel.core.data.session.SessionHelper
import com.docotel.core.util.SchedulerProviderUtil
import com.docotel.coreandroid.data.network.repository.CompanyRepo

/**
 * Created by bezzo on 11/01/18.
 */

class ApiHelper
constructor(val schedulerProvider: SchedulerProviderUtil) {

    lateinit var session : SessionHelper

    var companyRepo = CompanyRepo(schedulerProvider)
}
