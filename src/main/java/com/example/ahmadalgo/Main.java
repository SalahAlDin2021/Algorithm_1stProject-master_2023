/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/11/2023
 * Time: 12:20 AM
 * Project Name: ahmadAlgo
 */

package com.example.ahmadalgo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/input.txt");
        System.out.println(file.exists());

        TravelCost travelCost = new TravelCost();
        travelCost.readData("src/main/input.txt");
        System.out.println(travelCost.findMinCost("Start", "End"));
        List<String> path = travelCost.findPathOfMinCost("Start", "End");
        System.out.println("Path: " + String.join(" -> ", path));

    }
}
