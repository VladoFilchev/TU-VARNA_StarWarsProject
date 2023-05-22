package EnumHandler;

import enums.Ranks;

public class RankHandler {
    public Ranks getNextRank(Ranks currentRank) {
        switch (currentRank) {
            case YOUNGLING:
                return Ranks.INITIATE;
            case INITIATE:
                return Ranks.PADAWAN;
            case PADAWAN:
                return Ranks.KNIGHT_ASPIRANT;
            case KNIGHT_ASPIRANT:
                return Ranks.KNIGHT;
            case KNIGHT:
                return Ranks.MASTER;
            case MASTER:
                return Ranks.BATTLE_MASTER;
            case BATTLE_MASTER:
                return Ranks.GRAND_MASTER;
            case GRAND_MASTER:
            default:
                return null;
        }
    }
    public Ranks getDemotedRank(Ranks currentRank) {
        switch (currentRank) {
            case GRAND_MASTER:
                return Ranks.BATTLE_MASTER;
            case BATTLE_MASTER:
                return Ranks.MASTER;
            case MASTER:
                return Ranks.KNIGHT;
            case KNIGHT:
                return Ranks.KNIGHT_ASPIRANT;
            case KNIGHT_ASPIRANT:
                return Ranks.PADAWAN;
            case PADAWAN:
                return Ranks.INITIATE;
            case INITIATE:
                return Ranks.YOUNGLING;
            case YOUNGLING:
            default:
                return null;
        }
    }
}
