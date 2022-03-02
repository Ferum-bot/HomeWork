package com.ferumbot.mapper.impl.classes;

import com.ferumbot.mapper.impl.enums.TestEnum;
import ru.hse.homework4.Exported;
import ru.hse.homework4.enums.NullHandling;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Exported(
    nullHandling = NullHandling.INCLUDE
)
public class ComplicatedClass2 {

    private List<String> notes = new ArrayList<>();

    private Set<Double> costs = new HashSet<>();

    private TestEnum testEnum = TestEnum.SECOND_VALUE;

    private LocalTime exportedTime;

    private String nullString;

    public ComplicatedClass2() {
        notes.add("TestNote1");
        notes.add("TestNote2");
        notes.add("TestNote3");
        notes.add("TestNote4");

        costs.add(123.3);
        costs.add(23D);
        costs.add(-123.1);
        costs.add(0.123);

        exportedTime = LocalTime.now();

        nullString = null;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Set<Double> getCosts() {
        return costs;
    }

    public void setCosts(Set<Double> costs) {
        this.costs = costs;
    }

    public TestEnum getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    public LocalTime getExportedTime() {
        return exportedTime;
    }

    public void setExportedTime(LocalTime exportedTime) {
        this.exportedTime = exportedTime;
    }

    public String getNullString() {
        return nullString;
    }

    public void setNullString(String nullString) {
        this.nullString = nullString;
    }
}
