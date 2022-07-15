package com.tanhoang.com.model.employee

import android.util.Log
import com.tanhoang.com.handler.CallHandler
import com.tanhoang.com.model.request.CallRequest
import java.util.*

class Employee(
    private val id: String,
    private val name: String,
    private val role: Role
) : CallHandler {
    private var status: EmployeeStatus = EmployeeStatus.FREE

    fun getId() = id
    fun getRole() = role

    override fun canHandle(currentLevel: Role): Boolean {
        return (role.role == currentLevel.role) && isFree()
    }

    override fun disPatchCall(request: CallRequest) {
        status = EmployeeStatus.BUSY
        sleep(200L)

        if (role.role < request.targetRole.role) {
            Log.d("TDebug", "[request ${request.getId()}-${request.targetRole.name}]: can not handled by Employee $id (${role.name})")
            request.setNextProcessRole()
        } else {
            Log.d("TDebug", "[request ${request.getId()}-${request.targetRole.name}]: is handled SUCCESSFULLY by Employee $id (${role.name})")
            request.completeCall(success = true)
        }
        status = EmployeeStatus.FREE
    }

    private fun isFree(): Boolean = status == EmployeeStatus.FREE

    private fun sleep(duration: Long) {
        val startTime = Date().time
        var now = Date().time
        while (now - startTime < duration)
            now = Date().time
    }
}

