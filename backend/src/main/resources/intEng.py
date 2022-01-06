import sys
import json
import numpy as np

from metpy.interpolate import interpolate_to_grid

if __name__ == "__main__":
    data = json.loads(sys.stdin.read())
    lon, lat, value = np.array([x["lon"] for x in data]), np.array([x["lat"] for x in data]), np.array(
        [x["value"] for x in data])

    gx, gy, img = interpolate_to_grid(lon, lat, value, hres=0.2, interp_type='linear',
                                      boundary_coords={"west": 13, "south": 42, "east": 20,
                                                       "north": 47})  # , boundary_coords= {"west": 853623.1261727292, "south": 4764880.149561648, "east": 1269454.6407286846, "north": 5154135.508919207})#

    result = []
    for i in range(gx.shape[0]):
        for j in range(gx.shape[1]):
            if not np.isnan(img[i, j]):
                result.append({"lon": gx[i, j], "lat": gy[i, j], "value": img[i, j]})

    print(result)
