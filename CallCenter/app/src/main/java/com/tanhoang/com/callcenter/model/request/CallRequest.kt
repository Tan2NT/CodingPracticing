package com.tanhoang.com.callcenter.model.request

import android.util.Log
import com.tanhoang.com.callcenter.model.employee.Role
import java.util.*

/*
    The respondent must be process the message first before decide to forward it upward to the higher level
 */
class CallRequest(
    private val id: String,
    private val message: String,
    val targetRole: Role = Role.RESPONDENT,
    var nextProcessesRole: Role = Role.RESPONDENT,
) {
    private var status: RequestStatus = RequestStatus.NOT_HANDLED

    private val processedEmployees = Vector<String>()

    fun getId() = id

    fun completeCall(success: Boolean, employeeId: String) {
        if (success) {
            setRequestStatus(RequestStatus.COMPLETED)
        } else {
            setNextProcessRole()
            setRequestStatus(RequestStatus.NOT_HANDLED)
        }
        processedEmployees.add(employeeId)
    }

    fun setRequestStatus(newStatus: RequestStatus) {
        status = newStatus
    }

    private fun setNextProcessRole() {
        nextProcessesRole = Role.fromInt(nextProcessesRole.role + 1)
    }

    fun printLog() {
        val employeePath = processedEmployees.toString()
        Log.d("TDebug", "[request ${id}-${targetRole.name}]: is handled SUCCESSFULLY by ${processedEmployees.size} employee ids $employeePath")
        //println("T[request ${id}-${targetRole.name}]: is handled SUCCESSFULLY by employee id $employeePath")
    }
}
