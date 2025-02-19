package org.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainServices {
    private Train train;
    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String trainPath = "app/src/main/java/org/booking/localDb/trains.json";

    public List<Train> loadTrain() throws IOException {
        File file = new File(trainPath);
        trainList = objectMapper.readValue(trainPath, new TypeReference<List<Train>>() {
        });
        return trainList;
    }

    public TrainServices(Train train) throws IOException {
        this.train = train;
        trainList = loadTrain();
    }

    public TrainServices() throws IOException {
        trainList = loadTrain();
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream().filter(train1 -> validTrain(train1, source, destination)).collect(Collectors.toList());
    }

    public void addTrain(Train newTrain) {
        Optional<Train> existingTrain = trainList.stream().filter(train1 -> train1.getTrainId().equalsIgnoreCase(newTrain.getTrainId())).findFirst();
        if (existingTrain.isPresent()) {
            updateTrain(newTrain);
        }
        else {
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    public void updateTrain(Train updatedTrain) {
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId())).findFirst();
        if (index.isPresent()) {
            trainList.set(index.getAsInt(), updatedTrain);
            saveTrainListToFile();
        }
        else {
            trainList.add(updatedTrain);
            saveTrainListToFile();
        }
    }

    private void saveTrainListToFile() {
        try{
            objectMapper.writeValue(new File(trainPath), trainList);
        } catch (IOException e) {
            System.out.println("Error while saving train list");
        }

    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();
        if (stations.contains(source) && stations.contains(destination)) {
            if (stations.indexOf(source) < stations.indexOf(destination)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
        else {
            return Boolean.FALSE;
        }
    }

}




