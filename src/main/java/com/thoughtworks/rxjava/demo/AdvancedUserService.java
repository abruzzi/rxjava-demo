package com.thoughtworks.rxjava.demo;

import com.thoughtworks.rxjava.demo.models.Staff;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import rx.Observable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class AdvancedUserService {
    private final static String JIGSAW_ENDPOINT = "http://localhost:8080/staffs/";

    private Observable<Staff> fetchUserByName(String name) {
        String url = JIGSAW_ENDPOINT + name;

        Client client = ClientBuilder.newClient(new ClientConfig());

        client.property(ClientProperties.CONNECT_TIMEOUT, 10);
        client.property(ClientProperties.READ_TIMEOUT,    10);

        Invocation.Builder request = client.target(url).request(MediaType.APPLICATION_JSON);

        Observable<Staff> staff;

        try {
            staff = Observable.just(request.get(Staff.class));
        } catch (Exception ex) {
            staff = Observable.just(null);
        }

        return staff;
    }

    public Observable<List<Staff>> fetchAllUsers(String[] ids) {
        return Observable.from(ids)
                .flatMap(this::fetchUserByName)
                .toList();
    }

    public static void main(String[] args) {
        AdvancedUserService main = new AdvancedUserService();
        main.fetchAllUsers(new String[]{"jtqiu", "luliu", "jlou", "rongzhu"}).subscribe(staffs -> {
            for(Staff s: staffs) {
                System.err.println(s.getEmployeeId());
            }
        });
    }
}
