package com.tanhoang.com.callcenter.model.employee

import com.tanhoang.com.callcenter.handler.CallHandler
import com.tanhoang.com.callcenter.model.request.CallRequest
import com.tanhoang.com.callcenter.model.request.RequestStatus
import com.tanhoang.com.utils.Utils.sleep

const val CALL_DURATION = 500L

class Employee(
    private val id: String,
    private val name: String,
    private val role: Role
) : CallHandler {
    private var status: EmployeeStatus = EmployeeStatus.FREE

    fun getId() = id
    fun getRole() = role

    private fun isFree(): Boolean = status == EmployeeStatus.FREE

    fun setStatus(newStatus: EmployeeStatus) {
        status = newStatus
    }

    override fun canReceiveCall(processLevel: Role): Boolean {
        return (role == processLevel) && isFree()
    }

    override fun canHandleCall(targetLevel: Role): Boolean {
        return role == targetLevel
    }

    /**
     * return True mean this call is processed successfully
     * return False mean this call is not completed and must forward to the higher level
     */
    override fun processCall(request: CallRequest): Boolean {
        request.setRequestStatus(RequestStatus.BEING_HANDLED)
        sleep(CALL_DURATION)

        val succeed = canHandleCall(request.getTargetRole())
        request.completeCall(succeed, id)
        return succeed
    }
}

