package cn.attackme.myuploader.dao;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

public class Test extends Tester{

    @org.junit.Test
    public void Test1(){
        System.out.println(SpringVersion.getVersion());
        System.out.println(SpringBootVersion.getVersion());
    }

}
