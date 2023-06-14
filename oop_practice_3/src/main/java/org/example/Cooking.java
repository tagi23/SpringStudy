package org.example;

public class Cooking {  //요리사

    public Cook makeCook(MenuItem menuitem){
        Cook cook = new Cook(menuitem);
        return cook;
    }
}
