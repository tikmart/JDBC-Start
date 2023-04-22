package am.hitech.jdbc;

import am.hitech.jdbc.repo.AccountRepo;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.service.AccountService;
import am.hitech.jdbc.service.AddressService;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.Scanner;

public class Main {
    UserService userService = new UserService();
    UserRepo userRepo = new UserRepo();
    AccountService accountService = new AccountService();
    AccountRepo accountRepo = new AccountRepo();
    AddressService addressService = new AddressService();
    public static void main(String[] args) throws InternalServerError, NotFoundException {

        Main main = new Main();

        Scanner sc = new Scanner(System.in);

        System.out.print("Input phone number: ");
        String num = sc.next();

        System.out.print("input amount: ");
        int amount = sc.nextInt();

        System.out.println(main.accountService.findByPhone(num));

//        main.transfer(1,id,amount);






//        System.out.println(main.addressService.getById(1));
//        main.addressService.updateAddress(1,"Armenia", "Armavir", "Charents", 32);

    }


    private void transfer(int fromUserId, int toUserId, int amount) {
       accountService.transfer(fromUserId,toUserId,amount);
    }
}
