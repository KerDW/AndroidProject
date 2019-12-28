package com.example.projecte_uf1;

public class Difficulty{

    private static long time;
    private static String difficulty;

    public static long getTime() {
        return time;
    }

    public static void setDifficulty(String d) {
        difficulty = d;
        switch(difficulty){
            case "easy":
                time = DifficultyEnum.EASY.getTime();
                break;
            case "medium":
                time = DifficultyEnum.MEDIUM.getTime();
                break;
            case "hard":
                time = DifficultyEnum.HARD.getTime();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    private enum DifficultyEnum {
        EASY(90000),
        MEDIUM(60000),
        HARD(30000);

        private final long time;

        DifficultyEnum(int time){
            this.time = time;
        }

        public long getTime() {
            return time;
        }

    }
}

