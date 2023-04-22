package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.AddressRepo;
import am.hitech.jdbc.util.exceptionmessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;

public class AddressService {
    private final AddressRepo addressRepo = new AddressRepo();

    public Address getByUserId(int userId) throws NotFoundException {
        if (addressRepo.getByUserID(userId) == null) {
            throw new NotFoundException(ErrorMessage.ADDRESS_NOT_FOUND);
        }
        return addressRepo.getByUserID(userId);
    }

    public Address getById(int id) throws NotFoundException {
        if (addressRepo.getById(id) == null) {
            throw new NotFoundException(ErrorMessage.ADDRESS_NOT_FOUND);
        }

        return addressRepo.getById(id);
    }

    public List getAllAddress() throws InternalServerError {
        if (addressRepo.getAllAddress().isEmpty()) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }

        return addressRepo.getAllAddress();
    }

    public void deleteByUserID(int userId) throws NotFoundException {
        if (addressRepo.deleteByUserId(userId) == 0) {
            throw new NotFoundException(ErrorMessage.ADDRESS_NOT_FOUND);
        }

    }

    public void updateAddress(int userId, String country, String city, String street, int home) throws InternalServerError {
        if (addressRepo.updateAddress(userId,country,city,street,home) == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createAddress(Address address) throws InternalServerError {
        if (addressRepo.createAddress(address) == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }
}
