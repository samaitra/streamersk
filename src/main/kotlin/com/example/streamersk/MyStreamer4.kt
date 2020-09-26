package com.example.streamersk

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

fun main() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("12345","12346", "12347", "12348", "12345", "12345")
            .flatMap(MyFlatMapper())
            .keyBy(0)
            .sum(1)
            .print()

    env.execute()
}