package com.demo.springboot.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public class ValidationPesel {
    private final byte[] PESEL = new byte[11];

    public ResponseEntity<Void> validation(String pesel) {
        if (pesel.length() != 11) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            for (int i = 0; i < 11; i++) {
                PESEL[i] = Byte.parseByte(pesel.substring(i, i + 1));
            }
            if (checkSum()) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    private boolean checkSum() {
        int sum = 1 * PESEL[0] +
                3 * PESEL[1] +
                7 * PESEL[2] +
                9 * PESEL[3] +
                1 * PESEL[4] +
                3 * PESEL[5] +
                7 * PESEL[6] +
                9 * PESEL[7] +
                1 * PESEL[8] +
                3 * PESEL[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;
        if (sum == PESEL[10]) {
            return true;
        } else {
            return false;
        }
    }
}
