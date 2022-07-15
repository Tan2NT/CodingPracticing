package com.tanhoang.com.service

import android.telecom.Call
import android.util.Log
import com.tanhoang.com.model.employee.Employee
import com.tanhoang.com.model.employee.Role
import com.tanhoang.com.model.request.CallRequest
import com.tanhoang.com.model.request.RequestStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CallProcessor(
    private val respondents: LinkedList<Employee>,
    private val managers: LinkedList<Employee>,
    private val directors: LinkedList<Employee>,
) {
    private val respondentRequests = LinkedList<CallRequest>()
    private val managerRequests = LinkedList<CallRequest>()
    private val directorRequests = LinkedList<CallRequest>()
    private val handledRequests = LinkedList<CallRequest>()

    private var isRunning = true

    fun receiveCall(call: CallRequest) {
        respondentRequests.push(call)
    }

    fun execute() {
        val employees = respondents + managers + directors
        for (employee in employees) {
            GlobalScope.launch(Dispatchers.Default) {
                startCallHandleThread(employee.getRole())
            }
        }
    }

    private fun startCallHandleThread(employeeRole: Role) {
        Log.d("TDebug", "start a worker thread for employee ..... ")
        while (isRunning) {
            val request = getUnProcessedCallByEmployeeRole(employeeRole)
            request?.let {
                processCall(it)
            }
        }
    }

    private fun getUnProcessedCallByEmployeeRole(employeeRole: Role): CallRequest? {
        return when (employeeRole) {
            Role.RESPONDENT -> respondentRequests.pollLast()
            Role.MANAGER -> managerRequests.pollLast()
            Role.DIRECTOR -> directorRequests.pollLast()
        }
    }

    private fun processCall(request: CallRequest) {
        request.setRequestStatus(RequestStatus.HANDLING)
        while (request.isBeingHandled()) {
            getRequestExecutor(request.nextProcessesRole)?.disPatchCall(request)
        }

        handleResult(request)
    }

    private fun getRequestExecutor(requestLevel: Role): Employee? {
        return try {
            when (requestLevel) {
                Role.RESPONDENT -> respondents.first { it.canHandle(requestLevel) }
                Role.MANAGER -> managers.first { it.canHandle(requestLevel) }
                Role.DIRECTOR -> directors.first { it.canHandle(requestLevel) }
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    private fun handleResult(request: CallRequest) {
        if (request.hasCompleted()) {
            handledRequests.push(request)
        } else {
            when (request.nextProcessesRole) {
                Role.RESPONDENT -> respondentRequests.addLast(request)
                Role.MANAGER -> managerRequests.addLast(request)
                Role.DIRECTOR -> directorRequests.addLast(request)
            }
        }
    }
}
