package com.nyp.test.hashmap;

import com.nyp.test.model.hashmap.KeyTestConflict;
import com.nyp.test.model.hashmap.KeyTestNoConflict;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: java-benchmark
 * @package: com.nyp.test.hashmap
 * @className: Hashmap1Benchmark
 * @author: nyp
 * @description: TODO
 * @date: 2023/1/18 10:21
 * @version: 1.0
 */
//使用模式 默认是Mode.Throughput
@BenchmarkMode(Mode.AverageTime)
// 配置预热次数，默认是每次运行1秒，运行10次，这里设置为3次
@Warmup(iterations = 3, time = 1)
// 本例是一次运行4秒，总共运行3次，在性能对比时候，采用默认1秒即可
@Measurement(iterations = 3, time = 4)
// 配置同时起多少个线程执行
@Threads(1)
//代表启动多个单独的进程分别测试每个方法，这里指定为每个方法启动一个进程
@Fork(1)
// 定义类实例的生命周期，Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能
@State(value = Scope.Benchmark)
// 统计结果的时间单元
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class HashMapPutBenchmark {

//    @Param(value = {"1000","100000","1000000"})
    @Param(value = {"1000000"})
    int value;

    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
    public void testConflict(){
        HashMap map = new HashMap(1300000);
        Random random = new Random();
        for (int i = 0; i < value; i++) {
            KeyTestConflict test = new KeyTestConflict(random, 10000);
            test.setName(i+"");
            map.put(test, test);
        }
    }

    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
    public void testNoConflict(){
        HashMap map2 = new HashMap(1300000);
        for (int i = 0; i < value; i++) {
            KeyTestNoConflict test = new KeyTestNoConflict();
            test.setName(i+"");
            map2.put(test, test);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HashMapPutBenchmark.class.getSimpleName())
                .mode(Mode.All)
                // 指定结果以json结尾，生成后复制可去：http://deepoove.com/jmh-visual-chart/ 或https://jmh.morethan.io/ 得到可视化界面
                .result("hashmap_result_put_all.json")
                .resultFormat(ResultFormatType.JSON).build();

        new Runner(opt).run();
    }
}