package enums;

public enum Ranks {
        YOUNGLING(8),
        INITIATE(7),
        PADAWAN(6),
        KNIGHT_ASPIRANT(5),
        KNIGHT(4),
        MASTER(3),
        BATTLE_MASTER(2),
        GRAND_MASTER(1);

        private final int rankValue;

        Ranks(int rankValue) {
                this.rankValue = rankValue;
        }

        public int getRankValue() {
                return rankValue;
        }

}
