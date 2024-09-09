import java.util.*;

public class Car {
    public final int WIDTH = 6;
    public final int HEIGHT = 6;

    private String[][] body = new String[WIDTH + 2][HEIGHT + 2];
    private int[] freeRowCellsCounters = new int[HEIGHT + 2];

    public Car() {
        setBorders();
        Arrays.fill(freeRowCellsCounters, WIDTH);
    }

    public String[][] getBody() {
        return body;
    }

    public void setBody(String[][] body) {
        this.body = body;
    }

    private void setBorders() {
        for (int i = 0; i < HEIGHT + 2; i++) {
            for (int j = 0; j < WIDTH + 2; j++) {
                if (i == 0 || i == HEIGHT + 1 || j == 0 || j == WIDTH + 1) {
                    body[i][j] = "+";
                }
                else {
                    body[i][j] = " ";
                }
            }
        }
    }

    public int[] getFreeRowCellsCounters() {
        return freeRowCellsCounters;
    }

    public void setFreeRowCellsCounters(int[] freeRowCellsCounters) {
        this.freeRowCellsCounters = freeRowCellsCounters;
    }

    public int getFirstFreeCellInRow(int row) {
        int firstFreeCell = WIDTH + 1;
        for (int i = 1; i < HEIGHT + 2; i++) {
            if (body[row][i].equals(" ")) {
                firstFreeCell = i;
                break;
            }
        }
        return firstFreeCell;
    }

    public void fillBody(List<Parcel> parcels) {
        int currRow = fillLayer(parcels, HEIGHT);
        while (currRow > 0) {
            currRow = fillLayer(parcels, currRow);
        }
    }

    private int fillLayer(List<Parcel> parcels, int row) {
        Iterator<Parcel> iterator = parcels.iterator();
        while (iterator.hasNext()) {
            Parcel parcel = iterator.next();

            boolean suitableWidth = isWidthSuitable(row, parcel);
            boolean suitableHeight = isHeightSuitable(row, parcel);
            if (suitableWidth && suitableHeight) {
                int firstFreeCell = getFirstFreeCellInRow(row);

                boolean suitableBottom = isBottomSuitable(row, firstFreeCell, parcel);
                if (suitableBottom) {
                    putParcel(row, firstFreeCell, parcel);
                    iterator.remove();
                }
            }
        }
        return --row;
    }

    public void putParcel(int row, int firstFreeCell, Parcel parcel) {
        for (int i = parcel.getHeight() - 1; i >= 0; i--) {
            for (int j = firstFreeCell; j < firstFreeCell + parcel.getLayerWidths().get(i); j++) {
                body[row + i - parcel.getHeight() + 1][j] = parcel.getValue();
                freeRowCellsCounters[row + i - parcel.getHeight() + 1]--;
            }
        }
    }

    public boolean isWidthSuitable(int row, Parcel parcel) {
        return parcel.getBottom() <= freeRowCellsCounters[row];
    }

    public boolean isHeightSuitable(int row, Parcel parcel) {
        return row - parcel.getHeight() >= 0;
    }

    public boolean isBottomSuitable(int row, int firstFreeCell, Parcel parcel) {
        int bottomCounter = 0;
        for (int i = firstFreeCell; i < firstFreeCell + parcel.getBottom(); i++) {
            if (!body[row + 1][i].equals(" ")) {
                bottomCounter++;
            }
        }
        return bottomCounter >= parcel.getBottom() / 2 + 1;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        for (String[] strings : body) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < body[0].length; j++) {
                sb.append(strings[j]);
            }
            sj.add(sb.toString());
        }
        sj.add("");
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.deepEquals(body, car.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WIDTH, HEIGHT, Arrays.deepHashCode(body), Arrays.hashCode(freeRowCellsCounters));
    }
}
