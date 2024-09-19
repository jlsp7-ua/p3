package es.ua.dlsi.prog3.p1;
import java.util.ArrayList;

public class SummaryStatistics {
    private static int NEXT_ID = 1;
    private int id;
    private ArrayList<Integer> values;

    public SummaryStatistics() {
        id = NEXT_ID++;
        values = new ArrayList<>();
    }
    public SummaryStatistics(ArrayList<Integer> values) {
        id = NEXT_ID++;
        this.values = new ArrayList<>(values); // Copia defensiva
    }
    public SummaryStatistics(SummaryStatistics ss) {
        this.id = ss.id;
        this.values = new ArrayList<>(ss.values);
        NEXT_ID++;
    }
    public void add (int value) {
        values.add(value);
    }
    public int getId() {
        return id;
    }
    public Integer getAverage() {
        if (values.isEmpty()) return null;
        int suma = 0;
        for (Integer num: values) {
            suma += num.intValue();
        }
        return suma/values.size();
    }
    public int getMax() {
        return values.isEmpty() ? null : values.stream().reduce(Integer.MIN_VALUE, Math::max);
        // Tomamos `Integer.MIN_VALUE` para asegurarnos que coge el primer elemento de `values`
    }
    public int getMin() {
        return values.isEmpty() ? null : values.stream().reduce(Integer.MAX_VALUE, Math::min);
    }
    public int getSize() {
        return values.size();
    }
    public static int COUNT_INSTANCES() {
        return NEXT_ID-1;
    }
}
