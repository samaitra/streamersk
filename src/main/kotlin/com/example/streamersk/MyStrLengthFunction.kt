package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector


class MyStrLengthFunction: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(input: String, output: Collector<Tuple2<String, Int>>) {
        val tuple2 = Tuple2(input, input.length)
        output.collect(tuple2)
    }
}
