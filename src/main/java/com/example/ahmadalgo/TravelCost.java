/**
 * Created By: Basil Assi
 * ID Number: 1192308
 * Date: 5/11/2023
 * Time: 12:18 AM
 * Project Name: ahmadAlgo
 */

package com.example.ahmadalgo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TravelCost {
    public HashMap<String, City> cities;

    public TravelCost() {
        this.cities = new HashMap<>();
    }

    public void readData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int numCities = Integer.parseInt(reader.readLine());
            String[] route = reader.readLine().split(",");

            for (int i = 0; i < numCities; i++) {
                String line = reader.readLine();
                if(line == null)
                    break;
                if(line.contains("[") || line.contains( "]")) {
                    line = line.replace("[", "").replace("]", "");
                }
                String[] parts = line.split(",");
                City city = cities.get(parts[0].trim());
                if (city == null) {
                    city = new City(parts[0].trim());
                    cities.put(parts[0].trim(), city);
                }

                for (int j = 1; j < parts.length; j += 3) {
                    City neighbour = cities.get(parts[j].trim());
                    if (neighbour == null) {
                        neighbour = new City(parts[j].trim());
                        cities.put(parts[j].trim(), neighbour);
                    }

                    int petrolCost = Integer.parseInt(parts[j+1].trim());
                    int hotelCost = Integer.parseInt(parts[j+2].trim());

                    city.neighbours.put(neighbour, petrolCost + hotelCost);
                    neighbour.neighbours.put(city, petrolCost + hotelCost);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file: " + filename);
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing data from file: " + filename);
            e.printStackTrace();
        }
    }


    public void printPathAndCost(String endCityName) {
        City currentCity = cities.get(endCityName);
        if (currentCity == null) {
            System.out.println("Invalid end city.");
            return;
        }

        // Print the path by iterating backwards from the end city
        List<String> path = new ArrayList<>();
        while (currentCity != null) {
            path.add(currentCity.name);
            currentCity = currentCity.previousCity;
        }

        // Reverse the path so it goes from start to end
        Collections.reverse(path);

        System.out.println("Path: " + String.join(" -> ", path));

    }


    public int findMinCost(String start, String end) {
        HashMap<City, Integer> costs = new HashMap<>();
        PriorityQueue<City> queue = new PriorityQueue<>((a, b) -> costs.get(a) - costs.get(b));

        City startCity = cities.get(start);
        costs.put(startCity, 0);
        queue.add(startCity);

        while (!queue.isEmpty()) {
            City city = queue.poll();

            if (city.name.equals(end)) {
                return costs.get(city);
            }

            for (City neighbour : city.neighbours.keySet()) {
                int newCost = costs.get(city) + city.neighbours.get(neighbour);

                if (!costs.containsKey(neighbour) || newCost < costs.get(neighbour)) {
                    costs.put(neighbour, newCost);
                    queue.add(neighbour);
                }
            }
        }
        return -1;
    }






    public List<String> findPathOfMinCost(String start, String end) {
        HashMap<City, Integer> costs = new HashMap<>();
        HashMap<City, City> previousCities = new HashMap<>();
        PriorityQueue<City> queue = new PriorityQueue<>((a, b) -> costs.get(a) - costs.get(b));

        City startCity = cities.get(start);
        costs.put(startCity, 0);
        queue.add(startCity);

        while (!queue.isEmpty()) {
            City city = queue.poll();

            if (city.name.equals(end)) {
                break; // Found the end city, exit the loop
            }

            for (City neighbour : city.neighbours.keySet()) {
                int newCost = costs.get(city) + city.neighbours.get(neighbour);

                if (!costs.containsKey(neighbour) || newCost < costs.get(neighbour)) {
                    costs.put(neighbour, newCost);
                    previousCities.put(neighbour, city);
                    queue.add(neighbour);
                }
            }
        }

        // Build the path by traversing the previous cities
        List<String> path = new ArrayList<>();
        City currentCity = cities.get(end);
        while (currentCity != null) {
            path.add(currentCity.name);
            currentCity = previousCities.get(currentCity);
        }

        Collections.reverse(path); // Reverse the path to go from start to end
        return path;
    }

}