package com.tanhoang.com.callcenter.handler

import com.tanhoang.com.callcenter.model.employee.Role
import com.tanhoang.com.callcenter.model.request.CallRequest

interface CallHandler {
    fun canReceiveCall(processLevel: Role): Boolean
    fun canHandleCall(targetLevel: Role): Boolean
    fun processCall(callRequest: CallRequest): Boolean
}