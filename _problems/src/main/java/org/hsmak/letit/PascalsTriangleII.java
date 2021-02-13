package org.hsmak.letit;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public static void main(String[] args) {
        System.out.println(new PascalsTriangleII().nextRow(List.of(1, 3, 3, 1)));
        System.out.println(new PascalsTriangleII().getRow(33));
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> nthRow = List.of(1);
        while (rowIndex-- > 0)
            nthRow = nextRow(nthRow);
        return nthRow;
    }

    List<Integer> nextRow(List<Integer> currentRow) {
        List<Integer> nextRow = new ArrayList<>();
        nextRow.add(1);

        for (int i = 1; i < currentRow.size(); i++) {
            nextRow.add(currentRow.get(i - 1) + currentRow.get(i));
        }
        nextRow.add(1);
        return nextRow;
    }
}
