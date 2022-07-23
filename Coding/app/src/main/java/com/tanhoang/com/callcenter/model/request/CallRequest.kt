package com.tanhoang.com.callcenter.model.request

import com.tanhoang.com.callcenter.model.employee.Role
import java.util.*

/*
    The respondent must be process the message first before decide to forward it upward to the higher level
 */
class CallRequest(
    private val id: String,
    private val message: String,
    private val targetRole: Role = Role.RESPONDENT,
    private var processesRole: Role = Role.RESPONDENT,
) {
    private var status: RequestStatus = RequestStatus.NOT_HANDLED

    private val processedEmployees = Vector<String>()

    fun getId() = id
    fun getTargetRole() = targetRole
    fun getProcessesRole() = processesRole
    fun setProcessesRole(newRole: Role) { processesRole = newRole }

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
        processesRole = Role.fromInt(processesRole.role + 1)
    }

    fun printLog() {
        val employeePath = processedEmployees.toString()
        System.out.println("[request $id-${targetRole.name}]: is handled SUCCESSFULLY by ${processedEmployees.size} employee ids $employeePath")
    }
}
