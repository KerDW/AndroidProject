package com.example.projecte_uf1;

// there's probably a better way to implement a difficulty class than this

public class Difficulty{

    private static String difficulty;

    private static long time;
    private static int letterNo;
    private static int animationTime;
    private static int checkBoxNo;

    public static long getTime() {
        return time;
    }

    public static int getAnimationTime() {
        return animationTime;
    }
    public static int getLetterNo() {
        return letterNo;
    }

    public static int getCheckBoxNo(){ return checkBoxNo; }

    public static void setDifficulty(String d) {
        difficulty = d;
        switch(difficulty){
            case "easy":
                time = DifficultyEnum.EASY.getTime();
                letterNo = DifficultyEnum.EASY.getLetterNo();
                animationTime = DifficultyEnum.EASY.getAnimationTime();
                checkBoxNo = DifficultyEnum.EASY.getCheckboxNo();
                break;
            case "medium":
                time = DifficultyEnum.MEDIUM.getTime();
                letterNo = DifficultyEnum.MEDIUM.getLetterNo();
                animationTime = DifficultyEnum.MEDIUM.getAnimationTime();
                checkBoxNo = DifficultyEnum.MEDIUM.getCheckboxNo();
                break;
            case "hard":
                time = DifficultyEnum.HARD.getTime();
                letterNo = DifficultyEnum.HARD.getLetterNo();
                animationTime = DifficultyEnum.HARD.getAnimationTime();
                checkBoxNo = DifficultyEnum.HARD.getCheckboxNo();
                break;
            case "extreme":
                time = DifficultyEnum.EXTREME.getTime();
                letterNo = DifficultyEnum.EXTREME.getLetterNo();
                animationTime = DifficultyEnum.EXTREME.getAnimationTime();
                checkBoxNo = DifficultyEnum.EXTREME.getCheckboxNo();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    private enum DifficultyEnum {
        EASY(90000, 3, 200, 2),
        MEDIUM(60000, 5, 250, 4),
        HARD(30000, 6, 300, 5),
        EXTREME(100000, 7, 1000, 7);

        private final long time;
        private final int letterNo;
        private final int animationTime;
        private final int checkBoxNo;

        DifficultyEnum(long time, int letterNo, int animationTime, int checkBoxNo){
            this.time = time;
            this.letterNo = letterNo;
            this.animationTime = animationTime;
            this.checkBoxNo = checkBoxNo;
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

        public int getCheckboxNo() { return checkBoxNo; }

    }
}

