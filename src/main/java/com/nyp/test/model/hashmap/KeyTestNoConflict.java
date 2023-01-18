package com.nyp.test.model.hashmap;

/**
 * @projectName: al-alpha-zfw
 * @package: com.alpha.data.util
 * @className: KeyTest
 * @author: nyp
 * @description: TODO
 * @date: 2023/1/17 10:23
 * @version: 1.0
 */
public class KeyTestNoConflict {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyTestNoConflict keyTest = (KeyTestNoConflict) o;

        return name != null ? name.equals(keyTest.name) : keyTest.name == null;
    }

    @Override
    public int hashCode() {
         return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
