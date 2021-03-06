package fer.gis.backend.web.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fer.gis.backend.model.enitity.temp.TemperatureRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

@RestController
public class InterpolationController {

    @PostMapping("/int_to_grid")
    public Collection<PointWithValue> getInterpolatedGrid(@RequestBody IntToGrid body) {
        ProcessBuilder builder = new ProcessBuilder("src/main/resources/cvenv/python", "./intEng.py", "-g");
        builder.directory(new File("src/main/resources"));
        try {
            Process exec = builder.start();
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()))) {
                Gson gson = new Gson();
                bw.write(gson.toJson(body.getData()));
            }
            try(var reader = new InputStreamReader(exec.getInputStream())) {
                Gson gson = new Gson();

                PointWithValue[] result = gson.fromJson(reader, PointWithValue[].class);

                return Arrays.asList(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/int_to_points")
    public Collection<PointWithValue> getInterpolatedPoints(@RequestBody IntToPoints body) {
        ProcessBuilder builder = new ProcessBuilder("src/main/resources/cvenv/python", "./intEng.py", "-p");
        builder.directory(new File("src/main/resources"));
        try {
            Process exec = builder.start();
            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()))) {
                Gson gson = new Gson();
                bw.write(gson.toJson(body));
            }
            try(var reader = new InputStreamReader(exec.getInputStream())) {
                Gson gson = new Gson();

                PointWithValue[] result = gson.fromJson(reader, PointWithValue[].class);

                return Arrays.asList(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IntToGrid implements Serializable {
        Collection<PointWithValue> data;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IntToPoints implements Serializable {
        Collection<PointWithValue> src;
        Collection<PointWithValue> dst;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PointWithValue implements Serializable {
        double lon;
        double lat;
        double value;
    }
}
