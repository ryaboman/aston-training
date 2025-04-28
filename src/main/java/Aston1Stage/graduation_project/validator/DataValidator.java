package Aston1Stage.graduation_project.validator;

import Aston1Stage.graduation_project.model.Bus;
import Aston1Stage.graduation_project.model.Student;
import Aston1Stage.graduation_project.model.User;

import java.util.Optional;

public class DataValidator {

    public static Optional<String> validateAndReturnBusNumber(String number) {
        String busNumberRegex = "^[A-Za-zА-Яа-я0-9]+$";
        if (number != null && !number.isEmpty() && number.matches(busNumberRegex)) {
            return Optional.of(number);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnBusModel(String model) {
        if (model != null && !model.isEmpty() && model.length() <= 50) {
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public static Optional<Integer> validateAndReturnMileage(String mileageInput) {
        try {
            int mileage = Integer.parseInt(mileageInput);

            if (mileage > 0) {
                return Optional.of(mileage);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Integer> validateAndReturnGroupNumber(String groupNumberString) {
        try {
            int groupNumber = Integer.parseInt(groupNumberString.trim());

            if (groupNumber > 0) {
                return Optional.of(groupNumber);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> validateAndReturnAverageGrade(String averageGradeString) {
        try {
            double averageGrade = Double.parseDouble(averageGradeString.trim());

            if (averageGrade >= 0.0 && averageGrade <= 5.0) {
                return Optional.of(averageGrade);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> validateAndReturnStudentBookNumber(String studentBookNumberString) {
        try {
            long studentBookNumber = Long.parseLong(studentBookNumberString.trim());

            if (studentBookNumber > 0) {
                return Optional.of(studentBookNumber);
            } else {
                return Optional.empty();
            }
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> validateAndReturnUserName(String name) {
        String userNameRegex = "[A-Za-zА-Яа-яЁё]+(?: [A-Za-zА-Яа-яЁё]+)*";
        if (name != null && !name.isEmpty() && name.matches(userNameRegex)) {
            return Optional.of(name);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnPassword(String password) {
        String passwordRegex = "^(?=.*[@$!%*?&])[\\S]{9,}$";
        if (password != null && !password.isEmpty() && password.matches(passwordRegex)) {
            return Optional.of(password);
        }
        return Optional.empty();
    }

    public static Optional<String> validateAndReturnEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email != null && !email.isEmpty() && email.matches(emailRegex)) {
            return Optional.of(email);
        }
        return Optional.empty();
    }

    public static Optional<Bus> validateAndReturnBusWithComment(String number, String model, String mileage) {

        Bus.BusBuilder busBuilder = Bus.create();

        Optional<String> numberOptional = validateAndReturnBusNumber(number);
        numberOptional.ifPresentOrElse(busBuilder::withNumber,
                () -> System.out.println("Некорректный номер автобуса."));

        Optional<String> modelOptional = validateAndReturnBusModel(model);
        modelOptional.ifPresentOrElse(busBuilder::withModel,
                () -> System.out.println("Некорректная модель автобуса."));

        Optional<Integer> mileageOptional = validateAndReturnMileage(mileage);
        mileageOptional.ifPresentOrElse(busBuilder::withMileage,
                () -> System.out.println("Некорректное значение пробега автобуса.")
        );

        Bus bus = busBuilder.build();

        return Optional.of(bus);
    }

    public static Optional<User> validateAndReturnUserWithComment(String userName, String userEmail, String userPassword) {

        User.UserBuilder userBuilder = User.create();

        Optional<String> nameOptional = validateAndReturnUserName(userName);
        nameOptional.ifPresentOrElse(userBuilder::withName,
                () -> System.out.println("Некорректное имя пользователя"));

        Optional<String> emailOptional = validateAndReturnEmail(userEmail);
        emailOptional.ifPresentOrElse(userBuilder::withEmail,
                () -> System.out.println("Некорректный email пользователя"));

        Optional<String> passwordOptional = validateAndReturnPassword(userPassword);
        passwordOptional.ifPresentOrElse(userBuilder::withPassword,
                () -> System.out.println("Некорректный пароль.\n" +
                        "Пароль должен содержать хотя бы один \n" +
                        "специальный символ (@$,!,%,*,?,&) и быть длиннее 8 символов.")
        );

        User user = userBuilder.build();

        return Optional.of(user);
    }

    public static Optional<Student> validateAndReturnStudentWithComment(
            String studentGroupNumber, String studentAverageGrade, String studentBookNumber) {

        Student.StudentBuilder studentBuilder = Student.create();

        Optional<Integer> groupNumberOptional = validateAndReturnGroupNumber(studentGroupNumber);
        groupNumberOptional.ifPresentOrElse(studentBuilder::withGroupNumber,
                () -> System.out.println("Некорректный номер группы."));

        Optional<Double> averageGradeOptional = validateAndReturnAverageGrade(studentAverageGrade);
        averageGradeOptional.ifPresentOrElse(studentBuilder::withAverageGrade,
                () -> System.out.println("Некорректный средний балл."));

        Optional<Long> studentBookNumberOptional = validateAndReturnStudentBookNumber(studentBookNumber);
        studentBookNumberOptional.ifPresentOrElse(studentBuilder::withStudentBookNumber,
                () -> System.out.println("Некорректный номер студенческого билета."));

        Student student = studentBuilder.build();
        return Optional.of(student);
    }
}