package com.example;

import java.util.Random;

public class Jokes {

    public static final String TEXT_JOKE = "text_joke";

    String[] jokes = new String[]
            {"What's the difference between snowmen and snowladies? Snowballs",
                    "How do you make holy water? You boil the hell out of it.",
                    "I am a nobody, nobody is perfect, therefore I am perfect.",
                    "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all."};

    public String getInitialJoke(){
        int random = new Random().nextInt(jokes.length);
        return jokes[random];
    }
}
