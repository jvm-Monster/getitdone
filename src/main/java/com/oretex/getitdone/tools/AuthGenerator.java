package com.oretex.getitdone.tools;

import java.util.UUID;

public class AuthGenerator {
     private static String auth=UUID.randomUUID().toString();

     public static String getAuth() {
         
         return auth;
     }
 }
