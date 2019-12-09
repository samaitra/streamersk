package com.example.streamersk

import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.util.Collector

class Splitter : FlatMapFunction<String, Tuple2<String, Int>> {
    @Throws(Exception::class)
    override fun flatMap(sentence: String, out: Collector<Tuple2<String, Int>>) {
        for (word in sentence.split(" ".toRegex())) {
            out.collect(Tuple2<String, Int>(word, 1))
        }
    }
}