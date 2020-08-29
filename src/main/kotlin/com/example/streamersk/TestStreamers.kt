package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector

fun main() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()

    env.fromElements("Saikat", "Denis", "Sam", "Ramesh", "Dinesh", "Gilfoyle", "Richard", "Monica", "Big Head")
            .flatMap(MyStrLengthCounter())
            .print()

    env.execute()
}

class MyStrLengthCounter: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(p0: String, p1: Collector<Tuple2<String, Int>>) {
        p1.collect(Tuple2(p0, p0.length))
    }
}