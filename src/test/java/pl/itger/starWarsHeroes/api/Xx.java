package pl.itger.starWarsHeroes.api;

import java.util.function.Function;

public class Xx {

    public static void main(String [] str){
        int a = 10;
        int b= 20;
        //doProcess(a, i -> System.out.println(i));
        Function<Integer, Integer> cx = (x) -> {
            return x*2;
        };
        System.out.println(cx.apply(3));
    }

}
