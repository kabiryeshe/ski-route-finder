package com.rm.route.IO;

import com.rm.route.Model.ElevationMap;
import com.rm.route.Model.MapPoint;
import com.rm.route.Model.RouteInfo;

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
