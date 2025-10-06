package Page343;

import java.util.Comparator;

class NameCompare implements Comparator<Mountain> {
    public int compare(Mountain one, Mountain two) {
        return one.name.compareTo(two.name);
    }
}

class HeightCompare implements Comparator<Mountain> {
    public int compare(Mountain one, Mountain two) {
        return two.height - one.height; // descending order
    }
}
