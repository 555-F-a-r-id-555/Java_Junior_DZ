package org.example.DZ3.V2;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Department {
    @Setter
    @Getter
    private long departmentId;
    @Getter
    private String departmentName;
    private final int departmentNumber = 5;

    public Department(long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department() {
        setDepartmentName();
    }


    public void setDepartmentName() {
        this.departmentName = "departmentName #" + ThreadLocalRandom.current().nextInt(0, departmentNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(departmentName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
