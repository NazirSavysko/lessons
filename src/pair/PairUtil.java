package pair;

public final class PairUtil {

    public static boolean Equals(final Pair<? extends Comparable<?>> firstPair, final Pair<? extends Comparable<?>> secondPair) {
        return firstPair.getFirst().equals(secondPair.getFirst())
                && secondPair.getSecond().equals(firstPair.getSecond());
    }

    public static <T> void Swap(final Pair<T> pair) {
        T temp = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(temp);
    }

    public static <T> Pair<T> Copy(final Pair<T> pair1) {
        return new Pair<>(pair1.getFirst(), pair1.getSecond());
    }
}