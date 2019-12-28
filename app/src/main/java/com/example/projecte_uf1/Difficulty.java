package com.example.projecte_uf1;

// there's probably a better way to implement a difficulty class than this

public class Difficulty{

    private static long time;
    private static String difficulty;
    private static int letterNo;
    private static int animationTime;

    public static long getTime() {
        return time;
    }

    public static int getAnimationTime() {
        return animationTime;
    }
    public static int getLetterNo() {
        return letterNo;
    }

    public static void setDifficulty(String d) {
        difficulty = d;
        switch(difficulty){
            case "easy":
                time = DifficultyEnum.EASY.getTime();
                letterNo = DifficultyEnum.EASY.getLetterNo();
                animationTime = DifficultyEnum.EASY.getAnimationTime();
                break;
            case "medium":
                time = DifficultyEnum.MEDIUM.getTime();
                letterNo = DifficultyEnum.MEDIUM.getLetterNo();
                animationTime = DifficultyEnum.MEDIUM.getAnimationTime();
                break;
            case "hard":
                time = DifficultyEnum.HARD.getTime();
                letterNo = DifficultyEnum.HARD.getLetterNo();
                animationTime = DifficultyEnum.HARD.getAnimationTime();
                break;
            case "extreme":
                time = DifficultyEnum.EXTREME.getTime();
                letterNo = DifficultyEnum.EXTREME.getLetterNo();
                animationTime = DifficultyEnum.EXTREME.getAnimationTime();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    private enum DifficultyEnum {
        EASY(90000, 3, 200),
        MEDIUM(60000, 5, 250),
        HARD(30000, 6, 300),
        EXTREME(100000, 7, 1000);

        private final long time;
        private final int letterNo;
        private final int animationTime;

        DifficultyEnum(long time, int letterNo, int animationTime){
            this.time = time;
            this.letterNo = letterNo;
            this.animationTime = animationTime;
        }

        public int getAnimationTime() {
            return animationTime;
        }

        public long getTime() {
            return time;
        }

        public int getLetterNo() {
            return letterNo;
        }

    }
}

