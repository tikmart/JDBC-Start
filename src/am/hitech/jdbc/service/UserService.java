package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.util.exceptionmessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

public class UserService {
    private final UserRepo userRepo = new UserRepo();

    public User createUser(User user) throws InternalServerError {
        int row = userRepo.createUserV1(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }

        return user;
    }


    public User getByUser(String userName) throws NotFoundException {
        if (userRepo.getByUser(userName) == null) {
            throw new NotFoundException(ErrorMessage.USER_NOT_FOUND);
        }
        return userRepo.getByUser(userName);
    }

    public User getById(int id) throws NotFoundException , InternalServerError{
        try {
            User user = userRepo.getById(id);

            if (user == null) {
                throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
            }
            return user;
        } catch (RuntimeException e) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }

    }


    public void updateUser(int id, String firstName, String lastName) throws InternalServerError {
        int row = userRepo.updateUser(id, firstName, lastName);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }


}
