package lv.javaguru.travel.insurance;

import java.time.LocalDate;

public class tests {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2030, 3, 30);
        System.out.println(date.isBefore(LocalDate.now()));
    }
}
