package com.example.streamersk

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

fun main(){
    val env = StreamExecutionEnvironment.getExecutionEnvironment()

    val stream = env.fromElements("Saikat", "Suman", "Val", "Denis", "Saikat")
    stream.flatMap(MyStrLen2())
            .keyBy(0)
            .sum(1)
            .print()
    env.execute()
}