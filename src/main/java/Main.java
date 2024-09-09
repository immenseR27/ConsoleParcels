import java.io.*;
import java.util.*;

public class Main {
    static List<Parcel> parcels = new LinkedList<>();

    public static void main(String[] args) throws ParcelException {

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Parcel parcel = new Parcel();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    addParcel(parcel);
                    parcel = new Parcel();
                }
                else {
                    parcel.setValue(line.substring(0, 1));
                    checkLine(parcel, line);
                    parcel.getLayerWidths().add(line.length());
                }
            }
            addParcel(parcel);

            System.out.println("Выберите алгоритм (введите 1 или 2)");
            System.out.println("1. Упаковывать по порядку");
            System.out.println("2. Упаковывать с сортировкой");
            if (scanner.nextInt() == 2) {
                Comparator<Parcel> bottomComparator = Comparator.comparing(Parcel::getBottom);
                Comparator<Parcel> topComparator = Comparator.comparing(Parcel::getTop);
                parcels.sort(bottomComparator.reversed().thenComparing(topComparator.reversed()));
            }
            scanner.close();

            List<Car> cars = new ArrayList<>();

            while (!parcels.isEmpty()) {
                Car car = new Car();
                cars.add(car);
                car.fillBody(parcels);
            }

            for (Car car : cars) {
                System.out.println(car.toString());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла " + fileName);
        }
    }

    public static void addParcel(Parcel parcel) throws ParcelException {
        if (Parcel.isValidForm(parcel)) {
            parcels.add(parcel);
        }
        else throw new ParcelException(ParcelErrorCode.WRONG_PARCEL_FORM);

    }

    public static void checkLine(Parcel parcel, String line) throws ParcelException {
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != parcel.getValue().charAt(0)) {
                throw new ParcelException(ParcelErrorCode.WRONG_PARCEL_CONTENT);
            }
        }
    }
}
