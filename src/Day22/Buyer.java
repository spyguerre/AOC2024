package Day22;

import utils.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Buyer {
    public long secret;
    private final List<Integer> prices;
    private final List<Integer> diffs;
    private final List<List<Integer>> sequences;

    public Buyer(long secret) {
        this(secret, 0);
    }

    public Buyer(long secret, int secretsToGenerate) {
        this.secret = secret;

        this.prices = new ArrayList<>();
        this.diffs = new ArrayList<>();
        this.sequences = new ArrayList<>();
        this.generateSecrets(secretsToGenerate);
    }

    public void evolve() {
        // Step 1
        secret = ((64 * secret) ^ secret) % 16777216;

        // Step 2
        secret = ((secret / 32) ^ secret) % 16777216;

        // Step 3
        secret = ((2048 * secret) ^ secret) % 16777216;
    }

    public void generateSecrets(int quantity) {
        if (quantity <= 0) {
            return;
        }

        if (prices.isEmpty()) {
            diffs.add(null);
        } else {
            diffs.add((int)secret%10 - prices.getLast());
        }
        prices.add((int)secret%10);
        evolve();
        generateSecrets(quantity-1);
    }

    public int getPriceWithSequence(List<Integer> sequence) {
        for (int i = 0; i < diffs.size() - sequence.size(); i++) {
            boolean valid = true;
            for (int j = 0; j < sequence.size(); j++) {
                if (!Objects.equals(sequence.get(j), diffs.get(i + j))) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return prices.get(i+3);
            }
        }

        return 0;
    }

    public List<Integer> getSequenceAt(int i) {
        List<Integer> sequence = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            sequence.add(diffs.get(i+j));
        }
        return sequence;
    }

    public List<List<Integer>> getSequences(Map<Object, Long> sequencesOccurences) {
        if (!sequences.isEmpty()) { // Returns field reference if it was already initialized, else compute it.
            return sequences;
        }

        sequences.add(null); // Add nulls so that list has coherent size and indexes.
        for (int i = 1; i < diffs.size()-3; i++) {
            List<Integer> sequence = getSequenceAt(i);
            sequences.add(sequence);
            MapUtil.incr(sequencesOccurences, sequence);
        }
        sequences.add(null); sequences.add(null); sequences.add(null);

        return sequences;
    }
}
