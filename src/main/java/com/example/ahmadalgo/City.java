/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/11/2023
 * Time: 12:16 AM
 * Project Name: ahmadAlgo
 */

package com.example.ahmadalgo;

import java.io.*;
import java.util.*;

class City {
    String name;
    int petrolCost;
    int hotelCost;
    HashMap<City, Integer> neighbours;
    City previousCity;
    public City(String name) {
        this.name = name;
        this.neighbours = new HashMap<>();
    }
}



