import java.util.*;

public class Parcel {
    private static Set<Parcel> templates = new HashSet<>();

    private String value;
    private List<Integer> layerWidths = new ArrayList<>();

    static {
        templates.add(new Parcel(1, new ArrayList<>(List.of(1))));
        templates.add(new Parcel(2, new ArrayList<>(List.of(2))));
        templates.add(new Parcel(3, new ArrayList<>(List.of(3))));
        templates.add(new Parcel(4, new ArrayList<>(List.of(4))));
        templates.add(new Parcel(5, new ArrayList<>(List.of(5))));
        templates.add(new Parcel(6, new ArrayList<>(List.of(3, 3))));
        templates.add(new Parcel(7, new ArrayList<>(List.of(3, 4))));
        templates.add(new Parcel(8, new ArrayList<>(List.of(4, 4))));
        templates.add(new Parcel(9, new ArrayList<>(List.of(3, 3, 3))));
    }

    public static boolean isValidForm(Parcel parcel) {
        return templates.contains(parcel);
    }

    public Parcel() {
    }

    public Parcel(int value, List<Integer> layerWidths) {
        this.value = String.valueOf(value);
        this.layerWidths = layerWidths;
    }

    public static Set<Parcel> getTemplates() {
        return templates;
    }

    public static void setTemplates(Set<Parcel> templates) {
        Parcel.templates = templates;
    }

    public void setLayerWidths(List<Integer> layerWidths) {
        this.layerWidths = layerWidths;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public List<Integer> getLayerWidths() {
        return layerWidths;
    }

    public int getHeight() {
        return layerWidths.size();
    }

    public int getTop() {
        return layerWidths.getFirst();
    }

    public int getBottom() {
        return layerWidths.getLast();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Objects.equals(value, parcel.value) && Objects.equals(layerWidths, parcel.layerWidths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, layerWidths);
    }
}
