package com.riobener.userservice

import io.opentracing.Tracer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun initTracer(): Tracer {
        val samplerConfig = io.jaegertracing.Configuration.SamplerConfiguration().withType("const").withParam(1)
        val sender = io.jaegertracing.Configuration.SenderConfiguration().withAgentHost("host.docker.internal")
        val reporter = io.jaegertracing.Configuration.ReporterConfiguration().withSender(sender)
        return io.jaegertracing.Configuration.fromEnv("user-service").withSampler(samplerConfig).withReporter(reporter).tracer
    }
}