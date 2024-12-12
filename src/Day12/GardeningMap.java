package Day12;

import utils.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a gardening map.
 */
public class GardeningMap {
    /**
     * The garden matrix containing letters for each plant.
     */
    public List<List<Character>> garden;
    /**
     * Contains a mapping letter -> list of regions (which are Lists of coordinates).
     */
    private final Map<Character, List<List<Coordinate>>> dict;
    /**
     * Basic array of uppercase letters, used to iterate over plants names.
     */
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    /**
     * Array of directions to look for extension of the same region or fences.
     */
    private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Constructor for a gardening map.
     * @param input The matrix of plants.
     */
    public GardeningMap(List<List<Character>> input) {
        // Deepcopy the input argument.
        this.garden = new ArrayList<>();
        for (List<Character> row : input) {
            this.garden.add(new ArrayList<>(row));
        }

        // Build the this.dict.
        this.dict = new HashMap<>();
        this.buildDict();
    }

    /**
     * Initializes the this.dict with the correct regions.
     */
    private void buildDict() {
        for (Character c : alphabet) {
            this.dict.put(c, new ArrayList<>());
        }
        for (int i = 0; i < this.garden.size(); i++) {
            for (int j = 0; j < this.garden.get(i).size(); j++) {
                // Found a new region, explore it
                char c = this.garden.get(i).get(j);
                if (c != '.') {
                    List<Coordinate> region = new ArrayList<>();
                    region.add(new Coordinate(i, j));
                    this.garden.get(i).set(j, '.');
                    this.dict.get(c).add(region);
                    dfs(i, j, c, region);
                }
            }
        }
    }

    /**
     * Searches the region of the plant at coordinates (i, j) and containing plant c,
     * and adds the Coordinates found into region.
     * @param i The first coordinate to start searching at.
     * @param j The second coordinate to start searching at.
     * @param c The plant to identify.
     * @param region The list in which to add the new Coordinates found.
     */
    private void dfs(int i, int j, char c, List<Coordinate> region) {
        for (int[] direction : directions) {
            Coordinate newCoos = new Coordinate(i + direction[0], j + direction[1]);
            if (newCoos.isInMap(this.garden.size(), this.garden.getFirst().size())) {
                if (this.garden.get(newCoos.i).get(newCoos.j) == c) {
                    region.add(newCoos);
                    this.garden.get(newCoos.i).set(newCoos.j, '.');
                    dfs(newCoos.i, newCoos.j, c, region);
                }
            }
        }
    }

    /**
     * Searches for coordinate (i, j) in region.
     * @param i The first coordinate to look for.
     * @param j The second coordinate to look for.
     * @param region The list of Coordinates to look into.
     * @return whether the coordinate was found in the list.
     */
    private boolean isInRegion(int i, int j, List<Coordinate> region) {
        boolean found = false;
        Coordinate coos = new Coordinate(i, j);
        for (Coordinate coosIter : region) {
            found = coosIter.equals(coos);
            if (found) {break;}
        }
        return found;
    }

    /**
     * Computes the surface of a region of plants.
     * @param region The region of plants.
     * @return the region's surface.
     */
    private int getSurface(List<Coordinate> region) {
        return region.size();
    }

    /**
     * Computes the perimeter of a region of plants.
     * @param region The region of plants.
     * @return the region's perimeter.
     */
    private int getPerimeter(List<Coordinate> region) {
        int perimeter = 0;
        for (Coordinate coos : region) {
            for (int[] direction : directions) {
                // Looks for every side of every tile, whether it is an edge of the region.
                perimeter += this.isInRegion(coos.i+direction[0], coos.j+direction[1], region) ? 0 : 1;
            }
        }
        return perimeter;
    }

    /**
     * Computes the list of Fences surrounding a region.
     * @param region The list of Coordinates around which to get the fences.
     * @return the list of Fences found.
     */
    private List<Fence> getFences(List<Coordinate> region) {
        List<Fence> fences = new ArrayList<>();

        for (Coordinate coos : region) {
            for (int dir = 0; dir < 4; dir++) {
                if (!this.isInRegion(coos.i+directions[dir][0], coos.j+directions[dir][1], region)) {
                    fences.add(new Fence(coos.i, coos.j, dir));
                }
            }
        }

        return fences;
    }

    /**
     * Computes a new list of Fences, containing only one fence per side.
     * @param fences The list of fences to simplify.
     * @return a new list of fences, whith only one fence per side.
     */
    private List<Fence> simplifyFences(List<Fence> fences) {
        List<Fence> simplifiedFences = new ArrayList<>();

        while (!fences.isEmpty()) {
            Fence fence = fences.getFirst();
            simplifiedFences.add(fence);
            fence.isInList(fences, true);
            removeSimilarFences(fence, fences);
        }

        return simplifiedFences;
    }

    /**
     * Removes every unnecessary fence that is in the same side as fence from a list of Fences.
     * @param fence the fence to start iterationg from.
     * @param fences the list to remove fences from.
     */
    private void removeSimilarFences(Fence fence, List<Fence> fences) {
        for (int i = 1; i < this.garden.size(); i++) { // Remove fences with lower coordinates
            Fence fToCheck;
            if (fence.dir%2 == 1) { // Horizontal fences
                fToCheck = new Fence(fence.i, fence.j-i, fence.dir);
            } else { // Vertical fence
                fToCheck = new Fence(fence.i-i, fence.j, fence.dir);
            }
            if (!fToCheck.isInList(fences, true)) {break;} // Remove fences from list
        }
        for (int i = 1; i < this.garden.size(); i++) { // Remove fences with higher coordinates
            Fence fToCheck;
            if (fence.dir%2 == 1) { // Horizontal fences
                fToCheck = new Fence(fence.i, fence.j+i, fence.dir);
            } else { // Vertical fences
                fToCheck = new Fence(fence.i+i, fence.j, fence.dir);
            }
            if (!fToCheck.isInList(fences, true)) {break;} // Remove fences from list
        }
    }

    /**
     * Computes the number of sides of a region.
     * @param region The list of Coordinates to compute the sides of.
     * @return the number of sides of the region.
     */
    public int getNumberOfSides(List<Coordinate> region) {
        List<Fence> simplifiedFences = simplifyFences(getFences(region));
        return simplifiedFences.size();
    }

    /**
     * Computes the fencing price of a region, as described in the subject.
     * @param region The region to compute the fencing price of.
     * @param bulkDiscount Whether the company is qualified to bulk discount.
     * @return the fencing price of the region.
     */
    private int getRegionPrice(List<Coordinate> region, boolean bulkDiscount) {
        if (!bulkDiscount){
            return this.getPerimeter(region) * this.getSurface(region);
        } else {
            return this.getNumberOfSides(region) * this.getSurface(region);
        }
    }

    /**
     * Compute the fencing price of the whole garden.
     * @param bulkDiscount Whether the company is qualified to bulk discount.
     * @return the fencing price of the whole garden.
     */
    public int getPrice(boolean bulkDiscount) {
        int price = 0;
        for (char c : alphabet) {
            for (List<Coordinate> region : this.dict.get(c)) {
                price += this.getRegionPrice(region, bulkDiscount);
            }
        }
        return price;
    }
}
