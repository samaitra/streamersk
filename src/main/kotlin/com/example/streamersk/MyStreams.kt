package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.util.Collector

fun main() {
    val env = StreamExecutionEnvironment.getExecutionEnvironment()
    val stream = env.fromElements("Saikat", "Tom", "Jerry", "Denis", "Evan")
    stream.flatMap(MyFlatmapStringLength())
            .print()
    env.execute()
}

class MyFlatmapStringLength : FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(input: String, out: Collector<Tuple2<String, Int>>) {
        out.collect(Tuple2(input, input.length))
    }

}
