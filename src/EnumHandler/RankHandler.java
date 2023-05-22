package EnumHandler;

import enums.Ranks;

public class RankHandler {
    public Ranks getNextRank(Ranks currentRank) {
        return switch (currentRank) {
            case YOUNGLING -> Ranks.INITIATE;
            case INITIATE -> Ranks.PADAWAN;
            case PADAWAN -> Ranks.KNIGHT_ASPIRANT;
            case KNIGHT_ASPIRANT -> Ranks.KNIGHT;
            case KNIGHT -> Ranks.MASTER;
            case MASTER -> Ranks.BATTLE_MASTER;
            default -> Ranks.GRAND_MASTER;
        };
    }

    public Ranks getDemotedRank(Ranks currentRank) {
        return switch (currentRank) {
            case GRAND_MASTER -> Ranks.BATTLE_MASTER;
            case BATTLE_MASTER -> Ranks.MASTER;
            case MASTER -> Ranks.KNIGHT;
            case KNIGHT -> Ranks.KNIGHT_ASPIRANT;
            case KNIGHT_ASPIRANT -> Ranks.PADAWAN;
            case PADAWAN -> Ranks.INITIATE;
            default -> Ranks.YOUNGLING;
        };
    }
}
