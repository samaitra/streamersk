package com.example.streamersk

import org.apache.commons.lang3.StringUtils.isNotBlank
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.ignite.sink.flink.IgniteSink
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.Properties

fun main(args: Array<String>) {
    //load configurations
    val appProperties = Properties()
    appProperties.load(config(args[0]))

    //start IgniteSink instance
    val igniteSink = sink(appProperties)

    // get the execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment()

    // get input data by connecting to kafka
    val text = env.addSource(FlinkKafkaConsumer010<String>(
            appProperties.getProperty("topic"), SimpleStringSchema(), appProperties))

    // parse the data, group it, window it, and aggregate the counts
    val windowCounts = text
            .flatMap(Splitter())
            .keyBy(0)
            .timeWindow(Time.seconds(10))
            .sum(1)
            .map(Formatter())

    windowCounts.addSink(igniteSink)
    env.execute("Stream Window Item Count")
}

private fun sink(appProperties: Properties): IgniteSink<Map<String, Int>> {
    //start IgniteSink instance
    val igniteSink = IgniteSink<Map<String, Int>>(appProperties.getProperty("ignite.cache"),
            appProperties.getProperty("ignite.config"))
    igniteSink.setAllowOverwrite(true)
    igniteSink.setAutoFlushFrequency(5L)
    val p = Configuration()
    igniteSink.open(p)
    return igniteSink
}

private fun config(configPath: String): InputStream {
    if (isNotBlank(configPath)) {
        return FileInputStream(configPath)
    } else
        throw FileNotFoundException()
}