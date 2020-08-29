package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

fun main() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("SaikatSaikat", "Denis", "Alok", "Alexey", "Ivan")
    stream.flatMap(MyStrLen())
            .keyBy(0)
            .sum(1)
            .print()

    env.execute()
}

class MyStrLen: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(p0: String, p1: Collector<Tuple2<String, Int>>) {
         p1.collect(Tuple2(p0, p0.length))
    }
}
