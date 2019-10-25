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
	//create a method for checking if the item exist
	
	/*
public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
    int x = random.nextInt(clazz.getEnumConstants().length);
    return clazz.getEnumConstants()[x];
}
}*/
/* try this :  String name = Modes.mode1.name(); 
 * // Returns the name of this enum constant, exactly as declared in its enum declaration.
 * 
 */// i tried it return the name if u give him the name, its useless, i want a random value 

/*try to create a method toString() here for each element maybe*/
/* Exemple :
 

  public enum Country {

  DE {
    @Override
    public String toString() {
      return "Germany";
    }
  },
  IT {
    @Override
    public String toString() {
      return "Italy";
    }
  },
  US {
    @Override
    public String toString() {
      return "United States";
    }
  }

}
  */