package etc;

import java.util.ArrayList;
import java.util.List;

public class Statistics {


    static List<Double> PAST_CAPITAL = new ArrayList<>();


    public static void gatherStats(double newCapital){
        PAST_CAPITAL.add(newCapital);
    }


    private static double CALC_GROWTH_IN_PERCENT(int numberOfPastDays, int indexOfCurrentDay) {

        if(indexOfCurrentDay - numberOfPastDays < 0)
            return 0;


        double tmp = PAST_CAPITAL.get(indexOfCurrentDay) / PAST_CAPITAL.get(indexOfCurrentDay - numberOfPastDays);

        if (tmp < 1) {
            tmp = -(2 - tmp);

            tmp = (tmp + 1) * 100;

        } else{
            tmp = (tmp - 1) * 100;
        }

        return Bank.cutDouble(tmp);
    }

    private static double CALC_GROWTH_IN_CURRENCY(int numberOfPastDays, int indexOfCurrentDay){

        if(indexOfCurrentDay - numberOfPastDays < 0)
            return 0;

        return PAST_CAPITAL.get(indexOfCurrentDay) - PAST_CAPITAL.get(indexOfCurrentDay - numberOfPastDays);
    }


    public static void SHOW_GROWTH_PER_DAY(){

        System.out.println("Growth per day:");

        for(int i = 1; i < PAST_CAPITAL.size()-1; i++)
            System.out.println("Day " + i + ": " + CALC_GROWTH_IN_PERCENT(1, i) + "% = " + CALC_GROWTH_IN_CURRENCY(1, i) + "€  Total Capital: " + PAST_CAPITAL.get(i) + "€");

    }

    public static void SHOW_GROWTH_PER_WEEK(){

        System.out.println("Growth per week:");

        for(int i = 7; i < PAST_CAPITAL.size()-1; i+=7)
            System.out.println("Week " + i/7 + ": " + CALC_GROWTH_IN_PERCENT(7, i) + "% = " + CALC_GROWTH_IN_CURRENCY(7, i) + "€  Total Capital: " + PAST_CAPITAL.get(i) + "€");
    }

    public static void SHOW_GROWTH_PER_MONTH(){

        System.out.println("Growth per month:");

        for(int i = 30; i < PAST_CAPITAL.size()-1; i+=30)
            System.out.println("Month " + i/30 + ": " + CALC_GROWTH_IN_PERCENT(30, i) + "% = " + CALC_GROWTH_IN_CURRENCY(30, i) + "€  Total Capital: " + PAST_CAPITAL.get(i) + "€");
    }

    public static void SHOW_GROWTH_PER_YEAR(){

        System.out.println("Growth per year:");

        for(int i = 365; i < PAST_CAPITAL.size()-1; i+=365)
            System.out.println("Year " + i/365 + ": " + CALC_GROWTH_IN_PERCENT(365, i) + "% = " + CALC_GROWTH_IN_CURRENCY(365, i) + "€  Total Capital: " + PAST_CAPITAL.get(i) + "€");
    }


}

