package com.tanhoang.com.model.request

import com.tanhoang.com.model.employee.Role
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
    private val creationTime: Long = Date().time

    private var status: RequestStatus = RequestStatus.NOT_HANDLED

    fun getId() = id

    fun setNextProcessRole() {
        nextProcessesRole = Role.fromInt(nextProcessesRole.role + 1)
    }

    fun isBeingHandled() = status == RequestStatus.HANDLING
    fun hasCompleted() = status == RequestStatus.HANDLED

    fun completeCall(success: Boolean) {
        status = if (success) RequestStatus.HANDLED else RequestStatus.NOT_HANDLED
    }

    fun setRequestStatus(newStatus: RequestStatus) {
        status = newStatus
    }
}
