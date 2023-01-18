package com.nyp.test.model.hashmap;

import java.util.Random;

/**
 * @projectName: java-benchmark
 * @package: com.nyp.test.model.hashmap
 * @className: KeyTest
 * @author: nyp
 * @description: TODO
 * @date: 2023/1/18 10:22
 * @version: 1.0
 */
public class KeyTestConflict {
    private String name;

    private Integer randomMax;

    private Random random;

    public KeyTestConflict() {
    }

    public KeyTestConflict(Random random){
        this.random = random;
        this.randomMax = 10000;
    }

    public KeyTestConflict(Random random, Integer randomMax){
        this.random = random;
        this.randomMax = randomMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyTestConflict keyTest = (KeyTestConflict) o;

        return name != null ? name.equals(keyTest.name) : keyTest.name == null;
    }

    @Override
    public int hashCode() {
        // return name != null ? name.hashCode() : 0;
        int code = random.nextInt(randomMax);
//        System.out.println("冲突  code = " + code);
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
