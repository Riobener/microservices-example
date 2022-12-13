package com.riobener.userservice.controller

import com.riobener.userservice.entity.JpaUser
import com.riobener.userservice.service.UserService
import io.opentracing.Span
import io.opentracing.Tracer
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val tracer: Tracer,
) {

    @PostMapping("/")
    fun saveUser(@RequestParam name: String, @RequestParam departmentId: String): JpaUser {
        val sprintSpan: Span = tracer.buildSpan("saveUser")
            .withTag("name", name).withTag("departmentId", departmentId).start()
        sprintSpan.finish()
        return userService.saveUser(
            JpaUser(
                id = UUID.randomUUID(),
                name = name,
                departmentId = UUID.fromString(departmentId)
            )
        )
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: String): JpaUser? {
        val sprintSpan: Span = tracer.buildSpan("getUserById")
            .withTag("id", userId).start()
        sprintSpan.finish()
        return userService.getUser(UUID.fromString(userId))
    }
}