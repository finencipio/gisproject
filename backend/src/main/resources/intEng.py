import sys
import json
import numpy as np
import argparse

from metpy.interpolate import interpolate_to_grid, interpolate_to_points


def extract_lon_lat_value(data: dict, keys: tuple = ("lat", "lon", "value")):
    return (np.array([x[key] for x in data]) for key in keys)


def grid():
    data = json.loads(sys.stdin.read())
    lon, lat, value = extract_lon_lat_value(data)

    gx, gy, img = interpolate_to_grid(lon, lat, value, hres=0.2, interp_type='linear',
                                      boundary_coords={"west": 13, "south": 42, "east": 20, "north": 47})
    _, _, img_2 = interpolate_to_grid(lon, lat, value, hres=0.2, interp_type='nearest',
                                      boundary_coords={"west": 13, "south": 42, "east": 20, "north": 47})

    img = np.where(np.isnan(img), img_2, img)

    result = []
    for i in range(gx.shape[0]):
        for j in range(gx.shape[1]):
            result.append({"lon": gx[i, j], "lat": gy[i, j], "value": img[i, j]})

    print(result)


def points():
    lines = ''.join(sys.stdin.readlines())
    data = json.loads(lines)
    lon_src, lat_src, value_src = extract_lon_lat_value(data['src'])
    lon_dst, lat_dst = extract_lon_lat_value(data['dst'], ("lat", "lon"))

    points_src = np.vstack((lon_src, lat_src)).T
    points_dst = np.vstack((lon_dst, lat_dst)).T

    int_values = interpolate_to_points(points_src, value_src, points_dst, interp_type='linear')
    int_values_2 = interpolate_to_points(points_src, value_src, points_dst, interp_type='nearest')

    int_values = np.where(np.isnan(int_values), int_values_2, int_values)

    result = []
    for i in range(lon_dst.shape[0]):
        result.append({"lon": lon_dst[i], "lat": lat_dst[i], "value": int_values[i]})

    print(result)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Interpolation enging.')
    parser.add_argument('-g', '--grid', dest='grid', action='store_true', help='Interpolate points to grid')
    parser.add_argument('-p', '--points', dest='points', action='store_true',
                        help='Interpolate points from other points')

    args = parser.parse_args()

    if args.points:
        points()
    elif args.grid:
        grid()
