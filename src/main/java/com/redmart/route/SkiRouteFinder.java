package com.redmart.route;

import com.redmart.route.Model.ElevationMap;
import com.redmart.route.Model.RouteInfo;

import java.util.List;

public class SkiRouteFinder {

    public static void main(String []args) {

        String input = "4 4\n4 8 7 3\n2 5 9 3\n6 3 2 5\n4 4 1 6\n";
        List<List<Integer>> grid = InputReader.convertToGrid(input);
        ElevationMap elevationMap = new ElevationMap(grid);
        RouteInfo routeInfo = elevationMap.findLongestRouteWithMaximumDrop();
        new RoutePrinter().printRoute(elevationMap, routeInfo);

    }
}
