package com.ferumbot.main;

import com.ferumbot.mapper.impl.DefaultMapper;
import ru.hse.homework4.Mapper;

public class Main {

    public static void main(String[] args) {
        System.out.println("Mapper main");
        Mapper mapper = new DefaultMapper();
    }
}
