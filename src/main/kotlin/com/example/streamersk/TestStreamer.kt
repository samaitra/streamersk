package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector

fun main(args: Array<String>){
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("Saikat", "Denis", "Ivan", "Amol", "Samantha")
    stream.flatMap(MyStringLengthCounter()).keyBy(0).print()
    env.execute()
}

class MyStringLengthCounter: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(value: String, out: Collector<Tuple2<String, Int>>) {
        val tuple = Tuple2(value, value.length)
        out.collect(tuple)
    }

}
