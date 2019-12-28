package com.example.projecte_uf1;

// there's probably a better way to implement a difficulty class than this

public class Difficulty{

    private static long time;
    private static String difficulty;
    private static int letterNo;

    public static long getTime() {
        return time;
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
                break;
            case "medium":
                time = DifficultyEnum.MEDIUM.getTime();
                letterNo = DifficultyEnum.MEDIUM.getLetterNo();
                break;
            case "hard":
                time = DifficultyEnum.HARD.getTime();
                letterNo = DifficultyEnum.HARD.getLetterNo();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    private enum DifficultyEnum {
        EASY(90000, 3),
        MEDIUM(60000, 5),
        HARD(30000, 6);

        private final long time;
        private final int letterNo;

        DifficultyEnum(long time, int letterNo){
            this.time = time;
            this.letterNo = letterNo;
        }

        public long getTime() {

            return time;
        }

        public int getLetterNo() {
            return letterNo;
        }

    }
}

