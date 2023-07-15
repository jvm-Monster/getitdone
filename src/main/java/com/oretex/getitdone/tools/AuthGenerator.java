package com.oretex.getitdone.tools;

import java.util.UUID;

public class AuthGenerator {
 
     public String getAuth() {
         
         return UUID.randomUUID().toString();
     }
 }
