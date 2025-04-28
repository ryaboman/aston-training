package Aston1Stage.graduation_project.controllers;

@FunctionalInterface
public interface IHandler<T> {
    T process();
}
