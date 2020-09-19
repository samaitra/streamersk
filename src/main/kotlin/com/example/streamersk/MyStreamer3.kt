package com.example.streamersk

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

fun main(){
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("12345", "12346", "12457","123546","456463", "12346")
            .flatMap(MyFlatMapper())
            .keyBy(0)
            .sum(1)
            .print()

    env.execute()
}