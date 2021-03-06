package com.tanhoang.com.callcenter.service

import com.tanhoang.com.callcenter.model.employee.Employee
import com.tanhoang.com.callcenter.model.employee.EmployeeStatus
import com.tanhoang.com.callcenter.model.employee.Role
import com.tanhoang.com.callcenter.model.request.CallRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.NoSuchElementException

class CallProcessor(
    private val respondents: LinkedList<Employee>,
    private val managers: LinkedList<Employee>,
    private val directors: LinkedList<Employee>,
) {
    private val respondentRequests = LinkedList<CallRequest>()
    private val managerRequests = LinkedList<CallRequest>()
    private val directorRequests = LinkedList<CallRequest>()

    private val handledRequests = Vector<String>()
    fun getHandledRequestsCount() = handledRequests.size

    /**
     *  This function runs on a separate thread
     */
    fun receiveCall(call: CallRequest) = CoroutineScope(Dispatchers.Default).launch {
        // find out if is there any employee is freeing now
        val employee = getRequestExecutorByRequestLevel(call.getProcessesRole())
        if (employee != null) {
            processCall(employee, call)
        } else {
            // there is no freeing employee, so let handle it later
            pushCallInQueue(call)
        }
    }

    private fun getRequestExecutorByRequestLevel(requestLevel: Role): Employee? {
        return try {
            when (requestLevel) {
                Role.RESPONDENT -> respondents.first { it.canReceiveCall(requestLevel) }
                Role.MANAGER -> managers.first { it.canReceiveCall(requestLevel) }
                Role.DIRECTOR -> directors.first { it.canReceiveCall(requestLevel) }
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    private fun processCall(employee: Employee, call: CallRequest) {
        employee.setStatus(EmployeeStatus.BUSY)
        val succeed = employee.processCall(call)
        handleProcessedRequestResult(employee, succeed, call)
    }

    private fun getUnCompletedCallByEmployeeRole(employeeRole: Role): CallRequest? {
        return when (employeeRole) {
            Role.RESPONDENT -> respondentRequests.pollFirst()
            Role.MANAGER -> managerRequests.pollFirst()
            Role.DIRECTOR -> directorRequests.pollFirst()
        }
    }

    private fun handleProcessedRequestResult(employee: Employee, succeed: Boolean, call: CallRequest) {
        if (!succeed) {
            // find the higher lever employee to process the uncompleted or push it into a queue
            getRequestExecutorByRequestLevel(call.getProcessesRole())?.let { newEmployee ->
                processCall(newEmployee, call)
            } ?: pushCallInQueue(call)
        } else {
            // Complete the succeed call
            markRequestAsCompleted(call)
        }

        // continue to process other an uncompleted call if has any, otherwise reset employee status to FREE
        getUnCompletedCallByEmployeeRole(employee.getRole())?.let { newCall ->
            processCall(employee, newCall)
        } ?: employee.setStatus(EmployeeStatus.FREE)
    }

    private fun markRequestAsCompleted(call: CallRequest) {
        call.printLog()
        handledRequests.add(call.getId())
        System.out.println("[${handledRequests.size}] requests completed: $handledRequests")
    }

    private fun pushCallInQueue(request: CallRequest) {
        when (request.getProcessesRole()) {
            Role.RESPONDENT -> respondentRequests.add(request)
            Role.MANAGER -> managerRequests.add(request)
            Role.DIRECTOR -> directorRequests.add(request)
        }
    }
}
