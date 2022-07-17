package com.tanhoang.com

import com.tanhoang.com.callcenter.model.employee.Employee
import com.tanhoang.com.callcenter.model.employee.Role
import com.tanhoang.com.callcenter.model.request.CallRequest
import com.tanhoang.com.callcenter.service.CallProcessor
import com.tanhoang.com.utils.Utils
import org.junit.Assert
import org.junit.Test

import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun numberOfCompletedRequest_isEqualTo_numberOfRequest() {

        val respondents = listOf(
            Employee("1", "A", Role.RESPONDENT),
            Employee("2", "B", Role.RESPONDENT),
            Employee("3", "C", Role.RESPONDENT)
        )

        val managers = listOf(
            Employee("4", "G", Role.MANAGER),
            Employee("5", "H", Role.MANAGER),
        )

        val directors = listOf(
            Employee("6", "I", Role.DIRECTOR)
        )

        val callProcess = CallProcessor(
            respondents = LinkedList(respondents),
            managers = LinkedList(managers),
            directors = LinkedList(directors)
        )

        val calls = listOf(
            CallRequest("c1", "M1", targetRole = Role.RESPONDENT),
            CallRequest("c2", "M2", targetRole = Role.MANAGER),
            CallRequest("c3", "M3", targetRole = Role.RESPONDENT),
            CallRequest("c4", "M4", targetRole = Role.DIRECTOR),
            CallRequest("c5", "M5", targetRole = Role.RESPONDENT),
            CallRequest("c6", "M6", targetRole = Role.RESPONDENT),
            CallRequest("c7", "M7", targetRole = Role.MANAGER),
            CallRequest("c8", "M8", targetRole = Role.RESPONDENT),
            CallRequest("c9", "M9", targetRole = Role.DIRECTOR),
            CallRequest("c10", "M10", targetRole = Role.RESPONDENT),
            CallRequest("c11", "M11", targetRole = Role.MANAGER),
            CallRequest("c12", "M12", targetRole = Role.RESPONDENT),
            CallRequest("c13", "M13", targetRole = Role.DIRECTOR),
            CallRequest("c14", "M14", targetRole = Role.RESPONDENT),
            CallRequest("c15", "M15", targetRole = Role.RESPONDENT),
            CallRequest("c16", "M16", targetRole = Role.MANAGER),
            CallRequest("c17", "M17", targetRole = Role.MANAGER),
            CallRequest("c18", "M18", targetRole = Role.RESPONDENT),
            CallRequest("c19", "M19", targetRole = Role.MANAGER),
            CallRequest("c20", "M20", targetRole = Role.RESPONDENT),
        )

        for (call in calls) {
            callProcess.receiveCall(call)
        }

        Utils.sleep(5000L)

        Assert.assertEquals(calls.size, callProcess.getHandledRequestsCount())
    }
}