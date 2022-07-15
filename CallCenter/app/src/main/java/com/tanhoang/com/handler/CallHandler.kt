package com.tanhoang.com.handler

import com.tanhoang.com.model.employee.Role
import com.tanhoang.com.model.request.CallRequest

interface CallHandler {
    fun canHandle(requestLevel: Role): Boolean
    fun disPatchCall(callRequest: CallRequest)
}