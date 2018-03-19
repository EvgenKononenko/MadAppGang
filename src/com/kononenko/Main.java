package com.kononenko;

public class Main {
    private static String test1 = "with its powerful tools and dazzling effects,Keynote makes it Easy to create stunning and memorable presentations. ";
    private static String test2 = "See Who you ’re working with ... While you’re editing, a separate list lets you quickly see who else is in the presentation.";
    private static StringBuilder  sb;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String sumText = test1 + test2;
        String resultComa = checkPunctuation(sumText, ","); //в цикле можно перебрать любые знаки припинания
        String resultCapitalization = checkCapitalization(resultComa);
        String resultApostrophe = checkApostrophe(resultCapitalization);
        System.out.println(resultApostrophe);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("0."+timeConsumedMillis+" c");
    }

    public static String checkCapitalization(String text){
        sb = new StringBuilder();
        String str = text.toLowerCase();
        String[] sentences = sep(str, "\\.");
        for(String sentence: sentences){
            String trimSentence = sentence.trim();
            char[] litters = trimSentence.toCharArray();
            for(int i = 0; i<litters.length; i++){
                if(i == 0){
                    sb.append(" ");
                    String capLetter = String.valueOf(litters[0]).toUpperCase();
                    sb.append(capLetter);
                }else{
                    sb.append(litters[i]);
                }
            }
        }return sb.toString().trim();
    }

    public static String checkApostrophe(String text){
        sb = new StringBuilder();
        String[] words = text.split(" ");
        for(String element: words){
            char[] litters = element.toCharArray();
            if(litters[0] == '’' || litters[0] == '.'){
                sb.append(element);
            }else {
                sb.append(" " + element);
            }
        }
        return sb.toString().trim();
    }

    public static String checkPunctuation(String text, String symbol){
        sb = new StringBuilder();
        String[] wordsArray = text.split(" ");
        for(String element: wordsArray) {
            char[] letters = element.toCharArray();
            if (element.contains(symbol) && element.length()>1 && letters[letters.length-1] != symbol.charAt(0)) {
                for (int i = 0; i < letters.length; i++) {
                    if (letters[i] == symbol.charAt(0)) {
                        sb.append(letters[i] + " ");
                    }else{
                        sb.append(letters[i]);
                    }
                }
            }else if(element.length()==1 && element.equals(symbol)){
                sb.append(element);
            }else{
                sb.append(" " + element);
            }
        }
        return sb.toString().trim();
    }

    public static String[] sep (String str, String symbol){
        String[] res = str.split("(?<="+symbol+")");
        return res;
    }
}
