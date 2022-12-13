package com.riobener.departmentservice.controller

import com.riobener.departmentservice.entity.Department
import com.riobener.departmentservice.service.DepartmentService
import io.opentracing.Span
import io.opentracing.SpanContext
import io.jaegertracing.internal.JaegerSpanContext
import io.opentracing.Tracer
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/departments")
class DepartmentController(
    private val departmentService: DepartmentService,
/*    private val tracer: Tracer,*/
) {

    @PostMapping("/")
    fun saveDepartment(@RequestParam name: String, address: String, code: String): Department {
/*        val sprintSpan: Span = tracer.buildSpan("saveDepartment")
            .withTag("name", name).withTag("address", address).withTag("code", code).start()
        sprintSpan.finish()*/
        return departmentService.saveDepartment(
            Department(
                id = UUID.randomUUID(),
                departmentName = name,
                departmentAddress = address,
                departmentCode = code
            )
        )
    }

    @GetMapping("/{id}")
    fun findDepartmentById(@PathVariable("id") departmentId: String): Department? {
/*        val sprintSpan: Span = tracer.buildSpan("findDepartmentById")
            .withTag("departmentId", departmentId).start()
        sprintSpan.finish()*/
        return departmentService.findDepartmentById(UUID.fromString(departmentId))
    }
}