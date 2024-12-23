package Day22;

import java.util.*;

public class Buyers {
    public List<Buyer> buyers;
    public Map<List<Integer>, Integer> sequencesTested;
    public Map<Object, Long> sequencesOccurences;

    public Buyers(List<String> input) {
        this(input, 0);
    }

    public Buyers(List<String> input, int secretsToGenerate) {
        buyers = new ArrayList<>();
        for (String line: input) {
            buyers.add(new Buyer(Long.parseLong(line), secretsToGenerate));
        }

        sequencesTested = new HashMap<>();
        sequencesOccurences = new HashMap<>();
    }

    public long getSecretSum() {
        long res = 0L;
        for (Buyer buyer : buyers) {
            res += buyer.secret;
        }
        return res;
    }

    public int getPrice(List<Integer> sequence) {
        // Return result if it is already known in the Map.
        if (sequencesTested.containsKey(sequence)) {
            return sequencesTested.get(sequence);
        }

        int price = 0;

        for (Buyer buyer : buyers) {
            price += buyer.getPriceWithSequence(sequence);
        }

        sequencesTested.put(sequence, price);

        return price;
    }

    private void computeSequencesPriorityMap() {
        for (Buyer buyer : buyers) {
            buyer.getSequences(sequencesOccurences);
        }
    }

    public long getBestPrice() {
        this.computeSequencesPriorityMap();

        int best = 0;
        while (!sequencesOccurences.isEmpty()) {
            List<Integer> sequence = (List<Integer>)Collections.max(sequencesOccurences.entrySet(), Map.Entry.comparingByValue()).getKey();
            if (sequencesOccurences.get(sequence) < best/9) {
                sequencesOccurences.remove(sequence);
                continue;
            }
            sequencesOccurences.remove(sequence);

            int currentPrice = getPrice(sequence);

            if (currentPrice > best) {
                best = currentPrice;
                System.out.println("New best price is " + best);
            }
        }

        return best;
    }
}
