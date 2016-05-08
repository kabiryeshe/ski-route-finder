package com.redmart.route;

import com.redmart.route.Model.ElevationMap;
import com.redmart.route.Model.MapPoint;
import com.redmart.route.Model.Route;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RoutePrinter {

    public void printRoute(ElevationMap elevationMap, Route route) {

        List<MapPoint> mapPoints = elevationMap.getCompleteRoute(route);

        List<Integer> elevations = mapPoints.stream().map(MapPoint::getElevation).collect(toList());
        String routeString = elevations.stream().map(e -> e.toString()).collect(Collectors.joining(" "));

        System.out.println("Longest route with highest drop");
        System.out.println(routeString);

        System.out.println("Drop : " + route.getSlope());
        System.out.println("Length : " + route.getLength());
    }
}
