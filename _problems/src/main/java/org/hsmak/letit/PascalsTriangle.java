package org.hsmak.letit;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        System.out.println(new PascalsTriangle().nextRow(List.of(1, 3, 3, 1)));
        System.out.println(new PascalsTriangle().generate(3));
    }

    public List<List<Integer>> generate(int numRows) {
        if(numRows == 0)
            return List.of();

        List<List<Integer>> allRows = new ArrayList<>();
        List<Integer> nthRow = List.of(1);
        allRows.add(nthRow);
        while (--numRows > 0) {
            nthRow = nextRow(nthRow);
            allRows.add(nthRow);
        }
        return allRows;
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
