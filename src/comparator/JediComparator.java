package comparator;

import models.Jedi;

import java.util.Comparator;

public class JediComparator implements Comparator<Jedi> {
    @Override
    public int compare(Jedi o1, Jedi o2) {
        Integer rankValue1=o1.getRank().getRankValue();
        Integer rankValue2=o2.getRank().getRankValue();
        if(rankValue1.compareTo(rankValue2)==0)
        {
          return o1.getName().compareTo(o2.getName());
        }
        return rankValue1.compareTo(rankValue2);
    }
}
