package com.example.streamersk

import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.java.tuple.Tuple2


class TimeAggregate : AggregateFunction<Tuple2<String, Int>, String, String> {
    override fun add(value: Tuple2<String, Int>, accumulator: String): String {
        return accumulator + value.f0 + ":" + value.f1 + "\n"
    }

    override fun getResult(accumulator: String): String {
        return accumulator.orEmpty()
    }

    override fun merge(a: String, b: String): String {
        return a + b
    }

    override fun createAccumulator(): String {
        return "";
    }
}