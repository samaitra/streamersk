package com.example.streamersk

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

fun main() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("Saikat", "Denis", "Tom", "Jerry", "Calvin")
            .flatMap(MyStrLengthFunction())
            .print()
    env.execute()
}