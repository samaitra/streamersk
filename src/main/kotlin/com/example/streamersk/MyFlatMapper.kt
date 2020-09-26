package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector

class MyFlatMapper: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(input: String, output: Collector<Tuple2<String, Int>>) {
        output.collect(Tuple2(input, 1))
    }
}
