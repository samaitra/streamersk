package com.example.streamersk

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.api.java.tuple.Tuple2
import java.util.HashMap

class Formatter : MapFunction<Tuple2<String, Int>, Map<String, Int>> {
    override fun map(tuple2: Tuple2<String, Int>): Map<String, Int> {
        val myWordMap = HashMap<String, Int>()
        myWordMap[tuple2.f0] = tuple2.f1
        return myWordMap
    }
}