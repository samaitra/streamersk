package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector

fun main (args: Array<String>) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment();

    val stream = env.fromElements("Saikat", "Denis", "Ivan", "Amol", "Samantha")
            .flatMap(MyMapper())
            .keyBy(0)
    stream.print()
    env.execute()
}

class MyMapper : FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(value: String, out: Collector<Tuple2<String,Int>>) {

            out.collect(Tuple2(value, value.length))

    }
}
