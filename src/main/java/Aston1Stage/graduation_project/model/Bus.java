package Aston1Stage.graduation_project.model;

import Aston1Stage.graduation_project.validator.DataValidator;

import java.util.Comparator;
import java.util.Optional;

public class Bus implements Comparable<Bus>, SerializableToCSVString {
    private String number;
    private String model;
    private int mileage;

    private Bus(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public static BusBuilder create() {
        return new BusBuilder();
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public int compareTo(Bus o) {
        return Comparator.comparing(Bus::getNumber)
                .thenComparing(Bus::getModel)
                .thenComparing(Bus::getMileage)
                .compare(this, o);
    }

    public int magicCompare(Bus o) {
        int thisMileage = this.getMileage();
        int oMileage = o.getMileage();

        if (thisMileage % 2 == 0 && oMileage % 2 == 0) {
            return Integer.compare(thisMileage, oMileage);
        } else {
            return 0;
        }
    }

    public static class BusBuilder {
        private String number = "";
        private String model = "";
        private int mileage;

        public BusBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder withMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }

    public static Optional<Bus> fromCSVString(String stringObjectCSV) {

        String[] busData = stringObjectCSV.split(";");
        if (busData.length != 3) {
            return Optional.empty();
        }

        String number = busData[0];
        String model = busData[1];
        String mileage = busData[2];

        BusBuilder busBuilder = Bus.create();

        Optional<String> numberOptional = DataValidator.validateAndReturnBusNumber(number);
        numberOptional.ifPresent(busBuilder::withNumber);

        Optional<String> modelOptional = DataValidator.validateAndReturnBusModel(model);
        modelOptional.ifPresent(busBuilder::withModel);

        Optional<Integer> mileageOptional = DataValidator.validateAndReturnMileage(mileage);
        mileageOptional.ifPresent(busBuilder::withMileage);

        Bus bus = busBuilder.build();

        return Optional.of(bus);
    }

    @Override
    public String toCSVString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number)
                .append(";")
                .append(model)
                .append(";")
                .append(mileage);

        return stringBuilder.toString();
    }
}