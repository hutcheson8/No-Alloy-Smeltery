import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
	public static final ArrayList<String[]> RECIPES = new ArrayList<String[]>();
	public static final TreeSet<String> METALS = new TreeSet<String>();
	private static final int SECONDS = 60;

	public static final void main(String[] args) {
		Scanner recipeScanner = null;
		try {
			recipeScanner = new Scanner(new BufferedInputStream(new FileInputStream("recipes.txt")));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		while (recipeScanner.hasNextLine())
			RECIPES.add(recipeScanner.nextLine().split("\\+"));
		for (String[] recipe : RECIPES)
			for (String s : recipe)
				METALS.add(s);
		System.out.println("Number of Metals: " + METALS.size());
		for (String s : METALS)
			System.out.println(s);
		Result bestResult = new Result();
		for (long x = System.currentTimeMillis() + SECONDS * 1000; System.currentTimeMillis() < x;) {
			Result newResult = new Result();
			bestResult = bestResult.numSmelteries() < newResult.numSmelteries() ? bestResult : newResult;
		}
		System.out.println(bestResult);
	}
}