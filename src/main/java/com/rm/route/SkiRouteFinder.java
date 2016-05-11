package com.rm.route;

import com.rm.route.IO.InputReader;
import com.rm.route.IO.RoutePrinter;
import com.rm.route.Model.ElevationMap;
import com.rm.route.Model.RouteInfo;

import java.util.List;

public class SkiRouteFinder {

    public static void main(String []args) {
        try {
            List<List<Integer>> grid = InputReader.readAndParseInput("./sample_input_1000.txt");
            ElevationMap elevationMap = new ElevationMap(grid);
            RouteInfo routeInfo = elevationMap.findLongestRouteWithMaximumDrop();
            new RoutePrinter().printRoute(elevationMap, routeInfo);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
