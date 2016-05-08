package com.redmart.route.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MapPoint {
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final Integer elevation;
}
