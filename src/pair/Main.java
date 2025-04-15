package pair;


public class Main {
    public static void main(String[] args) {
        final Pair<String> firstStringPair = new Pair<>("Hello", "World");
        final Pair<String> secondStringPair = new Pair<>("Hello", "World");
        final Pair<Integer> integerPair = new Pair<>(1, 2);


        final boolean areEqualPairs1 = PairUtil.equals(firstStringPair, secondStringPair);
        System.out.println("First  pair: " + firstStringPair.getFirst() + " " + firstStringPair.getSecond()
                + ", Second  pair: " + secondStringPair.getFirst() + " " + secondStringPair.getSecond());

        System.out.print("Are pairs equaled? ");
        System.out.println(areEqualPairs1 ? "Yes" : "No");

        System.out.println();

        final boolean areEqualPairs2 = PairUtil.equals(firstStringPair, integerPair);
        System.out.println("First  pair: " + firstStringPair.getFirst() + " " + firstStringPair.getSecond() +
                ", Second pair: " + integerPair.getFirst() + " " + integerPair.getSecond());

        System.out.print("Are pairs equaled? ");
        System.out.println(areEqualPairs2 ? "Yes" : "No");

        System.out.println("----------------------------------------");

        System.out.println("Pair before swap: " + firstStringPair.getFirst() + " " + secondStringPair.getSecond());
        PairUtil.swap(firstStringPair);
        System.out.println("pair after swap: " + firstStringPair.getFirst() + " " + secondStringPair.getSecond());

        System.out.println("----------------------------------------");

        final Pair<String> copiedPair = PairUtil.copy(secondStringPair);
        System.out.println("Original pair: " + secondStringPair.getFirst() + " " + secondStringPair.getSecond());
        System.out.println("Copied  pair: " + copiedPair.getFirst() + " " + copiedPair.getSecond());
    }
}

