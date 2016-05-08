package com.redmart.route;

import com.redmart.route.Model.ElevationMap;
import com.redmart.route.Model.MapPoint;
import com.redmart.route.Model.RouteInfo;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RoutePrinter {

    public void printRoute(ElevationMap elevationMap, RouteInfo routeInfo) {

        List<MapPoint> mapPoints = elevationMap.getCompleteRoute(routeInfo);

        List<Integer> elevations = mapPoints.stream().map(MapPoint::getElevation).collect(toList());
        String routeString = elevations.stream().map(e -> e.toString()).collect(Collectors.joining(" "));

        System.out.println("Longest route with highest drop");
        System.out.println(routeString);

        System.out.println("Drop : " + routeInfo.getSlope());
        System.out.println("Length : " + routeInfo.getLength());
    }
}
