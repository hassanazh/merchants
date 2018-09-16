import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.print("Enter number of merchants to get:");
        Scanner scanner = new Scanner(System.in);
        int merchants = scanner.nextInt();

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Indian Street Food,hospitality,100:83:80");
        strings.add("Barrels,hospitality,94:120:76");
        strings.add("Prime burger,hospitality,34:80:22");
        strings.add("Pippi longstockings socks,fashion,60:200:89");

        // map: merchant name -> Qi value
        // sort?
        List<Map.Entry<String, Integer>> sortedMap = getSortedMerchants(getMerchants(strings), merchants);
        for (Map.Entry<String, Integer> string : sortedMap) {
            System.out.println(string);
        }
    }

    // take merchants, calculate Qi return top m, sort by Qi and return
    private static Map<String, Integer> getMerchants(ArrayList<String> strings) {
        Map<String, Integer> mapWithQi = new HashMap<>();
        for (String string : strings) {
            // ["Indian Street Food", "hospitality", "100:83:80"]
            String[] stringElements = string.split(",");
            // ["100","83","80"]
            String[] cpvSalesCosts = stringElements[2].split(":");
            // Qi = 100 + (83 - 80)
            int qi = Integer.parseInt(cpvSalesCosts[0]) + (Integer.parseInt(cpvSalesCosts[1]) - Integer.parseInt(cpvSalesCosts[2]));
            mapWithQi.put(stringElements[0], qi);
        }

        return mapWithQi;
    }

    private static List<Map.Entry<String, Integer>> getSortedMerchants(Map<String, Integer> mapWithQi, int m) {
        return mapWithQi
                .entrySet()
                .stream()
                .sorted((t1, t2) -> Integer.compare(t2.getValue(), t1.getValue()))
                .limit(m)
                .collect(Collectors.toList());
    }
}
