package dto;

import entities.Actor;

public class ActorDTO {
    private int id;
    private String name;
    private int age;

    public ActorDTO(Actor actor){
        this.id = actor.getId();
        this.name = actor.getName();
        this.age = actor.getAge();
    }
}
