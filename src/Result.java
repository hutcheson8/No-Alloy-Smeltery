import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class Result {
	private static final ArrayList<String[]> RECIPES = Main.RECIPES;
	private static final TreeSet<String> METALS = Main.METALS;
	private final ArrayList<String> todoList = new ArrayList<String>();
	private final Random randomNumberGenerator = new Random();
	private int numSmelteries = 0;
	private final ArrayList<String[]> smelteries = new ArrayList<String[]>();

	private final int randInt(int max) {
		return randomNumberGenerator.nextInt(max);
	}

	public Result() {
		todoList.addAll(METALS);
		do {
			numSmelteries++;
			ArrayList<String> smeltery = new ArrayList<String>();
			ArrayList<String> canAdd = new ArrayList<String>();
			canAdd.addAll(todoList);
			do {
				String adding = canAdd.get(randInt(canAdd.size()));
				smeltery.add(adding);
				canAdd.remove(adding);
				todoList.remove(adding);
				for (String[] recipe : RECIPES) {
					int mustRemoveIndex = 0;
					int remains = recipe.length;
					for (int x = 0; x < recipe.length; x++) {
						boolean isInSmeltery = false;
						for (String inSmeltery : smeltery)
							isInSmeltery = inSmeltery.equals(recipe[x]);
						if (isInSmeltery) {
							remains--;
						} else {
							mustRemoveIndex = x;
						}
					}
					if (remains == 1)
						canAdd.remove(recipe[mustRemoveIndex]);
				}
			} while (canAdd.size() > 0);
			smelteries.add(smeltery.toArray(new String[0]));
		} while (todoList.size() > 0);
	}

	public final int numSmelteries() {
		return numSmelteries;
	}

	public final String toString() {
		String toReturn = "Number of Smelteries: " + numSmelteries + '\n';
		for (String[] smeltery : smelteries) {
			toReturn += "Smeltery #" + smelteries.indexOf(smeltery) + '\n';
			for (String metal : smeltery) {
				toReturn += metal + '\n';
			}
		}
		return toReturn;
	}
}