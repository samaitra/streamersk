package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector

class MyFlatMapper: FlatMapFunction<String, Tuple2<String, Int>> {
    override fun flatMap(p0: String, p1: Collector<Tuple2<String, Int>>) {
        p1.collect(Tuple2(p0, 1))
    }
}
