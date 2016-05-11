package com.rm.route.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RouteInfo {
    @Getter
    private MapPoint firstPoint;
    @Getter
    private MapPoint nextPoint;
    @Getter
    private MapPoint lastPoint;
    @Getter
    int length;

    public RouteInfo(MapPoint firstPoint, RouteInfo pathAhead) {
        this.firstPoint = firstPoint;
        this.nextPoint = pathAhead.firstPoint;
        this.lastPoint = pathAhead.lastPoint;
        this.length = pathAhead.length + 1;
    }


    public RouteInfo(MapPoint onlyPoint) {
        this.firstPoint = onlyPoint;
        this.nextPoint = onlyPoint;
        this.lastPoint = onlyPoint;
        this.length = 1;
    }


    public int getSlope() {
        return firstPoint.getElevation() - lastPoint.getElevation();
    }


    public static RouteInfo longerSteeper(RouteInfo path1, RouteInfo path2) {
        if (null == path1) {
            return path2;
        }
        if (null == path2) {
            return path1;
        }
        if (path1.length > path2.length) {
            return path1;
        }
        if (path2.length > path1.length) {
            return path2;
        }

        //Equal Path length
        return path1.getSlope() > path2.getSlope() ? path1 : path2;
    }

}
