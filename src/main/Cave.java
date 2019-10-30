package main;

import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;

public enum Cave {
	CLE,LIVRE,EPEE,MANTEAU,PIERRE,BOUTEILLE,BRIQUET,MARTEAU,BOMBE,FLECHE,COUTEAU;

	  private static final List<Cave> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	  private static final int SIZE = VALUES.size();
	  private static final Random RANDOM = new Random();

	  public static Cave randomObj()  {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	  }
}