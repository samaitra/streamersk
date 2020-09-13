package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector

class MyStrLen2: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(input: String, out: Collector<Tuple2<String, Int>>) {
        return out.collect(Tuple2(input, input.length))
    }

}