package org.example.storage.impl;

import org.example.entities.User;
import org.example.entities.impl.DefaultUser;
import org.example.storage.StorageServiceUser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserStorage implements StorageServiceUser {
    public static final String RESOURCES_FOLDER="resource";
    public static final String USERS="customer.csv";
    private static UserStorage instance;
    public static UserStorage getInstance(){
        if(instance ==null){
            instance =new UserStorage();
        }
        return instance;
    }
    @Override
    public void saveUser(User user) {
        try{
            Path path1 = new File(RESOURCES_FOLDER, USERS).toPath();
            Files.writeString(
                    path1,
                    System.lineSeparator()+ formatted(user),
                    StandardOpenOption.CREATE,StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String formatted(User user){
        return user.getId()+","+user.getFirstName()+","+user.getLastName()+","
                +user.getPassword()+","+user.getEmail();
    }
    @Override
    public List<User> loadUser() {
        try(var users=
                    Files.lines(Paths.get("resource/customer.csv"))) {
            return users.filter(Objects::nonNull).filter(line->!line.isEmpty()).map(s -> s.split(","))
                    .map(s -> new DefaultUser(Integer.parseInt(s[0]), s[1], s[2],s[3],s[4])).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
